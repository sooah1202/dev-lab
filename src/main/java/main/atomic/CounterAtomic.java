package main.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CounterAtomic {
    static AtomicLong counter = new AtomicLong(0);
    static long counter2 = 0;

    public static void main(String[] args) throws Exception{
        int threads = 10, perThread = 100_000;
        List<Thread> list = new ArrayList<>();
        for (int t = 0; t < threads; t++) {
            list.add(new Thread(() -> {
                for (int i = 0; i < perThread; i++) {
                    counter.incrementAndGet(); // 원자적 증가
                    counter2++;
                }
            }));
        }

        list.forEach(Thread::start);
        for(Thread th : list)
            th.join();
        System.out.println("atomic = " + counter.get());
        System.out.println("non-atomic = " + counter2);
    }
}
