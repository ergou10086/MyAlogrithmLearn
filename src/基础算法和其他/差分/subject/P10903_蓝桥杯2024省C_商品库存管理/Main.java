package 基础算法和其他.差分.subject.P10903_蓝桥杯2024省C_商品库存管理;

// 每个操作涉及到一个特定的商品区间，即一段连续的商品编号范围（例如区间 [L,R]）。
// 执行这些操作时，区间内每种商品的库存量都将增加 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    // 有 N 个数和 M 个操作，每次操作会把一段区间加一，问如果取消某一次操作，N 个数中为 0 的数的个数
    public Solutions(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();   // 种类数
        int m = sc.nextInt();   // 操作个数
        int[] l = new int[m + 1];
        int[] r = new int[m + 1];

        int[] diff = new int[n + 2];  // 差分数组
        for (int i = 1; i <= m; i++) {
            l[i] = sc.nextInt();
            r[i] = sc.nextInt();
            diff[l[i]]++;
            if (r[i] + 1 <= n) { // 防止越界
                diff[r[i] + 1]--;
            }
        }

        int ans = 0;
        int[] occ = new int[n + 1];   // 处理后的数量
        occ[1] = diff[1];
        for (int i = 2; i <= n; i++) {
            occ[i] = occ[i - 1] + diff[i];
        }

        // 记录未进行撤回操作时已经是0的个数，那么你执行了之后依旧为0
        int res1 = 0;
        for(int j = 1; j <= n; j++){
            if (occ[j] == 0) {
                res1++;
            }
        }

        // 预处理前缀和数组，sum[i]表示前i个商品中有多少个总次数为1的
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + (occ[i] == 1 ? 1 : 0);
        }

        // 处理每个操作
        for (int i = 1; i <= m; i++) {
            int L = l[i];
            int R = r[i];
            // 计算区间[L, R]内总次数为1的商品数目
            int res2 = sum[R] - sum[L - 1];
            System.out.println(res1 + res2);
        }
    }

    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }
        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        Long nextLong(){
            return Long.parseLong(next());
        }
    }
}
