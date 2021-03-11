package com.ace.boot.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUtil {
    private final Log log = LogFactory.getLog(this.getClass());


    public static void main(String[] args) {
        // Map map = new Hashtable();
        // Map map = new HashMap();
        //Map map = new ConcurrentHashMap();
        //  Map map = new LinkedHashMap();
        // Map map = new TreeMap();

        Map<String, Object> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");
        map.put("c", "cc");
        map.put("d", "dd");
        map.put("e", "ee");
        MapUtil mapUtil = new MapUtil();
        mapUtil.iterateMapKeyset(map);
        //  mapUtil.printEntrySet(map);
        System.out.println("**************************************");

//        TestEntity t1 = new TestEntity();
//        t1.setId(10);
//        t1.setUserId(102);
//        t1.setUserName("garlam");
//        t1.setEmail("garlam_au@qq.com");
//        Map m1 = objectToMap(t1);
//        mapUtil.printKeyValuesOnMap(m1);


        Map<String, Integer> SataMap = new HashMap<>();
        final Map<Integer, List<String>> groupMap = new HashMap<>();
        SataMap.put("A", 1);
        SataMap.put("b", 3);
        SataMap.put("c", 2);
        SataMap.put("D", 4);
        SataMap.put("e", 2);
        SataMap.put("f", 4);
        SataMap.put("G", 3);
        SataMap.put("h", 2);
        Map<Integer, List<Map.Entry<String, Integer>>> result = SataMap.entrySet().stream().collect(Collectors.groupingBy(c -> c.getValue()));

        System.out.println(result);
    }


    public static void groupByMapsElement() {
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, List<Object>> groupMap = new HashMap<>();
        map.put("A", 1);
        map.put("b", 3);
        map.put("c", 2);
        map.put("D", 4);
        map.put("e", 2);
        map.put("f", 4);
        map.put("G", 3);
        map.put("h", 2);
        Map<Integer, List<Map.Entry<String, Integer>>> result = map.entrySet().stream().collect(Collectors.groupingBy(c -> c.getValue()));
    }

    /**
     * 历遍所有map元素
     *
     * @param map
     */
    public void iterateMapKeyset(@NotNull Map map) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            log.info("Key: " + key.toString());
            log.info("value: " + val.toString());
        }
        log.info("The Map size is : " + map.size());
        log.info("key set : " + map.keySet());
    }

    public void printKeyValuesOnMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            //System.out.println("Key: " + k + "   " + "value: " + v.toString());
            System.out.print("Key: ");
            Console.print(k, Console.BOLD, Console.BLUE);
            System.out.print("   ");
            System.out.print("value: ");
            Console.println(v.toString(), Console.BOLD, Console.BLUE);
        }
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static <T extends Object> T flushObject(T t, Map<String, Object> params) {
        if (params == null || t == null) {
            return t;
        }

        Class<?> clazz = t.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    // 获取属性的名字
                    String name = fields[i].getName();
                    Object value = params.get(name);
                    if (value != null && !"".equals(value)) {
                        // 注意下面这句，不设置true的话，不能修改private类型变量的值
                        fields[i].setAccessible(true);
                        fields[i].set(t, value);
                    }
                }
            } catch (Exception e) {
            }
        }
        return t;
    }

}
