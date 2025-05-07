package 基础算法和其他.分数规划.template.luogu_P4377;

// 分数规划
// 用途，求分数的最值
// 问题：每种物品有两个权值ai和bi，选出若干物品使得 Σai / Σbi 最大或者最小
// 等价于，给出ai和bi，求一组wi属于{0， 1}，最大化或者最小化 Σ wi*ai / Σ wi*bi
// 二分化
// 最大化：二分一个答案x， Σ wi*ai / Σ wi*bi >= x
//        移项得，Σ wi * (ai - x * bi) >= 0
// 最小化：二分一个答案x， Σ wi*ai / Σ wi*bi <= x
//        移项得，Σ wi * (ai - x * bi) <= 0


import java.util.Scanner;

public class Main {
    // 参加比赛的一组奶牛必须总重量至少为 W
    static int n, W;
    // 重量为 wi，才艺水平为 ti
    static int[] w = new int[255];
    static int[] t = new int[255];
    static double[] f = new double[1005];

    // 这题多了分母至少为W的限制，贪心用不了，用01背包
    // 二分一个答案x，把wi作为第i个物品的重量，ti-x*wi作为第i个物品的价值，f[W]为最大值
    public static boolean check(double x){
        for(int i = 1; i <= W; i++) f[i] = -1e9;
        // 01背包倒叙枚举第二维，滚动数组优化
        for(int i = 1; i <= n; i++){
            for(int j = W; j >= 0; j--){
                // min防止突破至少为W的限制
                int k = Math.min(W, j + w[i]);
                f[k] = Math.max(f[k], f[j] + t[i] - x * w[i]);
            }
        }
        return f[W] >= 0;
    }

    public static double find(){
        double l = 0, r = 1000;
        // 精度为后五位
        while(r - l > 1e-5){
            double mid = (l + r) / 2;
            if(check(mid)) l = mid;
            else r = mid;
        }
        // 返回r避免精度出现问题
        return r;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        W = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
            t[i] = scanner.nextInt();
        }
        System.out.printf("%d\n", (int) (find() * 1000));
        scanner.close();
    }
}
