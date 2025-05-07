package 基础算法和其他.排序.template.堆排序;

import java.util.PriorityQueue;
import java.util.Scanner;

public class HeapSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            minheap.add(scanner.nextInt());
        }

        for (int i = 0; i < n; i++) {
            System.out.print(minheap.poll() + " "); // 弹出堆顶元素并打印
        }

        scanner.close();
    }
}
