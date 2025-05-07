package 数据结构.优先队列和链表.subject.P1631_序列合并;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        // 读取输入数据
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        sc.close();

        // 对数组排序
        Arrays.sort(a);
        Arrays.sort(b);

        // 使用优先队列（小根堆）
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        // 初始化堆：将每个a[i]与b[0]的和加入堆
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{a[i] + b[0], i, 0});
        }

        // 查找前 n 小的值
        StringBuilder sb = new StringBuilder();
        while(n-- > 0){
            int[] curr = pq.poll();
            int sum = curr[0];
            int aIdx = curr[1];
            int bIdx = curr[2];

            sb.append(sum).append(" ");

            // 如果当前b索引+1有效，则将下一个和加入堆
            if (bIdx + 1 < b.length) {
                pq.offer(new int[]{a[aIdx] + b[bIdx + 1], aIdx, bIdx + 1});
            }
        }

        System.out.println(sb.toString().trim());
    }
}
