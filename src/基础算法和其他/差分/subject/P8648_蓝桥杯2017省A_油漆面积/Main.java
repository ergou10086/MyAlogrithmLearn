package 基础算法和其他.差分.subject.P8648_蓝桥杯2017省A_油漆面积;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 二维前缀和可以处理若干次单点加，最后若干次矩形查的问题。

class Solutions{
    static final int N = 10005;
    static int n, ans;
    static short[][] a = new short[N][N];

    public Solutions(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            ++a[x1][y1];
            --a[x1][y2];
            --a[x2][y1];
            ++a[x2][y2];
        }

        for(int i = 0; i <= 10000; i++){
            for (int j = 0; j <= 10000; j++) {
                if(i > 0 && j > 0){
                    a[i][j] = (short) (a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1] + a[i][j]);
                } else if (i > 0) {
                    a[i][j] = (short) (a[i - 1][j] + a[i][j]);
                } else if (j > 0) {
                    a[i][j] = (short) (a[i][j - 1] + a[i][j]);
                } else {
                    a[i][j] = a[i][j];
                }
                if (a[i][j] != 0) ++ans;
            }
        }

        System.out.println(ans);
    }


}
