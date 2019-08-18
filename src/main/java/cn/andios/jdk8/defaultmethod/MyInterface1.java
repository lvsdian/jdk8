package cn.andios.jdk8.defaultmethod;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/11/10:05
 */
public interface MyInterface1 {
    default void myMethod(){
        System.out.println("myInterface1");
    }
}
