package 数据结构.图.template.Tarjan.最近公共祖先LCA.LuoguP3379_模板_最近公共祖先LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

// Tarjan算法是一种离线算法，使用并查集维护祖先节点
// 从根开始深搜遍历，入u时候打标记
// 枚举完u的儿子v，遍历完v的子树，回u时候把v指向u
// 遍历完u的儿子们，离开u的时候枚举以u为起点的查询，若终点v被搜过，查找v的根，就是u和v的lca，计入ans[]
// 递归遍历完整树

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions{
    private final int N = 500005, M = 2 * N;
    private int n, m, s;   // 节点数量、查询数量、根节点
    private List<Integer>[] e;   // 邻接表，存储树,e[i]对应的相邻节点列表
    private HashMap<Integer, List<int[]>> query;    // 存储每个节点相关的查询，键为节点编号，值为包含对应查询节点和查询编号的列表
    private int[] fa;   // 并查集fa
    private boolean[] vis;   // 标记节点是否被访问过
    private int[] ans;   // 存储查询结果

    // 并查集找
    private int find(int x){
        if(x == fa[x]){
            return x;
        }else{
            return fa[x] = find(fa[x]);    // 路径压缩
        }
    }


    // 塔杨lca
    private void tarjan(int x) {
        vis[x] = true;  // 入时标记

        // 遍历u当前节点的所有儿子
        for (int y : e[x]) {
            // 保证向下走
            if (!vis[y]) {
                // 深搜
                tarjan(y);
                // 回u时候，v指向u
                fa[y] = x;
            }
        }

        // 离开u时候，枚举lca
        List<int[]> queries = query.get(x);
        for (int[] q : queries) {
            // 取出查询
            int y = q[0];
            // 取出终点
            int i = q[1];
            // 搜过就记录答案
            if (vis[y]) {
                ans[i] = find(y);
            }
        }
    }

    public Solutions(){
        FastReader fr = new FastReader();
        n = fr.nextInt();
        m = fr.nextInt();
        s = fr.nextInt();

        // 初始化图
        e = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            e[i] = new ArrayList<>();
        }
        // 初始化查询相关的哈希表
        query = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            query.put(i, new ArrayList<>());
        }

        // 读取树的边信息，构建邻接表表示的树结构
        for (int i = 1; i < n; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            e[a].add(b);
            e[b].add(a);
        }

        // 读取查询信息，存入query哈希表中
        for (int i = 1; i <= m; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            query.get(a).add(new int[]{b, i});
            query.get(b).add(new int[]{a, i});
        }

        // 初始化并查集，每个节点的父节点初始化为自己
        fa = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }

        // 标记节点访问情况的数组，初始化为false，表示未访问
        vis = new boolean[n + 1];

        // 存储查询结果的数组，初始化为0
        ans = new int[m + 1];

        tarjan(s);

        // 输出查询结果
        for (int i = 1; i <= m; i++) {
            System.out.println(ans[i]);
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
