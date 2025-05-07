import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    static class Item {
        long wei, num;
        public Item(long wei, long num) {
            this.wei = wei;
            this.num = num;
        }
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Item)) return false;
            Item p = (Item) obj;
            return this.wei == p.wei && this.num == p.num;
        }
        @Override
        public int hashCode() {
            return Objects.hash(wei, num);
        }
    }

    public Solutions() {
        FastReader fs = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);
        int n = fs.nextInt();
        int k = fs.nextInt();
        int l = fs.nextInt();
        long[] w = new long[n];

        for (int i = 0; i < n; i++) w[i] = fs.nextInt();

        // 小根堆，保证每次弹出最小重量
        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.wei));
        Set<Item> visited = new HashSet<>();
        Set<Long> answerSet = new HashSet<>();

        // 初始化：每种物品单独放入背包
        for (int i = 0; i < n; i++) {
            Item item = new Item(w[i], 1);
            if (visited.add(item)) {
                pq.offer(item);
            }
        }

        int count = 0;
        long answer = -1;
        while (!pq.isEmpty()) {
            Item cur = pq.poll();
            // 只统计物品数>=k且重量未计入答案的情况
            if (cur.num >= k && answerSet.add(cur.wei)) {
                count++;
                if (count == l) {
                    answer = cur.wei;
                    break;
                }
            }
            // 无论是否计入答案都要扩展
            for (int i = 0; i < n; i++) {
                Item next = new Item(cur.wei + w[i], cur.num + 1);
                if (visited.add(next)) {
                    pq.offer(next);
                }
            }
        }
        pw.println(answer);
        pw.flush();
        pw.close();
    }

    static class FastReader {
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