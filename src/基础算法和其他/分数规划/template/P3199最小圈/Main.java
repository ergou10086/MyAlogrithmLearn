package 基础算法和其他.分数规划.template.P3199最小圈;

// 在图中找一个环，使得环上边权之和除以节点个数最小，求这个最小平均值
// 每条边边权wi，求一个环使得 边权和/边条数 最小
// 二分一个答案x，把wi-x作为边权，那么最小环就是最小值，判断最小值是否小于等于0

import java.util.*;

public class Main{
    static final int N = 3005;
    static final int M = 10005;
    static int n, m;
    static int[] h = new int[N];
    static int[] to = new int[M];
    static int[] ne = new int[M];
    static double[] w = new double[M];
    static double[] d = new double[N];
    static boolean[] vis = new boolean[N];
    static int tot = 0;

    static void add(int a, int b, double c) {
        to[++tot] = b;
        ne[tot] = h[a];
        w[tot] = c;
        h[a] = tot;
    }

    static boolean spfa(int u, double x) { // 判负环
        vis[u] = true;
        for (int i = h[u]; i > 0; i = ne[i]) {
            int v = to[i];
            if (d[v] > d[u] + w[i] - x) {
                d[v] = d[u] + w[i] - x;
                if (vis[v] || spfa(v, x)) {
                    return true;
                }
            }
        }
        vis[u] = false;
        return false;
    }

    static boolean check(double x) {
        Arrays.fill(d, Double.POSITIVE_INFINITY);
        Arrays.fill(vis, false);
        for (int i = 1; i <= n; i++) {
            if (spfa(i, x)) {
                return true;
            }
        }
        return false;
    }

    static double find() {
        double l = -1e7, r = 1e7;
        while (r - l > 1e-10) {
            double mid = (l + r) / 2;
            if (check(mid)) {
                r = mid; // 最小化
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 1; i <= m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            double z = scanner.nextDouble();
            add(x, y, z);
        }
        System.out.printf("%.8f\n", find());
        scanner.close();
    }
}
