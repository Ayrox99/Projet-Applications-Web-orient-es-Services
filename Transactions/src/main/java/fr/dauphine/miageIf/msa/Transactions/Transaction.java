package fr.dauphine.miageIf.msa.Transactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

// Classe persistante représentant une "transaction"
@Entity
public class Transaction {

    @Id
    private Long id;

    @Column(name="devise_src")
    private String source;

    @Column(name="devise_dest")
    private String dest;

    private double montant;
    private Date date;
    private BigDecimal taux;

    public Transaction() {

    }

    public Transaction (Long id, String source, String dest, double montant, BigDecimal taux, Date date){
        super();
        this.id = id;
        this.source = source;
        this.dest = dest;
        this.montant = montant;
        this.taux = taux;
        this.date = date;
    }

    public Long getId(){
        return id;
    }

    public String getSource(){
        return source;
    }

    public String getDest(){
        return dest;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant){
        this.montant = montant;
    }

    public BigDecimal getTaux() {
        return taux;
    }

    public void setTaux(BigDecimal taux){
        this.taux = taux;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
