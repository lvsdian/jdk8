package cn.andios.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/**
 * @description:字符串比较
 * @author:LSD
 * @when:2019/8/5/16:10
 */
public class StringComparator {
    public static void main(String[] args) {
        List<String> names1 = Arrays.asList("zhangsan","lisi","wangwu");
        List<String> names2 = Arrays.asList("zhangsan","lisi","wangwu");

        //statement语句（需有 { }和;）
        Collections.sort(names1,(String o1,String o2)->{
            return o1.compareTo(o2);
        });

        names1.forEach(System.out::println);//lisi wangwu zhangsan

        //expression表达式（没有{ }）
        Collections.sort(names2,(o1,o2)->o1.compareTo(o2));
        names2.forEach(System.out::println);////lisi wangwu zhangsan
    }
}
