package com.retaiontest.relationtest.repository;

import com.retaiontest.relationtest.Enumiration.FILIERE;
import com.retaiontest.relationtest.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

