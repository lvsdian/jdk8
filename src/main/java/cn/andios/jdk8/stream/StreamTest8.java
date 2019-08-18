package cn.andios.jdk8.stream;

import java.util.stream.IntStream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/17/19:06
 */
public class StreamTest8 {
    public static void main(String[] args) {
        //以下例子中，如果distinct在limit之前调用，则先会去重，再限制个数为10个，但由于结果为0,1，
        //个数为2，小于10,所以程序会一直执行下去，应该是先做限制，再去重。
        IntStream.iterate(0,i->(i + 1) % 2).limit(10).distinct().forEach(System.out::println);
        IntStream.iterate(0,i->(i + 1) % 2).distinct().limit(10).forEach(System.out::println);
    }
}
