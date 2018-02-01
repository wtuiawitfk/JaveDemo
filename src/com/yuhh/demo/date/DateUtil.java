package com.yuhh.demo.date;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //1.把日期类型Date-->转换成String类型
    public static String date2string(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        //判断pattern是否为空
        if ("".equals(pattern) || null == pattern) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    //重载方法
    public static String date2string(Date date) {
        return date2string(date,null);
    }

    //2.把String类型-->转换成Date类型
    public static Date string2date(String string, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        //判断pattern是否为空
        if ("".equals(pattern) || null == pattern) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        sdf.applyPattern(pattern);
        return sdf.parse(string);
    }

    public static Date string2date(String string) throws ParseException {
        return string2date(string, null);
    }

    @Test
    public void testDateUtil() throws ParseException {
        Date date = new Date();
        String pattern = "yyyy-MM-dd HH:mm:ss";

        String date2 = "2014-01-12 12:23:54";
        Date date1 = string2date(date2);

        String d = date2string(date);
        System.out.println(d);
        System.out.println(date1);
    }

}
