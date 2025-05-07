package 动态规划.template.单调队列.P1886_滑动窗口_模板_单调队列;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 快读类
class FastReader_2 {
    BufferedReader br;
    StringTokenizer st;

    public FastReader_2() {
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

public class Main_N {
    static final int N = 1000010;
    static int[] a = new int[N];

    public static void main(String[] args) throws IOException {
        FastReader_2 fr = new FastReader_2();
        FastWriter fw = new FastWriter();

        int n = fr.nextInt();
        int k = fr.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = fr.nextInt();
        }

        // 维护窗口最小值
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            while (!q.isEmpty() && a[q.peekLast()] >= a[i]) {
                q.pollLast();
            }
            q.offerLast(i);
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }
            if (i >= k) {
                fw.write(a[q.peekFirst()]);
            }
        }
        fw.newLine();
        fw.flush();

        // 维护窗口最大值
        q.clear();
        for (int i = 1; i <= n; i++) {
            while (!q.isEmpty() && a[q.peekLast()] <= a[i]) {
                q.pollLast();
            }
            q.offerLast(i);
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }
            if (i >= k) {
                fw.write(a[q.peekFirst()]);
            }
        }
        fw.newLine();
        fw.flush();
    }
}