package sync.test2_syncListTest_vs_COWListTest;

public class CopyOnWriteArrayList {
	public static void main(String[] args) {
		java.util.concurrent.CopyOnWriteArrayList<String> list = new java.util.concurrent.CopyOnWriteArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");

		new Thread(() -> {
			try {
				Thread.sleep(10);
				list.add("D");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		for(String s : list) {
			System.out.println(s);
			try { Thread.sleep(50); } catch(InterruptedException e) {}
		}
	}
}
