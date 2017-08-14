package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171102Bean;
import com.msk.price.bean.SP171102Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 供应商需求发布详细Logic
 * @author peng_hao
 */
@Service
public class SP171102Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171102Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yang_yang
     */
    private interface SqlId {
        String SQL_FIND_DEMAND_PUBLISH = "findDemandPublish";
        String SQL_FIND_DEMAND_PUBLISH_DETIAL = "findDemandPublishDetial";
        String SQL_INSERT_DEMAND_PUBLISH_DETIAL = "insertDemandPublishDetail";
        String SQL_INSERT_DEMAND_PUBLISH = "insertDemandPublish";
        String SQL_MODIFY_DEMAND_PUBLISH = "modifyDemandPublish";
        String SQL_MODIFY_DEMAND_PUBLISH_DETIAL = "modifyDemandPublishDetail";
        String SQL_GET_BALANCE_RATIO = "getBalanceRatio";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 供应商需求发布查询
     * @param sp171102Param
     * @return
     */
    @Transactional(readOnly = true)
    public SP171102Bean findDemandPublish(SP171102Bean sp171102Param) {
        logger.info("供应商需求发布表查看");
        BaseParam param = new BaseParam();
        param.setFilter("publishId", sp171102Param.getPublishId().toString());
        SP171102Bean sp171102Bean=super.findOne(SqlId.SQL_FIND_DEMAND_PUBLISH, param);
        return sp171102Bean;
    }

    /**
     * 供应商需求发布明细查询
     * @param sp171102Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171102Bean> findDemandPublishDetial(SP171102Param sp171102Param) {
        logger.info("供应商需求发布明细查看");
        List<SP171102Bean> sp171102Beans=new ArrayList<SP171102Bean>();
        BaseParam param = new BaseParam();
        if(sp171102Param!=null){
            param.setFilter("publishId", sp171102Param.getPublishId().toString());
            sp171102Beans=super.findList(SqlId.SQL_FIND_DEMAND_PUBLISH_DETIAL,param);
        }
        return sp171102Beans;
    }

    /**
     * 插入 供应商数量发布表数据
     * @param sp171102Param
     */
    @Transactional
    public void insertDemandPublish(SP171102Bean sp171102Param){
        super.save(SqlId.SQL_INSERT_DEMAND_PUBLISH,sp171102Param);
    }


    /**
     * 插入 供应商数量发布明细表数据
     * @param sp171102Param
     */
    @Transactional
    public void insertDemandPublishDetail(SP171102Bean sp171102Param){
        super.save(SqlId.SQL_INSERT_DEMAND_PUBLISH_DETIAL,sp171102Param);
    }

    /**
     *修改供应商需求发布
     * @param sp171102Param
     */
    @Transactional
    public void modifyDemandPublish(SP171102Bean sp171102Param){
        logger.info("供应商需求发布修改");
        super.save(SqlId.SQL_MODIFY_DEMAND_PUBLISH,sp171102Param);

    }

    /**
     *修改供应商需求发布明细
     * @param sp171102Param
     */
    @Transactional
    public void modifyDemandPublishDetail(SP171102Bean sp171102Param){
        logger.info("供应商需求发布明细修改");
        super.save(SqlId.SQL_MODIFY_DEMAND_PUBLISH_DETIAL,sp171102Param);
    }

    /**
     * 获得分配平衡系数(%)
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171102Bean> getBalanceRatio(){
        BaseParam param = new BaseParam();
        List<SP171102Bean>  sp171102Beans=super.findList(SqlId.SQL_GET_BALANCE_RATIO,param);
        return sp171102Beans;
    }
}
