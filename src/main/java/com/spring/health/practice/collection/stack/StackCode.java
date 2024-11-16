package com.spring.health.practice.collection.stack;

import java.util.Collections;
import java.util.Stack;

public class StackCode {
    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        stringStack.add("Rabiul islam");
        stringStack.add("Sinha islam");
        stringStack.add("Youzayer islam");
        stringStack.add("Farha islam");

        for (String ss : stringStack ) {
            System.out.println(ss);
        }

        stringStack.pop();
        System.out.println("After pop"+stringStack);


    }
}
