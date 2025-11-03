package main.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TryLockRaceDemo {
    static final int THREADS = 256;

    public static void main(String[] args) throws Exception {
        System.out.println("Bad  = " + runBad());
        System.out.println("Good = " + runGood());
    }

    static int runBad() throws Exception {
        MyLockBad lock = new MyLockBad();
        AtomicInteger success = new AtomicInteger();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            Thread t = new Thread(() -> {
                if (lock.tryLock())
                    success.incrementAndGet();
            });
            ts.add(t);
            t.start();
        }

        for (Thread t : ts)
            t.join();
        return success.get(); // 기대: 1보다 클 수 있음(동시 성공)
    }

    static int runGood() throws Exception {
        MyLockGood lock = new MyLockGood();
        AtomicInteger success = new AtomicInteger();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            Thread t = new Thread(() -> {
                if (lock.tryLock())
                    success.incrementAndGet();
            });
            ts.add(t);
            t.start();
        }
        for (Thread t : ts)
            t.join();
        return success.get(); // 기대: 항상 1
    }
}
