package 基础算法和其他.贪心.subject.P12173_蓝桥杯2025省PythonB_最多次数;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // lqb lbq qbl qlb blq bql 可以发现题目给出的是全排列
        // 遇到连续的 lqb 三个字母拿掉就行
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = {"lqb", "lbq", "qlb", "qbl", "blq", "bql"};
        int res = 0, index = 0;
        for(int i = 0; i < str.length() - 2; i++){   // 注意防止越界
            String sub = str.substring(i, i + 3);
            for(int j = 0; j < 6; j++){
                if(sub.equals(strs[j])){
                    i += 2;   // 不判断中间的内容了
                    res++;
                    break;
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}
