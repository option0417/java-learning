package tw.com.wd.cache.localcache.obj;

public class ObjectCacheItem extends CacheItem<Object> {
    ObjectCacheItem(Object objValue) {
        super(objValue);
    }

    public Object getValue() {
        return this.value;
    }
}
