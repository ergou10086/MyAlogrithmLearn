package 数据结构.图.subject.强连通分量或Tarjan.P3398_仓鼠找sugar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private static final int N = 100010;
    private int[][] fa = new int[N][25];
    private int[] dep = new int[N];
    private int[] dis = new int[N];
    // 图结构
    private int[] to = new int[2 * N], next = new int[2 * N], head = new int[N];
    private int tot = 0, t;  // 边的计数器

    private void add(int a, int b) {
        to[++tot] = b;
        next[tot] = head[a];
        head[a] = tot;
    }

    // 树上任意两点间距离,dis[a] + dis[b] - 2 * dis[lca(a,b)]
    private int dist(int a, int b) {
        return dis[a] + dis[b] - 2 * dis[lca(a, b)];
    }

    // 使用bfs来替代原来的dfs，求出每个节点的深度、祖先节点以及距离信息
    private void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        // 将根节点入队，初始化根节点的深度和距离为0
        queue.add(root);
        dep[root] = 1;
        dis[root] = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            // 遍历当前节点的所有邻接节点
            for (int i = head[x]; i!= 0; i = next[i]) {
                int y = to[i];
                if (dep[y] == 0) {  // 如果邻接节点未被访问过
                    dep[y] = dep[x] + 1;
                    dis[y] = dis[x] + 1;
                    fa[y][0] = x;
                    // 递推求出祖先节点（和原来dfs中的逻辑类似，这里也是倍增思想）
                    for (int j = 1; j <= t; j++) {
                        fa[y][j] = fa[fa[y][j - 1]][j - 1];
                    }
                    queue.add(y);
                }
            }
        }
    }

    /*dfs会递归爆炸
     // dfs求出每个节点的深度和祖先节点，还有距离
    private void dfs(int x, int f) {
        dep[x] = dep[f] + 1;
        dis[x] = dis[f] + 1;
        fa[x][0] = f;

        // 每次向上跳几步递推求出祖先节点
        for(int i = 1; i <= t; i++){
            fa[x][i] = fa[fa[x][i - 1]][i - 1];
        }

        for (int i = head[x]; i != 0; i = next[i]) {
            int y = to[i];
            if (y != f) {
                dfs(y, x);
            }
        }
    }
     */

    private int lca(int x, int y) {
        if (dep[x] < dep[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = t; i >= 0; i--) {
            if (dep[fa[x][i]] >= dep[y]) {
                x = fa[x][i];
            }
        }

        if (x == y) return y;

        for (int i = t; i >= 0; i--) {
            if (fa[x][i]!= fa[y][i]) {
                x = fa[x][i];
                y = fa[y][i];
            }
        }

        return fa[x][0];
    }

    public Solutions() {
        FastReader fr = new FastReader();

        int n = fr.nextInt();
        int q = fr.nextInt();

        // Initialize graph
        Arrays.fill(head, 0);
        Arrays.fill(dep, 0);

        for (int i = 1; i < n; i++) {
            int a = fr.nextInt(), b = fr.nextInt();
            add(a, b);
            add(b, a);
        }

        // lca递归的最大深度
        t = (int) (Math.log(n) / Math.log(2)) + 1;

        bfs(1);

        for (int i = 1; i <= q; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            int c = fr.nextInt();
            int d = fr.nextInt();

            // 如果两个起点的距离 + 两个终点的距离 >= 两条路径的长度和
            // 那么两条路径有一部分一定是重合的（或者说一定是存在公共点的）
            if (dist(a, b) + dist(c, d) >= dist(a, c) + dist(b, d)) {
                System.out.println("Y");
            } else {
                System.out.println("N");
            }
        }
    }

    private class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null ||!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    System.out.println("Nothing");
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}