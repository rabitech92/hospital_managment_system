package com.spring.health.practice.nestedclass;

public class NestedClass implements Student{

    public void display(){
        System.out.println("I am a NestedClass");
    }


     class Teacher{
         public void display(){
             System.out.println("I am a student");
         }

    }
    public static void main(String[] args) {
        Student nestedClass = new NestedClass();
        nestedClass.display();





    }



}
