package 基础算法和其他.差分.template.树上差分.Luogu_P3128;

import java.util.*;

public class Main2 {
    static final int N = 50005;
    static final int M = N - 1;

    static int cnt;
    static int[] head = new int[N];
    static int[][] fa = new int[N][21];
    static int[] depth = new int[N];
    static int[] power = new int[N];
    static int ans;

    static class Edge {
        int v;
        int next;
    }

    static Edge[] edges = new Edge[M * 2];

    static {
        for (int i = 0; i < M * 2; i++) {
            edges[i] = new Edge();
        }
    }

    static void add(int u, int v) {
        cnt++;
        edges[cnt].v = v;
        edges[cnt].next = head[u];
        head[u] = cnt;
    }

    static void dfs(int son, int father) {
        fa[son][0] = father;
        depth[son] = depth[father] + 1;

        for (int i = 1; (1 << i) <= depth[son]; i++) {
            fa[son][i] = fa[fa[son][i - 1]][i - 1];
        }

        for (int i = head[son]; i != 0; i = edges[i].next) {
            int v = edges[i].v;
            if (v != father) {
                dfs(v, son);
            }
        }
    }

    static int lca(int x, int y) {
        if (depth[x] < depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = 20; i >= 0; i--) {
            if (depth[fa[x][i]] >= depth[y]) {
                x = fa[x][i];
            }
        }
        if (x == y) return x;

        for (int i = 20; i >= 0; i--) {
            if (fa[x][i] != fa[y][i]) {
                x = fa[x][i];
                y = fa[y][i];
            }
        }
        return fa[x][0];
    }

    static void getAns(int son, int father) {
        for (int i = head[son]; i != 0; i = edges[i].next) {
            if (edges[i].v == father) continue;
            getAns(edges[i].v, son);
            power[son] += power[edges[i].v];
        }
        ans = Math.max(ans, power[son]);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int u, v;


        for (int i = 0; i < n - 1; i++) {
            u = scanner.nextInt();
            v = scanner.nextInt();
            add(u, v);
            add(v, u);
        }

        dfs(1, 0);

        for (int i = 0; i < k; i++) {
            u = scanner.nextInt();
            v = scanner.nextInt();
            int togetherFather = lca(u, v);
            power[u]++;
            power[v]++;
            power[togetherFather]--;
            power[fa[togetherFather][0]]--;
        }

        getAns(1, 0);
        System.out.println(ans);
        scanner.close();
    }
}
