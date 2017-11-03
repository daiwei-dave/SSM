package org.study.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by daiwei on 2017/11/3.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        Person p=context.getBean("person",Person.class);
        p.info();
    }

}
