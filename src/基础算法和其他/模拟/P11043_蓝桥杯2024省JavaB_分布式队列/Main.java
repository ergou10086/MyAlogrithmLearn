package 基础算法和其他.模拟.P11043_蓝桥杯2024省JavaB_分布式队列;
import java.util.Arrays;
import java.util.Scanner;

// 史上最纱布的题目
// add element是主节点插入element
// sync follower_id 表示同步主节点，其实就是把 follower_id 的结点从主节点中拿过来自己没有的值
// 元素添加到主节点后，需要同步到所有的副节点后，才具有可见性
// query代表最短的副节点长度

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] main_a = new boolean[200005];   // 模拟主节点
        Arrays.fill(main_a, false);
        int[] cnt = new int[200005];   // 模拟各个副节点

        while(sc.hasNext()) {
            String op = sc.next();
            int main_point = 0;   // 模拟主节点的指针
            int point = 0;    // 模拟副节点的指针

            if(op.equals("add")) {
                int pot = sc.nextInt();
                main_a[pot] = true;
                cnt[0]++;
            }
            else if(op.equals("query")) {
                int res = cnt[0];
                for (int i = 1; i < n; i++) {
                    res = Math.min(res, cnt[i]);
                }
                System.out.println(res);
            }
            else if(op.equals("sync")) {
                int std = sc.nextInt();
                //副队列同步主队列，如果在执行这次操作时副队列已经是跟主队列相同了，此时再加1并不合理，最高就是跟主队列相同
                cnt[std] = Math.min(cnt[0], cnt[std] + 1);
            }
        }
        sc.close();
    }
}
