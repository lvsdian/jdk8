package cn.andios.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/10/16:50
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world");

        list.forEach(System.out::println);
    }
}
