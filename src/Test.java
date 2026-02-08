import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


public class Test {
    private static final Object lock = new Object();
    private static volatile boolean hasData = false;

    public static void main(String[] args) throws InterruptedException {
        // 消费者
        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                while (!hasData) {
                    try {
                        System.out.println("消费者: 等待数据...");
                        lock.wait(); // 释放锁，等待
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("消费者: 处理数据!");
                hasData = false;
                lock.notify(); // 通知生产者
            }
        }, "Consumer");

        // 生产者
        Thread producer = new Thread(() -> {
            synchronized (lock) {
                while (hasData) {
                    try {
                        System.out.println("生产者: 等待消费...");
                        lock.wait(); // ⏳ 释放锁，等待
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("生产者: 生成数据!");
                hasData = true;
                lock.notify(); // 通知消费者
            }
        }, "Producer");

        consumer.start();
        Thread.sleep(100); // 让消费者先启动
        producer.start();
    }
}
