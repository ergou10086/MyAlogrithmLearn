package 数据结构.Java容器.Map.使用;

import java.util.*;

public class LinkedHashMap_Use {
    public static void main(String[] args) {
        // Map接口是一个键值对的集合

    }
}

class LinkedHashMap_ {
    // 迭代HashMap的顺序并不是HashMap放置的顺序，也就是无序
    // LinkedHashMap可以认为是HashMap+LinkedList
    // LinkedHashMap是HashMap的子类，通过维护一个运行于所有条目的双向链表
    // LinkedHashMap保证了元素迭代的顺序。该迭代顺序可以是插入顺序或者是访问顺序。
    // LinkedHashMap是否允许重复数据: Key重复会覆盖、Value允许重复
    // LinkedHashMap是否线程安全  非线程安全

    public static void demoLinkedHashMap() {
        // 创建LinkedHashMap实例，指定键的类型为String，值的类型为Integer（可按需指定类型），默认按插入顺序
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> linkedHashMap1 = new LinkedHashMap<>(15, 10, true);  // 指定容量、负载因子、是否按访问顺序排序

        // 添加键值对到LinkedHashMap，按照插入顺序记录
        linkedHashMap.put("key1", 1);
        linkedHashMap.put("key2", 2);
        linkedHashMap.put("key3", 3);

        // 获取LinkedHashMap的大小（键值对的数量）
        int size = linkedHashMap.size();
        System.out.println("LinkedHashMap当前大小: " + size);

        // 判断LinkedHashMap是否为空
        boolean isEmpty = linkedHashMap.isEmpty();
        System.out.println("LinkedHashMap是否为空: " + isEmpty);

        // 通过键获取对应的值
        Integer valueForKey1 = linkedHashMap.get("key1");
        System.out.println("键key1对应的值是: " + valueForKey1);

        // 检查LinkedHashMap是否包含某个键
        boolean containsKey = linkedHashMap.containsKey("key3");
        System.out.println("是否包含键key3: " + containsKey);

        // 检查LinkedHashMap是否包含某个值（遍历查找，效率相对较低）
        boolean containsValue = linkedHashMap.containsValue(3);
        System.out.println("是否包含值3: " + containsValue);

        // 移除指定键对应的键值对
        Integer removedValue = linkedHashMap.remove("key1");
        System.out.println("移除键key1对应的键值对，移除的值是: " + removedValue);

        // 演示putAll方法，将另一个Map中的所有键值对添加到当前LinkedHashMap
        LinkedHashMap<String, Integer> anotherLinkedHashMap = new LinkedHashMap<>();
        anotherLinkedHashMap.put("key4", 4);
        anotherLinkedHashMap.put("key5", 5);
        linkedHashMap.putAll(anotherLinkedHashMap);
        System.out.println("使用putAll方法后LinkedHashMap内容:");
        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示keySet方法，获取LinkedHashMap中所有键的集合
        Set<String> keySet = linkedHashMap.keySet();
        System.out.println("LinkedHashMap的键集合:");
        for (String key : keySet) {
            System.out.println(key);
        }

        // 演示values方法，获取LinkedHashMap中所有值的集合
        Collection<Integer> values = linkedHashMap.values();
        System.out.println("LinkedHashMap的值集合:");
        for (Integer value : values) {
            System.out.println(value);
        }

        // 演示entrySet方法，获取LinkedHashMap中所有键值对的集合（以Entry形式）
        Set<Map.Entry<String, Integer>> entrySet = linkedHashMap.entrySet();
        System.out.println("使用entrySet遍历LinkedHashMap:");
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示替换指定键对应的值（如果键存在）
        boolean replacedValue = linkedHashMap.replace("key2", 2, 22);
        System.out.println("替换键key2的值，原来的值是: " + replacedValue);

        // 演示仅当指定键不存在时才添加键值对（putIfAbsent方法）
        Integer putIfAbsentValue = linkedHashMap.putIfAbsent("key6", 6);
        System.out.println("使用putIfAbsent添加键key6，返回值（若键已存在则返回旧值，不存在返回null）: " + putIfAbsentValue);

        // 演示根据旧值来替换新值（仅当旧值匹配时替换，replace方法的另一种重载形式）
        boolean replacedByOldValue = linkedHashMap.replace("key2", 22, 23);
        System.out.println("根据旧值替换键key2的值，是否替换成功: " + replacedByOldValue);

        // 演示使用lambda表达式结合forEach方法遍历LinkedHashMap（Java 8及以后）
        linkedHashMap.forEach((key, value) -> System.out.println(key + " -> " + value));

        // 演示使用迭代器遍历entrySet（更灵活，可在遍历中进行更多操作）
        Iterator<Map.Entry<String, Integer>> iterator = linkedHashMap.entrySet().iterator();
        System.out.println("使用迭代器遍历LinkedHashMap:");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            if ("key4".equals(entry.getKey())) {
                iterator.remove(); // 可以在迭代过程中移除元素
            }
        }

