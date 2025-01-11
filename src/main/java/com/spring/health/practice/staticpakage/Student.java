package com.spring.health.practice.staticpakage;

public class Student {
    static int id= 101;
    static String name = "Kamal";
    Teacher teacher;

     static void getStudent() {
         System.out.println("Static Value:" +Student.id+" "+Student.name+" "+ Teacher.teachName);
    }
    static {
        System.out.println("Static block:" +Student.id+" "+Student.name);
    }

    public static void main(String[] args) {
         Student student = new Student();
         getStudent();





    }
    static class Teacher{
        static String teachName = "Teaching are good job";
    }
}
