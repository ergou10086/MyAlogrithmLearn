package 搜索.DFS.template.DFS剪枝.ybt1221_分成互质组;

import java.util.*;

public class Main {
    static int n, cnt;   // cnt记录已经被分了几个组
    static int[] a = new int[11];
    static int ans = 11;
    static ArrayList<Integer>[] g = new ArrayList[11];  // 记录分组情况

    // 计算最大公约数
    private static int gcd(int x, int y) {
        return y == 0? x : gcd(y, x % y);
    }

    // 如果a[num]能放进第i组
    static boolean check(int x, int i) {
        ArrayList<Integer> group = g[i];  // 取出第i组的情况
        for (int num : group) {
            if (gcd(x, num) > 1) {
                return false;
            }
        }
        return true;
    }

    static void dfs(int num) {
        if (cnt >= ans) return;    // 已经不优了，剪枝
        if (num == n) {
            ans = cnt;
            return;
        }

        // 枚举已有组，看看a[num]能放到哪组
        for (int i = 0; i < cnt; i++) {
            if (check(a[num], i)) {
                // 将a[num]放入第i组
                g[i].add(a[num]);
                // 检查下一个数的情况
                dfs(num + 1);
                g[i].remove(g[i].size() - 1); // 恢复现场,回溯
            }
        }

        // 新开一组，再dfs看看什么情况
        g[cnt].add(a[num]);
        cnt++;
        dfs(num + 1);
        cnt--;
        g[cnt].remove(g[cnt].size() - 1); // 恢复现场,回溯
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        // 初始化g数组中的ArrayList对象
        for (int i = 0; i < 11; i++) {
            g[i] = new ArrayList<>();
        }

        dfs(0);
        System.out.println(ans);
        sc.close();
    }
}