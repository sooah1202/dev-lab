package sync.test1_singleton_vs_sync;

import java.util.ArrayList;
import java.util.List;

public class SingletonSafe implements SyncDataStore {

	private static SingletonSafe instance = null;

	private List<String> dataList = new ArrayList<>();

	private SingletonSafe() {

	}

	public static SingletonSafe getInstance() {
		if(instance == null)
		{
			synchronized(SingletonSafe.class)
			{
				if(instance == null)
					instance = new SingletonSafe();
			}
		}
		return instance;
	}

	@Override
	public synchronized void add(String value) {
		dataList.add(value);
	}

	@Override
	public synchronized int size() {
		return dataList.size();
	}

	@Override
	public synchronized List<String> getAll() {
		return new ArrayList<>(dataList);
	}

}


