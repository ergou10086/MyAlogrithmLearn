package 基础算法和其他.贪心.temple.贪心.P1094_NOIP_2007_普及组_纪念品分组;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt();
        int n = scanner.nextInt();
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) p[i] = scanner.nextInt();

        int head = 1, tail = n, ans = 0;
        Arrays.sort(p);

        while(head <= tail){
            if(p[head] + p[tail] <= w){
                head++;
                tail--;
                ans++;
            }else{
                tail--;
                ans++;
            }
        }

        System.out.println(ans);
        scanner.close();
    }
}

// 每组最多只能包括两件纪念品， 并且每组纪念品的价格之和不能超过一个给定的整数
// 那么也就是最大的和最小的从头开始向中间匹配


