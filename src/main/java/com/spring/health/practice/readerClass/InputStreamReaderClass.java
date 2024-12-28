package com.spring.health.practice.readerClass;

import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderClass {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        int len = reader.read();
        System.out.println("Enter a string:"+len);
    }
}
