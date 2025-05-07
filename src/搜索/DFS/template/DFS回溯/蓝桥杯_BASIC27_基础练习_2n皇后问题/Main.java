package 搜索.DFS.template.DFS回溯.蓝桥杯_BASIC27_基础练习_2n皇后问题;

/*
问题描述
　　给定一个n*n的棋盘，棋盘中有一些位置不能放皇后。现在要向棋盘中放入n个黑皇后和n个白皇后，使任意的两个黑皇后都不在同一行、同一列或同一条对角线上，任意的两个白皇后都不在同一行、同一列或同一条对角线上。问总共有多少种放法？n小于等于8。
输入格式
　　输入的第一行为一个整数n，表示棋盘的大小。
　　接下来n行，每行n个0或1的整数，如果一个整数为1，表示对应的位置可以放皇后，如果一个整数为0，表示对应的位置不可以放皇后。
输出格式
　　输出一个整数，表示总共有多少种放法。
样例输入
4
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
样例输出
2
样例输入
4
1 0 1 1
1 1 1 1
1 1 1 1
1 1 1 1
样例输出
0
 */

// 确保他们互不冲突，先放置黑皇后再放置白皇后
// 从第一行开始放置，一行一行地寻找合法的位置。这里可以看出，每次放置时的动作都是相似的：定位行，找合法列，放置
// 回溯 + 递归

// 判断一个位置是否合法
// 放置黑皇后的时候是 空而且能放置皇后 并且 这个位置能放置黑皇后
// 放置白皇后的条件是 空而且能放置皇后 && 格子没有被占 && 能放白皇后

import java.util.Scanner;

public class Main {
    private static final int M = 12;
    // board 数组用于存储棋盘的初始状态，0 表示该位置不能放置皇后，1 表示可以放置
    private static int[][] board = new int[M][M];
    // 标记黑皇后和白皇后
    private static int[][] flag_black = new int[M][M];
    private static int[][] flag_white = new int[M][M];
    // 是否放了皇后
    private static int[][] flag = new int[M][M];
    private static int n, ans;

    private static void backTrackBlack(int pb){
        // 黑皇后摆完了，开始摆白的
        if(pb == n){
            backTrackWhite(0);
            return;
        }

        // 一列一列来
        for(int col = 0; col < n; col++){
            // 放不了
            if(board[pb][col] == 0 && flag_black[pb][col] != 0) continue;
            // 放
            flag[pb][col] = 1;
            // 更新附近不能放的情况
            for(int k = 0; k < n; k++){
                flag_black[k][col]++;
                // 主对角线方向的位置（如果在棋盘范围内）在范围内
                if (k - pb + col < n) flag_black[k][k - pb + col]++;
                // 副对角线方向的位置（如果在棋盘范围内）在范围内
                if (k - pb + col >= 0) flag_black[k][col - k + pb]++;
            }

            // 处理下一行
            backTrackBlack(pb + 1);
            // 回溯
            flag_black[pb][col] = 0;
            // 恢复黑皇后的攻击范围标记
            for (int k = 0; k < n; k++) {
                flag_black[k][col]--;
                if (k - pb + col < n) flag_black[k][k - pb + col]--;
                if (k - pb + col >= 0) flag_black[k][col - k + pb]--;
            }
        }
    }

    // 放置白皇后的
    private static void backTrackWhite(int pw){
        // 如果已经处理完所有行，说明白皇后已经放置完毕
        if (pw == n) {
            // 满足条件的方案数量加 1
            ans++;
            return;
        }

        for(int col = 0; col < n; col++){
            if (board[pw][col] == 0 || flag[pw][col] != 0 || flag_white[pw][col] != 0) continue;
            flag[pw][col] = 1;

            for(int k = 0; k < n; k++){
                flag_white[k][col]++;
                // 主对角线方向的位置（如果在棋盘范围内）在范围内
                if (k - pw + col < n) flag_white[k][k - pw + col]++;
                // 副对角线方向的位置（如果在棋盘范围内）在范围内
                if (k - pw + col >= 0) flag_white[k][col - k + pw]++;
            }

            backTrackWhite(col + 1);

            // 回溯
            flag_black[pw][col] = 0;
            // 恢复白皇后的攻击范围标记
            for (int k = 0; k < n; k++) {
                flag_black[k][col]--;
                if (k - pw + col < n) flag_white[k][k - pw + col]--;
                if (k - pw + col >= 0) flag_white[k][col - k + pw]--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ans = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
                flag_black[i][j] = 0;
                flag_white[i][j] = 0;
                flag[i][j] = 0;
            }
        }

        backTrackBlack(0);

        System.out.println(ans);

        sc.close();
    }
}
