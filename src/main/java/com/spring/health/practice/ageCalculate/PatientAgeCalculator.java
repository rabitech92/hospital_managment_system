package com.spring.health.practice.ageCalculate;

import java.time.LocalDate;
import java.time.Period;

public class PatientAgeCalculator {

    public static void main(String[] args) {
        LocalDate agePeriod  = LocalDate.of(2024,2,16);
        Period age = calculateAge(agePeriod);
        System.out.println("Patient's Year:" + age.getYears()+", Month:  "+age.getMonths()+", Days "+age.getDays());
    }


    public static Period calculateAge(LocalDate dateOfBirth) {

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period;
    }
}
