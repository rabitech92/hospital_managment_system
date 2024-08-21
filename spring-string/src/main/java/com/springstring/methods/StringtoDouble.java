package com.springstring.methods;

public class StringtoDouble {
    public static void main(String[] args) {


        Double aDouble1 = 10.00;
        String  s = String.valueOf(aDouble1) ;
        System.out.println(s);

        String str = "welcome";
        Double aDouble = (str !=null) ? Double.parseDouble(str) :10;
        System.out.println(aDouble);
    }
}
