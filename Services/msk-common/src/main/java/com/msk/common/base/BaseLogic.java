package com.msk.common.base;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.exception.DataNotFindException;
import com.hoperun.core.exception.SystemException;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Base Service
 * 
 * @author jiang_nan
 *
 */
public class BaseLogic {
    /** Base Dao */
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

    /**
     * 返回唯一数据
     * 
     * @param param
     *        Base Param
     * @return Entity对象
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> T findOne(BaseParam param) {
        return this.baseDao.selectOne(this.getSqlId(CommSqlId.FIND_ONE), param);
    }

    /**
     * 返回唯一数据
     * 
     * @param param
     *        Base Param
     * @return Entity对象
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> T findOne(Map<String, Object> param) {
        return this.baseDao.selectOne(this.getSqlId(CommSqlId.FIND_ONE), param);
    }

    /**
     * 返回唯一数据
     * 
     * @param sqlId
     *        SQL ID
     * @param param
     *        Base Param
     * @return Entity对象
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> T findOne(String sqlId, BaseParam param) {
        return this.baseDao.selectOne(this.getSqlId(sqlId), param);
    }

    /**
     * 返回唯一数据
     *
     * @param param
     *        BaseEntity
     * @return Entity对象
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> T findOne(String sqlId, BaseEntity param) {
        return this.baseDao.selectOne(this.getSqlId(sqlId), param);
    }

    /**
     * 返回唯一数据
     * 
     * @param sqlId
     *        SQL ID
     * @param param
     *        Map Param
     * @return Entity对象
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> T findOne(String sqlId, Map<String, Object> param) {
        return this.baseDao.selectOne(this.getSqlId(sqlId), param);
    }

    /**
     * 返回唯一数据,如果没有数据则抛出DataNotFindException异常
     * 
     * @param param
     *        Base Param
     * @return Entity对象
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> T load(BaseParam param) {
        T entity = this.baseDao.selectOne(this.getSqlId(CommSqlId.FIND_ONE), param);
        if (entity == null) {
            throw new DataNotFindException("没有找到你想要的数据");
        }
        return entity;
    }

    /**
     * 返回唯一数据,如果没有数据则抛出DataNotFindException异常
     * 
     * @param sqlId
     *        SQL ID
     * @param paramMap
     *        查询参数Param Map
     * @return Entity对象
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> T load(String sqlId, Map<String, Object> paramMap) {
        T entity = this.baseDao.selectOne(this.getSqlId(sqlId), paramMap);
        if (entity == null) {
            throw new DataNotFindException("没有找到你想要的数据");
        }
        return entity;
    }

    /**
     * 插入操作
     * 
     * @param entity
     *        Entity对象
     * @return 影响的行数
     */
    @Transactional
    public int save(BaseEntity entity) {
        return this.baseDao.insert(this.getSqlId(CommSqlId.SAVE), entity);
    }

    /**
     * 插入操作
     * 
     * @param sqlId
     *        SQL ID
     * @param entity
     *        Entity对象
     * @return 影响的行数
     */
    @Transactional
    public int save(String sqlId, BaseEntity entity) {
        return this.baseDao.insert(this.getSqlId(sqlId), entity);
    }

    /**
     * 插入操作
     * 
     * @param param
     *        Base Param
     * @return 影响的行数
     */
    @Transactional
    public int save(BaseParam param) {
        return this.baseDao.insert(this.getSqlId(CommSqlId.SAVE), param);
    }

    /**
     * 插入操作
     * 
     * @param sqlId
     *        SQL ID
     * @param param
     *        Base Param
     * @return 影响的行数
     */
    @Transactional
    public int save(String sqlId, BaseParam param) {
        return this.baseDao.insert(this.getSqlId(sqlId), param);
    }

    /**
     * 更新数据操作
     * 
     * @param param BaseParam
     * @return 更新影响的行数
     */
    @Transactional
    public int modify(BaseParam param) {
        return this.baseDao.update(this.getSqlId(CommSqlId.MODIFY), param);
    }

