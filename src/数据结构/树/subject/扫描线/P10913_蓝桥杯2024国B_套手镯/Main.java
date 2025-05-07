package 数据结构.树.subject.扫描线.P10913_蓝桥杯2024国B_套手镯;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    // 手镯结构体
    class Circle implements Comparable<Circle> {
        int x, y, r;  // 圆心位置和半径

        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public int compareTo(Circle a) {
            return (this.x + this.r) - (a.x + a.r);
        }
    }

    private int n, w, h, res;
    private int[] db;    // 记录每个圆的底边
    private Circle[] circles;

    private int smx(int t) {
        // 装圆左端点的队列，用堆为了在一个y轴情况下是从左向右遍历
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            // 够不到的和超出去的不要，只要完全在里面的
            if (circles[i].y - circles[i].r < t || circles[i].y + circles[i].r > h + t) continue;
            // 长宽装不下你这个圆
            if (2 * circles[i].r > w || 2 * circles[i].r > h) continue;

            // 将圆按照右端点升序排序
            int rigEd = circles[i].x + circles[i].r;
            // 如果直径比纸板的宽或者高大，这个圆是要不了的，弹出
            while (!pq.isEmpty() && rigEd - pq.peek() > w) pq.poll();
            int leftEd = circles[i].x - circles[i].r;
            pq.offer(leftEd);
            ans = Math.max(ans, pq.size());
        }

        return ans;
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        w = sc.nextInt();
        h = sc.nextInt();

        circles = new Circle[n];
        db = new int[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int r = sc.nextInt();
            circles[i] = new Circle(x, y, r);
            // 圆的底切线的y坐标
            db[i] = circles[i].y - circles[i].r;
        }

        // 只对前 n 个元素排序
        Arrays.sort(circles, 0, n);
        Arrays.sort(db, 0, n);

        for (int i = 0; i < n; i++) res = Math.max(res, smx(db[i]));
        // 因为纸板可以换个方向放
        int temp = w;
        w = h;
        h = temp;
        for (int i = 0; i < n; i++) res = Math.max(res, smx(db[i]));

        System.out.println(res);
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}