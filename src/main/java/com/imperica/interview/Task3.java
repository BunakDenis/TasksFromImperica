package com.imperica.interview;

import java.util.ArrayList;
import java.util.List;

public class Task3 {

    public static void findSmallestMultiple(int a, int b, int c) {

        int result = 0;
        List<Integer> numbers = new ArrayList<>();

        numbers.add(a);
        numbers.add(b);
        numbers.add(c);

        Integer min = numbers.stream()
                .min(Integer::compareTo)
                .get();

        if ((a * b) % a == 0 && (a * b) % b == 0 && (a * b) % c == 0) {
            result = a * b;
        } else if ((a * c) % a == 0 && (a * c) % b == 0 && (a * c) % c == 0) {
            result = a * c;
        } else if ((c * b) % a == 0 && (c * b) % b == 0 && (c * b) % c == 0) {
            result = a * c;
        } else {

            int multipleNumber = 0;
            if (a % 3 == 0 && b % 3 == 0 || c % 3 == 0) {
                multipleNumber = 3;
            } else if (a % 2 == 0 && b % 2 == 0 || c % 2 == 0) {
                multipleNumber = 2;
            } else if (a % 5 == 0 && b % 5 == 0 || c % 5 == 0) {
                multipleNumber = 5;
            }

            boolean isSmallestMultipleIsFind = true;
            int d = min;
            while (isSmallestMultipleIsFind) {
                d += multipleNumber;

                if (d % a == 0 && d % b == 0 && d % c == 0) {
                    result = d;
                    isSmallestMultipleIsFind = false;
                }
            }
        }
        System.out.println("result = " + result);
    }

}
