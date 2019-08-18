package cn.andios.jdk8.stream;

import java.util.stream.IntStream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/14/17:22
 */
public class StreamTest2 {
    public static void main(String[] args) {
        //创建IntStream
        IntStream.of(1,2,3,4,5).forEach(System.out::println);

        //1-5左闭右开
        IntStream.range(1,5).forEach(System.out::println);
        //1-5左闭右闭
        IntStream.rangeClosed(1,5).forEach(System.out::println);
    }
}
