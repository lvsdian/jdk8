package cn.andios.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *  java.lang.FunctionalInterface
 *  函数式接口：
 *      1.如果一个接口只有一个抽象方法，那么该接口就是一个函数式接口
 *      2.如果我们在某个接口上声明了FunctionInterface注解，那么编译器会按照函数式接口的定义来要求该接口
 *      3.如果一个接口只有一个抽象方法，但我们并没有给该接口声明FunctionInterface,编译器依旧将该接口看作函数式接口
 *      类似于Override注解，加上后增加可读性，如果出错也可以检测出来
 *      4.函数式接口实例，可通过lambda表达式，方法引用，构造方法引用来实现。
 * @description:
 * @author:LSD
 * @when:2019/8/4/14:53
 */
public class Test1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("---------------------------");

        //list.forEach参数为一个Consumer接口，接收一个参数，执行操作，无返回值
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("---------------------------");

        //lambda表达式创建实例
        list.forEach(i-> System.out.println(i));
        System.out.println("---------------------------");

        //方法引用创建实例
        list.forEach(System.out::println);

    }
}
