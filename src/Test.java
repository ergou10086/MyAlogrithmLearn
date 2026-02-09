import java.util.concurrent.atomic.AtomicReference;

// 不可变状态类
class State {
    public final String name;
    public final long timestamp;

    public State(String name) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "State{name='" + name + "', ts=" + timestamp + "}";
    }
}

public class Test {

    // 使用 AtomicReference 持有当前状态
    private static final AtomicReference<State> currentState =
            new AtomicReference<>(new State("INIT"));

    // 安全地尝试从 expectedName 切换到 newName
    public static boolean tryTransition(String expectedName, String newName) {
        State current = currentState.get();
        if (!current.name.equals(expectedName)) {
            return false; // 当前状态不符合预期，不允许切换
        }
        State newState = new State(newName);
        return currentState.compareAndSet(current, newState);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("初始状态: " + currentState.get());

        // 启动多个线程并发尝试状态切换
        Thread t1 = new Thread(() -> {
            if (tryTransition("INIT", "RUNNING")) {
                System.out.println("成功从 INIT → RUNNING");
            } else {
                System.out.println("无法从 INIT → RUNNING（可能已被其他线程修改）");
            }
        });

        Thread t2 = new Thread(() -> {
            if (tryTransition("INIT", "RUNNING")) {
                System.out.println("成功从 INIT → RUNNING");
            } else {
                System.out.println("无法从 INIT → RUNNING（可能已被其他线程修改）");
            }
        });

        Thread t3 = new Thread(() -> {
            // 尝试非法跳转：直接从 INIT 到 STOPPED（应失败）
            if (tryTransition("INIT", "STOPPED")) {
                System.out.println("成功从 INIT → STOPPED（这不应该发生！）");
            } else {
                System.out.println("止了非法跳转：INIT → STOPPED");
            }
        });

        Thread t4 = new Thread(() -> {
            // 等待一会儿，再尝试 RUNNING → STOPPED
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            if (tryTransition("RUNNING", "STOPPED")) {
                System.out.println("成功从 RUNNING → STOPPED");
            } else {
                System.out.println("无法从 RUNNING → STOPPED（可能还没到 RUNNING）");
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // 等待所有线程完成
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("最终状态: " + currentState.get());
    }
}