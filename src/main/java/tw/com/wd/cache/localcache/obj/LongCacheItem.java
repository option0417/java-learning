package tw.com.wd.cache.localcache.obj;

public class LongCacheItem extends CacheItem<Long> {
    LongCacheItem(Long longValue) {
        super(longValue);
    }

    LongCacheItem(long longValue) {
        super(Long.valueOf(longValue));
    }

    public Long getValue() {
        return this.value;
    }

    public Long getLongValue() {
        return this.value.longValue();
    }
}
