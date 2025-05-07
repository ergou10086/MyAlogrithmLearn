package 基础算法和其他.二分.template.二分答案;

import java.util.Scanner;

public class Main {
    static int L, n, M;
    static int[] a = new int[500005];

    // 检查给定的距离 x 是否可以通过 M 次切割实现
    public static boolean check(int x) {
        int last = 0, cnt = 0;
        for (int i = 1; i <= n + 1; i++) {
            if (a[i] - a[last] < x) {
                cnt++;
            } else {
                last = i;
            }
        }
        return cnt <= M; // 判断切割次数是否在限制内
    }

    // 二分查找最大可切割距离
    public static int find() {
        int l = 0, r = (int) 1e9 + 1;
        while (l + 1 < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        n = sc.nextInt();
        M = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        a[n + 1] = L; // 添加右边界

        System.out.println(find());
        sc.close();
    }
}

