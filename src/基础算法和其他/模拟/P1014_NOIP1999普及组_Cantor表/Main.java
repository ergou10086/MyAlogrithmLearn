package 基础算法和其他.模拟.P1014_NOIP1999普及组_Cantor表;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 前i条斜线一共j个数
        int i = 0, j = 0;
        // 找到最小的i使得j>=n
        while(n > j){
            i++;
            j += i;
        }
        // j - n + 1 判断是这条斜线上，到第第几条了， i + n - j判断在这个小循环内，你到第几个了
        // 奇数，倒数
        if(i % 2 == 1) System.out.println((j - n + 1) + "/" + (i + n - j));
        // 偶数，正数第几个数
        if(i % 2 == 0) System.out.println((i + n - j) + "/" + (j - n + 1));
    }
}
