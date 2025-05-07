package 基础算法和其他.双指针.subject.PAT_AdvancedLevel_Practice1048FindCoins;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] arr = new int[1001];   // 覆盖所有可能的硬币面值

        // 读取硬币并填充桶数组
        for (int i = 0; i < n; i++) {
            int temp = scanner.nextInt();
            arr[temp]++;
        }


        // 遍历所有可能的硬币面值
        for (int i = 0; i <= 1000; i++) {
            if (arr[i] > 0) {
                arr[i]--;  // 尝试使用当前硬币i
                int target = m - i;
                // 检查target是否合法且存在可用硬币
                if (target >= 0 && target <= 1000 && arr[target] > 0) {
                    System.out.println(i + " " + target);
                    return;  // 找到解立即退出
                }
                arr[i]++;  // 回溯硬币i的使用
            }
        }

        // 无解情况
        System.out.println("No Solution");
    }
}
