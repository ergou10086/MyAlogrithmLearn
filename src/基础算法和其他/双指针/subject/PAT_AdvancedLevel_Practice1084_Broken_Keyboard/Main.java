package 基础算法和其他.双指针.subject.PAT_AdvancedLevel_Practice1084_Broken_Keyboard;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String original = scanner.nextLine().toUpperCase();  // 统一转为大写处理
        String typed = scanner.nextLine().toUpperCase();

        Set<Character> wornKeys = new HashSet<>();
        StringBuilder result = new StringBuilder();
        int typedIndex = 0;  // 记录已匹配的实际输入字符位置

        for (int i = 0; i < original.length(); i++) {
            char currentChar = original.charAt(i);

            // 当实际输入未越界且字符匹配时，移动实际输入指针
            if (typedIndex < typed.length() && currentChar == typed.charAt(typedIndex)) {
                typedIndex++;
            } else {
                // 仅当该坏键未被记录时，添加到结果中
                if (!wornKeys.contains(currentChar)) {
                    wornKeys.add(currentChar);
                    result.append(currentChar);
                }
            }
        }

        System.out.println(result.toString());
    }
}

/*
7_This_is_a_test
_hs_s_a_es
 */