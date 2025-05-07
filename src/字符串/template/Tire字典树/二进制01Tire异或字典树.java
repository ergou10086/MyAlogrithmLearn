package 字符串.template.Tire字典树;

import java.util.Scanner;

public class 二进制01Tire异或字典树 {
    static final int N = 100010;
    static int n, cnt = 0;
    static int[] a = new int[N];
    static int[][] ch = new int[N * 31][2];

    // 创建01Tire，插入数
    public static void insert(int x){
        // 从高位向低位创建
        int p = 0;
        for (int i = 30; i >= 0; i--) {
            int j = x >> i & 1;   // 取出第i位
            if(ch[p][j] == 0) ch[p][j] = ++cnt;
            p = ch[p][j];
        }
    }

    // 查询,做计算
    // 异或相同则结果为0，不同则结果为1
    public static int query(int x){
        int p = 0, res = 0;
        for (int i = 30; i >= 0; i--) {
            // 取出这一位是0或1
            int j = x >> i & 1;
            // 在分界点处,尽量找相反的，这样结果最大
            // 有相反位走相反位，没有就走相同的，这样累加边权可以找到最大异或
            if(ch[p][1 - j] != 0){
                res += 1 << i;  // 累加位权
                p = ch[p][1 - j];
            }else{
                p = ch[p][j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            insert(a[i]);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, query(a[i]));
        }

        System.out.println(ans);
        scanner.close();
    }
}
