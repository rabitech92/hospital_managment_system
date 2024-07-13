package com.spring.health.practice.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LamdaCode {

    public static void main(String[] args) {
        //mapping
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> list = integerList.stream()
                .map(n ->n*n)
                .collect(Collectors.toList());
        System.out.println(list);

// filter
        List<String> stringList = Arrays.asList("Jamal","Kamal","Hasib","Rahman","Astreammamal","Kamal","Hasib","ARahman");
        List<String> strings = stringList.stream()
                .filter(s -> s.equalsIgnoreCase("hasib")).collect(Collectors.toList());
        System.out.println(strings);

// sorting
        List<String>  sorting = Arrays.asList("Abdullah","Jama","Kamal","Rahim","Afifa");
        List<String> sorted = sorting.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted);

        integerList.stream()
                .forEach(num -> System.out.print(num+","));

       List<String> strings1 = strings.stream()
                .collect(Collectors.toList());
        System.out.println(strings1);

        List<String> strings2 = strings.stream()
                .filter(name ->name.startsWith("A"))
                .map(name -> name.toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(strings2);
    }

}
