package com.springstring.callByValue;

public class CallBayValue {

    private Integer num = 50;
    void sum(Integer num){
        num =num+50;
    }

    public static void main(String[] args) {
       CallBayValue value = new CallBayValue();
        System.out.println("Before add ="+value.num);
       value.sum(100);
        System.out.println(" After add value ="+value.num);
    }
}
