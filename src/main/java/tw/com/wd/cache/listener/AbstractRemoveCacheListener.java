package tw.com.wd.cache.listener;

import tw.com.wd.cache.CacheOperation;

public class AbstractRemoveCacheListener extends AbstractCacheListener implements CacheListener {

	@Override
	public CacheOperation fetchCacheOperation() {
		return CacheOperation.Remove;
	}

}
