package Java算竞实用技术.自定义排序.注意事项;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int[] primitiveArr = {5, 2, 8, 1, 3};
        // Arrays.sort() 默认是实现升序
        Arrays.sort(primitiveArr);
        System.out.println("基本数据类型升序: " + Arrays.toString(primitiveArr)); // [1, 2, 3, 5, 8]

        // 正常情况下想要改为降序，Arrays.sort(排序数组, Collections.reverseOrder()); 这样处理就可以了。
        // 但是基本数据类型数组无法直接降序排序（会编译错误）
        // 直接对基本类型数组使用Comparator是错误的
        //Arrays.sort(primitiveArr, Collections.reverseOrder());

        // 因为基本数据类型只能直接排序，不可以自定义排序规则（可以使用stream流操作）
        // 要想改变默认的排列顺序，不能使用基本类型（int,double, char）而要使用它们对应的包装类
        // 解决方案1：转换为包装类数组
        Integer[] wrapperArr = Arrays.stream(primitiveArr).boxed().toArray(Integer[]::new);
        Arrays.sort(wrapperArr, Collections.reverseOrder());
        System.out.println("包装类降序: " + Arrays.toString(wrapperArr));

        // 解决方案2：使用Stream反向排序
        int[] reversedArr = Arrays.stream(primitiveArr)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println("Stream降序: " + Arrays.toString(reversedArr));


        // int是基本数据类型，而int[] 不是基本数据类型
        // 所以在使用map集合等等泛型中需要自定义排序的情况，int[]直接写就好，不能写Integer[].


        // 如何使用stream转化，将 int[] 转成 Integer[]
        int[] nums = {1, 2, 3, 4, 5, 6};
        //将int数组转换为数值流
        IntStream stream = Arrays.stream(nums);
        //流中的元素全部装箱，转换为Integer流
        Stream<Integer> integerStream = stream.boxed();
        //将流转换为数组
        Integer[] integers = integerStream.toArray(Integer[]::new);

        // 上面是分解步骤，实际应用中一行代码即可解决
        Integer[] newArr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        // 讲一下Integer[]::new
        // Object[] toArray()：此方法会返回一个Object类型的数组，不过这通常不是我们想要的，因为我们期望得到的是具体类型的数组，像Integer[]这种。
        // A[] toArray(IntFunction<A[]> generator)：该方法能够返回指定类型的数组。
        // 这里的IntFunction<A[]>是一个函数式接口，其作用是依据传入的数组大小生成对应类型的数组。
        // Integer[]::new属于方法引用，相当于一个工厂方法，它代表的是Integer数组的构造函数。
        // 当调用toArray方法时，Java 会把流中元素的数量当作参数传递给Integer[]::new，进而创建一个大小合适的Integer数组。
    }
}
