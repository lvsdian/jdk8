package cn.andios.jdk8;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @description:
 * @author:LSD
 * @when:2019/8/10/11:20
 */
public class OptionalTest2 {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setName("张三");

        Employee employee2 = new Employee();
        employee1.setName("李四");

        Company company = new Company();
        company.setName("尚客优");

        List<Employee> employees = Arrays.asList(employee1,employee2);
        company.setEmployees(employees);

        List<Employee> list = company.getEmployees();

        Optional<Company> optional = Optional.ofNullable(company);
        System.out.println(optional.map(theCompany->theCompany.getEmployees()).orElse(Collections.emptyList()));




    }
}
