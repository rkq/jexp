package com.github.rkq.jexp.ut;

/**
 * Created by rick on 8/19/15.
 */
public class Calculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand: expression.split("\\+"))
            sum += Integer.valueOf(summand);
        return sum;
    }
}
