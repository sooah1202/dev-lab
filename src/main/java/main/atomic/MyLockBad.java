package main.atomic;

public class MyLockBad {
    private boolean locked = false;

    public boolean tryLock() {
        if(!locked) {
            for (int i = 0; i < 100_000; i++) {}
            locked = true;
            return true;
        }
        return false;
    }
}
