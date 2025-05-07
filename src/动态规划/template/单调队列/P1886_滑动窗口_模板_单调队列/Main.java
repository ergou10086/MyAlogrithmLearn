package 动态规划.template.单调队列.P1886_滑动窗口_模板_单调队列;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 快读
class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

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

    int nextInt() {
        return Integer.parseInt(next());
    }
}

class Solution {
    // 以求最小值为例，f[i]表示以i为结尾的窗口中的最小值，k为窗口大小
    // f[i] = min(a[j]), i - k + 1 <= j <= i

    // 单调队列：队尾进队出队，队头出队，维护子序列的单调性
    // 队尾出队的条件，队列不空并且新元素更优，队中旧元素队尾出队
    // 每个元素必然从队尾进队一次
    // 队头出队的条件是队头元素滑出了窗口
    // 队列中存储的是元素的下标，方便判断队头出队

    private int[] arr = new int[1000086];
    private int[] queue = new int[1000086];
    private int n;
    private int k;

    // 维护窗口的最小值
    private void Solution_Min() throws IOException {
        // 利用BufferedWriter进行快速输出
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int head = 1, tail = 0; // 头尾指针初值
        for (int i = 1; i <= n; i++) {
            // 队列不空且新元素更优，队尾出队，tail--
            while (head <= tail && arr[queue[tail]] >= arr[i])
                tail--;
            // 队尾入队，存储的下标
            queue[++tail] = i;
            // 队头元素滑出窗口，出队
            if (queue[head] < i - k + 1)
                head++;
            // 输出最值
            if (i >= k) {
                bw.write(arr[queue[head]] + " ");
            }
        }
        bw.newLine();
        bw.flush();
    }

    // 维护窗口的最大值
    private void Solution_Max() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int head = 1, tail = 0;
        for (int i = 1; i <= n; i++) {
            while (head <= tail && arr[queue[tail]] <= arr[i])
                tail--;
            queue[++tail] = i;
            if (queue[head] < i - k + 1)
                head++;
            if (i >= k) {
                bw.write(arr[queue[head]] + " ");
            }
        }
        bw.newLine();
        bw.flush();
    }

    public Solution() throws IOException {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        k = sc.nextInt();

        for (int i = 1; i <= n; i++)
            arr[i] = sc.nextInt();

        Solution_Min();
        Solution_Max();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution();
    }
}