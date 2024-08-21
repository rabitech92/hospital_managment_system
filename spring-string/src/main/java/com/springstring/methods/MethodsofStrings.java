package com.springstring.methods;

public class MethodsofStrings {
    public static void main(String[] args) {
    char result1;
        String str = "Welcome";
        String str1 = new String("Welcome");

       String b = String.valueOf(str.equals(str1));
        System.out.println(b);

        //compares the values
        Boolean  str2 = str.equalsIgnoreCase(str1);
        System.out.println(str2);

        String  result = String.valueOf(str1.length());
        System.out.println("length of result "+result);

        String string = "Abc";
        String string1 = new String("Abc");
        System.out.println("Comparing tow string : "+string == string1); // result false

        String str5 = "abc";
        String str6 = "abc";

        System.out.println(str5 == str6);
        System.out.println(str5 == "abc");




    }
}
