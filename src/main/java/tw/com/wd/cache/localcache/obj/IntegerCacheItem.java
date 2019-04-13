package tw.com.wd.cache.localcache.obj;

public class IntegerCacheItem extends CacheItem<Integer> {
    IntegerCacheItem(Integer integerValue) {
        super(integerValue);
    }

    IntegerCacheItem(int intValue) {
        super(Integer.valueOf(intValue));
    }

    public Integer getValue() {
        return this.value;
    }

    public int getIntValue() {
        return this.value.intValue();
    }
}
