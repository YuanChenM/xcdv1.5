package com.msk.common.base;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jackjiang on 16/8/13.
 */
public class BaseServices {
    @Autowired
    private BaseRedisDao baseRedisDao;
    public Long maxId(String tableName){
        return this.maxId(tableName,1);
    }
    public Long maxId(String tableName,int step){
        tableName = tableName.toUpperCase();
        return this.baseRedisDao.getTablePrimaryKe(tableName,step);
    }
}
