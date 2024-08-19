package com.springstring;

public class ImmutableStrings {
    public static void main(String[] args) {

        String s = "Rabiul islam";
        s.concat("With Sinha");
        System.out.println(s);

        String s1 = new String("Rabiul islam");
        s1.concat("With Sinha");
        System.out.println(s1);
    }
}
