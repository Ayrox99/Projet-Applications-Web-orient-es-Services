package fr.dauphine.miageIf.msa.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

//Création de JPA Repository basé sur Spring Data
//Permet de "générer" toutes sortes de requêtes JPA, sans implémentation
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAll();
    Transaction findFirstByOrderByIdDesc();
    List<Transaction> findAllBySourceAndDest(String source, String dest);

    //filtres par montant
    List<Transaction> findAllByMontant(double montant);
    List<Transaction> findAllByMontantLessThanEqual(double montant);
    List<Transaction> findAllByMontantLessThan(double montant);
    List<Transaction> findAllByMontantGreaterThanEqual(double montant);
    List<Transaction> findAllByMontantGreaterThan(double montant);

    //filtres par devise
    List<Transaction> findAllBySource(String source);
    List<Transaction> findAllByDest(String dest);

    //filtres sur la date
    List<Transaction> findAllByDateBefore(Date date);
    List<Transaction> findAllByDateAfter(Date date);

    Transaction save(Transaction transaction);
    void delete(Transaction transaction);

}
