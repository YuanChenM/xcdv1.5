package com.msk.common.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.common.dao.RedisExtendsUtils;
import com.msk.core.entity.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CommonLogic
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Service("commonLogic")
public class CommonLogic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(CommonLogic.class);

    @Autowired
    private RedisExtendsUtils redisExtendsUtils;
    /**
     * SQL Id
     */
    interface SqlId{
        /**获得最大Max Id*/
        String SQL_ID_MAX_ID = "maxId";
        String SQL_ID_FIND_CONSTANT_LIST = "findConstantList";
    }
    /**
     *获得Max Id
     * @param tableName 表名
     * @param tableFiled 字段名称
     * @return Max Id
     */
    @Transactional(readOnly = true)
    public Long maxId(String tableName,String tableFiled){
        logger.debug("获得Max Id");
        //BaseParam param = new BaseParam();
        //param.setFilter("tableName",tableName);
        //param.setFilter("tableFiled",tableFiled);
        //return (Long)super.findObject(SqlId.SQL_ID_MAX_ID,param);
        redisExtendsUtils.setDatabase(RedisDataBaseDef.PRIMARY_KEY_DB);
        Long maxId = redisExtendsUtils.increment(tableName.toUpperCase());
        logger.debug("Max Id:" + maxId);
        return maxId;
    }

    /**
     * 连续获得多个Max Id
     *
     * @param tableName 表名
     * @param step      长度
     * @return Max Ids
     */
    @Transactional(readOnly = true)
    public Long maxIds(String tableName, long step) {
        logger.debug("获得Max Id");
        redisExtendsUtils.setDatabase(RedisDataBaseDef.PRIMARY_KEY_DB);
        return redisExtendsUtils.increment(tableName, step);
    }
    public List<CommConstant> findConstantList(String codeName){
        BaseParam param = new BaseParam();
        param.setFilter("codeName",codeName);
        return super.findList(SqlId.SQL_ID_FIND_CONSTANT_LIST,param);
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
