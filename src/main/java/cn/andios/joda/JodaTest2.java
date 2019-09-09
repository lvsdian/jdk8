package cn.andios.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * @description:
 * @author:LSD
 * @when:2019/9/9/17:06
 */
public class JodaTest2 {
    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        System.out.println(today.toString("yyy-MM-dd"));//2019-09-09
        System.out.println(tomorrow.toString("yyy-MM-dd"));//2019-09-10

        System.out.println("------------------");

        //返回DateTime的副本，年月都不变，修改天数
        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString("yyyy-MM-dd"));//2019-09-01

        System.out.println("-------------------");

        //当前
        LocalDate localDate = new LocalDate();
        System.out.println("当前："+ localDate);

        //当前日期三个月之后的第一天
        localDate = localDate.plusMonths(3).dayOfMonth().withMinimumValue();
        System.out.println("当前日期三个月之后的第一天：" + localDate);

        //计算2年前今天的前三个月的最后一天的日期
        DateTime dateTime = new DateTime();
        DateTime dateTime1 = dateTime.minusYears(2).monthOfYear()
                .setCopy(3).dayOfMonth().withMaximumValue();
        System.out.println("计算2年前今天的前三个月的最后一天的日期"+dateTime1.toString("yyyy-MM-dd"));
    }
}
