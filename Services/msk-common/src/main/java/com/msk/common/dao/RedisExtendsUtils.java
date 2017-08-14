package com.msk.common.dao;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.redis.BaseRedisDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shi_yuxi on 2016/6/13.
 */
public class RedisExtendsUtils extends BaseRedisDao {
    private RedisTemplate<String, String> redisTemplate;

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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                Boolean flag = connection.exists(byteKey);
                if(!flag){
                    byte[] byteValue = serializer.serialize("149999");
                    connection.set(byteKey, byteValue);
                }
                return connection.incrBy(byteKey, step);
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

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        super.setRedisTemplate(redisTemplate);
        this.setDatabase(NumberConst.IntDef.INT_ZERO);

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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteField = serializer.serialize(field);
                byte[] byteValue = serializer.serialize(value);
                return connection.hSet(byteKey, byteField, byteValue);
            }
        })).booleanValue();
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
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(value);
                connection.set(byteKey, byteValue);
                return connection.expire(byteKey, seconds);
            }
        })).booleanValue();
        return result;
    }

    /**
     * 保存map
     *
     * @param key     key
     * @param redisMap redisMap
     * @return 结果
     * @author shi_yuxi
     */
    public void saveRedisMap(final String key, final Map<String, Object> redisMap) {
        this.redisTemplate.execute(new RedisCallback() {
            public Void doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
                byte[] keyByte = serializer.serialize(key);
                Map<byte[], byte[]> mapValue = new HashMap<byte[], byte[]>();
                for(Map.Entry<String,Object> key : redisMap.entrySet()){
                    mapValue.put(serializer.serialize(key.getKey()), serializer.serialize(key.getValue()));
                }
                redisConnection.hMSet(keyByte, mapValue);
                return null;
            }
        });
    }

    /**
     * 获取map
     *
     * @param key     key
     * @return 结果
     * @author shi_yuxi
     */
    public Map<String, String> getRedisMapValue(final String key) {
        return (Map)this.redisTemplate.execute(new RedisCallback() {
            public Map<String, String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer serializer = RedisExtendsUtils.this.getRedisSerializer();
                byte[] keyByte = serializer.serialize(key);
                Map<byte[], byte[]> valueByte = redisConnection.hGetAll(keyByte);
                Map<String, String> result = new HashMap<String, String>();
                for(Map.Entry<byte[], byte[]> key : valueByte.entrySet()){
                    result.put((String)serializer.deserialize(key.getKey()), (String)serializer.deserialize(key.getValue()));
                }
                return result;
            }
        });
    }

    /**
     * 向hash中删除一条记录
     *
     * @param key   key
     * @param fields fields
     * @return 结果
     * @author shi_yuxi
     */
    public boolean removeRedisMapItems(final String key, final String fields) {
        boolean result = ((Boolean) this.redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = getRedisSerializer();
                return connection.hDel(serializer.serialize(key),serializer.serialize(fields));
            }
        })).booleanValue();
        return result;
    }
}
