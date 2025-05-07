package 基础算法和其他.排序.subject.P10901_蓝桥杯2024省C_封闭图形个数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 数字的大小不仅仅取决于它们的数值大小，还取决于它们所形成的“封闭图形”的个数
    // 封闭图形多的大，优先级是封闭图形，之后是数值较大的数更大
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private int n;
    private Integer[] arr;
    private int[] di = {1, 0, 0, 0, 1, 0, 1, 0, 2, 1};  // 封闭图形个数打表

    // 拆位求和
    private int getSum(int a){
        int sum = 0;
        while(a > 0){
            sum += di[a % 10];
            a /= 10;
        }
        return sum;
    }

    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        arr = new Integer[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        // 自定义排序，从小到大排序
        Arrays.sort(arr, (a, b) -> {
            int sumA = getSum(a);
            int sumB = getSum(b);

            // a的封闭数更少，a应该排在b前面（升序）
            if (sumA < sumB) {
                return -1;
            }else if(sumA > sumB){
                return 1;
            }else{
                return a - b;
            }
        });

        for (int num : arr) {
            System.out.print(num + " ");
        }


    }

    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }
        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        Long nextLong(){
            return Long.parseLong(next());
        }
    }
}