package Java算竞实用技术.常用实用方法.大数处理.大整数BigInteger;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // BigInteger，代表大整数类用于对大整数进行操作
        // BigDecimal，代表大浮点型
        // 这两种类的使用方法是一样

        // 新建
        BigInteger a = new BigInteger("1275822782545723");  //第一种，参数是字符串
        BigInteger a2 = BigInteger.valueOf(1725425523);    //第二种，参数可以是int、long

        // 大整数的四则运算
        a.add(new BigInteger("154616845136485"));    // 加
        System.out.println(a);
        a.subtract(new BigInteger("1545535336485"));   // 减
        System.out.println(a);
        a.multiply(a2);     // 乘
        System.out.println(a);
        a.mod(new BigInteger("47546168455"));    // 除
        System.out.println(a);

        // 大整数求模
        BigInteger divisor = new BigInteger("889668455");
        BigInteger resultMod = a.mod(divisor);
        System.out.println("求模结果: " + resultMod);

        // 大整数比较大小
        int compareResult = a.compareTo(a2);
        if (compareResult > 0) {
            System.out.println("a 大于 a2");
        } else if (compareResult < 0) {
            System.out.println("a 小于 a2");
        } else {
            System.out.println("a 等于 a2");
        }

        // 常用方法
        // 素数判断
        boolean isPrime = a.isProbablePrime(10);
        System.out.println("a 是否为素数: " + isPrime);

        // 求最大公约数
        BigInteger ad = a.gcd(divisor);
        System.out.println(ad);

        // 求大整数的位数
        //先转换成字符串再求字符串的长度
        int a_len = a.toString().length();
        System.out.println(a_len);

        // 进制转换
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
        // 转换为二进制数组
        BigInteger sd = new BigInteger("98645312648513");
        byte[] b2 = sd.toByteArray();



    }
}
