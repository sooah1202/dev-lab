package main.thread;

public class InterruptTest
{
	public static void main(String[] args) {

		Thread targetThread = new targetThread();
		targetThread.start();

		Thread targetThread2 = new targetThread2();
		targetThread2.start();

		try {
			Thread.sleep(1000);
			// 1초 뒤 targetThread interrupt 합니다.
			targetThread.interrupt();
			targetThread2.interrupt();
		} catch (InterruptedException e) {
			System.out.println("finished!");
		}
	}

	static class targetThread extends Thread {
		@Override
		public void run() {
			// Thread interrupt 될 때까지 반복합니다.
			while (!Thread.currentThread().isInterrupted()) {
				try {
					System.out.println("targetThread--sleeping...");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("targetThread--It is interrupted!");
					// Thread.currentThread().interrupt();
				}
			}
		}
	}

	static class targetThread2 extends Thread {
		@Override
		public void run() {
			// Thread interrupt 될 때까지 반복합니다.
			while (!Thread.currentThread().isInterrupted()) {
				try {
					System.out.println("targetThread2--sleeping...");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("targetThread2--It is interrupted!");
					 Thread.currentThread().interrupt();
				}
			}
		}
	}
}
