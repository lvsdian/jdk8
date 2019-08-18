package cn.andios.jdk8;

import java.util.function.Supplier;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/6/16:13
 */
public class StudentTest {
    public static void main(String[] args) {
        //构造方法引用，创建Student对象实例
        Supplier<Student> supplier1 = ()->new Student();
        System.out.println(supplier1.get().getAge());;

        Supplier<Student> supplier2 = Student::new;

        System.out.println(supplier2.get().getAge());
    }
}
