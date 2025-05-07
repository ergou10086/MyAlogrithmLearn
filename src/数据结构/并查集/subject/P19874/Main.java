package 数据结构.并查集.subject.P19874;

import java.util.Scanner;

public class Main {

    static int N = 100010;
    static int[]p = new int[100010];

    // 寻找 x 的根节点
    public static int findRoot(int x) {
        if(x!=p[x]) p[x] = findRoot(p[x]);
        return p[x];
    }

    // 合并
    public static void merge(int x, int y){
        x = findRoot(x);
        y = findRoot(y);
        p[x] = y;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();

        // 初始化 p[i] = i，表示每个节点的父节点是自己
        for (int i = 0; i <= n; i++) {
            p[i] = i;
        }

        for(int i = 1; i <= m; i++) {
            char op = scan.next().charAt(0);
            int a = scan.nextInt(), b = scan.nextInt();

            if(op == 'M') {
                merge(a,b);
            }else{
                if(findRoot(a)==findRoot(b)) System.out.println("Yes");
                else System.out.println("No");
            }
        }

        scan.close();
    }
}
