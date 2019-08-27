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

        //这里第1个参数是list，第2个参数应该传一个Comparator<? super T>,
        // 这里直接传一个比较器，所以会自动推断类型为list里面的元素类型
        Collections.sort(list,(item1,item2)->item1.length()-item2.length());

        Collections.sort(list,Comparator.comparingInt(item->item.length()));

        //这里reversed返回类型也为Comparator<T>，但它里上下文的感知比较远，
        // 不知道list里面的类型是什么，所以编译器作为Object来看待，需要显式指明String类型
        Collections.sort(list,Comparator.comparingInt((String item)->item.length()).reversed());




    }
}
