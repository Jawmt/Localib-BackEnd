package com.m2iformation.ecf.Localib.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String prenom;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDeNaissance;

    private String email;

    private String numeroTel;

}
