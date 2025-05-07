package 基础算法和其他.贪心.subject.P11006_蓝桥杯2024省PythonB_纯职业小组;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 至少需要选择多少人才能在sum个人中组成k个拥有三名相同职业的纯职业队伍
// 不够k，凑不出来，-1
// 我们希望取最多人数的组成队伍，可以先将所有队伍取出 2 个人，若队伍为 1 人则取 1 人，而此时不构成一支队伍
// 如果 k=1，那就只能再选一个人，就成一个队伍了
// 如果 k>1
//    还有一个队伍有大于三人的情况，这三个都要
//    还要一个剩余 ≥2 个人的职业，那就先选 1 个构成一支队伍，然后可以多拿一个
//    如果有一个剩余 ≥1 个人的职业，那就选 1 个构成一支队伍
// 根据贪心的策略，有 3 选 3，无 3 选 2，无 2 选 1。

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {

    public Solutions() {
        FastReader sc = new FastReader();
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            long k = sc.nextInt();

            // 统计职业人数
            Map<Integer, Long> occ = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int a = sc.nextInt();
                long b = sc.nextLong(); // 改为long
                occ.put(a, occ.getOrDefault(a, 0L) + b);
            }

            // 取出人数
            List<Long> numb = new ArrayList<>();
            for (long p : occ.values()) {
                numb.add(p);
            }

            // 第一次选，尽可能多地组成完整小组
            long cnt = 0;    // 已组成的小组数
            long res = 0;    // 已选择的士兵数
            for (int i = 0; i < numb.size(); i++) {
                long num = numb.get(i);
                long fullGroups = num / 3;   // 你这个职业够凑几组完整的
                cnt += fullGroups;
                long used = Math.min(num, 2);   // 从当前职业选择的士兵数，从每个职业最多只选2人
                numb.set(i, num - used);   // 剩余要变
                res += used;
            }


            // 第一次筛选就不够，那寄了
            if (cnt < k) {
                System.out.println(-1);
                continue;
            }


            // 统计剩余的资源
            long c1 = 0, c2 = 0, c3 = 0;
            for (long x : numb) {
                c3 += x / 3;
                x %= 3;
                if (x == 1) {
                    c1++;
                } else if (x == 2) {
                    c2++;
                }
            }


            // k--，res++是因为组出一组了已经
            k--;
            res++;

            // 先用能组成完整的3人组的，再是2，这样都有白嫖的情况
            long v = Math.min(k, c3); // 改为long
            k -= v;
            c3 -= v;
            res += v * 3;

            v = Math.min(k, c2);
            k -= v;
            c2 -= v;
            res += v * 2;

            v = Math.min(k, c1);
            k -= v;
            c1 -= v;
            res += v;

            System.out.println(res);
        }
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

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
