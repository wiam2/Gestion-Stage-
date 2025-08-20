package com.retaiontest.relationtest;

import com.retaiontest.relationtest.Etudiant;
import com.retaiontest.relationtest.Post;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table
public class Fichier {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private long id ;
        private String chemin_CV; // hada anstockiw hna gha chemin w fichier aytstoka f projet
        private String lettre_motivation; // hada anstockiw hna gha chemin w fichier aytstoka f projet


        @ManyToOne
        @JoinColumn(name="id_etudiant ", referencedColumnName = "id" , nullable = false)
        private Etudiant id_etudiant;

        @ManyToOne
        @JoinColumn(name = "id_post" , referencedColumnName = "id" ,nullable = false)
        private Post post;



}
