package 字符串.template.最小表示法;

// 当字符串S中选定一个位置i满足S[i-n] + S[1 - i-1] = T,则T是S的循环同构串
// 也就是说字符串S在一个地方开始从前向后到尾再循环到那个地方组成的字符串为循环同构串
// 最小表示法就是找出字符串S的循环同构串中字典序最小的那个

// 对于循环串或者环问题，通常复制一遍，破环成链，然后扫描
// 指针i，j控制匹配的起始位置，k控制匹配长度
// 当不断匹配的过程中，i+k和j+k会出现字典序不一样大的情况
// 如果S[i+k] >S[j+k], S[i ~ i+k]之内的任何一个位置开始的循环同构串都会因为大而被淘汰掉，i直接跳到i+k+1位置继续比较

// 算法流程
// 把字符串复制一遍，破环成链
// 初始化指针i=1， j=2，匹配长度k=0
// 比较s[i+k]和s[j+k]是否相等
//   如果s[i+k] == s[j+k]，那么k++
//   如果s[i+k] > s[j+k],则i = i+k+1
//   如果s[i+k] < s[j+k],则j = j+k+1
//   如果跳转后两个指针位置相同，则j++，确保比较中两个字符串不同
// 答案为min(i, j)

import java.util.*;

public class Main {
    static final int N = 7*10^5 + 5;
    static int[] s = new int[N];
    static int n;
    static Scanner scanner = new Scanner(System.in);

    public static int get_min_char(){
        // 破环成链
        for(int i = 1; i <= n; i++) s[n + i] = s[i];

        int i = 1, j = 2, k = 0;
        while(i <= n && j <= n){
            for(k = 0; k < n && s[i + k] == s[j + k]; k++){
                // S[i ~ i+k]之内的任何一个位置开始的循环同构串都会因为大而被淘汰掉，i直接跳到i+k+1位置继续比较
                if(s[i + k] > s[j + k]) i = i + k + 1;
                else if(s[i + k] < s[j + k]) j = j + k + 1;
                if(i == j) j++;
            }
        }
        return Math.min(i, j);
    }

    public static void main(String[] args) {
        n = scanner.nextInt();
        for (int i = 1; i <= n; i++) s[i] = scanner.nextInt();

        int k = get_min_char();

        for(int i = 0;i < n; i++) System.out.println(s[k + i]);
    }


}
