package cn.andios.joda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

/**
 * @description:
 * @author:LSD
 * @when:2019/9/9/18:30
 */
public class Java8TimeTest {
    public static void main(String[] args) {
        //LocalDate关注日期
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);//2019-09-09
        System.out.println(localDate.getDayOfMonth() + "--"+ localDate.getMonth());

        System.out.println("------------------");

        LocalDate localDate1 = LocalDate.of(2019,9,10);
        System.out.println("localDate1：" + localDate1);

        System.out.println("------------------");

        //这里两个MonthDay的年份不同，但月份相同，所以会返回true
        LocalDate localDate2 = LocalDate.of(2010,12,23);
        MonthDay monthDay = MonthDay.of(localDate2.getMonth(),localDate2.getDayOfMonth());
        MonthDay monthDay1 = MonthDay.from(LocalDate.of(2021,11,23));
        if(monthDay.equals(monthDay1)){
            System.out.println("equals");
        }else{
            System.out.println("not equals");
        }

        System.out.println("--------------------");

        //LocalTime关注时分秒
        LocalTime time = LocalTime.now();
        System.out.println("当前时分秒："+ time);//18:50:10.336

        System.out.println("----------------------");

        LocalDate localDate3 = localDate.plus(2,ChronoUnit.WEEKS);
        System.out.println("当前日期加两周："+ localDate3);

        System.out.println("-----------------------");

        Clock clock = Clock.systemDefaultZone();
        System.out.println("当前时区："+ clock);
        System.out.println(clock.getZone().toString());
        System.out.println(clock.millis());

        System.out.println("----------------------");

        //
        LocalDate localDate4 = LocalDate.now();
        LocalDate localDate5 = LocalDate.of(2019,9,9);
        System.out.println(localDate4.isEqual(localDate5));
        System.out.println(localDate4.isAfter(localDate5));
        System.out.println(localDate4.isBefore(localDate5));

        System.out.println("------------------------");

        //时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        Set<String> treeSet = new TreeSet<String>(){
            {
                addAll(zoneIds);
            }
        };
        //treeSet.stream().forEach(System.out::println);

        System.out.println("-----------------");

        //带上时区的时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        System.out.println(zonedDateTime);

        System.out.println("---------------------");

        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);//2019-09
        System.out.println(yearMonth.lengthOfMonth());//30
        System.out.println(yearMonth.isLeapYear());//false

        System.out.println("-----------------------");

        YearMonth yearMonth1 = YearMonth.of(2017,9);
        System.out.println(yearMonth1);//2017-09
        System.out.println(yearMonth1.lengthOfMonth());//30
        System.out.println(yearMonth1.lengthOfYear());//365
        System.out.println(yearMonth1.isLeapYear());//false

        System.out.println("---------------------------");

        //计算两个日期的时间差
        LocalDate localDate6 = LocalDate.now();
        LocalDate localDate7 = LocalDate.of(2018,11,12);

        Period period = Period.between(localDate7,localDate6);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

        System.out.println("-------------------------------");

        System.out.println(Instant.now());//2019-09-09T11:37:55.724Z
    }
}
