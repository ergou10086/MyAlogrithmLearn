package 数学.subject.P1512_伊甸园日历游戏;

// 特判 m=9,d=30，此时为必胜。
// 特判 m=11,d=30，此时为必胜。
// 判断 (m+d)mod2=0此时为必胜

// 也就是说，从有30号的月份的偶数天开始加是必胜
// 有31号（29号）的月份的奇数天开始加是必胜
// 因为只要隔开三天，就可以

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] year = new int[n];
        int[] month = new int[n];
        int[] day = new int[n];

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            year[i] = Integer.parseInt(input[0]);
            month[i] = Integer.parseInt(input[1]);
            day[i] = Integer.parseInt(input[2]);
        }

        for (int i = 0; i < n; i++) {
            if ((month[i] == 9 && day[i] == 30) || (month[i] == 11 && day[i] == 30) || ((month[i] + day[i]) % 2 == 0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        scanner.close();
    }
}
