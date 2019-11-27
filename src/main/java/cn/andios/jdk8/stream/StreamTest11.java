package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/21/11:18
 */
public class StreamTest11 {
    public static void main(String[] args) {
        //将集合元素去重打印
        List<String> list1 = Arrays.asList("hello welcome","world hello","hello world hello","hello welcome");
        List<String> list2 = Arrays.asList("hello welcome","world hello","hello world hello","hello welcome");

        //list1.stream().flatMap(item->item.split(" ")).distinct().forEach(System.out::println);
        //Array转Stream
        //                 map：空格分割成数组         flatMap：Stream<String []>打平成Stream<String>
        list2.stream().map(item->item.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);//hello world welcome

    }
}
