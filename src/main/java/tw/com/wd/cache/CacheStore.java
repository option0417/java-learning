package tw.com.wd.cache;

import tw.com.wd.cache.listener.CacheListener;

public interface CacheStore<KEY, VALUE> {
    /**
     * Put VALUE to cache store with KEY
     * @param key
     * @param value
     * @return
     */
    public VALUE put(KEY key, VALUE value);

    /**
     * Get VALUE from cache store by KEY
     * @param key
     * @return
     */
    public VALUE get(KEY key);

    /**
     * Remove VALUE int the cache store by KEY
     * @param key
     * @return
     */
    public VALUE remove(KEY key);

    /**
     * Check the value associate with the key exist or not.
     * @param key
     * @return
     */
    public boolean isExist(KEY key);

    /**
     * Get current size of cache store
     * @return
     */
    public int getCacheSize();

    /**
     * Get capacity of cache store
     * @return
     */
    public int getCacheCapacity();
    
    /**
     * Set cache-listener to cache store
     * @param cacheListener
     */
    public void setCacheListener(CacheListener cacheListener);

    /**
     * Remove cache-listener
     */
    public void removeCacheListener();
    
    /**
     * Check cache-listen exist or not.
     * @return
     */
    public boolean isCacheListenerExist();
}
