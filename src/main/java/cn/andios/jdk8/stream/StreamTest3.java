package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/14/17:38
 */
public class StreamTest3 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        //将每个元素乘2再打印结果--流的map方法和reduce方法
        System.out.println(list.stream().map((i)-> i *=2).reduce(0,Integer::sum));
    }
}
