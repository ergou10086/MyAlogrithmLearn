package 基础算法和其他.排序.template.中位数_距离和最小的值.二维_曼哈顿距离.CF1468B;

// 曼哈顿距离就是平面上d(i, j) = |x_i - x_j| + |y_i - y_j|，两点的绝对轴距总和
// 可发现，改变x坐标，y坐标的距离和不变，可转换为一维问题，分别求出x轴和y轴的最优点，相乘就是ans

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

class Eastern{
    public Eastern() {
        FastReader fs = new FastReader();
        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int n1 = 1010;
            int[] a = new int[n1];
            int[] b = new int[n1];
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
                b[i] = fs.nextInt();
            }
            Arrays.sort(a);
            Arrays.sort(b);

            int x = a[n /2] - a[(n -1)/2] + 1;
            int y = b[n /2] - b[(n -1)/2] + 1;
            long ans = (long) x * y;
            System.out.println(ans);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new Eastern();
    }
}
