package com.aceboot.util.concept;

import com.aceboot.data.DataGenerator;
import com.aceboot.entity.jpa.TestEntity;
import com.aceboot.entity.jpa.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapConcept {

    public static void main(String[] args) {
        //cacheMapsConcept();
        groupMapsByElement();
    }

    static Map<Integer, UserEntity> userEntityCache = new HashMap<>();

    public static void cacheMapsConcept() {
        //list of testEntity data (100items)
        List<TestEntity> testEntityList = DataGenerator.getTestEntity();

        //get all fk and add to list
        List<Integer> testUserIds = new ArrayList<>();
        for (TestEntity testEntity : testEntityList) {
            if (!userEntityCache.containsKey(testEntity.getId())) {
                testUserIds.add(testEntity.getUserId());
            }
        }
        System.out.println("-----");

        // query db in Ids
        List<UserEntity> userList = new ArrayList<>();
        //   List<UserEntity> userList = Mybatis.getDataInIds();

        //for entity and put into maps
        for (UserEntity user : userList) {
            userEntityCache.put(user.getId(), user);
        }
    }

    public static void groupMapsByElement() {
        List<TestEntity> testList = DataGenerator.getTestEntity();

        //group by UserName
        Map<String, List<TestEntity>> groupDdtlMap = new HashMap<String, List<TestEntity>>();
        for (TestEntity test : testList) {
            String userName = test.getUserName();
            if (groupDdtlMap.containsKey(userName)) {
                groupDdtlMap.get(userName).add(test);
            } else {
                //   groupDdtlMap.put(rn, Lists.newArrayList(prpDisposalItem));
                List<TestEntity> testLs = new ArrayList<>();
                testLs.add(test);
                groupDdtlMap.put(userName, testLs);
            }
        }
        System.out.println("--------");
    }
}