package 动态规划.template.背包.树上背包.普通树上背包;

// N个物品和容量为V的背包
// 物品之间有依赖关系，且依赖关系构成给一棵树的形状，如果选择了一个物品那么必须选择它的父节点

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 最大物品数量
    static final int N = 110;
    // 物品数量
    static int n;
    // 背包容量
    static int V;
    // 根节点编号
    static int root;
    // 物品体积
    static int[] v = new int[N];
    // 物品价值
    static int[] w = new int[N];
    // 邻接表存储树的结构
    static List<List<Integer>> adj = new ArrayList<>();
    // 动态规划数组，f[u][j] 表示以 u 为根的子树，背包容量不超过 j 时的最大价值
    // 节点u有i个子节点s1，s2，s3，可以把u的i个子节点看作i组物品
    // 每组物品si按照单位体积拆分，有0，1，2，。。j-v[u]
    // 按照单位体积拆分因为si的子孙可能存在体积为1的物品
    // 拆到j-v[u]你得给父亲留出地方
    static int[][] f = new int[N][N];

    private static void add(int a, int b){
        adj.get(a).add(b);
    }

    // 从根节点u开始dfs，从根到叶，再从叶到根回溯，做分组背包，更新f值
    private static void dfs(int u){
        // 初始化，当背包容量大于等于当前物品体积时，价值为当前物品价值
        for (int i = v[u]; i <= V; i++) {
            f[u][i] = w[u];
        }

        // 遍历当前节点的所有子节点
        for (int s : adj.get(u)) {
            dfs(s);
            //分组背包，每个儿子是一组物品
            // 从大到小枚举背包容量，枚举到v[u]给父亲留出地方
            for (int j = V; j >= v[u]; j--) {
                // 枚举分配给子树的容量，分组背包的决策
                for (int k = 0; k <= j - v[u]; k++) {
                    // f[s][k] 每组决策对应一个打包的物品，可能包含多个子孙物品的组合
                    f[u][j] = Math.max(f[u][j], f[u][j - k] + f[s][k]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        V = scanner.nextInt();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
            int p = scanner.nextInt();
            if (p == -1) {
                root = i;
            } else {
                add(p, i);
            }
        }

        // 进行深度优先搜索
        dfs(root);

        // 输出结果
        System.out.println(f[root][V]);
        scanner.close();
    }
}

