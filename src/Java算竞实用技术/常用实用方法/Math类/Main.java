package Java算竞实用技术.常用实用方法.Math类;

public class Main {
    public static void main(String[] args) {
        // Math类位于java.lang 包中，不用导能直接用

        // 常量
        double e = Math.E;
        double pi = Math.PI;
        System.out.println(e);
        System.out.println(pi);

        // 基本数学运算
        // Math.abs(double a)：返回参数的绝对值
        // Math.max(double a, double b)：返回两个参数中较大的那个。
        // Math.min(double a, double b)：返回两个参数中较小的那个。
        // Math.pow(double a, double b)：返回 a 的 b 次方。
        // Math.sqrt(double a)：返回参数的平方根。
        // Math.cbrt(double a)：返回参数的立方根。
        // Math.round(double a)：将参数四舍五入为最接近的整数

        // 三角函数
        // Math.sin(double a)：返回参数的正弦值。
        // Math.cos(double a)：返回参数的余弦值。
        // Math.tan(double a)：返回参数的正切值。
        // Math.asin(double a)：返回参数的反正弦值。
        // Math.acos(double a)：返回参数的反余弦值。
        // Math.atan(double a)：返回参数的反正切值。
        // Math.atan2(double y, double x)：返回 y/x 的反正切值，区间为 (-π, π]。

        // 取整函数
        //  Math.ceil(double a)：返回大于等于参数的最小整数。
        //  Math.floor(double a)：返回小于等于参数的最大整数。
        //  Math.round(double a)：将参数四舍五入为最接近的整数。

        // 指对数
        //  Math.exp(double a)：返回参数的指数值。
        //  Math.log(double a)：返回参数的自然对数。
        //  Math.log10(double a)：返回参数的以 10 为底的对数。
    }
}
