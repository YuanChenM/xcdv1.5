package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xhy
 * @version 创建时间：2016年1月13日 下午14:26:20
 *          产品分类接口
 */
@Service
public class IPD141110Logic extends BaseLogic {


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}