package com.msk.order.service;

import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.result.PageResult;
import com.msk.common.data.redis.BaseRedisDao;
import com.msk.common.exception.SystemException;
import com.msk.common.constant.NumberConstant;
import com.msk.common.data.jpa.condition.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public abstract class BaseService<T,PK extends Serializable> extends com.msk.common.data.jpa.BaseService<T,PK>{
    private static Logger logger = LoggerFactory.getLogger(BaseService.class);
    @Autowired
    private BaseRedisDao redisDao;

    public Long maxId(String tableName){
        return this.maxId(tableName,1);
    }
    public Long maxId(String tableName,int step){
        tableName = tableName.toUpperCase();
        logger.debug("Table Name : "+ tableName);
        return this.redisDao.getTablePrimaryKe(tableName,step);
    }

    @Transactional(readOnly = true)
    public PageResult<T> findPageResult(final BasePageParam pageParam, final Filter<T> filter){
        int pageSize = pageParam.getPageSize();
        if(pageSize == NumberConstant.IntDef.INT_ZERO){
            throw new SystemException("分页查询,Page Size 不能为空");
        }
        int startPos =  pageParam.getStartPos();
        int page = startPos / pageSize;
        Pageable pageable = new PageRequest(page, pageSize);
        Page<T> pageInfo =  this.findAll(filter,pageable);
        PageResult<T> result = new PageResult();
        result.setRecordsTotal(new Long(pageInfo.getTotalElements()).intValue());
        result.setData(pageInfo.getContent());
        return result;
    }

}
