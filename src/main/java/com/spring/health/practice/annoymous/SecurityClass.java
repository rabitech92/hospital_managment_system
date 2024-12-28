package com.spring.health.practice.annoymous;

public class SecurityClass implements MonitorClass{
    @Override
    public void monitor() {
        System.out.println("Security gured to ");
    }
}
