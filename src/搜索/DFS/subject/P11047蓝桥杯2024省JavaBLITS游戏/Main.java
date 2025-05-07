package 搜索.DFS.subject.P11047蓝桥杯2024省JavaBLITS游戏;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    final static int[][][][] place_way = {
            // L
            {
                    {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, // 0
                    {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, // 正向90
                    {{0, 0}, {0, 1}, {1, 1}, {2, 1}}, // 180
                    {{0, 2}, {1, 0}, {1, 1}, {1, 2}}  // 反向90
            },
            // I
            {
                    {{0, 0}, {1, 0}, {2, 0}, {3, 0}}, // 0
                    {{0, 0}, {0, 1}, {0, 2}, {0, 3}},  // 180
                    {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
                    {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            },
            // T
            {
                    {{0, 0}, {0, 1}, {0, 2}, {1, 1}},  // 0
                    {{0, 0}, {1, 0}, {2, 0}, {1, -1}}, // 正向90
                    {{0, 1}, {1, 0}, {1, 1}, {1, 2}}, // 180
                    {{0, 0}, {1, 0}, {2, 0}, {1, 1}}  // 反向90
            },
            // S
            {
                    {{0, 0}, {0, 1}, {1, 1}, {1, 2}}, // 0
                    {{0, 1}, {1, 0}, {1, 1}, {2, 0}}, // 90
                    {{0, 0}, {0, 1}, {1, 1}, {1, 2}}, // 180
                    {{0, 1}, {1, 0}, {1, 1}, {2, 0}}  // 270
            }
    };
    private int[][] g = new int[55][55];
    private int n;

    // 判断是否在网格的有效范围内
    private boolean inBound(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }

    private boolean dfs(int x, int y, int state) {
        if (state == 4) return true;

        // 纵坐标超过大小，说明这行不够放，去下一行放
        if (y > n) {
            return dfs(x + 1, 1, state);
        }

        // 横坐标超了就是另一回事了，就说明放不下
        if (x > n) return false;

        // 如果当前格子为0，跳过
        if (g[x][y] == 0) {
            return dfs(x, y + 1, state);
        }

        // 四种形状遍历
        for (int i = 0; i < 4; i++) {
            // 这种方块的形状试过了
            if (state == i) continue;

            // 旋转状态遍历
            for (int j = 0; j < 4; j++) {
                boolean valid = true;

                // 检查方块的四种情况的坐标是否都在
                for (int k = 0; k < 4; k++) {
                    int delta_x = x + place_way[i][j][k][0];
                    int delta_y = y + place_way[i][j][k][1];
                    if (!inBound(delta_x, delta_y) || g[delta_x][delta_y] != 1) {
                        valid = false;
                        break;
                    }
                }

                // 当前放得下
                if (valid) {
                    for (int k = 0; k < 4; k++) {
                        int delta_x = x + place_way[i][j][k][0];
                        int delta_y = y + place_way[i][j][k][1];
                        g[delta_x][delta_y] = 0;   // 放置
                    }

                    // 下一个位置尝试
                    if (dfs(x, y + 1, state + 1)) return true;

                    // 回溯
                    for (int k = 0; k < 4; k++) {
                        int delta_x = x + place_way[i][j][k][0];
                        int delta_y = y + place_way[i][j][k][1];
                        g[delta_x][delta_y] = 1;   // 释放
                    }
                }
            }
        }

        return dfs(x, y + 1, state);
    }

    public Solutions() {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    g[i][j] = sc.nextInt();
                }
            }
            if (dfs(1, 1, 0)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
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

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}