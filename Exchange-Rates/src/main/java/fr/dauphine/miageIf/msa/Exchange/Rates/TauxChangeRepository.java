package fr.dauphine.miageIf.msa.Exchange.Rates;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

//Création de JPA Repository basé sur Spring Data
//Permet de "générer" toutes sortes de requêtes JPA, sans implémentation
public interface TauxChangeRepository extends JpaRepository<TauxChange, Long> {
    TauxChange findFirstBySourceAndDestAndDate(String source, String dest, Date date);
    List<TauxChange> findAllBySourceAndDestAndDateBefore(String source, String dest, Date date);
    List<TauxChange> findAllBySourceAndDest(String source, String dest);
    List<TauxChange> findAll();
    TauxChange findFirstByOrderByIdDesc(); //utile pour récupérer l'id actuel le plus haut

    TauxChange save(TauxChange tauxChange);
    void delete(TauxChange tauxChange);

}
