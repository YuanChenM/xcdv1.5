package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerSalestarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * IBY121204Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121204Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121204Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家销售对象获取
        static String SQL_FIND_SALES_TARGET = "findSalesTarget";
        //买家销售对象更新
        static String SQL_BUYER_SALES_TARGET_MODIFY = "buyerSalesTargetModify";
        //买家销售对象插入
        static String SQL_BUYER_SALES_TARGET_INSERT = "buyerSalesTargetInsert";
        //买家销售对象删除
        static String SQL_BUYER_SALES_TARGET_DELETE = "buyerSalesTargetDelete";
        //接口买家销售对象获取
        static String SQL_FIND_SALES_TARGET_LIST = "findSalesTargetList";
        //验证买家id是否存在
        static String TEST_BUYER_ID = "testBuyerId";
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
     * 买家销售对象更新接口
     * @param param
     * @return
     */
    @Transactional
    public int buyerSalesTargetModify(List<ByBuyerSalestarget> param){
        int resultCount = NumberConst.IntDef.INT_ZERO;
        int delFlagCount = NumberConst.IntDef.INT_ZERO;
        int count = NumberConst.IntDef.INT_ONE;
        //将DB中买家的数据DEL_FLG全部设置为1
        delFlagCount = super.modify(SqlId.SQL_BUYER_SALES_TARGET_DELETE,param.get(NumberConst.IntDef.INT_ZERO));

        if(!CollectionUtils.isEmpty(param)){
            for(int i = NumberConst.IntDef.INT_ZERO;i < param.size();i++){
                //根据传入参数获取买家销售对象
                BaseParam inParam = new BaseParam();
                inParam.setFilter("buyerId", param.get(i).getBuyerId());
                inParam.setFilter("salesTargetType",param.get(i).getSalesTargetType());
                inParam.setFilter("salesTargetName",param.get(i).getSalesTargetName());
                inParam.setFilter("delFlg","1");
                //获取到数据则更新表
                ByBuyerSalestarget salestarget = super.findOne(SqlId.SQL_FIND_SALES_TARGET,inParam);
                if(null == salestarget){
                    if(this.getCount(SqlId.TEST_BUYER_ID,inParam)>NumberConst.IntDef.INT_ZERO){
                        if(!StringUtil.isNullOrEmpty(param.get(i).getSalesTargetType()) && !StringUtil.isNullOrEmpty(param.get(i).getSalesTargetName())){
                            resultCount += buyerSalesTargetSave(param.get(i));
                        }
                    }else{
                        resultCount=NumberConst.IntDef.INT_ZERO;
                    }
                }else{
                    ByBuyerSalestarget updateParam = param.get(i);
                    updateParam.setId(salestarget.getId());
                    updateParam.setUpdTime(DateTimeUtil.getCustomerDate());
                    resultCount += super.modify(SqlId.SQL_BUYER_SALES_TARGET_MODIFY,updateParam);
                }
            }
        }
        if(resultCount == NumberConst.IntDef.INT_ZERO && delFlagCount == NumberConst.IntDef.INT_ZERO){
            count = NumberConst.IntDef.INT_ZERO;
        }
        return count;
    }
    /**
     * 买家销售对象插入
     * @param insertParam
     * @return
     */
    @Transactional
    public int buyerSalesTargetSave(ByBuyerSalestarget insertParam){
        Long id = commonLogic.maxId("by_buyer_salestarget","ID");
        insertParam.setId(id);
        insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdId(insertParam.getUpdId());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_BUYER_SALES_TARGET_INSERT,insertParam);
    }

    /**
     * 买家销售对象查询
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerSalestarget> buyerSalesTargetFind(ByBuyerSalestarget param){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId",param.getBuyerId());
        inParam.setFilter("delFlg","0");
        List<ByBuyerSalestarget> salesTargetList = super.findList(SqlId.SQL_FIND_SALES_TARGET,inParam);
        return salesTargetList;
    }

    /**
     * 接口买家销售对象查询
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerSalestarget> buyerSalesTargetList(BuyerRelationParam param){
        List<ByBuyerSalestarget> salesTargetList = super.findList(SqlId.SQL_FIND_SALES_TARGET_LIST,param);
        return salesTargetList;
    }
}
