package com.spring.health.practice.thread;

public class ThreadClass {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        System.out.println(thread.getPriority());
    }
}
