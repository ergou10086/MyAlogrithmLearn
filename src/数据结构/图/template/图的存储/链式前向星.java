package 数据结构.图.template.图的存储;

// 链式前向星和链式邻接表处理是差不多的
// 一个表头数组悬挂多个链表
// 边集数组e[i]存储第i条出边的{终点v，边权w，下一条边ne}
// 表头数组h[u]存储u点的第一条出边的编号
// 边的编号idx可取0，1，2，3
// 时间空间复杂度都是O（n + m），复杂度低
// 应用：各种图，能处理反向边
// 先插后访问

import java.util.*;

public class 链式前向星 {
    static final int N = 510;
    static final int M = 3000;
    static int n, m, a, b, c;

    static class Edge {
        // 终点，边权，下一条边
        int v, w, ne;
        public Edge(int v, int w, int ne) {
            this.v = v;
            this.w = w;
            this.ne = ne;
        }
    }

    static Edge[] e = new Edge[M];
    static int idx = 0;   // 边的编号
    static int[] h = new int[N];


    // 添加边到邻接表
    static void add(int a, int b, int c) {
        // e[idx]存储第idx条边的终点，权值，以及是否还有下一条边及其信息，每条边的起点通过h数组的下标存
        e[idx] = new Edge(b, c, h[a]);
        // 更新表头数组，起点a的出边编号为idx，idx指针需要加一
        h[a] = idx++;
    }


    // 深度优先搜索访问
    static void dfs(int u, int fa) {
        // 取出起点u的第一条出边的信息，每次取其下一条边的信息，后加的边先访问，因为是头插法
        for (int i = h[u]; i != -1; i = e[i].ne) {
            int v = e[i].v;
            int w = e[i].w;
            if (v == fa) continue;  // 判重
            System.out.printf("%d,%d,%d\n", u, v, w);
            dfs(v, u);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        Arrays.fill(h, -1);  // 初始化h数组为-1，代表无下一条边

        // 读入边并构造图
        for (int i = 1; i <= m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);  // 因为是无向图，双向添加
        }

        // 从节点1开始DFS遍历
        dfs(1, 0);

        sc.close();
    }
}
