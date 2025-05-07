package 搜索.DFS.template.双向DFS.P10484_送礼物;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int n, m, ans, cnt;
    private static int[] g = new int[46];
    private static int[] w = new int[1 << 23];

    private static void dfs1(int u, int s) {
        if (u == n / 2) {
            w[cnt++] = s;
            return;
        }
        dfs1(u + 1, s); // 不选g[u]
        if ((long)g[u] <= m - s) { // 防止整数溢出
            dfs1(u + 1, s + g[u]); // 选g[u]
        }
    }

    private static void dfs2(int u, int s) {
        if (u == n) {
            // 使用二分查找找到不大于m-s的最大值
            int left = 0, right = cnt - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (w[mid] <= m - s) {
                    ans = Math.max(ans, w[mid] + s);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return;
        }
        dfs2(u + 1, s); // 不选g[u]
        if ((long)g[u] <= m - s) { // 防止整数溢出
            dfs2(u + 1, s + g[u]); // 选g[u]
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            g[i] = scanner.nextInt();
        }

        // 优化搜索顺序：从大到小排序
        Arrays.sort(g, 0, n);
        reverse(g, 0, n - 1);

        dfs1(0, 0); // 搜前半部分
        Arrays.sort(w, 0, cnt);
        cnt = unique(w, cnt); // 去重

        dfs2(n / 2, 0); // 搜后半部分
        System.out.println(ans);
        scanner.close();
    }

    // 数组反转
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // 数组去重
    private static int unique(int[] arr, int len) {
        if (len == 0) return 0;
        int j = 0;
        for (int i = 1; i < len; i++) {
            if (arr[i] != arr[j]) {
                arr[++j] = arr[i];
            }
        }
        return j + 1;
    }
}