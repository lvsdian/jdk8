package cn.andios.jdk8;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/6/16:33
 */
public class BinaryOperatorTest {
    public static void main(String[] args) {
        BinaryOperatorTest binaryOperatorTest = new BinaryOperatorTest();

        //传入两个Integer类型，返回的结果也是Integer类型
        System.out.println(binaryOperatorTest.operate(1,2,(value1,value2)->value1 + value2));

        System.out.println(binaryOperatorTest.getShort("hello","world",(a,b)-> a.length() - b.length()));;
        System.out.println(binaryOperatorTest.getShort("hello","world",(a,b)->a.charAt(0) - b.charAt(0)));
    }

    public Integer operate(Integer a,Integer b,BinaryOperator<Integer> binaryOperator){
        //因为BinaryOperator继承了BiFunction接口，所以有apply方法
        return binaryOperator.apply(a,b);
    }

    public String getShort(String a, String b, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(a,b);
    }
}
