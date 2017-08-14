package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121001Bean;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class IBY121315Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121315Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private BY121001Logic by121001Logic;

    interface SqlId{
        //获取当前的买家编码
        static String FIND_BUYERCODE_BY_BUYERID = "findBuyerCodeByBuyerId";
        //更新最新的买家编码
        static String MODIFY_BUYERCODE_BY_BUYERID = "modifyBuyerCodeByBuyerId";
    }
    /**
     * 买家上线状态变更时
     */
    @Transactional
    public void modifyBuyerCode(BaseParam param){
        //获取买家上线状态
        String marketingsStatus = String.valueOf(param.getFilterMap().get("marketingsStatus"));
        //根据买家ID获取当前买家编码
        ByBuyerBasicInfo buyerBasicInfo = this.findOne(SqlId.FIND_BUYERCODE_BY_BUYERID,param);
        if(null == buyerBasicInfo){
            throw new BusinessException("参数错误");
        }
        String buyerCode = buyerBasicInfo.getBuyerCode();
        int index = buyerCode.indexOf("-");
        if(index > NumberConst.IntDef.INT_ZERO){
            //获取买家校验码
            BY121001Bean by121001Bean = new BY121001Bean();
            by121001Bean.setBuyerCode(buyerCode);
            by121001Bean.setMarketingsStatus(marketingsStatus);
            String saleStatusCode = by121001Logic.marketingsStatusCode(by121001Bean);
            String identifyCode = by121001Logic.getSecIdenCode(by121001Bean);
            buyerCode = buyerCode.replace(buyerCode.substring(index - 2,buyerCode.length()),saleStatusCode + identifyCode);
        }
        param.setFilter("buyerCode",buyerCode);
        //更新最新的买家编码
        this.modify(SqlId.MODIFY_BUYERCODE_BY_BUYERID, param);
    }
}
