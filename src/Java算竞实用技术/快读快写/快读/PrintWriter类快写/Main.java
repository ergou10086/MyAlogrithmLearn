package Java算竞实用技术.快读快写.快读.PrintWriter类快写;

import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // 创建快速输出对象（自动刷新缓冲区）
        // 第二个参数 true 表示自动刷新缓冲区，每次调用 println()/printf() 后自动 flush
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true);

        // 1. 不换行输出
        pw.print("Hello"); // 需要参数
        pw.print(" World");

        // 2. 换行输出
        pw.println(); // 单独调用 println() 会输出换行
        pw.println("Java Fast Output");

        // 3. 格式化输出
        int num = 2024;
        double pi = 3.1415;
        pw.printf("Number: %d, PI: %.2f%n", num, pi); // %n 表示换行

        // 不需要手动 flush()，因为构造时设置了 autoFlush=true


        // 特殊字符处理测试
        // 输出 CSV 格式
        pw.println("Name,Age,Score"); // 自动处理逗号
        pw.println("Alice,25,95.5");

        // 输出 Unicode 字符
        pw.println("中文测试");
        pw.println("😊 Emoji Test");
    }
}