    /**
     * 更新数据操作
     * 
     * @param param BaseParam
     * @return 更新影响的行数
     */
    @Transactional
    public int modify(BaseEntity param) {
        return this.baseDao.update(this.getSqlId(CommSqlId.MODIFY), param);
    }

    /**
     * 更新数据操作
     * 
     * @param sqlId Mapper SQL Id
     * @param param BaseParam
     * @return 更新影响的行数
     */
    @Transactional
    public int modify(String sqlId, BaseParam param) {
        return this.baseDao.update(this.getSqlId(sqlId), param);
    }

    /**
     * 更新数据操作
     * 
     * @param sqlId Mapper SQL Id
     * @param param BaseEntity
     * @return 更新影响的行数
     */
    @Transactional
    public int modify(String sqlId, BaseEntity param) {
        return this.baseDao.update(this.getSqlId(sqlId), param);
    }

    /**
     * 删除操作
     * 
     * @param param BaseParam
     * @return 删除影响的行数
     */
    @Transactional
    public int remove(BaseParam param) {
        return this.baseDao.delete(this.getSqlId(CommSqlId.REMOVE), param);
    }

    /**
     * 删除操作
     * 
     * @param entity BaseEntity
     * @return 删除影响的行数
     */
    @Transactional
    public int remove(BaseEntity entity) {
        return this.baseDao.delete(this.getSqlId(CommSqlId.REMOVE), entity);
    }

    /**
     * 删除操作
     * 
     * @param sqlId Mapper Sql Id
     * @param param BaseParam
     * @return 删除影响的行数
     */
    @Transactional
    public int remove(String sqlId, BaseParam param) {
        return this.baseDao.delete(this.getSqlId(sqlId), param);
    }

    /**
     * 删除操作
     * 
     * @param sqlId Mapper Sql Id
     * @param entity BaseEntity
     * @return 删除影响的行数
     */
    @Transactional
    public int remove(String sqlId, BaseEntity entity) {
        return this.baseDao.delete(this.getSqlId(sqlId), entity);
    }

    /**
     * List页面分页查询
     * 
     * @param param
     * @param clazz
     * @return 分页结果
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> PageResult<T> findPage(BaseParam param, Class<T> clazz) {
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setRecordsTotal(this.getPageCount(param));
        if (pageResult.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
            pageResult.setData(this.findPageList(param, clazz));
        } else {
            pageResult.setData(new ArrayList<T>());
        }
        return pageResult;
    }

    /**
     * Page Count
     * 
     * @param param BaseParam
     * @return Count
     */
    @Transactional(readOnly = true)
    public int getPageCount(BaseParam param) {
        return this.baseDao.count(getPageCountSqlId(), param);
    }

    /**
     * Select Count
     * 
     * @param sqlId Mapper Sql Id
     * @param param BaseParam
     * @return Count
     */
    @Transactional(readOnly = true)
    public int getCount(String sqlId, BaseParam param) {
        return this.baseDao.count(this.getSqlId(sqlId), param);
    }

    /**
     * Select Count
     * 
     * @param sqlId Mapper Sql Id
     * @param param BaseEntity
     * @return Count
     */
    @Transactional(readOnly = true)
    public int getCount(String sqlId, BaseEntity param) {
        return this.baseDao.count(this.getSqlId(sqlId), param);
    }
    @Transactional(readOnly = true)
    public int getCount(BaseParam param) {
        return this.baseDao.count(this.getSqlId(CommSqlId.COUNT), param);
    }

    /**
     * Select Count
     * 
     * @param sqlId Mapper Sql Id
     * @param param Map
     * @return Count
     */
    @Transactional(readOnly = true)
    public int getCount(String sqlId, Map<String, Object> param) {
        return this.baseDao.count(this.getSqlId(sqlId), param);
    }

