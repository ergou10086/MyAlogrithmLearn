package 基础算法和其他.日期.P10983_蓝桥杯2023国_Python_A_跑步计划;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

//    从2023年1月1日开始，遍历整年的每一天
//    对于每一天，检查日期或星期是否包含数字1
//    如果包含则跑5公里，否则跑1公里
//    累加全年跑步总公里数

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        // 日期set
        Set<LocalDate> dates = new HashSet<>();

        // 遍历2023年的每一天
        LocalDate date = LocalDate.of(2023, 1, 1);
        while(date.getYear() == 2023){
            // 检查日期中是否包含数字1
            if(check(date)){
                sum += 5;
            }else{
                sum += 1;
            }
            date = date.plusDays(1);
        }
        System.out.println(sum);
    }

    private static boolean check(LocalDate date){
        // 格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = date.format(formatter);

        // 检查年月日
        if(dateStr.contains("1")){
            return true;
        }

        // 检查星期
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.MONDAY){
            return true;
        }

        return false;
    }
}
