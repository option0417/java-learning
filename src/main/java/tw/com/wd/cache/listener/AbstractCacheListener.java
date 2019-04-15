package tw.com.wd.cache.listener;


public abstract class AbstractCacheListener implements CacheListener {

	@Override
	public void preStage() {
		// Just return as default implementation.
		return;
	}

	@Override
	public void postStage() {
		// Just return as default implementation.
		return;
	}

}
