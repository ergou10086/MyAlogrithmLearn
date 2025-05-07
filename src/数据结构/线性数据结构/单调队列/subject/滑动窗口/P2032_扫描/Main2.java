package 数据结构.线性数据结构.单调队列.subject.滑动窗口.P2032_扫描;

// 其实还有很多做法
// 我维护一个队列，每个node存值和下标

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        new Solutions2();
    }
}

class Solutions2{
    private class Node{
        int v, id;  // 值和下标
        public Node(int v, int id) {
            this.v = v;
            this.id = id;
        }
    }

    public Solutions2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 数组长度
        int k = sc.nextInt();   // 滑动窗口小

        Node[] nodes = new Node[n + 1];

        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(sc.nextInt(), i);
        }

        // 双端队列Deque
        Deque<Node> q = new ArrayDeque<>(); // 使用 Deque 实现单调队列

        for(int i = 1; i <= n; i++){
            while(!q.isEmpty() && q.getLast().v <= nodes[i].v) q.removeLast();
            q.addLast(nodes[i]);   // 大的元素入队尾
            if(!q.isEmpty() && q.getFirst().id < i - k + 1) q.removeFirst();  // 不断向后滑动超过窗口头出
            if(i >= k) System.out.println(q.getFirst().v);
        }
    }
}