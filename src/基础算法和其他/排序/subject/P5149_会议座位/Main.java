package 基础算法和其他.排序.subject.P5149_会议座位;

import java.util.*;

// 字符串求逆序对
// 归并排序
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static long dissatisfactionCount = 0;
    static int[] newPositions;   // 原始数组
    static int[] cos_newPositions;  // 归并用的拷贝数组

    public static void mergeSort(int left, int right){
        if(left == right) return;
        int mid = (left + right) >> 1;

        int i = left, j = mid + 1, k = left;

        mergeSort(left, mid);
        mergeSort( mid + 1, right);

        while(i <= mid && j <= right){
            if(newPositions[i] <= newPositions[j]) cos_newPositions[k++] = newPositions[i++];
            else{
                cos_newPositions[k++] = newPositions[j++];
                dissatisfactionCount += mid - i + 1;
            }
        }

        while(i <= mid) cos_newPositions[k++] = newPositions[i++];
        while(j <= right) cos_newPositions[k++] = newPositions[j++];
        for(i = left; i <= right; i++) newPositions[i] = cos_newPositions[i];

    }

    public static void main(String[] args) {
        n = sc.nextInt();
        sc.nextLine();

        newPositions = new int[n + 1];
        cos_newPositions = new int[n + 1];

        HashMap<String, Integer> teacher = new HashMap<>();

        String[] original = sc.nextLine().split(" ");
        String[] shuffled = sc.nextLine().split(" ");

        // hashmap离散化
        for (int i = 0; i < n; i++) {
            teacher.put(original[i], i);
        }

        // 将打乱后的座位表转换为原始索引的数组
        for (int i = 1; i <= n; i++) {
            newPositions[i] = teacher.get(shuffled[i - 1]);
        }

        mergeSort(1, n);

        System.out.println(dissatisfactionCount);
        sc.close();
    }
}
