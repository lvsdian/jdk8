package cn.andios.jdk8.defaultmethod;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/11/10:08
 */
public interface MyInterface2 {
    default void myMethod(){
        System.out.println("myInterface2");
    }
}
