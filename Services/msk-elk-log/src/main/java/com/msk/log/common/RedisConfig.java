package com.msk.log.common;

import com.hoperun.jdbc.redis.BaseRedisDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mao_yejun on 2016/6/8.
 */
public class RedisConfig extends BaseRedisDao{
    private RedisTemplate<String, String> redisTemplate;

    public Map<String, String> getAllUrl() {
        Map<String, String> result = ((Map<String, String>)this.redisTemplate.execute(new RedisCallback<Map<String, String>>() {
            public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
                Map<String, String> result = new HashMap<String, String>();
                RedisSerializer serializer = RedisConfig.this.getRedisSerializer();
                byte[] byteKey = serializer.serialize("*");
                Set<byte[]> keys = connection.keys(byteKey);
                for(byte[] key : keys){
                    byte[] value = connection.get(key);
                    result.put(serializer.deserialize(key).toString(), serializer.deserialize(value).toString());
                }
                return result;
            }
        }));
        return result;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        super.setRedisTemplate(redisTemplate);
        this.setDatabase(14);
    }
}
