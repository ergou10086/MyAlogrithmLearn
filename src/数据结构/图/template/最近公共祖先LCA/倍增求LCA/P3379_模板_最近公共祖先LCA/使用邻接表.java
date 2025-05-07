package 数据结构.图.template.最近公共祖先LCA.倍增求LCA.P3379_模板_最近公共祖先LCA;

// 邻接表的形式

import java.util.Scanner;

public class 使用邻接表 {
    public static void main(String[] args) {
        new Solution();
    }
}


class Solution{
    private static final int N = 500010;  // 最大节点数
    private static final int M = 2 * N;   // 最大边数（每个边出现两次）

    private int n, m, s;
    private int[] dep = new int[N];  // 记录每个节点的深度
    private int[][] fa = new int[N][22];  // fa[i][j]记录i节点的2^j祖先
    private int[] h = new int[N];   // 邻接表的头部
    private int[] to = new int[M];  // 邻接表的节点
    private int[] ne = new int[M];  // 邻接表的下一节点
    private int tot = 0;  // 边的计数器

    // 向邻接表中添加边
    private void add(int a, int b) {
        to[++tot] = b;
        ne[tot] = h[a];
        h[a] = tot;
    }

    // 深度优先搜索，计算dep和fa
    private void dfs(int x, int f) {
        dep[x] = dep[f] + 1;
        fa[x][0] = f;
        for (int i = 0; i < 20; i++) {
            fa[x][i + 1] = fa[fa[x][i]][i];
        }

        for(int i = h[x]; i != 0; i = ne[i]){
            int y = to[i];
            if (y != f) {
                dfs(y, x);
            }
        }
    }


    // 求两个节点的最近公共祖先 (LCA)
    private int lca(int x, int y) {
        if (dep[x] < dep[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = 20; i >= 0; i--) {
            if (dep[fa[x][i]] >= dep[y]) {
                x = fa[x][i];
            }
        }

        if(x == y) return y;

        for(int i = 20; i >= 0; i--) {
            if(fa[x][i] != fa[y][i]){
                x = fa[x][i];
                y = fa[y][i];
            }
        }
        return fa[x][0];
    }

    public Solution(){
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        n = scanner.nextInt();
        m = scanner.nextInt();
        s = scanner.nextInt();

        // 初始化邻接表
        for (int i = 1; i <= n; i++) {
            h[i] = 0;
        }

        // 输入边
        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            add(a, b);
            add(b, a);
        }

        // 从根节点s开始深度优先搜索
        dfs(s, 0);

        // 查询LCA
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(lca(a, b));
        }

        scanner.close();
    }
}
