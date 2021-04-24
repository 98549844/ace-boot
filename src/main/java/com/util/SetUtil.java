package com.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.util.*;

public class SetUtil {
    static private Log log = LogFactory.getLog(SetUtil.class);


    public static List setToList(Set set) {
        List ls = new ArrayList();
        if (isNull(set)) {
            log.error("Set is null");
            return ls;
        }
        for (Object o : set) {
            ls.add(o);
        }
        return ls;
    }


    public static <T extends Number> void iterateSet(Set<T> tSet) {
        Iterator<T> iterator = tSet.iterator();
        while (iterator.hasNext()) {
            log.info("1: " + iterator.next());
        }
    }



    public static Object getSetFirstValue(Set set) {
        if (isNull(set)) {
            log.error("Set is null");
            return null;
        }
        return set.iterator().next();
    }

    public static boolean isNull(Set set) {
        boolean result = false;
        if (set == null || set.size() == 0) {
            result = true;
        }
        return result;
    }

    public static boolean isNotNull(Set set) {
        boolean result = true;
        if (set == null || set.size() == 0) {
            result = false;
        }
        return result;
    }


}
