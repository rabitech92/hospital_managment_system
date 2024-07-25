package com.spring.health.practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexCode {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Tabiul islam",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("Tabiul Islam");
        boolean b = matcher.find();
        if (b){
            System.out.println("data found");
        }else {
            System.out.println("data no found");
        }
    }
}
