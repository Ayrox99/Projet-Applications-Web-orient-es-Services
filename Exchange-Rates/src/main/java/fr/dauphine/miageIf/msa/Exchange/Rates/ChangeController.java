package fr.dauphine.miageIf.msa.Exchange.Rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    private TauxChangeRepository repository;

    @GetMapping("/exchange-rate/get-all")
    public List<TauxChange> renvoyerTousLesTauxChange(){
        List<TauxChange> listTauxChange = repository.findAll();
        for (TauxChange tauxChange : listTauxChange){
            Date date = tauxChange.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            tauxChange.setDate(cal.getTime());
        }
        return listTauxChange;
    }

    @GetMapping("/exchange-rate/get/couple-history/source={source}&dest={dest}")
    public List<TauxChange> renvoyerTousLesTauxEntreDeuxMonnaies
            (@PathVariable String source, @PathVariable String dest){
        List<TauxChange> listTauxChange = repository.findAllBySourceAndDest(source, dest);
        for (TauxChange tauxChange : listTauxChange){
            Date date = tauxChange.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            tauxChange.setDate(cal.getTime());
        }
        return listTauxChange;
    }

    @GetMapping("/exchange-rate/get/rate/source={source}&dest={dest}&date={date}")
    public BigDecimal retrouverdernierTauxChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable Date date){
        //on augmente la date de 1 jour car la recherche par data est stricte sur la borne supérieur
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tmpDate = cal.getTime();

        List<TauxChange> tauxChangeList = repository.findAllBySourceAndDestAndDateBefore(source, dest, tmpDate);
        //TauxChange TauxChange = repository.findFirstBySourceAndDestAndDate(source, dest, date);
        TauxChange tauxChange = tauxChangeList.get(tauxChangeList.size()-1);
        return tauxChange.getTaux();
    }

    @RequestMapping("/exchange-rate/add-rate/source={source}&dest={dest}&rate={taux}&date={date}")
    public TauxChange ajouterTauxChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable BigDecimal taux, @PathVariable Date date){
        Long id;
        TauxChange t = repository.findFirstByOrderByIdDesc();
        if (t == null){
            id = new Long(1);
        }else{
            id = t.getId() + 1;
        }
        TauxChange tauxChange= new TauxChange(id, source, dest, taux, date);
        repository.save(tauxChange);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        tauxChange.setDate(cal.getTime());

        return tauxChange;
    }

    @RequestMapping("/exchange-rate/update-existing-rate/source={source}&dest={dest}&date={date}&new_rate={taux}")
    public TauxChange modifierTauxChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable BigDecimal taux, @PathVariable Date date){
        TauxChange tauxChange = repository.findFirstBySourceAndDestAndDate(source, dest, date);
        tauxChange.setTaux(taux);
        repository.save(tauxChange);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        tauxChange.setDate(cal.getTime());

        return tauxChange;
        /*repository.updateTauxBySourceAndDestAndDate(taux, source, dest, date);
        return repository.findFirstBySourceAndDestAndDate(source, dest, date);*/
    }

    @RequestMapping("/exchange-rate/delete-existing-rate/source={source}&dest={dest}&date={date}")
    public TauxChange supprimerTauxChange
            (@PathVariable String source, @PathVariable String dest, @PathVariable Date date){
        TauxChange tauxChange = repository.findFirstBySourceAndDestAndDate(source, dest, date);
        repository.delete(tauxChange);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        tauxChange.setDate(cal.getTime());

        return tauxChange;
    }
}
