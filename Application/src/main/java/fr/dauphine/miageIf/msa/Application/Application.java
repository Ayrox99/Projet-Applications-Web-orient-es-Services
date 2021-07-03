package fr.dauphine.miageIf.msa.Application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Application {

	private JFrame frame;
	private JTextField TCSource;
	private JTextField TCDest;
	private JTextField TCTaux;
	private JTextField TCDate;
	private JTextField TDate;
	private JTextField TMontant;
	private JTextField TDest;
	private JTextField TSource;
	private JTextField TTaux;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		//Pour faire l'affichage des résultats de requêtes
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 223, 829, 511);
		panel.add(textArea);

//---------------------------------------------------------------------

		JLabel lblNewLabel = new JLabel("TAUX DE CHANGE :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 120, 24);
		panel.add(lblNewLabel);

		TCSource = new JTextField();
		TCSource.setBounds(183, 14, 86, 20);
		panel.add(TCSource);
		TCSource.setColumns(10);

		TCDest = new JTextField();
		TCDest.setBounds(324, 14, 86, 20);
		panel.add(TCDest);
		TCDest.setColumns(10);

		TCTaux = new JTextField();
		TCTaux.setBounds(462, 14, 86, 20);
		panel.add(TCTaux);
		TCTaux.setColumns(10);

		TCDate = new JTextField();
		TCDate.setBounds(601, 14, 86, 20);
		panel.add(TCDate);
		TCDate.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Source");
		lblNewLabel_1.setBounds(140, 17, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Dest");
		lblNewLabel_1_1.setBounds(285, 17, 46, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Taux");
		lblNewLabel_1_1_1.setBounds(425, 17, 46, 14);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Date");
		lblNewLabel_1_1_2.setBounds(569, 17, 46, 14);
		panel.add(lblNewLabel_1_1_2);

//---------------------------------------------------------------------

		JLabel lblTransaction = new JLabel("TRANSACTION :");
		lblTransaction.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTransaction.setBounds(10, 113, 120, 24);
		panel.add(lblTransaction);

		JLabel lblNewLabel_1_2 = new JLabel("Source");
		lblNewLabel_1_2.setBounds(119, 119, 46, 14);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_3 = new JLabel("Dest");
		lblNewLabel_1_1_3.setBounds(268, 119, 46, 14);
		panel.add(lblNewLabel_1_1_3);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Montant");
		lblNewLabel_1_1_1_2.setBounds(411, 119, 46, 14);
		panel.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Date");
		lblNewLabel_1_1_2_1.setBounds(569, 119, 46, 14);
		panel.add(lblNewLabel_1_1_2_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Taux");
		lblNewLabel_1_1_1_1.setBounds(714, 119, 46, 14);
		panel.add(lblNewLabel_1_1_1_1);

		TDate = new JTextField();
		TDate.setColumns(10);
		TDate.setBounds(601, 116, 86, 20);
		panel.add(TDate);

		TMontant = new JTextField();
		TMontant.setColumns(10);
		TMontant.setBounds(462, 116, 86, 20);
		panel.add(TMontant);

		TDest = new JTextField();
		TDest.setColumns(10);
		TDest.setBounds(303, 116, 86, 20);
		panel.add(TDest);

		TSource = new JTextField();
		TSource.setColumns(10);
		TSource.setBounds(166, 116, 86, 20);
		panel.add(TSource);

		TTaux = new JTextField();
		TTaux.setColumns(10);
		TTaux.setBounds(753, 116, 86, 20);
		panel.add(TTaux);


//---------------------------------------------------------------------

		JButton btnInserer = new JButton("Ins\u00E9rer");
		btnInserer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInserer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8000/exchange-rate/add-rate/source="+TCSource.getText()+"&dest="+TCDest.getText()+"&rate="+TCTaux.getText()+"&date="+TCDate.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText(text);
			}
		});
		btnInserer.setBounds(518, 68, 89, 23);
		panel.add(btnInserer);

		JButton btnToutRcuprer = new JButton("Tout r\u00E9cup\u00E9rer");
		btnToutRcuprer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnToutRcuprer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8000/exchange-rate/get-all";
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnToutRcuprer.setBounds(10, 68, 110, 23);
		panel.add(btnToutRcuprer);

		JButton btnRcuprerHistoriqueCouple = new JButton("R\u00E9cup\u00E9rer historique couple");
		btnRcuprerHistoriqueCouple.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRcuprerHistoriqueCouple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8000/exchange-rate/get/couple-history/source="+TCSource.getText()+"&dest="+TCDest.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnRcuprerHistoriqueCouple.setBounds(130, 68, 184, 23);
		panel.add(btnRcuprerHistoriqueCouple);

		JButton btnRcuprerTauxDeChange = new JButton("R\u00E9cup\u00E9rer taux de change");
		btnRcuprerTauxDeChange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRcuprerTauxDeChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8000/exchange-rate/get/rate/source="+TCSource.getText()+"&dest="+TCDest.getText()+"&date="+TCDate.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText(text);
			}
		});
		btnRcuprerTauxDeChange.setBounds(324, 68, 184, 23);
		panel.add(btnRcuprerTauxDeChange);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8000/exchange-rate/update-existing-rate/source="+TCSource.getText()+"&dest="+TCDest.getText()+"&date="+TCDate.getText()+"&new_rate="+TCTaux.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText(text);
			}
		});
		btnModifier.setBounds(618, 68, 89, 23);
		panel.add(btnModifier);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8000/exchange-rate/delete-existing-rate/source="+TCSource.getText()+"&dest="+TCDest.getText()+"&date="+TCDate.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText(text);
			}
		});
		btnSupprimer.setBounds(715, 68, 89, 23);
		panel.add(btnSupprimer);

