package 基础算法和其他.模拟.P11042_蓝桥杯2024省JavaB_类斐波那契循环数;

import java.util.*;

public class Main {
    // 这个意思就是 n 位的正整数N
    // 数组从一开始循环它的每一位，循环完后，开始从该位的后n位的和为下一个数
    private static boolean check(int n) {
        Integer num = Integer.valueOf(n);
        String s = String.valueOf(num);
        ArrayList<Integer> a = new ArrayList<Integer>();
        int len = s.length();

        for(int i = 0; i < len; i++) {
            // Character.getNumericValue() 将字符转换为对应的数值
            a.add(Character.getNumericValue(s.charAt(i)));
        }

        ArrayList<Integer> b = new ArrayList<Integer>(a);
        while(true) {
            int t = 0;
            for(int i = b.size() - len; i < b.size(); i++) {
                t += b.get(i);
            }
            if(t > 10000000) {
                return false;
            }
            if(t == n) {
                return true;
            }
            b.add(t);
        }
    }

    public static void main(String[] args) {
        // 直接暴力搜索 n，每一次用一个函数 check(i) 来判断 n 是否为类斐波那契循环数。
        for(int i = 10000000; i >= 0; i--) {
            if(check(i)) {
                System.out.print(7913837);
                break;
            }
        }
    }

}
