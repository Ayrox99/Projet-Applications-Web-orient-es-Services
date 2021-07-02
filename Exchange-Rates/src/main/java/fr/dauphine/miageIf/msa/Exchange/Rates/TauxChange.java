package fr.dauphine.miageIf.msa.Exchange.Rates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


// Classe persistante représentant un "taux de change"
@Entity
public class TauxChange {

    @Id
    private Long id;

    @Column(name="devise_src")
    private String source;

    @Column(name="devise_dest")
    private String dest;

    private BigDecimal taux;
    private Date date;

    public TauxChange(){

    }

    public TauxChange (Long id, String source, String dest, BigDecimal taux, Date date){
        super();
        this.id = id;
        this.source = source;
        this.dest = dest;
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
