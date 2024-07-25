package com.spring.health.practice.streamList;


import lombok.Data;

import java.util.List;
@Data
public class Student {

    private int id;
    private String name;
    private List<Teacher> teacher;
    private List<Guardian> guardians;
}
