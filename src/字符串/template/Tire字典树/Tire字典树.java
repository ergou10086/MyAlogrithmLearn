package 字符串.template.Tire字典树;

import java.util.Scanner;

public class Tire字典树 {
    static final int N = 100010; // 最大节点数
    static int[][] ch = new int[N][26]; // Trie 树的字典树
    static int[] cnt = new int[N]; // 存储每个节点的插入次数
    static int idx = 0; // 当前节点的索引
    static Scanner scanner = new Scanner(System.in); // 输入扫描器

    // 插入字符串到字典树
    static void insert(String s) {
        int p = 0; // 从根节点开始
        for (int i = 0; i < s.length(); i++) {
            int j = s.charAt(i) - 'a'; // 字母映射
            if (ch[p][j] == 0) {
                ch[p][j] = ++idx; // 如果当前字符的节点不存在，则创建新节点
            }
            p = ch[p][j]; // 移动到下一个节点
        }
        cnt[p]++; // 在当前节点增加插入次数
    }

    // 查询字符串在字典树中出现的次数
    static int query(String s) {
        int p = 0; // 从根节点开始
        for (int i = 0; i < s.length(); i++) {
            int j = s.charAt(i) - 'a'; // 字母映射
            if (ch[p][j] == 0) return 0; // 如果当前字符的节点不存在，返回 0
            p = ch[p][j]; // 移动到下一个节点
        }
        return cnt[p]; // 返回当前节点的插入次数
    }

    public static void main(String[] args) {
        int n = scanner.nextInt(); // 读入操作次数
        scanner.nextLine(); // 处理换行符
        while (n-- > 0) {
            String op = scanner.next(); // 读入操作
            String s = scanner.next(); // 读入字符串
            if (op.equals("I")) {
                insert(s); // 插入操作
            } else {
                System.out.println(query(s)); // 查询操作
            }
        }
    }
}
