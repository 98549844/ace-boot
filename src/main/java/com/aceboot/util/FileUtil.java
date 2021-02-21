package com.aceboot.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.*;


public class FileUtil {
    static private Log log = LogFactory.getLog(LogUtil.class);

    static String path = "/Users/garlam/IdeaProjects/ace-boot/";
    //static String path = "C:\\project\\spring-boot\\POLICE\\redmine\\15392\\log\\";
    static String fileName = "log.sql";
    static String slash = "\\";
    static String dot = ".";
    static String ext = "";
    static String fullPath = path + slash + fileName + slash + dot + ext;

    public static void main(String[] args) throws IOException {

        List<String> ls = getFileNames(path);

    }

    private static boolean isSlash(String path) {
        boolean isSlash = false;
        String end = path.substring(path.length() - 1);
        if (!path.isEmpty() && (end.equals("/") || end.equals("\\"))) {
            isSlash = true;
        } else {
            log.info("Missing slash");
        }
        return isSlash;
    }

    private static String addSlashIfMissing(String path) {
        if (!isSlash(path)) {
            String[] t = path.split("/");
            if (t.length > 0) {
                path = path + "/";
            } else {
                path = path + "\\\\";
            }
        }
        return path;
    }

    /**
     * 用缓冲区读写，来提升读写效率。
     */
    private static void CopyFileWithNewName(String path, List<String> fileNames, String ext, Boolean delFile) {
        addSlashIfMissing(path);

        log.info("Start writing file ...");
        for (String fileName : fileNames) {
            FileWriter fw = null;
            FileReader fr = null;
            String newFileName = "";
            if (!fileName.substring(fileName.length() - 4).equals(ext)) {
                newFileName = fileName.substring(0, fileName.length() - 4) + ext;
            } else {
                newFileName = "checked_" + fileName;
            }

            try {
                fr = new FileReader(path + fileName);//读
                fw = new FileWriter(path + newFileName);//写
                char[] buf = new char[1024];//缓冲区
                int len;
                while ((len = fr.read(buf)) != -1) {
                    fw.write(buf, 0, len);//读几个写几个
                }

                File file = new File(path + fileName);
                if (delFile != null && delFile && file.exists()) {
                    file.delete();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (fr != null) {
                    try {
                        fr.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (fw != null) {
                    try {
                        fw.flush();
                        fw.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        log.info("File writing complete !!!");
    }


    public static ArrayList<String> getFileNames(String path) {
        FileUtil.addSlashIfMissing(path);

        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempLists = file.listFiles();
        for (int i = 0; i < tempLists.length; i++) {
            if (tempLists[i].isFile() && !tempLists[i].getName().equals(".DS_Store")) {
                files.add(tempLists[i].getName());//file name
                // files.add(tempLists[i].toString());//full path
            }
        }
        for (int i = 0; i < files.size(); i++) {
            log.info(files.get(i));
        }
        return files;
    }

    public Map<String, Object> read(String path) throws IOException {
        File f = new File(path);
        // 建立一个输入流对象reader
        InputStreamReader reader = new InputStreamReader(new FileInputStream(f), "UTF-8");
        // 建立一个对象，它把文件内容转成计算机能读懂的语言
        BufferedReader br = new BufferedReader(reader);
        String line;
        line = br.readLine();
        int i = 1;

        StringBuilder content1 = new StringBuilder();
        StringBuilder content2 = new StringBuilder();
        List<StringBuilder> builderList = new LinkedList<>();
        while (line != null) {

            // 一次读入一行数据,并显示行数
            //content.append(i + "*: ");
            content1.append(line + System.getProperty("line.separator"));
            content2.append(line);
            builderList.add(new StringBuilder(line));
            i++;
            // 把所有内容在一行显示
            line = br.readLine();
        }
        br.close();
        reader.close();

        Map<String, Object> map = new HashMap();
        map.put("original", content1);
        map.put("oneline", content2);
        map.put("list", builderList);

        return map;
    }

    /**
     * 删除line 0 到 lineNum的内容
     * 主要用于log太大, 把多余的log删除
     *
     * @param fullPath
     * @param lineNum
     * @return
     * @throws IOException
     */
    public List<String> removeLinesByLineNum(String fullPath, int lineNum) throws IOException {
        File file = new File(fullPath);
        List<String> strList = new ArrayList<String>();
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rw");
            //Initial write position
            long writePosition = raf.getFilePointer();
            for (int i = 0; i < lineNum; i++) {
                String line = raf.readLine();
                if (line == null) {
                    break;
                }
                strList.add(line);
            }
            // Shift the next lines upwards.
            long readPosition = raf.getFilePointer();

            byte[] buff = new byte[1024];
            int n;
            while (-1 != (n = raf.read(buff))) {
                raf.seek(writePosition);
                raf.write(buff, 0, n);
                readPosition += n;
                writePosition += n;
                raf.seek(readPosition);
            }
            raf.setLength(writePosition);
        } catch (IOException e) {
            log.error("Remove Lines error", e);
            throw e;
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                log.error("close Random Access File error", e);
                throw e;
            }
        }
        return strList;
    }

    /**
     * folder not exist, create folders
     *
     * @param fullPath
     */
    public void mkDirs(String fullPath) {
        File f = new File(fullPath);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public void write(String filePath, String fileName, Object obj) {
        //boolean isOk = false;
        String type = "String";
        StringBuilder content = null;
        List<StringBuilder> contentList = new ArrayList<>();
        if (obj instanceof String) {
            content = new StringBuilder((String) obj);
            type = "String";
        }
        if (obj instanceof List) {
            contentList = (List) obj;
            type = "List";
        }

        if (filrStatus(filePath, fileName)) {
            FileOutputStream fop = null;

            try {
                log.info("File Path : " + filePath + fileName);
                File file = new File(filePath + fileName);
                //fop = new FileOutputStream(file);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-16LE");
                // get the content in bytes
                String contentInBytes = null;
                if (type.equals("String")) {
                    contentInBytes = content.toString();
                    outputStreamWriter.append(contentInBytes);
                    outputStreamWriter.flush();
                } else if (type.equals("List")) {
                    for (int i = 0; i < contentList.size(); i++) {
//                        contentInBytes = contentList.get(i).toString().getBytes();
//                        fop.write(contentInBytes);
                        contentInBytes = contentList.get(i).toString();
                        outputStreamWriter.append(contentInBytes);
                        outputStreamWriter.flush();
                    }

                } else {
                    log.info("contentInBytes: " + contentInBytes.toString());
                }

                outputStreamWriter.close();
                // fop.flush();
                // fop.close();

                log.info("File writing complete");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fop != null) {
                        fop.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    //check file and dir status
    public static boolean filrStatus(String filePath, String fileName) {


        //check dir exist
        File folder = new File(filePath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
            log.info("Directory created");
        } else {
            log.info("Directory is exist");
        }


        //check file exist
        boolean isOK = false;
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                file.setWritable(true);
                log.info("File created");
            } catch (IOException e) {
                e.printStackTrace();
            }
            isOK = true;
        } else {
            log.info("File is exist");
            if (file.exists() && file.length() == 0) {
                log.info("File is empty");
            } else {
                log.info("File is not empty, please check !");

            }
            if (file.canWrite()) {
                log.info("File can write");
                isOK = true;
            } else {
                log.info("File can't write");
                isOK = false;
            }
        }
        return isOK;
    }

}
