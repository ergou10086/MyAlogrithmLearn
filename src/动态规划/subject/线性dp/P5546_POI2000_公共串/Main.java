package 动态规划.subject.线性dp.P5546_POI2000_公共串;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // Java21要消费换行符

        // 读取所有字符串
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = scanner.nextLine();
        }

        // 找到最短字符串（减少枚举量）
        int minLen = strs[0].length();
        String shortest = strs[0];
        for (String s : strs) {
            if (s.length() < minLen) {
                minLen = s.length();
                shortest = s;
            }
        }

        // 按长度从大到小枚举最短字符串的所有子串
        for (int l = minLen; l >= 1; l--) { // l是子串长度
            // 枚举所有可能的起始位置
            for (int i = 0; i <= shortest.length() - l; i++) {
                String sub = shortest.substring(i, i + l); // 当前子串
                boolean allContains = true;

                // 使用KMP算法检查该子串是否在所有其他字符串中存在
                for (String s : strs) {
                    if (!kmpContains(s, sub)) {
                        allContains = false;
                        break;
                    }
                }

                // 若所有字符串都包含该子串，直接返回长度l
                if (allContains) {
                    System.out.println(l);
                    return;
                }
            }
        }

        // 无公共子串
        System.out.println(0);
    }


    // KMP算法：检查主串s中是否包含模式串pattern
    private static boolean kmpContains(String s, String pattern) {
        if (pattern.isEmpty()) return true;
        if (s.length() < pattern.length()) return false;

        int[] next = getNext(pattern);
        int j = 0; // 模式串指针

        for (int i = 0; i < s.length(); i++) {
            // 失配时回退
            while (j > 0 && s.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // 匹配成功，模式串指针前进
            if (s.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 完全匹配
            if (j == pattern.length()) {
                return true;
            }
        }
        return false;
    }

    // 计算KMP的next数组
    private static int[] getNext(String pattern) {
        int n = pattern.length();
        int[] next = new int[n];
        int j = 0; // 前缀指针

        for (int i = 1; i < n; i++) {
            // 失配时回退
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // 匹配成功，前缀长度加1
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
