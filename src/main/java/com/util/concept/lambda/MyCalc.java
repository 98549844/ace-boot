package com.util.concept.lambda;

public class MyCalc {


    public static void main(String[] args) {
        int result = 0;

        Calc calc1 = new CalcImpl();
        result = calc1.add(3, 2);
        System.out.println("result: " + result);


        Calc calc2 = (a, b) -> {
            int c = a + b;
            System.out.println("lambda calc2: " + (c));
            return c;
        };
    }
}
