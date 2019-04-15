package tw.com.wd.cache.listener;

import tw.com.wd.cache.CacheOperation;

public abstract class AbstractPutCacheListener extends AbstractCacheListener implements CacheListener {
	@Override
	public CacheOperation fetchCacheOperation() {
		return CacheOperation.Put;
	}
}
