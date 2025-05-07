package 数学.subject.P11041_蓝桥杯2024省JavaB_报数游戏;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // 前面都是20 和 24的交替
        // 20 和 24的最小公倍数是 120
        // 202420242024 / 10 = 20242024202 余 4
        // 但是这样输出不了System.out.print(20242024202 * 120 + 48);
        BigInteger bigInteger = new BigInteger("20242024202");
        BigInteger multiplier = new BigInteger("120");
        BigInteger addplier = new BigInteger("48");
        BigInteger temp = bigInteger.multiply(multiplier);
        BigInteger temp2 = temp.add(addplier);
        System.out.print(temp2);
    }
}

