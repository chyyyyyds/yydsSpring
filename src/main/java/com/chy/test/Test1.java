package com.chy.test;

import com.chy.context.ClassPathXmlApplicationContext;


public class Test1 {
    public static void main(String[] args) throws  Exception{
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        Aservice aService = (Aservice)ioc.getBean("aservice");
        aService.sayHello();
    }
}
