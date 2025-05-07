package 数据结构.Java容器.List;

import 数据结构.Java容器.List.源码.ArrayList_source;

import java.util.*;

public class Main {
    // Collection接口下的List接口主要实现如下三个类，可以存储重复元素
    public static void main(String[] args) {
        // 演示ArrayList的使用
        Solutions_ArrayList.demoArrayList();

        // 演示LinkedList的使用
        Solutions_LinkedList.demoLinkedList();

        // 演示Vector的使用
        Solutions_Vector.demoVector();
    }
}

class Solutions_ArrayList {
    // 基于数组实现，内部维护一个动态大小的数组来存储元素
    // 允许对元素进行快速随机访问，但是向List中间插入与移除元素的速度很慢。线程不安全
    // 会自动进行扩容操作,每次原数组1.5倍
    public static void demoArrayList() {
        // 创建ArrayList实例，指定其元素类型为String（可以是任意类型）
        ArrayList_source<String> arrayList = new ArrayList_source<>();

        // 添加元素到ArrayList
        arrayList.add("元素1");
        arrayList.add("元素2");
        arrayList.add("元素3");

        // 获取ArrayList的大小（元素个数）
        int size = arrayList.size();
        System.out.println("ArrayList当前大小: " + size);

        // 访问指定位置的元素（注意索引从0开始）
        String elementAtIndex1 = arrayList.get(1);
        System.out.println("索引为1的元素是: " + elementAtIndex1);

        // 修改指定位置的元素
        arrayList.set(1, "修改后的元素2");
        System.out.println("修改后索引为1的元素是: " + arrayList.get(1));

        // 检查ArrayList是否包含某个元素
        boolean containsElement = arrayList.contains("元素3");
        System.out.println("是否包含元素3: " + containsElement);

        // 移除指定元素（移除首次出现的该元素）注意是Object
        boolean removed = arrayList.remove("元素1");
        System.out.println("移除元素1是否成功: " + removed);

        // 遍历ArrayList的方式一：普通for循环
        System.out.println("使用普通for循环遍历ArrayList:");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }

        // 遍历ArrayList的方式二：增强for循环
        System.out.println("使用增强for循环遍历ArrayList:");
        for (String element : arrayList) {
            System.out.println(element);
        }

