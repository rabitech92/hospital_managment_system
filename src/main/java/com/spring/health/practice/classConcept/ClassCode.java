package com.spring.health.practice.classConcept;

import jakarta.annotation.PostConstruct;

public class ClassCode {

    public static void main(String[] args) {
        C c = new B();
        c.Cprint();

        C b =  new B();
        ((B) b).addB();
        b.Cprint();

        C c1 = new A();
        ((A) c1).addA();
        c1.Cprint();
        A a = new A();
        C  c2 = a;
        ((A) c2).addA();



           }
}
