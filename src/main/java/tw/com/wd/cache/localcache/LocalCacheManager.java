package tw.com.wd.cache.localcache;

import tw.com.wd.cache.localcache.obj.CacheItem;

public class LocalCacheManager {
    private LocalCache<Object, Object> localCache;


    private LocalCacheManager() {
        super();
        localCache = new LocalCache<Object, Object>(CacheConfiguration.buildCacheConfiguration());
    }

    private static final class InstanceHolder {
        private static LocalCacheManager instance = new LocalCacheManager();
    }

    static final LocalCacheManager getInstance() {
        return InstanceHolder.instance;
    }

    public <KEY> void putCacheItem(KEY key, CacheItem<?> cacheItem) {
        try {
            //localCache.put(key, cacheItem);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    public <KEY> CacheItem<?> getCacheItem(KEY key) {
        try {
            return localCache.get(key);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    public <KEY> CacheItem<?> deleteCacheItem(KEY key) {
        try {
            return localCache.remove(key);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}
