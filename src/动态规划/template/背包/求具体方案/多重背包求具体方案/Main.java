package 动态规划.template.背包.求具体方案.多重背包求具体方案;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static final int N = 2010;
    // 体积
    private static int[] v = new int[N];
    // 价值
    private static int[] w = new int[N];
    // 数量
    private static int[] s = new int[N];

    private static int[] f = new int[N];
    // 路径，记录状态转移的路径
    private static int[][] path = new int[N][N];
    // 记录每种物品选择的数量
    private static int[] choice = new int[N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 读取每个物品的体积、价值和数量
        for (int i = 1; i <= n; i++) {
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        // 枚举物品
        for(int i = 1; i <= n; i++){
            // 枚举余数w(w个类)
            for (int r = 0; r < v[i]; r++) {
                // 单调队列
                LinkedList<Integer> q = new LinkedList<>();
                // g拷贝f，实际操作在g中进行
                int[] g = Arrays.copyOf(f, f.length);

                // 对每个类使用单调队列
                // 对于每个余数 j，枚举所有 k = j,j+r,j+2r（同类中枚举）
                for (int k = r; k <= m; k += v[i]) {
                    // 维护窗口大小不超过s[i]个物品的体积
                    while(!q.isEmpty() && k - q.getFirst() > s[i] * v[i]){
                        q.removeFirst();
                    }

                    // 计算当前状态的最大价值
                    if (!q.isEmpty()) {
                        int prev = q.peekFirst();
                        int currValue = g[prev] + (k - prev) / v[i] * w[i];
                        if (currValue > f[k]) {
                            f[k] = currValue;
                            path[i][k] = prev;
                        }
                    }

                    // 维护队列单调递减
                    while (!q.isEmpty()) {
                        // 队列中最后一个状态 last 对应的物品数量，也就是从当前余数 r 开始的体积增量
                        int tLast = (q.peekLast() - r) / v[i];
                        // 当前状态 k 对应的物品数量。
                        int tK = (k - r) / v[i];
                        // 比较队列尾部状态 last 和当前状态 k 的价值，更大则更优，不需要移除
                        if(g[q.peekLast()] - tLast * w[i] >= g[k] - tK * w[i]) break;
                        q.pollLast();
                    }
                    q.offerLast(k);
                }
            }
        }

        System.out.println(f[m]);

        int j = m;
        for (int i = n; i >= 1; i--) {
            if (path[i][j] != 0) {
                // 得到每种物品选择的数量
                choice[i] = (j - path[i][j]) / v[i];
                j = path[i][j];
            }
        }

        for (int i = 1; i <= n; i++) {
            if (choice[i] > 0) {
                System.out.print("物品 " + i + " 选 " + choice[i] + " 个; ");
            }
        }
    }
}
