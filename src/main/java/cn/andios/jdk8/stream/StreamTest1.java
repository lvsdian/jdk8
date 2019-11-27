package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description: 创建流
 * @author:LSD
 * @when:2019/8/14/17:11
 */
public class StreamTest1 {
    public static void main(String[] args) {
        //创建Stream
        //1.Stream本身的方法
        Stream <String> stream1 = Stream.of("hello","world","hello world");

        String [] str = new String[]{"hello","world","hello world"};
        //2.Arrays.stream
        Stream stream2 = Arrays.stream(str);

        //3.list.stream
        List<String> list = Arrays.asList(str);
        Stream<String> stream3 = list.stream();

    }
}
