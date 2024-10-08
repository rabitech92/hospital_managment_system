package com.spring.health.practice.stream;

import com.lowagie.text.pdf.codec.Base64;

import java.io.InputStream;
import java.util.stream.IntStream;

public class StreamCode {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(10,20,30);
        long count = intStream.count();
        System.out.println(count);
    }
}
