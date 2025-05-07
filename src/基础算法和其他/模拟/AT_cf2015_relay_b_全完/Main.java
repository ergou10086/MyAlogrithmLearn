package 基础算法和其他.模拟.AT_cf2015_relay_b_全完;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] a = new char[11][11];
        boolean[] b = new boolean[11];
        Scanner scanner = new Scanner(System.in);

        // 循环读取输入字符，构建二维数组，并标记每列是否有'o'
        for (int i = 0; i < 10; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 10; j++) {
                a[i][j] = line.charAt(j);
                if (a[i][j] == 'o') {
                    b[j] = true;
                }
            }
        }

        boolean canAnswerAll = true;
        // 遍历判断每列是否都至少有一个人会（即对应元素为true）
        for (int i = 0; i < 10; i++) {
            if (!b[i]) {
                canAnswerAll = false;
                break;
            }
        }

        if (canAnswerAll) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        scanner.close();
    }
}