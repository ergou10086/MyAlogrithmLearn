package 数据结构.并查集.template;

import java.util.Scanner;

public class UnionFind_basic {

    static int[] p; // 存储每个节点的父节点

    // 寻找 x 的根节点
    public static int findRoot(int x) {
        // 如果 x 是自己的父节点，返回 x
        if (x == p[x]) {
            return x;
        } else {
            // 路径压缩：将 p[x] 的根节点直接指向 x 的根节点
            p[x] = findRoot(p[x]);
            return p[x];
        }
    }

    // 合并 x 和 y 所在的集合
    public static void merge(int x, int y) {
        int rootX = findRoot(x);    // 找到 x 的根节点
        int rootY = findRoot(y);    // 找到 y 的根节点
        p[rootX] = rootY;           // 将 x 的根节点指向 y 的根节点
    }

    // 查询 x 和 y 是否在同一个集合
    public static boolean query(int x, int y) {
        return findRoot(x) == findRoot(y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 节点数量
        int q = scanner.nextInt(); // 操作数量
        p = new int[n + 1]; // 初始化父节点数组

        // 初始化 p[i] = i，表示每个节点的父节点是自己
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }

        for(int i = 0; i < q; i++) {
            int op = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (op == 1) {
                // 合并操作
                merge(x, y);
            } else {
                // 查询操作
                if (query(x, y)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }

        scanner.close();
    }
}
