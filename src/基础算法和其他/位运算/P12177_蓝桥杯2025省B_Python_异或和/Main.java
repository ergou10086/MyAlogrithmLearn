package 基础算法和其他.位运算.P12177_蓝桥杯2025省B_Python_异或和;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger[] arr = new BigInteger[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextBigInteger();
        }
        BigInteger res = BigInteger.ZERO;

        // 只有两个数当前位不同才能产生贡献。
        for (int dig = 0; dig <= 20; dig++) {
            int[] cnt = {0, 0};   // 记录当前已处理过的数中，二进制位 k 为 0 或 1 的数的个数。
            BigInteger[] sum = {BigInteger.ZERO, BigInteger.ZERO};   // 下标差的动态和
            for (int i = 1; i <= n; i++) {
                // 提取arr[i]的二进制中第 dig 位的值
                int bit = arr[i].shiftRight(dig).and(BigInteger.ONE).intValue();
                // 取出相反位的值，这样的才可以产生贡献
                BigInteger multiplier = BigInteger.valueOf(1L << dig);
                res = res.add(sum[bit ^ 1].multiply(multiplier));
                cnt[bit]++;
                // 每次处理一个数时，所有之前记录的数的下标差会自然增加
                sum[0] = sum[0].add(BigInteger.valueOf(cnt[0]));
                sum[1] = sum[1].add(BigInteger.valueOf(cnt[1]));
            }
        }

        System.out.println(res);
    }
}