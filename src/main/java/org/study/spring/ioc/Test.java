package org.study.spring.ioc;

import org.seckill.entity.Cart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by daiwei on 2017/11/3.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
//        Cart cart = context.getBean(Cart.class);
//        if (cart==null){
//            System.out.println("not get bean");
//        }


        Person p=null;
//        p=context.getBean("person",Person.class);
//        System.out.println(p.hashCode());

        p = context.getBean(Person.class);
        System.out.println(p.hashCode());
        p.info();
    }

}
