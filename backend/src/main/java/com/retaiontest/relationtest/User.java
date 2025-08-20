package com.retaiontest.relationtest;


import com.retaiontest.relationtest.Enumiration.Role;
import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}