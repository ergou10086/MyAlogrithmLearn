package 数据结构.树.subject.线段树.P11048_蓝桥杯2024省JavaB_拼十字.暴力;

import java.util.Scanner;

public class Main {
    // 暴力一下得了
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 长、宽和颜色。
        int[] l = new int[n + 1];
        int[] w = new int[n + 1];
        int[] c = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            l[i] = sc.nextInt();
            w[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }

        int ans = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j) continue;
                if(c[i] != c[j] && l[i] > l[j] && w[i] < w[j]){
                    ans++;
                }
            }
        }

        System.out.println(ans);
        sc.close();
    }
}

