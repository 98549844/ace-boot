package com.aceboot.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class ObjectUtil {

    public static Boolean isArray(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().isArray();
    }

    public static Integer getObjectArraySize(Object obj) {
        if (obj == null) {
            return null;
        }
        int size = 0;
        if (obj != null && isArray(obj)) {
            size = Array.getLength(obj);
        }
        return size;
    }

    public static Object[] getObjectArray(Object obj) {
        if (obj != null && isArray(obj)) {
            Object[] os = (Object[]) obj;
            return os;
        }
        return null;
    }

    public static void printListObjectArray(List<Object[]> os) {
        if (os == null) {
            Console.println("Object NullException", Console.RED, Console.BOLD);
        }
        for (Object[] objects : os) {
            for (int i = 0; i < objects.length; i++) {
                System.out.print(objects[i] + " | ");
            }
            System.out.println();
        }
    }

    public static void printObjectArray(Object[] obj) {
        if (obj == null) {
            Console.println("Object NullException", Console.RED, Console.BOLD);
        }
        for (int i = 0; i < obj.length; i++) {
            System.out.print(obj[i] + " | ");
        }
        System.out.println();
    }

    public static void printListObject(List<Object> obj) {
        if (obj == null) {
            Console.println("Object NullException", Console.RED, Console.BOLD);
        }

        for (Object objects : obj) {
            if (isArray(objects)) {
                Object[] os = (Object[]) objects;
                for (int i = 0; i < os.length; i++) {
                    System.out.print(os[i] + " | ");
                }
            } else {
                System.out.println(objects + " | ");
            }
        }
    }

    public static String checkObjectType(Object obj) {
        if (obj == null) {
            Console.println("Object NullException", Console.RED, Console.BOLD);
        }
        if (obj instanceof Boolean) {
            Console.println("Type : Boolean", Console.BLUE);
            return "Boolean";
        }
        if (obj instanceof Integer) {
            Console.println("Type : Integer", Console.BLUE);
            return "Integer";
        }
        if (obj instanceof String) {
            Console.println("Type : String", Console.BLUE);
            return "String";
        }
        if (obj instanceof List) {
            Console.println("Type : List", Console.BLUE);
            return "List";
        }
        if (obj instanceof Map) {
            Console.println("Type : Map", Console.BLUE);
            return "Map";
        }
        return null;
    }

}
