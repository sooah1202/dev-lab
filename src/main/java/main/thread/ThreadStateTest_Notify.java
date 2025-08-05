package main.thread;

public class ThreadStateTest_Notify {

	private static final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new MyThread(), "thread1");
		Thread t2 = new Thread(new MyThread(), "thread2");
		Thread t3 = new Thread(new MyThread(), "thread3");

		t1.start();
		t2.start();
		t3.start();

		Thread.sleep(1000);

		synchronized(lock) {
			System.out.println("[Main notify()]");
			lock.notify();
		}

		Thread.sleep(1000);

		synchronized(lock) {
			System.out.println("[Main notifyAll()]");
			lock.notifyAll();
		}
	}

	static class MyThread implements Runnable {
		@Override
		public void run()
		{
			synchronized(lock) {
				try{
					System.out.println("[" + Thread.currentThread().getName() + "] waiting...");
					lock.wait();
					System.out.println("[" + Thread.currentThread().getName() + "] notified and running!");
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


