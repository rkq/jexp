package com.github.rkq.jexp.ut.test;

import com.github.rkq.jexp.ut.Calculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by rick on 8/19/15.
 */
public class CalculatorTest {
    @Test
    public void testEvaluateExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        Assert.assertEquals(6, sum);
    }
}