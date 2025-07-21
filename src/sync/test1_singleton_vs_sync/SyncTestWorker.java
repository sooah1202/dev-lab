package sync.test1_singleton_vs_sync;

public class SyncTestWorker implements Runnable
{
	private final String name;
	private final SyncDataStore store;

	public SyncTestWorker(String name, SyncDataStore store)
	{
		this.name = name;
		this.store = store;
	}

	@Override
	public void run()
	{
		for(int i = 0; i < 1000; i++)
		{
			store.add(name + "-A-" + i);
		}
		for(int i = 0; i < 1000; i++)
		{
			store.add(name + "-A-" + i);
		}
	}
}
