package Java算竞实用技术.自定义排序.使用比较器Comparator接口.正常写法;

// 使用比较器：实现Comparator接口
// 需要实现java.util.Comparator接口。
// 需要实现compare(Object o1, Object o2)方法,制定比较规则
// 经常要把这个Comparator这个比较器传递给需要的部分（如TreeSet的构造方法、Arrays.sort(排序的内容，比较器)）

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 正常写法 {
    public static void main(String[] args) {
        // 使用ArrayList的情况
        ArrayList<island> IslandList = new ArrayList<>();
        IslandList.add(new island(100.0, 50.0));
        IslandList.add(new island(80.0, 40.0));
        IslandList.add(new island(100.0, 45.0));
        IslandList.add(new island(150.0, 60.0));
        IslandList.add(new island(100.2, 54.0));

        // 排序，在排序方法中的第二个形参传入自定义比较器
        IslandList.sort(new islandComparator());

        // 或者
        Collections.sort(IslandList, new islandComparator());

        // 输出排序结果
        System.out.println("ArrayList 排序结果:");
        for (island x : IslandList) {
            System.out.println(x);
        }


        // 使用数组的情况
        island[] islands = new island[5];
        islands[0] = new island(100.0, 50.0);
        islands[1] = new island(80.0, 40.0);
        islands[2] = new island(100.0, 45.0);
        islands[3] = new island(150.0, 60.0);
        islands[4] = new island(100.2, 54.0);

        // 排序（传入自定义比较器）
        Arrays.sort(islands, new islandComparator());

        // 输出排序结果
        System.out.println("\n数组排序结果:");
        for (island x : islands) {
            System.out.println(x);
        }
    }
}


// 自定义比较器类
// 需要实现Comparator 接口
class islandComparator implements Comparator<island> {
    @Override
    public int compare(island o1, island o2) {
        // 1. 按面积降序排序,o2.square - o1.square，大的在前,降序
        int saq_comp = Double.compare(o2.square, o1.square);
        if (saq_comp != 0) {
            return saq_comp;
        }

        // 2. 面积相同则按周长升序排序，小的在前，升序
        // 使用 Double.compare() 避免浮点数精度问题。
        return Double.compare(o1.circumference, o2.circumference);
    }
}

class island {
    double square;
    double circumference;

    public island(double square, double circumference){
        this.square = square;
        this.circumference = circumference;
    }
}