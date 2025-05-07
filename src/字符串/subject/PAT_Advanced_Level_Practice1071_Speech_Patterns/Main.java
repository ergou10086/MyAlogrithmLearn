package 字符串.subject.PAT_Advanced_Level_Practice1071_Speech_Patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    public Solutions() {
        FastReader sc = new FastReader();
        String line = sc.nextLine();
        // System.out.println(line.charAt(5));

        Map<String, Integer> map = new HashMap<>();
        StringBuilder curr = new StringBuilder();

        for (char c : line.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                curr.append(Character.toLowerCase(c));   // The word should be printed in all lower case.
            }else{
                // meet block or other char, put word in map
                if(curr.length() > 0) {
                    String word = curr.toString();
                    map.put(word, map.getOrDefault(word, 0) + 1);
                    curr.setLength(0);
                }
            }
        }

        // dispose final word
        if(curr.length() > 0) {
            String word = curr.toString();
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        String result = "";
        int maxCount = 0;
        // following the map set
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > maxCount || (entry.getValue() == maxCount && entry.getKey().compareTo(result) < 0)) {
                maxCount = entry.getValue();
                result = entry.getKey();
            }
        }

        System.out.println(result + " " + maxCount);
    }


    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}