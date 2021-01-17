package com.aceboot.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlGenerator {

//    select mysql all table
//    SELECT table_name,table_rows FROM information_schema.tables
//    WHERE TABLE_SCHEMA = 'ace_uat' ORDER BY table_rows DESC;

    private static Log log = LogFactory.getLog(SqlGenerator.class);

    public static void main(String[] args) {
        //实体类所在的package在磁盘上的绝对路径
        String packageName = "C:\\project\\spring-boot\\src\\main\\java\\com\\entity";
        //生成sql的文件夹
        String filePath = "C:\\project\\spring-boot\\src\\main\\resources\\sql";
        //项目中实体类的路径
        String prefix = "com.entity.";
        String className;

        StringBuilder sqls = new StringBuilder();
        //获取包下的所有类名称
        List<String> list =  getAllClasses(packageName);
        for (String str : list) {
            if (!str.contains(".")) {
                continue;
            }
            className = prefix + str.substring(0, str.lastIndexOf("."));
            String sql = generateSql(className, filePath);
            sqls.append(sql);
        }
        System.out.println(sqls.toString());
        StringToSql(sqls.toString(), filePath + "report.sql");

    }

    /**
     * 根据实体类生成建表语句
     * @param className 全类名
     * @param filePath  磁盘路径  如 : d:/workspace/
     * @date 2018年4月11日
     */
    public static String generateSql(String className, String filePath) {
        try {
            Class<?> clz = Class.forName(className);
            className = clz.getSimpleName();
            Field[] fields = clz.getDeclaredFields();
            StringBuilder column = new StringBuilder();
            String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,";
            for (Field f : fields) {
                column.append(" \n `" + f.getName() + "`").append(varchar);
            }
            StringBuilder sql = new StringBuilder();
            sql.append("\n DROP TABLE IF EXISTS `" + className + "`; ").append(" \n CREATE TABLE `" + className + "`  (").append(" \n `id` int(11) NOT NULL AUTO_INCREMENT,").append(" \n " + column).append(" \n PRIMARY KEY (`id`) USING BTREE,").append("\n INDEX `id`(`id`) USING BTREE").append(" \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
            return sql.toString();
        } catch (ClassNotFoundException e) {
            log.error("该类未找到！");
            return null;
        }

    }

    /**
     * 获取包下的所有类名称,获取的结果类似于 XXX.java
     *
     * @param packageName
     * @return list
     * @date 2018年4月11日
     */
    public static List<String> getAllClasses(String packageName) {
        List<String> classList = new ArrayList<String>();
        String className ;
        File f = new File(packageName);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
                className = file.getName();
                classList.add(className);
            }
            return classList;
        } else {
            log.error("包路径未找到！");
            return null;
        }
    }

    /**
     * 将string 写入sql文件
     *
     * @param str
     * @param path
     * @author
     * @date 2018年4月11日
     */
    public static void StringToSql(String str, String path) {
        byte[] sourceByte = str.getBytes();
        if (null != sourceByte) {
            try {
                File file = new File(path);     //文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
                outStream.write(sourceByte);
                outStream.flush();
                outStream.close();  //关闭文件输出流
                System.out.println("生成成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
