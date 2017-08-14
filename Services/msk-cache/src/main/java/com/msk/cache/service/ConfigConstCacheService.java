package com.msk.cache.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by jackjiang on 16/7/21.
 */
@Service
public class ConfigConstCacheService {
    @Autowired
    @Qualifier("configConstCache")
    private Cache configConstCache;

    public void setCacheValue(String key, String value) {
        Element element = new Element(key, value);
        configConstCache.put(element);
    }

    public String getCacheValue(String key){
        Element element = configConstCache.get(key);
        if(element == null){
            return null;
        }
        Object value = element.getObjectValue();

        return (String) value;
    }

    public void updateCacheValue(String key, String value){
        Element element = new Element(key, value);
        configConstCache.put(element);
    }

    public void removeCacheValue(String key){
        this.configConstCache.remove(key);
    }

    public void removeAllCacheValue(){
        this.configConstCache.removeAll();
    }


}
