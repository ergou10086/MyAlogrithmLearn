package 搜索.DFS.template.DFS回溯.p1219_USACO_八皇后_Checker_Challenge;

import java.util.Scanner;

public class Main {
    static int n, ans = 0;
    static int[] a = new int[30];     // 行
    static int[] c = new int[30];     // 列
    static int[] p = new int[30];     // 左下到右上的对角线
    static int[] q = new int[30];     // 左上到右下的对角线；

    public static void dfs(int hang){
        if(hang > n){
            ans += 1;
            if(ans <= 3){
                for(int i = 1; i <= n; i++){
                    System.out.print(a[i] + " ");
                }
                System.out.println();
            }
        }

        for(int lie = 1; lie <= n; lie++){
            if(c[lie] == 1 || p[hang + lie] == 1 || q[hang - lie + n] == 1) continue;
            // 第hang行上的棋子放到了第lie列
            a[hang] = lie;
            c[lie] = p[hang + lie] =  q[hang - lie + n] = 1;   // 占领
            dfs(hang + 1);
            c[lie] = p[hang + lie] =  q[hang - lie + n] = 0;   // 回溯
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dfs(1);
        System.out.println(ans);
        sc.close();
    }
}
