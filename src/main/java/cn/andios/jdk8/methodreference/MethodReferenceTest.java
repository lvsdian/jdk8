package cn.andios.jdk8.methodreference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @description: 可以将方法引用看作一个 函数指针
 *  方法引用分为4类：
 *      1.类名::静态方法名
 *      2.引用名(对象名)::实例方法名
 *      3.类名::实例方法名
 *      4.类名::new(构造方法引用)
 * @author:LSD
 * @when:2019/8/10/17:09
 */
public class MethodReferenceTest {

    public String getString1(Supplier<String> supplier){
        return supplier.get() + "test";
    }

    public String getString2(String  str,Function<String,String> function){
        return function.apply(str);
    }

    public static void main(String[] args) {
        Student student1 = new Student("张三1",991);
        Student student2 = new Student("张三3",992);
        Student student3 = new Student("张三4",993);
        Student student4 = new Student("张三2",994);

        List<Student> studentList = Arrays.asList(student1,student2,student3,student4);

        //lambda表达式形式
        studentList.sort((studentParam1,studentParam2)->Student.compareStudentByName(studentParam1,studentParam2));

        //方法引用形式  1.类名::静态方法名
        studentList.sort(Student::compareStudentByName);
        //方法引用形式  2.引用名::实例方法名
        studentList.sort(new StudentComparator()::compareStudentByName);
        //方法引用形式  3.类名::实例方法名
        studentList.sort(Student::compareByName);

        //方法引用形式  4.构造方法引用 类名::new (具体调用哪个构造方法，编译器自动判断)
        MethodReferenceTest test = new MethodReferenceTest();
        //getString1中参数为Supplier接口，不接收参数有一个返回值，所以这里调用String的无参构造
        System.out.println(test.getString1(String::new));


        //getString2中参数为 字符串,Function接口，接收一个参数有一个返回值，所以这里调用String的一个参数的构造
        System.out.println(test.getString2("hello",String::new));


        studentList.forEach(System.out::println);
    }
}
