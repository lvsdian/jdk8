package cn.andios.jdk8.stream.source;

import java.util.*;

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
        Student student5 = new Student("赵六",100);

        List<Student> studentList = Arrays.asList(student1,student2,student3,student4,student5);
        List<Student> students = studentList.stream().collect(toList());
        students.forEach(System.out::println);

        //Collectors计数
        studentList.stream().collect(counting());
        //stream计数
        studentList.stream().count();

        studentList.stream().collect(minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        System.out.println("平均值：" + studentList.stream().collect(averagingInt(Student::getScore)));
        System.out.println("总和：" + studentList.stream().collect(summingInt(Student::getScore)));
        System.out.println("汇总信息：" + studentList.stream().collect(summarizingInt(Student::getScore)));
        System.out.println("姓名拼接(无前后缀)：" + studentList.stream().map(Student::getName).collect(joining("-")));
        System.out.println("姓名拼接(有前后缀)：" + studentList.stream().map(Student::getName).collect(joining("-","begin "," end")));
        System.out.println("-----------------------");

        //先根据分数分组，所以返回的map的键类型为Integer，值的类型为List<Student>
        //再将List<Student>根据姓名进行分组，所以键的类型为String,值得类型为List<Student>
        Map<Integer,Map<String,List<Student>>> map1=  studentList.stream().collect(groupingBy(Student::getScore,groupingBy(Student::getName)));
        System.out.println(map1);
        System.out.println("------------------------");

        Map<Boolean, List<Student>> map2 = studentList.stream().collect(partitioningBy(student -> student.getScore() > 90));
        System.out.println(map2);
        System.out.println("------------------------");

        Map<Boolean, Map<Boolean, List<Student>>> map3 = studentList.stream().collect(partitioningBy(student -> student.getScore() > 80, partitioningBy(student -> student.getScore() > 90)));
        System.out.println(map3);
        System.out.println("------------------------");

        Map<Boolean, Long> map4 = studentList.stream().collect(partitioningBy(student -> student.getScore() > 90, counting()));
        System.out.println(map4);
        System.out.println("-------------------------");

        Map<String, Student> map5 = studentList.stream().collect(groupingBy(Student::getName, collectingAndThen(minBy(Comparator.comparingInt(Student::getScore)), Optional::get)));
        System.out.println(map5);
    }
}
