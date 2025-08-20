package com.retaiontest.relationtest;

//import jakarta.persistence.*;
import com.retaiontest.relationtest.Enumiration.DEPARTEMENT;
import lombok.Data;

import jakarta.persistence.*;




@Data
@Entity
public class Encadrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String tele;

    @Enumerated(EnumType.STRING)
    private DEPARTEMENT departement;

    @OneToOne
    @JoinColumn(name = "id_user", unique = true)
    private User user;

}

