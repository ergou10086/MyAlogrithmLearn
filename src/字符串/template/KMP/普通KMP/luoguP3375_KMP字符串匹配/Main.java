package 字符串.template.KMP.普通KMP.luoguP3375_KMP字符串匹配;

import java.io.*;
import java.util.StringTokenizer;

// 快读
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

class KMP{
    private final int N = 1000010;
    private int[] next = new int[N];
    private BufferedWriter writer;

    // 计算next数组的函数，表示模式串最长前后缀的长度
    // 通过模式串前后缀的自我匹配长度，计算next函数
    // next函数表示模式串P[1,i]中与自己的前后缀成功匹配的最长长度，既是它的后缀又是它的前缀的字符，长度要短于自身
    // next数组实际上就是打表，失配时候跳到next[j]的位置继续匹配
    private void GetNext(String P, int n){
        // 单字符无前后缀长度
        next[1] = 0;
        // i指针扫描模式串，j指针扫描前缀，从第二个字符开始才能匹配前后缀，j=0是有0个适配
        for(int i = 2, j = 0; i <= n; i++){
            // next函数的减少也是有规律的，如果发现了失配，那么能匹配的大小只能是失配位置x2-1长度，也就是j=next[j]
            while(j != 0 && P.charAt(i - 1) != P.charAt(j))  j = next[j];

            // next函数增加是有规律的，每次前缀长度加一，那么最多只能为最长前缀的长度增加一
            // 重要的是观察j指针前面的字符能否和匹配到的第i个字符相等
            if (P.charAt(i - 1) == P.charAt(j)) j++;
            next[i] = j;
        }
    }

    // 模式串与主串匹配函数
    // 取最长的相等前后缀，可以保证不丢解
    private void KMPSearch(String S, String P, int m, int n){
        for (int i = 1, j = 0; i <= m; i++) {
            // 主串与模式串出现了失配，那么j就需要回跳到匹配的位置，要不然就跳到0
            while (j > 0 && S.charAt(i - 1) != P.charAt(j)) j = next[j];
            // 成功匹配，j向右走一个
            if (S.charAt(i - 1) == P.charAt(j)) j++;
            // 匹配成功，输出匹配位置
            if (j == n) {
                System.out.println(i - n + 1);
                j = next[j];
            }
        }
    }

    public KMP(){
        FastReader sc = new FastReader();
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 模式串P，主串S
        String S = sc.next();
        String P = sc.next();

        int m = S.length();
        int n = P.length();


        GetNext(P, n);

        KMPSearch(S, P, m, n);

        for (int i = 1; i <= n; i++) {
            try {
                writer.write(next[i] + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.newLine(); // 换行
            writer.flush(); // 刷新输出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args){
        KMP kmp = new KMP();
    }
}
