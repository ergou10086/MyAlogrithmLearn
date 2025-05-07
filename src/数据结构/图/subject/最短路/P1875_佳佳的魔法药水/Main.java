package 数据结构.图.subject.最短路.P1875_佳佳的魔法药水;

import java.util.*;

class Solutions{
    private class Edge{
        // 终点，边权，下一条边
        int u, v, ne;
        public Edge(int u, int v, int ne) {
            this.u = u;
            this.v = v;
            this.ne = ne;
        }
    }

    // dijkstra用的结构体，存储节点信息，距离，路径数，判重
    private class part{
        int dis, cnt;
        boolean vis;
    }

    private part[] parts = new part[1001];
    private int[] h = new int[1005];
    private int tot = 0;
    private Edge[] edges = new Edge[1000005];

    private PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    private void addEdge(int s1, int s2, int v){
        edges[++tot] = new Edge(s2, v, h[s1]);
        h[s1] = tot;
    }

    private void Dijkstra(){
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int c = top[0];    // 节点u的最短路径距离
            int u = top[1];

            // 来自洛谷大神的思路
            // 从队列中取出的节点 u 的最短路径值 c 是否与parts[u].dis相同
            // 如果不同，说明该节点已经被更新过了，因此跳过这个节点，继续从队列中取下一个节点。
            if(c != parts[u].dis) continue;

            // 该节点的最短路径已经确定。
            parts[u].vis = true;

            // 遍历与节点 u 相连的所有边
            for(int i = h[u]; i != 0; i = edges[i].ne){
                int x = edges[i].u;   // 边的另一端 x 节点
                int v = edges[i].v;   // 权重,同时也是一个节点，因为相当于是累计更新

                // 节点 x 是否已经访问过。如果访问过，则跳过这个边的处理
                if(parts[x].vis){
                    // 当前节点 v 的最短路径可被松弛
                    if (parts[v].dis > c + parts[x].dis) {
                        // 节点 v 的路径数为从 u 到 v 的路径数之积
                        parts[v].cnt = parts[u].cnt * parts[x].cnt;
                        parts[v].dis = c + parts[x].dis;
                        pq.offer(new int[]{parts[v].dis, v});
                        // 找到了另一条相同最短路径。
                    }else if(parts[v].dis == c + parts[x].dis){
                        // 统计所有不同的最短路径
                        parts[v].cnt += parts[u].cnt * parts[x].cnt;
                    }
                }
            }
        }
        System.out.println(parts[0].dis + " " + parts[0].cnt);
    }

    public Solutions() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            parts[i] = new part();
            parts[i].dis = scanner.nextInt();
            parts[i].cnt = 1;
            pq.offer(new int[]{parts[i].dis, i});
        }

        while (scanner.hasNextInt()) {
            int u1 = scanner.nextInt();
            int u2 = scanner.nextInt();
            int v = scanner.nextInt();
            addEdge(u1, u2, v);
            if (u1 != u2) {
                addEdge(u2, u1, v);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}
