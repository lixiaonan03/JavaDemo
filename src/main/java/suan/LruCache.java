package suan;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author：李晓楠 时间：2022/9/11 16:22
 */
public class LruCache<K,V> extends LinkedHashMap<K,V> {
    private int cacheSize;

    public LruCache(int cacheSize) {
        super(16,0.75f,true);
        this.cacheSize = cacheSize;
    }

    /**
     * 判断元素个数是否超过缓存容量
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size() > cacheSize;
    }
}
