package cn.andios.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/6/11:43
 */
public class PredicateTest2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        PredicateTest2 predicateTest = new PredicateTest2();

        //test方法判断参数是否符合条件，返回true或false
        predicateTest.conditionFilter(list,item->item % 2 ==0);

        //and方法：判断两个test方法的与
        predicateTest.conditionFilter2(list,item->item > 5,item->item % 2 == 0);

        //or方法：判断两个test方法的或
        predicateTest.conditionFilter3(list,item->item > 5,item->item % 2 == 0);

        //negate方法：将test取反
        predicateTest.conditionFilter4(list,item->item > 5);

        System.out.println(predicateTest.isEqual("test"));
        System.out.println(predicateTest.isEqual("test").test("test"));
    }

    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer : list) {
            if(predicate.test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void conditionFilter2(List<Integer> list, Predicate<Integer> predicate1,
                                 Predicate<Integer> predicate2){
        for (Integer integer : list) {
            if(predicate1.and(predicate2).test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void conditionFilter3(List<Integer> list, Predicate<Integer> predicate1,
                                 Predicate<Integer> predicate2){
        for (Integer integer : list) {
            if(predicate1.or(predicate2).test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void conditionFilter4(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer : list) {
            if(predicate.negate().test(integer)){
                System.out.println(integer);
            }
        }
    }

    public Predicate<String> isEqual(Object object){
        return Predicate.isEqual(object);
    }
}
