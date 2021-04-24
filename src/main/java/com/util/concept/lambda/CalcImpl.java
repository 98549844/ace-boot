package com.util.concept.lambda;

import java.math.BigDecimal;

public class CalcImpl implements Calc {
    @Override
    public Integer add(int a, int b) {
        return a+b;
    }


    public Integer subtract(int a, int b) {
        return a-b;
    }

    public Integer multiply(int a, int b) {
        return a*b;
    }

    public BigDecimal divide(int a, int b) {
        BigDecimal ba = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);
        BigDecimal result = ba.divide(bb);
        return result;
    }
}
