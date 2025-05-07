package Java算竞实用技术.常用实用方法.字符串处理;

public class StringBuilder类 {
    public static void main(String[] args) {
        // 由于String类的对象内容不可改变，所以每当进行字符串拼接时，总是会在内存中创建一个新的对象
        // StringBuilder表面看起来是用来拼接、处理字符串的一个工具类，但它的内部实现其实是处理字符序列。
        // StringBuilder是可变字符序列，底层使用动态数组
        // StringBuilder与StringBuffer具有相同的功能

        // 常用方法
        // 拼接
        // void append(XXX xxx) 可接收大部分基本数据类型
        StringBuilder sb1 = new StringBuilder();
        char[] chars={'b','g','t'};
        sb1.append(chars);
        sb1.append(6);//append()方法其实就是向容器中追加数据
        sb1.append("abc");
        sb1.append('c');
        sb1.append(false);
        System.out.println(sb1);//cdebgt6abccfalse

        // 反序
        // void reverse()
        StringBuilder sb2 = new StringBuilder("猪头大一来过上海");
        sb2.reverse();
        System.out.println(sb2); // 输出结果为：海上过来一大头猪
        // 应用：回文处理
        StringBuilder sb3 = new StringBuilder("abbbba");
        if (sb3.reverse().toString().equals("abbbba")) {
            System.out.println(sb2);
        }

        // 替换
        // void replace(int start, int end, String str)
        // 根据索引把某部分替换成其它的。
        StringBuilder sb4 = new StringBuilder("春眠不觉晓，处处闻啼鸟。");
        sb4.replace(8, 11, "蚊子咬");
        System.out.println(sb4); // 输出结果为：春眠不觉晓，处处蚊子咬。

        // 删减
        // void delete(int start, int end)
        // 根据索引删减某部分。
        StringBuilder sb5 = new StringBuilder("爸爸的脸蛋有很多褶皱");
        sb5.delete(3, 4);
        System.out.println(sb5); // 输出结果为：爸爸的蛋有很多褶皱

        // 插入
        // void insert(int offset, XXX xxx)
        // 在索引位置插入一段。
        StringBuilder sb6 = new StringBuilder("大家好我是李鹏飞");
        sb6.insert(5, "bb鸟");
        System.out.println(sb6);

        // 容量和长度
        // StringBuilder默认的初始容量是16，可用带参构造器来自定义初始容量。
        // 当字符个数达到了当前容量，则容量会自动提升，容量提升的规律为：新容量 = 旧容量 * 2 + 2

        // 返回StringBuilder的当前容量
        // int capacity()
        System.out.println(sb6.capacity());

        // 长度
        // int length()
        // 返回当前已存储的字符的个数。
        StringBuilder sb7 = new StringBuilder(6);
        sb7.append("李鹏飞喜欢踩背？有大问题!.");
        System.out.println(sb7.capacity()); // 转出结果为：14
        System.out.println(sb7.length()); // 转出结果为：14

        // 获取内容
        // char charAt(int index)，根据索引，得到相应字符。
        // String substring(int start)，根据索引，以字符串形式截取尾部段。
        // String substring(int start, int end)，根据索引，以字符串形式截取内部段。
        // String toString()，转换为不可变的String对象


        // 返回匹配对应的索引
        //int indexOf(String str)，输出第一个匹配的索引。
        //int indexOf(String str, int fromIndex)，从指定的索引处开始，输出第一个匹配的索引。
        //int lastIndexOf(String str)，输出最后一个匹配的索引。
        //int lastIndexOf(String str, int fromIndex)，从指定的索引处开始，输出最后一个匹配的索引。
        System.out.println(sb7.indexOf("踩背", 3));


    }
}
