package com.imperica.interview;

public class Task1 {

    public static void changeValue(int a, int b) {

        a = a + b;
        b = b - a;
        b = -b;
        a = a - b;

        System.out.println("a = " + a + " , b = " + b);

    }

}
