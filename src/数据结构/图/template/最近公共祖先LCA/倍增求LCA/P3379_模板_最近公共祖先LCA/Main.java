package 数据结构.图.template.最近公共祖先LCA.倍增求LCA.P3379_模板_最近公共祖先LCA;

// 两个节点的最近公共祖先就是这两个点的公共祖先里面，离他们最近的那个
// 倍增算法求lca
// dep[u]存u点的深度
// fa[u][i]存从u点向上跳跃2^i层的祖先节点，i为0，1，2，3
// 算法流程
// dfs一遍，创建st表
// 倍增递推，fa[u][i] = fa[fa[u][i-1]][i-1]
// 利用打好的st表求lca
// 第一阶段，将u，v跳回到同一层
// 设u，v两点的深度之差为y，将y进行二进制拆分，可以将y次游标跳跃优化为 y的二进制所含1的个数 次游标跳跃，一定能跳到同一层
// 第二阶段，将u，v一起跳到lca的下一层
// 从最大的i开始尝试，一直尝试到0，最后游标u，v一定能停到lca的下一层

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{
    private static final int N = 500005;  // 最大节点数
    private int n, m, s;
    private List<Integer>[] e = new ArrayList[N];
    private int[] dep = new int[N];        // 记录每个节点的深度
    private int[][] fa = new int[N][22];   // fa[i][j]记录i节点的2^j祖先


    private void dfs(int x, int father){

        dep[x] = dep[father] + 1;

        fa[x][0] = father;    // x节点向上跳2^0步为其父节点

        // 每次向上跳几步递推求出祖先节点
        for(int i = 1; i <= 20; i++){
            fa[x][i] = fa[fa[x][i-1]][i-1];
        }

        // 枚举x点的儿子
        for (int y : e[x]) {
            // 判重，只允许向下深搜
            if (y != father) {
                dfs(y, x);
            }
        }
    }


    // 求两个节点的最近公共祖先
    private int lca(int x, int y){
        // 保证让x跳，深度大的跳
        if (dep[x] < dep[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        // x先大步后小步向上跳，直到与y同层
        for (int i = 20; i >= 0; i--) {
            // 为了能跳到同一层，一定要比较深度
            if (dep[fa[x][i]] >= dep[y]) {
                x = fa[x][i];
            }
        }

        if(x == y) return y;

        // x和y一起向上跳，直到找到LCA
        for (int i = 20; i >= 0; i--) {
            if (fa[x][i] != fa[y][i]) {
                x = fa[x][i];
                y = fa[y][i];
            }
        }
        return fa[x][0];
    }




    public Solutions(){
        FastReader scanner = new FastReader();

        n = scanner.nextInt();
        m = scanner.nextInt();
        s = scanner.nextInt();

        // 初始化每个节点的邻接表
        for (int i = 1; i <= n; i++) {
            e[i] = new ArrayList<>();
        }

        // 输入边
        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            e[a].add(b);
            e[b].add(a);
        }

        // 从根节点s开始深度优先搜索
        dfs(s, 0);

        // 查询LCA
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(lca(a, b));
        }

    }


    private class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}