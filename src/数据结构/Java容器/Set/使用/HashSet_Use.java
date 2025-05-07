package 数据结构.Java容器.Set.使用;

import java.util.*;
import java.util.stream.Collectors;

public class HashSet_Use {
    public static void main(String[] args) {
        // 演示HashSet的各种方法使用
        HashSet_Example.demoHashSet();
    }
}

class HashSet_Example {
    // HashSet类按照哈希算法来存取集合中的对象，存取速度比较快
    // HashSet类不保证集合的迭代顺序，并允许使用null元素，但是最多只能有一个
    // 也就是说，Set中是不能出现重复数据的

    public static void demoHashSet() {
        // 创建HashSet实例，可存储任意类型的对象，这里以存储String类型为例
        HashSet<String> hashSet = new HashSet<>();

        // 1. 添加元素
        hashSet.add("apple");
        hashSet.add("banana");
        hashSet.add("cherry");

        // 2. 获取HashSet的大小（元素个数）
        int size = hashSet.size();
        System.out.println("HashSet当前大小: " + size);

        // 3. 判断HashSet是否为空
        boolean isEmpty = hashSet.isEmpty();
        System.out.println("HashSet是否为空: " + isEmpty);

        // 4. 检查HashSet是否包含某个元素
        boolean containsElement = hashSet.contains("banana");
        System.out.println("是否包含元素 'banana': " + containsElement);

        // 5. 移除指定元素
        boolean removed = hashSet.remove("cherry");
        System.out.println("移除元素 'cherry' 是否成功: " + removed);

        // 6. 演示addAll方法，将另一个Collection中的所有元素添加到当前HashSet（会自动去重）
        List<String> list = Arrays.asList("date", "elder", "apple");
        hashSet.addAll(list);
        System.out.println("使用addAll方法后HashSet内容:");
        for (String element : hashSet) {
            System.out.println(element);
        }

        // 7. 演示removeAll方法，移除当前HashSet中与指定Collection中相同的元素
        Set<String> removeSet = new HashSet<>(Arrays.asList("apple", "date"));
        hashSet.removeAll(removeSet);
        System.out.println("使用removeAll方法后HashSet内容:");
        for (String element : hashSet) {
            System.out.println(element);
        }

        // 8. 演示retainAll方法，仅保留当前HashSet中与指定Collection中相同的元素
        Set<String> retainSet = new HashSet<>(Arrays.asList("banana", "elder"));
        hashSet.retainAll(retainSet);
        System.out.println("使用retainAll方法后HashSet内容:");
        for (String element : hashSet) {
            System.out.println(element);
        }

        // 9. 演示clear方法，清空HashSet
        hashSet.clear();
        System.out.println("HashSet清空后是否为空: " + hashSet.isEmpty());

        // 10. 演示使用迭代器遍历HashSet
        hashSet.add("grape");
        hashSet.add("honeydew");
        Iterator<String> iterator = hashSet.iterator();
        System.out.println("使用迭代器遍历HashSet:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 11. 演示使用增强for循环遍历HashSet
        System.out.println("使用增强for循环遍历HashSet:");
        for (String element : hashSet) {
            System.out.println(element);
        }

        // 12. 演示将HashSet转换为数组（返回Object[]类型的数组）
        hashSet.add("ice");
        hashSet.add("jackfruit");
        Object[] array = hashSet.toArray();
        System.out.println("HashSet转换为数组后的内容:");
        System.out.println(Arrays.toString(array));

        // 13. 演示将HashSet转换为指定类型的数组（需要传入对应类型的空数组作为参数）
        String[] stringArray = hashSet.toArray(new String[0]);
        System.out.println("HashSet转换为指定类型数组后的内容:");
        System.out.println(Arrays.toString(stringArray));

        // 14. 演示HashSet的克隆（浅克隆，克隆后的对象和原对象共享元素对象的引用）
        HashSet<String> clonedHashSet = (HashSet<String>) hashSet.clone();
        System.out.println("克隆后的HashSet内容:");
        for (String element : clonedHashSet) {
            System.out.println(element);
        }

        // 15. 演示containsAll方法，检查当前HashSet是否包含指定Collection中的所有元素
        Set<String> checkSet = new HashSet<>(Arrays.asList("ice", "jackfruit"));
        boolean containsAll = hashSet.containsAll(checkSet);
        System.out.println("是否包含指定集合中的所有元素: " + containsAll);

        // 16. 演示使用lambda表达式结合forEach方法遍历HashSet（Java 8及以后）
        hashSet.forEach(System.out::println);

        // 17. 演示使用stream API进行操作，比如过滤元素
        hashSet.add("kiwi");
        hashSet.add("lemon");
        List<String> filteredList = hashSet.stream().filter(s -> s.length() > 4).collect(Collectors.toList());
        System.out.println("过滤后的元素列表（长度大于4的元素）:");
        for (String element : filteredList) {
            System.out.println(element);
        }

        // 18. 演示使用stream API进行元素去重（在有重复元素的情况下重新构建一个HashSet来达到去重目的）
        List<String> duplicateList = Arrays.asList("apple", "banana", "apple", "cherry");
        HashSet<String> uniqueSet = duplicateList.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println("去重后的HashSet内容:");
        for (String element : uniqueSet) {
            System.out.println(element);
        }

        // 19. 演示使用stream API计算HashSet中元素的数量（其实直接用size方法更简单，但展示stream的用法）
        long count = hashSet.stream().count();
        System.out.println("HashSet中元素的数量（通过stream API计算）: " + count);

        // 20. 演示使用stream API查找HashSet中的最大值（假设元素实现了Comparable接口，这里以字符串自然顺序为例）
        Optional<String> maxElement = hashSet.stream().max(String::compareTo);
        maxElement.ifPresent(System.out::println);

        // 21. 演示使用stream API查找HashSet中的最小值（假设元素实现了Comparable接口，这里以字符串自然顺序为例）
        Optional<String> minElement = hashSet.stream().min(String::compareTo);
        minElement.ifPresent(System.out::println);

        // 22. 演示将HashSet转换为LinkedHashSet（保持元素插入顺序）
        HashSet<String> anotherHashSet = new HashSet<>(Arrays.asList("pear", "peach", "plum"));
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(anotherHashSet);
        System.out.println("转换为LinkedHashSet后的内容（保持插入顺序）:");
        for (String element : linkedHashSet) {
            System.out.println(element);
        }

        // 23. 演示将HashSet转换为TreeSet（按照元素自然顺序排序，元素需实现Comparable接口）
        HashSet<String> hashSetForTreeSet = new HashSet<>(Arrays.asList("mango", "orange", "watermelon"));
        TreeSet<String> treeSet = new TreeSet<>(hashSetForTreeSet);
        System.out.println("转换为TreeSet后的内容（按照自然顺序排序）:");
        for (String element : treeSet) {
            System.out.println(element);
        }

        // 24. 演示使用spliterator方法进行可拆分遍历（Java 8及以后）
        Spliterator<String> spliterator = hashSet.spliterator();
        Spliterator<String> trySplit = spliterator.trySplit();
        if (trySplit!= null) {
            System.out.println("第一个可拆分部分的元素遍历:");
            trySplit.forEachRemaining(System.out::println);
        }
        System.out.println("第二个可拆分部分的元素遍历:");
        spliterator.forEachRemaining(System.out::println);

        // 25. 演示批量添加元素（使用addAll方法结合可变参数形式，更方便添加多个元素）
        hashSet.clear();
        hashSet.addAll(Arrays.asList("apple", "banana", "cherry"));
        System.out.println("批量添加元素后的HashSet内容:");
        for (String element : hashSet) {
            System.out.println(element);
        }

        // 26. 演示使用自定义比较器进行排序（将HashSet转换为List后排序，再转换回HashSet，以实现按照自定义规则排序）
        HashSet<String> customSortHashSet = new HashSet<>(Arrays.asList("grape", "melon", "strawberry"));
        List<String> customSortList = new ArrayList<>(customSortHashSet);
        Collections.sort(customSortList, (s1, s2) -> s1.length() - s2.length());
        HashSet<String> sortedHashSetByCustom = new HashSet<>(customSortList);
        System.out.println("按照自定义规则排序后的HashSet内容（按字符串长度升序）:");
        for (String element : sortedHashSetByCustom) {
            System.out.println(element);
        }

        // 27. 演示使用HashSet存储自定义对象，并保证对象的唯一性（需重写hashCode和equals方法）
        HashSet<Person> personSet = new HashSet<>();
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 30);
        Person person3 = new Person("Alice", 25);
        personSet.add(person1);
        personSet.add(person2);
        personSet.add(person3);
        System.out.println("存储自定义对象的HashSet内容（根据重写的方法判断唯一性）:");
        for (Person person : personSet) {
            System.out.println(person);
        }

        // 28. 演示使用HashSet进行集合运算（与另一个HashSet求交集、并集、差集）
        HashSet<String> set1 = new HashSet<>(Arrays.asList("a", "b", "c"));
        HashSet<String> set2 = new HashSet<>(Arrays.asList("b", "c", "d"));

        // 求交集
        HashSet<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("两个HashSet的交集:");
        for (String element : intersection) {
            System.out.println(element);
        }

        // 求并集
        HashSet<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("两个HashSet的并集:");
        for (String element : union) {
            System.out.println(element);
        }

        // 求差集（set1 - set2）
        HashSet<String> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("set1与set2的差集（set1 - set2）:");
        for (String element : difference) {
            System.out.println(element);
        }

        // 29. 演示使用HashSet作为缓存（简单示例，实际应用需考虑更多因素如缓存过期等）
        HashSet<String> cache = new HashSet<>();
        String key = "query_key";
        if (!cache.contains(key)) {
            // 模拟从数据库或其他数据源获取数据
            String data = "Some data for " + key;
            cache.add(key);
            System.out.println("缓存未命中，添加数据到缓存: " + data);
        } else {
            System.out.println("缓存命中");
        }

        // 30. 演示使用HashSet进行数据去重并统计出现次数（结合HashMap实现，将元素作为键，出现次数作为值）
        List<String> dataList = Arrays.asList("apple", "banana", "apple", "cherry", "banana");
        HashSet<String> uniqueData = new HashSet<>();
        HashMap<String, Integer> countMap = new HashMap<>();
        for (String element : dataList) {
            if (uniqueData.add(element)) {
                countMap.put(element, 1);
            } else {
                countMap.put(element, countMap.get(element) + 1);
            }
        }
        System.out.println("去重后的数据及出现次数统计:");
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass()!= o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    "age=" + age +
                    '}';
        }
    }
}

