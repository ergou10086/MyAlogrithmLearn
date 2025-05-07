package 基础算法和其他.差分.subject.LuoguP4552_Poetize6_IncDec_Sequence;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            b[i] = a[i] - a[i - 1];
        }

        long p = 0;
        long q = 0;

        for (int i = 2; i <= n; i++) {
            if (b[i] > 0) {
                p += b[i];
            } else {
                q += Math.abs(b[i]);
            }
        }

        System.out.println(Math.max(p, q));
        System.out.println(Math.abs(p - q) + 1);

        scanner.close();
    }
}
