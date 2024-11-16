package com.spring.health.practice.collection.que;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class QueCode {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque(Collections.singleton("Hello one"));
        queue.add("Hello two");
        queue.add("Hello three");
        queue.add("Hello four");
        queue.add("Hello five");

        for (String name : queue ) {
            System.out.println(name);

        }
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        for (String name : queue ) {
            System.out.println("Remove after :"+name);

        }

    }
}
