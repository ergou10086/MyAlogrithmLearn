package 数据结构.并查集.subject.P2256_一中校运会之百米跑;

import java.util.Scanner;

public class Main {
    static int n, m, k;
    static String[] f, mz;

    // 查找并压缩路径
    static String find(String name) {
        if (f[getIndex(name)].equals(name)) {
            return name;
        }
        f[getIndex(name)] = find(f[getIndex(name)]); // 路径压缩
        return f[getIndex(name)];
    }

    // 获取名字对应的索引
    static int getIndex(String name) {
        for (int i = 1; i <= n; i++) {
            if (name.equals(mz[i])) {
                return i;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        f = new String[n + 1];
        mz = new String[n + 1];

        // 输入队长和对应人名
        for (int i = 1; i <= n; i++) {
            f[i] = scanner.next();
            mz[i] = f[i];
        }

        // 处理合并队伍操作
        for (int i = 1; i <= m; i++) {
            String a = scanner.next();
            String b = scanner.next();
            f[getIndex(find(a))] = find(b); // 将 a 的队伍合并到 b
        }

        k = scanner.nextInt();
        // 查询是否在同一队伍
        for (int i = 1; i <= k; i++) {
            String a = scanner.next();
            String b = scanner.next();
            if (find(a).equals(find(b))) {
                System.out.println("Yes.");
            } else {
                System.out.println("No.");
            }
        }

        scanner.close(); // 关闭输入流
    }
}
