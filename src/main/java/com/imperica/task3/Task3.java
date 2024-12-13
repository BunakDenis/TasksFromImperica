package com.imperica.task3;

import java.math.BigInteger;

public class Task3 {

    public static void main(String[] args) {
        long result = 0;

        int number = 100;

        //Get factorial of number
        BigInteger factorial = getFactorial(number);

        while (!factorial.equals(BigInteger.ZERO)) {

            //Get last digit of factorial
            int digit = factorial.mod(BigInteger.TEN).intValue();

            //Delete last digit from factorial
            factorial = factorial.divide(BigInteger.TEN);

            //Add last digit to result
            result += digit;
        }
        System.out.println("result = " + result);
    }

    public static BigInteger getFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= number; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

}
