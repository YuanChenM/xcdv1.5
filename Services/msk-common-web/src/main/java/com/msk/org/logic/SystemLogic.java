package com.msk.org.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * SystemLogic
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Service
public class SystemLogic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SystemLogic.class);

    interface SqlId {
        String SQL_ID_COUNT_SYSTEM = "countSystem";
    }

    /**
     * 根据员工编码和系统编码判断当前用户是否有进入该系统的权限
     * 
     * @param emplCode 员工编码
     * @param systemCode 系统编码
     * @return true:可以进入系统 false:不可以进入系统
     */
    @Transactional
    public boolean checkSystemExists(String emplCode, String systemCode) {
        logger.debug("根据员工编码和系统编码判断当前用户是否有进入该系统的权限");
        BaseParam param = new BaseParam();
        param.setFilter("emplCode", emplCode);
        param.setFilter("sysCode", systemCode);
        return super.getCount(SqlId.SQL_ID_COUNT_SYSTEM, param) > 0;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
