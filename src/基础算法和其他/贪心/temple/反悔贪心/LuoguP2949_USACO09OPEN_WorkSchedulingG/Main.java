package 基础算法和其他.贪心.temple.反悔贪心.LuoguP2949_USACO09OPEN_WorkSchedulingG;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 先对n项工作按截止日期排序，然后枚举每项工作，如果当前有时间做，就做，利润压入堆
// 如果当前工作和前项工作的截止时间相同，就比较，如果当前利润更大就放弃堆顶的工作（反悔）
class Solutions {
    class Node implements Comparable<Node> {
        int ddl, p;   // 截止日期和利润

        public Node(int ddl, int p) {
            this.ddl = ddl;
            this.p = p;
        }

        // 按照截止日期排序
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.ddl, o.ddl);
        }
    }

    public Solutions() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int ddl = scanner.nextInt();
            int p = scanner.nextInt();
            nodes[i] = new Node(ddl, p);
        }
        Arrays.sort(nodes);

        long ans = 0;
        // 小根堆存利润，反悔贪心实际上就是堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            // 如果当前工作和前项工作的截止时间相同
            if (nodes[i].ddl == pq.size()) {
                // 如果当前利润更大就放弃堆顶的工作
                if (!pq.isEmpty() && nodes[i].p > pq.peek()) {
                    ans -= pq.poll();
                    pq.offer(nodes[i].p);
                    ans += nodes[i].p;
                }
            } else {
                pq.offer(nodes[i].p);
                ans += nodes[i].p;
            }
        }

        System.out.println(ans);
        scanner.close();
    }
}