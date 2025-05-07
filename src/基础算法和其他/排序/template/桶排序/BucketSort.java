package 基础算法和其他.排序.template.桶排序;

import java.util.*;

public class BucketSort {
    // 桶排序的思想就是把待排序的数尽量均匀地放到各个桶中，再对各个桶进行局部的排序，最后再按序将各个桶中的数输出

    // 桶的核心函数
    public static void bucketSort(int[] array,int n ,int bucketCount) {
        // 找到最大值和最小值
        int maxValue = Arrays.stream(array).max().getAsInt();
        int minValue = Arrays.stream(array).min().getAsInt();

        // 确定每个桶的大小,要分几个桶，除以元素范围，确定的是每个桶的大小
        int bucketSize = (maxValue - minValue + 1) / bucketCount;

        // 创建桶
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i <= bucketCount; i++) buckets.add(new ArrayList<>());

        // 将元素放入对应桶内
        for(int i = 0; i < n; i++){
            // 计算桶的索引
            int index = (array[i] - minValue) / bucketSize;
            if (index >= bucketCount) {
                index = bucketCount - 1; // 确保不越界
            }
            buckets.get(index).add(array[i]);
        }

        // 对每个桶进行排序
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 合并桶中的元素
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int num : bucket) {
                array[index++] = num;
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 输入数组的长度
        int[] array = new int[n];

        for (int i = 0; i < n; i++) array[i] = scanner.nextInt();

        bucketSort(array,n ,2);

        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        scanner.close();
    }
}
