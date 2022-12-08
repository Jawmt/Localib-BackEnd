package com.m2iformation.ecf.Localib.reservation;

import com.m2iformation.ecf.Localib.client.Client;
import com.m2iformation.ecf.Localib.client.ClientRepository;
import com.m2iformation.ecf.Localib.exception.NotFoundException;
import com.m2iformation.ecf.Localib.vehicule.Vehicule;
import com.m2iformation.ecf.Localib.vehicule.VehiculeRepository;
import com.m2iformation.ecf.Localib.vehicule.VehiculeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ClientRepository  clientRepository;
    private VehiculeRepository vehiculeRepository;

    private static final Logger log = LoggerFactory.getLogger(Reservation.class);

    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository, VehiculeRepository vehiculeRepository){
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    /**
     * Methode permettant de retourner l'ensemble des reservations
     * @return l'ensemble des reservations
     */
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    /**
     * Methode permettant de sauvegarder une reservation et de passer le vehicule a non disponible
     * @param entity la reservation a sauvegarder
     * @param idClient l'id du client lie a la reservation
     * @param idVehicule l'id du vehicule lie a la reservation
     * @return la reservation sauvegardee
     */
    public Reservation save(Long idClient, Long idVehicule, Reservation entity) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new NotFoundException("Le client avec l'id : "+idClient +" n'existe pas"));
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(()-> new NotFoundException("Le vehicule avec l'id : "+idVehicule+" n'existe pas"));
        vehicule.setDisponible(false);
        entity.setClient(client);
        entity.setVehicule(vehicule);
        return reservationRepository.save(entity);
    }

    /**
     * Methode permettant de supprimer une reservation et de passer le vehicule en disponible
     * @param aLong
     */
    public void deleteById(Long aLong) {
        Optional<Reservation> reservationOptionnal = this.findById(aLong);
        if(!reservationOptionnal.isEmpty()){
            Reservation reservation = reservationOptionnal.get();
            reservation.getVehicule().setDisponible(true);
            reservationRepository.save(reservation);
        }
        reservationRepository.deleteById(aLong);
    }

    /**
     * Methode permettant de recuperer une reservation via son id
     * @param aLong l'id de la reservation
     * @return un optional de la reservation
     */
    public Optional<Reservation> findById(Long aLong) {
        return reservationRepository.findById(aLong);
    }

    public Reservation modifierReservation(Reservation reservation, Long idReservation){
        Optional<Reservation> reservationOptionnal = this.findById(idReservation);
        if(reservationOptionnal.isEmpty()){
            return reservationRepository.save(reservation);
        }
        Reservation reservationAModifier = reservationOptionnal.get();
        reservationAModifier.setDateHeureDebut(reservation.getDateHeureDebut());
        reservationAModifier.setDateHeureFin(reservation.getDateHeureFin());
        reservationAModifier.setVehicule(reservation.getVehicule());
        reservationAModifier.setClient(reservation.getClient());

        return reservationRepository.save(reservationAModifier);


    }
}
