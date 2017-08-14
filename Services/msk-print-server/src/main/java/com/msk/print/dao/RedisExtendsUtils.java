package com.msk.print.dao;

import com.msk.comm.consts.NumberConst;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shi_yuxi on 2016/6/23.
 */
public class RedisExtendsUtils extends BaseRedisDao {
    private RedisTemplate<String, String> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        super.setRedisTemplate(redisTemplate);
        this.setDatabase(NumberConst.IntDef.INT_ZERO);
    }

    /**
     * Redis Save key存在则覆盖原有value
     *
     * @param key   Redis key
     * @param value Redis value
     * @return true保存成功
     */
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] byteKey = serializer.serialize(key);
                byte[] byteValue = serializer.serialize(value);
                connection.set(byteKey, byteValue);
                connection.close();
                return true;
            }
        });
        return result;
    }

    /**
     * Redis批量查询Map
     *
     * @param  resultId  Redis keys
     * @return 结果
     */
    public List<Map<String, String>> getTreeNode(final List<String> resultId){
        List<Object> list = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                for (String s : resultId) {
                    stringRedisConn.hGetAll(s);
                }
                return null;
            }
        });
        return (List)list;
    }

    /**
     * Redis批量查询String
     *
     * @param  resultId  Redis keys
     * @return 结果
     */
    public List<String> getStringBatch(final List<String> resultId){
        List<Object> list = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                for (String s : resultId) {
                    stringRedisConn.get(s);
                }
                return null;
            }
        });
        return (List)list;
    }
    /**
     * Redis插入Map
     *
     * @param  map
     * @return 结果
     */
    public void setHashBatch(final  Map<String,Map<String,String>> map){
        redisTemplate.execute(new RedisCallback<Void>() {
                public Void doInRedis(RedisConnection connection) throws DataAccessException {
                    for(Map.Entry<String,Map<String,String>> m : map.entrySet()){
                        BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps(m.getKey());
                        boundHashOperations.putAll(m.getValue());
                    }
                    return null;
            }
        },false, true);
    }
    /**
     * Reids批量查询list
     *
     * @param  resultId  Redis keys
     * @return 结果
     */
    public List<List<String>> getTreeLeaf(final List<String> resultId){
        List<Object> list = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                for (String s : resultId) {
                    stringRedisConn.lRange(s,NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_N_ONE);
                }
                return null;
            }
        });
        return (List)list;
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
}