        // 演示按照插入顺序排序（默认就是插入顺序，这里再次强调展示）
        System.out.println("按照插入顺序遍历LinkedHashMap:");
        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示按照访问顺序排序
        LinkedHashMap<String, Integer> accessOrderLinkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderLinkedHashMap.put("a", 1);
        accessOrderLinkedHashMap.put("b", 2);
        accessOrderLinkedHashMap.put("c", 3);

        accessOrderLinkedHashMap.get("b"); // 访问了 "b" 这个键，会调整顺序
        System.out.println("按照访问顺序遍历LinkedHashMap（访问 'b' 后）:");
        for (Map.Entry<String, Integer> entry : accessOrderLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示merge方法，根据键合并值，自定义合并逻辑
        LinkedHashMap<String, Integer> mergeLinkedHashMap = new LinkedHashMap<>();
        mergeLinkedHashMap.put("key3", 33);
        mergeLinkedHashMap.put("key5", 55);

        linkedHashMap.forEach((key, value) -> mergeLinkedHashMap.merge(key, value, (v1, v2) -> v1 + v2));
        System.out.println("合并后的LinkedHashMap内容（数值合并）:");
        for (Map.Entry<String, Integer> entry : mergeLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示compute方法，对指定键的值进行重新计算
        LinkedHashMap<String, Integer> computeLinkedHashMap = new LinkedHashMap<>();
        computeLinkedHashMap.put("num1", 2);
        computeLinkedHashMap.put("num2", 3);

        computeLinkedHashMap.compute("num1", (key, oldValue) -> oldValue * oldValue);
        System.out.println("使用compute方法重新计算后（平方计算）的LinkedHashMap内容:");
        for (Map.Entry<String, Integer> entry : computeLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfAbsent方法，对指定键的值进行重新计算，如果不存在这个键，则添加到LinkedHashMap中
        LinkedHashMap<String, Integer> computeIfAbsentLinkedHashMap = new LinkedHashMap<>();
        computeIfAbsentLinkedHashMap.computeIfAbsent("newKey1", key -> key.length());
        System.out.println("使用computeIfAbsent方法添加新键值对并计算初始值（以键的长度作为初始值）后的LinkedHashMap内容:");
        for (Map.Entry<String, Integer> entry : computeIfAbsentLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfPresent方法，对指定键的值进行重新计算，前提是该键存在于LinkedHashMap中
        LinkedHashMap<String, Integer> computeIfPresentLinkedHashMap = new LinkedHashMap<>();
        computeIfPresentLinkedHashMap.put("key1", 2);
        computeIfPresentLinkedHashMap.computeIfPresent("key1", (key, oldValue) -> oldValue * 2);
        System.out.println("使用computeIfPresent方法更新存在键的值（加倍计算）后的LinkedHashMap内容:");
        for (Map.Entry<String, Integer> entry : computeIfPresentLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示将LinkedHashMap转换为List（以键值对Entry形式），方便进行更多基于List的操作，比如排序等
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(linkedHashMap.entrySet());
        System.out.println("将LinkedHashMap转换为List后的内容:");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示对转换后的List进行排序（按照键的自然顺序排序为例）
        Collections.sort(entryList, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
        System.out.println("对转换后的List按照键排序后的内容:");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示将排序后的List再转换回LinkedHashMap（需要重新构建LinkedHashMap对象）
        LinkedHashMap<String, Integer> sortedLinkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedLinkedHashMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println("将排序后的List转换回LinkedHashMap后的内容:");
        for (Map.Entry<String, Integer> entry : sortedLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示使用stream API计算LinkedHashMap中所有值的总和
        int sum = linkedHashMap.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("LinkedHashMap中所有值的总和是: " + sum);

        // 演示获取LinkedHashMap中值最大的键值对（使用stream API和自定义比较器）
        Optional<Map.Entry<String, Integer>> maxEntry = linkedHashMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
        maxEntry.ifPresent(entry -> System.out.println("值最大的键值对是: " + entry.getKey() + " -> " + entry.getValue()));

        // 演示获取LinkedHashMap中值最小的键值对（使用stream API和自定义比较器）
        Optional<Map.Entry<String, Integer>> minEntry = linkedHashMap.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));
        minEntry.ifPresent(entry -> System.out.println("值最小的键值对是: " + entry.getKey() + " -> " + entry.getValue()));

        // 清空LinkedHashMap
        linkedHashMap.clear();
    }
}