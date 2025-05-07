package 搜索.迭代加深.Luogu_UVA529_Addition_Chains;

// 搜索树的分支非常多，问题的答案在某个较浅的节点上，如果一开始深搜搜错了，就会出现浪费
// 可以从小到大限制搜索深度，如果在当前深度下搜不到答案，九八深度增加一层，重新搜索一遍

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// a[u] = a[u-1] + a[i] i <= u-1

class Solutions{
    // n 表示目标值，d 表示搜索深度
    private int n, d;
    // 数组 a 用于存储加成序列
    private int[] a = new int[10005];

    // 搜索第level层
    private boolean dfs(int level){
        if(level == d){
            // 最后一个元素是否等于目标值 n
            return a[level - 1] == n;
        }

        // 优化搜索顺序，从 u - 1 开始往前遍历
        for (int i = level - 1; i >= 0; i--) {
            int t = a[level - 1] + a[i];
            // 越界剪枝
            if (t > n) {
                continue;
            }

            a[level] = t;

            // 估价未来，如果未来即使每次都翻倍也无法达到目标值 n，剪枝
            int temp = t;
            for(int j = level + 1; j <= d; j++){
                temp *= 2;
            }
            if(temp < n){
                return false;
            }

            // 搜下一层
            if(dfs(level + 1)){
                return true;
            }
        }
        return false;
    }

    public Solutions(){
        Scanner scanner = new Scanner(System.in);
        // 加成序列的第一个元素为 1
        a[0] = 1;
        while (true) {
            // 读取输入的目标值 n
            n = scanner.nextInt();
            if (n == 0) {
                break;
            }

            // 迭代搜索板子部分
            // 初始搜索深度为 1
            d = 1;
            // 如果搜索失败，增加搜索深度
            while (!dfs(1)) {
                d++;
            }

            // 输出加成序列
            for (int i = 0; i < d; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
    }
}