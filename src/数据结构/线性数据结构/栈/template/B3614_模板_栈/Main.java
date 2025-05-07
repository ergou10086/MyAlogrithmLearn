package 数据结构.线性数据结构.栈.template.B3614_模板_栈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Stack {
    private long[] arr;
    private long top;

    public Stack() {
        arr = new long[100086]; // 可根据实际需求调整栈的初始大小
        top = -1;
    }

    // 入栈操作
    public void push(long x) {
        top++;
        arr[(int)top] = x;
    }

    // 出栈操作
    public void pop() {
        if (isEmpty()) {
            System.out.println("Empty");
        } else {
            top--;
        }
    }

    // 查询栈顶元素
    public void query() {
        if (isEmpty()) {
            System.out.println("Anguei!");
        } else {
            System.out.println(arr[(int)top]);
        }
    }

    // 获取栈的大小
    public void size() {
        System.out.println(top + 1);
    }

    private boolean isEmpty() {
        return top == -1;
    }
}

class FastReader {
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

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        long T = fr.nextInt();

        for (int i = 0; i < T; i++) {
            Stack stack = new Stack();
            long n = fr.nextInt();

            for (int j = 0; j < n; j++) {
                String operation = fr.next();

                if ("push".equals(operation)) {
                    long x = fr.nextInt();
                    stack.push(x);
                } else if ("pop".equals(operation)) {
                    stack.pop();
                } else if ("query".equals(operation)) {
                    stack.query();
                } else if ("size".equals(operation)) {
                    stack.size();
                }
            }
        }
    }
}