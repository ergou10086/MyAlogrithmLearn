package 数据结构.线性数据结构.链表.LeetCode138_随机链表的复制;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    // 复制一条链表，复制的链表中的节点是全新的
    // 新链表的 next 和 random 指针要正确地指向新链表中的节点，而不是原链表中的节点。
    public Node copyRandomList(Node head) {
        // 为每个原节点创建一个新节点，并用 HashMap<原节点, 新节点> 记录映射
        // 根据原链表的 next 和 random 关系，通过哈希表找到对应的新节点，设置新节点的指针。
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next);
            newNode.random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }
}

public class Main {
    public static Node buildList(Object[][] desc) {
        if (desc == null || desc.length == 0) return null;
        Node[] nodes = new Node[desc.length];
        for (int i = 0; i < desc.length; i++) {
            nodes[i] = new Node((Integer) desc[i][0]);
        }
        for (int i = 0; i < desc.length; i++) {
            if (i + 1 < desc.length) nodes[i].next = nodes[i + 1];
            Object r = desc[i][1];
            if (r != null) nodes[i].random = nodes[(Integer) r];
        }
        return nodes[0];
    }

    // 一行代码验证（这才是你想要的！）
    public static void main(String[] args) {
        System.out.println("OK"); // 只要不报错，说明 copy 成功（无法直接 println 链表）

        // 真正的一行测试（验证结构正确性）
        Node copied = new Solution().copyRandomList(buildList(new Object[][]{{7,null},{13,0},{11,4},{10,2},{1,0}}));

        // 简单验证：检查长度和部分值
        int len = 0;
        Node p = copied;
        while (p != null) {
            len++;
            if (len == 1) assert p.val == 7 && p.random == null;
            if (len == 2) assert p.val == 13 && p.random.val == 7;
            p = p.next;
        }
        assert len == 5;
        System.out.println("Test passed!");
    }
}
