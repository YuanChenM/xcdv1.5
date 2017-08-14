package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.BR121402Param;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 配置管理画面Logic
 * <p/>
 * Created by zhao_chen on 2016/06/14.
 */
@Service
public class BR121402Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BR121402Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 删除配置列表数据
     *
     * @param id
     * @return
     */
    @Transactional
    public int deleteSettingDate(Long id ,BR121402Param param) {
        logger.info("删除配置列表开始");
        param.setId(id);
        return super.modify(SqlId.DELETE_SETTING_DATE, param);
    }

    /**
     * 新增买家配置列表数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public int regRange(BR121402Param param) {
        logger.info("验证范围开始");
        return super.getCount(SqlId.REG_RANGE, param);
    }

    /**
     * 判断是否存在该等级
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public int isExist(BR121402Param param) {
        logger.info("判断是否存在该等级");
        return super.getCount(SqlId.IS_EXIST, param);
    }

    interface SqlId {
        String DELETE_SETTING_DATE = "deleteSettingDate";
        String REG_RANGE = "regRange";
        String IS_EXIST = "isExist";
    }

}


