package 基础算法和其他.二分.template.二分答案.P1163_银行贷款;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double w0 = scanner.nextInt();   // 借了多少
        double w = scanner.nextInt();   // 每月还多少
        double m = scanner.nextInt();    // 还了几个月

        // 搜的利率范围的的最小值和最大值
        double left = 0, right = 5;
        // 使用二分法来逼近正确的月利率

        for (int i = 0; i < 30; i++) {
            double mid = (left + right) / 2;
            // 月利率为x，换m个月，每月还款的现值总和为 = w/(1+x) + w/(1+x)^2 + w/(1+x)^3 +... + w/(1+x)^m
            // 等比数列 现值总和 = w * (1 - (1 / (1+x)^m)) / x
            double wm = Math.pow((1 + mid), m);
            double current = w * (1 - 1 / wm) / mid;

            // 根据现值总和与贷款原值的比较调整搜索区间
            if (current > w0) {
                left = mid; // 如果现值总和大于贷款原值，说明利率偏低，调整下界
            } else {
                right = mid; // 否则调整上界
            }
        }

        // 计算最终的月利率，转换为百分数并四舍五入到小数点后一位
        double ans = Math.round(right * 100 * 10) / 10.0;

        // 输出结果
        System.out.printf("%.1f\n", ans);

    }
}
