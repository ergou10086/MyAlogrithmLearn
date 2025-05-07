package 基础算法和其他.贪心.temple.反悔贪心.CF865DBuyLowSellHigh;

// 考虑 i < j < k pi < pj < pk 如何交易利润更大
// 如果在第i天买入，第j天卖出，利润 pj - pi
// 如果在第i天买入，第k天卖出，利润 pk - pi，利润更大
// 所以这个贪心我们需要可以反悔
// 当天有利可图，就卖出，同时买入当天股票，后面再涨再卖
// 小根堆维护每天的股票
// 从前往后枚举pi，每个pi压入堆，如果pi > q.top(),取出堆顶，获得利润，再次压入堆（买），以便返回

import java.beans.Beans;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static final int N = 300005;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++) arr[i] = scanner.nextInt();

        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(!pq.isEmpty() && pq.peek() < arr[i]){
                ans += (arr[i] - pq.poll());
                pq.offer(arr[i]);
            }
            pq.offer(arr[i]);
        }

        System.out.println(ans);
    }
}
