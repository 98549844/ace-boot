package com.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class RandomUtil {
    static private Log log = LogFactory.getLog(RandomUtil.class);

    public static List<Integer> getRandomIntegerWithNoDuplicate(Integer input) {
        return getRandomIntegerInRangeWithNoDuplicate(1, input);
    }

    public static List<Integer> getRandomIntegerInRangeWithNoDuplicate(Integer min, Integer max) {
        if (isNull(min, max)) {
            log.error("min or max is empty");
        }
        if (min > max) {
            Integer t = max;
            max = min;
            min = t;
        }
        Integer range = max - min;


        List<Integer> ls = new ArrayList<>();
        while (ls.size() < range) {
            Integer r = min + (int) (Math.random() * (max - min + 1));
            if (!ls.contains(r)) {
                ls.add(r);
            }
        }
        return ls;
    }


    public static List<Integer> getRandomIntegerWithDuplicate(Integer input) {
        return getRandomIntegerInRangeWithDuplicate(1, input);
    }

    public static List<Integer> getRandomIntegerInRangeWithDuplicate(Integer min, Integer max) {
        if (isNull(min, max)) {
            log.error("min or max is empty");
        }
        if (min > max) {
            Integer t = max;
            max = min;
            min = t;
        }
        Integer range = max - min;

        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            ls.add(min + (int) (Math.random() * (max - min + 1)));
        }
        return ls;
    }

    private static boolean isNull(Integer min, Integer max) {
        boolean isNull = false;
        if (NullUtil.isNull(min) || NullUtil.isNull(max)) {
            log.error("min or max is empty");
            isNull = true;
        }
        return isNull;
    }

}
