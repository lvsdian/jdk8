package cn.andios.jdk8;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/5/9:12
 */
@FunctionalInterface
interface MyInterface{
    void test();

    @Override
    String toString();
}
public class Test2 {

    public void myTest(MyInterface myInterface){
        System.out.println(1);
        myInterface.test();
        System.out.println(2);
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();

        test2.myTest(new MyInterface() {
            @Override
            public void test() {
                System.out.println("myTest1");
            }
        });//1 myTest1 2

        System.out.println("------------------------");
        test2.myTest(()->{
            System.out.println("myTest2");
        });//1 myTest2 2

        System.out.println("------------------------");
        //5.lambda表达式是对象，必须依附于一类特别的对象类型--函数式接口
        MyInterface myInterface = () -> {
            System.out.println("myTest3");
        };

        System.out.println("------------------------");
        System.out.println(MyInterface.class.getInterfaces()[0]);//java.lang.ArrayIndexOutOfBoundsException: 0
        System.out.println(myInterface.getClass().getInterfaces()[0]);//1
    }
}
