package com.retaiontest.relationtest.repository;

import com.retaiontest.relationtest.Etudiant;
import com.retaiontest.relationtest.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
