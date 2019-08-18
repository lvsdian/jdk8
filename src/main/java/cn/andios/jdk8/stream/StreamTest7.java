package cn.andios.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/17/18:51
 */
public class StreamTest7 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world");

        //list.stream().map(item->item.substring(0,1).toUpperCase() + item.substring(1)).forEach(System.out::println);

        //这里的forEach是终止操作，map里面是中间操作
        //流是惰性加载，就是说，没有遇到终止操作，中间操作就不会被执行
        //这里如果没有调用forEach，
        //那么什么都不会被打印
        list.stream().map(
                item->{
                    String result =  item.substring(0,1).toUpperCase() + item.substring(1);
                    System.out.println("test");
                    return result;
                }
        ).forEach(System.out::println);
    }
}
