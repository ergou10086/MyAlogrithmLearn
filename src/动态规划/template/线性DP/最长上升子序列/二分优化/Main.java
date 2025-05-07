package 动态规划.template.线性DP.最长上升子序列.二分优化;

import java.util.Scanner;

public class Main {
    private static final int N = 100010;
    private static int n, len;
    private static int[] a = new int[N], b = new int[N];

    private static int find(int x) {
        int l = -1, r = len;
        while (l + 1 < r) {
            int mid = l + r >> 1;
            if (b[mid] >= x) r = mid;
            else l = mid;
        }
        return r;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        b[0] = -999999998;     //哨兵
        for (int i = 0; i < n; i++) {
            if (b[len] < a[i]) b[++len] = a[i];    // 新数大于队尾数，则插入队尾
            else b[find(a[i])] = a[i];    // 替换第一个大于等于a[i]的数(贪心)
        }

        System.out.println(len);
        sc.close();
    }
}
