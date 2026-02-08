package 动态规划.subject.线性dp.P1412_经营与开发;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        double k = sc.nextDouble();
        double c = sc.nextDouble();
        double w = sc.nextDouble();

        // 存储星球信息，因为需要逆推
        int[] type = new int[n + 1];
        int[] val = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            type[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }

        // f[i] 表示从第 i 个星球开始，初始能力为 1 时的最大收益
        // 实际上只需要一个变量滚动更新即可
        double f_next = 0;

        double k_mul = 1 - 0.01 * k;
        double c_mul = 1 + 0.01 * c;

        for (int i = n; i >= 1; i--) {
            if(type[i] == 1){
                // 资源型：max（开采，不开采）
                f_next = Math.max(f_next, val[i] + f_next * k_mul);
            }else{
                // 维修型：max(维修, 不维修)
                f_next = Math.max(f_next, -val[i] + f_next * c_mul);
            }
        }

        // 最终结果乘以初始能力 w
        System.out.printf("%.2f\n", f_next * w);
    }
}
