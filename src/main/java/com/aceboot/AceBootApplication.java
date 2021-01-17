package com.aceboot;

import com.aceboot.util.ApplicationContextUtil;
import com.aceboot.util.IpUtil;
import com.aceboot.util.MapUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;


@SpringBootApplication
public class AceBootApplication {
    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(AceBootApplication.class, args);
       // BrowserConfig.OpenDefaultBrowser();
       // BrowserConfig.OpenSwaggerPage();
        //print all application context bean name
        ApplicationContextUtil.printAllBeanName(applicationContext);

        //iterate bean value by name
        //print server side information
        ApplicationContextUtil app = new ApplicationContextUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getClientHostInfo();
        MapUtil mapUtil = new MapUtil();
        mapUtil.iterateMapKeyset(m);
    }


}
