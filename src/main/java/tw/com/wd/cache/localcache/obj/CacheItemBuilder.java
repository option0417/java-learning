package tw.com.wd.cache.localcache.obj;

import java.util.Collection;

public class CacheItemBuilder {
    public static StringCacheItem buildStringCacheItem(String value) {
        StringCacheItem stringCacheItem = new StringCacheItem(value);
        return stringCacheItem;
    }

    public static IntegerCacheItem buildIntegerCacheItem(int value) {
        IntegerCacheItem intCacheitem = new IntegerCacheItem(value);
        return intCacheitem;
    }

    public static CollectionCacheItem buildCollectionCacheItem(Collection value) {
        CollectionCacheItem collectionCacheItem = new CollectionCacheItem(value);
        return collectionCacheItem;
    }

    public static ObjectCacheItem buildObjectCacheItem(Object value) {
        ObjectCacheItem objCacheItem = new ObjectCacheItem(value);
        return objCacheItem;
    }
}
