package 基础算法和其他.贪心.subject.P11044_蓝桥杯2024省JavaB_食堂;

import java.util.Scanner;

// 我测，dp
// 其实可以贪心
// 四人桌可以坐4，2+2，3余1，2余2
// 六人桌可以坐4+2，3+3，2+2+2，2+3余1，4+0余2，3+0余3，2+0余4
// 优先考虑六人桌

//dp[i][j][k] = max(
//	dp[i-1][j][k],  // 不选当前寝室
//  dp[i-1][j - x][k - y] + students  // 选当前寝室，x 和 y 是使用的桌子数量
// )

// ctrl + shift + f格式化

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int a2 = scanner.nextInt();
            int a3 = scanner.nextInt();
            int a4 = scanner.nextInt();
            int b4 = scanner.nextInt();
            int b6 = scanner.nextInt();

            int sum = 0;
            // 满座匹配4人寝坐4人桌
            while (b4 > 0 && a4 >= 1) {
                b4--;
                a4--;
                sum += 4;
            }
            // 满座匹配2x2人寝坐4人桌
            while (b4 > 0 && a2 >= 2) {
                b4--;
                a2 -= 2;
                sum += 4;
            }
            // 满座匹配2+4人寝坐6人桌
            while (b6 > 0 && a4 >= 1 && a2 >= 1) {
                b6--;
                a4--;
                a2--;
                sum += 6;
            }
            // 满座匹配2x3人寝坐6人桌
            while (b6 > 0 && a3 >= 2) {
                b6--;
                a3 -= 2;
                sum += 6;
            }
            // 满座匹配3x2人寝坐6人桌
            while (b6 > 0 && a2 >= 3) {
                b6--;
                a2 -= 3;
                sum += 6;
            }
            // 空1座匹配2+3人寝坐6人桌
            while (b6 > 0 && a2 >= 1 && a3 >= 1) {
                b6--;
                a2--;
                a3--;
                sum += 5;
            }
            // 空1座匹配3人寝坐4人桌
            while (b4 > 0 && a3 > 0) {
                b4--;
                a3--;
                sum += 3;
            }
            // 空2座匹配2x2人寝坐6人桌
            while (b6 > 0 && a2 >= 2) {
                b6--;
                a2 -= 2;
                sum += 4;
            }
            // 空2座匹配4人寝坐6人桌
            while (b6 > 0 && a4 > 0) {
                b6--;
                a4--;
                sum += 4;
            }
            // 空2座匹配2人寝坐4人桌
            while (b4 > 0 && a2 > 0) {
                b4--;
                a2--;
                sum += 2;
            }
            // 空3座匹配3人寝坐6人桌
            while (b6 > 0 && a3 > 0) {
                b6--;
                a3--;
                sum += 3;
            }
            // 空4座匹配2人寝坐6人桌
            while (b6 > 0 && a2 > 0) {
                b6--;
                a2--;
                sum += 2;
            }
            System.out.println(sum);
        }
    }

}


/*
private static int calculateMaxStudents(int a2, int a3, int a4, int b4, int b6) {
	// DP 表：dp[i][j][k] 表示使用前 i 种寝室，j 个四人桌，k 个六人桌时的最大学生数量
    int[][][] dp = new int[4][b4 + 1][b6 + 1];

    for(int i = 1; i <= 3; i++) {
    	for(int j = 0; j <= b4; j++) {
    		for(int k = 0; k <= b6; k++) {
    			// 不选当前寝室
                dp[i][j][k] = dp[i - 1][j][k];

                // 选
                if(i == 1)
    		}
    	}
    }

}、
*/
