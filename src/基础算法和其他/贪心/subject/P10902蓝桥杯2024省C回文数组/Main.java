package 基础算法和其他.贪心.subject.P10902蓝桥杯2024省C回文数组;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 贪心，加到中位数，如果是偶数就是加到较大的那个
// 很显然，我们可以只处理前半部分，统计单个元素的操作次数即可

class Solutions{

    public Solutions(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[100005];
        for (int i = 1; i <= n; i++) arr[i] = sc.nextInt();

        // 存差值，也就是需要处理的次数
        long[] arrb = new long[100005];
        // 回文数组考虑第 i 个数和第 n−i+1 是一样的
        for (int i = 1; i <= n / 2; i++) {
            arrb[i] = arr[n - i + 1] - arr[i];
        }

        long ans = 0;
        for(int i = 1; i <= n / 2; i++){
            ans += Math.abs(arrb[i]);
            // 如果两个符号一样，同时处理
            if(arrb[i] > 0 && arrb[i + 1] > 0){
                arrb[i + 1] -= Math.min(arrb[i], arrb[i + 1]);
            }
            if(arrb[i] < 0 && arrb[i + 1] < 0){
                arrb[i + 1] -= Math.max(arrb[i], arrb[i + 1]);
            }
        }

        System.out.println(ans);
        sc.close();
    }
}