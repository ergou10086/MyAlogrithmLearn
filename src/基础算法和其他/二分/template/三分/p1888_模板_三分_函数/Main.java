package 基础算法和其他.二分.template.三分.p1888_模板_三分_函数;

// 题目大意是给你一堆二次函数，F(x)定义为对于每一个x取每一个函数对应的y的最大值，题目是求F[x]在一定区间中的最小值。

import java.util.Scanner;

class ThirdSpare{
    private int n;
    private final double eps = 1e-9;
    private int[] a = new int[100086];
    private int[] b = new int[100086];
    private int[] c = new int[100086];

    // f计算F(x)， 定义为对于每一个 x，取每一个二次函数对应的 y 值中的最大值
    private double f(double x){
        double ans = Double.NEGATIVE_INFINITY;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, a[i] * x * x + b[i] * x + c[i]);
        }
        return ans;
    }

    public void solve(){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            n = scanner.nextInt();
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextInt();
                b[i] = scanner.nextInt();
                c[i] = scanner.nextInt();
            }

            double l = 0, r = 1000;
            while (r - l > eps) {
                // 求三分点，m1靠近左边，m2靠近右边
                double m1 = (2 * l + r) / 3;
                double m2 = (l + 2 * r) / 3;
                // 在靠近左边的三分点处更小，区间拉向左边缩小
                if (f(m1) < f(m2)) {
                    r = m2;
                } else {   // 这就是反着
                    l = m1;
                }
            }

            System.out.printf("%.4f\n", f(l));
        }

        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        ThirdSpare thirdSpare = new ThirdSpare();
        thirdSpare.solve();
    }
}
