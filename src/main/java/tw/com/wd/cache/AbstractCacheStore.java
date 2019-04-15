package tw.com.wd.cache;

import tw.com.wd.cache.listener.CacheListener;
import tw.com.wd.cache.localcache.CacheConfiguration;
import tw.com.wd.cache.localcache.obj.CacheStrategyType;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCacheStore<KEY, VALUE> implements CacheStore<KEY, VALUE> {
	private ConcurrentHashMap<KEY, VALUE> valueMap;
	private int cacheSize;
	private int cacheCapacity;
    private CacheStrategyType cacheStrategy;
	private CacheListener cacheListener;
	
	
	public AbstractCacheStore(int cacheCapacity) {
		super();
		valueMap 			= new ConcurrentHashMap<KEY, VALUE>(cacheCapacity);
		this.cacheSize 		= 0;
		this.cacheCapacity 	= cacheCapacity;
		this.cacheListener 	= null;
	}

	public AbstractCacheStore(CacheConfiguration cacheConfiguration) {
        super();
        this.cacheCapacity  	= cacheConfiguration.getCacheCapcity();
        this.cacheStrategy  	= cacheConfiguration.getCacheStrategy();
        this.cacheListener 		= null;
	}

	@Override
    public VALUE put(KEY key, VALUE value) {
		doCacheListenerPreStage(CacheOperation.Put);
		doPutProcess(key, value);
		doCacheListenerPostStage(CacheOperation.Put);
		return value;
	}


	@Override
    public VALUE get(KEY key) {
		doCacheListenerPreStage(CacheOperation.Get);
		VALUE value = doGetProcess(key);
		doCacheListenerPostStage(CacheOperation.Get);
		return value;
	}


	@Override
    public VALUE remove(KEY key) {
		doCacheListenerPreStage(CacheOperation.Remove);
		VALUE value = doRemoveProcess(key);
		doCacheListenerPostStage(CacheOperation.Remove);
		return value;
	}


	@Override
    public boolean isExist(KEY key) {
		return this.valueMap.containsKey(key);
	}


	@Override
    public int getCacheSize() {
		return this.cacheSize;
	}

	@Override
    public int getCacheCapacity() {
		return this.cacheCapacity;
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
	
	private void doCacheListenerPreStage(CacheOperation cacheOperation) {        
        if (cacheListener != null) {
        	if (cacheOperation == cacheListener.fetchCacheOperation()) {
        		cacheListener.preStage();
        	}
        }
	}
	
	private void doCacheListenerPostStage(CacheOperation cacheOperation) {        
        if (cacheListener != null) {
        	if (cacheOperation == cacheListener.fetchCacheOperation()) {
        		cacheListener.postStage();
        	}
        }
	}

	protected abstract VALUE doPutProcess(KEY key, VALUE value);
	protected abstract VALUE doGetProcess(KEY key);
	protected abstract VALUE doRemoveProcess(KEY key);
}
