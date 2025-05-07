package Java算竞实用技术.快读快写.快写.简单快写;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // StreamTokenizer类
        // StreamTokenizer类接收输入流并将其解析为“令牌”，允许一次读取一个令牌。 解析过程由表和多个可以设置为各种状态的标志来控制。
        // 流标记器可以识别标识符，数字，引用的字符串和各种注释样式。
        // 从输入流读取的每个字节被视为’\u0000’至’\u00FF’范围内的’\u0000’ ‘\u00FF’
        // 使用StreamTokenizer类要记得抛出I/O异常

        // 使用注意事项
        // 每一次读入之前都要用nextToken（）方法获取下一个数据
        // 读取数据的方法，sval方法读取字符串类型（以空格或者换行分隔），nval方法读取数字类型数据。
        // 读取字符串类型的数据时，一次只能读一个字符串，读取数字类型的数据时，默认为double类型
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        in.nextToken();   // 第一行必须是这个
        int n = (int) in.nval;    // 注意类型转换
        in.nextToken();
        int k = (int) in.nval;
        long[] arr = new long[1000000];
        long[] sum = new long[arr.length];
        for (int j = 0; j < n - 1; j++) {
            // 注意循环读入时候的in.nextToken();，每次读之前都要写这个
            in.nextToken();
            arr[j] = (long) in.nval;
        }

        // 如何读取字符串
        // 当我们只读字符串的时候我们就不需要加StreamTokenizer类
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        // 当我们使用这种方法的时候就可以一次性读取一大串带空格的字符串了
        String x = re.readLine();
        System.out.println(x);
    }
}
