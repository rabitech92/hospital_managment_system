package com.spring.health.practice.readerClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class URLReader {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URI uri = new URI("https://staffmate.crystaltechbd.com");
        BufferedReader reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
        String line;
        while ((line = reader.readLine())!= null) {
            System.out.println(line);
        }

    }
}
