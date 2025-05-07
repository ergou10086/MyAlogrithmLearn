package 数据结构.图.subject.最短路.P1027_NOIP2001提高组_Car的旅行路线;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions{
    private final static int inf = 0x3f3f3f3f;
    private class Node{
        int x, y;   // 坐标
        int city;  // 在哪个城市
        public Node(int x, int y, int city){
            this.x = x;
            this.y = y;
            this.city = city;
        }
    }
    private Node[] nodes = new Node[407];
    private double[] price = new double[407];  // 该城市高速铁路单位里程的价格
    private double[][] dis = new double[406][406];  // 距离的dp数组

    // 平方简写
    private int PF(int x) {
        return x * x;
    }

    // 两点间距离公式
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(PF(x1 - x2) + PF(y1 - y2));
    }

    // 求矩形的第四个点
    private void Get_4th_point(int x1, int y1, int x2, int y2, int x3, int y3, int index){
        int abF = PF(x1 - x2) + PF(y1 - y2);
        int acF = PF(x1 - x3) + PF(y1 - y3);
        int bcF = PF(x2 - x3) + PF(y2 - y3);
        int x4 = 0, y4 = 0;

        // 判断直角三角形的直角
        if(acF + abF == bcF){
            x4 = x2 + x3 - x1;
            y4 = y2 + y3 - y1;
        }

        if(abF + bcF == acF){
            x4 = x1 + x3 - x2;
            y4 = y1 + y3 - y2;
        }

        if(acF + bcF == abF){
            x4 = x1 + x2 - x3;
            y4 = y1 + y2 - y3;
        }

        nodes[index + 3] = new Node(x4, y4, nodes[index].city);
    }


    // 计算两节点距离之间的费用
    private double cost(Node x1, Node x2, double t){
        double distp = distance(x1.x, x1.y, x2.x, x2.y);
        if(x1.city == x2.city){
            return distp * price[x1.city];
        }
        return distp * t;
    }



    public Solutions(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        while(n-- > 0){
            double ans = 29292992.0;
            // 清空nodes
            for (int i = 0; i <= 406; i++) {
                nodes[i] = new Node(0, 0, 0);
            }

            // 初始化距离
            for (int i = 0; i < dis.length; i++) {
                Arrays.fill(dis[i], 98);
            }

            int s = sc.nextInt();
            int t = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();

            // 读入数据，建图
            for(int i = 1; i <= 4 * s; i += 4){
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                int x3 = sc.nextInt();
                int y3 = sc.nextInt();
                int ti = sc.nextInt();
                price[i / 4 + 1] = ti;
                nodes[i] = new Node(x1, y1, i / 4 + 1);
                nodes[i + 1] = new Node(x2, y2, i / 4 + 1);
                nodes[i + 2] = new Node(x3, y3, i / 4 + 1);
                nodes[i + 3] = new Node(0, 0, i / 4 + 1);
                Get_4th_point(nodes[i].x, nodes[i].y, nodes[i + 1].x, nodes[i + 1].y, nodes[i + 2].x, nodes[i + 2].y, i);
            }


            // 建图初始化
            for (int i = 1; i <= 4 * s; i++) {
                for (int j = 1; j <= 4 * s; j++) {
                    dis[i][j] = cost(nodes[i], nodes[j], t); // 计算每对点之间的费用
                }
            }


            // floyd
            for (int k = 1; k <= 4 * s; k++) {
                for (int i = 1; i <= 4 * s; i++) {
                    if (i != k) {
                        for (int j = 1; j <= 4 * s; j++) {
                            if (i != j && j != k) {
                                if (dis[i][j] > dis[i][k] + dis[k][j]) {
                                    dis[i][j] = dis[i][k] + dis[k][j];
                                }
                            }
                        }
                    }
                }
            }



            for (int i = A * 4 - 3; i <= A * 4; i++) {
                for (int j = B * 4 - 3; j <= B * 4; j++) {
                    if (dis[i][j] < ans) {
                        ans = dis[i][j];
                    }
                }
            }
            System.out.printf("%.1f\n", ans);
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
