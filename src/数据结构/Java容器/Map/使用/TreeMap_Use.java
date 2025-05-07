package 数据结构.Java容器.Map.使用;

import java.util.*;

public class TreeMap_Use {
    public static void main(String[] args) {
        new TreeMap_().demoTreeMap();
    }
}


class TreeMap_ {
    // TreeMap是有序的集合，可以任意顺序将元素插入到集合中，对集合进行遍历的时候每个元素将自动按照排好序的顺序输出
    // TreeMap的底层是红黑树，红黑树是一种平衡二叉树，它通过每个节点增加一个存储位表示节点的颜色，可以是RED或BLACK
    // TreeMap 继承于AbstractMap，所以它是一个Map，即一个key-value集合
    // TreeMap 实现了NavigableMap接口，意味着它支持一系列的导航方法

    public static void demoTreeMap() {
        // 创建TreeMap实例，指定键的类型为String，值的类型为Integer（可按需指定类型），键需要实现Comparable接口或者在构造函数传入自定义比较器来确定排序规则
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        // 添加键值对到TreeMap，元素会自动按照键的顺序排序
        treeMap.put("key3", 3);
        treeMap.put("key1", 1);
        treeMap.put("key2", 2);

        // 获取TreeMap的大小（键值对的数量）
        int size = treeMap.size();
        System.out.println("TreeMap当前大小: " + size);

        // 判断TreeMap是否为空
        boolean isEmpty = treeMap.isEmpty();
        System.out.println("TreeMap是否为空: " + isEmpty);

        // 通过键获取对应的值
        Integer valueForKey1 = treeMap.get("key1");
        System.out.println("键key1对应的值是: " + valueForKey1);

        // 检查TreeMap是否包含某个键
        boolean containsKey = treeMap.containsKey("key3");
        System.out.println("是否包含键key3: " + containsKey);

        // 检查TreeMap是否包含某个值（遍历查找，效率相对较低）
        boolean containsValue = treeMap.containsValue(3);
        System.out.println("是否包含值3: " + containsValue);

        // 移除指定键对应的键值对
        Integer removedValue = treeMap.remove("key1");
        System.out.println("移除键key1对应的键值对，移除的值是: " + removedValue);

        // 演示putAll方法，将另一个Map中的所有键值对添加到当前TreeMap
        TreeMap<String, Integer> anotherTreeMap = new TreeMap<>();
        anotherTreeMap.put("key4", 4);
        anotherTreeMap.put("key5", 5);
        treeMap.putAll(anotherTreeMap);
        System.out.println("使用putAll方法后TreeMap内容:");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示keySet方法，获取TreeMap中所有键的集合
        Set<String> keySet = treeMap.keySet();
        System.out.println("TreeMap的键集合:");
        for (String key : keySet) {
            System.out.println(key);
        }

        // 演示values方法，获取TreeMap中所有值的集合
        Collection<Integer> values = treeMap.values();
        System.out.println("TreeMap的值集合:");
        for (Integer value : values) {
            System.out.println(value);
        }

        // 演示entrySet方法，获取TreeMap中所有键值对的集合（以Entry形式）
        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        System.out.println("使用entrySet遍历TreeMap:");
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示替换指定键对应的值（如果键存在）
        boolean replacedValue = treeMap.replace("key2", 2, 22);
        System.out.println("替换键key2的值，原来的值是: " + replacedValue);

        // 演示仅当指定键不存在时才添加键值对（putIfAbsent方法）
        Integer putIfAbsentValue = treeMap.putIfAbsent("key6", 6);
        System.out.println("使用putIfAbsent添加键key6，返回值（若键已存在则返回旧值，不存在返回null）: " + putIfAbsentValue);

        // 演示根据旧值来替换新值（仅当旧值匹配时替换，replace方法的另一种重载形式）
        boolean replacedByOldValue = treeMap.replace("key2", 22, 23);
        System.out.println("根据旧值替换键key2的值，是否替换成功: " + replacedByOldValue);

        // 演示使用lambda表达式结合forEach方法遍历TreeMap（Java 8及以后）
        treeMap.forEach((key, value) -> System.out.println(key + " -> " + value));

        // 演示使用迭代器遍历entrySet（更灵活，可在遍历中进行更多操作）
        Iterator<Map.Entry<String, Integer>> iterator = treeMap.entrySet().iterator();
        System.out.println("使用迭代器遍历TreeMap:");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            if ("key4".equals(entry.getKey())) {
                iterator.remove(); // 可以在迭代过程中移除元素
            }
        }

        // 演示获取第一个键值对（基于红黑树的有序特性）
        Map.Entry<String, Integer> firstEntry = treeMap.firstEntry();
        System.out.println("TreeMap的第一个键值对是: " + firstEntry.getKey() + " -> " + firstEntry.getValue());

        // 演示获取最后一个键值对（基于红黑树的有序特性）
        Map.Entry<String, Integer> lastEntry = treeMap.lastEntry();
        System.out.println("TreeMap的最后一个键值对是: " + lastEntry.getKey() + " -> " + lastEntry.getValue());

        // 演示获取小于指定键的最大键值对
        Map.Entry<String, Integer> lowerEntry = treeMap.lowerEntry("key4");
        System.out.println("TreeMap中小于键key4的最大键值对是: " + lowerEntry.getKey() + " -> " + lowerEntry.getValue());

        // 演示获取小于等于指定键的最大键值对
        Map.Entry<String, Integer> floorEntry = treeMap.floorEntry("key4");
        System.out.println("TreeMap中小于等于键key4的最大键值对是: " + floorEntry.getKey() + " -> " + floorEntry.getValue());

