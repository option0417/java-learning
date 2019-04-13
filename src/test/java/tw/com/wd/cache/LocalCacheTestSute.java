package tw.com.wd.cache;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tw.com.wd.cache.obj.CacheItemTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CacheItemTest.class,
        CacheConfigurationTest.class,
        CacheItemBuilderTest.class,
        LocalCacheTest.class,
        LocalCacheLRUTest.class
})
public class LocalCacheTestSute {
}
