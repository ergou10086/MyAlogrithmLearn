package 基础算法和其他.差分.subject.p3258_松鼠的新家;

import java.util.Scanner;

public class Main {
    static final int maxn = 300050;
    static final int maxm = maxn << 1;
    static int n;
    static int[] a = new int[maxn];
    static int[] head = new int[maxn];
    static int cnt;
    static int[][] fa = new int[maxn][31];
    static int[] dep = new int[maxn];
    static int[] amounts = new int[maxn];

    static class Edge {
        int u;
        int v;
        int next;
    }

    static Edge[] edge = new Edge[maxm];


     static void addEdge(int u, int v) {
        edge[++cnt] = new Edge();
        edge[cnt].u = u;
        edge[cnt].v = v;
        edge[cnt].next = head[u];
        head[u] = cnt;
    }

    static void dfs(int u, int faa) {
        fa[u][0] = faa;
        dep[u] = dep[faa] + 1;

        for (int i = 1; i <= 30; i++) {
            fa[u][i] = fa[fa[u][i - 1]][i - 1];
        }

        for (int i = head[u]; i!= 0; i = edge[i].next) {
            int v = edge[i].v;
            if (v == faa) continue;
            dfs(v, u);
        }
    }

    static int lca(int x, int y) {
        if (dep[x] < dep[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = 30; i >= 0; i--) {
            if (dep[fa[x][i]] >= dep[y]) {
                x = fa[x][i];
            }
        }

        if (x == y) return x;

        for (int i = 30; i >= 0; i--) {
            if (fa[x][i]!= fa[y][i]) {
                x = fa[x][i];
                y = fa[y][i];
            }
        }

        return fa[x][0];
    }


    static void dfs2(int u, int faa) {
        for (int i = head[u]; i!= 0; i = edge[i].next) {
            int v = edge[i].v;
            if (v == faa) continue;
            dfs2(v, u);
            amounts[u] += amounts[v];
        }
    }



    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) a[i] = sc.nextInt();

        for (int i = 1; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addEdge(x, y);
            addEdge(y, x);
        }

        dfs(1, 0);

        for (int i = 1; i <= n - 1; i++) {
            int us = a[i];
            int vs = a[i + 1];
            int ts = lca(us, vs);
            amounts[us] += 1;
            amounts[vs] += 1;
            amounts[ts] -= 1;
            amounts[fa[ts][0]] -= 1;
        }

        dfs2(1, 0);

        for (int i = 2; i <= n; i++) {
            amounts[a[i]]--;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(amounts[i]);
        }
        sc.close();
    }
}

/*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for (int i = 1; i <= n; i++) a[i] = (int) in.nval;

        for (int i = 1; i <= n; i++) {
            int x = (int) in.nval;
            int y = (int) in.nval;
            addEdge(x, y);
            addEdge(y, x);
        }
        dfs2(1, 0);

        for (int i = 1; i <= n - 1; i++) {
            int us = a[i];
            int vs = a[i + 1];
            int ts = lca(us, vs);
            amounts[us] += 1;
            amounts[vs] += 1;
            amounts[ts] -= 1;
            amounts[fa[ts][0]] -= 1;
        }
        dfs2(1, 0);
        for (int i = 2; i <= n; i++) {
            amounts[a[i]]--;
        }
        for (int i = 1; i <= n; i++) {
            out.println(amounts[i]);
        }
        out.flush();
        out.close();
        br.close();
    }
}

*/