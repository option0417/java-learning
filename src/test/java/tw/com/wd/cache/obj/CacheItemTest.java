package tw.com.wd.cache.obj;

import org.junit.Test;
import tw.com.wd.cache.localcache.obj.CacheItemBuilder;
import tw.com.wd.cache.localcache.obj.IntegerCacheItem;
import tw.com.wd.cache.localcache.obj.StringCacheItem;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CacheItemTest {
    @Test
    public void testStringEquals() {
        StringCacheItem stringCacheItem1    = CacheItemBuilder.buildStringCacheItem("value1");
        StringCacheItem stringCacheItem2    = CacheItemBuilder.buildStringCacheItem("value1");
        StringCacheItem stringCacheItem3    = CacheItemBuilder.buildStringCacheItem("value2");
        StringCacheItem stringCacheItem4    = CacheItemBuilder.buildStringCacheItem("");
        StringCacheItem stringCacheItem5    = CacheItemBuilder.buildStringCacheItem(null);

        assertThat(stringCacheItem1.equals(stringCacheItem2),   is(true));
        assertThat(stringCacheItem1.equals(stringCacheItem3),   is(false));
        assertThat(stringCacheItem1.equals(stringCacheItem4),   is(false));
        assertThat(stringCacheItem1.equals(stringCacheItem5),   is(false));
        assertThat(stringCacheItem1.equals(null),               is(false));
    }

    @Test
    public void testDifferentObjectEquals() {
        StringCacheItem stringCacheItem     = CacheItemBuilder.buildStringCacheItem("value1");
        IntegerCacheItem integerCacheItem   = CacheItemBuilder.buildIntegerCacheItem(100);

        assertThat(stringCacheItem.equals(integerCacheItem), is(false));
    }
}
