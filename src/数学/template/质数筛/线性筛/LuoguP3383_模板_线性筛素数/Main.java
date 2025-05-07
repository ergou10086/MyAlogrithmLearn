package 数学.template.质数筛.线性筛.LuoguP3383_模板_线性筛素数;

// 线性筛，欧拉筛，用质数(枚举质数乘枚举的i)筛选质数
// 从小到大枚举每个数
// 如果当前数没有划掉，必定是质数，记录该质数
// 枚举已记录的质数（如果是合数已越界则中断）
// 合数没有越界，划掉
// i % p == 0，保证合数只被最小质因子划掉
    // 如果i是质数，枚举到自己的时候就中断了
    // 如果i是合数，枚举到自身的最小质数中断

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private final int N = 10000010;
    private boolean[] vis = new boolean[N];  // 划掉合数
    private int[] primes = new int[N];  // 记录质数
    private int cnt;   // 质数个数

    private void get_prim(int n){
        for(int i = 2; i <= n; i++){
            if(!vis[i]) primes[++cnt] = i;     // 不是合数，是质数，记录
            // 从小到大枚举已经记录的质数
            for(int j = 1; (long)i * primes[j] <= n; j++){
                vis[i*primes[j]] = true;   // 没越界，划掉（数*质数 = 质数）
                if(i % primes[j] == 0) break;   // 保证只被最小质因子划掉，保证只划掉一次
            }
        }
    }

    public Solutions(){

    }
}