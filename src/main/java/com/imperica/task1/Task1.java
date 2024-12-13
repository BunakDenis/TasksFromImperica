package com.imperica.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Read from console cont of right expressions
        int countOfRightExpression = scanner.nextInt();

        List<String> expressions = new ArrayList<>();

        scanner.nextLine();
        //Read from console expressions to end putting expression need to write "end"
        boolean endpoint = true;
        while (endpoint) {
            String expression = scanner.nextLine();

            if (expression.equalsIgnoreCase("end")) {
                endpoint = false;
                scanner.close();
            } else {
                expressions.add(expression);
            }
        }

        List<String> clearExpressions = new ArrayList<>();

        //Clear expressions from symbols, digits and spaces
        expressions.forEach(
                e -> clearExpressions.add(
                        Arrays.stream((e.split("")))
                                .filter(c -> (c.equals("(") || c.equals(")"))
                                )
                                .collect(Collectors.joining(""))
                )
        );

        //Checking right answers
        int countOfRightAnswers = 0;
        for (int i = 0; i < clearExpressions.size(); i++) {
            //Get expression from collection
            String clearExpression = clearExpressions.get(i);

            //Collect from stream brackets to collection
            List<String> brackets = Arrays.stream(clearExpression.split(""))
                    .collect(Collectors.toList());

            //count for coincidence of open and close bracket
            int count = 0;

            for (int j = 1; j < brackets.size(); j++) {

                if (brackets.size() > 1) {
                    if (brackets.get(j - 1).equals("(") & brackets.get(j).equals(")")) {
                        count++;

                        //if open and close bracket coincided, remove them from collection
                        brackets.remove(j);
                        brackets.remove(j - 1);
                        j = 0;
                    }
                }
            }

            /*
                Checking if collection of bracket consist any brackets and count of coincided brackets
                is more or equals then count of needed coincidence, than add one to right answer
             */
            if (brackets.isEmpty() && count >= countOfRightExpression) countOfRightAnswers++;

        }
        System.out.println("Right answer = " + countOfRightAnswers);
    }

}
