package com.spring.health.practice.readerClass;

import java.io.IOException;

public class SystemInClass {
    public static void main(String[] args) throws IOException {

        while (true){
            int i = System.in.read();
            System.out.print(i);
        }
    }
}
