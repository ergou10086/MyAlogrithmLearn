package 字符串.subject.P2580_于是他错误的点名开始了;

import java.util.Scanner;

public class Main {
    // 字典树的最大节点数
    static final int N = 300010;
    static int n, idx = 0;  // n 为学生数量，idx 为当前字典树节点的索引
    static int[] cnt = new int[N];  // 存储每个节点的插入次数
    static int[][] Tire = new int[N][26];  // 字典树
    static boolean[] isReported = new boolean[N];  // 标记已报名字的数组

    // 用于存储查询结果
    static class QueryResult {
        int count;
        boolean isRepeated;

        QueryResult(int count, boolean isRepeated) {
            this.count = count;
            this.isRepeated = isRepeated;
        }
    }

    // 插入学生名字
    public static void insert(String s) {
        int p = 0;  // 从根节点开始
        for (int i = 0; i < s.length(); i++) {
            int j = s.charAt(i) - 'a';  // 字母映射
            if (Tire[p][j] == 0) {
                Tire[p][j] = ++idx;  // 创建新节点
            }
            p = Tire[p][j];  // 移动到下一个节点
        }
        cnt[p]++;  // 在当前节点增加插入次数
    }

    // 查询名字的插入次数并判断是否重复
    public static QueryResult query(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = s.charAt(i) - 'a';
            if (Tire[p][j] == 0) return new QueryResult(0, false);  // 名字不存在
            p = Tire[p][j];
        }
        int count = cnt[p];  // 返回插入次数
        boolean isRepeated = isReported[p];  // 判断是否已经报过
        isReported[p] = true;  // 标记该名字已被报过
        return new QueryResult(count, isRepeated);  // 返回结果
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();  // 输入学生人数
        sc.nextLine();

        // 插入每个学生名字
        for (int i = 1; i <= n; i++) {
            String studentName = sc.nextLine();
            insert(studentName);
        }

        int m = sc.nextInt();  // 输入报的名字数量
        sc.nextLine();

        // 处理教练报的名字
        for (int i = 1; i <= m; i++) {
            String reportedName = sc.nextLine();
            QueryResult result = query(reportedName);

            // 如果名字错误
            if (result.count == 0) {
                System.out.println("WRONG");
            } else {
                // 如果该名字已经报过
                if (result.isRepeated) {
                    System.out.println("REPEAT");
                } else {  // 名字正确且是第一次出现
                    System.out.println("OK");
                }
            }
        }
        sc.close();  // 关闭输入流
    }
}
