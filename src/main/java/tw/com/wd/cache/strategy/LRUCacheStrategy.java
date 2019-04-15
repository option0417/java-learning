package tw.com.wd.cache.strategy;

public class LRUCacheStrategy implements CacheStrategy<byte[]> {
	@Override
	public byte[] replace() {
		return null;
	}
//	@Override
//    public byte[] replace() {
//        int minAgeCount 	= Integer.MAX_VALUE;
//        String replaceKEY 	= null;
//
//        Iterator<Map.Entry<String, CacheItem<?>>> iter = cacheItemMap.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry<KEY, CacheItem<?>> entry = iter.next();
//            CacheItem cacheItem             = entry.getValue();
//
//            if (cacheItem.getAgeCount() <= minAgeCount) {
//                replaceKEY  = entry.getKey();
//                minAgeCount = cacheItem.getAgeCount();
//            }
//        }
//
//        if (replaceKEY != null) {
//            cacheItemMap.remove(replaceKEY);
//        }
//        
//        return null;
//    }
}
