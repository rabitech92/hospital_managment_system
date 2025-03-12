package com.spring.health.practice.thread;

public class CurrentThread {
    public static void main(String[] args) {
        Thread.currentThread().setName("Current Thread");
        System.out.println("Current Thread Name: " + Thread.currentThread().getName());
    }
}
