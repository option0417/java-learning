package tw.com.wd.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tw.com.wd.cache.localcache.CacheConfiguration;
import tw.com.wd.cache.localcache.LocalCache;
import tw.com.wd.cache.localcache.obj.CacheItem;
import tw.com.wd.cache.localcache.obj.CacheItemBuilder;
import tw.com.wd.cache.localcache.obj.CacheItem;
import tw.com.wd.cache.localcache.obj.CacheItemBuilder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LocalCacheTest {
//    private CacheConfiguration cacheConfiguration;
//    private LocalCache<String> testLocalCache;
//
//    @Before
//    public void beforeTest() {
//        cacheConfiguration  = CacheConfiguration.buildCacheConfiguration(5);
//        testLocalCache      = new LocalCache<String>(cacheConfiguration);
//    }
//
//    @After
//    public void afterTest() {
//        testLocalCache      = null;
//        cacheConfiguration  = null;
//        System.gc();
//    }
//
//    @Test
//    public void testBuildLocalCacheFromCacheConfiguration() {
//        LocalCache localCache = new LocalCache(cacheConfiguration);
//
//        assertThat(localCache,                      not(nullValue()));
//        assertThat(localCache.getCacheCapacity(),   is(cacheConfiguration.getCacheCapcity()));
//        assertThat(localCache.getCacheStrategy(),   is(cacheConfiguration.getCacheStrategy()));
//        assertThat(localCache.getCacheSize(),       is(0));
//    }
//
//    @Test
//    public void testPutCacheItemToLocalCache() {
//        String cacheKey                     = "key001";
//        CacheItem<String> stringCacheItem   = CacheItemBuilder.buildStringCacheItem("String001");
//        int previoudSize                    = testLocalCache.getCacheSize();
//
//        testLocalCache.put(cacheKey, stringCacheItem);
//
//        assertThat(previoudSize,                                    is(0));
//        assertThat(testLocalCache.getCacheSize() - previoudSize,    is(1));
//        assertThat(testLocalCache.getCacheSize(),                   is(1));
//    }
//
//    @Test
//    public void testPutCacheItemWithSameKeyToLocalCache() {
//        String cacheKey                     = "key001";
//        CacheItem<String> stringCacheItem1  = CacheItemBuilder.buildStringCacheItem("String001");
//        CacheItem<String> stringCacheItem2  = CacheItemBuilder.buildStringCacheItem("String002");
//
//        testLocalCache.put(cacheKey, stringCacheItem1);
//        String value1 = (String)testLocalCache.get(cacheKey).getValue();
//
//        testLocalCache.put(cacheKey, stringCacheItem2);
//        String value2 = (String)testLocalCache.get(cacheKey).getValue();
//
//        assertThat(testLocalCache.getCacheSize(),   is(1));
//        assertThat(value1,                          is("String001"));
//        assertThat(value2,                          is("String002"));
//    }
//
//    @Test
//    public void testReadCacheItemToLocalCache() {
//        String cacheKey1                    = "key001";
//        String cacheKey2                    = "key002";
//        CacheItem<String> stringCacheItem   = CacheItemBuilder.buildStringCacheItem("String001");
//
//        testLocalCache.put(cacheKey1, stringCacheItem);
//
//        CacheItem<String> rtnCacheItem  = testLocalCache.get(cacheKey1);
//        CacheItem<String> nullCacheItem = testLocalCache.get(cacheKey2);
//
//        assertThat(rtnCacheItem,            not(nullValue()));
//        assertThat(rtnCacheItem.getValue(), is("String001"));
//        assertThat(nullCacheItem,           is(nullValue()));
//    }
//
//    @Test
//    public void testReadNullCacheItemToLocalCache() {
//        String cacheKey = "key001";
//        CacheItem<String> nullCacheItem = testLocalCache.get(cacheKey);
//
//        assertThat(nullCacheItem,           is(nullValue()));
//    }
//
//    @Test
//    public void testUpdateCacheItemToLocalCache() {
//        String cacheKey1                    = "key001";
//        CacheItem<String> stringCacheItem1  = CacheItemBuilder.buildStringCacheItem("String001");
//        CacheItem<String> stringCacheItem2  = CacheItemBuilder.buildStringCacheItem("String002");
//
//        testLocalCache.put(cacheKey1, stringCacheItem1);
//        testLocalCache.put(cacheKey1, stringCacheItem2);
//
//        CacheItem<String> rtnCacheItem  = testLocalCache.get(cacheKey1);
//
//        assertThat(rtnCacheItem,            not(nullValue()));
//        assertThat(rtnCacheItem.getValue(), is("String002"));
//    }
//
//    @Test
//    public void testDeleteCacheItemToLocalCache() {
//        String cacheKey                    = "key001";
//        CacheItem<String> stringCacheItem  = CacheItemBuilder.buildStringCacheItem("String001");
//
//        testLocalCache.put(cacheKey, stringCacheItem);
//        int sizeAfterPut = testLocalCache.getCacheSize();
//
//        testLocalCache.remove(cacheKey);
//        int sizeAfterDelete = testLocalCache.getCacheSize();
//
//        CacheItem<String> rtnCacheItem  = testLocalCache.get(cacheKey);
//
//        assertThat(sizeAfterPut,    is(1));
//        assertThat(sizeAfterDelete, is(0));
//        assertThat(rtnCacheItem,    is(nullValue()));
//    }
//
//    @Test
//    public void testIsCacheItemExist() {
//        String cacheKey1            = "key001";
//        String cacheKey2            = "key002";
//        CacheItem stringCacheItem1  = CacheItemBuilder.buildStringCacheItem("String001");
//
//        testLocalCache.put(cacheKey1, stringCacheItem1);
//
//        assertThat(testLocalCache.isExist(cacheKey1), is(true));
//        assertThat(testLocalCache.isExist(cacheKey2), is(false));
//    }
}
