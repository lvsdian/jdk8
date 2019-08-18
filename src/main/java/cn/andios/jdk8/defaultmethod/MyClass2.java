package cn.andios.jdk8.defaultmethod;

/**
 * @description: MyClass2继承MyInterface1Impl类,实现了MyInterface2接口，其中，MyInterface1Impl与MyInterface2中都有myMethod方法
 *  此时MyClass2对象调用myMethod方法时会调用父类(MyInterface1Impl)中的方法，因为：
 *  实现类优先级高于接口优先级
 * @author:LSD
 * @when:2019/8/13/18:29
 */
public class MyClass2 extends MyInterface1Impl implements MyInterface2{
    public static void main(String[] args) {
        MyClass2 myClass2 = new MyClass2();
        myClass2.myMethod();
    }
}
