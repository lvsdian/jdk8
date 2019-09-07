package cn.andios.jdk8.stream.source;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @description:
 * @author:LSD
 * @when:2019/9/5/9:38
 */
public class ConsumerTest {

    public static void main(String[] args) {
        ConsumerTest consumerTest = new ConsumerTest();

        Consumer<Integer> consumer = i-> System.out.println(i);
        IntConsumer intConsumer = i-> System.out.println(i);

        System.out.println(consumer instanceof  Consumer);//true
        System.out.println(intConsumer instanceof  IntConsumer);//true

        //面向对象方式传递：传递的是对象
        consumerTest.test(consumer);//100
        //consumerTest.test(intConsumer);//编译错误
        //consumerTest.test((Consumer) intConsumer);//运行错误

        //函数式方式：传递的是行为
        consumerTest.test(consumer::accept);//100
        consumerTest.test(intConsumer::accept);//100
    }

    public void test(Consumer<Integer> consumer){
        consumer.accept(100);
    }
}
