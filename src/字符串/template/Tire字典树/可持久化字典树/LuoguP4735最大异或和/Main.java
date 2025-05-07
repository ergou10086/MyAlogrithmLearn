package 字符串.template.Tire字典树.可持久化字典树.LuoguP4735最大异或和;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 区间和的问题通常需要转化为前缀和
// s[1] = a[0]  xor a[1]
// s[p] = s[p - 1] xor a[p]
// a[p] xor a[p + 1] xor ... xor a[N] xor x = s[p - 1] xor s[N] xor x
// s[p - 1] xor s[N] xor x
// 求在区间[l-1, r-1]中，s[p - 1]与s[N] xor x异或的最大值

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private static final int N = 600010;
    private int n, m, idx, cnt;
    private int[] rt = new int[N];  // 存各个版本的根
    private int[][] ch = new int[N * 24][2];  // 数字各位
    private int[] siz = new int[N * 25];  // 存每个版本的树大小

    private void insert(int v){
        rt[++idx] = ++cnt;
        int x = rt[idx - 1];
        int y = rt[idx];
        for (int i = 23; i >= 0; i--) {
            int j = (v >> i) & 1;
            ch[y][j ^ 1] = ch[x][j ^ 1]; // 异位继承
            ch[y][j] = ++cnt;
            x = ch[x][j];
            y = ch[y][j];
            siz[y] = siz[x] + 1;
        }
    }

    private int query(int x, int y, int v){
        int ans = 0;
        for(int i = 23; i >= 0; i--) {
            int j = (v >> i) & 1;
            if(siz[ch[y][j ^ 1]] > siz[ch[x][j ^ 1]]){
                x = ch[x][j ^ 1];
                y = ch[y][j ^ 1];
                ans += (1 << i);
            }else {
                x = ch[x][j];
                y = ch[y][j];
            }
        }
        return ans;
    }


    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        int s = 0;
        // 求在区间[l-1, r-1]时候，r-1和l-2做差会越界，先插入一个0，这样版本序号会右移一位，这样可以r-(l-1)
        insert(0); // 插入左边界 0

        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            s ^= x;
            insert(s);
        }

        while(m-- > 0){
            String op = sc.next();
            if(op.equals("A")){
                int x = sc.nextInt();
                s ^= x;
                insert(s);
            }else{
                int l = sc.nextInt();
                int r = sc.nextInt();
                int x = sc.nextInt();
                System.out.println(query(rt[l - 1], rt[r], s ^ x));
            }
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
    }
}
