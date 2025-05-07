package 动态规划.template.背包.混合背包.混合背包之多重背包二进制拆分;

// 混合背包通常是几种背包混合的
// 例如，第一类物品只能用一次（01背包）
// 第二类物品可以用无限次（完全背包）
// 第三类物品最多只能用si次（多重背包）  二进制优化---多重背包转换为多个01背包
// a,b,c三个数组记录转化之后所有背包的体积，价值，类型，c[i]==0表示完全背包，c[i]==1表示01背包

import java.util.Scanner;

public class Main {
    static final int N = 1010;
    static final int M = 10000;

    // 所有背包的体积，价值，类型
    static int[] a = new int[M];
    static int[] b = new int[M];
    static int[] c = new int[M];  // c[i]==0表示完全背包，c[i]==1表示01背包
    static int[] f = new int[N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取物品数量 n 和背包容量 m
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int num = 1;
        for(int i = 1; i <= n; i++){
            // 读取物品的体积v，价值w，数量s
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            int s = scanner.nextInt();

            if (s == 0) { // 完全背包
                a[num] = v;
                b[num] = w;
                c[num++] = 0; // 背包类型
            }else{   // 多重背包二进制拆分
                if (s == -1) s = 1;   // 01背包，先让s=1，转换为多重背包
                int k = 1;
                while(s >= k){
                    a[num] = v * k;
                    b[num] = w * k;
                    c[num++] = 1;
                    s -= k;  // 注意拆分的时候减去k
                    k *= 2;
                }
                // 处理剩余
                if (s > 0) {
                    a[num] = s * v;
                    b[num] = s * w;
                    c[num++] = 1;
                }
            }
        }


        // 动态规划求解
        for (int i = 1; i < num; i++) {
            if (c[i] == 1) { // 01 背包，逆序枚举
                for (int j = m; j >= a[i]; j--) {
                    f[j] = Math.max(f[j], f[j - a[i]] + b[i]);
                }
            } else { // 完全背包。顺序枚举
                for (int j = a[i]; j <= m; j++) {
                    f[j] = Math.max(f[j], f[j - a[i]] + b[i]);
                }
            }
        }
        // 输出结果
        System.out.println(f[m]);
        scanner.close();
    }
}


