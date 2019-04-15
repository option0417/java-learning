package tw.com.wd.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tw.com.wd.cache.localcache.CacheConfiguration;
import tw.com.wd.cache.localcache.LocalCache;
import tw.com.wd.cache.localcache.obj.CacheItem;
import tw.com.wd.cache.localcache.obj.CacheItemBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class LocalCacheLRUTest {
//    private int testCacheSize;
//    private CacheConfiguration cacheConfiguration;
//    private LocalCache<String> testLocalCache;
//
//    private String cacheKey1;
//    private String cacheKey2;
//    private String cacheKey3;
//    private String cacheKey4;
//    private String cacheKey5;
//    private String cacheKey6;
//    private CacheItem stringCacheItem1;
//    private CacheItem stringCacheItem2;
//    private CacheItem stringCacheItem3;
//    private CacheItem stringCacheItem4;
//    private CacheItem stringCacheItem5;
//    private CacheItem stringCacheItem6;
//
//    @Before
//    public void beforeTest() {
//        this.testCacheSize      = 5;
//        this.cacheConfiguration = CacheConfiguration.buildCacheConfiguration(testCacheSize);
//        this.testLocalCache     = new LocalCache<String>(cacheConfiguration);
//
//        this.cacheKey1 = "key01";
//        this.cacheKey2 = "key02";
//        this.cacheKey3 = "key03";
//        this.cacheKey4 = "key04";
//        this.cacheKey5 = "key05";
//        this.cacheKey6 = "key06";
//
//        this.stringCacheItem1 = CacheItemBuilder.buildStringCacheItem("String001");
//        this.stringCacheItem2 = CacheItemBuilder.buildStringCacheItem("String002");
//        this.stringCacheItem3 = CacheItemBuilder.buildStringCacheItem("String003");
//        this.stringCacheItem4 = CacheItemBuilder.buildStringCacheItem("String004");
//        this.stringCacheItem5 = CacheItemBuilder.buildStringCacheItem("String005");
//        this.stringCacheItem6 = CacheItemBuilder.buildStringCacheItem("String006");
//    }
//
//    @After
//    public void afterTest() {
//        testLocalCache      = null;
//        cacheConfiguration  = null;
//
//        this.cacheKey1 = null;
//        this.cacheKey2 = null;
//        this.cacheKey3 = null;
//        this.cacheKey4 = null;
//        this.cacheKey5 = null;
//        this.cacheKey6 = null;
//
//        this.stringCacheItem1 = null;
//        this.stringCacheItem2 = null;
//        this.stringCacheItem3 = null;
//        this.stringCacheItem4 = null;
//        this.stringCacheItem5 = null;
//        this.stringCacheItem6 = null;
//
//        System.gc();
//    }
//
//    @Test
//    public void testLRUCacheStrategyWithSequencePut() {
//        testLocalCache.put(cacheKey1, stringCacheItem1);
//        testLocalCache.put(cacheKey2, stringCacheItem2);
//        testLocalCache.put(cacheKey3, stringCacheItem3);
//        testLocalCache.put(cacheKey4, stringCacheItem4);
//        testLocalCache.put(cacheKey5, stringCacheItem5);
//        testLocalCache.put(cacheKey6, stringCacheItem6);
//
//
//        assertThat(testLocalCache.getCacheSize(),               is(5));
//        assertThat(testLocalCache.isExist(cacheKey1),  is(false));
//        assertThat(testLocalCache.get(cacheKey1),               is(nullValue()));
//        assertThat(testLocalCache.get(cacheKey2),               is(stringCacheItem2));
//        assertThat(testLocalCache.get(cacheKey3),               is(stringCacheItem3));
//        assertThat(testLocalCache.get(cacheKey4),               is(stringCacheItem4));
//        assertThat(testLocalCache.get(cacheKey5),               is(stringCacheItem5));
//        assertThat(testLocalCache.get(cacheKey6),               is(stringCacheItem6));
//    }
//
//    @Test
//    public void testLRUCacheStrategyWithAgeCount() {
//        testLocalCache.put(cacheKey1, stringCacheItem1);
//        testLocalCache.put(cacheKey2, stringCacheItem2);
//        testLocalCache.put(cacheKey3, stringCacheItem3);
//        testLocalCache.put(cacheKey4, stringCacheItem4);
//        testLocalCache.put(cacheKey5, stringCacheItem5);
//
//        testLocalCache.get(cacheKey1);
//        testLocalCache.get(cacheKey2);
//        testLocalCache.put(cacheKey6, stringCacheItem6);
//
//        assertThat(testLocalCache.getCacheSize(),               is(5));
//        assertThat(testLocalCache.isExist(cacheKey3),  is(false));
//        assertThat(testLocalCache.get(cacheKey3),               is(nullValue()));
//        assertThat(testLocalCache.get(cacheKey1),               is(stringCacheItem1));
//        assertThat(testLocalCache.get(cacheKey2),               is(stringCacheItem2));
//        assertThat(testLocalCache.get(cacheKey4),               is(stringCacheItem4));
//        assertThat(testLocalCache.get(cacheKey5),               is(stringCacheItem5));
//        assertThat(testLocalCache.get(cacheKey6),               is(stringCacheItem6));
//    }
//
//    @Test
//    public void testLRUCacheStrategyWithAgeCountAndSkip() {
//        testLocalCache.put(cacheKey1, stringCacheItem1);
//        testLocalCache.put(cacheKey2, stringCacheItem2);
//        testLocalCache.put(cacheKey3, stringCacheItem3);
//        testLocalCache.put(cacheKey4, stringCacheItem4);
//        testLocalCache.put(cacheKey5, stringCacheItem5);
//
//        testLocalCache.get(cacheKey1);
//        testLocalCache.get(cacheKey2);
//        testLocalCache.get(cacheKey3);
//        testLocalCache.get(cacheKey5);
//
//        testLocalCache.put(cacheKey6, stringCacheItem6);
//
//        assertThat(testLocalCache.getCacheSize(),               is(5));
//        assertThat(testLocalCache.isExist(cacheKey4),  is(false));
//        assertThat(testLocalCache.get(cacheKey4),               is(nullValue()));
//        assertThat(testLocalCache.get(cacheKey1),               is(stringCacheItem1));
//        assertThat(testLocalCache.get(cacheKey2),               is(stringCacheItem2));
//        assertThat(testLocalCache.get(cacheKey3),               is(stringCacheItem3));
//        assertThat(testLocalCache.get(cacheKey5),               is(stringCacheItem5));
//        assertThat(testLocalCache.get(cacheKey6),               is(stringCacheItem6));
//    }
}
