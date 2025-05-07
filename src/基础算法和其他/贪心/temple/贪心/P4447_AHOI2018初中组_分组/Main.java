package 基础算法和其他.贪心.temple.贪心.P4447_AHOI2018初中组_分组;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 手写upperBound，查找第一个大于目标值的位置
    private static int upperBound(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] <= target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];   // 第i个队员实力
        for (int i = 1; i <= n; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr, 1, n + 1);

        int[] q = new int[n + 1];  // 记录每组最大实力值
        int[] siz = new int[n + 1];  // 记录每组的人数

        // 要求实力连续，所以每个位置二分查找最大实力为a[i]-1的最后一组
        // 相等的话，直接加进去

        // 哨兵，保证k=0时候能新开一组
        q[0] = 876451230;
        int cnt = 0, ans = 876451230;   // 组数和答案
        for (int i = 1; i <= n; i++) {
            // 从1开始找，到组数cnt,二分查找最大实力为a[i]-1的最后一组的组数
            int k = upperBound(q, 1, cnt + 1, arr[i] - 1) - 1;
            //System.out.println(k);
            // 接入第k组
            if(q[k] == arr[i] - 1){
                q[k] = arr[i];
                siz[k]++;
            // 新开一组
            }else{
                q[++cnt] = arr[i];
                siz[cnt] = 1;
            }
        }

        for(int i = 1; i <= cnt; i++){
            ans = Math.min(ans, siz[i]);
        }

        System.out.println(ans);
        sc.close();
    }
}
