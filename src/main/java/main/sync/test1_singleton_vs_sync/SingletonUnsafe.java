package main.sync.test1_singleton_vs_sync;

import java.util.ArrayList;
import java.util.List;

public class SingletonUnsafe implements SyncDataStore {

	private static SingletonUnsafe instance = null;
	private final List<String> dataList = new ArrayList<>();

	private SingletonUnsafe () {

	}

	public static SingletonUnsafe getInstance()
	{
		if(instance == null)
		{
			synchronized(SingletonUnsafe.class)
			{
				if(instance == null)
					instance = new SingletonUnsafe();
			}
		}
		return instance;
	}

	@Override
	public void add(String value) {
		dataList.add(value); // 동기화 안 됨 → 경쟁 조건 발생 가능
	}

	@Override
	public int size() {
		return dataList.size();
	}

	@Override
	public List<String> getAll() {
		return dataList;
	}
}
