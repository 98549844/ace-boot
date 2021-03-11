package com.ace.boot;

import com.ace.boot.config.BrowserConfig;
import com.ace.boot.util.ApplicationContextUtil;
import com.ace.boot.util.MapUtil;
import com.ace.boot.util.IpUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Map;

/*
@SpringBootConfiguration
        读取配置文件，配置文件的路径是当前根目录(src/main/resources/application.yml等)
@EnableAutoConfiguration
        开启自动配置，扫描当前的所有依赖的jar包，发现新的依赖出现将会将会根据依赖完各种自动配置
        (扫描start_web，自动配置内置tomcat默认路径、端口；依赖了rabbitmq，自动配置rabbitTemble)
@ComponetScan
        属于Spring框架(@Component,@Service,@Controller,@Repository,@Entity)，扫描范围默认情况下是启动类坐在的同名包及其子孙包
*/
@SpringBootApplication
public class AceBootApplication {
    public static ApplicationContext applicationContext;

    public static void main(String[] args) throws IOException {
        applicationContext = SpringApplication.run(AceBootApplication.class, args);

        //iterate bean value by name
        //print server side information
        ApplicationContextUtil app = new ApplicationContextUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getClientHostInfo();
        MapUtil mapUtil = new MapUtil();
        mapUtil.iterateMapKeyset(m);

        BrowserConfig.OpenMacDefaultBrowser();

        // BrowserConfig.OpenWindowsDefaultBrowser();
        // BrowserConfig.OpenSwaggerPage();

        //print all application context bean name
        // ApplicationContextUtil.printAllBeanName(applicationContext);
    }


}
