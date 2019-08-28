package cn.andios.jdk8.stream.source;

import org.omg.CORBA.portable.IDLEntity;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * @description:Set实现Collector接口
 * @author:LSD
 * @when:2019/8/28/17:08
 */
public class MyCollectorSetImpl<T> implements Collector<T,Set<T>,Set<T>>{

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked");
        //如果这里不使用Set接口，而使用实现类比如TreeSet，
        // 就可能与supplier返回的容器类型不符
        //或者使用lambad表达式：return (set,item)->set.add(item);
        return Set<T>::add;

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
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher invoked");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked");

        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH,UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welcome");
        Set<String> set = list.stream().collect(new MyCollectorSetImpl<>());
        System.out.println(set);
    }
}
