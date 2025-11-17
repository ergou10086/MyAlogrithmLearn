package 动态规划.subject.单调队列.P1440_求m区间内的最小值;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

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

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        int[] queue = new int[n + 2];
        int h = 1, t = 0;

        bw.write("0\n");

        // 处理j=1到n-1（因为i从2到n，对应j=i-1）
        for(int j = 1; j < n; j++) {
            // 1. 维护单调递增队列：删除队尾比当前元素大的下标
            while(h <= t && a[queue[t]] >= a[j]){
                t--;
            }

            // 2. 当前元素下标入队
            queue[++t] = j;

            // 3. 窗口左边界：i = j+1，左边界 = max(1, (j+1)-m) = j+1 -m（若j+1-m >=1）
            int left = (j + 1) - m;
            // 只有左边界>1时，才可能有元素超出窗口
            if (left > 1){
                if(queue[h] < left){
                    h++;
                }
            }

            // 4. 输出第i=j+1项的答案（队头是窗口最小值）
            bw.write(a[queue[h]] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
