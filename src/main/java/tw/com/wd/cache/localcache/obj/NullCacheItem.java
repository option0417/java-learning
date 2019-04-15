package tw.com.wd.cache.localcache.obj;

public class NullCacheItem extends CacheItem<Void>{
	
	
	NullCacheItem(Void voidObj) {
		super(voidObj);
	}

	@Override
	public Void getValue() {
		return null;
	}

}
