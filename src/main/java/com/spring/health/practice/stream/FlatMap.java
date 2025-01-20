package com.spring.health.practice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> integerList1 = Arrays.asList(11,12,13,14,15,16,17,18,19,20);
        List<Integer> integerList2 = Arrays.asList(21,22,23,24,25,26,27,28,29,30);
        List<Integer> integerList3 = Arrays.asList(31,32,33,34,35,36,37,38,39,40);
        List<List<Integer>> listList = new ArrayList<>();
        listList.add(integerList);
        listList.add(integerList1);
        listList.add(integerList2);
        listList.add(integerList3);

        List<Integer> integers = listList.stream().flatMap(list ->list.stream()).collect(Collectors.toList());
        System.out.println(integers);
    }
}
