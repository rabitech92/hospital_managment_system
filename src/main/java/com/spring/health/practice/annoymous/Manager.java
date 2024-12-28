package com.spring.health.practice.annoymous;

public class Manager implements MonitorClass{
    @Override
    public void monitor() {
        System.out.println("Manager are manage their work");
    }
}
