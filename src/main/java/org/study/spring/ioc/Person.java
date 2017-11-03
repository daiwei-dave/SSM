package org.study.spring.ioc;

/**
 * Created by daiwei on 2017/11/3.
 */
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void info(){
 //       System.out.println("go go go");
        System.out.println("name:"+getName()+" age:"+getAge());
    }
}
