package 基础算法和其他.模拟.P8799_蓝桥杯2022国B_齿轮;

import java.util.BitSet;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int Q = sc.nextInt();

        int maxR = 0;
        int[] radii = new int[n];
        for (int i = 0; i < n; i++) {
            radii[i] = sc.nextInt();
            if (radii[i] > maxR) {
                maxR = radii[i];
            }
        }

        BitSet bucket = new BitSet(maxR + 1);
        for (int r : radii) {
            bucket.set(r);
        }

        BitSet ans = new BitSet(maxR + 1);

        // 检查是否有重复元素（q=1的情况）
        if (radii.length != bucket.cardinality()) {
            ans.set(1);
        }

        // 预处理所有可能的比值
        for (int i = 0; i < n; i++) {
            int r1 = radii[i];
            for (int j = i + 1; j < n; j++) {
                int r2 = radii[j];
                if (r1 > r2) {
                    if (r1 % r2 == 0) {
                        ans.set(r1 / r2);
                    }
                } else if (r2 % r1 == 0) {
                    ans.set(r2 / r1);
                }
            }
        }

        // 处理查询
        while (Q-- > 0) {
            int x = sc.nextInt();
            System.out.println(x <= maxR && ans.get(x) ? "YES" : "NO");
        }
        sc.close();
    }
}
