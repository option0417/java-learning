package tw.com.wd.cache.localcache.obj;


/**
 * Data Type of Value supported :
 * 1. String
 * 2. Integer
 * 3. Collection
 */
public abstract class CacheItem<VALUE> {
    protected VALUE value;
    protected int ageCount;
    protected long ttl;


    CacheItem(VALUE value) {
        this.value  = value;
        this.ttl    = 0l;
    }

    public int getAgeCount() {
        return this.ageCount;
    }

    public CacheItem<VALUE> setAgeCount(int ageCount) {
        this.ageCount = ageCount;
        return this;
    }

    public long getTTL() {
        return ttl;
    }

    public void setTTL(long ttl) {
        this.ttl = ttl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (this.getClass().isInstance(obj)) {
                CacheItem tmpCacheItem = (CacheItem)obj;

                if (this.value.equals(tmpCacheItem.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    public abstract VALUE getValue();
}
