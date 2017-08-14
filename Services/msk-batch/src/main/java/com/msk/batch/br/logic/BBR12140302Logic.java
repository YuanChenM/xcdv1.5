package com.msk.batch.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 同步买家数据
 * Created by yuan_zhifei on 2016/7/22.
 */
@Service
public class BBR12140302Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR12140302Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    Date currentDate = DateTimeUtil.getCustomerDate();

    interface SqlId {
        String DELETE_NEED_FEA_DETAIL = "deleteNeedFeaDetail";
        String DELETE_BUYER_PD_CLA = "deleteBuyerPdCla";
        String DELETE_BUYER_INFO = "deleteBuyerInfo";
        String SAVE_BUYER_PD_CLA = "saveBuyerPdCla";
        String SAVE_NEED_FEA_DETAIL = "saveNeedFeaDetail";
        String GET_EMPLOYEE_COUNT = "getEmployeeCount";
        String GET_DELIVERY_COUNT = "getDeliveryCount";
    }

    @Transactional(readOnly = true)
    public int getEmployeeCount(BaseParam param) {
        return this.getCount(SqlId.GET_EMPLOYEE_COUNT, param);
    }

    @Transactional(readOnly = true)
    public int getDeliveryCount(BaseParam param) {
        return this.getCount(SqlId.GET_DELIVERY_COUNT, param);
    }

    /**
     * 清空买家需求调研数据
     */
    @Transactional
    public void deleteNeedFeaDetail(BaseParam param) {
        this.remove(SqlId.DELETE_NEED_FEA_DETAIL, param);
    }

    /**
     * 清空买家经营产品类型数据
     */
    @Transactional
    public void deleteBuyerPdCla(BaseParam param) {
        this.remove(SqlId.DELETE_BUYER_PD_CLA, param);
    }

    /**
     * 清空买家基本信息数据
     *
     * @param param
     */
    @Transactional
    public void deleteBuyerInfo(BaseParam param) {
        this.remove(SqlId.DELETE_BUYER_INFO, param);
    }

    /**
     * 同步买家经营产品类型数据
     */
    @Transactional
    public int saveBuyerPdCla(BaseParam param) {
        /*param.setCrtTime(currentDate);
        param.setUpdTime(currentDate);
        param.setActTime(currentDate);*/
        return this.save(SqlId.SAVE_BUYER_PD_CLA, param);
    }

    /**
     * 同步买家需求调研数据
     */
    @Transactional
    public int saveNeedFeaDetail(BaseParam param) {
       /* param.setCrtTime(currentDate);
        param.setUpdTime(currentDate);
        param.setActTime(currentDate);*/
        return this.save(SqlId.SAVE_NEED_FEA_DETAIL, param);
    }
}
