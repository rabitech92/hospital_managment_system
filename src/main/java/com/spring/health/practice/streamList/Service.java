package com.spring.health.practice.streamList;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Service {


    public static void main(String[] args) {
        Guardian guardian = new Guardian();
        guardian.setId(101);
        guardian.setName("Rabiul islam");
        guardian.setAddress("Kotalipara");

        List<Guardian> guardians = new ArrayList<>();
        guardians.add(guardian);
//        System.out.println(guardians);

        Teacher teacher = new Teacher();
        teacher.setId(202);
        teacher.setName("Taiamur rahman");

       List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);

        Student student = new Student();
        student.setId(303);
        student.setName("Kawser Ahmed");
        student.setTeacher(teachers);
        student.setGuardians(guardians);


        List<Student> students = new ArrayList<>();
        students.add(student);



        List<Student> list = students.stream()
                .collect(Collectors.toList());
        System.out.println(list);

    }
}
