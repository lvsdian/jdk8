package cn.andios.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/20/17:41
 */
public class StreamTest9 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(500000);
        List<String> list2 = new ArrayList<>(500000);
        for (int i = 0; i < 500000; i++) {
            list1.add(UUID.randomUUID().toString());
            list2.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");

        long startTime1 = System.nanoTime();
        //串行排序s
        list1.stream().sorted().count();
        long endTime1 = System.nanoTime();

        long startTime2 = System.nanoTime();
        //并行排序
        list2.parallelStream().sorted().count();
        long endTime2 = System.nanoTime();

        long millis1 = TimeUnit.NANOSECONDS.toMillis(endTime1 - startTime1);
        long millis2 = TimeUnit.NANOSECONDS.toMillis(endTime2 - startTime2);
        System.out.println("串行流排序耗时："+ millis1);//串行流排序耗时：526
        System.out.println("并行流排序耗时："+ millis2);//并行流排序耗时：232
    }
}
