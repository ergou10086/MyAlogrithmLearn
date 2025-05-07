package 基础算法和其他.排序.template.快速排序;

import java.util.Scanner;

// 主要利用分治思想，O(nlogn)
// 令指针i，j指向数列的区间外侧，数列的中值记为x
// 将数列中的<=x放在左端，>=x放在右段
// 对于左右两端，再递归以上的过程，直到每段只有一个数，即全部有序

public class SwiftSort {

    static int[] a;

    // swap方法
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    // 快速排序方法
    static void quicksort(int l, int r) {
        if(l == r) return;
        int i = l - 1, j = r + 1, midp = a[(l + r) / 2];
        while(i < j){
            do i++; while(a[i] < midp); //向右找>=midp的数
            do j--; while(a[j] > midp); //向左找<=midp的数
            if(i < j) swap(a[i], a[j]);
        }
        quicksort(l, j); // 对左侧进行递归排序
        quicksort(j + 1, r); // 对右侧进行递归排序
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 输入数组的大小
        a = new int[n]; // 创建数组
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt(); // 输入数组元素
        }
        quicksort(0, n - 1); // 调用快速排序
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " "); // 输出排序后的数组
        }
        scanner.close();
    }
}
