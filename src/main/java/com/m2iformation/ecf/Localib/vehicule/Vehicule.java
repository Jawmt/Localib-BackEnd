package com.m2iformation.ecf.Localib.vehicule;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String marque;
    private String modele;
    private String etatDuVehicule;
    private double prixLocJournee;
    private boolean disponible;
    private String type;



}
