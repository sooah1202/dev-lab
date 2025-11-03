package main.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyLockGood {
    private final AtomicBoolean locked = new AtomicBoolean(false);

    public boolean tryLock() {
        for(int i = 0; i < 100_000; i++) {}
        return locked.compareAndSet(false, true);
    }
}
