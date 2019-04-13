package tw.com.wd.cache;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tw.com.wd.cache.obj.CacheItemTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  CacheConfigurationTest.class,
  CacheItemBuilderTest.class,
  CacheItemTest.class,
  LocalCacheLRUTest.class,
  LocalCacheTest.class,
  LocalCacheThreadSafeTest.class
})
public class CacheTestSuite {

}
