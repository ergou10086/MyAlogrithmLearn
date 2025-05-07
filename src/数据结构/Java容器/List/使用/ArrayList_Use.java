package 数据结构.Java容器.List.使用;

import java.util.*;

public class ArrayList_Use {
    public static void main(String[] args) {
        Solutions_ArrayList.demoArrayList();
    }
}


class Solutions_ArrayList {
    // 基于数组实现，内部维护一个动态大小的数组来存储元素
    // 允许对元素进行快速随机访问，但是向List中间插入与移除元素的速度很慢。线程不安全
    // 会自动进行扩容操作,每次原数组1.5倍
    public static void demoArrayList() {
        // 创建ArrayList实例，指定其元素类型为String（可以是任意类型）
        ArrayList<String> arrayList = new ArrayList<>();

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
        ArrayList<String> anotherList = new ArrayList<>();
        anotherList.add("元素4");
        anotherList.add("元素5");
        arrayList.addAll(anotherList);
        System.out.println("使用addAll方法后ArrayList内容:");
        for (String element : arrayList) {
            System.out.println(element);
        }

        // 演示removeAll方法，移除当前ArrayList中与指定Collection中相同的元素，求差集
        ArrayList<String> removeList = new ArrayList<>();
        removeList.add("元素3");
        removeList.add("元素5");
        arrayList.removeAll(removeList);
        System.out.println("使用removeAll方法后ArrayList内容:");
        for (String element : arrayList) {
            System.out.println(element);
        }

        // 演示retainAll方法，仅保留当前ArrayList中与指定Collection中相同的元素
        ArrayList<String> retainList = new ArrayList<>();
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
        ArrayList<String> sortableList = new ArrayList<>(Arrays.asList("ccc", "aaa", "bbb"));
        Collections.sort(sortableList);
        System.out.println("使用Collections.sort结合元素自身Comparable排序后的列表:");
        for (String element : sortableList) {
            System.out.println(element);
        }

        // 演示使用Comparator进行自定义排序，比如按照字符串长度排序
        ArrayList<String> customSortList = new ArrayList<>(Arrays.asList("ccc", "aaa", "bbb"));
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