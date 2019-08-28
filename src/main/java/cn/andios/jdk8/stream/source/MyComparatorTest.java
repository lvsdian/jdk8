package cn.andios.jdk8.stream.source;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/27/9:55
 */
public class MyComparatorTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hi","welcome","hello","world");

        //用流的sort方法自然比较
        list.stream().sorted((item1,item2)->item1.compareTo(item2));

        //用流的sort方法比较长度
        list.stream().sorted((item1,item2)->item1.length()-item2.length());

        //Collections的sort方法，采用方法引用根据长度进行比较并反转
        Collections.sort(list,Comparator.comparingInt(String::length).reversed());

        //这里第1个参数是List<T>，第2个参数是一个Comparator<? super T>,即T或T的父类，在
        // java.util.List.sort方法中可以看到，这里根据Comparator<? super E>进行排序，
        // 但返回结果强制为E类型，即排序的标准可以是T或T的父类，但返回的结果必须是T类型。
        // 这样设计比较的范围更宽泛。
        Collections.sort(list,(item1,item2)->item1.length()-item2.length());

        Collections.sort(list,Comparator.comparingInt(item->item.length()));

        //这里reversed返回类型也为Comparator<T>，在调用reversed之前，comparingInt也返回一个Comparator<T>
        // reversed上下文感知比较远，不知道list里面的类型是什么，所以编译器作为Object来看待，需要显式指明String类型
        Collections.sort(list,Comparator.comparingInt((String item)->item.length()).reversed());

        //list的sort方法
        list.sort(Comparator.comparingInt(String::length).reversed());
        //与上面一样，这里也需要显式指明类型
        list.sort(Comparator.comparingInt((String item)->item.length()).reversed());


        //先比较长度，再比较ascii（双层比较）
        Collections.sort(list,Comparator.comparingInt(String::length).
                thenComparing(String.CASE_INSENSITIVE_ORDER));

        //comparingInt返回类型为Comparator<T>，这里T是String类型
        //所以thenComparing里面的item1，item2就是字符串
        Collections.sort(list,Comparator.comparingInt(String::length).
                thenComparing((item1,item2)->item1.compareToIgnoreCase(item2)));

        Collections.sort(list,Comparator.comparingInt(String::length).
                thenComparing(Comparator.comparing(String::toLowerCase)));

        Collections.sort(list,Comparator.comparingInt(String::length).
                thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));

        Collections.sort(list,Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));

        Collections.sort(list,Comparator.comparingInt(String::length).reversed()
                .thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder()))
                .thenComparing(Comparator.reverseOrder()));
    }
}
