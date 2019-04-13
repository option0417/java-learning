package tw.com.wd.cache;


import org.junit.Test;
import tw.com.wd.cache.localcache.obj.CacheItem;
import tw.com.wd.cache.localcache.obj.CacheItemBuilder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CacheItemBuilderTest {

    @Test
    public void testBuildStringCacheItem() {
        String stringValue = "String001";
        CacheItem<String> stringCacheItem = CacheItemBuilder.buildStringCacheItem(stringValue);

        assertThat(stringCacheItem,             not(nullValue()));
        assertThat(stringCacheItem.getValue(),  is(stringValue));
    }
}
