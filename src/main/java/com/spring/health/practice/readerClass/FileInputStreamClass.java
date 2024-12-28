package com.spring.health.practice.readerClass;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileInputStreamClass {

    public static void main(String[] args)throws Exception{
        FileInputStream stream = new FileInputStream("ভাড়াটিয়া সনদপত্র.docx");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine())!= null) {
            System.out.println(line);
        }
    }
}
