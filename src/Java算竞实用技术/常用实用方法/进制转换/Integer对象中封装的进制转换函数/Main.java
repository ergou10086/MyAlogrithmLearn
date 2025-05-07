package Java算竞实用技术.常用实用方法.进制转换.Integer对象中封装的进制转换函数;

/*
10进制转2进制	Integer.toBinaryString(n);	一个二进制字符串.
10进制转8进制	Integer.toOctalString(n);	一个八进制字符串
10进制转16进制	Integer.toHexString(n);	一个16进制字符串
10进制转 r 进制	Integer.toString(100, 16);	一个r进制字符串
 */

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        int n = 18;
        // 十进制转换为r进制  Integer.to进制英文String(n);
        // 转二进制
        String binary_num = Integer.toBinaryString(n);
        System.out.println(binary_num);
        // 八进制
        String Octal_num = Integer.toOctalString(n);
        System.out.println(Octal_num);
        // 十六进制
        String Hexadecimal_num = Integer.toHexString(n);
        System.out.println(Hexadecimal_num);
        // r进制toString(原10进制数据, r进制)
        String Quaternary_num = Integer.toString(n, 4);
        System.out.println(Quaternary_num);

        // 使用Integer.toString()方法也可以实现
        String tb = Integer.toString(-900, 2); // 10进制转2进制
        String to = Integer.toString(10465, 8);    // 10进制转8进制
        String th = Integer.toString(1194684, 16);// 10进制转16进制
        System.out.println(tb);
        System.out.println(to);
        System.out.println(th);


        // r进制转换为10进制
        String s2 = "-10101010111";   // 转换成负数只需要在字符串前加上负号-
        String s8 = "365417";
        // Integer.parseInt(原r进制数据字符串, r进制);
        int b = Integer.parseInt("1001", 2);    // 2进制转10进制
        int o = Integer.parseInt("12", 8);    // 8进制转10进制
        int h = Integer.parseInt("123ABC", 16);    // 16进制转10进制
        System.out.println(b + " " + o + " " + h);

        int n210 = Integer.parseInt(s2, 2);
        System.out.println(n210);
        int n810 = Integer.parseInt(s8, 8);
        System.out.println(n810);

        // Integer.valueOf()方法也可以实现
        Integer b2 = Integer.valueOf("-1001", 2); // 2进制转10进制
        Integer o2 = Integer.valueOf("12", 8);    // 8进制转10进制
        Integer h2 = Integer.valueOf("123ABC", 16);    // 16进制转10进制
        System.out.println(b2 + " " + o2 + " " + h2);

        // 数字补前置0
        int a = 3;
        System.out.printf("%04d\n", a);

        String str= String.format("%04d",a);
        System.out.println(str);


        // 基于大数的进制转换，在new BigInteger对象时候就可以带r进制的形参
        BigInteger b3 = new BigInteger("1001", 2); // 2进制转10进制
        BigInteger o3 = new BigInteger("12", 8);    // 8进制转10进制
        BigInteger h3 = new BigInteger("123ABC", 16);// 16进制转10进制
        System.out.println(b3 + " " + o3 + " " + h3);
        String tb2 = b3.toString(2);    // 10进制转2进制
        String to2 = o3.toString(8);    // 10进制转8进制
        String th2 = h3.toString(16);    // 10进制转16进制
        System.out.println(tb2);
        System.out.println(to2);
        System.out.println(th2);

    }
}
