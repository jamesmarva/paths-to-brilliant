package cache.demo;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author 王涵威
 * @date 21.6.11 17:23
 */
public class Demo01 {

    public static void main(String[] args) {
        LoadingCache<String, String> strings = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, String>() {

                            @Override
                            public String load(String key) throws Exception {
                                return System.currentTimeMillis() + "";
                            }
                });

        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }
}
