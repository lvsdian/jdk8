package cn.andios.jdk8.stream.source;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * @description:
 *  自定义收集器实现，并且：
 *      实例输入：["hello","world","hello world"]
 *      实例输出：[{hello,hello},{world,world},{hello world,hello world}]
 * @author:LSD
 * @when:2019/8/28/19:43
 */
public class MyCollectorSetImpl2<T> implements Collector<T,Set<T>,Map<T,T>> {

    @Override
    public Supplier<Set<T>> supplier() {

        System.out.println("supplier invoked");
        //return HashSet<T>::new;

        /**
         *     A a1 = supplier.get();
         *     accumulator.accept(a1, t1);
         *     accumulator.accept(a1, t2);
         *     R r1 = finisher.apply(a1);  // result without splitting
         *
         *     A a2 = supplier.get();
         *     accumulator.accept(a2, t1);
         *     A a3 = supplier.get();
         *     accumulator.accept(a3, t2);
         *     R r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting
         */
        return ()->{
            System.out.println("----------");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked");
        return (set,item)->{
            System.out.println("accumulator："+ set + "\t" + Thread.currentThread().getName());
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked");
        return (left,right)->{
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked");

        return set ->  {
            Map<T,T> map = new HashMap<>();
            set.stream().forEach(item->map.put(item,item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked");
        //如果这里加上了IDENTITY_FINISH,那么中间类型强制转为最终结果类型，finisher方法就不会被调用，
        //见 java.util.stream.ReferencePipeline.collect(java.util.stream.Collector<? super P_OUT,A,R>)
        // 而中间类型为Set，最终类型为Map，不能强制转换，所以不能加上IDENTITY_FINISH

        //有CONCURRENT，多个线程操作一个结果容器，不加CONCURRENT，几个线程就操作几个结果容器
        //这里加上CONCURRENT后，只有一个中间结果容器，就不需要执行combiner,执行时，可能一个
        // 线程在往集合中添加元素，而在accumulator函数中，另一个线程在打印set，
        // 就会出现java.util.ConcurrentModificationException异常
        return Collections.unmodifiableSet(EnumSet.of(UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome","a","b","c","d");
        Set<String> set = new HashSet<>();
        set.addAll(list);

        for (int i = 0; i < 100; i++) {
            //set.stream().parallel()与set.parallelStream()等价，
            Map<String,String> map = set.parallelStream().collect(new MyCollectorSetImpl2<>());
            System.out.println(map);
        }

    }
}
