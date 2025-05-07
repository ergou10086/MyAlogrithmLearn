package 基础算法和其他.模拟.P1086_花生采摘;

import java.util.*;

public class Main {
    static int n, m, k, res = 0, w = 0; // 这里初始化 w 为 0
    static int[][] a = new int[23][23];
    // 大根堆
    static PriorityQueue<Field> q = new PriorityQueue<>(new Comparator<Field>() {
        @Override
        public int compare(Field f1, Field f2) {
            return f2.huas_num - f1.huas_num; // 大根堆，按huas_num降序排列
        }
    });

    public static class Field {
        int huas_num, x, y;

        Field(int huas_num, int x, int y) {
            this.huas_num = huas_num;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                a[i][j] = sc.nextInt();
                if (a[i][j] != 0) {
                    q.offer(new Field(a[i][j], i, j)); // 存储坐标从 1 开始
                }
            }
        }

        Field filedpr = q.poll();
        int ele = filedpr.huas_num;
        int x = filedpr.x;
        int y = filedpr.y;
        w += x + 1;

        while (w + x <= k) {
            res += ele;
            if (q.isEmpty()) break;
            filedpr = q.poll();
            ele = filedpr.huas_num; // 更新当前的花生数量
            w += Math.abs(filedpr.x - x) + Math.abs(filedpr.y - y) + 1; // 注意这里+1
            x = filedpr.x; // 更新当前田地的位置
            y = filedpr.y;
        }

        System.out.println(res);
    }
}
