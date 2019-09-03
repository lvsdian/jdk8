package cn.andios.jdk8.stream.source;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/31/16:25
 */
public class StreamTest3 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world");

        list.stream().forEach(System.out::println);
    }
}
