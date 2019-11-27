package cn.andios.jdk8;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/10/11:18
 */
public class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