//---------------------------------------------------------------------------------------------------

		JButton btnToutRecuperTransaction = new JButton("Tout r\u00E9cup\u00E9rer");
		btnToutRecuperTransaction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnToutRecuperTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8034/transaction/get-all";
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnToutRecuperTransaction.setBounds(10, 158, 110, 23);
		panel.add(btnToutRecuperTransaction);

		JSpinner spinnerMontant = new JSpinner();
		spinnerMontant.setModel(new SpinnerListModel(new String[] {"=", "<", "<=", ">", ">="}));
		spinnerMontant.setBounds(285, 159, 46, 20);
		panel.add(spinnerMontant);

		JButton btnFiltrerMontant = new JButton("Filtrer sur montant");
		btnFiltrerMontant.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnFiltrerMontant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8034/transaction/filter/montant"+ spinnerMontant.getValue() +TMontant.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnFiltrerMontant.setBounds(140, 158, 129, 23);
		panel.add(btnFiltrerMontant);

		JButton btnFiltrerSource = new JButton("Filtrer sur Source");
		btnFiltrerSource.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnFiltrerSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8034/transaction/filter/source="+TSource.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnFiltrerSource.setBounds(352, 158, 119, 23);
		panel.add(btnFiltrerSource);

		JButton btnFiltreDest = new JButton("Filtrer sur Dest");
		btnFiltreDest.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnFiltreDest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8034/transaction/filter/dest="+TDest.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnFiltreDest.setBounds(481, 158, 110, 23);
		panel.add(btnFiltreDest);

		JSpinner spinnerDate = new JSpinner();
		spinnerDate.setModel(new SpinnerListModel(new String[] {"<", ">"}));
		spinnerDate.setBounds(713, 159, 30, 20);
		panel.add(spinnerDate);

		JButton btnFiltrerDate = new JButton("Filtrer sur Date");
		btnFiltrerDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnFiltrerDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8034/transaction/filter/date" + spinnerDate.getValue() +TDate.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnFiltrerDate.setBounds(601, 158, 106, 23);
		panel.add(btnFiltrerDate);

		JButton btnRcuprerHistoriqueCoupleTransactions = new JButton("R\u00E9cup\u00E9rer historique couple");
		btnRcuprerHistoriqueCoupleTransactions.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRcuprerHistoriqueCoupleTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8034/transaction/filter/couple="+TSource.getText()+"&"+TDest.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText("");
				int posInter = 0;
				int posTotale = 0;
				if (!text.contains("}")){ //sécurité si liste vide
					textArea.append(text);
				}else{
					while (posTotale < text.length()) {
						posInter = text.substring(posTotale).indexOf("}");
						textArea.append(text.substring(posTotale, posTotale + posInter + 2) + "\n");
						posTotale += posInter + 2;
					}
				}
			}
		});
		btnRcuprerHistoriqueCoupleTransactions.setBounds(10, 192, 184, 23);
		panel.add(btnRcuprerHistoriqueCoupleTransactions);

		JButton btnFaireTransaction = new JButton("Faire transaction");
		btnFaireTransaction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnFaireTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestTemplate restTemplate = new RestTemplate();
				String uri = "http://localhost:8034/transaction/make/source="+TSource.getText()+"&dest="+TDest.getText()+"&montant="+TMontant.getText()+"&date="+TDate.getText();
				ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
				String text = response.getBody().toString();
				textArea.setText(text);
			}
		});
		btnFaireTransaction.setBounds(216, 192, 115, 23);
		panel.add(btnFaireTransaction);








	}
}

