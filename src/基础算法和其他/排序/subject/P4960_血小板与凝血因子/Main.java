package 基础算法和其他.排序.subject.P4960_血小板与凝血因子;

// 题意就是有两种容器装n个数
// 第一种容器只能装一样的数，答案就是有多少不一样的数
// 第二种容器只能装不一样的数
// 选一种容器，用最少的容器个数，存下n个数

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 快读类
class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    String next() {
        while (st == null || !st.hasMoreElements()) {
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

// 解决问题类
class solve{
    private final int MAXN = 1001;

    public solve() {
        FastReader fs = new FastReader();
        int[] a = new int[MAXN + 1];
        Map<Integer, Integer> b = new HashMap<>();
        Set<Integer> vis = new HashSet<>();        // 判重，标记该元素是否有重复元素
        List<Integer> idk = new ArrayList<>();     // 用来标记只出现一次的数字

        int n = fs.nextInt();
        // 分别对应使用两种容器的情况
        int cnt1 = 0, cnt2 = 0;

        for (int i = 1; i <= n; i++) {
            a[i] = fs.nextInt();
            // b记录数组a中每个元素出现的次数
            b.put(a[i], b.getOrDefault(a[i], 0) + 1);
            // ans1统计出现最多的数出现的次数
            cnt1 = Math.max(cnt1, b.get(a[i]));
            // 没出现过的数字，标记
            if (!vis.contains(a[i])) {
                idk.add(a[i]);
                vis.add(a[i]);
                cnt2++;
            }
        }

        // 出现最多的数多，用第一种容器
        if(cnt2 <= cnt1){
            System.out.println(cnt2 + " " + 1);
            for (int i = 0; i < cnt2; i++) {
                // 找每个数的出现次数
                int count = b.get(idk.get(i));
                System.out.print(count);
                for (int j = 0; j < count; j++) {
                    System.out.print(" " + idk.get(i));
                }
                System.out.println();
            }
        }else{
            int sum = cnt2;
            System.out.println(cnt1 + " " + 2);
            for (int i = 0; i < cnt1; i++) {
                System.out.print(sum);
                // 对于只出现一次的数字进行的
                for (int j = 0; j < cnt2; j++) {
                    if (b.get(idk.get(j)) > 0) {
                        System.out.print(" " + idk.get(j));
                        // 在b中删掉，也就是打印过一次了
                        b.put(idk.get(j), b.get(idk.get(j)) - 1);
                        if (b.get(idk.get(j)) == 0) {
                            sum--;
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new solve();
    }
}
