package com.msk.common.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


public class SpringMyBatisDao extends SqlSessionDaoSupport{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SpringMyBatisDao.class);

    /**
     * 查询返回List结果,参数为Base Param
     *
     * @param sqlId Sql Id
     * @param param Base Param
     * @return List数据集
     */
    public <T extends Serializable> List<T> selectList(String sqlId, Serializable param) {
        logger.debug("select List by BaseParam");
        return this.getSqlSession().selectList(sqlId, param);
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
     * 插入影响的行数
     *
     * @param sqlId SQL ID
     * @param param Base Param
     * @return
     */
    public int insert(String sqlId, Serializable param) {
        return this.getSqlSession().insert(sqlId, param);
    }




    /**
     * 批量插入
     *
     * @param sqlId SQL ID
     * @param entityList 批量插入的数据集
     * @return 插入返回的行数
     */
    public <T extends Serializable> int batchInsert(String sqlId, List<Serializable> entityList) {
        return this.getSqlSession().insert(sqlId, entityList);
    }

    /**
     * 更新操作。
     *
     * @param sqlId SQL ID
     * @param param 更新参数
     * @return 返回更新影响的行数
     */
    public int update(String sqlId, Serializable param) {
        return this.getSqlSession().update(sqlId, param);
    }



    /**
     * 删除操作
     *
     * @param sqlId SQL ID
     * @param param 删除条件
     * @return 删除影响的行数
     */
    public int delete(String sqlId, Serializable param) {
        return this.getSqlSession().delete(sqlId, param);
    }

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
