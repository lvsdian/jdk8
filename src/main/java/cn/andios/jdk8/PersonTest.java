package cn.andios.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/6/10:03
 */
public class PersonTest {
    public static void main(String[] args) {
        Person person1 = new Person("zhangsan",10);
        Person person2 = new Person("lisi",20);
        Person person3 = new Person("wangwu",30);

        List<Person> personList = Arrays.asList(person1,person2,person3);
        PersonTest personTest = new PersonTest();

        //通过stream来完成
        List<Person> personResult1 = personTest.getPersonsByUsername("zhangsan",personList);
        personResult1.forEach(System.out::println);//Person{username='zhangsan', age=10}

        //通过BiFunction调用stream来完成,BiFunction的具体操作在函数里已写死
        List<Person> personResult2 = personTest.getPersonsByAge1(10,personList);
        personResult2.forEach(System.out::println);//Person{username='lisi', age=20}

        //将BiFunction作为参数，执行apply的具体操作由用户调用时输入，更灵活
        List<Person> personResult3 = personTest.getPersonsByAge2(10,personList,(value1,value2)->
            value2.stream().filter(person -> person.getAge()<value1).collect(Collectors.toList())
        );
        personResult3.forEach(System.out::println);//Person{username='wangwu', age=30}
    }

    public List<Person> getPersonsByUsername(String username,List<Person> personList){
        //把personList变为一个流，流里的每个person对象需满足用户名与username相同，再转为一个list进行返回
        return personList.stream().filter(person -> person.getUsername().equals(username))
                .collect(Collectors.toList());
    }
    public List<Person> getPersonsByAge1(int age,List<Person> personList){
        //定义一个BiFunction(因为是两个输入，age和personList，符合age条件的personList,所以用BiFunction)
        //BiFunction的操作在这里已经确定，用户调用时传入参数值即可
        BiFunction<Integer,List<Person>,List<Person>> biFunction = (value1,value2)->
                 value2.stream().filter(person -> person.getAge() > value1).collect(Collectors.toList());
         return biFunction.apply(age,personList);
    }

    public List<Person> getPersonsByAge2(int age,List<Person> personList,BiFunction<Integer,List<Person>,List<Person>> biFunction){
        return  biFunction.apply(age,personList);
    }
}