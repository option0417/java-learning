package tw.com.wd.cache.localcache.obj;

public interface CacheKey {
    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
}