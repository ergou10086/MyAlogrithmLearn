package 字符串.subject.P4551_最长异或路径;

import java.util.Scanner;

public class Main {
    static final int N = 100010;
    static int n;
    static Edge[] edges = new Edge[N * 2]; // 存储边的信息
    static int[] h = new int[N]; // 每个节点的邻接表头
    static int idx = 0; // 边的索引
    static int[] sum = new int[N]; // sum[x] 存储 x 到根的异或和
    static int[][] ch = new int[N * 31][2]; // 字典树结构
    static int cnt = 0; // 字典树的节点计数

    // 边的结构体
    static class Edge {
        int to, w, ne; // to: 边的终点, w: 边的权重, ne: 下一个边的索引
        Edge(int to, int w, int ne) {
            this.to = to;
            this.w = w;
            this.ne = ne;
        }
    }

    // 添加边到邻接表
    public static void add(int a, int b, int c) {
        edges[++idx] = new Edge(b, c, h[a]);
        h[a] = idx;
    }


    // 深度优先搜索计算每个节点到根节点的异或和
    public static void dfs(int x, int fa){
        for(int i = h[x]; i > 0; i = edges[i].ne){
            int y = edges[i].to;
            int w = edges[i].w;
            if(y == fa) continue;
            sum[y] = sum[x] ^ w;  // 计算到根节点的异或和
            dfs(y, x);
        }
    }



    public static void insert(int x) {
        int p = 0;
        for (int i = 30; i >= 0; i--) { // 从高位到低位插入
            int j = (x >> i) & 1; // 取出当前位
            if (ch[p][j] == 0) {
                ch[p][j] = ++cnt; // 创建新节点
            }
            p = ch[p][j]; // 移动到下一层
        }
    }


    // 查询与当前异或和 x 最远的异或和
    public static int query(int x) {
        int p = 0, res = 0;
        for (int i = 30; i >= 0; i--) { // 从高位到低位
            int j = x >> i & 1; // 取出当前位
            if (ch[p][1 - j] != 0) { // 如果有相反的路径
                res += 1 << i; // 更新结果
                p = ch[p][1 - j]; // 移动到相反的路径
            } else {
                p = ch[p][j]; // 否则继续在当前路径
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 读取节点数
        for (int i = 1; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int w = scanner.nextInt();
            add(x, y, w); // 添加边
            add(y, x, w); // 添加反向边
        }
        dfs(1, 0); // 从节点 1 开始 DFS
        for(int i = 1; i <= n; i++) {
            insert(sum[i]);  // 插入每个节点的异或和
        }
        int resp = 0;
        for (int i = 1; i <= n; i++) {
            resp = Math.max(resp, query(sum[i]));
        }
        System.out.println(resp);
        scanner.close();
    }
}
