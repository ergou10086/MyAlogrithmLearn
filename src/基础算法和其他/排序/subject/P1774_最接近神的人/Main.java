package 基础算法和其他.排序.subject.P1774_最接近神的人;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}


class Solution{
    private int n;
    private long ans = 0;
    private int[] a = new int[500005];
    private int[] b = new int[500005];;

    private void megaSort(int l, int r) {
        if(l >= r) return;

        int mid = (l + r) / 2;

        // 拆分
        megaSort(l, mid);
        megaSort(mid + 1, r);

        // 合并
        int i = l;
        int j = mid + 1;
        int k = l;
        while(i <= mid && j <= r) {
            // 两边元素符合不下降序列，合并
            if(a[i] <= a[j]) {
                // 正着拷贝
                b[k++] = a[i++];
            }else{
                // 反着拷贝
                b[k++] = a[j++];
                ans += mid - i + 1;
            }
        }

        // 处理剩余元素
        while (i <= mid) b[k++] = a[i++];
        while (j <= r) b[k++] = a[j++];

        // 拷贝回原数组
        for (int p = l; p <= r; p++) {
            a[p] = b[p];
        }
    }


    public Solution() {
        FastReader in = new FastReader();
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        megaSort(0, n - 1);
        System.out.println(ans);
    }


    private class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

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
}
