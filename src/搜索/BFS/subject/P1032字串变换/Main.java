package 搜索.BFS.subject.P1032字串变换;

import java.util.*;

public class Main {
    static String a, b;                // 字符串A与字符串B
    static String[] sa = new String[8]; // 存放转换方式
    static String[] sb = new String[8]; // 存放转换后的字符串
    static Map<String, Integer> map1 = new HashMap<>(); // 存放已经宽搜过的字符串
    static int way;                       // 转换方式的数量
    static Queue<String> q = new LinkedList<>(); // 存放转换出来的字符串
    static Queue<Integer> step_q = new LinkedList<>(); // 存放当前转换出的字符串已经使用的步数

    public static int bfs() {
        while (!q.isEmpty()) {
            String currentString = q.poll(); // 获取当前字符串
            int currentStep = step_q.poll(); // 获取当前步数

            // 如果当前字符串等于目标字符串，返回步数
            if (currentString.equals(b)) {
                return currentStep;
            }

            // 限制步数不超过10
            if (currentStep >= 10) {
                continue; // 超过10步不处理
            }

            // 遍历所有转换规则
            for (int i = 0; i < way; i++) {
                String s = currentString;
                int index = s.indexOf(sa[i]);

                // 替换所有可能的子串
                while (index != -1) {
                    // 替换子串
                    String newString = s.substring(0, index) + sb[i] + s.substring(index + sa[i].length());
                    // 如果新的字符串没有被处理过，则添加到队列中
                    if (!map1.containsKey(newString)) {
                        q.add(newString);
                        step_q.add(currentStep + 1);
                        map1.put(newString, 1); // 记录新的字符串
                    }
                    // 继续查找下一个位置
                    index = s.indexOf(sa[i], index + 1); // 从下一个位置查找
                }
            }
        }
        return -1; // 如果队列为空，返回-1
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        a = scanner.next(); // 读入字符串A
        b = scanner.next(); // 读入字符串B
        way = 0;

        // 读入转换方式
        while (scanner.hasNext()) {
            sa[++way] = scanner.next(); // 读取sa
            sb[way] = scanner.next();   // 读取sb
        }

        if (way == 0 && !a.equals(b)) {
            System.out.println("NO ANSWER!");
            return;
        }

        q.add(a);       // 将字符串a压入队列
        step_q.add(0);  // 将初始步数0压入队列
        map1.put(a, 1); // 记录初始字符串

        int result = bfs();
        if (result == -1) {
            System.out.println("NO ANSWER!");
        } else {
            System.out.println(result); // 输出最小步数
        }
    }
}
