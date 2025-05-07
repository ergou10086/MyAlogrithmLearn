package 基础算法和其他.排序.template.归并排序;

import java.util.Scanner;

public class LuoguP1177 {

    static final int N = 100010;
    static int[] a = new int[N]; // 原始数组
    static int[] b = new int[N]; // 辅助数组

    // 归并排序方法
    static void mergesort(int l, int r) {
        if (l == r) return; // 递归结束条件
        int m = (l + r) >> 1;

        mergesort(l, m);         // 递归拆分左半边
        mergesort(m + 1, r);  // 递归拆分右半边

        // 合并
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (a[i] <= a[j]) {
                b[k++] = a[i++]; // 将较小的元素放入辅助数组
            }else{
                b[k++] = a[j++];
            }
        }
        while (i <= m) b[k++] = a[i++];    // 处理剩余左半边元素
        while (j <= r) b[k++] = b[j++];    // 处理剩余右半边元素

        // 将合并后的结果复制回原数组
        for (i = l; i <= r; i++)  a[i] = b[i];

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)  a[i] = sc.nextInt(); // 输入数组元素

        mergesort(0, n - 1);

        for (int i = 0; i < n; i++) System.out.print(a[i] + " ");

        sc.close();
    }
}
