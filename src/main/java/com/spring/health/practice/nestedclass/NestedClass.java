package com.spring.health.practice.nestedclass;

public class NestedClass implements Student{

    public void display(){
        System.out.println("I am a NestedClass");
    }


    static class Teacher{
         public void display(){
             System.out.println("I am a student");
         }

    }
    public static void main(String[] args) {
        Student nestedClass = new Student() {
            @Override
            public void display() {
                System.out.println("I am anonymous class");
            }
        };
        nestedClass.display();





    }



}
