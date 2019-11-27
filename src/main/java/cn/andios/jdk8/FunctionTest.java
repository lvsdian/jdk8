package cn.andios.jdk8;

import java.util.function.Function;

/**
 * @description:function接口的apply方法
 * @author:LSD
 * @when:2019/8/5/16:42
 */
public class FunctionTest {
    public static void main(String[] args) {
        /**
         * 这里定义的方法的参数为Function接口，调用时传递的是行为，不是简单的值
         * 如果是普通的面向对象，函数的行为事先定义好，后面只是调用
         */
        FunctionTest functionTest = new FunctionTest();
        int val;
        String str;
        //Function的apply方法，将输入参数进行apply操作，具体怎么操作由用户调用方法时输入
        //statement
        val = functionTest.compute(1,value->{return  3 * value;});
        System.out.println(val);//3
        //expression
        val = functionTest.compute(1,value->2*value);
        System.out.println(val);//2

        str = functionTest.convert(1,value->String.valueOf(value+"hello world"));
        System.out.println(str);//1hello world


    }

    public int compute(int a, Function<Integer,Integer> function){
        return function.apply(a);
    }

    public String convert(int a,Function<Integer,String> function){
        return function.apply(a);
    }


}
