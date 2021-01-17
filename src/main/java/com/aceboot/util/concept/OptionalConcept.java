package com.aceboot.util.concept;


import com.aceboot.entity.mybatis.UserEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalConcept {
    public void checkNullPointSample() {
        UserEnt u = new UserEnt();
        u.setEmail("garlam@qq.com");
        String result = Optional.ofNullable(u).map(i -> i.getEmail()).orElse("NULL EMAIL");
        System.out.println(result);
    }

    public void checkListNullPointSample() {
        List<String> listStr = new ArrayList<>();
        listStr.add("abc");
        listStr.add(null);
        listStr.add("EDF");
        Optional.ofNullable(listStr).ifPresent(ls -> {
            for (String s : listStr) {
                System.out.println(s);
            }
        });
    }
}
