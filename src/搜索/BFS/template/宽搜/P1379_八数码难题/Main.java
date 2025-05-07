package 搜索.BFS.template.宽搜.P1379_八数码难题;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 移动数字相当于是移动空格
// 本质是看空格怎么走
// 一个布局对应一个字符串，对应一个状态点，从一个布局到另一个布局对应走过了一次
//

class Solutions{
    private String s;
    // 存储每个状态的步数，键值对映射
    private Map<String, Integer> step = new HashMap<String, Integer>();
    // 用于 BFS 的队列
    private Queue<String> q = new LinkedList<>();
    // 探照灯
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};

    private int bfs(String s){
        q.offer(s);
        step.put(s, 0);
        String res = "123804765";
        while(!q.isEmpty()){
            // 取队头
            String str = q.poll();
            if(str.equals(res)){
                return step.get(str);
            }

            // 找到 0 的位置
            int k = str.indexOf('0'); // 使用当前状态 str
            // 计算 0 在 3x3 矩阵中的 x 和 y 坐标
            int x = k / 3;
            int y = k % 3;

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i];
                int b = y + dy[i];
                // 越界
                if (a < 0 || a >= 3 || b < 0 || b >= 3) continue;

                // 创建一个可变的字符串用于交换字符
                StringBuilder sb = new StringBuilder(str);
                // 每次循环都会重新创建 StringBuilder，因此不需要还原状态。
                char temp = sb.charAt(k);
                // a*3+b代表要交换的字符串从二维到一维的位置
                int swapPos = a * 3 + b;
                sb.setCharAt(k, sb.charAt(swapPos));
                sb.setCharAt(swapPos, temp);

                // 如果新状态未被访问过
                String newState = sb.toString();
                if (!step.containsKey(newState)) {
                    step.put(newState, step.get(str) + 1);
                    q.offer(newState);
                }
            }
        }
        return -1;
    }

    public Solutions() {
        Scanner sc = new Scanner(System.in);
        s = sc.next(); // 直接读取整个输入
        System.out.println(bfs(s));
        sc.close();
    }
}