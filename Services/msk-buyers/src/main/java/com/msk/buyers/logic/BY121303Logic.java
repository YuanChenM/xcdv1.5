package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BY121303Logic.
 *
 */
@Service
public class BY121303Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121303Logic.class);
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //删除买家基本信息
        static String SQL_DELETE_BUYER_INFO = "deleteBuyerInfo";
    }
    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 删除买家信息
     * @param deleteParam
     */
    @Transactional
    public void deleteBuyerInfo(BaseParam deleteParam){
      /*  BaseParam deleteParam = new BaseParam();*/
       /* deleteParam.setFilter("buyerId",buyerId);*/
        deleteParam.setFilter("delFlg","1");
        //删除买家雇员信息
        deleteParam.setFilter("tableName","by_buyer_employee");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家收货地址信息
        deleteParam.setFilter("tableName","by_buyer_rec_addr");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家收货时间信息
        deleteParam.setFilter("tableName","by_buyer_rec_time");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家销售对象信息
        deleteParam.setFilter("tableName","by_buyer_salestarget");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家销售产品信息
        deleteParam.setFilter("tableName","by_buyer_pd_cla");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家证照信息
        deleteParam.setFilter("tableName","by_buyer_licence");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家证照图片信息
        deleteParam.setFilter("tableName","by_buyer_pictures");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家账号信息
        deleteParam.setFilter("tableName","by_buyer_account");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
        //删除买家基本信息
        deleteParam.setFilter("tableName","by_buyer_basic_info");
        super.modify(SqlId.SQL_DELETE_BUYER_INFO,deleteParam);
    }
}
