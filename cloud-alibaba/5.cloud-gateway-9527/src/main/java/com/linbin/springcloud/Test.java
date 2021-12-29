package com.linbin.springcloud;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/12/24 10:08
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        //获取默认时区时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        //使用指定的年月日、时分秒、纳秒以及时区ID来新建对象
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");//参数是时区如中国上海时区
        ZonedDateTime dateTime2 = ZonedDateTime.of(2015, 11, 30, 23, 45, 59, 0, zoneId);
        System.out.println(dateTime2);
    }
}
