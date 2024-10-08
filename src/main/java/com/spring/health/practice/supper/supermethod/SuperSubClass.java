package com.spring.health.practice.supper.supermethod;

public class SuperSubClass extends SuperMethod{

    void print() {
        System.out.println("this is super sub class");
    }
    void message(){
        print();
        super.print();
    }
}
