package cn.andios.joda;

import org.joda.primitives.list.IntList;
import org.joda.primitives.list.impl.ArrayIntList;

/**
 * @description:
 * @author:LSD
 * @when:2019/9/9/16:21
 */
public class JodaTest1 {
    public static void main(String[] args) {
        IntList intList = new ArrayIntList();

        //ArrayIntList中使用int型数组来存放元素，和ArrayList中不一样
        //避免了拆箱装箱的过程
        intList.add(1);
        intList.add(2);

        intList.forEach(System.out::println);
    }
}
