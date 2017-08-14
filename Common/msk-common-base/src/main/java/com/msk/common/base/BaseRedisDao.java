package com.msk.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class BaseRedisDao {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public Long getTablePrimaryKe(final String tableName,final int step){
        long result = ((long) this.redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = BaseRedisDao.this.getRedisSerializer();
                byte[] byteKey = serializer.serialize(tableName);
                Boolean flag = connection.exists(byteKey);
                if(!flag){
                    byte[] byteValue = serializer.serialize("99999");
                    connection.set(byteKey, byteValue);
                }
                return connection.incrBy(byteKey, step);
            }
        }));
        return result;
    }
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

}
