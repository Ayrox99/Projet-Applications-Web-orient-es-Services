package fr.dauphine.miageIf.msa.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ChangeController {

    //Spring se charge de la création d'instance
    @Autowired
    private Environment environment;

    //Spring se charge de la création d'instance
    @Autowired
    private TransactionRepository repository;

    @GetMapping("/transaction/get-all")
    public List<Transaction> renvoyerToutesLesTransactions(){
        List<Transaction> listTransactions = repository.findAll();
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/montant={montant}")
    public List<Transaction> renvoyerToutesLesTransactionsPourUnMontant
            (@PathVariable double montant){
        List<Transaction> listTransactions = repository.findAllByMontant(montant);
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/montant<={montant}")
    public List<Transaction> renvoyerToutesLesTransactionsPourUnMontantMaxInclus
            (@PathVariable double montant){
        List<Transaction> listTransactions = repository.findAllByMontantLessThanEqual(montant);
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/montant<{montant}")
    public List<Transaction> renvoyerToutesLesTransactionsPourUnMontantMax
            (@PathVariable double montant){
        List<Transaction> listTransactions = repository.findAllByMontantLessThan(montant);
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/montant>={montant}")
    public List<Transaction> renvoyerToutesLesTransactionsPourUnMontantMinInclus
            (@PathVariable double montant){
        List<Transaction> listTransactions = repository.findAllByMontantGreaterThanEqual(montant);
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/montant>{montant}")
    public List<Transaction> renvoyerToutesLesTransactionsPourUnMontantMin
            (@PathVariable double montant){
        List<Transaction> listTransactions = repository.findAllByMontantGreaterThan(montant);
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/source={source}")
    public List<Transaction> renvoyerToutesLesTransactionsPourUneMonnaieSource
            (@PathVariable String source){
        List<Transaction> listTransactions = repository.findAllBySource(source);
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/dest={dest}")
    public List<Transaction> renvoyerToutesLesTransactionsPourUneMonnaieDestination
            (@PathVariable String dest){
        List<Transaction> listTransactions = repository.findAllByDest(dest);
        for (Transaction transaction : listTransactions){
            Date date = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/date>{date}")
    public List<Transaction> renvoyerToutesLesTransactionsApresUneDate
            (@PathVariable Date date){
        List<Transaction> listTransactions = repository.findAllByDateAfter(date);
        for (Transaction transaction : listTransactions){
            Date d = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/date<{date}")
    public List<Transaction> renvoyerToutesLesTransactionsAvantUneDate
            (@PathVariable Date date){
        List<Transaction> listTransactions = repository.findAllByDateBefore(date);
        for (Transaction transaction : listTransactions){
            Date d = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @GetMapping("/transaction/filter/couple={source}&{dest}")
    public List<Transaction> renvoyerToutesLesTransactionsEntreDeuxMonnaies
            (@PathVariable String source, @PathVariable String dest){
        List<Transaction> listTransactions = repository.findAllBySourceAndDest(source, dest);
        for (Transaction transaction : listTransactions){
            Date d = transaction.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            transaction.setDate(cal.getTime());
        }
        return listTransactions;
    }

    @RequestMapping("/transaction/make/source={source}&dest={dest}&montant={montant}&date={date}")
    public Transaction demanderTransaction
            (@PathVariable String source, @PathVariable String dest, @PathVariable double montant, @PathVariable Date date){
        RestTemplate restTemplate = new RestTemplate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String uri = "http://localhost:8000/exchange-rate/get/rate/source="+source+"&dest="+dest+"&date="+dateFormat.format(date);
        ResponseEntity<BigDecimal> response = restTemplate.getForEntity(uri, BigDecimal.class);
        BigDecimal taux = response.getBody();
        Long id = repository.findFirstByOrderByIdDesc().getId() + 1;

        Transaction transaction = new Transaction(id, source, dest, montant, taux, date);
        repository.save(transaction);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        transaction.setDate(cal.getTime());

        return transaction;
    }

}
