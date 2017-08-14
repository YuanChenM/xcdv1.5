package com.msk.springboot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msk.config.dao.BaseRedisDao;
import com.msk.config.dao.RedisExtendsUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackjiang on 16/6/20.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };

    }

    @Bean
    public CacheManager cacheManager(
            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
    /**
     *  初始化redisTemplate
     *  @param factory factory
     *  @return StringRedisTemplate
     */
    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
    /**
     *  初始化BaseRedisDao
     *  @param redisTemplate redisTemplate
     *  @return BaseRedisDao
     */
    @Bean
    public BaseRedisDao baseRedisDao(RedisTemplate<String, String> redisTemplate){
        BaseRedisDao redisDao = new BaseRedisDao();
        redisDao.setRedisTemplate(redisTemplate);
        return redisDao;
    }
    /**
     *  初始化RedisExtendsUtils
     *  @param redisTemplate redisTemplate
     *  @return BaseRedisDao
     */
    @Bean
    public RedisExtendsUtils redisExtendsUtils(RedisTemplate<String, String> redisTemplate){
        RedisExtendsUtils redisExtendsUtils = new RedisExtendsUtils();
        redisExtendsUtils.setRedisTemplate(redisTemplate);
        return redisExtendsUtils;
    }
    /**
     *  初始化ObjectMapper
     *  @return BaseRedisDao
     */
    @Bean
    public ObjectMapper setObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
    /**
     *  初始化MappingJackson2HttpMessageConverter
     *  @param objectMapper objectMapper
     *  @return MappingJackson2HttpMessageConverter
     */
    @Bean
    public MappingJackson2HttpMessageConverter getMapping(ObjectMapper objectMapper){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setPrettyPrint(true);
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return mappingJackson2HttpMessageConverter;
    }

    /**
     *  初始化RequestMappingHandlerAdapter
     *  @param jsonConverter jsonConverter
     *  @return RequestMappingHandlerAdapter
     */
    @Bean
    public RequestMappingHandlerAdapter getRequestMapping(MappingJackson2HttpMessageConverter jsonConverter){
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(jsonConverter);
        requestMappingHandlerAdapter.setMessageConverters(converters);
        return requestMappingHandlerAdapter;
    }
}
