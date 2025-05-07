package 数据结构.Java容器.List.使用;

import java.util.*;

public class Vector_Use {
    public static void main(String[] args) {
        new Solutions_Vector();
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