package 基础算法和其他.二分.subject.P1182_数列分段_SectionII;

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

class SequenceSegmentation{
    private int n, m;
    private int[] a = new int[100086];

    private boolean check(int mid){
        // 区段和最大值和分段次数
        int sum = 0, cnt = 1;
        for(int i = 1; i <= n; i++){
            // 目前这一段的和没有大于mid比对值
            if(sum + a[i] <= mid){
                sum += a[i];
            }else{
                // 达到了mid对比值，从新开一段
                sum = a[i];
                cnt += 1;
            }
        }
        return cnt > m;
    }

    // 分段，l为最小和，全部都分段，此时最小和为max(arr)，r为最大和，只分一段，此时最大和为sum(arr)
    // 分段数 > 指定分段次数 ， 说明指定的和太小,右移
    // 分段数 < 指定分段次数 ， 说明此时指定的和太大，左移
    public SequenceSegmentation() {
        FastReader sc = new FastReader();
        int l = 0, r = 0, mid;
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 1; i <= n; i++){
            a[i] = sc.nextInt();
            l = Math.max(l, a[i]);
            r += a[i];
        }

        while(l <= r){
            mid = (l + r) / 2;
            if(check(mid)){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        System.out.println(l);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new SequenceSegmentation();
    }
}
