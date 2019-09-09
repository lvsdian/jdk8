package cn.andios.jdk8.stream.source;

/**
 * @description:
 * @author:LSD
 * @when:2019/9/7/11:58
 */
public class LambdaTest {
    //打印为cn.andios.jdk8.stream.source.LambdaTest@2ceb2de，是当前类
    Runnable r1 = ()-> System.out.println(this);

    //表示一个实现了Runnable接口的实例，是个匿名类
    //打印为cn.andios.jdk8.stream.source.LambdaTest$1@47e3e4b5
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();

        Thread t1 = new Thread(lambdaTest.r1);
        t1.start();

        System.out.println("-----------------");

        Thread t2 = new Thread(lambdaTest.r2);
        t2.start();
    }
}
