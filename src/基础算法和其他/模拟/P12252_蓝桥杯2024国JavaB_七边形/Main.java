package 基础算法和其他.模拟.P12252_蓝桥杯2024国JavaB_七边形;

public class Main {
    public static void main(String[] args) {
        // 第 1 至第 4 个七边形图案消耗的小球数量依次是 1、7、18、34
        // 差分别是 6 11 16
        // 可以发现每增加一层就是 首项为6，公差为5的等差数列
        // 等差数列前n项和公式 Sn = n (a1+an)/2   Sn = n * a1 + n * (n-1)* d / 2
        // 通项公式为 An = a1 + (n − 1)d
        // An = 6 + 5 * (n - 1)  Sn = 6n + n * (n-1)* 5 / 2
        int n = 20240601;
        long ans = 1;
        for(int i = 2; i <= n; i++) {
            ans += (long) 5 * i - 4;
        }
        System.out.println(ans);
    }
}
