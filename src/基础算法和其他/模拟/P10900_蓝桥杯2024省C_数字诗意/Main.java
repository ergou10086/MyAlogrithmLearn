package 基础算法和其他.模拟.P10900_蓝桥杯2024省C_数字诗意;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Long j = sc.nextLong();
            while(j % 2 == 0 && j > 1) {
                j /= 2;
            }
            if(j == 1) {
                ans++;
            }

            System.out.println(ans);
        }

        // sum = l + (l + 1) + (l + 2) +...+(l + r - 1)
        // sum = r * l + r(r - 1) / 2
        // 所以只要判断每个数是不是 2 的幂次就可以了


    }
}


