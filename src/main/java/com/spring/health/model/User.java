package com.spring.health.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 30,nullable = false)
    private String name;
    @Column(length = 120,nullable = false)
    private String email;
    @Column(length = 64,nullable = false)
    private String password;


}
