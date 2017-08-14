package com.msk.common.base.mybatis.spring;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.msk.comm.exception.DataNotFindException;
import com.msk.common.constant.StringConstant;

public class BaseService {
    @Autowired
    private BaseDao baseDao;
    /**
     * 共通SQL Id.
     *
     * @author jiang_nan
     */
    interface CommSqlId {
        /** 分页Count Sql Id */
        String PAGE_COUNT = "getPageCount";
        /** 分页List Sql Id */
        String PAGE_LIST = "findPageList";
        /** 分页List Sql Id */
        String FIND_LIST = "findList";
        /** 插入Sql Id */
        String SAVE = "save";
        /** 查询获得唯一数据的SQL ID */
        String FIND_ONE = "findOne";
        /** 编辑SQL ID */
        String MODIFY = "modify";
        /** 删除SQL ID */
        String REMOVE = "remove";
        /**Batch Save*/
        String BATCH_SAVE = "batchSave";
        /**Sql Count*/
        String COUNT = "count";
    }

    @Transactional(readOnly = true)
    public <T extends Serializable> T findOne(Serializable param) {
        return this.baseDao.selectOne(this.getSqlId(CommSqlId.FIND_ONE), param);
    }

    @Transactional(readOnly = true)
    public <T extends Serializable> T findOne(String sqlId, Serializable param) {
        return this.baseDao.selectOne(this.getSqlId(sqlId), param);
    }

    @Transactional(readOnly = true)
    public <T extends Serializable> T load(Serializable param) {
        T entity = this.baseDao.selectOne(this.getSqlId(CommSqlId.FIND_ONE), param);
        if (entity == null) {
            throw new DataNotFindException("没有找到你想要的数据");
        }
        return entity;
    }
    @Transactional(readOnly = true)
    public <T extends Serializable> T load(Serializable param,String sqlId) {
        T entity = this.baseDao.selectOne(sqlId, param);
        if (entity == null) {
            throw new DataNotFindException("没有找到你想要的数据");
        }
        return entity;
    }


    @Transactional
    public int save(Serializable entity) {
        return this.baseDao.insert(this.getSqlId(CommSqlId.SAVE), entity);
    }

    @Transactional
    public int save(String sqlId, Serializable entity) {
        return this.baseDao.insert(this.getSqlId(sqlId), entity);
    }


    @Transactional
    public int modify(Serializable param) {
        return this.baseDao.update(this.getSqlId(CommSqlId.MODIFY), param);
    }


    @Transactional
    public int modify(String sqlId, Serializable param) {
        return this.baseDao.update(this.getSqlId(sqlId), param);
    }


    @Transactional
    public int remove(Serializable param) {
        return this.baseDao.delete(this.getSqlId(CommSqlId.REMOVE), param);
    }


    /**
     * 删除操作
     *
     * @param sqlId Mapper Sql Id
     * @param param BaseParam
     * @return 删除影响的行数
     */
    @Transactional
    public int remove(String sqlId, Serializable param) {
        return this.baseDao.delete(this.getSqlId(sqlId), param);
    }

    @Transactional(readOnly = true)
    public int getPageCount(Serializable param) {
        return this.baseDao.count(getPageCountSqlId(), param);
    }

    @Transactional(readOnly = true)
    public int getCount(String sqlId, Serializable param) {
        return this.baseDao.count(this.getSqlId(sqlId), param);
    }

    @Transactional(readOnly = true)
    public int getCount(Serializable param) {
        return this.baseDao.count(this.getSqlId(CommSqlId.COUNT), param);
    }

    @Transactional(readOnly = true)
    public <T extends Serializable> List<T> findPageList(Serializable param) {
        return this.baseDao.selectList(getPageListSqlId(), param);
    }

    @Transactional(readOnly = true)
    public <T extends Serializable> List<T> findList(String sqlId, Serializable param) {
        return this.baseDao.selectList(this.getSqlId(sqlId), param);
    }

    @Transactional(readOnly = true)
    public <T extends Serializable> List<T> findList(Serializable param) {
        return this.baseDao.selectList(getSqlId(CommSqlId.FIND_LIST), param);
    }


    /**
     * MyBatis Name spaceId
     *
     * @return name spaceId
     */
    public String getNamespaceId() {
        return this.getClass().getName();
    }

    /**
     * MyBatis SQL Mapper中的方法ID
     *
     * @param sqlId SQL Id
     * @return 生成SQL Id
     */
    public String getSqlId(String sqlId) {
        return this.getNamespaceId() + StringConstant.DOT + sqlId;
    }

    /**
     * 获得分页Count Sql Id
     *
     * @return Count Sql Id
     */
    public String getPageCountSqlId() {
        return this.getSqlId(CommSqlId.PAGE_COUNT);
    }

    /**
     * 获得分页集合Sql Id
     *
     * @return 集合 Sql Id
     */
    public String getPageListSqlId() {
        return this.getSqlId(CommSqlId.PAGE_LIST);
    }

    /**
     * @return the baseDao
     */
    public BaseDao getBaseDao() {
        return baseDao;
    }





    /**
     * 批量插入
     *
     * @param entityList Entity List
     * @param <T> Table对应的Entity
     * @return 插入影响行数
     */
    @Transactional
    public <T extends Serializable> int batchSave(List<Serializable> entityList) {
        return this.baseDao.batchInsert(this.getSqlId(CommSqlId.BATCH_SAVE), entityList);
    }







}
