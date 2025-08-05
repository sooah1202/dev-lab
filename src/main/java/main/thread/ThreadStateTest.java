package main.thread;

public class ThreadStateTest {
	private static Thread myThread;
	private static Thread stateThread;

	public static void main(String[] args) {
		stateThread = new Thread(() -> {
			while(true) {
				Thread.State state = myThread.getState();
				System.out.printf("[%s] - Threading.... - [%s]\n", myThread.getName(), state);

				if(state == Thread.State.NEW) {
					myThread.start();
				}

				if(state == Thread.State.WAITING) {
					myThread.interrupt();
				}

				if(state == Thread.State.TERMINATED) {
					System.out.printf("[%s] - Threading.... - [%s]\n", myThread.getName(), state);
					break;
				}

				try {
					Thread.sleep(500);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		myThread = new MyThread("myThread", stateThread);
		stateThread.start();
	}
}

class MyThread extends Thread {
	private Thread target;

	public MyThread(String name, Thread target) {
		super(name);
		this.target = target;
	}

	@Override
	public void run() {
		for(int i = 0; i <= 2_000_000_000; i++);
		for(int i = 0; i <= 2_000_000_000; i++);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			target.join();
		}
		catch(InterruptedException e) {
			for(int x = 0; x <= 2_000_000_000; x++);
			for(int x = 0; x <= 2_000_000_000; x++);
		}
	}
}
