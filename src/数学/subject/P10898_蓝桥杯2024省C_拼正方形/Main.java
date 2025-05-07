package 数学.subject.P10898_蓝桥杯2024省C_拼正方形;

import java.math.BigInteger;

public class Main {
    // 尽量多拼大的，那就是要多用 2 x 2的
    // 10470245个1x1 相当于 10470244 / 4 = 261811 个 2x2
    // 7385137888721 + 261811 = 7385138150532
    public static void main(String[] args) {
        BigInteger bi1 = new BigInteger("7385137888721");
        BigInteger bi2 = new BigInteger("261811");
        bi1 = bi1.add(bi2);
        // System.out.println(bi1);  // 7385138150532

        // 7385138150532 的 开方并取整 大约是 2717553
        double sqrt = Math.sqrt(bi1.doubleValue());
        long sqrtFloor = (long) Math.floor(sqrt);
        System.out.println(sqrtFloor * 2);


    }
}