        // 演示获取大于指定键的最小键值对
        Map.Entry<String, Integer> higherEntry = treeMap.higherEntry("key4");
        System.out.println("TreeMap中大于键key4的最小键值对是: " + higherEntry.getKey() + " -> " + higherEntry.getValue());

        // 演示获取大于等于指定键的最小键值对
        Map.Entry<String, Integer> ceilingEntry = treeMap.ceilingEntry("key4");
        System.out.println("TreeMap中大于等于键key4的最小键值对是: " + ceilingEntry.getKey() + " -> " + ceilingEntry.getValue());

        // 演示获取指定键的前一个键（基于有序特性）
        String lowerKey = treeMap.lowerKey("key4");
        System.out.println("TreeMap中键key4的前一个键是: " + lowerKey);

        // 演示获取指定键的后一个键（基于有序特性）
        String higherKey = treeMap.higherKey("key4");
        System.out.println("TreeMap中键key4的后一个键是: " + higherKey);

        // 演示获取键值对的子集（从指定的fromKey到toKey，左闭右开区间，基于有序特性）
        NavigableMap<String, Integer> subMap = (NavigableMap<String, Integer>) treeMap.subMap("key2", "key5");
        System.out.println("TreeMap的键值对子集（'key2'到'key5'，左闭右开）内容:");
        for (Map.Entry<String, Integer> entry : subMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示获取键值对的子集（从第一个键到指定的toKey，左闭右开区间，基于有序特性）
        NavigableMap<String, Integer> headMap = (NavigableMap<String, Integer>) treeMap.headMap("key5");
        System.out.println("TreeMap的键值对头子集（到'key5'，左闭右开）内容:");
        for (Map.Entry<String, Integer> entry : headMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示获取键值对的子集（从指定的fromKey到最后一个键，左闭右开区间，基于有序特性）
        NavigableMap<String, Integer> tailMap = (NavigableMap<String, Integer>) treeMap.tailMap("key2");
        System.out.println("TreeMap的键值对尾子集（从'key2'开始，左闭右开）内容:");
        for (Map.Entry<String, Integer> entry : tailMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示合并两个TreeMap（使用merge方法，根据键合并值，自定义合并逻辑）
        TreeMap<String, Integer> mergeTreeMap = new TreeMap<>();
        mergeTreeMap.put("key3", 33);
        mergeTreeMap.put("key5", 55);

        treeMap.forEach((key, value) -> mergeTreeMap.merge(key, value, (v1, v2) -> v1 + v2));
        System.out.println("合并后的TreeMap内容（数值合并）:");
        for (Map.Entry<String, Integer> entry : mergeTreeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示compute方法，对指定键的值进行重新计算
        TreeMap<String, Integer> computeTreeMap = new TreeMap<>();
        computeTreeMap.put("num1", 2);
        computeTreeMap.put("num2", 3);

        computeTreeMap.compute("num1", (key, oldValue) -> oldValue * oldValue);
        System.out.println("使用compute方法重新计算后（平方计算）的TreeMap内容:");
        for (Map.Entry<String, Integer> entry : computeTreeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfAbsent方法，对指定键的值进行重新计算，如果不存在这个键，则添加到TreeMap中
        TreeMap<String, Integer> computeIfAbsentTreeMap = new TreeMap<>();
        computeIfAbsentTreeMap.computeIfAbsent("newKey1", key -> key.length());
        System.out.println("使用computeIfAbsent方法添加新键值对并计算初始值（以键的长度作为初始值）后的TreeMap内容:");
        for (Map.Entry<String, Integer> entry : computeIfAbsentTreeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfPresent方法，对指定键的值进行重新计算，前提是该键存在于TreeMap中
        TreeMap<String, Integer> computeIfPresentTreeMap = new TreeMap<>();
        computeIfPresentTreeMap.put("key1", 2);
        computeIfPresentTreeMap.computeIfPresent("key1", (key, oldValue) -> oldValue * 2);
        System.out.println("使用computeIfPresent方法更新存在键的值（加倍计算）后的TreeMap内容:");
        for (Map.Entry<String, Integer> entry : computeIfPresentTreeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示将TreeMap转换为List（以键值对Entry形式），方便进行更多基于List的操作，比如排序等（虽然TreeMap本身有序，但转换后可利用List其他功能）
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(treeMap.entrySet());
        System.out.println("将TreeMap转换为List后的内容:");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示对转换后的List进行排序（按照值的大小排序为例，这里自定义比较器）
        Collections.sort(entryList, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        System.out.println("对转换后的List按照值排序后的内容:");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示将排序后的List再转换回TreeMap（需要重新构建TreeMap对象）
        TreeMap<String, Integer> sortedTreeMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedTreeMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println("将排序后的List转换回TreeMap后的内容:");
        for (Map.Entry<String, Integer> entry : sortedTreeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示使用stream API计算TreeMap中所有值的总和
        int sum = treeMap.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("TreeMap中所有值的总和是: " + sum);

        // 演示获取TreeMap中值最大的键值对（使用stream API和自定义比较器）
        Optional<Map.Entry<String, Integer>> maxEntry = treeMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
        maxEntry.ifPresent(entry -> System.out.println("值最大的键值对是: " + entry.getKey() + " -> " + entry.getValue()));

        // 演示获取TreeMap中值最小的键值对（使用stream API和自定义比较器）
        Optional<Map.Entry<String, Integer>> minEntry = treeMap.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));
        minEntry.ifPresent(entry -> System.out.println("值最小的键值对是: " + entry.getKey() + " -> " + entry.getValue()));

        // 清空TreeMap
        treeMap.clear();
    }
}