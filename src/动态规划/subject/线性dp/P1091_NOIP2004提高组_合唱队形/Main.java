package 动态规划.subject.线性dp.P1091_NOIP2004提高组_合唱队形;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
        }

        // 步骤1：计算left数组（每个位置的左侧最长严格上升子序列长度）
        int[] left = getLIS(t);

        // 步骤2：计算right数组（每个位置的右侧最长严格下降子序列长度）
        int[] reversedT = reverseArray(t); // 反转数组
        int[] reversedRight = getLIS(reversedT); // 反转数组的LIS = 原数组的LDS
        int[] right = reverseArray(reversedRight); // 再反转回来，得到right数组

        // 步骤3：找最长合唱队形长度
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int current = left[i] + right[i] - 1;  // 峰值重复计算，减1
            if (current > maxLen) maxLen = current;
        }

        // 步骤4：最少出列人数 = 总人数 - 最长合唱队形长度
        System.out.println(n - maxLen);
        sc.close();
    }

    // 二分贪心计算最长严格上升子序列（LIS）的长度数组
    private static int[] getLIS(int[] t) {
        int n = t.length;
        // 存储每个位置的LIS长度
        int[] lenArr = new int[n];
        // 辅助数组：tails[k]是长度为k+1的LIS的最小结尾
        int[] tails = new int[n];
        // 指针，当前tails的有效长度
        int len = 0;

        for(int i = 0; i < n; i++) {
            int x = t[i];
            // 二分查找tails中第一个 >= x的位置（严格上升，所以找>=）
            int l = -1, r = len;

            while (l + 1 < r) {
                int mid = l + r >> 1;
                if(tails[mid] >= x) {
                    r = mid;
                }else{
                    l = mid;
                }
            }

            tails[r] = x;
            // 更新当前位置的LIS长度（r+1，因为tails[0]对应长度1）
            lenArr[i] = r + 1;
            // 记得更新浮标
            if (r == len) {
                len++;
            }
        }

        return lenArr;
    }


    // 反转数组（用于计算LDS）
    private static int[] reverseArray(int[] arr) {
        int n = arr.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = arr[n - 1 - i];
        }
        return reversed;
    }
}
