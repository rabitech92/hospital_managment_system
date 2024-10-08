package com.spring.health.practice.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListCode {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(110);
        integers.add(10);
        integers.add(11);
        integers.add(114);
        integers.add(33);
        integers.add(55);

        Iterator iterator = integers.iterator();
        iterator.hasNext();

        System.out.println(iterator.next());
    }
}
