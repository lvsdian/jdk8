package cn.andios.jdk8.stream.source;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/30/16:31
 */
public class AutoCloseableTest implements AutoCloseable {

    public void doSomething(){
        System.out.println("do something ...");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close invoked");
    }

    public static void main(String[] args) throws Exception {
        //在try()中声明对象，资源会自动关闭
        try (AutoCloseableTest autoCloseableTest = new AutoCloseableTest()){
            autoCloseableTest.doSomething();
        }
    }
}
