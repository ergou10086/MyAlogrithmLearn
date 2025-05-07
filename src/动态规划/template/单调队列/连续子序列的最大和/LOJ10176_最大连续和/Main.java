package 动态规划.template.单调队列.连续子序列的最大和.LOJ10176_最大连续和;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] s = new int[n + 1];   // 前缀和
        int[] q = new int[n + 1];  // 单调队列
        int[] f = new int[n + 1];  // dp数组

        for (int i = 1; i <= n; i++) {
            s[i] = scanner.nextInt();
            s[i] += s[i - 1];   // 前缀和
        }

        int head = 1, tail = 0;
        for (int i = 1; i <= n; i++) {
            while(head <= tail && s[q[tail]] >= s[i - 1]) tail--;     // 队尾出队(队列不空且新元素更优)
            q[++tail] = i - 1;        // 队尾入队(存储下标 方便判断队头出队)
            if(q[head] < i - m) head++;    // 队头出队(队头元素滑出窗口)  q[h]不在窗口[i-m,i]内
            f[i] = s[i] - s[q[head]];   // 更新答案（前缀和求[i-m,i]的和）
        }

        int res = 0;
        for (int i = 1; i <= n; i++) res = Math.max(res, f[i]);

        System.out.println(res);

        scanner.close();
    }
}


