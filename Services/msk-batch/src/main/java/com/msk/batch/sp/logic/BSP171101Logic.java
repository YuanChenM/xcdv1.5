package com.msk.batch.sp.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.sp.bean.BSP171101Bean;
import com.msk.batch.sp.bean.BSP171101Param;
import com.msk.batch.sp.commUtils.RestUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * BSP171101Logic
 *
 * @author sun_jiaju
 **/
@Service
public class BSP171101Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSP171101Logic.class);
    /**
     * CommonLogic
     */
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     *  数据处理
     *
     * @return
     */
    @Transactional
    public void dataResolve(BaseParam param) {
        BSP171101Param dataParam = (BSP171101Param) param;
        // 共通获取价盘周期
        dataParam.setPricePeriod(RestUtils.getPriceCyclePeriod());
        logger.debug("数据处理开始");

        List<BSP171101Bean> resultList = super.findList(SqlId.SQLID_SELECT_WAYGRADE_PRICE, dataParam);
        Date nowDate =  DateTimeUtil.getCustomerDate();
        if (CollectionUtils.isNotEmpty(resultList)){
            //相应价盘周期的数据逻辑删除
            /*
            将其挪至此处，是防止如果上来就将当前时间的下一个周期价盘数据逻辑删除的话，
            就会影响价盘录入（简易版）采购员报价的价盘数据给删除了。
             */
            super.modify(SqlId.SQLID_UPDATE_SELLER_PD_PRICEPLATE, dataParam);
            for (BSP171101Bean bsp171101Bean : resultList){
                long maxPriceId = commonLogic.maxId("SP_SELLER_PD_PRICEPLATE", "PRICE_ID");
                bsp171101Bean.setPriceId(maxPriceId);
                bsp171101Bean.setCrtId("batch");
                bsp171101Bean.setCrtTime(nowDate);
                bsp171101Bean.setUpdId("batch");
                bsp171101Bean.setUpdTime(nowDate);
                bsp171101Bean.setActId("batch");
                bsp171101Bean.setActTime(nowDate);

                //0 未参与分销，则delFlg为1
                if("0".equals(bsp171101Bean.getIsValid())){
                    bsp171101Bean.setDelFlg("1");
                }else{
                    bsp171101Bean.setDelFlg("0");
                }
            }
                int insert_max = NumberConst.IntDef.INT_HUNDRED;
                List<BSP171101Bean> insertList =  new ArrayList<BSP171101Bean>();
                int length = resultList.size();
                int start = NumberConst.IntDef.INT_ZERO;
                int end = length > insert_max ? insert_max : length;
                while(start < length){
                    insertList = resultList.subList(start,end);
                    super.batchSave(insertList);
                    start = end;
                    end = length > (end + insert_max) ? (end + insert_max) : length;
                }
        }
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    public interface SqlId {
        static final String SQLID_UPDATE_SELLER_PD_PRICEPLATE = "updateSellerPdPriceplate";;
        static final String SQLID_SELECT_WAYGRADE_PRICE = "selectWaygradePrice";
    }
}
