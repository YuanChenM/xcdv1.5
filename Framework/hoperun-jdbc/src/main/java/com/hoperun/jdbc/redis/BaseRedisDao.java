package com.hoperun.jdbc.redis;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.DataNotFindException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.*;

/**
 * Base Redis Dao
 * @author jiang_nan
 * @version 1.0
 **/
public class BaseRedisDao {
    /**Spring Redis Template*/
    private RedisTemplate<String, String> redisTemplate;
    /**
     * Redis Save
     * @param key Redis key
     * @param value Redis value
     * @return true保存成功false保存失败
     */
    public boolean save(final String key, final String value){
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey  = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(value);
                return connection.setNX(byteKey, byteValue);
            }
        });
        return result;
    }

    /**
     * 根据Redis Key删除Redis value
     * @param key Redis Key
     */
    public void delete(String key) {
        List<String> list = new ArrayList<String>();
        list.add(key);
        delete(list);
    }
    /**
     * 根据Redis Key List删除多个Redis value
     * @param keys Redis Key List
     */
    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }
    /**
     * 修改Redis Value
     * @param key Redis key
     * @param value Redis Value
     * @return true修改成功false修改失败
     */
    public boolean update(final String key,final String value) {
        if (get(key) == null) {
            throw new DataNotFindException("数据行不存在, key = " + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey  = serializer.serialize(key);
                byte[] byteName = serializer.serialize(value);
                connection.set(byteKey, byteName);
                return true;
            }
        });
        return result;
    }
    /**
     * 通过key获取Redis Value
     * @param key Redis Key
     * @return Redis Value
     */
    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteValue = connection.get(byteKey);
                if (byteValue == null) {
                    return null;
                }
                String value = serializer.deserialize(byteValue);
                return value;
            }
        });
        return result;
    }

    /**
     * 获得Redis Map Value
     * @param key key
     * @return Redis Map Value
     */
    public Map<String,String> getRedisMapValue(final String key){
        return redisTemplate.execute(new RedisCallback<Map<String,String>>() {
            public Map<String, String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
                Map<String, String> data = boundHashOperations.entries();
                return data;
            }
        });
    }

    /**
     *
     * @param key
     * @param redisMap
     */
    public void saveRedisMap(final String key,final Map<String,Object> redisMap){
        redisTemplate.execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection redisConnection) throws DataAccessException {
                BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps(key);
                boundHashOperations.putAll(redisMap);
                return null;
            }
        });
    }
    /**
     * 获取 RedisSerializer
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }
    /**
     * 设置Redis Template
     * @param redisTemplate Redis Template
     */
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.setDatabase(NumberConst.IntDef.INT_ZERO);
    }

    /**
     * 设置Data Base
     * @param index Data Base Index
     */
    public void setDatabase(int index){
        JedisConnectionFactory connectionFactory = (JedisConnectionFactory)this.redisTemplate.getConnectionFactory();
        connectionFactory.setDatabase(index);
    }

    /**
     * 集合新增
     * @param key
     * @param values
     */
    public boolean sadd(final String key, final String values){
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey  = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(values);
                return connection.sAdd(byteKey, byteValue);
            }
        });
        return false;
    }

    /**
     * 集合排序查询
     * @param key
     */
    public List<String> sort(final String key){

        List<String> result = redisTemplate.execute(new RedisCallback<List<String>>() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey  = serializer.serialize(key);
                Set<byte[]> list = connection.sMembers(byteKey);
                List<String> result = new ArrayList<String>();
                for(byte[] b : list) {
                    result.add(serializer.deserialize(b));
                }
				Collections.sort(result);
                return result;
            }
        });
        return result;
    }

    /**
     * 移除集合中元素
     * @param key
     * @param values
     */
    public boolean srem(final String key, final String values){
        this.setDatabase(NumberConst.IntDef.INT_TWELVE);

        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey  = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(values);
                return connection.sRem(byteKey, byteValue);
            }
        });
        return false;
    }
}
