package main.sync.test1_singleton_vs_sync;

import java.util.List;

public interface SyncDataStore {
	void add(String value);
	int size();
	List<String> getAll();
}
