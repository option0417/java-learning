package tw.com.wd.cache.localcache;

import tw.com.wd.cache.localcache.obj.*;

import java.util.Collection;

public class CacheAdapter {
    private static final LocalCacheManager LOCAL_CACHE_MANAGER = LocalCacheManager.getInstance();


    private CacheAdapter() {
        super();
    }

    private static final class InstanceHolder {
        private static final CacheAdapter instance = new CacheAdapter();
    }

    public static CacheAdapter getInstance() {
        return InstanceHolder.instance;
    }

    public <KEY> void putCacheItem(KEY key, String value) {
        StringCacheItem cacheItem = CacheItemBuilder.buildStringCacheItem(value);
        LOCAL_CACHE_MANAGER.putCacheItem(key, cacheItem);
    }

    public <KEY> void putCacheItem(KEY key, int value) {
        IntegerCacheItem cacheItem = CacheItemBuilder.buildIntegerCacheItem(value);
        LOCAL_CACHE_MANAGER.putCacheItem(key, cacheItem);
    }

    public <KEY> void putCacheItem(KEY key, Collection value) {
        CollectionCacheItem cacheItem = CacheItemBuilder.buildCollectionCacheItem(value);
        LOCAL_CACHE_MANAGER.putCacheItem(key, cacheItem);
    }

    public <KEY> void putCacheItem(KEY key, Object value) {
        ObjectCacheItem cacheItem = CacheItemBuilder.buildObjectCacheItem(value);
        LOCAL_CACHE_MANAGER.putCacheItem(key, cacheItem);
    }

    public <KEY> CacheItem<?> getCacheItem(KEY key) {
        return LOCAL_CACHE_MANAGER.getCacheItem(key);
    }

    public <KEY> CacheItem<?> deleteCacheItem(KEY key) {
        return LOCAL_CACHE_MANAGER.deleteCacheItem(key);
    }
}
