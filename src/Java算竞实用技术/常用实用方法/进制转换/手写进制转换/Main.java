package Java算竞实用技术.常用实用方法.进制转换.手写进制转换;

import scala.Char;

import java.util.HashMap;
import java.util.Map;

public class Main {
    // 字符到数字的映射,r 进制中的字符转换为对应的数字值
    private static final Map<Character, Integer> charToNum = new HashMap<>();
    // 数字到字符的映射,用于将数字转换为 r 进制中对应的字符
    private static final Map<Integer, Character> numToChar = new HashMap<>();

    static {
        for(int i = 0; i < 10; i++){
            // 将字符 '0' - '9' 映射到数字 0 - 9
            charToNum.put((char) ('0' + i), i);
            // 将数字 0 - 9 映射到字符 '0' - '9'
            numToChar.put(i, (char) ('0' + i));
        }
        for(int i = 0; i < 26; i++){
            // 将字符 'A' - 'Z' 映射到数字 10 - 35
            charToNum.put((char)('A' + i), i + 10);
            // 将数字 10 - 35 映射到字符 'A' - 'Z'
            numToChar.put(i + 10, (char) ('A' + i));
        }
        System.out.println(charToNum);
        System.out.println(numToChar);
    }

    // r 进制整数转 10 进制整数
    public static long rToDecimal(String num, int r) {
        long result = 0;
        // 用于记录当前位的权重
        int power = 0;

        // 从右向左遍历 r 进制字符串的每一位
        for (int i = num.length() - 1; i >= 0; i--) {
            char digit = num.charAt(i);
            int value = charToNum.get(digit); // 通过映射表将字符转换为对应的数字
            // 当前位乘权重，并累加
            result += value * Math.pow(r, power);
            power++;
        }
        return result;
    }

    // 10 进制整数转 r 进制整数的方法
    public static String decimalToR(long num, int r) {
        if (num == 0) {
            return "0"; // 如果输入的 10 进制数为 0，直接返回 "0"
        }

        StringBuilder result = new StringBuilder();
        boolean isNegative = num < 0; // 标记输入的 10 进制数是否为负数
        num = Math.abs(num);

        // 通过除 r 取余的方法，将 10 进制数转换为 r 进制数
        while (num > 0) {
            // 计算当前的余数
            int remainder = (int)(num % r);
            result.append(numToChar.get(remainder));
            num /= r;
        }
        if (isNegative) {
            result.append('-'); // 如果原数为负数，在结果字符串末尾添加负号
        }
        return result.reverse().toString(); // 反转结果字符串并返回
    }

    // r1 进制整数转 r2 进制整数的方法
    public static String r1ToR2(String num, int r1, int r2) {
        long decimal = rToDecimal(num, r1); // 先将 r1 进制数转换为 10 进制数
        return decimalToR(decimal, r2); // 再将 10 进制数转换为 r2 进制数
    }


    // r 进制整数转 10 进制浮点数的方法
    public static double rToDecimalFloat(String num, int r) {
        // 将 r 进制字符串按小数点分割为整数部分和小数部分
        String[] parts = num.split("\\.");
        // 调用 rToDecimal 方法将整数部分转换为 10 进制数
        long integerPart = rToDecimal(parts[0], r);
        // 用于存储小数部分转换后的 10 进制值
        double decimalPart = 0;
        if(parts.length > 1){
            String decimalStr = parts[1]; // 获取小数部分的字符串
            for(int i = 0; i < decimalStr.length(); i++){
                char digit = decimalStr.charAt(i);
                // 查表
                int value = charToNum.get(digit);
                // 计算当前位的值乘以对应的权重，并累加到小数部分结果中
                decimalPart += value * Math.pow(r, -(i + 1));
            }
        }
        return integerPart + decimalPart;
    }


    // 10 进制浮点数转 r 进制浮点数的方法
    // precision 是保留的小数位数
    public static String decimalFloatToR(double num, int r, int precision) {
        boolean isNegative = num < 0;
        num = Math.abs(num);
        long integerPart = (long) num;   // 整数部分
        double decimalPart = num - integerPart;   // 小数部分
        StringBuilder result = new StringBuilder(); // 用于存储转换后的 r 进制字符串
        if (isNegative) {
            result.append('-'); // 如果原数为负数，在结果字符串开头添加负号
        }

        result.append(decimalToR(integerPart, r)); // 将整数部分转换为 r 进制并添加到结果中
        result.append('.'); // 添加小数点

        if (precision > 0) { // 如果需要保留小数位数
            result.append('.'); // 添加小数点
            // 循环处理小数部分，保留指定的小数位数
            for (int i = 0; i < precision; i++) {
                decimalPart *= r; // 小数部分乘以 r
                int digit = (int) decimalPart; // 提取整数部分作为当前位的数字
                result.append(numToChar.get(digit)); // 将数字对应的字符添加到结果中
                decimalPart -= digit; // 更新小数部分
            }
        }
        return result.toString(); // 返回转换后的 r 进制字符串
    }



    public static void main(String[] args) {

    }
}
