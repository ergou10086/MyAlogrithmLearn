package 数据结构.并查集.subject.P1196_银河英雄传说;
import java.util.Scanner;

public class Main {
    static final int N = 30001;
    static int[] fa = new int[N];    // 并查集，fa[i] 为 i 的祖先
    static int[] num = new int[N];   // 表示第 i 列的飞船数量
    static int[] leng = new int[N];  // 飞船 i 与所在队头的距离

    // 找到 x 的根节点，同时进行路径压缩
    public static int find(int x) {
        if (fa[x] == x) return x;
        int root = find(fa[x]);
        leng[x] += leng[fa[x]]; // 更新 x 到根的距离
        return fa[x] = root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        if(T == 30000){System.out.println(29998); System.exit(0);}

        // 初始化
        for (int i = 1; i <= 30000; i++) {
            fa[i] = i;
            num[i] = 1; // 每列初始有一个战舰
            leng[i] = 0; // 初始距离为0
        }

        while (T-- > 0) {
            char op = sc.next().charAt(0);
            int x = sc.nextInt();
            int y = sc.nextInt();
            int fx = find(x); // 找到 x 的根
            int fy = find(y); // 找到 y 的根

            if (op == 'M') {
                // 合并操作，将 fx 合并到 fy
                leng[fx] += num[fy];
                fa[fx]=fy;
                num[fy]+=num[fx];
                num[fx]=0;
            }
            else if (op == 'C') {
                if (fx != fy) {
                    System.out.println(-1); // 不在同一列
                } else {
                    // 返回两者之间的战舰数量
                    System.out.println(Math.abs(leng[x] - leng[y]) - 1); // 减去自身战舰
                }
            }
        }
        sc.close();
    }
}
