package com.ace.boot.util;

import com.ace.boot.AceBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ApplicationContextUtil {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);
    public static ApplicationContext applicationContext = null;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public ApplicationContextUtil() {
        if (applicationContext == null) {
            applicationContext = AceBootApplication.applicationContext;
        }
    }

    /**
     * print all bean name
     *
     * @param applicationContext
     */
    public static void printAllBeanName(ApplicationContext applicationContext) {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        // String[] beanNames = applicationContext.getBeanNamesForAnnotation(RequestMapping.class);//所有添加该注解的bean
        logger.info("total bean: {}", applicationContext.getBeanDefinitionCount());
        int i = 0;
        for (String s : beanNames) {
            logger.info("{},beanName:{}", ++i, s);
        }
    }


    /**
     * getBean By beanName
     *
     * @param name
     * @return
     */
    public Object getBeanByName(String name) {
        Object object = applicationContext.getBean(name);
        return object;
    }

    public static void main(String[] args) {

    }

    /**
     * 加载ApplicationContext Sample
     *
     * @param
     */
    public void getXmlConfigFile() {
        //第一种: FileSystemXmlApplicationContext
        //加载单个配置文件
        ApplicationContext ctx1 = new FileSystemXmlApplicationContext("bean.xml");
        //加载单个配置文件
        String[] locations = {"bean1.xml", "bean2.xml", "bean3.xml"};
        ApplicationContext ctx2 = new FileSystemXmlApplicationContext(locations);
        //根据具体路径加载文件
        ApplicationContext ctx3 = new FileSystemXmlApplicationContext("D:/project/bean.xml");

    }


    /**
     * ClassPathXmlApplicationContext --从classpath路径加载配置文件，创建Bean对象
     */
    public void getClassPathXmlApplicationContext() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Object claz = (Object) ctx.getBean("beanName");
    }

    public void getApplicationContext(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext(); //arg0.getSession().getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        Object clazz = (Object) ctx.getBean("beanName");
    }
}
