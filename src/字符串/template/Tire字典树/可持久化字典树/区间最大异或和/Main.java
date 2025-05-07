package 字符串.template.Tire字典树.可持久化字典树.区间最大异或和;

// 可持久化Tire和可持久线段树的构造方式类似，都是在非持久化的基础上
// 连边，动态开点，每次只添加新的节点，继承没有被改动的节点
// 最终从每个版本的Tire树的根遍历，所分离出的Tire树都包含前面的全部信息

// 大部分可持久化Tire树都是01Tire树

// 应用，给一个整数数列a[N],M次查询，每次查询一个区间[l,r]内与树x异或的最大值

// 用前缀和和差分的思想，用两棵前缀Tire树相减就得到该区间的Tire树，查询的时候，用贪心思想，尽量往与当前位不同的地方跳，也就得到异或的最大值

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions{
    private final int N = 600005;
    private int n, m, idx, cnt;
    private int[] rt = new int[N];    // 记录每个版本的根
    private int[][] ch = new int[N * 25][2];  // 记录分支所走的点
    private int[] siz = new int[N];    // 记录新开节点总是比旧版对应分支的节点数多1，方便查询时候判断是否越界

    private void insert(int v){
        rt[++idx] = ++cnt; // 新根开点
        int x = rt[idx - 1]; // 旧版的根节点
        int y = rt[idx];   // 新版的根节点
        for(int i = 23; i >= 0; i--){
            int j = v >> i & 1;  // 取出 v 的第 i 位（0 或 1）
            // 异位继承，共享未修改的子节点
            // 也就是你这一位如果异或后能和上一个版本的指向一个节点，就继承直接指过去
            ch[y][j ^ 1] = ch[x][j ^ 1];
            ch[y][j] = ++cnt;      // 新位开点
            x = ch[x][j];
            y = ch[y][j];  // 走位
            // 更新节点的大小。
            // siz[y] 表示新版本中当前节点的子树大小
            //siz[x] 是旧版本中对应节点的子树大小。
            siz[y] = siz[x] + 1;   // 新位多一
        }
    }

    // 取出v的第i位数字，根据前缀和
    //  x：旧版本的根节点（对应区间 [1, l-1]）。
    //  y：新版本的根节点（对应区间 [1, r]）。
    private int query(int x, int y, int v){
        int ans = 0;
        for(int i = 23; i >= 0; i--){
            int j = v >> i & 1;    // 取出 v 的第 i 位（0 或 1）
            // 为了使异或结果最大，应该尽量相反
            // siz[ch[y][j ^ 1]]表示在新版本（区间 [1, r]）中，走了 j ^ 1 分支的值的个数
            // 如果这个值大于旧版本的，那么肯定有一个值走了j ^ 1的分支（要不然全走旧版本，就是相等）
            if(siz[ch[y][j ^ 1]] > siz[ch[x][j ^ 1]]){   // 判断是否可以走相反的分支。
                // 移动到相反分支的下一层节点。
                x = ch[x][j ^ 1];
                y = ch[y][j ^ 1];
                ans += (1 << i);   // 将当前位的贡献加到结果中
            } else {
                x = ch[x][j];
                y = ch[y][j];
            }
        }
        return ans;
    }

    public Solutions(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            int x = scanner.nextInt();
            insert(x);
        }
        while (m-- > 0) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int x = scanner.nextInt();
            System.out.println(query(rt[l - 1], rt[r], x));
        }
        scanner.close();
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
