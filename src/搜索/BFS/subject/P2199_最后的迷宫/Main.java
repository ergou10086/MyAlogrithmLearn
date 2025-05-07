package 搜索.BFS.subject.P2199_最后的迷宫;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private final int N = 20002;
    private int n, m, sx, sy, ex, ey;
    private char[] map = new char[N];
    private boolean[] visited = new boolean[N];
    private int[] dx_8 = {1, 1, -1, 1, 0, -1, 0, -1};
    private int[] dy_8 = {0, 1, 0, -1, 1, 1, -1, -1};
    private int[] dx_4 = {0, 1, 0, -1};
    private int[] dy_4 = {1, 0, -1, 0};

    private class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    private boolean is_valid(int x, int y) {
        if (x < 1 || x > n || y < 1 || y > m) return false;
        return map[(x - 1) * m + y] != 'X';
    }

    private boolean can_be_vision(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int delta_x = x + dx_8[i];
            int delta_y = y + dy_8[i];
            while (is_valid(delta_x, delta_y)) {
                if (delta_x == ex && delta_y == ey) return true;
                delta_x += dx_8[i];
                delta_y += dy_8[i];
            }
        }
        return false;
    }

    private boolean bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy, 0));
        visited[(sx - 1) * m + sy] = true;

        boolean flag = false;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == ex && node.y == ey || can_be_vision(node.x, node.y)) {
                System.out.println(node.step);
                flag = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int delta_x = node.x + dx_4[i];
                int delta_y = node.y + dy_4[i];
                if (is_valid(delta_x, delta_y) && !visited[(delta_x - 1) * m + delta_y]) {
                    queue.add(new Node(delta_x, delta_y, node.step + 1));
                    visited[(delta_x - 1) * m + delta_y] = true;
                }
            }
        }

        return flag;
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            String row = sc.next();
            for (int j = 1; j <= m; j++) {
                map[(i - 1) * m + j] = row.charAt(j - 1);
            }
        }

        while (true) {
            ex = sc.nextInt();
            ey = sc.nextInt();
            sx = sc.nextInt();
            sy = sc.nextInt();

            if (sx == 0 && sy == 0 && ex == 0 && ey == 0) return;

            Arrays.fill(visited, false); // 重置 visited 数组
            boolean points = bfs();

            if (!points) System.out.println("Poor Harry");
        }
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}