    /**
     * 查询分页数据
     * 
     * @param param param
     * @param clazz Class
     * @return 数据集合
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(BaseParam param, Class<T> clazz) {
        return this.baseDao.selectList(getPageListSqlId(), param);
    }

    /**
     * 查询数据集合
     * 
     * @param sqlId Sql Mapper Id
     * @param param param
     * @return 数据集合
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findList(String sqlId, BaseParam param) {
        return this.baseDao.selectList(this.getSqlId(sqlId), param);
    }

    /**
     * 查询数据集合
     * 
     * @param param param
     * @return 数据集合
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findList(BaseParam param) {
        return this.baseDao.selectList(getSqlId(CommSqlId.FIND_LIST), param);
    }

    /**
     * 查询数据集合
     * 
     * @param paramMap Map对象
     * @param sqlId Sql Mapper Sql Id
     * @return 数据集合
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findList(Map<String, Object> paramMap, String sqlId) {
        return this.baseDao.selectList(getSqlId(sqlId), paramMap);
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
        return this.getNamespaceId() + StringConst.DOT + sqlId;
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
     * @param baseDao
     *        the baseDao to set
     */
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    /**
     * 获得任意对象数据
     * 
     * @param sqlId SQL Id
     * @param param BaseParam
     * @return Object对象
     */
    @Transactional(readOnly = true)
    public Object findObject(String sqlId, BaseParam param) {
        return this.baseDao.selectObject(this.getSqlId(sqlId), param);
    }

    /**
     * 批量插入
     * 
     * @param entityList Entity List
     * @param <T> Table对应的Entity
     * @return 插入影响行数
     */
    @Transactional
    public <T extends BaseEntity> int batchSave(List<T> entityList) {
        return this.baseDao.batchInsert(this.getSqlId(CommSqlId.BATCH_SAVE), entityList);
    }

    /**
     * 默认获得SQL Count数据
     * 
     * @param param RsPageParam
     * @return Count SQL
     */
    @Transactional(readOnly = true)
    public int getPageCount(RsPageParam param) {
        return this.baseDao.count(getPageCountSqlId(), param);
    }

    /**
     * 分页查询, 获得一页的List数据
     * 
     * @param param RsPageParam
     * @param <T> Entity对象
     * @return List 数据
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param) {
        return this.baseDao.selectList(getPageListSqlId(), param);
    }

    /**
     * 获得List数据
     * 
     * @param param RsPageParam
     * @param <T> Entity对象
     * @return List 数据
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findList(RsPageParam param) {
        return this.baseDao.selectList(this.getSqlId(CommSqlId.FIND_LIST), param);
    }

    /**
     * Web Service 查询分页
     * 
     * @param param 分页参数
     * @param pageResult 分页结果
     * @param <T> 返回Entity
     * @return 结果集
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        pageResult.setTotalCount(this.getPageCount(param));
        pageResult.setPageNo(param.getPageNo());
        pageResult.setTotalPage(pageResult.getTotalCount(), param.getPageCount());
        if (pageResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            return this.findPageList(param);
        }
        return new ArrayList<T>();
    }

    /**
     * 表的版本号验证共通
     * 
     * @param tableName 表名
     * @param primaryKey 主键名称可以是组合主键
     * @param primaryKeyValue 主键值
     * @param ver version值
     */
    @Transactional(readOnly = true)
    public void versionValidator(String tableName, String[] primaryKey, Object[] primaryKeyValue, int ver) {
        String sqlId = "com.msk.common.logic.CommonLogic.selectVersion";
        BaseParam param = new BaseParam();
        if (primaryKey == null || primaryKeyValue == null) {
            throw new SystemException("传入数据格式错误,primaryKey或者primaryKeyValue为空");
        }
        int primaryKeyLength = primaryKey.length;
        int primaryKeyValueLength = primaryKeyValue.length;
        if (primaryKeyLength != primaryKeyValueLength) {
            throw new SystemException("传入数据格式错误,primaryKey和primaryKeyValue 长度不一致");
        }

        StringBuffer condtion = new StringBuffer();
        for (int i = 0; i < primaryKeyLength; i++) {
            String key = primaryKey[i];
            Object value = primaryKeyValue[i];
            condtion.append(" AND " + key + " = '" + value + "'");
        }

        param.setFilterObject("tableName", tableName);
        param.setFilterObject("condtion", condtion);
        param.setFilterObject("ver", ver);

        BaseEntity entity = this.baseDao.selectOne(sqlId, param);
        if (entity == null) {
            throw new BusinessException("当前数据已经被别人修改了,请重新加载数据进行修改");
        }

    }
}
