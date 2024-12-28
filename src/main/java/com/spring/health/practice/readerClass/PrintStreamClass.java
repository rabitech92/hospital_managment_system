package com.spring.health.practice.readerClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PrintStreamClass {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream  stream = new PrintStream(new File("C:\\Users\\rabiu\\Downloads\\ভাড়াটিয়া সনদপত্র.docx"));
        PrintStream console = System.out;
        System.setOut(stream);
        System.setOut(console);
    }
}
