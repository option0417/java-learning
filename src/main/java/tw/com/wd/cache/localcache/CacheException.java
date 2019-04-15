package tw.com.wd.cache.localcache;

public class CacheException extends RuntimeException {
    public CacheException(String errMsg) {
        super(errMsg);
    }

    public CacheException(Throwable t) {
        super(t);
    }

    public CacheException(String errMsg, Throwable t) {
        super(errMsg, t);
    }
}
