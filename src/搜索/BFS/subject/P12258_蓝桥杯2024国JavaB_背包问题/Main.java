package 搜索.BFS.subject.P12258_蓝桥杯2024国JavaB_背包问题;

import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        long wei;
        int num;
        Pair(long wei, int num) {
            this.wei = wei;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);
        int l = Integer.parseInt(firstLine[2]);

        // 输入并去重物品重量
        Set<Long> ws = new HashSet<>();
        String[] weights = br.readLine().split(" ");
        for (String s : weights) {
            ws.add(Long.parseLong(s));
        }
        List<Long> uniqueW = new ArrayList<>(ws);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.wei, b.wei));
        Map<Long, Integer> maxNum = new HashMap<>();
        Set<Long> answerSet = new HashSet<>();

        // 初始化队列
        for (Long w : uniqueW) {
            if (!maxNum.containsKey(w) || maxNum.get(w) < 1) {
                maxNum.put(w, 1);
                pq.add(new Pair(w, 1));
            }
        }

        int count = 0;
        long ans = -1;

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            long curWei = cur.wei;
            int curNum = cur.num;

            // 跳过非最大num的状态
            if (maxNum.getOrDefault(curWei, 0) != curNum) continue;

            // 满足条件则加入答案集合
            if (curNum >= k) {
                if (answerSet.add(curWei)) {
                    if (++count == l) {
                        ans = curWei;
                        break;
                    }
                }
            }

            // 生成新的状态
            for (Long w : uniqueW) {
                long newWei = curWei + w;
                int newNum = curNum + 1;

                // 仅当新数目更大时更新
                if (newNum > maxNum.getOrDefault(newWei, 0)) {
                    maxNum.put(newWei, newNum);
                    pq.add(new Pair(newWei, newNum));
                }
            }
        }

        System.out.println(ans);
    }
}