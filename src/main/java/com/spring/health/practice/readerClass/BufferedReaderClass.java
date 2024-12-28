package com.spring.health.practice.readerClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderClass {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        System.out.println("Hello text Line ");
        System.out.println(input);
        reader.close();

        
    }
}