        // 遍历ArrayList的方式三：使用迭代器
        System.out.println("使用迭代器遍历ArrayList:");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            // hasNext()方法用于判断迭代器指向的容器中是否还有下一个元素
            // next方法，返回迭代器当前指向的元素，并将迭代器的指针向前移动一位
            System.out.println(iterator.next());
        }

        // toArray用法,toArray() 返回的是 Object[] 数组
        String[] array = arrayList.toArray(new String[0]);
        // 两种用法
        String[] String1 = new String[arrayList.size()];
        arrayList.toArray(new String[0]);

        // 演示addAll方法，将另一个Collection中的所有元素添加到当前ArrayList，求并集
        ArrayList_source<String> anotherList = new ArrayList_source<>();
        anotherList.add("元素4");
        anotherList.add("元素5");
        arrayList.addAll(anotherList);
        System.out.println("使用addAll方法后ArrayList内容:");
        for (String element : arrayList) {
            System.out.println(element);
        }

        // 演示removeAll方法，移除当前ArrayList中与指定Collection中相同的元素，求差集
        ArrayList_source<String> removeList = new ArrayList_source<>();
        removeList.add("元素3");
        removeList.add("元素5");
        arrayList.removeAll(removeList);
        System.out.println("使用removeAll方法后ArrayList内容:");
        for (String element : arrayList) {
            System.out.println(element);
        }

        // 演示retainAll方法，仅保留当前ArrayList中与指定Collection中相同的元素
        ArrayList_source<String> retainList = new ArrayList_source<>();
        retainList.add("元素2");
        retainList.add("元素4");
        arrayList.retainAll(retainList);
        System.out.println("使用retainAll方法后ArrayList内容:");
        for (String element : arrayList) {
            System.out.println(element);
        }

        // 演示indexOf方法，查找指定元素在ArrayList中首次出现的索引，若不存在返回 -1
        int index = arrayList.indexOf("元素2");
        System.out.println("元素2首次出现的索引为: " + index);

        // 演示lastIndexOf方法，查找指定元素在ArrayList中最后一次出现的索引，若不存在返回 -1
        int lastIndex = arrayList.lastIndexOf("元素2");
        System.out.println("元素2最后一次出现的索引为: " + lastIndex);

        // 演示subList方法，获取指定范围的子列表（左闭右开区间）
        List<String> subList = arrayList.subList(0, 1);
        System.out.println("获取的子列表内容:");
        for (String element : subList) {
            System.out.println(element);
        }

        // 演示ListIterator，它可以双向遍历列表，还能在遍历过程中进行修改等操作
        ListIterator<String> listIterator = arrayList.listIterator();
        System.out.println("正向使用ListIterator遍历ArrayList:");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("反向使用ListIterator遍历ArrayList:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // 演示排序，使用Collections.sort方法结合元素自身实现Comparable接口来排序（假设元素类实现了Comparable）
        ArrayList_source<String> sortableList = new ArrayList_source<>(Arrays.asList("ccc", "aaa", "bbb"));
        Collections.sort(sortableList);
        System.out.println("使用Collections.sort结合元素自身Comparable排序后的列表:");
        for (String element : sortableList) {
            System.out.println(element);
        }

        // 演示使用Comparator进行自定义排序，比如按照字符串长度排序
        ArrayList_source<String> customSortList = new ArrayList_source<>(Arrays.asList("ccc", "aaa", "bbb"));
        Collections.sort(customSortList, (o1, o2) -> o1.length() - o2.length());   // 正则表达式
        System.out.println("使用Comparator按照字符串长度排序后的列表:");
        for (String element : customSortList) {
            System.out.println(element);
        }
        // List自带的排序
        customSortList.sort(new Comparator<String>() {    // 重写迭代器
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println("使用List自带排序按照字符串长度排序后的列表:");
        for (String element : customSortList) {
            System.out.println(element);
        }


        // 清空ArrayList
        arrayList.clear();
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



class Solutions_Vector {
    // Vector 可实现自动增长的对象数组,类似动态数组的功能
    // 创建了一个向量类的对象后，可以往其中随意插入不同类的对象，即不需顾及类型也不需预先选定向量的容量，并可以方便地进行查找
    // 对于预先不知或者不愿预先定义数组大小，并且需要频繁地进行查找，插入，删除工作的情况，可以考虑使用向量类。
    public static void demoVector() {
        // 创建Vector实例，指定元素类型为Double（类型可按需指定）
        Vector<Double> vector = new Vector<>();
        // initialcapacity设定向量对象的容量,capacityincrement给定了每次扩充的扩充值
        Vector<Double> vector1 = new Vector<>(15, 15);

        // 添加元素到Vector
        vector.add(1.1);
        vector.add(2.2);
        vector.add(3.3);

        // 将obj插入向量的尾部。obj可以是任何类型的对象
        vector.addElement(4.44);

        // 获取Vector的大小
        int size = vector.size();
        System.out.println("Vector当前大小: " + size);

        // setSize方法可以改变Vector的大小，如果新的大小比原大小小，那么多出的元素会被删除
        vector1.setSize(2);

        // 访问指定位置的元素
        Double elementAtIndex1 = vector.get(1);
        System.out.println("索引为1的元素是: " + elementAtIndex1);

        // 修改指定位置的元素
        vector.set(1, 2.5);
        System.out.println("修改后索引为1的元素是: " + vector.get(1));

        // 检查Vector是否包含某个元素
        boolean containsElement = vector.contains(3.3);
        System.out.println("是否包含元素3.3: " + containsElement);

        // 移除指定元素（首次出现的该元素）
        boolean removed = vector.remove(1.1);
        System.out.println("移除元素1.1是否成功: " + removed);

        // 演示addAll方法，将另一个Collection中的所有元素添加到当前Vector
        Vector<Double> anotherVector = new Vector<>();
        anotherVector.add(4.4);
        anotherVector.add(5.5);
        vector.addAll(anotherVector);
        System.out.println("使用addAll方法后Vector内容:");
        for (Double element : vector) {
            System.out.println(element);
        }

        // 演示removeAll方法，移除当前Vector中与指定Collection中相同的元素
        Vector<Double> removeVector = new Vector<>();
        removeVector.add(2.5);
        removeVector.add(4.4);
        vector.removeAll(removeVector);
        System.out.println("使用removeAll方法后Vector内容:");
        for (Double element : vector) {
            System.out.println(element);
        }

        // 演示retainAll方法，仅保留当前Vector中与指定Collection中相同的元素
        Vector<Double> retainVector = new Vector<>();
        retainVector.add(3.3);
        retainVector.add(5.5);
        vector.retainAll(retainVector);
        System.out.println("使用retainAll方法后Vector内容:");
        for (Double element : vector) {
            System.out.println(element);
        }

        // 演示indexOf方法，查找指定元素在Vector中首次出现的索引，若不存在返回 -1
        int index = vector.indexOf(3.3);
        System.out.println("元素3.3首次出现的索引为: " + index);

        // 演示lastIndexOf方法，查找指定元素在Vector中最后一次出现的索引，若不存在返回 -1
        int lastIndex = vector.lastIndexOf(3.3);
        System.out.println("元素3.3最后一次出现的索引为: " + lastIndex);

        // firstElement方法
        Double firstEle = vector.firstElement();
        System.out.println("Vector的第一个元素是: " + firstEle);

        // lastElement方法
        Double lastEle = vector.lastElement();
        System.out.println("Vector的最后一个元素是: " + lastEle);

        // 演示subList方法，获取指定范围的子列表（左闭右开区间）
        List<Double> subList = vector.subList(0, 1);
        System.out.println("获取的子列表内容:");
        for (Double element : subList) {
            System.out.println(element);
        }

        // 演示ListIterator，它可以双向遍历列表，还能在遍历过程中进行修改等操作
        ListIterator<Double> listIterator = vector.listIterator();
        System.out.println("正向使用ListIterator遍历Vector:");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("反向使用ListIterator遍历Vector:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // 演示排序，使用Collections.sort方法结合元素自身实现Comparable接口来排序（假设元素类实现了Comparable）
        Vector<Double> sortableVector = new Vector<>(Arrays.asList(5.5, 3.3, 4.4));
        Collections.sort(sortableVector);
        System.out.println("使用Collections.sort结合元素自身Comparable排序后的向量:");
        for (Double element : sortableVector) {
            System.out.println(element);
        }

        // 演示使用Comparator进行自定义排序，比如按照元素与5的差值绝对值排序
        Vector<Double> customSortVector = new Vector<>(Arrays.asList(3.3, 7.7, 4.4));
        Collections.sort(customSortVector, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (int) (Math.abs(o1 - 5) - Math.abs(o2 - 5));
            }
        });
        System.out.println("使用Comparator按照自定义规则排序后的向量:");
        for (Double element : customSortVector) {
            System.out.println(element);
        }

        // 演示containsAll方法，检查当前Vector是否包含指定Collection中的所有元素
        Vector<Double> checkVector = new Vector<>(Arrays.asList(3.3, 5.5));
        boolean containsAll = vector.containsAll(checkVector);
        System.out.println("是否包含指定集合中的所有元素: " + containsAll);

        // 演示toArray方法，将Vector转换为数组
        Object[] array = vector.toArray();
        System.out.println("转换后的数组内容: " + Arrays.toString(array));

        // 演示toArray(T[] a)方法，将Vector转换为指定类型的数组
        Double[] doubleArray = vector.toArray(new Double[0]);
        System.out.println("转换后的双精度数组内容: " + Arrays.toString(doubleArray));

        // 演示clone方法，克隆当前Vector（浅克隆）
        Vector<Double> clonedVector = (Vector<Double>) vector.clone();
        System.out.println("克隆后的Vector内容:");
        for (Double element : clonedVector) {
            System.out.println(element);
        }

        // 模拟队列相关操作（虽然Vector本身不是Queue实现，但可以模拟部分行为）
        // 查看队首元素（不删除），类似Queue的peek方法
        Double peekElement = vector.isEmpty()? null : vector.firstElement();
        System.out.println("模拟队列查看队首元素（不删除）: " + peekElement);

        // 取出队首元素（删除），类似Queue的poll方法
        Double pollElement = vector.isEmpty()? null : vector.remove(0);
        System.out.println("模拟队列取出队首元素（删除）: " + pollElement);

        // 向队尾添加元素，类似Queue的offer方法
        vector.add(6.6);
        System.out.println("模拟队列向队尾添加元素后Vector内容:");
        for (Double element : vector) {
            System.out.println(element);
        }

        // 模拟双端队列（Deque）相关操作（部分模拟）
        // 在队首插入元素，类似Deque的addFirst方法
        vector.add(0, 0.1);
        System.out.println("模拟双端队列在队首插入元素后Vector内容:");
        for (Double element : vector) {
            System.out.println(element);
        }

        // 在队尾插入元素，类似Deque的addLast方法（等同于普通的add方法）
        vector.add(vector.size(), 7.7);
        System.out.println("模拟双端队列在队尾插入元素后Vector内容:");
        for (Double element : vector) {
            System.out.println(element);
        }

        // 获取队首元素（不删除），类似Deque的peekFirst方法（等同于上面模拟队列的peek操作）
        Double peekFirstElement = vector.isEmpty()? null : vector.firstElement();
        System.out.println("模拟双端队列查看队首元素（不删除）: " + peekFirstElement);

        // 获取队尾元素（不删除），类似Deque的peekLast方法
        Double peekLastElement = vector.isEmpty()? null : vector.lastElement();
        System.out.println("模拟双端队列查看队尾元素（不删除）: " + peekLastElement);

        // 取出队首元素（删除），类似Deque的pollFirst方法（等同于上面模拟队列的poll操作）
        Double pollFirstElement = vector.isEmpty()? null : vector.remove(0);
        System.out.println("模拟双端队列取出队首元素（删除）: " + pollFirstElement);

        // 取出队尾元素（删除），类似Deque的pollLast方法
        Double pollLastElement = vector.isEmpty()? null : vector.remove(vector.size() - 1);
        System.out.println("模拟双端队列取出队尾元素（删除）: " + pollLastElement);

        // 遍历Vector的方式一：普通for循环
        System.out.println("使用普通for循环遍历Vector:");
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i));
        }

        // 遍历Vector的方式二：增强for循环
        System.out.println("使用增强for循环遍历Vector:");
        for (Double element : vector) {
            System.out.println(element);
        }

        // 遍历Vector的方式三：使用Iterator迭代器
        System.out.println("使用Iterator迭代器遍历Vector:");
        Iterator<Double> iterator = vector.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 使用枚举集遍历
        System.out.println("使用枚举集遍历Vector:");
        Enumeration<Double> elements = vector1.elements();
        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }

        // 清空Vector
        vector.clear();
    }
}