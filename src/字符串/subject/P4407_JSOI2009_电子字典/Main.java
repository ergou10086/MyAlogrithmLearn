package 字符串.subject.P4407_JSOI2009_电子字典;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private int n, m, ans, tot = 1;
    private int[][] trie = new int[200010][26];
    private boolean[] end = new boolean[200010];
    private String string;

    // 插入操作，构建字典树
    private void insert(String str) {
        int p = 1, len = str.length();
        for (int i = 0; i < len; i++) {
            int ch = str.charAt(i) - 'a';
            if (trie[p][ch] == 0) {
                trie[p][ch] = ++tot;
            }
            p = trie[p][ch];
        }
        end[p] = true;
    }

    // 查找是否存在
    private boolean search(String str) {
        int p = 1, len = str.length();
        for (int i = 0; i < len; i++) {
            int ch = str.charAt(i) - 'a';
            p = trie[p][ch];
            if (p == 0) {
                return false;
            }
        }
        return end[p];
    }


    // 对于改变一个字符的情况的dfs
    // p是节点编号,l是当前匹配到的字符串位数,change记录是否操作过
    private void dfs_change(int p, int l, boolean changed){
        // 匹配到最后一位了，并且存在，加一
        if (l == string.length() && end[p]) {
            ans++;
            return;
        }

        // 这个操作过，就继续向下跑
        if (changed) {
            p = trie[p][string.charAt(l) - 'a'];
            if (p == 0) return;
            dfs_change(p, l + 1, changed);
        } else {
            // 下面的所有结点扫一遍
            for(int i = 0; i < 26; i++){
                if(trie[p][i] == 0) continue;
                // 当前字符和扫到的字符一样，向后移动
                if(i == string.charAt(l) - 'a'){
                    dfs_change(trie[p][i], l + 1, false);
                // 不一样，对这个单词操作
                }else{
                    dfs_change(trie[p][i], l + 1, true);
                }
            }
        }
    }

    // 对于删除一个字符的情况的dfs
    private void dfs_delete(int p, int l, boolean changed){
        // 到头了
        if(l == string.length() && end[p] && changed){
            ans++;
            return;
        }
        // 删除最后一位的特判
        if (l == string.length() - 1 && !changed && end[p]) {
            ans++;
            return;
        }
        // 已经改过了，忽略向下走
        if(changed){
            p = trie[p][string.charAt(l) - 'a'];
            if(p == 0) return;
            dfs_change(p, l + 1, changed);
        }else{
            for(int i = 0; i < 26; i++){
                if(trie[p][i] == 0) continue;
                if(i == string.charAt(l) - 'a'){
                    dfs_delete(trie[p][i], l + 1, false);
                }else{
                    dfs_delete(trie[p][i], l + 2, true);
                }
            }
        }
    }


    // 增加一个字符的情况
    private void dfs_add(int p, int l, boolean changed){
        // 到头了
        if(l == string.length() && changed && end[p]){
            ans++;
            return;
        }
        // 可以在最后一位增加，扫一遍所有字母看看有没有一样的
        if(l == string.length() && !changed){
            for(int i = 0; i < 26; i++){
                if(end[trie[p][i]]){
                    ans++;
                }
            }
            return;
        }

        if(changed) {
            p = trie[p][string.charAt(l) - 'a'];
            if (p == 0) return;
            dfs_change(p, l + 1, changed);
        }else{
            for(int i = 0; i < 26; i++){
                if(trie[p][i] == 0) continue;
                if (i == string.charAt(l) - 'a') {
                    dfs_add(trie[p][i], l + 1, false);
                // 存在在这个字符的下一个字符为i的单词
                }else if (trie[trie[p][i]][string.charAt(l) - 'a']!= 0) {
                    dfs_add(trie[trie[p][i]][string.charAt(l) - 'a'], l + 1, true);
                }
            }
        }

    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            string = sc.next();
            insert(string);
        }
        while(m-- > 0){
            String s = sc.next();
            if (search(s)) {
                System.out.println("-1");
                continue;
            }
            ans = 0;
            dfs_change(1, 0, false);
            dfs_delete(1, 0, false);
            dfs_add(1, 0, false);
            System.out.println(ans);
        }
    }



    private class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
