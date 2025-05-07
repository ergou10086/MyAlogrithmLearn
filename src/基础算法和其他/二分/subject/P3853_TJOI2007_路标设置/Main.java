package 基础算法和其他.二分.subject.P3853_TJOI2007_路标设置;

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

class BlockPlace{
    private int l, n, k;
    private int[] road = new int[100005];

    private boolean check(int mid){
        int size = 0;   // 比较位置
        int sp = k;  // 记录可放置的路标树
        // 枚举两个路标之间的距离
        for(int i = 1; i <= n; i++){
            // 判断
            if(sp < 0){
                break;
            }
            // 两个路标之间的距离小于mid,不用管,接着移动
            if(road[i] - size <= mid){
                size = road[i];
            }else{
                size = size + mid;  // 不够就放一个路标
                sp -= 1;
                i -= 1;  // 洛谷大神的处理,防止不检查放置的路标的情况
            }
        }
        if(sp >= 0){
            return true;
        }else{
            return false;
        }
    }

    public BlockPlace() {
        FastReader sc = new FastReader();
        l = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();
        // 路标放置和统计
        int t = 0;
        while(t < n){
            road[t] = sc.nextInt();
            t++;
        }
        int le = 0, ri = l;
        int ans = 0;
        // 判断一个空旷指数p，从头枚举两个相邻路标之间的距离，大于p，放一个路标
        // 如果路标数量不够，那么p太小，如果路标有剩下，p太大
        // 二分
        while(le <= ri){
            int mid = le + (ri - le)/2;
            if(check(mid)){
                ans = mid;
                ri  = mid - 1;   // 有可能有更小的空旷指数
            }else{
                le = mid + 1;   // 存在更大的空旷指数
            }
        }
        System.out.println(ans);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new BlockPlace();
    }
}
