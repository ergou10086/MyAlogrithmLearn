package 数据结构.Java容器.Map.使用;

import java.util.*;

public class HashMap_Use {
    public static void main(String[] args) {
        new HashMap_();
    }
}


class HashMap_ {
    // Map接口是一个键值对的集合,HashMap是Map接口的一个实现类
    // HashMap的数据结构是数组+链表，在JDK1.8及以后优化为数组+链表+红黑树。
    // 对于哈希冲突的不同键值对结点，使用红黑树优化
    // HashMap 的 key 与 value 类型可以相同也可以不同
    // HashMap的key和value都可以为null，但是key为null的键值对只能有一个
    // HashMap的key不能重复，value无限制
    // HashMap的key和value都是无序的，不保证顺序

    public static void demoHashMap() {
        // 创建HashMap实例，指定键的类型为String，值的类型为Integer（可按需指定类型）
        HashMap<String, Integer> hashMap = new HashMap<>();

        // 添加键值对到HashMap
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        hashMap.put("key3", 3);

        // 获取HashMap的大小（键值对的数量）
        int size = hashMap.size();
        System.out.println("HashMap当前大小: " + size);

        // 判断HashMap是否为空
        boolean isEmpty = hashMap.isEmpty();
        System.out.println("HashMap是否为空: " + isEmpty);

        // 通过键获取对应的值
        Integer valueForKey1 = hashMap.get("key1");
        System.out.println("键key1对应的值是: " + valueForKey1);

        // 检查HashMap是否包含某个键
        boolean containsKey = hashMap.containsKey("key3");
        System.out.println("是否包含键key3: " + containsKey);

        // 检查HashMap是否包含某个值（遍历查找，效率相对较低）
        boolean containsValue = hashMap.containsValue(3);
        System.out.println("是否包含值3: " + containsValue);

        // 移除指定键对应的键值对
        Integer removedValue = hashMap.remove("key1");
        System.out.println("移除键key1对应的键值对，移除的值是: " + removedValue);

        // 演示putAll方法，将另一个Map中的所有键值对添加到当前HashMap
        HashMap<String, Integer> anotherHashMap = new HashMap<>();
        anotherHashMap.put("key4", 4);
        anotherHashMap.put("key5", 5);
        hashMap.putAll(anotherHashMap);
        System.out.println("使用putAll方法后HashMap内容:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示keySet方法，获取HashMap中所有键的集合
        Set<String> keySet = hashMap.keySet();
        System.out.println("HashMap的键集合:");
        for (String key : keySet) {
            System.out.println(key);
        }

        // 演示values方法，获取HashMap中所有值的集合
        Collection<Integer> values = hashMap.values();
        System.out.println("HashMap的值集合:");
        for (Integer value : values) {
            System.out.println(value);
        }

        // 演示entrySet方法，获取HashMap中所有键值对的集合（以Entry形式）
        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        System.out.println("使用entrySet遍历HashMap:");
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示替换指定键对应的值（如果键存在）
        boolean replacedValue = hashMap.replace("key2", 2, 22);
        System.out.println("替换键key2的值，原来的值是: " + replacedValue);

        // 演示仅当指定键不存在时才添加键值对（putIfAbsent方法）
        Integer putIfAbsentValue = hashMap.putIfAbsent("key6", 6);
        System.out.println("使用putIfAbsent添加键key6，返回值（若键已存在则返回旧值，不存在返回null）: " + putIfAbsentValue);

        // 演示根据旧值来替换新值（仅当旧值匹配时替换，replace方法的另一种重载形式）
        boolean replacedByOldValue = hashMap.replace("key2", 22, 23);
        System.out.println("根据旧值替换键key2的值，是否替换成功: " + replacedByOldValue);

        // 演示使用lambda表达式结合forEach方法遍历HashMap（Java 8及以后）
        hashMap.forEach((key, value) -> System.out.println(key + " -> " + value));

        // 演示使用迭代器遍历entrySet（更灵活，可在遍历中进行更多操作）
        Iterator<Map.Entry<String, Integer>> iterator = hashMap.entrySet().iterator();
        System.out.println("使用迭代器遍历HashMap:");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            if ("key4".equals(entry.getKey())) {
                iterator.remove(); // 可以在迭代过程中移除元素
            }
        }

        // 演示排序（按照键排序，先将键值对转换为List，再自定义排序规则，这里以键的自然顺序排序为例）
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(hashMap.entrySet());
        Collections.sort(entryList, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
        System.out.println("按照键排序后的HashMap内容:");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示合并两个HashMap（使用merge方法，根据键合并值，自定义合并逻辑）
        HashMap<String, Integer> mergeHashMap = new HashMap<>();
        mergeHashMap.put("key3", 33);
        mergeHashMap.put("key5", 55);

        // 1. merge方法示例一：简单的合并数值，若键不存在则添加
        hashMap.forEach((key, value) -> mergeHashMap.merge(key, value, (v1, v2) -> v1 + v2));
        System.out.println("合并后的HashMap内容（简单数值合并）:");
        for (Map.Entry<String, Integer> entry : mergeHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 2. merge方法示例二：合并字符串值（拼接），若键不存在则添加
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put("name", "Alice");
        HashMap<String, String> anotherStringHashMap = new HashMap<>();
        anotherStringHashMap.put("name", "Bob");
        anotherStringHashMap.put("city", "New York");

        anotherStringHashMap.forEach((key, value) -> stringHashMap.merge(key, value, (v1, v2) -> v1 + ", " + v2));
        System.out.println("合并后的字符串HashMap内容（字符串拼接）:");
        for (Map.Entry<String, String> entry : stringHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 3. merge方法示例三：根据条件合并，例如只合并偶数数值
        HashMap<String, Integer> conditionMergeHashMap = new HashMap<>();
        conditionMergeHashMap.put("key1", 1);
        conditionMergeHashMap.put("key2", 2);
        HashMap<String, Integer> moreDataHashMap = new HashMap<>();
        moreDataHashMap.put("key1", 3);
        moreDataHashMap.put("key2", 4);

        moreDataHashMap.forEach((key, value) -> conditionMergeHashMap.merge(key, value, (v1, v2) -> (v1 % 2 == 0 && v2 % 2 == 0)? v1 + v2 : v1));
        System.out.println("根据条件合并后的HashMap内容（只合并偶数数值）:");
        for (Map.Entry<String, Integer> entry : conditionMergeHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示compute方法示例一：简单的重新计算值（平方计算）
        HashMap<String, Integer> computeHashMap = new HashMap<>();
        computeHashMap.put("num1", 2);
        computeHashMap.put("num2", 3);

        computeHashMap.compute("num1", (key, oldValue) -> oldValue * oldValue);
        System.out.println("使用compute方法重新计算后（平方计算）的HashMap内容:");
        for (Map.Entry<String, Integer> entry : computeHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示compute方法示例二：根据条件重新计算值（大于5则减1）
        computeHashMap.compute("num2", (key, oldValue) -> oldValue > 5? oldValue - 1 : oldValue);
        System.out.println("使用compute方法根据条件重新计算后（大于5则减1）的HashMap内容:");
        for (Map.Entry<String, Integer> entry : computeHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfAbsent方法示例一：添加新键值对并计算初始值（以键的长度作为初始值）
        HashMap<String, Integer> computeIfAbsentHashMap = new HashMap<>();
        computeIfAbsentHashMap.computeIfAbsent("newKey1", key -> key.length());
        System.out.println("使用computeIfAbsent方法添加新键值对并计算初始值（以键的长度作为初始值）后的HashMap内容:");
        for (Map.Entry<String, Integer> entry : computeIfAbsentHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfAbsent方法示例二：利用已有数据结构计算并添加新键值对（以List中元素个数作为值）
        HashMap<String, List<Integer>> listHashMap = new HashMap<>();
        List<Integer> list = Arrays.asList(1, 2, 3);
        listHashMap.computeIfAbsent("listKey", k -> new ArrayList<>()).addAll(list);
        System.out.println("使用computeIfAbsent方法利用已有数据结构计算并添加新键值对（以List中元素个数作为值）后的HashMap内容:");
        for (Map.Entry<String, List<Integer>> entry : listHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfPresent方法示例一：更新存在键的值（加倍计算）
        HashMap<String, Integer> computeIfPresentHashMap = new HashMap<>();
        computeIfPresentHashMap.put("key1", 2);
        computeIfPresentHashMap.computeIfPresent("key1", (key, oldValue) -> oldValue * 2);
        System.out.println("使用computeIfPresent方法更新存在键的值（加倍计算）后的HashMap内容:");
        for (Map.Entry<String, Integer> entry : computeIfPresentHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示computeIfPresent方法示例二：根据条件更新存在键的值（大于3则变为负数）
        computeIfPresentHashMap.computeIfPresent("key1", (key, oldValue) -> oldValue > 3? -oldValue : oldValue);
        System.out.println("使用computeIfPresent方法根据条件更新存在键的值（大于3则变为负数）后的HashMap内容:");
        for (Map.Entry<String, Integer> entry : computeIfPresentHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 演示使用stream API计算HashMap中所有值的总和（使用stream API，Java 8及以后）
        int sum = hashMap.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("HashMap中所有值的总和是: " + sum);

        // 演示获取HashMap中值最大的键值对（使用stream API和自定义比较器，Java 8及以后）
        Optional<Map.Entry<String, Integer>> maxEntry = hashMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
        maxEntry.ifPresent(entry -> System.out.println("值最大的键值对是: " + entry.getKey() + " -> " + entry.getValue()));

        // 演示获取HashMap中值最小的键值对（使用stream API和自定义比较器，Java 8及以后）
        Optional<Map.Entry<String, Integer>> minEntry = hashMap.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));
        minEntry.ifPresent(entry -> System.out.println("值最小的键值对是: " + entry.getKey() + " -> " + entry.getValue()));

        // 清空HashMap
        hashMap.clear();
    }
}
