package Java算竞实用技术.自定义排序.不使用比较器Comparable接口;

// Comparable和Comparator怎么选择呢？
// 1、当比较规则不会发生改变的时候，或者说当比较规则只有1个的时候，建议实现Comparable接口。
// 2、如果比较规则有多个，并且需要多个比较规则之间频繁切换，建议使用Comparator接口

// 不使用比较器，实现Comparable接口
// 实现java.lang.Comparable接口
// 重写compareTo(Object obj)方法
// 注意使用this(当前元素)和obj进行比较，单参数
// compareTo()这个方法的返回值,就规定了排序的规则
// o.属性 - this.属性  降序
// this.属性 - o.属性  升序

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    // 对自定义类进行排序
    public static void main(String[] args) {
    }
}

class Solutions{
    public Solutions(){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("Alice", 25, 100.5));
        nodes.add(new Node("Bob", 25, 90.0));
        nodes.add(new Node("Bob", 25, 120.3));
        nodes.add(new Node("Alice", 30, 80.0));
        nodes.add(new Node("Alice", 25, 150.7));

        Collections.sort(nodes);  // 调用Collections.sort()排序

        for (Node node : nodes) {
            System.out.println(node);
        }
    }

    // 自定义类想进行排序，需要实现Comparable接口，否则在排序的时候，会出现向下转型失败
    class Node implements Comparable<Node>{
        String name;
        int age;
        double square;
        Node(){}
        Node(String name, int age, double square){
            this.name = name;
            this.age = age;
            this.square = square;
        }


        // 并且要重写compareTo方法
        /*
        compareTo方法的返回值很重要：
            如果当前对象this大于形参对象obj，则返回正整数；  表示升序
            如果当前对象this小于形参对象obj，则返回负整数；  表示降序
            如果当前对象this等于参数对象obj，则返回零。
        */
        @Override
        // 参数为对象
        public int compareTo(@NotNull Node o) {
            // 按照年龄升序排序
            int ageCompare = Integer.compare(this.age, o.age);
            if (ageCompare != 0) {
                return ageCompare;
            }

            // 年龄相同时按照名字排序。
            // 姓名是String类型，可以直接比。调用compareTo来完成比较。
            // 如果年龄一样的再按照姓名升序
            int nameCompare = this.name.compareTo(o.name);
            if (nameCompare != 0) {
                return nameCompare;
            }

            // 3. 姓名和年龄都相同则按 square 降序
            return Double.compare(o.square, this.square);
        }

        @Override
        public String toString() {
            return String.format("Name: %-6s Age: %-3d Square: %.1f", name, age, square);
        }
    }



    // 另外一种写法
    class bbBird implements Comparable<bbBird>{
        String bird_name;
        int big;
        int weight;

        public bbBird(){}
        public bbBird(String name, int big, int weight){
            this.bird_name = name;
            this.big = big;
            this.weight = weight;
        }


        @Override
        public int compareTo(bbBird o) {
            if(this.weight == o.weight){
                return o.big - this.big;   // 重量相同按照大小降序
            }else{
                return this.weight - o.weight;
            }
        }
    }
}
