package tw.com.wd.cache.localcache.obj;

public class StringCacheItem extends CacheItem<String> {
    StringCacheItem(String s) {
        super(s);
    }

    public String getValue() {
        return this.value;
    }
}
