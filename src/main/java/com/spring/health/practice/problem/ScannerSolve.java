package com.spring.health.practice.problem;

import java.util.Scanner;

public class ScannerSolve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("================================");
        for (int i = 0; i < 3; i++) {
            int x = sc.nextInt();
            String s = sc.next();
            int y = sc.nextInt();
            String s1 = sc.next();
            int z = sc.nextInt();
            String s2 = sc.next();
            System.out.println(x);
            System.out.println(y);
            System.out.println(z);
        }
        System.out.println();


    }
}
