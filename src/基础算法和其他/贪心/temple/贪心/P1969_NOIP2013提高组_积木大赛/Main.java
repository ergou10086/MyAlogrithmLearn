package 基础算法和其他.贪心.temple.贪心.P1969_NOIP2013提高组_积木大赛;

// 拆解成一段段不下降子区间，然后减
// 右边比左边高，那么就要往上面叠积木
// 左边比右边高，那么就不用动

import java.nio.channels.FileLockInterruptionException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] h = new int[n + 1];
        for (int i = 1; i <= n; i++) h[i] = scanner.nextInt();
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            if(h[i] > h[i - 1]){
                ans += h[i] - h[i - 1];
            }
        }

        System.out.println(ans);
    }
}
