package 字符串.subject.PAT_Advanced_Level_Practice1001;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int sum = a + b;

        String numStr = String.valueOf(Math.abs(sum));
        int length = numStr.length();

        StringBuilder sb = new StringBuilder();

        if(sum < 0){
            sb.append("-");
        }

        int re = length % 3;
        if (re != 0) {
            sb.append(numStr, 0, re);
            if (length > re) {
                sb.append(",");
            }
        }

        for (int i = re; i < length; i += 3) {
            sb.append(numStr, i, i + 3);
            if(i + 3 < length){
                sb.append(",");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
