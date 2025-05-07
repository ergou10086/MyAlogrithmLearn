package Java算竞实用技术.常用实用方法.字符串处理;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class char的各种 {
    public static void main(String[] args) throws IOException {
        // 单个字符处理（字母、数字、符号）
        // 高效字符串操作（避免 String 的不可变性开销）
        // 位运算优化（ASCII 特性）
        char c = 'A';
        // ASCII 值转换
        int ascii = (int)c;     // 65
        char fromAscii = 97;    // 'a'（自动转换）
        // Unicode 支持
        char chinese = '中';    // Unicode 编码

        // 包装类Character类的常用方法

        // isLetter(char ch)
        // 该方法用于判断指定字符是否为字母。
        char c1 = 'A';
        boolean isLetter = Character.isLetter(c);
        System.out.println(isLetter); // 输出: true

        // isDigit(char ch)
        // 该方法用于判断指定字符是否为数字
        char c2 = '5';
        boolean isDigit = Character.isDigit(c);
        System.out.println(isDigit); // 输出: true

        // 将一个字符转换为数字
        // getNumericValue(char c)、
        char ch3 = '5';
        int num = Character.getNumericValue(ch3); // 5
        System.out.println(num);

        // 将一个字符串转换为字符数组
        String str = "hello";
        char[] chars = str.toCharArray(); // {'h', 'e', 'l', 'l', 'o'}


        // isUpperCase(char ch) 和 isLowerCase(char ch)
        // isUpperCase 方法用于判断字符是否为大写字母，isLowerCase 方法用于判断字符是否为小写字母。
        char upper = 'A';
        char lower = 'a';
        boolean isUpper = Character.isUpperCase(upper);
        boolean isLower = Character.isLowerCase(lower);
        System.out.println(isUpper); // 输出: true
        System.out.println(isLower); // 输出: true


        // 实用技巧
        // 字符数组的读入
        // 方法1：读取字符串后转数组
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        // 方法2 直接读字符数组（无空格）
        char[] arr2 = new char[1000];
        int len = br.read(arr2, 0, 1000);  // len为实际读取长度

        // 方法3：逐字符读取（适用于特殊分隔符）
        int ch2;
        while ((ch2 = br.read()) != -1) {
            char ch = (char)c2;
            // 处理每个字符
        }

        // 利用字符的 ASCII 值做快速映射
        int[] count = new int[128]; // 快速统计字符出现次数
        String string = "ahiudshdahiadhiudaadshsddsadasfdadsfdsfdsafdsfds";
        // 遍历字符串中的每个字符
        for (int i = 0; i < string.length(); i++) {
            char cp = string.charAt(i);
            // 利用字符的 ASCII 值作为数组索引，对应位置计数加 1
            count[cp]++;
        }
        // 输出每个字符及其出现次数
        for (int i = 0; i < 128; i++) {
            if (count[i] > 0) {
                System.out.println("字符 '" + (char) i + "' 出现了 " + count[i] + " 次。");
            }
        }
    }
}
