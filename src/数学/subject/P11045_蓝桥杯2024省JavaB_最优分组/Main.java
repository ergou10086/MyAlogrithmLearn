package 数学.subject.P11045_蓝桥杯2024省JavaB_最优分组;

import java.util.Scanner;
import java.awt.image.RescaleOp;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        // 设总共有 N 个个体，分成了 g=N / K 组，每一组有 K 个个体，设总共消耗了 X 份检测试剂。
        // g = 1 和 g = N 相当于不分组
        Scanner sc = new Scanner(System.in);
        double n = sc.nextDouble();
        double p = sc.nextDouble();  // 被感染的概率为 p

        // 从 g≥2 开始，首先需要消耗 g 支试剂为每一组进行一次检测。
        // 在每一组当中： (1 - p)^k 的概率全部阴性，出现阳性就是 1 - (1 - p)^k
        // 设在此期间有 Y 组检测结果为阳性，那么满足二项分布 Y ~ B（g, 1 - (1 - p)^k)
        // 如果出现了阳性，那么还需要消耗K个试剂测
        // E(X) = (N / K * (1 - (1 - p)^k)) * K + N / K

        // 枚举组数
        double current = n;   // 需要试剂
        int res = 1;
        for(int k = 2; k <= n; k++) {
            // 正好分组
            if(n % k == 0) {
                double need = n / k + n * (1 - Math.pow(1 - p, k));  // 期望试剂
                if(current > need) {
                    current = need;
                    res = k;
                }
            }
        }
        System.out.print(res);
        sc.close();
    }
}
