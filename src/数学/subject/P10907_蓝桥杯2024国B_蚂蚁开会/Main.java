package 数学.subject.P10907_蓝桥杯2024国B_蚂蚁开会;

// 枚举点，逐个判断吗，O^3了都
// 遍历每条线段上的整数点，出现的次数存到HashMap中，再统计交点的数量

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 必须重写equals和hashCode方法，以便在HashMap中正确使用
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    private int n;
    private int[] ux = new int[502];
    private int[] uy = new int[502];
    private int[] vx = new int[502];
    private int[] vy = new int[502];
    private Map<Point, Integer> pointCounts = new HashMap<>();

    // 辗转相除法gcd
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 记录每条线段经过的整数点
    private void countPoints(int index) {
        // 起点终点
        int xup = ux[index];
        int yup = uy[index];
        int xvp = vx[index];
        int yvp = vy[index];

        // 求方向向量，并简化
        int dx = xvp - xup, dy = yvp - yup;
        int step = gcd(Math.abs(dx), Math.abs(dy));
        if (step != 0) {  // 避免除以0
            dx /= step;
            dy /= step;
        }

        // 用参数方程的性质
        // 遍历线段上的所有整数点
        for(int i = 0; ; i++){
            int x = xup + i * dx;
            int y = yup + i * dy;

            // 记录这个点
            pointCounts.merge(new Point(x, y), 1, Integer::sum);

            // 到达终点时停止
            if ((dx >= 0 && x >= xvp) || (dx < 0 && x <= xvp)) {
                if ((dy >= 0 && y >= yvp) || (dy < 0 && y <= yvp)) {
                    break;
                }
            }
        }
    }

    public void solve() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            ux[i] = sc.nextInt();
            uy[i] = sc.nextInt();
            vx[i] = sc.nextInt();
            vy[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++) countPoints(i);

        int result = 0;
        for (int count : pointCounts.values()) {
            if (count >= 2) {
                result++;
            }
        }

        System.out.println(result);
    }

    public Solutions(){
        solve();
    }


    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
