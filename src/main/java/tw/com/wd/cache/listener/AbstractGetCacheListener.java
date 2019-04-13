package tw.com.wd.cache.listener;

import tw.com.wd.cache.CacheOperation;

public abstract class AbstractGetCacheListener extends AbstractCacheListener implements CacheListener {

	@Override
	public CacheOperation fetchCacheOperation() {
		return CacheOperation.Get;
	}

}
