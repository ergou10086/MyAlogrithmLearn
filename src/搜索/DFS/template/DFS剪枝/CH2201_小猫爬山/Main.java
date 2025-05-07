package 搜索.DFS.template.DFS剪枝.CH2201_小猫爬山;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static int n, w, ans = 33, cnt;
    private static int[] cat;
    private static int[] sum = new int[33];   // 状态重量

    private static void dfs(int u) {
        if (cnt >= ans) return;  // 最优性剪枝

        if (u == n) {
            ans = cnt;
            return;
        }

        for (int i = 0; i < cnt; i++) {
            if (sum[i] + cat[u] <= w) {
                sum[i] += cat[u];
                dfs(u + 1);
                sum[i] -= cat[u];
            }
        }

        // 新开一辆车
        sum[cnt] = cat[u];
        cnt++;
        dfs(u + 1);
        cnt--;
        sum[cnt] = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();    // n只猫
        w = sc.nextInt();    // 缆车最大承重量为 W

        cat = new int[n];
        for (int i = 0; i < n; i++) {
            cat[i] = sc.nextInt();
        }

        // 将猫的重量从大到小排序
        Integer[] wrapperArr = Arrays.stream(cat).boxed().toArray(Integer[]::new);
        Arrays.sort(wrapperArr, Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            cat[i] = wrapperArr[i];
        }

        dfs(0);
        System.out.println(ans);
        sc.close();
    }
}