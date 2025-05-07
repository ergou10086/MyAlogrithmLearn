package 基础算法和其他.贪心.subject.P12176_蓝桥杯2025省PythonB_书架还原;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];   // 书架
        int[] p = new int[n + 1];   // p[x]表示书本编号现在所处的位置
        int res = 0;
        for(int i = 1; i <= n; i++){
            a[i] = sc.nextInt();
            p[a[i]] = i;    // 桶记录书本a[i]的正确位置，p[a[i]] 是书本应该去的位置
        }

        // 从第一个位置开始检查，如果当前数字不在正确位置，就找到它应该去的位置，把这两个位置的数字交换。
        for(int i = 1; i <= n; i++){
            if(a[i] != i){   // 不在正确位置，需要调整位置
                p[a[i]] = p[i];   // 把书本放到应该去的位置
                ++res;
                // 把正确位置p[i]上的书本和当前书本位置交换
                int temp = a[p[i]];
                a[p[i]] = a[i];
                a[i] = temp;
            }
        }

        System.out.println(res);
    }
}
