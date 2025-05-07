package 数据结构.Java容器.Set.使用;

import java.util.*;
import java.util.stream.Collectors;

public class TreeSet_Use {
    public static void main(String[] args) {
        new TreeSet_Example().demoTreeSet();
    }
}


class TreeSet_Example {
    // TreeSet 是一种特殊类型的集合，它通过红黑树（Red-Black Tree）数据结构实现了有序的、唯一元素存储
    // TreeSet 中的元素按照自然排序（元素的自然顺序）或者指定的排序方式（通过比较器）排列,遍历 TreeSet 得到的元素是按照一定的顺序排列的
    // 与 HashSet 一样，TreeSet 也保证元素的唯一性，不允许重复元素

    public static void demoTreeSet() {
        // 1. 创建TreeSet实例，可存储任意类型的对象，这里以存储Integer类型为例，使用默认的自然排序（Integer本身实现了Comparable接口）
        TreeSet<Integer> treeSet = new TreeSet<>();

        // 2. 添加元素
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        treeSet.add(1);

        // 3. 获取TreeSet的大小（元素个数）
        int size = treeSet.size();
        System.out.println("TreeSet当前大小: " + size);

        // 4. 判断TreeSet是否为空
        boolean isEmpty = treeSet.isEmpty();
        System.out.println("TreeSet是否为空: " + isEmpty);

        // 5. 检查TreeSet是否包含某个元素
        boolean containsElement = treeSet.contains(3);
        System.out.println("是否包含元素 3: " + containsElement);

        // 6. 移除指定元素
        boolean removed = treeSet.remove(7);
        System.out.println("移除元素 7 是否成功: " + removed);

        // 7. 演示addAll方法，将另一个Collection中的所有元素添加到当前TreeSet（会自动去重并按照顺序排列）
        List<Integer> list = Arrays.asList(6, 4, 8, 3);
        treeSet.addAll(list);
        System.out.println("使用addAll方法后TreeSet内容:");
        for (Integer element : treeSet) {
            System.out.println(element);
        }

        // 8. 演示removeAll方法，移除当前TreeSet中与指定Collection中相同的元素
        Set<Integer> removeSet = new HashSet<>(Arrays.asList(3, 6));
        treeSet.removeAll(removeSet);
        System.out.println("使用removeAll方法后TreeSet内容:");
        for (Integer element : treeSet) {
            System.out.println(element);
        }

        // 9. 演示retainAll方法，仅保留当前TreeSet中与指定Collection中相同的元素
        Set<Integer> retainSet = new HashSet<>(Arrays.asList(4, 5));
        treeSet.retainAll(retainSet);
        System.out.println("使用retainAll方法后TreeSet内容:");
        for (Integer element : treeSet) {
            System.out.println(element);
        }

        // 10. 演示clear方法，清空TreeSet
        treeSet.clear();
        System.out.println("TreeSet清空后是否为空: " + treeSet.isEmpty());

        // 11. 演示使用迭代器遍历TreeSet
        treeSet.add(2);
        treeSet.add(4);
        Iterator<Integer> iterator = treeSet.iterator();
        System.out.println("使用迭代器遍历TreeSet:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 12. 演示使用增强for循环遍历TreeSet
        System.out.println("使用增强for循环遍历TreeSet:");
        for (Integer element : treeSet) {
            System.out.println(element);
        }

        // 13. 演示将TreeSet转换为数组（返回Object[]类型的数组）
        treeSet.add(6);
        treeSet.add(8);
        Object[] array = treeSet.toArray();
        System.out.println("TreeSet转换为数组后的内容:");
        System.out.println(Arrays.toString(array));

        // 14. 演示将TreeSet转换为指定类型的数组（需要传入对应类型的空数组作为参数）
        Integer[] integerArray = treeSet.toArray(new Integer[0]);
        System.out.println("TreeSet转换为指定类型数组后的内容:");
        System.out.println(Arrays.toString(integerArray));

        // 15. 演示TreeSet的克隆（浅克隆，克隆后的对象和原对象共享元素对象的引用）
        TreeSet<Integer> clonedTreeSet = (TreeSet<Integer>) treeSet.clone();
        System.out.println("克隆后的TreeSet内容:");
        for (Integer element : clonedTreeSet) {
            System.out.println(element);
        }

        // 16. 演示containsAll方法，检查当前TreeSet是否包含指定Collection中的所有元素
        Set<Integer> checkSet = new HashSet<>(Arrays.asList(2, 4));
        boolean containsAll = treeSet.containsAll(checkSet);
        System.out.println("是否包含指定集合中的所有元素: " + containsAll);

        // 17. 演示使用lambda表达式结合forEach方法遍历TreeSet（Java 8及以后）
        treeSet.forEach(System.out::println);

        // 18. 演示使用stream API进行操作，比如过滤元素
        treeSet.add(10);
        treeSet.add(12);
        List<Integer> filteredList = treeSet.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println("过滤后的元素列表（偶数元素）:");
        for (Integer element : filteredList) {
            System.out.println(element);
        }

        // 19. 演示使用stream API进行元素去重（虽然TreeSet本身就去重，这里展示一种通用方式）
        List<Integer> duplicateList = Arrays.asList(1, 2, 1, 3, 2);
        TreeSet<Integer> uniqueSet = duplicateList.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("去重后的TreeSet内容:");
        for (Integer element : uniqueSet) {
            System.out.println(element);
        }

        // 20. 演示使用stream API计算TreeSet中元素的数量（其实直接用size方法更简单，但展示stream的用法）
        long count = treeSet.stream().count();
        System.out.println("TreeSet中元素的数量（通过stream API计算）: " + count);

        // 21. 演示使用stream API查找TreeSet中的最大值（基于元素的排序）
        Optional<Integer> maxElement = treeSet.stream().max(Integer::compareTo);
        maxElement.ifPresent(System.out::println);

        // 22. 演示使用stream API查找TreeSet中的最小值（基于元素的排序）
        Optional<Integer> minElement = treeSet.stream().min(Integer::compareTo);
        minElement.ifPresent(System.out::println);

        // 23. 演示TreeSet实现SortedSet接口的方法 - first()获取第一个元素
        TreeSet<Integer> sortedTreeSetForSortedSet = new TreeSet<>(Arrays.asList(15, 10, 20));
        Integer firstElement = sortedTreeSetForSortedSet.first();
        System.out.println("TreeSet作为SortedSet，第一个元素是: " + firstElement);

        // 24. 演示TreeSet实现SortedSet接口的方法 - last()获取最后一个元素
        Integer lastElement = sortedTreeSetForSortedSet.last();
        System.out.println("TreeSet作为SortedSet，最后一个元素是: " + lastElement);

        // 25. 演示TreeSet实现SortedSet接口的方法 - headSet()获取子集（小于指定元素的子集，左闭右开区间）
        SortedSet<Integer> headSet = sortedTreeSetForSortedSet.headSet(15);
        System.out.println("TreeSet作为SortedSet，headSet（小于 15）的内容:");
        for (Integer element : headSet) {
            System.out.println(element);
        }

        // 26. 演示TreeSet实现SortedSet接口的方法 - tailSet()获取子集（大于等于指定元素的子集，左闭右开区间）
        SortedSet<Integer> tailSet = sortedTreeSetForSortedSet.tailSet(15);
        System.out.println("TreeSet作为SortedSet，tailSet（大于等于 15）的内容:");
        for (Integer element : tailSet) {
            System.out.println(element);
        }

        // 27. 演示TreeSet实现SortedSet接口的方法 - subSet()获取子集（指定范围的子集，左闭右开区间）
        SortedSet<Integer> subSet = sortedTreeSetForSortedSet.subSet(10, 20);
        System.out.println("TreeSet作为SortedSet，subSet（10到 20）的内容:");
        for (Integer element : subSet) {
            System.out.println(element);
        }

        // 28. 演示TreeSet实现NavigableSet接口的方法 - lower()获取小于指定元素的最大元素
        TreeSet<Integer> navigableTreeSet = new TreeSet<>(Arrays.asList(12, 14, 16));
        Integer lowerElement = navigableTreeSet.lower(16);
        System.out.println("TreeSet作为NavigableSet，lower（小于 16）的元素是: " + lowerElement);

        // 29. 演示TreeSet实现NavigableSet接口的方法 - floor()获取小于等于指定元素的最大元素
        Integer floorElement = navigableTreeSet.floor(16);
        System.out.println("TreeSet作为NavigableSet，floor（小于等于 16）的元素是: " + floorElement);

        // 30. 演示TreeSet实现NavigableSet接口的方法 - higher()获取大于指定元素的最小元素
        Integer higherElement = navigableTreeSet.higher(16);
        System.out.println("TreeSet作为NavigableSet，higher（大于 16）的元素是: " + higherElement);

        // 31. 演示TreeSet实现NavigableSet接口的方法 - ceiling()获取大于等于指定元素的最小元素
        Integer ceilingElement = navigableTreeSet.ceiling(16);
        System.out.println("TreeSet作为NavigableSet，ceiling（大于等于 16）的元素是: " + ceilingElement);

        // 32. 演示TreeSet实现NavigableSet接口的方法 - pollFirst()获取并移除第一个元素
        Integer pollFirstElement = navigableTreeSet.pollFirst();
        System.out.println("TreeSet作为NavigableSet，pollFirst（获取并移除第一个元素）的元素是: " + pollFirstElement);

        // 33. 演示TreeSet实现NavigableSet接口的方法 - pollLast()获取并移除最后一个元素
        Integer pollLastElement = navigableTreeSet.pollLast();
        System.out.println("TreeSet作为NavigableSet，pollLast（获取并移除最后一个元素）的元素是: " + pollLastElement);

        // 34. 演示TreeSet实现NavigableSet接口的方法 - descendingSet()获取逆序视图
        NavigableSet<Integer> descendingSet = navigableTreeSet.descendingSet();
        System.out.println("TreeSet作为NavigableSet，descendingSet（逆序视图）的内容:");
        for (Integer element : descendingSet) {
            System.out.println(element);
        }

        // 35. 演示TreeSet实现NavigableSet接口的方法 - descendingIterator()获取逆序迭代器
        Iterator<Integer> descendingIterator = navigableTreeSet.descendingIterator();
        System.out.println("TreeSet作为NavigableSet，使用descendingIterator（逆序迭代器）遍历:");
        while (descendingIterator.hasNext()) {
            System.out.println(descendingIterator.next());
        }

        // 36. 演示TreeSet实现NavigableSet接口的方法 - subSet()的另一种重载形式（指定范围，可包含边界，通过布尔参数控制）
        NavigableSet<Integer> inclusiveSubSet = navigableTreeSet.subSet(12, true, 16, true);
        System.out.println("TreeSet作为NavigableSet，inclusiveSubSet（12到 16，包含边界）的内容:");
        for (Integer element : inclusiveSubSet) {
            System.out.println(element);
        }

        // 37. 演示TreeSet实现NavigableSet接口的方法 - headSet()的另一种重载形式（指定范围，可包含边界，通过布尔参数控制）
        NavigableSet<Integer> inclusiveHeadSet = navigableTreeSet.headSet(16, true);
        System.out.println("TreeSet作为NavigableSet，inclusiveHeadSet（到 16，包含边界）的内容:");
        for (Integer element : inclusiveHeadSet) {
            System.out.println(element);
        }

        // 38. 演示TreeSet实现NavigableSet接口的方法 - tailSet()的另一种重载形式（指定范围，可包含边界，通过布尔参数控制）
        NavigableSet<Integer> inclusiveTailSet = navigableTreeSet.tailSet(12, true);
        System.out.println("TreeSet作为NavigableSet，inclusiveTailSet（从 12开始，包含边界）的内容:");
        for (Integer element : inclusiveTailSet) {
            System.out.println(element);
        }

    }
}