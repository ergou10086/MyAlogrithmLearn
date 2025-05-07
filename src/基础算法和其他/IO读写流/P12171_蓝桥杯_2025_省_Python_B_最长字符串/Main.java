package 基础算法和其他.IO读写流.P12171_蓝桥杯_2025_省_Python_B_最长字符串;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //  读取 words.txt 文件中的单词
        List<String> words = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("D:\\WorkSpace\\JavaDemo\\IDEA\\Algorithm\\src\\字符串\\subject\\P12171_蓝桥杯2025省PythonB最长字符串\\words.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        // 按长度升序、字典序升序排序（确保处理顺序正确）
        words.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));

        Set<String> bs = new HashSet<>();

        // 动态规划：判断每个单词是否是优美字符串
        for(String word : words){
            if(word.length() == 1){
                bs.add(word);
            }else{
                // 如果 前n-1 位的长度的字符串，我们把其和要对比的字符串按一定规律重组，那么两者就是一样的了
                String pre = sortString(word.substring(0, word.length() - 1));
                for(String sbs: bs){
                    if(sortString(sbs).equals(pre)){
                        bs.add(word);
                        break;
                    }
                }
            }
        }

        // 找到最长的优美字符串
        int maxLength = 0;
        for (String word : bs) {
            maxLength = Math.max(maxLength, word.length());
        }

        List<String> ls = new ArrayList<>();
        for (String word : bs) {
            if (word.length() == maxLength) {
                ls.add(word);
            }
        }

        // 输出字典序最小的结果
        ls.sort(Comparator.naturalOrder());
        System.out.println(ls.get(0));

        // System.out.println("afplcu");
    }

    // 字符串按字母字典序重组
    private static String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
