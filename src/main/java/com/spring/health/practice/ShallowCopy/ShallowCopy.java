package com.spring.health.practice.ShallowCopy;

public class ShallowCopy {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.numbers = 15;
        System.out.println("Student hashcode: " + stu);
        Student stu1 = stu;
        System.out.println("After reference "+ stu1);
        stu1.numbers = 20;
        System.out.println("reusing value " + stu1.numbers);

    }
}
