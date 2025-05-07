package 基础算法和其他.日期.P1167_刷题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{
    public Solutions() {
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

        FastReader sc = new FastReader();

        int n = sc.nextInt();
        int[] oj = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            oj[i] = sc.nextInt();
        }
        Arrays.sort(oj);

        String dateS = sc.next();
        String endS = sc.next();

        // 输入的日期转换标准格式
        LocalDateTime datS = LocalDateTime.parse(dateS, formatter);
        LocalDateTime datE = LocalDateTime.parse(endS, formatter);

        // 时间差
        Duration duration = Duration.between(datS, datE);
        // 转换为分钟
        long minutes = duration.toMinutes();

        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(minutes > 0){
                minutes -= oj[i];
                ans++;
                if(minutes < 0){
                    ans--;
                    break;
                }
            }else{
                break;
            }
        }

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
