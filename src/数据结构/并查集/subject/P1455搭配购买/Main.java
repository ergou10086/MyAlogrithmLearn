package 数据结构.并查集.subject.P1455搭配购买;

import java.util.Scanner;

public class Main {
    static int[] p = new int[10086];    // 并查集用
    static int[] money = new int[10086];   // 合并后的钱
    static int[] val = new int[10086];   // 合并后的价值
    static int[] dp = new int[10086];   // 01背包用

    public static int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            p[fx] = fy;
            money[fy] += money[fx];  // 合并总钱数
            val[fy] += val[fx];      // 合并总价值
            money[fx] = 0;           // 清空已经合并的节点的值
            val[fx] = 0;             // 清空已经合并的节点的值
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 有 n 朵云，m 个搭配和你现有的钱 w
        int n = sc.nextInt();
        int m = sc.nextInt();
        int w = sc.nextInt();

        // 初始化并查集和读入云朵的信息
        for (int i = 1; i <= n; i++) {
            p[i] = i;
            money[i] = sc.nextInt(); // 价格
            val[i] = sc.nextInt();    // 价值
        }

        // 处理配对关系
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            union(x, y);
        }

        // 进行01背包动态规划
        for (int i = 1; i <= n; i++) {
            if (p[i] == i) { // 只有根节点有价格和价值时才处理
                for (int j = w; j >= money[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - money[i]] + val[i]);
                }
            }
        }

        // 输出最大价值
        System.out.println(dp[w]);
        sc.close();
    }
}
