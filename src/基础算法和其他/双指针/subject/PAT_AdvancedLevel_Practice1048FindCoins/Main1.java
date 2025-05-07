package 基础算法和其他.双指针.subject.PAT_AdvancedLevel_Practice1048FindCoins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1 {
    // 双指针解法
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        int[] coins = new int[N];
        String[] coinValues = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(coinValues[i]);
        }
        Arrays.sort(coins);

        int left = 0, right = N - 1;

        while (left < right) {
            int sum = coins[left] + coins[right];
            if(sum == M){
                System.out.println(coins[left] + " " + coins[right]);
                return;
            }else if(sum < M){
                left++;
            }else {
                right--;
            }
        }

        System.out.println("No Solution");
    }
}
