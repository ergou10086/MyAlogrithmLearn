package 字符串.subject.PAT_AdvancedLevel_Practice1002_AsumB_for_Polynomials;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] res = new double[1008];    // 存储多项式系数

        int cnt = 0;
        for(int i = 0; i < 2; i++){
            // 当前多项式的非零项数
            int k = sc.nextInt();
            while(k-- > 0){
                // 指数
                int n = sc.nextInt();
                // 系数
                double a = sc.nextDouble();
                // 相同指数的系数相加
                res[n] += a;
            }
        }

        for (int i = 1007; i >= 0; i--) {
            if(res[i] != 0){
                cnt++;
            }
        }

        System.out.print(cnt); // 输出非零项数
        for (int i = 1007; i >= 0; i--) {
            if (res[i] != 0) {
                System.out.printf(" %d %.1f", i, res[i]); // 输出指数和系数
            }
        }
    }
}
