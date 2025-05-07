package 搜索.DFS.subject.P12253_蓝桥杯2024国JavaB_宝塔;

public class Main {
    public static void main(String[] args) {
        // 实际上这题可以用bfs去搜
        // 但是 这不就是数独吗
        // 这里展示用 dfs 的做法，类似八皇后
        new Solutions();
    }
}

class Solutions{
    private int dx1[] = {2, 2, 1, 3, 3}; // 列：从上到下可见数
    private int dx2[] = {1, 4, 2, 2, 3}; // 列：从下到上可见数
    private int dy1[] = {2, 2, 3, 2, 1}; // 行：从左到右可见数
    private int dy2[] = {3, 3, 1, 2, 4}; // 行：从右到左可见数

    private int[][] board = new int[5][5];    // 棋盘
    private int[][] row = new int[5][6];   // 跟纵每行数字
    private int[][] col = new int[5][6];   // 跟踪每列数字


    private boolean checkrow(int rowId) {
        // 检查从左到右可见数
        int max = board[rowId][0];
        int cnt = 1;
        for(int i = 1; i < 5; i++){
            if(board[rowId][i] > max){
                max = board[rowId][i];
                cnt++;
            }
        }
        if(cnt != dy1[rowId]) return false;

        // 检查从右到左可见数
        max = board[rowId][4];
        int cnt2 = 1;
        for(int i = 3; i >= 0; i--){
            if(board[rowId][i] > max){
                max = board[rowId][i];
                cnt2++;
            }
        }
        return cnt2 == dy2[rowId];
    }

    private boolean checkcol(int colId) {
        // 检查从上到下可见数
        int max = board[0][colId];
        int cnt = 1;
        for (int i = 1; i < 5; i++) {
            if (board[i][colId] > max) {
                max = board[i][colId];
                cnt++;
            }
        }
        if (cnt != dx1[colId]) return false;

        // 检查从下到上可见数
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

    private boolean dfs(int x, int y){
        if(x >= 5){
            // 检查所有列
            for (int j = 0; j < 5; j++) {
                if (!checkcol(j)) return false;
            }
            return true;
        }
        if (y >= 5) {
            // 检查当前行并进入下一行
            if (!checkrow(x)) return false;
            return dfs(x + 1, 0);
        }

        // 尝试填入数字
        for(int i = 1; i <= 5; i++){
            if(row[y][i] == 0 && col[x][i] == 0){
                row[y][i] = 1; // 标记列y已使用i
                col[x][i] = 1; // 标记行x已使用i
                board[x][y] = i;

                if (dfs(x, y + 1)) {
                    return true;
                }

                // 回溯
                row[y][i] = 0;
                col[x][i] = 0;
            }
        }
        return false;
    }


    public Solutions(){
        if (dfs(0, 0)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    sb.append(board[i][j]);
                }
            }
            System.out.println(sb);
        }else {
            System.out.println(-1);
        }

        // 4352115243243153215451432
    }
}