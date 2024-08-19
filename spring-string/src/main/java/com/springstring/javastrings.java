package com.springstring;

public class javastrings {


    public static void main(String[] args) {

        // Using literal String
        String str = "Welcome";
        String str1 = new String("Welcome");
        System.out.println("literal String" + str.hashCode());
        System.out.println("New keyword String" +str1.hashCode());
        System.out.println(str);
        System.out.println(str1);
    }

}
