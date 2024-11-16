package com.spring.health.practice.random;

import java.util.Random;

public class RandomDigitSelector {
    public static void main(String[] args) {
        Random random = new Random();

        // Generate a random 12-digit number
        StringBuilder twelveDigitNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(12); // Random digit between 0 and 9
            twelveDigitNumber.append(digit);
        }

        // Display the 12-digit number
        System.out.println("12-digit number: " + twelveDigitNumber);

        // Select a random position from 0 to 11
        int randomPosition = random.nextInt(12);

        // Get the digit at the randomly selected position
        char selectedDigit = twelveDigitNumber.charAt(randomPosition);

        // Display the selected digit
        System.out.println("Randomly selected digit: " + selectedDigit);

    }
}
