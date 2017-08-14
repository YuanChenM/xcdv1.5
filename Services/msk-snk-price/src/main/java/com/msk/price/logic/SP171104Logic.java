package com.msk.price.logic;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.consts.CodeMasterConst;
import com.msk.common.logic.CommonLogic;
import com.msk.order.bean.ISO151434OrderRsResult;
import com.msk.order.bean.ISO151434RsParam;
import com.msk.order.bean.ISO151434RsResult;
import com.msk.price.bean.SP171104Bean;
import com.msk.price.bean.SP171104Param;
import com.msk.price.utils.CommRestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/5/9.
 */
@Service
public class SP171104Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * 取得分配平衡系数
     * @param param
     * @return 分配平衡系数
     */
    @Transactional(readOnly = true)
    public List<SP171104Bean> findConstRatio(SP171104Param param){
        param.setFilterObject("ratioType", "balanceRatio");
        List<SP171104Bean> result= super.findList(SqlId.SQL_ID_CONST_RATIO,param);
        return result;
    }

    /**
     * 取得发布总数量和等级占比
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SP171104Bean findDemandPublish(SP171104Param param){
        SP171104Bean result= super.findOne(SqlId.SQL_ID_DEMAND_PUBLISH, param);
        return result;
    }

    /**
     * 查询卖家供应商投标数量表
     * @param param
     * @return 申报数量、分配数量、是否同意档案卡
     */
    @Transactional(readOnly = true)
    public SP171104Bean findDemandApply(SP171104Param param){
        SP171104Bean result = super.findOne(SqlId.SQL_ID_DEMAND_APPLY,param);
        return result;
    }

    /**
     * 更新卖家供应商投标数量表，插入卖家供应商投标数量履历明细表
     * @param param
     */
    @Transactional
    public void updateDemandApply(SP171104Param param){
        int resultCount = NumberConst.IntDef.INT_ZERO;
        Date nowDate = DateTimeUtil.getCustomerDate();
        param.setCrtTime(nowDate);
        param.setUpdTime(nowDate);
        param.setActTime(nowDate);
        //检查数据版本是否正确
        super.versionValidator("SP_SELLER_PD_DEMANDAPPLY_NUM",new String[]{"DEMAND_ID"},new Object[]{param.getDemandId()},param.getVer());
        // 更新卖家供应商投标数量表
        resultCount = super.modify(SqlId.SQL_ID_MODIFY_DEMAND_APPLY,param);
        if(resultCount == NumberConst.IntDef.INT_ONE){
            // 如果只是修改状态，不需要追加履历
            if ("1".equals(param.getUpdateFlg())){
                resultCount =  this.insertHistory(param);
                if(resultCount != NumberConst.IntDef.INT_ONE){
                    throw new BusinessException("履历表插入失败！");
                }
            }
        } else {
            throw new BusinessException("主表更新失败！");
        }
    }

    /**
     * 查询某区域下申报的卖家信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171104Bean> searchSeller(SP171104Param param){
        List<SP171104Bean>  sp171104BeanList = super.findList(param);
        List<String> sellerCodeList = new ArrayList<>();
        for (SP171104Bean sp171104Bean :sp171104BeanList){
            sellerCodeList.add(sp171104Bean.getSellerCode());
        }
        ISO151434RsParam iso151434RsParam = new ISO151434RsParam();
        iso151434RsParam.setThisMonth(param.getDemandYearMonth());
        iso151434RsParam.setSellerCodeList(sellerCodeList);
        iso151434RsParam.setDistrictCode(param.getLgcsCode());
        iso151434RsParam.setOrderSource(CodeMasterConst.SupplyPlatform.SNK);
        // 1-表示查询某区域下供应商的销售量
        iso151434RsParam.setFlag(1);
        RsResponse<ISO151434OrderRsResult> seller =  CommRestUtil.halfMonthOrder(iso151434RsParam);
        List<ISO151434RsResult> rsResultsList = new ArrayList<>();
        if(seller.getResult() !=null){
            rsResultsList  = seller.getResult().getIso151434RsResultList();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (ISO151434RsResult result : rsResultsList){
            total =  DecimalUtil.add(total, result.getOrderCount());
        }
        List<SP171104Bean> list = new ArrayList<>();
        for(SP171104Bean sp171104Bean :sp171104BeanList){
            if(rsResultsList.isEmpty()){
                sp171104Bean.setOrderCount(BigDecimal.ZERO);
                list.add(sp171104Bean);
            }else {
                for (ISO151434RsResult result : rsResultsList){
                    if(result.getSellerCode().equals(sp171104Bean.getSellerCode())){
                        sp171104Bean.setOrderCount(result.getOrderCount());
                        sp171104Bean.setPercent(result.getOrderCount().divide(total));
                    }else{
                        sp171104Bean.setOrderCount(BigDecimal.ZERO);
                    }
                    list.add(sp171104Bean);
                }
            }
        }
        return list;
    }
    /**
     * 插入卖家供应商投标数量履历明细表
     * @param param
     */
    @Transactional
    public int insertHistory(SP171104Param param){
        long maxDemandHisId = commonLogic.maxId("SP_SELLER_PD_DEMANDAPPLY_NUM_HIS", "DEMAND_HIS_ID");
        long nextDemandDetailId = (Long) super.findObject(SqlId.SQL_ID_FIND_DEMAND_DETAIL_ID,param);
        param.setFilterObject("demandHisId",maxDemandHisId);
        param.setFilterObject("demandDetailId",nextDemandDetailId);
        return super.save(param);
    }

    interface SqlId{
        String SQL_ID_MODIFY_DEMAND_APPLY = "modifyDemandApply";
        String SQL_ID_FIND_DEMAND_DETAIL_ID = "findDemandDetailId";
        String SQL_ID_DEMAND_APPLY = "findDemandApply";
        String SQL_ID_CONST_RATIO = "findConstRatio";
        String SQL_ID_DEMAND_PUBLISH = "findDemandPublish";
    }

}
