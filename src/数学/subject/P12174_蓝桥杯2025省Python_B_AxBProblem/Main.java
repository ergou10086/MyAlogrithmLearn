package 数学.subject.P12174_蓝桥杯2025省Python_B_AxBProblem;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int[] t = new int[l + 1];
        Arrays.fill(t, 0);

        // 枚举所有可能的 (a,b)组合，并统计每个乘积的出现次数，打表出四元组的前两部分乘积
        for (int a = 1; a <= l; a++) {
            for(int b = 1; a * b <= l; b++){
                t[a * b]++;
            }
        }

        // 前缀和
        int[] s = new int[l + 1];
        s[0] = 0;
        for (int i = 1; i <= l; i++) {
            s[i] = s[i - 1] + t[i];
        }

        long total = 0;
        // 对于每个 x
        for(int x = 1; x < l; x++){
            // 计算其对应的 y 的最大值 l - x
            int y = l - x;
            total += (long)t[x] * s[y];
        }

        System.out.println(total);
    }
}
