package com;

import com.util.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hello {

    public static void main(String[] args) {
        Map<String, Integer> aa = new HashMap<>();

        Map<String, String> map = new HashMap<>();
        map.put("A", "1");
        map.put("b", "3");
        map.put("c", "2");
        map.put("D", "4");
        map.put("e", "2");
        map.put("f", "4");
        map.put("G", "3");
        map.put("h", "2");
       String a =  map.get("h");
        System.out.println(a);

        MapUtil mapUtil = new MapUtil();
        Map<Object, List<Map.Entry<String, String>>> ls= MapUtil.groupElement(map);

        mapUtil.iterateMapKeyset(ls);
    }
}
