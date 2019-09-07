package cn.andios.jdk8.stream.source;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/31/16:25`
 */
public class StreamTest3 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome","hi","goodbye");

        //这里直接对源进行forEach操作，会执行java.util.stream.ReferencePipeline.Head.forEach这个foreach方法
        list.stream().forEach(System.out::println);
        //这里对源进行了中间操作，会执行java.util.stream.ReferencePipeline.forEach这个foreach方法
        list.stream().map(item->item).forEach(System.out::println);
    }
}
