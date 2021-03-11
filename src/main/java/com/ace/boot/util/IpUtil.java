package com.ace.boot.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

//@Component("自定义beanName")
@Component
public class IpUtil implements ApplicationListener<WebServerInitializedEvent> {
    private final Log log = LogFactory.getLog(this.getClass());

    public static int Port;
    public static String ip;
    public static String hostName;
    public static String domain;


    public static void main(String[] args) {
        IpUtil ip = new IpUtil();
        Map m = ip.getClientHostInfo();
        MapUtil mapUtil = new MapUtil();
        mapUtil.iterateMapKeyset(m);

    }

    public  String getHostName() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+": ");

        IpUtil u = new IpUtil();
        Map m = u.getClientHostInfo();
        hostName = (String) m.get("hostName");
        return hostName;
    }


    public Map getClientHostInfo() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+": ");

        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.ip = address.getHostAddress();
        this.hostName = address.getHostName();
        this.domain = address.getHostName();

        Map m = new HashMap();

        m.put("ip", ip);
        m.put("port", Port);
        m.put("hostName", hostName);
        m.put("domain", domain);

        return m;
    }


    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {

        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+": ");

        this.Port = event.getWebServer().getPort();
    }

}