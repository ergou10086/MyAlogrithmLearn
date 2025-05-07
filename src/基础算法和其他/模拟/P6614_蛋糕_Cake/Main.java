package 基础算法和其他.模拟.P6614_蛋糕_Cake;

import java.util.*;

// 当k很大的时候，函数就相当于一条平行于纵轴的直线
// 所以左边满足 n * (a / (a + b))，右边满足 n * (b / (a + b))
// 那么k = 10^12情况下一定有合法解
// 而此时x0的大小就是分割开左边点和右边点的那个分界线，也就是n * (a / (a + b))
// 那么就是第a个点

public class Main {
    // 由于点之间排布是又顺序的，要排序
    private static class Node implements Comparable<Node>{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        // 这里的 y 从大到小是因为斜线上的点属于左边，那么它下面点就在右边了。
        @Override
        public int compareTo(Node o) {
            if(this.x != o.x){
                return Integer.compare(this.x, o.x);
            }else{
                return Integer.compare(o.y, this.y); // 让 y 从大到小排序
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        long K = (long) Math.pow(10, 12);
        int index = n * a / (a + b);

        Node[] pudding = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            pudding[i] = new Node(x, y);
        }

        Arrays.sort(pudding, 1, n + 1);

        System.out.println(K + " " + pudding[index].x + " " + pudding[index].y);

        scanner.close();
    }
}