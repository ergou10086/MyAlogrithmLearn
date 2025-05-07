package 搜索.BFS.template.双向BFS.P1032_NOIP2002提高组_字串变换;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int N = 7;
    static int n = 0;
    static String A, B;
    static String[] a = new String[N];
    static String[] b = new String[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        A = firstLine[0];
        B = firstLine[1];

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(" ");
            if (parts.length < 2) continue;
            a[n] = parts[0];
            b[n] = parts[1];
            n++;
        }

        int t = bfs();
        System.out.println(t > 10 ? "NO ANSWER!" : t);
    }

    private static int extend(Queue<String> q, Map<String, Integer> da,
                              Map<String, Integer> db, String[] a, String[] b) {
        int m = q.size();
        while (m-- > 0) {
            String f = q.poll();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < f.length(); j++) {
                    if (j + a[i].length() <= f.length() &&
                            f.substring(j, j + a[i].length()).equals(a[i])) {
                        String s = f.substring(0, j) + b[i] + f.substring(j + a[i].length());
                        if (da.containsKey(s)) continue;
                        if (db.containsKey(s)) return da.get(f) + db.get(s) + 1;
                        da.put(s, da.get(f) + 1);
                        q.add(s);
                    }
                }
            }
        }
        return 11;
    }

    private static int bfs() {
        if (A.equals(B)) return 0;

        Queue<String> qa = new LinkedList<>();
        Queue<String> qb = new LinkedList<>();
        Map<String, Integer> da = new HashMap<>();
        Map<String, Integer> db = new HashMap<>();

        qa.add(A); da.put(A, 0);
        qb.add(B); db.put(B, 0);

        int step = 0;
        while (step++ < 10) {
            int t;
            if (qa.size() <= qb.size()) {
                t = extend(qa, da, db, a, b);
            } else {
                t = extend(qb, db, da, b, a);
            }
            if (t <= 10) return t;
        }
        return 11;
    }
}