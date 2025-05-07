package 数据结构.图.template.拓扑排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 给定一张有向无环图，排出所有顶点的一个序列A满足
// 对于图中的每条有向边（x,y）,x在A中一定在y前面,则A是该图顶点的一个拓扑序列
// 拓扑排序可以判断有向图中是否存在环



// Kahn算法的核心是用队列维护一个入度为0的节点的队列
// 某种意义上也是宽搜
public class Kahn算法 {
    public static void main(String[] args) throws IOException {
        new Solutions();
    }
}



class Solutions{
    private final int MAX_N = 100010;  // 图中节点的最大数量
    private ArrayList<Integer>[] adjList = new ArrayList[MAX_N];  // 邻接表
    private int[] inDegree = new int[MAX_N];  // 入度数组
    private int n, m;  // n为节点数，m为边数

    private boolean topologicalSort() {
        // 队列存放入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        // 结果列表，存储拓扑排序的结果。
        ArrayList<Integer> result = new ArrayList<>();

        // 找到所有入度为0的节点
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 节点被逐个从队列中取出，并检查其邻接节点的入度。
        // 如果邻接节点的入度减为0，则将其加入队列，最终得到一个拓扑排序。
        // 代码的核心是维持一个入度为 0 的顶点的集合
        while(!queue.isEmpty()) {
            // 每次从队列中取出一个节点放入reslut
            int node = queue.poll();
            result.add(node);

            // 访问所有邻接节点
            for(int neighbor : adjList[node]) {
                // 减少邻接节点的入度，相当于删除点的出边
                inDegree[neighbor]--;
                // 如果邻接节点的入度为0，则加入队列
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // 如果拓扑排序的结果包含所有节点，说明没有环
        return result.size() == n;
    }


    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();   // 节点数
        m = sc.nextInt();   // 边数

        // 初始化邻接表
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 读入边的信息
        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            adjList[a].add(b);
            inDegree[b]++;  // 增加b的入度
        }


        // 使用Kahn算法进行拓扑排序
        if (!topologicalSort()) {
            System.out.println("-1");
        } else {
            // 输出拓扑排序结果
            for (int i = 1; i <= n; i++) {
                System.out.print(i + " ");
            }
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
