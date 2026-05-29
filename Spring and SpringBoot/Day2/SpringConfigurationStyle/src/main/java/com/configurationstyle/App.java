package com.configurationstyle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        /* ---------- Annotation Based Configuration ---------- */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

        System.out.println("***********Annotation Based Configuration************************************************");
        DelegateXmlBased delegate = context.getBean("delegate", DelegateXmlBased.class);
        delegate.notifyUser();
        context.close();
        
        

        /* ---------- XML Based Configuration ---------- */
        ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("***********XML Based Configuration*******************************************************");
        DelegateXmlBased delegate1 = context1.getBean("delegate", DelegateXmlBased.class);
        delegate1.notifyUser();
        ((ClassPathXmlApplicationContext) context1).close();
        
        

        /* ---------- Java Based Configuration ---------- */
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(JavaConfig.class);

        System.out.println("***********Java Based Configuration***********************************************************");
        DelegateXmlBased delegate2 = context2.getBean("delegate", DelegateXmlBased.class);
        delegate2.notifyUser();
        context2.close();
    }
}