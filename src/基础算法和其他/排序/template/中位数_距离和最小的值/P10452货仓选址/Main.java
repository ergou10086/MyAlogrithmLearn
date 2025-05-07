package 基础算法和其他.排序.template.中位数_距离和最小的值.P10452货仓选址;

// 一个数轴，n点坐标，求一个点对于所有点距离之和最小
// 使用中位数排序思想
// 这个点的位置应该建立在中位数处,n为奇数，建在a[n/2]处，n为偶数，建在a[(n-1)/2]-a[n/2]之间

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int N = 100100;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = scanner.nextInt();
        }
        Arrays.sort(arr);
        int ans = 0;
        for(int i=0; i<n; i++) ans += Math.abs(arr[i]  -arr[n/2]);
        System.out.println(ans);
        scanner.close();
    }
}
