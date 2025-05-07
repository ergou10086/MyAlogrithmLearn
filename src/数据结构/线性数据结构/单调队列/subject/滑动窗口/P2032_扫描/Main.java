package 数据结构.线性数据结构.单调队列.subject.滑动窗口.P2032_扫描;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try {
            new Solutions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solutions{
    private int n, k, ans;
    private int[] num;
    private int[] queue;   // 存储下标

    public Solutions() throws IOException {
        FastReader fs = new FastReader();
        n = fs.nextInt();
        k = fs.nextInt();
        num = new int[n + 1];
        queue = new int[100006];
        for (int i = 1; i <= n; i++)
            num[i] = fs.nextInt();

        int head = 1, tail = 0;

        for (int i = 1; i <= n; i++) {
            // 新数比队尾大，队尾出队
            while(head <= tail && num[queue[tail]] <= num[i]) tail--;
            queue[++tail] = i;   // 下标再入队
            if(queue[head] < i - k + 1) head++;   // 队头长了，短一个
            if (i >= k) System.out.println(num[queue[head]]);
        }
    }

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
}
