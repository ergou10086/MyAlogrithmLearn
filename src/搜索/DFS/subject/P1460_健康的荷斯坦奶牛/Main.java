package 搜索.DFS.subject.P1460_健康的荷斯坦奶牛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 快读
class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
        while (!st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }
}

class Healthy_Holsteins {
    private int[] ans = new int[1001];   // 存解
    private int[] vtea = new int[1001];   // 牛每天所需要的每种维他命的最小量
    private int[][] food_vtea = new int[1001][1001];  // 饲料每种维他命的含量
    private int[] id = new int[1001];   // 搜索选择的饲料编号
    private int n, m, minn = 1000000; // 适当修改初始值

    // 判断此时选择饲料里面的维他命量是否满足需求
    private boolean check(int x) {
        // 每种维他命量
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            // 饲料类型
            for (int j = 1; j <= x; j++) {
                sum += food_vtea[id[j]][i];
            }
            // 一种不合适直接退出
            if (sum < vtea[i]) return false;
        }
        return true;
    }

    // temp是当前正在考虑选择的饲料编号,s是选的饲料的总数
    private void dfs(int temp, int s) {
        // 如果选择的饲料编号大于了总数，截断
        if (temp > m) {
            // 合理性检查
            if (check(s) && s < minn) {
                // 饲料总数更新
                minn = s;
                for (int i = 1; i <= s; i++) {
                    ans[i] = id[i];
                }
            }
            return;
        }
        // 下一个饲料选择temp类型的
        id[s + 1] = temp;
        dfs(temp + 1, s + 1);
        // 回溯
        id[s + 1] = 0;
        // 不选temp类型的，选temp+1的
        dfs(temp + 1, s);
    }

    public Healthy_Holsteins() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        for (int i = 1; i <= n; i++)  vtea[i] = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                food_vtea[i][j] = sc.nextInt();
            }
        }
        dfs(1, 0);
        System.out.print(minn + " ");
        for (int i = 1; i <= minn; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new Healthy_Holsteins();
    }
}

