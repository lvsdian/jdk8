package cn.andios.jdk8;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @description:Function接口的compute、andThen方法,BiFunction接口
 * @author:LSD
 * @when:2019/8/5/20:26
 */
public class FunctionTest2 {
    public static void main(String[] args) {
        FunctionTest2 functionTest2 = new FunctionTest2();
        //Function的compose方法会将输入参数先执行function2,将执行function2的结果作为function1的输入参数执行function1
        //Function的andThen方法会将输入参数先执行function1,将执行function1的结果作为function2的输入参数执行function2
        //2*2=4，4*3=12
        System.out.println(functionTest2.compute1(2,value->value*3,value->value * value));//12
        //2*3=6，6*6=36
        System.out.println(functionTest2.compute2(2,value->value*3,value->value * value));//36

        //BiFunction的apply方法接收两个参数，返回一个结果
        System.out.println(functionTest2.compute3(1,2,(value1,value2)->value1 + value2));//3

        //BiFunction的andThen方法，给两个参数，先执行biFunction的apply,得到一个结果，再将结果执行function的apply
        //即给两个参数，执行biFunction后只能得到一个结果值，所以biFunction没有compose方法，如果有compose方法，就要执行
        //两次biFunction(参照Function的compose方法),但只有一个参数，所以没有compose方法。
        System.out.println(functionTest2.compute4(1,2,(value1,value2)->value1 + value2,value->value * value));//9
    }
    public int compute1(int a, Function<Integer,Integer> function1,Function<Integer,Integer> function2){
        return function1.compose(function2).apply(a);
    }

    public int compute2(int a, Function<Integer,Integer> function1,Function<Integer,Integer> function2){
        return function1.andThen(function2).apply(a);
    }

    public int compute3(int a, int b, BiFunction<Integer,Integer,Integer> biFunction){
        return biFunction.apply(a,b);
    }

    public int compute4(int a, int b, BiFunction<Integer,Integer,Integer> biFunction,
                        Function<Integer,Integer> function){
        return biFunction.andThen(function).apply(a,b);
    }
}
