package com.m2iformation.ecf.Localib.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.m2iformation.ecf.Localib.client.Client;
import com.m2iformation.ecf.Localib.vehicule.Vehicule;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dateHeureDebut;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dateHeureFin;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Vehicule vehicule;

    /**
     * Methode permettant de calculer le montant en fonction du prix journalier
     * @return le montant a payer
     */
    @Transient
    public double getMontantLocation(){
        double minutes = ChronoUnit.MINUTES.between(dateHeureDebut,dateHeureFin);
        double jour = minutes/60/24;
        int jourEntier = (int) (minutes/60/24);
        double jourLongRef = jourEntier;

        double montant=0;
        double prixJour = this.getVehicule().getPrixLocJournee();

        if(jourLongRef<jour){
            montant = prixJour*(jourEntier+1);
        }else if(jourLongRef == jour){
            montant = prixJour*jourEntier;
        }

        return montant;

    }
}
