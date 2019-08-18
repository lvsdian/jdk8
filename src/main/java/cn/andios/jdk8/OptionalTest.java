package cn.andios.jdk8;

import java.util.Optional;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/10/11:07
 */
public class OptionalTest {
    public static void main(String[] args) {

        Optional<String> optional = Optional.of("hello");
        Optional<String> optional2 = Optional.ofNullable("hello");
        //打印hello（这种写法不推荐）
        //if(optional.isPresent()){
        //    System.out.println(optional.get());
        //}

        //打印hello
        optional.ifPresent(item-> System.out.println(item));
        //打印hello
        optional.ifPresent(System.out::println);

        System.out.println(optional.orElse("world"));

        System.out.println(optional.orElseGet(()->"heihei"));
    }
}
