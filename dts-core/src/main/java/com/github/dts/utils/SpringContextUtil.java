package com.github.dts.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public synchronized static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext =
                    new ClassPathXmlApplicationContext(new String[]{"classpath*:spring/applicationContext.xml"});
        }
        return applicationContext;
    }

    @Override
    public synchronized void setApplicationContext(ApplicationContext context) throws BeansException {
        if (applicationContext == null) {
            applicationContext = context;
        }
    }

    public synchronized static void setApplicationContextManual(ApplicationContext context) {
        if (applicationContext == null) {
            applicationContext = context;
        }
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getApplicationContext().getBean(beanName, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return getApplicationContext().getBean(requiredType);
    }

    public static Class<?> getType(String beanName) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getType(beanName);
    }

}
