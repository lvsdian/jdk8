package cn.andios.jdk8.defaultmethod;

/**
 * @description: MyClass1同时实现MyInterface1,MyInterface2两个接口
 *  这两个接口都有myMethod这个默认方法，MyClass1中需要重写这个方法，不然会报错。
 * @author:LSD
 * @when:2019/8/11/10:07
 */
public class MyClass1 implements MyInterface1,MyInterface2{
    @Override
    public void myMethod() {
        //重写时调用MyInterface2中的myMethod方法
        MyInterface2.super.myMethod();
    }

    public static void main(String[] args) {
        MyClass1 myClass1 = new MyClass1();
        myClass1.myMethod();
    }
}
