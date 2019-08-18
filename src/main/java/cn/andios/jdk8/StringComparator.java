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
        List<String> names = Arrays.asList("zhangsan","lisi","wangwu");

        //statement语句（需有 { }和;）
        Collections.sort(names,(String o1,String o2)->{
            return o1.compareTo(o2);
        });
        //expression表达式（没有{ }）
        Collections.sort(names,(o1,o2)->o1.compareTo(o2));
    }
}
