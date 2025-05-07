package 基础算法和其他.前缀和.template.普通前缀和;

import java.util.*;

public class Main {
    // sum[i] = sum[i-1] + a[i]
    // a[l] + ... + a[r] = sum[r] - sum[l - 1]
    // 前缀和的目的是快速求出一段区间[l,r]之和
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[100005];
        int[] s = new int[100005];

        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            s[i] = s[i - 1] + a[i];
        }
        int m = scanner.nextInt();
        for (int i = 1; i <= m; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            System.out.println(s[r] - s[l - 1]);
        }
        scanner.close();
    }

}
