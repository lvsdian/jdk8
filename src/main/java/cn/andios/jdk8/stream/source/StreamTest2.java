package cn.andios.jdk8.stream.source;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/31/15:46
 */
public class StreamTest2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world");

        NullPointerException nullPointerException = new NullPointerException("my exception");

        try (Stream<String> stream = list.stream()){
            stream.onClose(()->{
                System.out.println("aaa");
                //ArithmeticException与ArrayIndexOutOfBoundsException是不同的一样，第2个异常ArrayIndexOutOfBoundsException会被压制
                //throw new ArithmeticException("first exception");

                //抛出的都是nullPointerException异常对象，同一个异常对象不可能既被抛出又被压制，所以nullPointerException对象不会被压制
                //throw nullPointerException;

                //虽然都是抛出NullPointerException,但是不是相同的对象，所以new NullPointerException("second exception")会被压制
                throw new NullPointerException("first exception");
            }).onClose(()->{
                System.out.println("bbb");
                //throw new ArrayIndexOutOfBoundsException("second exception");
                //throw nullPointerException;
                throw new NullPointerException("second exception");
            }).forEach(System.out::println);
        }
    }
}
