package com.msk.print.dao;

import com.msk.comm.consts.NumberConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
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

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BaseRedisDao.class);
    /**Spring Redis Template*/
    protected RedisTemplate<String, String> redisTemplate;
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
                boolean result = connection.setNX(byteKey, byteValue);
                connection.close();
                return result;
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
            throw new RuntimeException("数据行不存在, key = " + key);
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
                connection.close();
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
        logger.info("redis getRedisMapValue方法执行！");
        return redisTemplate.execute(new RedisCallback<Map<String,String>>() {
            public Map<String, String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
                Map<String, String> data = boundHashOperations.entries();
                redisConnection.close();
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
                redisConnection.close();
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
    public long sadd(final String key, final String values){
        long result = ((Long)redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey  = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(values);
                return connection.sAdd(byteKey, byteValue);
            }
        })).longValue();
        return result;
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
    public long srem(final String key, final String values){
        long result = ((Long)redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey  = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(values);
                return connection.sRem(byteKey, byteValue);
            }
        })).longValue();
        return result;
    }

    /**
     * 计数器
     *
     * @param key  key
     * @param step step
     * @return 结果
     * @author shi_yuxi
     */
    public long increment(final String key, final long step) {
        long result = ((long) this.redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                Long result = connection.incrBy(byteKey, step);
                connection.close();
                return result;
            }
        }));
        return result;
    }

    /**
     * 计数器 默认+1
     *
     * @param key key
     * @return 结果
     * @author shi_yuxi
     */
    public long increment(final String key) {
        return this.increment(key, NumberConst.IntDef.INT_ONE);
    }


    /**
     * 判断key是否存在
     *
     * @param key key
     * @return 结果
     * @author shi_yuxi
     */
    public boolean exist(final String key) {
        boolean result = ((Boolean) this.redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                return connection.exists(byteKey);
            }
        })).booleanValue();
        return result;
    }


    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     *
     * @param key key
     * @return 结果
     * @author shi_yuxi
     */
    public long rpush(final String key, final String value) {
        long result = ((Long) this.redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(value);
                return connection.rPush(byteKey, byteValue);
            }
        })).longValue();
        return result;
    }


    /**
     * 从list中移除
     *
     * @param key    key
     * @param values values
     * @return 结果
     * @author shi_yuxi
     */
    public long rpop(final String key, final String values) {
        long result = ((Long) this.redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(values);
                return connection.lRem(byteKey, NumberConst.IntDef.INT_ZERO, byteValue);
            }
        })).longValue();
        return result;
    }

    /**
     * 查询list集合
     *
     * @param key key
     * @return 结果
     * @author shi_yuxi
     */
    public List<String> getList(final String key) {
        List<String> result = ((List<String>) this.redisTemplate.execute(new RedisCallback() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                List<String> resultList = new ArrayList<String>();
                List<byte[]> list = connection.lRange(byteKey, NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_N_ONE);
                for (byte[] b : list) {
                    resultList.add((String) serializer.deserialize(b));
                }
                return resultList;
            }
        }));
        return result;
    }

    /**
     * 查询数据type
     *
     * @param key key
     * @return 结果
     * @author shi_yuxi
     */
    public DataType getType(final String key) {
        DataType result = (DataType) this.redisTemplate.execute(new RedisCallback() {
            public DataType doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                return connection.type(byteKey);
            }
        });
        return result;
    }

    /**
     * 向hash中插入一条数据
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return 结果
     * @author shi_yuxi
     */
    public boolean saveRedisMapOne(final String key, final String field, final String value) {
        boolean result = ((Boolean) this.redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteField = serializer.serialize(field);
                byte[] byteValue = serializer.serialize(value);
                return connection.hSet(byteKey, byteField, byteValue);
            }
        })).booleanValue();
        return result;
    }

    /**
     * 向hash中插入一条或多条数据
     *
     * @param key   key
     * @param fields fields
     * @return 结果
     * @author shi_yuxi
     */
    public long removeRedisMapItems(final String key, final String... fields) {
        long result = ((Long) this.redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                return stringRedisConn.hDel(key,fields);
            }
        })).longValue();
        return result;
    }
    /**
     * 清空当前db
     *
     * @author shi_yuxi
     */
    public void flushdb() {
        this.redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return true;
            }
        });
    }

    /**
     * 给key提供生存周期
     *
     * @param key     key
     * @param seconds seconds
     * @return 结果
     * @author shi_yuxi
     */
    public boolean expire(final String key, final String value, final long seconds) {
        boolean result = ((Boolean) this.redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(value);
                connection.set(byteKey, byteValue);
                return connection.expire(byteKey, seconds);
            }
        })).booleanValue();
        return result;
    }
}
