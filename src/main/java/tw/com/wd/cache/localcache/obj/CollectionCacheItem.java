package tw.com.wd.cache.localcache.obj;

import java.util.Collection;

public class CollectionCacheItem extends CacheItem<Collection> {
    CollectionCacheItem(Collection collectionValue) {
        super(collectionValue);
    }

    public Collection getValue() {
        return this.value;
    }
}
