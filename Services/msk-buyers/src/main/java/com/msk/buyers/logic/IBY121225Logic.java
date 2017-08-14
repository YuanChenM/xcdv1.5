package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.buyers.bean.IBY121207RsParam;
import com.msk.buyers.bean.IBY121225Param;
import com.msk.buyers.bean.IBY121225Result;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.ByBuyerEmployee;
import com.msk.core.entity.ByBuyerPdCla;
import com.msk.core.entity.ByBuyerSalestarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tao_zhifa on 2016/6/20.
 */
@Service
public class IBY121225Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(IBY121225Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId {
        // 查询雇员信息
        static String FIND_EMPLOYEE = "findEmployee";
        // 查询经营产品类别
        static String FIND_PDCLASS = "findPdClass";
        // 查询销售产品信息
        static String FIND_PDSALESTARGET = "findSalesTarget";
    }


    /**
     * 获取所有的BuyerId对应的买家销售对象信息
     * @param baseParam
     * @return
     */
    public  List<ByBuyerSalestarget> getBuyerSalesTargetList(BaseParam baseParam){
        return  this.findList(SqlId.FIND_PDSALESTARGET,baseParam);
    }

    /**
     * 获取所有的BuyerId对应的买家经营类别信息
     * @param baseParam
     * @return
     */
    public  List<ByBuyerPdCla> getBuyerPdClaList(BaseParam baseParam){
        return  this.findList(SqlId.FIND_PDCLASS,baseParam);
    }
}
