package com.aceboot.data;


import com.aceboot.entity.jpa.TestEntity;
import com.aceboot.entity.jpa.UserEntity;
import com.aceboot.entity.mybatis.TestEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) {
        List<TestEntity> ls = getTestEntity();
        for (TestEntity t : ls) {
            System.out.println(t.toString());
        }
    }

    public static List<UserEntity> getTestDataList() {
        UserEntity u1 = new UserEntity();
        u1.setUserName("Garlam");
        u1.setEmail("garlam_au@qq.com");

        UserEntity u2 = new UserEntity();
        u2.setUserName("lily");
        u2.setEmail("lily_fu@qq.com");

        UserEntity u3 = new UserEntity();
        u3.setUserName("peter");
        u3.setEmail("peter_lee@qq.com");

        UserEntity u4 = new UserEntity();
        u4.setUserName("mary");
        u4.setEmail("mary_ma@qq.com");

        List<UserEntity> ulist = new ArrayList<>();
        ulist.add(u1);
        ulist.add(u2);
        ulist.add(u3);
        ulist.add(u4);
        return ulist;
    }

    /**
     * @return
     */
    public static List<TestEntity> getTestEntity() {
        List<TestEntity> testEntityList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i < 30) {
                TestEntity testEntity = new TestEntity();
                testEntity.setId(get4Int());
                testEntity.setUserId(get1Int());
                testEntity.setUserName("Peter");
                testEntity.setEmail(get2Int()+"peter@domain.com");
                testEntityList.add(testEntity);
            } else if (i >= 30 && i < 85) {
                TestEntity testEntity = new TestEntity();
                testEntity.setId(get4Int());
                testEntity.setUserId(get1Int());
                testEntity.setUserName("Lily");
                testEntity.setEmail(get2Int()+"lily@domain.com");
                testEntityList.add(testEntity);
            } else {
                TestEntity testEntity = new TestEntity();
                testEntity.setId(get4Int());
                testEntity.setUserId(get1Int());
                testEntity.setUserName("Garlam");
                testEntity.setEmail(get2Int()+"garlam@domain.com");
                testEntityList.add(testEntity);
            }
        }
        return testEntityList;
    }

    public static List<TestEnt> getMybatisTestEnt() {
        List<TestEnt> testEntityList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i < 30) {
                TestEnt testEntity = new TestEnt();
             //   testEntity.setId(get4Int());
                testEntity.setUserId(get1Int());
                testEntity.setUserName("Peter");
                testEntity.setEmail(get2Int()+"_peter@domain.com");
                testEntityList.add(testEntity);
            } else if (i >= 30 && i < 85) {
                TestEnt testEntity = new TestEnt();
            //   testEntity.setId(get4Int());
                testEntity.setUserId(get1Int());
                testEntity.setUserName("Lily");
                testEntity.setEmail(get2Int()+"_lily@domain.com");
                testEntityList.add(testEntity);
            } else {
                TestEnt testEntity = new TestEnt();
             //   testEntity.setId(get4Int());
                testEntity.setUserId(get1Int());
                testEntity.setUserName("Garlam");
                testEntity.setEmail(get2Int()+"_garlam@domain.com");
                testEntityList.add(testEntity);
            }
        }
        return testEntityList;
    }


    public static int get1Int() {
        Random random = new Random();
        int i = random.nextInt(9) + 1;
        return i;
    }

    public static int get2Int() {
        Random random = new Random();
        int i = random.nextInt(90) + 10;
        return i;
    }

    public static int get3Int() {
        Random random = new Random();
        int i = random.nextInt(900) + 100;
        return i;
    }

    public static int get4Int() {
        Random random = new Random();
        int i = random.nextInt(9000) + 1000;
        return i;
    }

    public static int get5Int() {
        Random random = new Random();
        int i = random.nextInt(90000) + 10000;
        return i;
    }

    public static int get6Int() {
        Random random = new Random();
        int i = random.nextInt(900000) + 100000;
        return i;
    }


}
