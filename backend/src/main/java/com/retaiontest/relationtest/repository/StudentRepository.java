package com.retaiontest.relationtest.repository;

import com.retaiontest.relationtest.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Etudiant,Long> {
}
