package 数据结构.优先队列和链表.subject.P1878_舞蹈课;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 用优先队列来维护相邻舞者之间的技术值的差的绝对值，每次取最小的
// 如果绝对值一样就按照左端点从小到大排序即可
// 维护人和人之间的关系，链表

class Solutions{
    private int n;
    private int[] ai;
    private boolean[] vis;
    private char[] gender;


    class Node implements Comparable<Node>{
        int x, y, d;  // 当前一对的编号和差的绝对值

        public Node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node others) {
            // 差一样，左端点从小到大排序即可
            if(this.d == others.d){
                return Integer.compare(this.x, others.x);
            }else{
                return Integer.compare(this.d, others.d);
            }
        }
    }


    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        String s = sc.next();
        gender = s.toCharArray();
        ai = new int[n + 1];
        vis = new boolean[n + 1];
        Arrays.fill(vis, false);
        for(int i = 1; i <= n; i++){
            ai[i] = sc.nextInt();
        }
        // System.out.println(s);

        PriorityQueue<Node> q = new PriorityQueue<>();
        for(int i = 1; i < n; i++){
            // 要的是相邻且一对男女
            if(gender[i - 1] != gender[i]){
                q.add(new Node(i, i+1,Math.abs(ai[i] - ai[i + 1])));
            }
        }

        // 代表左边有谁
        int[] leftPos = new int[n + 1];
        // 代表右边有谁
        int[] rightPos = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            leftPos[i] = i - 1;
            rightPos[i] = i + 1;
        }
        leftPos[1] = Integer.MAX_VALUE;
        rightPos[n] = Integer.MAX_VALUE;

        int ans = 0;
        StringBuilder sb = new StringBuilder();

        while(!q.isEmpty()){
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            if(!vis[x] && !vis[y]){
                vis[x] = true;
                vis[y] = true;
                ans++;
                sb.append(x).append(" ").append(y).append("\n");
                // 实现连接
                // 右边的右边的l需要改成左边的左边，反之
                if(rightPos[y] != Integer.MAX_VALUE) leftPos[rightPos[y]] = leftPos[x];
                if(leftPos[x] != Integer.MAX_VALUE) rightPos[leftPos[x]] = rightPos[y];
                // 如果这两边性别不一样。形成了一对新人
                if (leftPos[x] != Integer.MAX_VALUE && rightPos[y] != Integer.MAX_VALUE && gender[leftPos[x] - 1] != gender[rightPos[y] - 1]) {
                    q.add(new Node(leftPos[x], rightPos[y], Math.abs(ai[leftPos[x]] - ai[rightPos[y]])));
                }
            }
        }

        System.out.println(ans);
        System.out.print(sb.toString());
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
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
