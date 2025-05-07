package 数据结构.Java容器.Set.使用;

import 数据结构.Java容器.Map.使用.LinkedHashMap_Use;

import java.util.*;
import java.util.stream.Collectors;

public class LinkedHashSet_Use {
    public static void main(String[] args) {
        new LinkedHashSet_Use1().demoLinkedHashSet();
    }
}

class LinkedHashSet_Use1 {
    // LinkedHashSet是有序的
    // LinkedHashSet在迭代访问Set中的全部元素时，性能比HashSet好，但是插入时性能稍微逊色于HashSet。
    // LinkedHashSet的实现是继承自HashSet，底层使用的是HashMap，只是多了一个双向链表来记录插入顺序。

    public static void demoLinkedHashSet() {
        // 1. 创建LinkedHashSet实例，可存储任意类型的对象，这里以存储String类型为例
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        // 2. 添加元素
        linkedHashSet.add("apple");
        linkedHashSet.add("banana");
        linkedHashSet.add("cherry");

        // 3. 获取LinkedHashSet的大小（元素个数）
        int size = linkedHashSet.size();
        System.out.println("LinkedHashSet当前大小: " + size);

        // 4. 判断LinkedHashSet是否为空
        boolean isEmpty = linkedHashSet.isEmpty();
        System.out.println("LinkedHashSet是否为空: " + isEmpty);

        // 5. 检查LinkedHashSet是否包含某个元素
        boolean containsElement = linkedHashSet.contains("banana");
        System.out.println("是否包含元素 'banana': " + containsElement);

        // 6. 移除指定元素
        boolean removed = linkedHashSet.remove("cherry");
        System.out.println("移除元素 'cherry' 是否成功: " + removed);

        // 7. 演示addAll方法，将另一个Collection中的所有元素添加到当前LinkedHashSet（会自动去重）
        List<String> list = Arrays.asList("date", "elder", "apple");
        linkedHashSet.addAll(list);
        System.out.println("使用addAll方法后LinkedHashSet内容:");
        for (String element : linkedHashSet) {
            System.out.println(element);
        }

        // 8. 演示removeAll方法，移除当前LinkedHashSet中与指定Collection中相同的元素
        Set<String> removeSet = new HashSet<>(Arrays.asList("apple", "date"));
        linkedHashSet.removeAll(removeSet);
        System.out.println("使用removeAll方法后LinkedHashSet内容:");
        for (String element : linkedHashSet) {
            System.out.println(element);
        }

        // 9. 演示retainAll方法，仅保留当前LinkedHashSet中与指定Collection中相同的元素
        Set<String> retainSet = new HashSet<>(Arrays.asList("banana", "elder"));
        linkedHashSet.retainAll(retainSet);
        System.out.println("使用retainAll方法后LinkedHashSet内容:");
        for (String element : linkedHashSet) {
            System.out.println(element);
        }

        // 10. 演示clear方法，清空LinkedHashSet
        linkedHashSet.clear();
        System.out.println("LinkedHashSet清空后是否为空: " + linkedHashSet.isEmpty());

        // 11. 演示使用迭代器遍历LinkedHashSet
        linkedHashSet.add("grape");
        linkedHashSet.add("honeydew");
        Iterator<String> iterator = linkedHashSet.iterator();
        System.out.println("使用迭代器遍历LinkedHashSet:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 12. 演示使用增强for循环遍历LinkedHashSet
        System.out.println("使用增强for循环遍历LinkedHashSet:");
        for (String element : linkedHashSet) {
            System.out.println(element);
        }

        // 13. 演示将LinkedHashSet转换为数组（返回Object[]类型的数组）
        linkedHashSet.add("ice");
        linkedHashSet.add("jackfruit");
        Object[] array = linkedHashSet.toArray();
        System.out.println("LinkedHashSet转换为数组后的内容:");
        System.out.println(Arrays.toString(array));

        // 14. 演示将LinkedHashSet转换为指定类型的数组（需要传入对应类型的空数组作为参数）
        String[] stringArray = linkedHashSet.toArray(new String[0]);
        System.out.println("LinkedHashSet转换为指定类型数组后的内容:");
        System.out.println(Arrays.toString(stringArray));

        // 15. 演示LinkedHashSet的克隆（浅克隆，克隆后的对象和原对象共享元素对象的引用）
        LinkedHashSet<String> clonedLinkedHashSet = (LinkedHashSet<String>) linkedHashSet.clone();
        System.out.println("克隆后的LinkedHashSet内容:");
        for (String element : clonedLinkedHashSet) {
            System.out.println(element);
        }

        // 16. 演示containsAll方法，检查当前LinkedHashSet是否包含指定Collection中的所有元素
        Set<String> checkSet = new HashSet<>(Arrays.asList("ice", "jackfruit"));
        boolean containsAll = linkedHashSet.containsAll(checkSet);
        System.out.println("是否包含指定集合中的所有元素: " + containsAll);

        // 17. 演示使用lambda表达式结合forEach方法遍历LinkedHashSet（Java 8及以后）
        linkedHashSet.forEach(System.out::println);

        // 18. 演示使用stream API进行操作，比如过滤元素
        linkedHashSet.add("kiwi");
        linkedHashSet.add("lemon");
        List<String> filteredList = linkedHashSet.stream().filter(s -> s.length() > 4).collect(Collectors.toList());
        System.out.println("过滤后的元素列表（长度大于4的元素）:");
        for (String element : filteredList) {
            System.out.println(element);
        }

        // 19. 演示使用stream API进行元素去重（虽然LinkedHashSet本身就去重，这里展示一种通用方式）
        List<String> duplicateList = Arrays.asList("apple", "banana", "apple", "cherry");
        LinkedHashSet<String> uniqueSet = duplicateList.stream().collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("去重后的LinkedHashSet内容:");
        for (String element : uniqueSet) {
            System.out.println(element);
        }

        // 20. 演示使用stream API计算LinkedHashSet中元素的数量（其实直接用size方法更简单，但展示stream的用法）
        long count = linkedHashSet.stream().count();
        System.out.println("LinkedHashSet中元素的数量（通过stream API计算）: " + count);

        // 21. 演示使用stream API查找LinkedHashSet中的最大值（假设元素实现了Comparable接口，这里以字符串自然顺序为例）
        Optional<String> maxElement = linkedHashSet.stream().max(String::compareTo);
        maxElement.ifPresent(System.out::println);

        // 22. 演示使用stream API查找LinkedHashSet中的最小值（假设元素实现了Comparable接口，这里以字符串自然顺序为例）
        Optional<String> minElement = linkedHashSet.stream().min(String::compareTo);
        minElement.ifPresent(System.out::println);

        // 23. 演示将LinkedHashSet转换为普通HashSet（去除顺序特性）
        HashSet<String> hashSet = new HashSet<>(linkedHashSet);
        System.out.println("转换为HashSet后的内容（无序）:");
        for (String element : hashSet) {
            System.out.println(element);
        }

        // 24. 演示LinkedHashSet实现SortedSet接口的方法 - first()获取第一个元素
        LinkedHashSet<String> sortedLinkedHashSetForSortedSet = new LinkedHashSet<>(Arrays.asList("mango", "orange", "watermelon"));
        TreeSet<String> treeSetForSortedSet = new TreeSet<>(sortedLinkedHashSetForSortedSet);
        String firstElement = treeSetForSortedSet.first();
        System.out.println("LinkedHashSet作为SortedSet（通过转换为TreeSet），第一个元素是: " + firstElement);

        // 25. 演示LinkedHashSet实现SortedSet接口的方法 - last()获取最后一个元素
        String lastElement = treeSetForSortedSet.last();
        System.out.println("LinkedHashSet作为SortedSet（通过转换为TreeSet），最后一个元素是: " + lastElement);

        // 26. 演示LinkedHashSet实现SortedSet接口的方法 - headSet()获取子集（小于指定元素的子集，左闭右开区间）
        SortedSet<String> headSet = treeSetForSortedSet.headSet("orange");
        System.out.println("LinkedHashSet作为SortedSet（通过转换为TreeSet），headSet（小于 'orange'）的内容:");
        for (String element : headSet) {
            System.out.println(element);
        }

        // 27. 演示LinkedHashSet实现SortedSet接口的方法 - tailSet()获取子集（大于等于指定元素的子集，左闭右开区间）
        SortedSet<String> tailSet = treeSetForSortedSet.tailSet("orange");
        System.out.println("LinkedHashSet作为SortedSet（通过转换为TreeSet），tailSet（大于等于 'orange'）的内容:");
        for (String element : tailSet) {
            System.out.println(element);
        }

        // 28. 演示LinkedHashSet实现SortedSet接口的方法 - subSet()获取子集（指定范围的子集，左闭右开区间）
        SortedSet<String> subSet = treeSetForSortedSet.subSet("mango", "watermelon");
        System.out.println("LinkedHashSet作为SortedSet（通过转换为TreeSet），subSet（'mango'到 'watermelon'）的内容:");
        for (String element : subSet) {
            System.out.println(element);
        }

        // 29. 演示LinkedHashSet实现NavigableSet接口的方法 - lower()获取小于指定元素的最大元素
        LinkedHashSet<String> linkedHashSetForNavigableSet = new LinkedHashSet<>(Arrays.asList("pear", "peach", "plum"));
        NavigableSet<String> navigableSet = new TreeSet<>(linkedHashSetForNavigableSet);
        String lowerElement = navigableSet.lower("peach");
        System.out.println("LinkedHashSet作为NavigableSet（通过转换为TreeSet），lower（小于 'peach'）的元素是: " + lowerElement);

        // 30. 演示LinkedHashSet实现NavigableSet接口的方法 - floor()获取小于等于指定元素的最大元素
        String floorElement = navigableSet.floor("peach");
        System.out.println("LinkedHashSet作为NavigableSet，floor（小于等于 'peach'）的元素是: " + floorElement);

        // 31. 演示LinkedHashSet实现NavigableSet接口的方法 - higher()获取大于指定元素的最小元素
        String higherElement = navigableSet.higher("peach");
        System.out.println("LinkedHashSet作为NavigableSet，higher（大于 'peach'）的元素是: " + higherElement);

        // 32. 演示LinkedHashSet实现NavigableSet接口的方法 - ceiling()获取大于等于指定元素的最小元素
        String ceilingElement = navigableSet.ceiling("peach");
        System.out.println("LinkedHashSet作为NavigableSet，ceiling（大于等于 'peach'）的元素是: " + ceilingElement);

        // 33. 演示LinkedHashSet实现NavigableSet接口的方法 - pollFirst()获取并移除第一个元素
        String pollFirstElement = navigableSet.pollFirst();
        System.out.println("LinkedHashSet作为NavigableSet，pollFirst（获取并移除第一个元素）的元素是: " + pollFirstElement);

        // 34. 演示LinkedHashSet实现NavigableSet接口的方法 - pollLast()获取并移除最后一个元素
        String pollLastElement = navigableSet.pollLast();
        System.out.println("LinkedHashSet作为NavigableSet，pollLast（获取并移除最后一个元素）的元素是: " + pollLastElement);

        // 35. 演示LinkedHashSet实现NavigableSet接口的方法 - descendingSet()获取逆序视图
        NavigableSet<String> descendingSet = navigableSet.descendingSet();
        System.out.println("LinkedHashSet作为NavigableSet，descendingSet（逆序视图）的内容:");
        for (String element : descendingSet) {
            System.out.println(element);
        }

        // 36. 演示LinkedHashSet实现NavigableSet接口的方法 - descendingIterator()获取逆序迭代器
        Iterator<String> descendingIterator = navigableSet.descendingIterator();
        System.out.println("LinkedHashSet作为NavigableSet，使用descendingIterator（逆序迭代器）遍历:");
        while (descendingIterator.hasNext()) {
            System.out.println(descendingIterator.next());
        }

        // 37. 演示LinkedHashSet实现NavigableSet接口的方法 - subSet()的另一种重载形式（指定范围，可包含边界，通过布尔参数控制）
        NavigableSet<String> inclusiveSubSet = navigableSet.subSet("pear", true, "plum", true);
        System.out.println("LinkedHashSet作为NavigableSet，inclusiveSubSet（'pear'到 'plum'，包含边界）的内容:");
        for (String element : inclusiveSubSet) {
            System.out.println(element);
        }

        // 38. 演示LinkedHashSet实现NavigableSet接口的方法 - headSet()的另一种重载形式（指定范围，可包含边界，通过布尔参数控制）
        NavigableSet<String> inclusiveHeadSet = navigableSet.headSet("plum", true);
        System.out.println("LinkedHashSet作为NavigableSet，inclusiveHeadSet（到 'plum'，包含边界）的内容:");
        for (String element : inclusiveHeadSet) {
            System.out.println(element);
        }

        // 39. 演示LinkedHashSet实现NavigableSet接口的方法 - tailSet()的另一种重载形式（指定范围，可包含边界，通过布尔参数控制）
        NavigableSet<String> inclusiveTailSet = navigableSet.tailSet("pear", true);
        System.out.println("LinkedHashSet作为NavigableSet，inclusiveTailSet（从 'pear'开始，包含边界）的内容:");
        for (String element : inclusiveTailSet) {
            System.out.println(element);
        }
    }
}