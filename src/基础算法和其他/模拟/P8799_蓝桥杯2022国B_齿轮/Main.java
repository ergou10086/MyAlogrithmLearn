package 基础算法和其他.模拟.P8799_蓝桥杯2022国B_齿轮;

// 齿轮之间的半径很明显决定了它们的速度关系，两个咬合在一起的齿轮，它们的速度之比与它们的半径之比成反比
// R1:R2=V2:V1
// 比例连乘  Rn：R1 = V1：Vn
// a1 - an中有没有一对数字的比值为qi
// 枚举aj，用 qi/aj得到另一个数

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int Q = sc.nextInt();
        int[] bucket = new int[200005];   // 桶存储，避免重复
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            bucket[x]++;
        }
        byte[] ans = new byte[200005];

        for(int i = 1; i <= 200000; i++){
            if (bucket[i] >= 2) ans[1] = 1;    // 自己整除自己
        }

        // 枚举ai，遍历aj
        for (int i = 2; i <= 200000; i++) {
            for (int j = 1; j <= 200000 / i; j++) {
                // 有这个aj，并且得到的qi也有
                if(bucket[j] != 0 && bucket[j * i] != 0){
                    ans[i] = 1;
                    break;
                }
            }
        }

        while (Q > 0) {
            int x = sc.nextInt();
            if (ans[x] == 1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            Q--;
        }
        sc.close();
    }
}

