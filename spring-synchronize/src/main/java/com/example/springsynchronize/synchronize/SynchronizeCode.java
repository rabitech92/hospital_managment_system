package com.example.springsynchronize.synchronize;

import lombok.Synchronized;

public class SynchronizeCode {

    public Synchronized sum(int a, int b){
        int c = a+b;
        System.out.println(c);

        return null;
    }
    int Synchronized(int a , int b){
        for (int i=0;i<10; i++){
            System.out.println("Synchronized result start = " + i);
        }

        for (int i=0;i<10; i++){
            System.out.println("Synchronized result-2 start = " + i);
        }
        int d = a-b;
        System.out.println(d);
        return d;
    }
    public static void main(String[] args) {
        SynchronizeCode synchronizeCode = new SynchronizeCode();
        synchronizeCode.sum(12,13);
        synchronizeCode.Synchronized(55,45);

    }
}
