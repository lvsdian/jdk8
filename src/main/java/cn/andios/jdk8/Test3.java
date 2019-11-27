package cn.andios.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/5/10:28
 */
public class Test3 {
    public static void main(String[] args) {
        //两个对象的表现形式都是()->{}，但代表不同的对象
        MyInterface1 interface1 = ()->{};
        System.out.println(interface1.getClass().getInterfaces()[0]);//interface cn.andios.jdk8.MyInterface1
        MyInterface2 interface2 = ()->{};
        System.out.println(interface2.getClass().getInterfaces()[0]);//interface cn.andios.jdk8.MyInterface2

        //jdk8之前出现的函数式接口也支持lambda
        new Thread(()->{
            System.out.println("hello world");
        }).start();//hello world

        List<String> list = Arrays.asList("hello world","hello","world");
        //将以上list内容变为大写
        //1.
        list.forEach(item ->{
            item.toUpperCase();
        });
        //2.
        list.stream().map(item->item.toUpperCase()).forEach(item-> System.out.println(item));//HELLO WORLD HELLO WORLD
        //3.
        list.stream().map(String::toUpperCase).forEach(System.out::println);//HELLO WORLD HELLO WORLD
    }
}

interface MyInterface1{
    void myMethod1();
}

interface MyInterface2{
    void myMethod2();
}
