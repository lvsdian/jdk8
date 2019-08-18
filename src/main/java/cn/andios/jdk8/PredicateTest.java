package cn.andios.jdk8;

import java.util.function.Predicate;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/6/11:36
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = p->p.length() > 5;
        System.out.println(predicate.test("hello"));

    }
}
