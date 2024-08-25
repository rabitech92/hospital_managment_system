package com.postconstruct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class User {
    @Id
    private int id;
    private String name;
    private String address;

}
