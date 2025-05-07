package Java算竞实用技术.自定义排序.使用比较器Comparator接口.匿名内部类和Lambda表达式;

import java.util.*;

public class 简略写法 {
    public static void main(String[] args) {
        List<island2> IslandList = new ArrayList<>();
        IslandList.add(new island2(100.0, 50.0));
        IslandList.add(new island2(80.0, 40.0));
        IslandList.add(new island2(100.0, 45.0));
        IslandList.add(new island2(150.0, 60.0));
        IslandList.add(new island2(100.2, 54.0));

        // 使用匿名内部类，直接new接口
        Collections.sort(IslandList, new Comparator<island2>() {
            @Override
            public int compare(island2 o1, island2 o2) {
                if(o2.square == o1.square) return (int) (o1.circumference - o2.circumference);
                else return Double.compare(o1.circumference, o2.circumference);
            }
        });

        System.out.println("ArrayList排序结果：");
        for (island2 island : IslandList) {
            System.out.println(island);
        }


        island2[] islandArray = {
                new island2(100.0, 50.0),
                new island2(80.0, 40.0),
                new island2(100.0, 45.0),
                new island2(150.0, 60.0),
                new island2(100.2, 54.0)
        };

        // 使用Lambda表达式
        // 直接在排序时定义规则
        Arrays.sort(islandArray, (o1, o2) -> {
            int areaCompare = Double.compare(o2.square, o1.square);
            return areaCompare != 0 ? areaCompare : Double.compare(o1.circumference, o2.circumference);
        });

        System.out.println("\n数组排序结果：");
        for (island2 island : islandArray) {
            System.out.println(island);
        }


        // 二维数组举例
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];  // 如果想让h相同k小的站前面,多这一行！
                return o2[0] - o1[0];  // o1本来就已经是数组中的每个元素，再o1[]就是该元素内部的东西再指定一次
            }
        });


        for (int[] data : people) {
            System.out.println(Arrays.toString(data));
        }

    }
}

class island2 {
    double square;
    double circumference;

    public island2(double square, double circumference) {
        this.square = square;
        this.circumference = circumference;
    }
}