package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/16/11:46
 */
public class StreamTest5 {
    public static void main(String[] args) {
        //将这个list中的所有元素字母变为大写
        List<String> list = Arrays.asList("hello","world","hello world");
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);


        //每个stream里面是多个list，list里面装的integer
        Stream <List<Integer>> stream1 = Stream.of(Arrays.asList(1,2,3),Arrays.asList(4,5,6),Arrays.asList(7,8,9));
        Stream <List<Integer>> stream2 = Stream.of(Arrays.asList(1,2,3),Arrays.asList(4,5,6),Arrays.asList(7,8,9));

        //flatMap将stream里的多个list打通，放在一个Stream里面，返回值为Stream<Integer>
        //map只是简单的映射，返回值为Stream<Stream<Integer>>
        stream1.flatMap(theList->theList.stream().map(Math::abs)).forEach(System.out::println);
        stream2.map    (theList->theList.stream().map(Math::abs)).forEach(item->item.forEach(System.out::println));

    }
}
