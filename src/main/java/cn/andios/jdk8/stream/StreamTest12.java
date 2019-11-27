package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/21/12:08
 */
public class StreamTest12 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("hi","hello","你好");
        List<String> list2 = Arrays.asList("张三","李四","王五");

        //这里用flatMap，返回值类型为Stream<String>
        list1.stream().flatMap(item1 -> list2.stream().map(item2 -> item1 + " " + item2)).forEach(System.out::println);
        //result：
        //  hi 张三
        //  hi 李四
        //  hi 王五
        //  hello 张三
        //  hello 李四
        //  hello 王五


        System.out.println("----------------");
        //这里用map，返回值类型为Stream<Stream<String>>
        list1.stream().map(item1 -> list2.stream().map(item2 -> item1 + " " + item2)).forEach(System.out::println);
        //result：
        //  java.util.stream.ReferencePipeline$3@568db2f2
        //  java.util.stream.ReferencePipeline$3@378bf509
        //  java.util.stream.ReferencePipeline$3@5fd0d5ae

        System.out.println("----------------");
        list1.stream().map(item1 -> list2.stream().map(item2 -> item1 + " " + item2)).forEach(item->item.forEach(System.out::println));
        //result：
        //  hi 张三
        //  hi 李四
        //  hi 王五
        //  hello 张三
        //  hello 李四
        //  hello 王五

    }
}
