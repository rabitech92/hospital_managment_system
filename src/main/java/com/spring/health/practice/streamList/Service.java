package com.spring.health.practice.streamList;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


public class Service {


    public static void main(String[] args) {
        Guardian guardian = new Guardian();
        guardian.setId(101);
        guardian.setName("Rabiul islam");
        guardian.setAddress("Kotalipara");

        List<Guardian> guardians = new ArrayList<>();
        guardians.add(guardian);
        System.out.println(guardians);




    }
}
