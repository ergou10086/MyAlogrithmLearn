package 动态规划.subject.线性dp.P10914_蓝桥杯2024国B_跳石头;

//package P10914_蓝桥杯_2024_国B_跳石头;

import java.util.BitSet;
import java.util.Scanner;

public class Main {
    static final int MAXN = 40001;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // bitset通过01位标识这个块石头能不能跳到
        BitSet[] bs = new BitSet[MAXN];
        int[] c = new int[MAXN];   // 权值

        int n = sc.nextInt();

        for (int i = 0; i < MAXN; i++) {
            bs[i] = new BitSet(MAXN);
        }

        // 读取输入并设置初始状态
        for (int i = 1; i <= n; i++) {
            c[i] = sc.nextInt();
        }

        int ans = 0;
        // 从后往前遍历每个位置
        for (int i = n; i >= 1; i--) {
            bs[i].set(c[i]);

            // 跳向第 j+cj​ 块石头,将 bs[i + c[i]] 的状态合并到 bs[i] 中
            if(i + c[i] <= n) {
                bs[i].or(bs[i + c[i]]);
            }

            // 跳向第 2j 块石头
            if(2 * i <= n) {
                bs[i].or(bs[2 * i]);
            }

            // 获取 bs[i] 中设置为 true 的位数
            ans = Math.max(ans, bs[i].cardinality());
        }

        System.out.println(ans);
        sc.close();
    }
}
