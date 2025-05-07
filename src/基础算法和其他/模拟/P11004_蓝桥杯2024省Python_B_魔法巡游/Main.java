package 基础算法和其他.模拟.P11004_蓝桥杯2024省Python_B_魔法巡游;

// 给出两个si和ti，要求 si1,ti2,si3,ti4,⋯，使得 i1<i2<i3<i4<⋯，且序列中所有元素的值包含 0,2,4
// 注意，相邻的元素间不需相同的共鸣元素，有共鸣元素即可。 所以求该序列最长是多少。

import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] si = new int[n + 1];
        int[] ti = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            si[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            ti[i] = sc.nextInt();
        }

        // 轮流取
        int inp = 1;
        int count = 0;

        for(int i = 1; i <= n; i++){
            boolean flag = false;
            int temp;

            if(inp % 2 == 1){
                temp = si[i];
            }else{
                temp = ti[i];
            }

            // 处理数字0的情况
            if (temp == 0) {
                flag = true;
            } else {
                // 检查每一位
                int std = temp;
                while(std != 0){
                    int digit = std % 10;
                    if (digit == 0 || digit == 2 || digit == 4) {
                        flag = true;
                        break;
                    }
                    std /= 10;
                }
            }

            if(flag){
                count++;
                inp++;
            }
        }

        System.out.println(count);

        sc.close();
    }
}
