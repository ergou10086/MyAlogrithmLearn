import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Test {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // 固定频率：初始延迟0秒，每隔3秒执行一次（T=3）
        executor.scheduleAtFixedRate(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 任务开始执行，时间：" + System.currentTimeMillis() / 1000);

            // 模拟任务执行时间：先设为2秒（正常），再改为5秒（超时）
            try {
                TimeUnit.SECONDS.sleep(5); // 改为5秒看超时效果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName + " 任务执行结束，时间：" + System.currentTimeMillis() / 1000);
        }, 0, 3, TimeUnit.SECONDS);
    }
}