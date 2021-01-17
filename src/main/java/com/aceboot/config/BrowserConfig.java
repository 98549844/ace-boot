package com.aceboot.config;


import com.aceboot.util.Console;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Application启动时自动打开默认Browser
 */
@Configuration
public class BrowserConfig {

    static String url = "http://localhost:8088/";
    static String SwaggerUrl = "http://localhost:8088/swagger-ui.html";
    // static String browser = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe ";
    static String browser = "/Users/garlam/Library/Application Support/Google/Chrome/Default";

    /**
     * 打开默认Browser
     */
/*
    public static void OpenDefaultBrowser() {
        try {
            ProcessBuilder proc = new ProcessBuilder(browser, url);
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("WELCOME PAGE:\t",url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    /**
     * 打开默认Browser
     */
    public static void OpenSwaggerPage() {
        try {
            ProcessBuilder proc = new ProcessBuilder(browser, SwaggerUrl);
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("SWAGGER:\t\t", SwaggerUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PrintUrl(String banner, String url) {
        System.out.print(LocalDateTime.now() + "  ");
        Console.print("INFO ", Console.GREEN);
        Console.println(banner + url, Console.BOLD, Console.BLUE);
    }

    public static boolean getOsInfo() {
        //Java获取当前操作系统的信息
        //https://blog.csdn.net/qq_35981283/article/details/73332040
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        System.out.println("操作系统称种类：" + props.getProperty("os.name"));
        if (osName != null && osName.toLowerCase().contains("windows")) {
            return true;
        } else {
            return false;
        }
    }

}