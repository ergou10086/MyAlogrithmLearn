package 搜索.BFS.template.宽搜.POJ3984_迷宫问题;

// 宽搜是一层一层扩展，想好要用什么，求最佳方案一般是宽搜，求解的数量一般是深搜

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private class Node{
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static final int INT = 1010;
    private int n;
    private int[][] graph = new int[INT][INT];   // 存图兼职判重，起点g[0][1] = 1
    private Node[][] path = new Node[INT][INT];   // 存路径
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        graph[x][y] = 1;   // 打标记

        while(!q.isEmpty()){
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int x_delta = cur.x + dx[i];
                int y_delta = cur.y + dy[i];
                if(x_delta < 0 && x_delta >= n && y_delta < 0 && y_delta >= n  && graph[x_delta][y_delta] == 1) continue;
                graph[x_delta][y_delta] = 1; // 打标记
                path[x_delta][y_delta] = cur;   // 做记录
                q.add(new Node(x_delta, y_delta));  // 入队列
            }
        }
    }

    public Solutions() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        bfs(0, 0);

        Node p = new Node(0, 0);  // 起点开始找路
        while(true){
            System.out.println(p.x + " " + p.y);
            if(p.x == n-1 && p.y == n-1) break;
            p = path[p.x][p.y];
        }
    }
}
