package tw.com.wd.cache.localcache;

import tw.com.wd.cache.AbstractCacheStore;
import tw.com.wd.cache.listener.CacheListener;
import tw.com.wd.cache.localcache.obj.CacheItem;
import tw.com.wd.cache.localcache.obj.CacheStrategyType;

import java.util.Iterator;
import java.util.Map;

/**
 * Data Type of KEY supported :
 * 1. String
 * 2. Integer
 * 3. Object
 */
public class LocalCache<KEY, DATA> extends AbstractCacheStore<KEY, CacheItem<DATA>> {
    public LocalCache(CacheConfiguration cacheConfiguration) {
		super(cacheConfiguration);
	}

	private Map<KEY, CacheItem<DATA>> cacheItemMap;
    private int cacheCapacity;
    private CacheStrategyType cacheStrategy;
    private int ageCount;
    private CacheListener cacheListener;

    public CacheStrategyType getCacheStrategy() {
        return cacheStrategy;
    }

    public int getCacheCapacity() {
        return cacheCapacity;
    }

    public int getCacheSize() {
        return cacheItemMap.size();
    }
    
    public boolean isExist(KEY cacheKey) {
        return cacheItemMap.containsKey(cacheKey);
    }

	@Override
	public void setCacheListener(CacheListener cacheListener) {
		this.cacheListener = cacheListener;
	}

	@Override
	public void removeCacheListener() {
		this.cacheListener = null;
	}

	@Override
	public boolean isCacheListenerExist() {
		return this.cacheListener != null;
	}

    private boolean isCacheFull() {
        return cacheItemMap.size() == cacheCapacity;
    }

    private void replaceCacheItemByLRU() {
        int minAgeCount = Integer.MAX_VALUE;
        KEY replaceKEY  = null;

        Iterator<Map.Entry<KEY, CacheItem<DATA>>> iter = cacheItemMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<KEY, CacheItem<DATA>> entry = iter.next();
            CacheItem cacheItem             = entry.getValue();

            if (cacheItem.getAgeCount() <= minAgeCount) {
                replaceKEY  = entry.getKey();
                minAgeCount = cacheItem.getAgeCount();
            }
        }

        if (replaceKEY != null) {
            cacheItemMap.remove(replaceKEY);
        }
    }

	@Override
	protected CacheItem doPutProcess(KEY key, CacheItem<DATA> value) {
        if (isCacheFull()) {
            replaceCacheItemByLRU();
        }
        
        if (value == null) {
        	return null;
        }
        
        
        //cacheItem.setAgeCount(++ageCount);
        cacheItemMap.put(key, value);
        return value;
	}

	@Override
	protected CacheItem doGetProcess(KEY key) {
        CacheItem cacheItem = cacheItemMap.get(key);
        if (cacheItem != null) {
            cacheItem.setAgeCount(++ageCount);
        }
        return cacheItem;
	}

	@Override
	protected CacheItem doRemoveProcess(KEY key) {
		return null;
	}
}
