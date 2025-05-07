package 搜索.BFS.subject.P12253_蓝桥杯2024国JavaB_宝塔;

import java.util.*;

public class Main {
    private static int[] dx1 = {2, 2, 1, 3, 3};
    private static int[] dx2 = {1, 4, 2, 2, 3};
    private static int[] dy1 = {2, 2, 3, 2, 1};
    private static int[] dy2 = {3, 3, 1, 2, 4};

    static class State {
        int[][] board = new int[5][5];
        int[][] row = new int[5][6]; // 列使用标记
        int[][] col = new int[5][6]; // 行使用标记
        int x, y;

        State() {}

        // 深拷贝构造函数
        State(State other) {
            for (int i = 0; i < 5; i++) {
                this.board[i] = Arrays.copyOf(other.board[i], 5);
                this.row[i] = Arrays.copyOf(other.row[i], 6);
                this.col[i] = Arrays.copyOf(other.col[i], 6);
            }
            this.x = other.x;
            this.y = other.y;
        }
    }

    private static boolean checkrow(int[][] board, int rowId) {
        int max = board[rowId][0];
        int cnt = 1;
        for (int i = 1; i < 5; i++) {
            if (board[rowId][i] > max) {
                max = board[rowId][i];
                cnt++;
            }
        }
        if (cnt != dy1[rowId]) return false;

        max = board[rowId][4];
        cnt = 1;
        for (int i = 3; i >= 0; i--) {
            if (board[rowId][i] > max) {
                max = board[rowId][i];
                cnt++;
            }
        }
        return cnt == dy2[rowId];
    }

    private static boolean checkcol(int[][] board, int colId) {
        int max = board[0][colId];
        int cnt = 1;
        for (int i = 1; i < 5; i++) {
            if (board[i][colId] > max) {
                max = board[i][colId];
                cnt++;
            }
        }
        if (cnt != dx1[colId]) return false;

        max = board[4][colId];
        cnt = 1;
        for (int i = 3; i >= 0; i--) {
            if (board[i][colId] > max) {
                max = board[i][colId];
                cnt++;
            }
        }
        return cnt == dx2[colId];
    }

    public static void main(String[] args) {
        Queue<State> queue = new LinkedList<>();
        State init = new State();
        queue.add(init);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // 终止条件检查
            if (current.x >= 5) {
                boolean valid = true;
                for (int j = 0; j < 5; j++) {
                    if (!checkcol(current.board, j)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    printSolution(current.board);
                    return;
                }
                continue;
            }

            if (current.y >= 5) {
                if (checkrow(current.board, current.x)) {
                    State next = new State(current);
                    next.x++;
                    next.y = 0;
                    queue.add(next);
                }
                continue;
            }

            // 尝试所有可能的数字
            for (int i = 1; i <= 5; i++) {
                if (current.row[current.y][i] == 0 &&
                        current.col[current.x][i] == 0) {

                    State next = new State(current);
                    next.board[next.x][next.y] = i;
                    next.row[next.y][i] = 1;
                    next.col[next.x][i] = 1;
                    next.y++;

                    queue.add(next);
                }
            }
        }

        System.out.println(-1);
    }

    private static void printSolution(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }
        System.out.println(sb);
    }
}