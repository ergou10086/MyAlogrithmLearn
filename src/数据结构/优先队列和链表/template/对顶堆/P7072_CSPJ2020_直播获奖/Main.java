package 数据结构.优先队列和链表.template.对顶堆.P7072_CSPJ2020_直播获奖;

// 对顶堆可以动态维护一个序列上第k大的数
// 由一个大根堆和一个小根堆组成，小根堆维护前k大的数，大根堆维护比第k大的数小的数
// 把序列以分为2

// 插入：插入元素 小于等于 小根堆堆顶元素，插入小根堆，否则插入大根堆
// 维护：当小根堆的大小大于k时，不断从小根堆对顶元素取出并插入大根堆，直到小根堆的大小等于k；
//      反之，则不断从不断从大根堆对顶元素取出并插入小根堆，直到小根堆的大小等于k；
// 查询第k大元素：小根堆对顶
// 删除第k大元素：删除小根堆对顶元素

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();

        // Java 中的 PriorityQueue 默认是小根堆，因此创建大根堆时使用 Collections.reverseOrder()。
        PriorityQueue<Integer> a = new PriorityQueue<>(Collections.reverseOrder()); // 大根堆
        PriorityQueue<Integer> b = new PriorityQueue<>(); // 小根堆

        for(int i = 1; i <= n; i++) {
            int x = scanner.nextInt();
            if (b.isEmpty() || x >= b.peek()) {
                b.offer(x); // b空或新元素x比b堆顶大，插入小根堆
            } else {
                a.offer(x);
            }

            // 第k大
            int k = Math.max(1, i * w / 100);
            // 不断调整出第k大的数
            while (b.size() > k) {
                a.offer(b.poll());
            }
            while (b.size() < k) {
                b.offer(a.poll());
            }
            System.out.print(b.peek() + " "); // 取值
        }
        scanner.close();
    }
}
