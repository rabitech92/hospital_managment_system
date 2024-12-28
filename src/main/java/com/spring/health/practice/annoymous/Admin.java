package com.spring.health.practice.annoymous;

public class Admin  implements MonitorClass{
    @Override
    public void monitor() {
        System.out.println("Admin are workingh");
    }
}
