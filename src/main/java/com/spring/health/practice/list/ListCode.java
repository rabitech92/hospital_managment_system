package com.spring.health.practice.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class listCode {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<String> it = list.iterator();
        it.next();
        it.remove();
        System.out.println(it);
    }
}
