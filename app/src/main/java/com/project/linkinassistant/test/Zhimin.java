package com.project.linkinassistant.test;

/**
 * Created by lzm on 2017/8/17.
 */
public class Zhimin {

    //公有+静态+无参数
    public static void hello(){
        System.out.println("hello");
    }

    //公有+静态+有参数+有返回值
    public static String hello2(String name, int age){
        System.out.println("hello2");
        return "hello: " + name + ", " + age;
    }

    //公有+非静态+有参数+有返回值
    public String hello3(String name, int age){
        System.out.println("hello3");
        return "hello: " + name + ", " + age;
    }

    //私有+静态+无参数
    private static void hello4(){
        System.out.println("hello4");
    }
}
