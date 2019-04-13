package tw.com.wd.cache.listener;

import tw.com.wd.cache.CacheOperation;

public interface CacheListener {
	/**
	 * Pre-stage which occur before cache operation.
	 */
	public void preStage();

	/**
	 * Post-stage which occur before cache operation.
	 */
	public void postStage();

	/**
	 * Fetch operation type of CacheListener
	 * @return
	 */
	public CacheOperation fetchCacheOperation();
}
