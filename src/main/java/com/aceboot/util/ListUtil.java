package com.aceboot.util;

import com.aceboot.data.DataGenerator;
import com.aceboot.entity.dao.jpa.TestEntity;
import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ListUtil {


    //根据长度把list拆分
    public static List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<List<T>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }


    public static List removeDuplicate(List list) {
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public static Integer getMaxByList(List<Integer> list) {
        int i = 0;
        try {
            if (list == null) {
                return i;
            }
            i = Collections.max(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 由小到大排序
     *
     * @param list
     * @return
     */
    public static List sortAsc(List list) {
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//        TestEntity t = new TestEntity();
//        t.setUserName("garlam");
//        t.setEmail("garlam_au@qq.com");
//
//        Class c = t.getClass();
//        Method m[] = c.getDeclaredMethods();
//        for (int i = 0; i < m.length; i++) {
//            if (m[i].getName().indexOf("get") == 0) {
//                System.out.println("方法名：" + m[i].getName() + "--值：" + m[i].invoke(t, new Object[0]));
//            }
//        }

        // ListUtil.sortListByGuava();
        ListUtil.sortListByCollections(null);
    }


    /**
     * 由大到小排序
     *
     * @param list
     * @return
     */
    public static List sortDesc(List list) {
        Collections.sort(list);
        int x = list.size() - 1;
        List reverse = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            reverse.add(list.get(x - i));
        }
        return reverse;
    }


    /**
     * 随机选取list的元素(重覆)
     *
     * @param ls
     * @param percentage
     * @return
     */
    public static List getRandomListByPercent(List ls, Integer percentage) {
        if (!checkNullInteger(percentage)) {
            return null;
        }
        Integer percentItems = ls.size() * percentage / 100;

        List resultLs = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < percentItems; i++) {
            int n = random.nextInt(ls.size());
            resultLs.add(ls.get(n));
        }
        return resultLs;
    }

    /**
     * 随机选取list的元素(不重覆)
     *
     * @param ls
     * @param percentage
     * @return
     */
    public static List getRandomListByPercentNonRepeatable(List ls, Integer percentage) {
        if (!checkNullInteger(percentage)) {
            return null;
        }

        Integer percentItems = ls.size() * percentage / 100;

        List resultLs = new ArrayList();
        Random random = new Random();
        int i = 0;
        while (resultLs.size() < percentItems) {
            int n = random.nextInt(ls.size());
            if (!resultLs.contains(ls.get(n))) {
                resultLs.add(ls.get(n));
            }
        }

        return resultLs;
    }

    private static boolean checkNullInteger(Integer percentage) {
        if (percentage == null) {
            System.out.println("Parameter are not require, please check!");
            return false;
        }
        return true;
    }

    /**
     * just a sample
     * need override
     */
    public static void sortListByGuava(List<TestEntity> testList) {
        if (testList == null) {
            testList = DataGenerator.getTestEntity();
        }
        int size = testList.size();
        System.out.println("********before sorting");
        for (int i = 0; i < size; i++) {
            if (i % 10 != 0) {
                System.out.print(testList.get(i).getId() + " ; ");
            } else {
                System.out.println();
            }
        }
        System.out.println("");
        System.out.println("********after sorting");

        Ordering<TestEntity> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<TestEntity, Integer>() {
            @Override
            public Integer apply(/*@Nullable*/ TestEntity testEntity) {
                return testEntity.getId();
            }
        });
        testList.sort(ordering);

        for (int i = 0; i < size; i++) {
            if (i % 10 != 0) {
                System.out.print(testList.get(i).getId() + " ; ");
            } else {
                System.out.println();
            }
        }
    }

    /**
     * just a sample
     * need override
     */
    public static void sortListByCollections(List<TestEntity> testList) {
        if (testList == null) {
            testList = DataGenerator.getTestEntity();
        }
        int size = testList.size();
        System.out.println("********before sorting");
        for (int i = 0; i < size; i++) {
            if (i % 10 != 0) {
                System.out.print(testList.get(i).getId() + "(" + testList.get(i).getUserName() + ")" + " ; ");
            } else {
                System.out.println();
            }
        }
        System.out.println("");
        System.out.println("********after sorting");


        //compare函数的返回值-1, 1, 0
        //-1表示两个数位置交换，1表示不交换，0岂不是没有什么存在意义
        Collections.sort(testList, new Comparator<TestEntity>() {
            @Override
            public int compare(TestEntity o1, TestEntity o2) {
                int i = 0;
                if (i == 0) {
                    i = o1.getUserName().compareTo(o2.getUserName());
                }

                if (i == 0) {
                    i = o1.getId().compareTo(o2.getId());
                }
                return i;
            }
        });

        for (int i = 0; i < size; i++) {
            if (i % 10 != 0) {
                System.out.print(testList.get(i).getId() + "(" + testList.get(i).getUserName() + ")" + " ; ");
            } else {
                System.out.println();
            }
        }
    }

    public String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        String result = list.isEmpty() ? "" : sb.toString().substring(0, sb.toString().length() - 1);
        System.out.println(result);
        return result;
    }


}



