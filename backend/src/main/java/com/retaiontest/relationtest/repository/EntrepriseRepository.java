package com.retaiontest.relationtest.repository;

import com.retaiontest.relationtest.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
}

