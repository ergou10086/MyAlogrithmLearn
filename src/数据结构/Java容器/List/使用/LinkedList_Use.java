package 数据结构.Java容器.List.使用;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedList_Use {
    public static void main(String[] args) {
        new Solutions_LinkedList();
    }
}


class Solutions_LinkedList {
    // LinkedList的底层是 双向链表结构
    // 在任意位置插入和删除元素时效率比较高，时间复杂度为O(1)
    // ArrayList可以支持随机访问，时间复杂度为O(1)，LinkedList比较适合插入和删除比较频繁的情况
    public static void demoLinkedList() {
        // 创建LinkedList实例，指定元素类型为Integer（同样可以是其他类型）
        LinkedList<Integer> linkedList = new LinkedList<>();

        // 使用ArrayList构造LinkedList
        List<String> list2 = new java.util.ArrayList<>();
        list2.add("JavaSE");
        list2.add("JavaWeb");
        list2.add("JavaEE");
        List<String> list3 = new LinkedList<>(list2);

        // 添加元素到LinkedList，添加到末尾
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        // 在指定位置插入元素（例如在索引1处插入新元素）
        linkedList.add(1, 10);

        // 获取LinkedList的大小
        int size = linkedList.size();
        System.out.println("LinkedList当前大小: " + size);

        // 是否空
        boolean isEmpty = list3.isEmpty();
        System.out.println("LinkedList是否为空: " + isEmpty);

        // 判断两个集合元素是否相等
        boolean isEqual = list2.equals(list3);
        System.out.println("两个集合是否相等: " + isEqual);

        // 访问指定位置的元素
        Integer elementAtIndex1 = linkedList.get(1);
        System.out.println("索引为1的元素是: " + elementAtIndex1);

        // 移除指定位置的元素
        Integer removedElement = linkedList.remove(2);
        System.out.println("移除索引为2的元素，该元素是: " + removedElement);

        // 将元素添加到链表头部
        linkedList.addFirst(0);

        // 将元素添加到链表尾部
        linkedList.addLast(4);

        // poll方法,获取首元素并从中删除；
        Integer pollElement = linkedList.poll();
        System.out.println("使用poll取出元素" + pollElement);

        // peek方法,获取首元素但不删除；
        Integer peekElement = linkedList.peek();
        System.out.println("使用peek取出元素" + peekElement);

        // 取出链表头部元素（同时会从链表移除该元素），使用pollFirst方法
        Integer firstElementPoll = linkedList.pollFirst();
        System.out.println("使用pollFirst取出的链表头部元素是: " + firstElementPoll);

        // 取出链表头部元素（不移除该元素），使用peekFirst方法
        Integer firstElementPeek = linkedList.peekFirst();
        System.out.println("使用peekFirst查看的链表头部元素是: " + firstElementPeek);

        // 取出链表尾部元素（同时会从链表移除该元素），使用pollLast方法
        Integer lastElementPoll = linkedList.pollLast();
        System.out.println("使用pollLast取出的链表尾部元素是: " + lastElementPoll);

        // 取出链表尾部元素（不移除该元素），使用peekLast方法
        Integer lastElementPeek = linkedList.peekLast();
        System.out.println("使用peekLast查看的链表尾部元素是: " + lastElementPeek);

        // 向链表头部插入元素（等同于addFirst），使用push方法
        linkedList.push(5);
        System.out.println("使用push方法向链表头部插入元素后，链表头部元素是: " + linkedList.peekFirst());

        // 将元素添加到链表末尾（等同于addLast），使用offer方法
        linkedList.offer(6);
        System.out.println("使用offer方法向链表末尾添加元素后，链表尾部元素是: " + linkedList.peekLast());

        // 演示addAll方法，将另一个Collection中的所有元素添加到当前LinkedList
        ArrayList<Integer> anotherList = new ArrayList<>();
        anotherList.add(7);
        anotherList.add(8);
        linkedList.addAll(anotherList);
        System.out.println("使用addAll方法后LinkedList内容:");
        for (Integer element : linkedList) {
            System.out.println(element);
        }

        // 演示removeAll方法，移除当前LinkedList中与指定Collection中相同的元素
        ArrayList<Integer> removeList = new ArrayList<>();
        removeList.add(2);
        removeList.add(4);
        linkedList.removeAll(removeList);
        System.out.println("使用removeAll方法后LinkedList内容:");
        for (Integer element : linkedList) {
            System.out.println(element);
        }

        // 演示retainAll方法，仅保留当前LinkedList中与指定Collection中相同的元素
        ArrayList<Integer> retainList = new ArrayList<>();
        retainList.add(1);
        retainList.add(3);
        linkedList.retainAll(retainList);
        System.out.println("使用retainAll方法后LinkedList内容:");
        for (Integer element : linkedList) {
            System.out.println(element);
        }

        // 演示indexOf方法，查找指定元素在LinkedList中首次出现的索引，若不存在返回 -1
        int index = linkedList.indexOf(1);
        System.out.println("元素1首次出现的索引为: " + index);

        // 演示lastIndexOf方法，查找指定元素在LinkedList中最后一次出现的索引，若不存在返回 -1
        int lastIndex = linkedList.lastIndexOf(1);
        System.out.println("元素1最后一次出现的索引为: " + lastIndex);

        // 演示subList方法，获取指定范围的子列表（左闭右开区间）
        List<Integer> subList = linkedList.subList(0, 1);
        System.out.println("获取的子列表内容:");
        for (Integer element : subList) {
            System.out.println(element);
        }

        // 演示ListIterator，它可以双向遍历列表，还能在遍历过程中进行修改等操作
        ListIterator<Integer> listIterator = linkedList.listIterator();
        System.out.println("正向使用ListIterator遍历LinkedList:");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("反向使用ListIterator遍历LinkedList:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // 演示排序，使用Collections.sort方法结合元素自身实现Comparable接口来排序（假设元素类实现了Comparable）
        LinkedList<Integer> sortableList = new LinkedList<>(Arrays.asList(5, 3, 4));
        Collections.sort(sortableList);
        System.out.println("使用Collections.sort结合元素自身Comparable排序后的列表:");
        for (Integer element : sortableList) {
            System.out.println(element);
        }

        // 演示使用Comparator进行自定义排序，比如按照元素与5的差值绝对值排序
        LinkedList<Integer> customSortList = new LinkedList<>(Arrays.asList(3, 7, 4));
        Collections.sort(customSortList, (o1, o2) -> Math.abs(o1 - 5) - Math.abs(o2 - 5));
        System.out.println("使用Comparator按照自定义规则排序后的列表:");
        for (Integer element : customSortList) {
            System.out.println(element);
        }

        // 演示contains方法，检查LinkedList是否包含指定元素
        boolean contains = linkedList.contains(1);
        System.out.println("LinkedList是否包含元素1: " + contains);

        // 演示toArray方法，将LinkedList转换为数组
        Object[] array = linkedList.toArray();
        System.out.println("转换后的数组内容: " + Arrays.toString(array));

        // 演示toArray(T[] a)方法，将LinkedList转换为指定类型的数组
        Integer[] intArray = linkedList.toArray(new Integer[0]);
        System.out.println("转换后的整数数组内容: " + Arrays.toString(intArray));

        // 演示clone方法，克隆当前LinkedList（浅克隆）
        LinkedList<Integer> clonedList = (LinkedList<Integer>) linkedList.clone();
        System.out.println("克隆后的LinkedList内容:");
        for (Integer element : clonedList) {
            System.out.println(element);
        }

        // 遍历LinkedList的方式一：普通for循环
        System.out.println("使用普通for循环遍历LinkedList:");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }

        // 遍历LinkedList的方式二：增强for循环
        System.out.println("使用增强for循环遍历LinkedList:");
        for (Integer element : linkedList) {
            System.out.println(element);
        }

        // 遍历LinkedList的方式三：使用Iterator迭代器
        System.out.println("使用Iterator迭代器遍历LinkedList:");
        // 正向迭代遍历
        ListIterator<Integer> stringListIterator = linkedList.listIterator();
        while (stringListIterator.hasNext()) {
            System.out.print(stringListIterator.next() + " ");
        }
        // 反向迭代遍历
        ListIterator<Integer> stringListIterator2 = linkedList.listIterator(linkedList.size());
        while (stringListIterator2.hasPrevious()) {
            System.out.print(stringListIterator2.previous() + " ");
        }

        // 表达式遍历
        System.out.println("使用表达式遍历LinkedList:");
        linkedList.forEach(System.out::println);

        // 清空LinkedList
        linkedList.clear();
    }
}