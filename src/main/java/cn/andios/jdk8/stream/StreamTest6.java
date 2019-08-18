package cn.andios.jdk8.stream;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/17/11:09
 */
public class StreamTest6 {
    public static void main(String[] args) {
        //empty创建空的stream对象
        Stream<String> stream1 = Stream.empty();
        System.out.println(stream1.count());

        //使用generate根据Supplier接口创建stream
        Stream<String> stream2 = Stream.generate(UUID.randomUUID()::toString);
        stream2.findFirst().ifPresent(System.out::print);

        //iterate会创建无限的串行流，如果这里如果不加limit，那么对初始值2会一直乘以2进行下去，加limit限制流的长度
        Stream.iterate(2,item->item * 2).limit(6).forEach(System.out::println);






        //小练习：
        Stream<Integer> stream3 = Stream.iterate(2, item -> item * 2).limit(20);
        Stream<Integer> stream4 = Stream.iterate(2, item -> item * 2).limit(20);
        //这里使用mapToInt避免了自动装箱拆箱的过程
        //mapToInt()返回值为IntStream类型，但map()返回值为Stream类型
        System.out.println(stream3.filter(item->item > 2).mapToInt(item->item *2).skip(2).limit(2).sum());

        //如果这里例子中，想同时调用min，max,sum等，可以用IntSummaryStatistics，如下：
        IntSummaryStatistics intSummaryStatistics = stream4.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();
        //jdk封装了max，average等等
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getSum());

    }
}
