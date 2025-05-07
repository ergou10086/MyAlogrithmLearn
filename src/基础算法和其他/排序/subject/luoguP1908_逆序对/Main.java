package 基础算法和其他.排序.subject.luoguP1908_逆序对;

// 题目中逆序对就是排在后面的比排在前面的小
// 每当从右段取数时候，就统计逆序对的数目，因为归并排序是保序的
// 每当从右段取数时候，就说明存在一个逆序对，因为是排在前面的数小，所以说右段先拿

import java.util.Scanner;

public class Main {
    static int n;
    static int[] a;
    static int[] b;
    static long res;

    public static void mergeSort(int l, int r) {
        if (l >= r) return;

        int mid = (l + r) / 2;

        // 拆分
        mergeSort(l, mid);
        mergeSort(mid + 1, r);

        // 合并
        int i = l;
        int j = mid + 1;
        int k = l;
        while (i <= mid && j <= r) {
            if (a[i] <= a[j]) {
                b[k++] = a[i++];
            } else {
                b[k++] = a[j++];
                res += mid - i + 1;
            }
        }

        // 处理剩余元素
        while (i <= mid) {
            b[k++] = a[i++];
        }
        while (j <= r) {
            b[k++] = a[j++];
        }

        // 拷贝回原数组
        for (int index = l; index <= r; index++) {
            a[index] = b[index];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        a = new int[n];
        b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        mergeSort(0, n - 1);

        System.out.println(res);

        scanner.close();
    }
}
