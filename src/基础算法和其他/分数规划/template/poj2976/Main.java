package 基础算法和其他.分数规划.template.poj2976;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int N = 1010;
    static int n, k;
    static double[] a = new double[N];
    static double[] b = new double[N];
    static double[] c = new double[N];

    // 二分一个x，Σ wi * (ai - x * bi) >= 0
    // 把ai - x * bi作为第i个物品的权值x，排序后，选最大的n-k个求和s，只要s大于等于0，就继续最大化x
    public static boolean check(double x){
        double sum = 0;
        for(int i = 1; i <= n; ++i) c[i] = a[i] - x * b[i];
        Arrays.sort(c);
        for(int i = k + 1; i <= n; ++i) sum += c[i];
        return sum >= x;
    }

    public static double find(){
        double l = 0, r = 1;
        while (r - l > 1e-4) {
            double mid = (l + r) / 2;
            if (check(mid)) {
                l = mid; // 最大化
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            n = scanner.nextInt();
            k = scanner.nextInt();
            if (n == 0) break;

            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextDouble();
            }
            for (int i = 1; i <= n; i++) {
                b[i] = scanner.nextDouble();
            }

            System.out.printf("%.0f\n", 100 * find());
        }
        scanner.close();
    }
}
