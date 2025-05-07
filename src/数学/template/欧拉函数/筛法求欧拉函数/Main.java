package 数学.template.欧拉函数.筛法求欧拉函数;

// 筛法求欧拉函数
// 若i是质数，phi[i] = i - 1
// 在线性筛中，每个合数m都是被最小的质因子筛掉的
// 设pj是m的最小质因子，则m通过m = pj * i筛掉
//  若i能被pj整除，则i包含了m的所有质因子，φ(m) = p[j] * φ(i)
//  若i不能被pj整除，则i和pj是互质的，φ(m) = (p[j] - 1) * φ(i)  （φ(j) = (pj - 1)，如果pj是质数）

import java.util.Scanner;

public class Main {
    static final int N = 1000010; // 定义常量 N
    static int[] p = new int[N];  // 存储质数
    static int[] vis = new int[N]; // 标记是否为质数
    static int[] phi = new int[N]; // 存储欧拉函数值
    static int cnt = 0; // 质数计数器

    // 筛法求欧拉函数
    static void getPhi(int n) {
        phi[1] = 1; // φ(1) = 1
        for (int i = 2; i <= n; i++) {
            if (vis[i] == 0) { // 如果 i 是质数
                p[cnt++] = i; // 将 i 加入质数数组
                phi[i] = i - 1; // φ(i) = i - 1
            }
            // 枚举每个已经记录的质数j，这里参考欧拉筛标记合数
            for (int j = 0; j < cnt && i * p[j] <= n; j++) {
                int m = i * p[j];
                vis[m] = 1; // 标记 m 为合数
                if (i % p[j] == 0) { // 如果 p[j] 是 i 的质因数
                    phi[m] = p[j] * phi[i]; // φ(m) = p[j] * φ(i)
                    break;
                } else {
                    phi[m] = (p[j] - 1) * phi[i]; // φ(m) = (p[j] - 1) * φ(i)
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 输入 n
        getPhi(n); // 计算欧拉函数
        for (int i = 1; i <= n; i++) {
            System.out.println(phi[i]); // 输出 φ(i)
        }
    }
}