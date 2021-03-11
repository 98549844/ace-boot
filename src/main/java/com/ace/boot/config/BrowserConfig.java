package com.ace.boot.config;


import com.ace.boot.util.ApplicationContextUtil;
import com.ace.boot.util.Console;
import com.ace.boot.util.DataTypeUtil;
import com.ace.boot.util.IpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;

/**
 * Application启动时自动打开默认Browser
 */
@Configuration
public class BrowserConfig {
    private static final Log log = LogFactory.getLog(BrowserConfig.class);

    static String url = "http://localhost:8088/";
    static String SwaggerUrl = "http://localhost:8088/swagger-ui.html";
    static String browser = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe ";

    /**
     * 打开默认Browser
     */

    public static void OpenWindowsDefaultBrowser() {
        try {
            ProcessBuilder proc = new ProcessBuilder(browser, url);
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("WELCOME PAGE: ", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void OpenMacDefaultBrowser() throws IOException {
        ApplicationContextUtil app = new ApplicationContextUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getClientHostInfo();

        String macUrl = url.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));
        String macSwaggerUrl = SwaggerUrl.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));

        log.info("Home Page:\t\t" + macUrl);
        String Command = "open " + macSwaggerUrl;
        if (StringUtils.hasText(macSwaggerUrl)) {
            log.info("Swagger2:\t\t" + SwaggerUrl);
            Process Child = Runtime.getRuntime().exec(Command);
        }
    }


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