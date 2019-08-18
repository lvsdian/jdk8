package cn.andios.jdk8;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/6/9:49
 */
public class Person {
    private String username;
    private int age;

    public Person() {
    }

    public Person(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
