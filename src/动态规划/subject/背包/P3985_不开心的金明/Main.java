package 动态规划.subject.背包.P3985_不开心的金明;

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
    static final int X = 110;

    // 反转数组的某一部分
    private void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public Solutions(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();   // 希望购买物品的个数
        int m = sc.nextInt();   // 总钱数

        int[] w = new int[105]; // 物品价格
        int[] v = new int[105]; // 物品重要度
        int minn = (int) 1e9 + 7; // 最小价格

        // 要求购物单上所有的物品价格的极差（最贵的减去最便宜的）不超过 3
        // 也就是开四个数组，存价格最小的差0，1，2，3的物品价格（重要度）
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
            minn = Math.min(minn, w[i]);
        }
        // 分别存储价格与最小价格差为 0, 1, 2, 3 的物品重要度
        int[] w0 = new int[105];
        int[] w1 = new int[105];
        int[] w2 = new int[105];
        int[] w3 = new int[105];
        int tot0 = 0, tot1 = 0, tot2 = 0, tot3 = 0; // 每种价格物品的数量

        // 将物品按价格与最小价格的差值分类
        for (int i = 1; i <= n; i++) {
            if (w[i] - minn == 0) w0[++tot0] = v[i];
            if (w[i] - minn == 1) w1[++tot1] = v[i];
            if (w[i] - minn == 2) w2[++tot2] = v[i];
            if (w[i] - minn == 3) w3[++tot3] = v[i];
        }

        // 贪心，将每种物品按重要度从高到低排序
        Arrays.sort(w0, 1, tot0 + 1);
        reverseArray(w0, 1, tot0);
        Arrays.sort(w1, 1, tot1 + 1);
        reverseArray(w1, 1, tot1);
        Arrays.sort(w2, 1, tot2 + 1);
        reverseArray(w2, 1, tot2);
        Arrays.sort(w3, 1, tot3 + 1);
        reverseArray(w3, 1, tot3);

        // 计算每种物品的前缀和
        int[] s0 = new int[105];
        int[] s1 = new int[105];
        int[] s2 = new int[105];
        int[] s3 = new int[105];
        for (int i = 1; i <= tot0; i++) s0[i] = s0[i - 1] + w0[i];
        for (int i = 1; i <= tot1; i++) s1[i] = s1[i - 1] + w1[i];
        for (int i = 1; i <= tot2; i++) s2[i] = s2[i - 1] + w2[i];
        for (int i = 1; i <= tot3; i++) s3[i] = s3[i - 1] + w3[i];

        int ans = 0;

        // 枚举每种物品的数量
        for(int i = 0; i <= tot0; i++) {
            for(int j = 0; j <= tot1; j++) {
                for(int k = 0; k <= tot2; k++) {
                    int sum = i * minn + j * (minn + 1) + k * (minn + 2);
                    if (sum <= m) { // 不超过总钱数
                        int p = (m - sum) / (minn + 3);  //可用再选几个+3元的物品
                        p = Math.min(p, tot3);
                        // 选前几个物品
                        ans = Math.max(ans, s0[i] + s1[j] + s2[k] + s3[p]);
                    }
                }
            }
        }
        System.out.println(ans);
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
    }
}