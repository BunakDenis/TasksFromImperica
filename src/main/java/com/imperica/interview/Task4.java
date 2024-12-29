package com.imperica.interview;

import java.util.ArrayList;
import java.util.List;

public class Task4 {

    public static int findLuckNumber(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(2);
        result.add(1);

        if (n >= 2) {
            for (int i = 2; i < n; i++) {
                result.add(
                        result.get(i - 2) + result.get(i - 1)
                );
            }

            return result.get(n - 1);
        }

        return result.get(n);
    }
}
