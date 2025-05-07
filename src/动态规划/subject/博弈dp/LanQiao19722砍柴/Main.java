package 动态规划.subject.博弈dp.LanQiao19722砍柴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        new Solutions4();
    }
}

// 小蓝先出手
// 轮到某一方时 x=1 或 x=0 输
// 博弈问题
// 每个人砍下的长度必须为质数，打表

class Solutions4{

    public Solutions4() {
        FastReader sc = new FastReader();

        // 存2-100000之间的质数
        List<Integer> zf = new ArrayList<>();
        for(int i = 2; i <= 100000; i++) {
            boolean flag = true;
            for(int j = 2; j * j <= i; j++) {
                if(i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if(flag) {
                zf.add(i);
            }
        }


        // dp动态规划
        int dp[] = new int[100001];  // 当前状态先手（小蓝）赢为1否则为0
        // 上来1，0，都是小蓝直接输，2那么小蓝直接赢
        dp[1] = 0;
        dp[0] = 0;
        dp[2] = 1;

        for (int i = 2; i <= 100000; i++) {
            for(int j = 0; j < zf.size(); j++) {
                int x = i - zf.get(j);  // 这一次砍的长度
                if(x < 0) break;

                // 如果砍了这些长度后，小蓝赢不了，下个人砍必输
                if(dp[x] == 0) {
                    dp[i] = 1;
                    break;
                }
            }
        }


        int T = sc.nextInt();   // 测试的组数
        int[] ans = new int[T];   // 值用来存输赢

        for (int i = 0; i < T; i++) {
            int t=sc.nextInt();
            ans[i] = dp[t] == 1 ? 1:0;
        }

        for (int i = 0; i < T; i++) {
            System.out.println(ans[i]);
        }
    }



    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while(!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
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
