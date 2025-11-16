package Java多线程.leetcode1117_H2O生成;

// 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
// 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。

import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;

class H2O {

    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(0);

    public H2O() {

    }

    // 每次 H 线程执行前，必须从 h 信号量获取 1 个许可（初始有 2 个，所以前 2 个 H 线程能直接执行，第 3 个 H 线程会阻塞）。
    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // 申请H许可（计数器-1），最多2个H线程能进入
        h.acquire();
        releaseHydrogen.run();
        // 输出 "H" 后，会给 o 信号量释放 1 个许可（让 O 线程知道有 1 个 H 已就绪）。
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2); // 申请2个O许可（必须等2个H线程释放许可后才能执行）
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2); // 释放2个H许可（允许新的2个H线程执行）
    }
}
