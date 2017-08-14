package com.msk.ds.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.ds.bean.SC182203Bean;
import com.msk.ds.bean.SC182203Param;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * SC182203Logic.
 *
 * @author yi_qixiang
 * @version 1.0
 **/
@Service
public class SC182203Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182203Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }



    /**
     * 根据用户账户查到供应商 该方法未被调用
     *
     * @return suppName
     */
    public SC182203Bean getSupp(SC182203Param sc182203Param) {

        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("slAccount", sc182203Param.getSlAccount());
        List<SC182203Bean> slEnterpriseList = new ArrayList<SC182203Bean>();
        //slEnterpriseList = this.findList(SqlId.SQL_GET_SUPP, sc182203Param);
        ISL231193RsParam param = new ISL231193RsParam();
        param.setSlAccount(sc182203Param.getSlAccount());
        /**根据账户只能查询出一个供应商应该调用queryslEpData，是否查询suppp集*/
        List<ISL231193RsResult> rsList = RestUtil.slEpDataListSearch(param);
        for(ISL231193RsResult rs : rsList){
            SC182203Bean scBean = new SC182203Bean();
            scBean.setSuppName(rs.getEpName());
            scBean.setSuppCode(rs.getSlCode());
            slEnterpriseList.add(scBean);
        }
        if(CollectionUtils.isNotEmpty(slEnterpriseList)){
            return slEnterpriseList.get(0);
        }else{
            return null;
        }

    }

}
