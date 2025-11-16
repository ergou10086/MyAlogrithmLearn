package Java多线程.leetcode1116_打印零与奇偶数;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * zeroSem：初始为1，让 zero 线程先执行
 * evenSem 和 oddSem：初始为0，等待被唤醒
 * 先获取 zeroSem，打印 0，然后根据当前数字的奇偶性释放 oddSem 或 evenSem
 * odd()：等待 oddSem，打印奇数，然后释放 zeroSem 开始下一轮
 * even同理
 */
class ZeroEvenOdd {
    private int n;
    private Semaphore zeroSem, evenSem, oddSem;
    private int currentNum = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.zeroSem = new Semaphore(1); // zero 先开始
        this.evenSem = new Semaphore(0);
        this.oddSem = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < n; i++){
            zeroSem.acquire(); // 获取 zero 信号量

            printNumber.accept(0);

            // 根据当前要打印的数字（currentNum）判断奇偶，释放对应信号量
            if (currentNum % 2 == 1) {
                oddSem.release(); // 奇数：唤醒odd线程
            } else {
                evenSem.release(); // 偶数：唤醒even线程
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        // 只处理偶数
        for(int i = 2; i <= n; i += 2){
            evenSem.acquire();   // 获取 even 信号量

            printNumber.accept(i); // 打印偶数

            currentNum++; // 数字递增（准备下一轮）
            zeroSem.release(); // 释放 zero 信号量，开始下一轮
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        // 只处理奇数
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();    // 获取 odd 信号量

            printNumber.accept(i);   // 打印奇数

            currentNum++; // 数字递增（准备下一轮）
            zeroSem.release();  // 释放 zero 信号量，开始下一轮
        }
    }
}
