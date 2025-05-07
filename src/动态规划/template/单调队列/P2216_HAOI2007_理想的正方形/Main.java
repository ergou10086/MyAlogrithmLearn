package 动态规划.template.单调队列.P2216_HAOI2007_理想的正方形;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 快读类
class FastReader {
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

    int nextInt() {
        return Integer.parseInt(next());
    }
}

// 快写类
class FastWriter {
    BufferedWriter bw;

    public FastWriter() {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    void write(int num) throws IOException {
        bw.write(num + " ");
    }

    void newLine() throws IOException {
        bw.newLine();
    }

    void flush() throws IOException {
        bw.flush();
    }
}

// 二维滑动窗口
// 分步做，把矩形区的最值先按行压缩到到一格存储，后按列压缩到一格存储
// 枚举行，横向滑动窗口，把每行的窗口最值存储在 maxv[i][j], minv[i][j]
// 枚举列，竖向滑动窗口，把每列的窗口最值存储在 c[i], d[i]

class Solution{
    private final int N = 1002;
    private int n, m, k;
    private int[][] w = new int[N][N];
    // 分别用于存储在行方向上滑动窗口后每行特定列范围内的最小值和最大值。
    private int[][] min_ele = new int[N][N];
    private int[][] max_ele = new int[N][N];
    private int[] queue = new int[N];
    private int[] a = new int[N];   // a数组用于临时存储第i行中第j - k + 1到j列的最大值
    private int[] b = new int[N];   // b数组用于临时存储第i行中第j - k + 1到j列的最小值
    private int[] c = new int[N];   // c数组是基于a数组，在列方向上进行滑动窗口操作后，第i - k + 1到i行，第j - k + 1到j列这个子矩阵范围内的最大值。
    private int[] d = new int[N];   // d数组是基于b数组，在列方向上进行滑动窗口操作后，第i - k + 1到i行，第j - k + 1到j列这个子矩阵范围内的最小值

    private void get_min(int[] arr, int[] b, int m){
        int head = 1, tail = 0;
        Arrays.fill(queue, 0);
        for(int i = 1; i <= m; i++){
            while(head <= tail && arr[queue[tail]] <= arr[i]) tail--;
            queue[++tail] = i;
            if(queue[head] < i - k + 1) head++;
            b[i] = arr[queue[head]];
        }
    }

    private void get_max(int[] arr, int[] b, int m){
        int head = 1, tail = 0;
        Arrays.fill(queue, 0);
        for(int i = 1; i <= m; i++){
            while(head <= tail && arr[queue[tail]] >= arr[i]) tail--;
            queue[++tail] = i;
            if(queue[head] < i - k + 1) head++;
            b[i] = arr[queue[head]];
        }
    }

    public Solution() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                w[i][j] = sc.nextInt();
            }
        }

        int res = Integer.MAX_VALUE;
        // 枚举行，在横向上对每一行进行了滑动窗口操作
        for(int i = 1; i <= n; i++){
            get_max(w[i], max_ele[i], m);
            get_min(w[i], min_ele[i], m);
        }
        // 枚举列
        for (int j = k; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                // 对于每一列j，中每行最大值max_ele[i][j]和最小值min_ele[i][j]放入a，b
                a[i] = max_ele[i][j];
                b[i] = min_ele[i][j];
            }
            // 在竖向上对每一列进行了滑动窗口操作
            get_max(a, c, n);
            get_min(b, d, n);
            for(int i = k; i <= n; i++) res = Math.min(res, c[i] - d[i]);
        }
        System.out.println(res);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
