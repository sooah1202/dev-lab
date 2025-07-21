package sync.test2_syncListTest_vs_COWListTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncListTest {

	public static void main(String[] args) {
		List<String> list = Collections.synchronizedList(new ArrayList<>());
		list.add("A");
		list.add("B");
		list.add("C");

		new Thread(() -> {
			try {
				Thread.sleep(10);
				list.add("D");
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		for(String s : list) {
			System.out.println(s);
			try { Thread.sleep(50); } catch(InterruptedException e) {}
		}
	}
}
