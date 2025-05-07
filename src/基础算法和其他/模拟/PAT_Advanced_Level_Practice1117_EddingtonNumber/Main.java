package 基础算法和其他.模拟.PAT_Advanced_Level_Practice1117_EddingtonNumber;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // E天的骑行距离超过E英里
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = scanner.nextInt();
        scanner.close();

        Arrays.sort(arr);

        for(int j = n - 1, ans = 1; j >= 0; j--, ans++){
            if(arr[j] <= ans){
                System.out.println(ans - 1);
                break;
            }
        }
    }
}
