package com.retaiontest.relationtest.repository;


import com.retaiontest.relationtest.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
//herité les methodes de CRUD
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
