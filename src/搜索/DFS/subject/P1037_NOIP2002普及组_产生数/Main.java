package 搜索.DFS.subject.P1037_NOIP2002普及组_产生数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.IllegalFormatPrecisionException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

    }
}


class Solutions{
    private char[] a = new char[100];  // 输入的数字串
    // x to y
    private char[] x = new char[19];
    private char[] y = new char[19];
    // 判定,flag[i]表示
    private boolean[] flag = new boolean[100];
    // 结果
    private char[] res = new char[100];
    private int k, l, st;

    private void dfs(char c){
        if(flag[c]) return;
        flag[c] = true;
        for(int i = 0; i < k; i++){
            if(x[i] == c){
                dfs(y[i]);
            }
        }
    }


    public Solutions(){
        FastReader sc = new FastReader();

        String str = sc.next();
        k = sc.nextInt();
        l = str.length();
        a = str.toCharArray();

        for (int i = 0; i < k; i++) {
            x[i] = sc.next().charAt(0);
            y[i] = sc.next().charAt(0);
        }

        // 高位到低位开始搜素
        dfs(a[0]);
        Arrays.fill(flag, false);
        flag[0] = false;
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
