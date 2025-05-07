package 基础算法和其他.模拟.LanQiao_19700召唤数学精灵;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

// 先找15个看看

public class Main {
    public static void main(String[] args) {
        /*
        List<BigInteger> list = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        BigInteger product = BigInteger.ONE;

        for (BigInteger i = BigInteger.ONE; list.size() < 15; i = i.add(BigInteger.ONE)) {
            sum = sum.add(i);
            product = product.multiply(i);

            BigInteger diff = sum.subtract(product);
            if (diff.mod(BigInteger.valueOf(100)).equals(BigInteger.ZERO)) {
                list.add(i);
                System.out.println("Found: " + i);
            }
        }

        System.out.println("\nFirst 15 numbers:");
        for (BigInteger num : list) {
            System.out.println(num);
        }
         */
        BigInteger a = new BigInteger("2024041331404202");
        BigInteger b = a.divide(new BigInteger("50"));

        // 40480826628084 + 2(1,3)

        System.out.println(b.add(new BigInteger("2")));
    }
}

// 24,175,199,200,224,375,399,400,424,575,599,600,624
// 每200出现四个