package 数据结构.图.template.拓扑排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 算法的核心的变色
// 如果有拓排序，每个点颜色都会变成0->-1->1
// 如果有环，所有点染色为0
// 枚举每个点，进入x点，染色为-1
// 然后枚举x的儿子y，如果y的颜色为0，没碰过该点，进入y继续走
// 枚举完x的儿子，没发现环，则x染色为1，x压入res
// 如果发现某孩子颜色为-1，说明回到了祖先节点，说明有环，返回false，退出


public class DFS求拓扑排序 {
    public static void main(String[] args) {
        new Soultions();
    }
}


class Soultions{
    private int n, m;   // n为节点数，m为边数
    private List<List<Integer>> e = new ArrayList<>();    // 邻接表，存储图的边关系，e.get(i)表示节点i连接的其他节点列表
    private LinkedList<Integer> tp = new LinkedList<>();  // 用于存储拓扑排序的结果
    private int[] c;   // 染色数组，记录节点状态，0表示未访问，-1表示正在访问在递归栈中，1表示已访问且完成拓扑排序

    private boolean dfs(int u) {
        c[u] = -1;  // 标记当前节点为正在访问,表示已经进入递归栈但还未处理完其所有子节点
        List<Integer> neighbors = e.get(u);   // 获取节点u的所有邻接节点
        for (int v : neighbors) {
            // 如果邻接节点v已经被标记为正在访问（-1），说明存在环，无法进行拓扑排序
            if (c[v] < 0) {
                return false;  // 存在环，无法进行拓扑排序
            } else if (c[v] == 0) {    // 如果邻接节点y还未被访问（0）
                // 递归地对y进行深度优先搜索，如果在搜索y的子树过程中发现环，返回false
                if (!dfs(v)) {
                    return false;    // 存在环，无法进行拓扑排序
                }
            }
        }
        c[u] = 1;     // 将当前节点标记为已访问且完成拓扑排序（1）
        tp.addFirst(u);  // 将当前节点添加到拓扑排序结果列表的头部（因为拓扑排序是逆序得到的，最后要反转）
        return true;
    }


    private boolean topologicalSort() {
        c = new int[n + 1];
        for (int x = 1; x <= n; x++) {
            if(c[x] == 0){
                if(!dfs(x)){
                    return false;
                }
            }
        }
        return true;
    }

    public Soultions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        // 初始化邻接表，每个节点都有一个对应的列表来存储其邻接节点
        for (int i = 0; i <= n; i++) {
            e.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            e.get(a).add(b);
        }


        if(topologicalSort()){
            for (int i = 0; i < n; i++) {
                System.out.print(tp.get(i) + " ");
            }
        }else{
            System.out.println("-1");
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