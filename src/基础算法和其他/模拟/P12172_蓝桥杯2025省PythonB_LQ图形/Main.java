package 基础算法和其他.模拟.P12172_蓝桥杯2025省PythonB_LQ图形;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int v = sc.nextInt();
        // 先是h高w宽的Q，然后是w高v+w宽
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 1; i <= w; i++) sb1.append('Q');
        for(int i = 1; i <= v + w; i++) sb2.append('Q');
        for(int i = 1; i <= h; i++) System.out.println(sb1);
        for(int i = 1; i <= w; i++) System.out.println(sb2);
    }
}
