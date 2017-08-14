package com.hoperun.jdbc.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hoperun.core.bean.BaseParam;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base Dao.
 * 
 * @author jiang_nan
 */
public class BaseDao extends SqlSessionDaoSupport {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BaseDao.class);

    /**
     * 查询返回List结果,参数为Base Param
     * 
     * @param sqlId Sql Id
     * @param param Base Param
     * @return List数据集
     */
    public <T extends Serializable> List<T> selectList(String sqlId, BaseParam param) {
        logger.debug("select List by BaseParam");
        return this.getSqlSession().selectList(sqlId, param);
    }

    /**
     * 查询获得List数据
     * 
     * @param sqlId SQL ID
     * @param param Map参数
     * @return List数据集合
     */
    public <T extends Serializable> List<T> selectList(String sqlId, Map<String, Object> param) {
        return this.getSqlSession().selectList(sqlId, param);
    }

    /**
     * 根据某一主键或者组合唯一条件获得唯一数据
     * 
     * @param sqlId SQL ID
     * @param param Base Param
     * @return Entity对象
     */
    public <T extends Serializable> T selectOne(String sqlId, BaseParam param) {
        return this.getSqlSession().selectOne(sqlId, param);
    }

    /**
     * 根据某一主键或者组合唯一条件获得唯一数据
     * 
     * @param sqlId SQL ID
     * @param param BaseEntity
     * @return Entity对象
     */
    public <T extends Serializable> T selectOne(String sqlId, Serializable param) {
        return this.getSqlSession().selectOne(sqlId, param);
    }

    /**
     * 根据某一主键或者组合唯一条件获得唯一数据
     * 
     * @param sqlId SQL ID
     * @param param Map
     * @return Entity对象
     */
    public <T extends Serializable> T selectOne(String sqlId, Map<String, Object> param) {
        return this.getSqlSession().selectOne(sqlId, param);
    }

    /**
     * Sql Count
     * 
     * @param sqlId SQL ID
     * @param param Base Param
     * @return Count
     */
    public int count(String sqlId, BaseParam param) {
        Integer count = this.getSqlSession().selectOne(sqlId, param);
        return count == null ? 0 : count;
    }

    /**
     * Sql Count
     * 
     * @param sqlId SQL ID
     * @param param BaseEntity
     * @return Count
     */
    public int count(String sqlId, Serializable param) {
        Integer count = this.getSqlSession().selectOne(sqlId, param);
        return count == null ? 0 : count;
    }

    /**
     * SQL Count
     * 
     * @param sqlId SQL ID
     * @param param Map Param
     * @return Count
     */
    public int count(String sqlId, Map<String, Object> param) {
        Integer count = this.getSqlSession().selectOne(sqlId, param);
        return count == null ? 0 : count;
    }

    /**
     * 插入影响的行数
     * 
     * @param sqlId SQL ID
     * @param param Base Param
     * @return
     */
    public int insert(String sqlId, BaseParam param) {
        return this.getSqlSession().insert(sqlId, param);
    }

    /**
     * 插入操作
     * 
     * @param sqlId SQL ID
     * @param entity
     * @return 插入返回的行数
     */
    public <T extends Serializable> int insert(String sqlId, T entity) {
        return this.getSqlSession().insert(sqlId, entity);
    }

    /**
     * 插入操作
     * 
     * @param sqlId SQL ID
     * @param param MAP Param
     * @return 插入返回的行数
     */
    public int insert(String sqlId, Map<String, Object> param) {
        return this.getSqlSession().insert(sqlId, param);
    }

    /**
     * 批量插入
     * 
     * @param sqlId SQL ID
     * @param entityList 批量插入的数据集
     * @return 插入返回的行数
     */
    public <T extends Serializable> int batchInsert(String sqlId, List<T> entityList) {
        return this.getSqlSession().insert(sqlId, entityList);
    }

    /**
     * 更新操作。
     * 
     * @param sqlId SQL ID
     * @param param 更新参数
     * @return 返回更新影响的行数
     */
    public int update(String sqlId, BaseParam param) {
        return this.getSqlSession().update(sqlId, param);
    }

    /**
     * 更新操作
     * 
     * @param sqlId SQL ID
     * @param entity Entity对象
     * @return 返回更新影响的行数
     */
    public <T extends Serializable> int update(String sqlId, T entity) {
        return this.getSqlSession().update(sqlId, entity);
    }

    /**
     * 删除操作
     * 
     * @param sqlId SQL ID
     * @param param 删除条件
     * @return 删除影响的行数
     */
    public int delete(String sqlId, BaseParam param) {
        return this.getSqlSession().delete(sqlId, param);
    }

    /**
     * 删除操作
     * 
     * @param sqlId SQL ID
     * @param entity 删除条件
     * @return 删除影响的行数
     */
    public <T extends Serializable> int delete(String sqlId, T entity) {
        return this.getSqlSession().update(sqlId, entity);
    }

    /**
     * 根据BaseParam返回一个Object
     * @param sqlId SQL ID
     * @param param 查询参数
     * @return Object对象
     */
    public Object selectObject(String sqlId, BaseParam param) {
        return this.getSqlSession().selectOne(sqlId, param);
    }

}
