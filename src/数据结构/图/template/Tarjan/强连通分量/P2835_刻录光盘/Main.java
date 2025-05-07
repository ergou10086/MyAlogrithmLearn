package 数据结构.图.template.Tarjan.强连通分量.P2835_刻录光盘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 一个联通图中我们只需要在最开始的点给一个光盘，就可以
// 找有几个连通图，找几个入边为0的点就行

class Solutions {
    static final int S = 205;
    private List<Integer>[] e = new ArrayList[S];   // 模拟栈
    private int[] dfn = new int[S];   // 时间戳：节点x第一次被访问的顺序
    private int[] low = new int[S];   // 追溯值：从节点x出发，所能访问到的最早时间戳
    private int[] stk = new int[S];   // 栈
    private int[] instk = new int[S];  // 是否在栈中
    private int[] scc = new int[S];   // 每个节点所属的强连通分量的编号。
    private int[] siz = new int[S];   // 每一组强连通的节点的数量
    private int[] rudu = new int[S];  // 强连通分量入度
    private int tot, top, cnt;   // tot是时间戳编号器，top是栈的指针

    private void tarjan(int x){
        // 初始化部分
        // 入x时，盖戳，表示第tot个被访问的节点
        // 所以说当前节点 x 的所能访问到最早的顺序位就是初始为它自己
        dfn[x] = low[x] = ++tot;
        // 入栈
        stk[++top] = x;
        instk[x] = 1;

        for(int y : e[x]){
            // 若y未被访问过，则递归访问
            if(dfn[y] == 0){
                tarjan(y);
                // 回到x时候，更新x的追溯值
                // 如果 y 能访问到更早的节点，那么 x 也能通过 y 访问到这些节点，因此更新 low[x]
                low[x] = Math.min(low[x], low[y]);
            // 在栈中，访问过
            }else if(instk[y] == 1){
                // 因为 y 是 x 的祖先节点，x 可以通过 y 访问到更早的节点，因此用 dfn[y] 更新 low[x]
                low[x] = Math.min(low[x], dfn[y]);
            }
        }

        // dfn[x] == low[x]，说明 x 无法通过它的子树访问到更早的节点，因此 x 是一个强连通分量的根。
        // 将栈中从x到栈顶的所有节点弹出，构成一个强连通分量
        if(dfn[x] == low[x]){
            ++cnt;  // 更新强连通分量的编号
            while(true){
                int y = stk[top--];
                instk[y] = 0;
                scc[y] = cnt;
                siz[cnt]++;
                if(y == x) break;
            }
        }
    }

    public Solutions() {
        FastReader sc = new FastReader();
        int n = sc.nextInt();   // 总节点数
        for(int i = 1; i <= n; i++) e[i] = new ArrayList<>();

        int rp = 1;
        for (int i = 1; i <= n; i++) {
            while(rp != 0){
                rp = sc.nextInt();
                if(rp == 0) continue;
                e[i].add(rp);
            }
            rp = 1;
        }

        // 执行 Tarjan 算法
        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0) {
                tarjan(i);
            }
        }

        // 统计入度
        for(int i = 1; i <= n; i++){
            for(int next: e[i]){
                // 如果他们不属于一个强连通分量
                if(scc[i] != scc[next]){
                    ++rudu[scc[next]];
                }
            }
        }

        int ans = 0;
        for(int i = 1; i <= cnt; i++){
            if(rudu[i] == 0) ++ans;
        }

        System.out.println(ans);
    }


    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }
        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
