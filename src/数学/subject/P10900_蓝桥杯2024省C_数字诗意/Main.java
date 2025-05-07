package 数学.subject.P10900_蓝桥杯2024省C_数字诗意;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int ans = 0;

        // 两个连续的数字加起来，肯定不是2的次幂
        // 2的n次幂是正整数中唯一不可连续分割的

        // 连除判断是不是2的n次幂
        for (int i = 0; i < n; i++) {
            int a = scan.nextInt();
            while(a % 2 == 0){
                a = a / 2;
            }
            if(a == 1){
                ans++;
            }
        }

        System.out.println(ans);
    }
}
