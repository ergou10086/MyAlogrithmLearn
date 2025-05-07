package 数据结构.线性数据结构.栈.template.B3614_模板_栈;

import java.util.Scanner;
import java.util.Stack;

public class Mainjavalang方法 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long T = scanner.nextInt();

        for (int i = 0; i < T; i++) {
            Stack<Long> stack = new Stack<>();
            long n = scanner.nextInt();

            for (int j = 0; j < n; j++) {
                String operation = scanner.next();

                if ("push".equals(operation)) {
                    long x = scanner.nextLong();
                    stack.push(x);
                } else if ("pop".equals(operation)) {
                    if (stack.isEmpty()) {
                        System.out.println("Empty");
                    } else {
                        stack.pop();
                    }
                } else if ("query".equals(operation)) {
                    if (stack.isEmpty()) {
                        System.out.println("Anguei!");
                    } else {
                        System.out.println(stack.peek());
                    }
                } else if ("size".equals(operation)) {
                    System.out.println(stack.size());
                }
            }
        }

        scanner.close();
    }
}
