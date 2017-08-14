package com.msk.comm.config;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {
    @Bean
    public CacheManager ehCacheCacheManager(){
        return CacheManager.newInstance();
    }

    @Bean(name = "configConstCache")
    public Cache configConstCache(CacheManager cacheManager){
        return cacheManager.getCache("configConstCache");
    }

}
