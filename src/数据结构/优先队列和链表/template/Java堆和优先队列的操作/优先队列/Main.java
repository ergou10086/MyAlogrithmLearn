package 数据结构.优先队列和链表.template.Java堆和优先队列的操作.优先队列;

import java.util.*;


class HeapOperations {

    // 添加元素，使用offer方法，add是将指定的元素插入此优先级队列。
    // offer方法：将指定元素插入此优先级队列。如果插入成功返回true，否则返回false。
    // 当队列已满（达到容量限制）时，offer方法不会抛出异常，而是返回false，这与add方法不同，add方法在这种情况下会抛出异常。
    public static void addElements(PriorityQueue<Integer> heap, int... elements) {
        for (int element : elements) {
            heap.offer(element);
        }
    }

    // 删除元素，使用remove方法，移除指定元素的单个实例
    // remove方法：从此优先级队列中移除指定元素的单个实例。如果队列包含指定元素，则将其移除，并返回true；否则返回false。
    public static void removeElement(PriorityQueue<Integer> heap, int element) {
        heap.remove(element);
    }

    // 获取堆顶元素，使用peek方法,获取但不移除此队列的头；如果此队列为空，则返回 null
    // peek方法：获取但不移除此队列的头（即优先级最高的元素）。如果队列为空，则返回null。
    public static Integer getTopElement(PriorityQueue<Integer> heap) {
        return heap.peek();
    }

    // poll方法，检索并删除此队列的头，如果此队列为空，则返回 null 。
    public static int getTopElementPool(PriorityQueue<Integer> heap) {
        return heap.poll();
    }


    // contains方法：如果此集合包含指定的元素，则返回true。
    public static boolean containsElement(PriorityQueue<Integer> heap, int element) {
        return heap.contains(element);
    }


    // 遍历堆，使用迭代器，hasNext判断迭代器指向的容器中是否还有下一个元素
    public static void traverseHeap(PriorityQueue<Integer> heap) {
        Iterator<Integer> iterator = heap.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    // 清空堆
    public static void clearHeap(PriorityQueue<Integer> heap) {
        heap.clear();
    }

    // 获取队列的大小
    // size方法：返回此集合中的元素数量，对于优先级队列来说，就是当前队列中包含的元素个数。
    public static int getHeapSize(PriorityQueue<Integer> heap) {
        return heap.size();
    }

    // 判断队列是否为空
    // isEmpty方法：如果此集合不包含任何元素，则返回true；否则返回false。对于优先级队列，就是判断队列中是否有元素。
    public static boolean isHeapEmpty(PriorityQueue<Integer> heap) {
        return heap.isEmpty();
    }

    // 将一个集合中的所有元素添加到优先级队列中
    // addAll方法：将指定集合中的所有元素添加到此优先级队列中。只要集合中有一个元素添加成功，就返回true；否则返回false。
    // 如果在添加过程中此队列的结构发生了改变（比如容量不够需要扩容等），则可能会抛出异常。
    public static boolean addAllElements(PriorityQueue<Integer> heap, ArrayList<Integer> elementsList) {
        return heap.addAll(elementsList);
    }

    // 移除队列中所有满足特定条件的元素
    // removeIf方法：移除满足给定谓词条件的所有元素。谓词是一个函数式接口，它接受一个参数并返回一个布尔值。
    // 在这里我们可以定义一个条件，比如移除所有小于某个值的元素等。
    public static void removeIfConditionMet(PriorityQueue<Integer> heap, java.util.function.Predicate<Integer> predicate) {
        heap.removeIf(predicate);
    }

    // 将优先级队列转换为数组
    // toArray方法：返回一个包含此队列中所有元素的数组。有两种形式的toArray方法，一种是返回Object[]类型的数组，另一种可以指定返回数组的类型。
    public static Object[] convertHeapToArray(PriorityQueue<Integer> heap) {
        return heap.toArray();
    }

    // 查找指定元素在队列中的位置（索引）
    // indexOf方法：返回指定元素在此列表中第一次出现的索引，如果列表不包含该元素，则返回 -1。
    // 不过需要注意的是，PriorityQueue本身并没有直接提供这个方法，我们可以通过将其转换为ArrayList等支持索引查找的集合来实现类似功能。
    public static int findIndexOfElement(PriorityQueue<Integer> heap, int element) {
        ArrayList<Integer> tempList = new ArrayList<>(heap);
        return tempList.indexOf(element);
    }
}


public class Main {
    public static void main(String[] args) {
        // 创建一个空的最小堆，java默认的是小根堆，默认容量为11
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 创建大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 创建一个带有初始元素的最小堆
        PriorityQueue<Integer> initialHeap = new PriorityQueue<>(Arrays.asList(5, 3, 8, 1, 2));

        // 打印初始堆
        System.out.println("Initial heap: " + initialHeap);

        // 添加元素
        HeapOperations.addElements(minHeap, 4, 6, 7);
        System.out.println("After adding elements: " + minHeap);

        // 删除元素
        HeapOperations.removeElement(minHeap, 4);
        System.out.println("After removing element 4: " + minHeap);

        // 获取堆顶元素
        Integer topElement = HeapOperations.getTopElement(minHeap);
        System.out.println("Top element: " + topElement);

        // 遍历堆
        System.out.println("Traversing the heap:");
        HeapOperations.traverseHeap(minHeap);

        // 清空堆
        HeapOperations.clearHeap(minHeap);
        System.out.println("After clearing the heap: " + minHeap);

        // 获取队列大小示例
        System.out.println("Size of minHeap: " + HeapOperations.getHeapSize(minHeap));

        // 判断队列是否为空示例
        System.out.println("Is minHeap empty? " + HeapOperations.isHeapEmpty(minHeap));

        // 添加集合元素示例
        ArrayList<Integer> elementsToAdd = new ArrayList<>(Arrays.asList(10, 11, 12));
        boolean addAllResult = HeapOperations.addAllElements(minHeap, elementsToAdd);
        System.out.println("Add all elements result: " + addAllResult);
        System.out.println("After adding all elements: " + minHeap);

        // 移除满足条件的元素示例
        HeapOperations.removeIfConditionMet(minHeap, (Integer element) -> element < 10);
        System.out.println("After removing elements less than 10: " + minHeap);

        // 将队列转换为数组示例
        Object[] heapArray = HeapOperations.convertHeapToArray(minHeap);
        System.out.println("Heap as an array: " + Arrays.toString(heapArray));

        // 查找元素索引示例
        int index = HeapOperations.findIndexOfElement(minHeap, 11);
        System.out.println("Index of element 11: " + index);
    }
}