package cn.andios.jdk8.stream;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/14/22:19
 */
public class StreamTest4 {
    public static void main(String[] args) {

        Stream <String> stream1 = Stream.of("hello","world","hello world");

        //stream转为字符串数组
        //toArray函数参数为IntFunction这个函数接口，接口的int型参数(比如这里的length)为返回的数组的长度
        //String [] strArray = stream1.toArray(length->new String[length]);
        String [] strArray = stream1.toArray(String[]::new);

        //字符串数组转为list
        Arrays.asList(strArray).forEach(System.out::println);

        Stream <String> stream2 = Stream.of("hello","world","hello world");

        //stream直接转为list---collect方法：
        //1.直接调用Collectors.toList()方法。
        List<String> collect2 = stream2.collect(Collectors.toList());
        System.out.println("collect2："+collect2);

        Stream <String> stream3 = Stream.of("hello","world","hello world");
        //2.collect的重载
        //  第1个参数是Supplier，返回最终的结果容器；
        //  第2个参数是BiConsumer，遍历stream中的每个item,并添加(accumulator,累加)到容器中；
        //  第3个参数还是BiConsumer，将第2步的每个list都添加到最终的要返回的list中，最后返回这个list(即theList1)

        //ArrayList<String> collect2 = stream2.collect(() -> new ArrayList<>(), (theList, item) -> theList.add(item),(theList1, theList2) -> theList1.addAll(theList2));
        ArrayList<String> collect3 = stream3.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("collect3："+collect3);


        //stream直接转为list---collect方法的重载
        Stream <String> stream4 = Stream.of("hello","world","hello world");
        ArrayList<String> collect4 = stream4.collect(Collectors.toCollection(ArrayList::new));
        collect4.forEach(System.out::println);



        //stream转为set
        Stream <String> stream5 = Stream.of("hello","world","hello world");
        TreeSet<String> collect5 = stream5.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect5.getClass());
        collect5.forEach(System.out::println);

        //stream转为字符串
        Stream <String> stream6 = Stream.of("hello","world","hello world");
        String collect6 = stream6.collect(Collectors.joining());
        System.out.println(collect6);

    }
}
