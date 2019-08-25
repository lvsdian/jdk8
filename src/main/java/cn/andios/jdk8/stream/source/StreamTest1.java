package cn.andios.jdk8.stream.source;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;
/**
 * @description:
 * @author:LSD
 * @when:2019/8/21/19:01
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Student student1 = new Student("张三",80);
        Student student2 = new Student("李四",100);
        Student student3 = new Student("王五",90);
        Student student4 = new Student("赵六",100);

        List<Student> studentList = Arrays.asList(student1,student2,student3,student4);
        List<Student> students = studentList.stream().collect(toList());
        students.forEach(System.out::println);

        //Collectors计数
        studentList.stream().collect(counting());
        //stream技术
        studentList.stream().count();

    }
}
