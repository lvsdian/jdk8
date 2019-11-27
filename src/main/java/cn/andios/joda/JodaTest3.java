package cn.andios.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author:LSD
 * @when:2019/9/9/18:04
 */
public class JodaTest3 {
    public static void main(String[] args) {
        //标准的UTC时间格式：2019-09-09T10:26:07.632Z  （Z表示东八区，也可以用+8:00来替换）
        //项目中，服务器端存储UTC时间，客户端拿到服务器端的UTC时间后再根据所在时区即可获得本地时间。
        //这样，就算有多个不同时区的客户端，时间也不会紊乱

        System.out.println(JodaTest3.convertUTC2Date("2019-09-09T10:26:07.632Z"));//Mon Sep 09 18:26:07 CST 2019
        System.out.println(JodaTest3.convertDate2UTC(new Date()));//2019-11-26T13:18:36.293Z
        System.out.println(JodaTest3.convertDate2LocalByDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));//2019-11-26 21:18:36

    }

    public static Date convertUTC2Date(String utcDate){
        try {
            DateTime dateTime = DateTime.parse(utcDate,DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertDate2UTC(Date javaDate){
        DateTime dateTime = new DateTime(javaDate,DateTimeZone.UTC);
        return dateTime.toString();
    }

    public static String convertDate2LocalByDateFormat(Date javaDate,String dateFormat){
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);

    }
}
