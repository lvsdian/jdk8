package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/20/18:30
 */
public class StreamTest10 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("word","world","hello","hello world");
        List<String> list2 = Arrays.asList("word","world","hello","hello world");

        //找到每个元素的长度(映射的结果是长度，为整型，所以用mapToInt)，判断长度是否等于5，再findFirst找到返回的第一个元素，返回一个Optional
        list1.stream().mapToInt(item -> item.length()).filter(length -> length == 5).findFirst().ifPresent(System.out::println);//5

        System.out.println("---------------------------");

        //这里只会打印word，world，不是打印所有的元素，因为：
        //可以把流看成一个容器，里面存放对每个元素的操作，当对流进行处理时，会对每个元素逐个应用这些操作，比如第1个元素，应用第1个操作，第2个操作，
        //第3个操作...，不是对第1个元素应用第1个操作，再对第2个元素应用第1个操作。
        //流的短路操作;这里的findFirst找到world之后，符合要求，就不会继续往后计算，所有后面的不会打印
        list2.stream().mapToInt(item ->{
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);//word  world 5
    }
}
