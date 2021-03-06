package com.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class BigDecimalUtil {
    private final Log log = LogFactory.getLog(this.getClass());

    private static boolean checkNull(Object obj) {
        boolean check = false;
        if (obj == null) {
            Console.println("parameter is null.Please check!");
            check = true;
        }
        return check;
    }


    /**
     * 方法名：sub
     * 功能：减法运算
     * 入参：v1:被减数 ,v2:减数
     * 出参：两个参数的差
     */
    public static Double sub(Double v1, Double v2) {
        if (checkNull(v1) || checkNull(v2)) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 方法名：add
     * 功能：加法运算
     * 入参：v1:被加数 ,v2:加数
     * 出参：两个参数的和
     */
    public static Double add(Double v1, Double v2) {
        if (checkNull(v1) || checkNull(v2)) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 方法名：mul
     * 功能：乘法运算
     * 入参：v1：被乘数，v2：乘数
     * 出参：两个参数的积
     */
    public static Double mul(Double v1, Double v2) {
        if (checkNull(v1) || checkNull(v2)) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 方法名：div
     * 功能：除法运算，当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 入参：v1：被除数，v2：除数，scale：表示表示需要精确到小数点以后几位
     * 出参：两个参数的商
     */
    public static Double div(Double v1, Double v2, Integer scale) {
        if (checkNull(v1) || checkNull(v2)) {
            return null;
        }

        if (scale == null) {
            scale = 2;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 方法名：isNumeric
     * 功能：是否为整数
     * 入参：
     * 出参：
     */
    public static Boolean isInteger(String str) {
        if (checkNull(str)) {
            return null;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

}
