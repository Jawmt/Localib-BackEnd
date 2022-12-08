package com.m2iformation.ecf.Localib.vehicule;

import com.m2iformation.ecf.Localib.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private VehiculeRepository vehiculeRepository;
    private static final Logger log = LoggerFactory.getLogger(VehiculeService.class);

    public VehiculeService(VehiculeRepository vehiculeRepository){
        this.vehiculeRepository = vehiculeRepository;
    }

    /**
     * Methode permettant de recuperer l'ensemble des vehicules sauvegarder
     * @return La liste de l'ensemble des vehicules sauvegarder en BDD
     */
    public List<Vehicule> findAll() {
        log.info("Liste des vehicules retournees");
        return vehiculeRepository.findAll();

    }

    /**
     * Methode permettant de sauvegarder un vehicule en BDD
     * @param entity L'entité vehicule a sauvegarder en BDD
     * @return le vehicule qui a été sauvegarder en BDD
     */
    public Vehicule save(Vehicule entity) {
        entity.setDisponible(true);
        Vehicule vehiculeSauvegarder = vehiculeRepository.save(entity);
        log.info("Le véhicule a été sauvegarder");
        return vehiculeSauvegarder;

    }

    /**
     * Methode permettant de supprimer un vehicule en base de données
     * @param aLong l'id du vehicule a supprimer
     */
    public void deleteById(Long aLong) {
        vehiculeRepository.deleteById(aLong);
        log.info("Le vehicule avec l'id "+aLong+" a bien été supprimer");
    }

    /**
     * Mehode permettant de retourner un vehicule via son id
     * @param aLong id du vehicule
     * @return le vehicule
     */
    public Optional<Vehicule> findById(Long aLong) {
        return vehiculeRepository.findById(aLong);
    }

    /**
     * Methode permettant de modifier les informations d'un vehicule ou de le créer si  il n'existe pas
     * @param vehicule le vehicule modifier
     * @param idVehicule id du vehicule a modifier
     * @return vehicule modifier
     */
    public Vehicule modifierVehicule(Vehicule vehicule, Long idVehicule){
        Optional<Vehicule> vehiculeOptional = this.findById(idVehicule);

        if(vehiculeOptional.isEmpty()){
            log.info("Le vehicule n'existait pas, il va être crée");
            return vehiculeRepository.save(vehicule);
        }

        Vehicule vehiculeAModifier = vehiculeOptional.get();
        vehiculeAModifier.setModele(vehicule.getModele());
        vehiculeAModifier.setMarque(vehicule.getMarque());
        vehiculeAModifier.setEtatDuVehicule(vehicule.getEtatDuVehicule());
        vehiculeAModifier.setType(vehicule.getType());
        vehiculeAModifier.setPrixLocJournee(vehicule.getPrixLocJournee());

        Vehicule vehiculeModifier = vehiculeRepository.save(vehiculeAModifier);
        log.info("Modification du vehicule");
        return  vehiculeModifier;
    }
}
