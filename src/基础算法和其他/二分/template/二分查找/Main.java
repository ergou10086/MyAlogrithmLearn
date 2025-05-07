package 基础算法和其他.二分.template.二分查找;

import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] a = new int[1000005];

    static int find(int q){
        int l = 0, r = n + 1;  // 闭区间
        while (l + 1 < r){   // // l = r 时结束
            int mid = (l + r) >> 1;
            if (a[mid] >= q) {
                r = mid; // 左区间符合
            }else{
                l = mid; // 右区间符合
            }
        }
        return a[r] == q ? r : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }

        for (int i = 1; i <= m; i++) {
            int q = scanner.nextInt();
            System.out.print(find(q) + " ");
        }

        scanner.close();
    }
}
