package sync.test1_singleton_vs_sync;

public class SyncExperimentRunner
{

	public static void main(String[] args) throws InterruptedException {

		SyncDataStore store = SingletonUnsafe.getInstance();
//		SyncDataStore store = SingletonSafe.getInstance();

		// 워커 실행 스레드 8개 준비 (A 4개, B 4개)
		Thread t1 = new Thread(new SyncTestWorker("Thread-A1", store));
		Thread t2 = new Thread(new SyncTestWorker("Thread-A2", store));
		Thread t3 = new Thread(new SyncTestWorker("Thread-B1", store));
		Thread t4 = new Thread(new SyncTestWorker("Thread-B2", store));
		Thread t5 = new Thread(new SyncTestWorker("Thread-A3", store));
		Thread t6 = new Thread(new SyncTestWorker("Thread-A4", store));
		Thread t7 = new Thread(new SyncTestWorker("Thread-B3", store));
		Thread t8 = new Thread(new SyncTestWorker("Thread-B4", store));

		// 실행
		t1.start(); t2.start(); t3.start(); t4.start();
		t5.start(); t6.start(); t7.start(); t8.start();

		// 모든 스레드 종료 대기
		t1.join(); t2.join(); t3.join(); t4.join();
		t5.join(); t6.join(); t7.join(); t8.join();

		// 결과 출력
		System.out.println("최종 리스트 크기: " + store.size());

		System.out.println("작업 완료");
	}
}