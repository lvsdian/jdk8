package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/21/17:47
 */
public class StreamTest13 {

    public static void main(String[] args) {
        Student student1 = new Student("张三",100,20);
        Student student2 = new Student("李四",900,20);
        Student student3 = new Student("王五",100,30);
        Student student4 = new Student("张三",800,40);

        List<Student> studentList = Arrays.asList(student1,student2,student3,student4);

        //根据name分组，类似sql：select * from student group by name
        Map<String, List<Student>> map1 = studentList.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(map1);

        //根据name分组并统计数量，类似sql：select name,count(*) from student group by name;
        Map<String, Long> map2 = studentList.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        System.out.println(map2);

        //根据name分组并计算平均分数
        Map<String, Double> map3 = studentList.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingInt(Student::getScore)));
        System.out.println(map3);

        //分区，两种情况(true和false)，是分组的特殊情况
        Map<Boolean, List<Student>> map4 = studentList.stream().collect(Collectors.partitioningBy(student -> student.getScore() >= 30));
        System.out.println(map4);
    }
}
