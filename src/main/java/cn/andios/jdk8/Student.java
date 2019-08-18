package cn.andios.jdk8;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/6/16:10
 */
public class Student {
    private String name = "zhangsan";
    private Integer age = 20;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
