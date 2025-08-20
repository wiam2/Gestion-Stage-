
package com.retaiontest.relationtest;

import com.retaiontest.relationtest.Enumiration.NIVEAU;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NonNull;

@Data
@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    private String tele;

    @NonNull
    private String dateNais;

    @Enumerated(EnumType.STRING)
    private NIVEAU niveau;

    @OneToOne
    @JoinColumn(name = "id_user", unique = true)
    private User user;


    public Etudiant() {

    }
}
