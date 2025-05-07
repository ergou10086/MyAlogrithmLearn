package Java算竞实用技术.常用实用方法.字符串处理;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 字符串方法 {
    public static void main(String[] args) {
        // String 声明为 final 的，也就是不可以被继承，因而 String 也不是 Java 的基本数据类型
        // String 实现了 Serializable 接口，说明字符串支持可序列化；实现了 Comparable 接口，表示可以比较大小
        // String 在内部定义了 final 的 char 型数组，用于存储字符串数组

        // String 的实例化方式
        // 方法1：通过字面量的方法，也就是直接给所定义的字符串赋值；
        // 方法2：通过 new + 构造器 的方法；

        // 通过字面量的方法:此时的s1和s2是声明在方法区中的字符串常量池中，保存在字符串常量池中，栈中存放形参变量
        String s1 = "JavaEE";
        String s2 = "JavaEE";

        // 通过new + 构造器的方法: 字符串在堆空间中开辟空间后，此时的s3和s4保存的是地址值，即保存在堆空间中
        String s3 = new String("JavaEE");
        String s4 = new String("JavaEE");

        System.out.println(s1.equals(s2));   //输出 true，比较的是本身值的大小
        System.out.println(s3.equals(s4));   //输出 true，比较的是本身值的大小
        System.out.println(s1 == s2);        // true
        System.out.println(s3 == s4);        //输出 false，比较的是地址值

        // 常见的字符串方法
        // String 的常用方法
        // 1、s1.length() 获取字符串的长度
        System.out.println("s1的长度: " + s1.length());

        // 2、s1.charAt() 获取字符串中指定索引的字符
        System.out.println("s1索引为2的字符: " + s1.charAt(2));

        // 3、s1.isEmpty() 判断字符串是否为空
        System.out.println("s1是否为空: " + s1.isEmpty());

        // 4、s1.toLowerCase() 将字符串中所有大写字母转换为小写
        System.out.println("s1转换为小写: " + s1.toLowerCase());

        // 5、s1.toUpperCase() 将字符串中所有小写字母转换为大写
        System.out.println("s1转换为大写: " + s1.toUpperCase());

        // 6、s1.trim() 返回删除字符串中前后空格后新的字符串
        String s5 = "  JavaEE  ";
        System.out.println("s5去除前后空格: " + s5.trim());

        // 7、s1.equals() 判断两个字符串是否相等
        System.out.println("s1和s3是否相等: " + s1.equals(s3));

        // 8、s1.equalsIgnoreCase() 忽略大小写的情况下判断两个字符串是否相等
        String s6 = "javaee";
        System.out.println("s1和s6忽略大小写是否相等: " + s1.equalsIgnoreCase(s6));

        // 9、s1.concat() 做字符串的拼接，相当于 ”+“
        System.out.println("s1拼接 'Hello': " + s1.concat("Hello"));

        // 10、s1.substring() 从指定索引处开始截取字符串，直到末尾
        System.out.println("s1从索引2开始截取: " + s1.substring(2));

        // 11、s1.substring(index1, index2) 采用 包含索引1，不包含索引2 形式的截取字符串
        System.out.println("s1从索引2到4截取: " + s1.substring(2, 4));

        // 12、s1.endsWith() 判断字符串是否以指定的后缀结束
        System.out.println("s1是否以 'EE' 结尾: " + s1.endsWith("EE"));

        // 13、s1.startsWith() 判断字符串是否以指定的前缀开始
        System.out.println("s1是否以 'Java' 开头: " + s1.startsWith("Java"));

        // 14、s1.contains() 当且仅当此字符串包含指定的char值序列时，返回true
        System.out.println("s1是否包含 'va': " + s1.contains("va"));

        // 15、s1.indexOf() 返回指定子字符串在此字符串中第一次出现处的索引，未找到返回 -1
        System.out.println("'va' 在s1中第一次出现的索引: " + s1.indexOf("va"));

        // 16、s1.lastIndexOf() 返回指定子字符串在此字符串中最右边出现处的索引，未找到返回 -1
        System.out.println("'a' 在s1中最后一次出现的索引: " + s1.lastIndexOf("a"));

        // 17、s1.replace(char oldChar, char newChar) 返回一个经过替换后新的字符串
        System.out.println("s1中 'a' 替换为 'o': " + s1.replace('a', 'o'));

        // 18、s1.matches(String regex) 判断此字符串是否匹配给定的正则表达式
        System.out.println("s1是否匹配 'Java.*': " + s1.matches("Java.*"));

        // 算法竞赛中实用的 String 方法和操作
        // 1. 字符串分割
        String sentence = "Hello,World,Java";
        String[] words = sentence.split(",");
        for (String word : words) {
            System.out.println("分割后的单词: " + word);
        }

        // 2. 字符串反转
        StringBuilder sb = new StringBuilder(s1);
        String reversed = sb.reverse().toString();
        System.out.println("s1反转后的字符串: " + reversed);

        // 3. 判断字符串是否为回文
        // 包装成StringBuilder
        boolean isPalindrome = s1.equals(new StringBuilder(s1).reverse().toString());
        System.out.println("s1是否为回文: " + isPalindrome);

        // 4. 字符串与字符数组转换
        char[] charArray = s1.toCharArray();
        String newString = new String(charArray);
        System.out.println("字符数组转换回字符串: " + newString);

        // 5.字符串拼接
        String[] words3 = {"Hello", "World", "Java"};
        String result = String.join(", ", words3);
        System.out.println(result);
        // 使用集合拼接
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        String joinedFruits = String.join(" | ", fruits);
        System.out.println(joinedFruits);

        // 6.使用Stream API数字数组转换为字符串
        int[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = Arrays.stream(intArray).   // Arrays.stream(intArray)：把 int 数组转换为 IntStream。
                mapToObj(String::valueOf).   // mapToObj(String::valueOf)：将 IntStream 里的每个 int 元素映射为 String 类型
                toArray(String[]::new);    // toArray(String[]::new)：把映射后的元素收集到一个 String 数组中。
        for (String str : strArray) {
            System.out.print(str + " ");
        }


        // 7.String 与 字符数组间的转换
        // String -> char[]：调用 String.toCharArray() 方法
        // char[] -> String：调用 String 的构造器
        String sp = "bbBird";
        char[] charsp = sp.toCharArray();
        for (int i = 0; i < charsp.length; i++) System.out.print(charsp[i] + " ");
        System.out.println();
        char[] c2 = new char[]{'h', 'e', 'l', 'l', 'o'};
        String s22 = new String(c2);
        System.out.println(s22);
    }
}
