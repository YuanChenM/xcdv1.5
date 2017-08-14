package com.msk.price.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.logic.CommonLogic;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171106Bean;
import com.msk.price.bean.SP171106Param;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/5/9.
 */
@Service
public class SP171106Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        String SQL_ID_MODIFY_DEMAND_APPLY = "modifyDemandApply";//更新卖家申报数量
        String SQL_ID_FIND_DEMAND_DETAIL_ID = "findDemandDetailId";//投标明细ID序列号
        String SQL_ID_DEMAND_APPLY = "findDemandApply";//获取卖家申报数量
        String SQL_ID_CONST_RATIO = "findConstRatio";//获取订单通道平衡系数
        String SQL_ID_DEMAND_PUBLISH = "findDemandPublish";//获取产品各等级需求百分百
        String SQL_ID_SAVE_DEMAND_APPLY = "saveDemandApply";//添加卖家申报数量数据
        String SQL_ID_QUERY_DEMAND_INFO = "queryDemandInfo";//根据申报id获取申报详情
        String SQL_ID_QUERY_PD_INFO = "queryPdInfo";//根据产品code获取产品信息
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 取得分配平衡系数
     *
     * @param param
     * @return 分配平衡系数
     */
    @Transactional(readOnly = true)
    public List<SP171106Bean> findConstRatio(SP171106Param param) {
        param.setFilterObject("ratioType", "balanceRatio");
        List<SP171106Bean> result = super.findList(SqlId.SQL_ID_CONST_RATIO, param);
        return result;
    }

    /**
     * 取得发布总数量和等级占比
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SP171106Bean findDemandPublish(SP171106Param param) {
        SP171106Bean result = super.findOne(SqlId.SQL_ID_DEMAND_PUBLISH, param);
        return result;
    }

    /**
     * 查询卖家供应商投标数量表
     *
     * @param param
     * @return 申报数量、分配数量、是否同意档案卡
     */
    @Transactional(readOnly = true)
    public SP171106Bean findDemandApply(SP171106Param param) {
        SP171106Bean result = super.findOne(SqlId.SQL_ID_DEMAND_APPLY, param);
        return result;
    }

    /**
     * 更新/插入卖家供应商投标数量表，插入卖家供应商投标数量履历明细表
     *
     * @param param
     */
    @Transactional
    public void update(SP171106Param param) {
        int resultCount = NumberConst.IntDef.INT_ZERO;
        Date nowDate = DateTimeUtil.getCustomerDate();
        param.setCrtTime(nowDate);
        param.setUpdTime(nowDate);
        param.setActTime(nowDate);
        Date firstDay = DateTimeUtil.parseDate(param.getDemandYearMonth(), DateTimeUtil.FORMAT_YEAR_MONTH);
        Date lastDay = DateTimeUtil.lastDay(firstDay);
        param.setDemandStartDate(firstDay);
        param.setDemandEndDate(lastDay);

        // 未申报的情况
        if (param.getDemandId() == NumberConst.IntDef.INT_ZERO) {
            long demandId = commonLogic.maxId("SP_SELLER_PD_DEMANDAPPLY_NUM", "DEMAND_ID");
            param.setDemandId(demandId);
            resultCount = super.save(SqlId.SQL_ID_SAVE_DEMAND_APPLY, param);
        } else {
            //检查数据版本是否正确
            super.versionValidator("SP_SELLER_PD_DEMANDAPPLY_NUM", new String[]{"DEMAND_ID"}, new Object[]{param.getDemandId()}, param.getVer());
            // 更新本条数据
            resultCount = super.modify(SqlId.SQL_ID_MODIFY_DEMAND_APPLY, param);
        }
        if (resultCount == NumberConst.IntDef.INT_ONE) {
            // 有修改数量或者备考时，追加履历
            if ("1".equals(param.getUpdateHisFlg())) {
                resultCount = this.insertHistory(param);
                if (resultCount != NumberConst.IntDef.INT_ONE) {
                    throw new BusinessException("履历表插入失败！");
                }
            }
        } else {
            throw new BusinessException("主表更新失败！");
        }
    }

    /**
     * 插入卖家供应商投标数量履历明细表
     *
     * @param param
     */
    @Transactional
    public int insertHistory(SP171106Param param) {
        long maxDemandHisId = commonLogic.maxId("SP_SELLER_PD_DEMANDAPPLY_NUM_HIS", "DEMAND_HIS_ID");
        long nextDemandDetailId = (Long) super.findObject(SqlId.SQL_ID_FIND_DEMAND_DETAIL_ID, param);
        param.setFilterObject("demandHisId", maxDemandHisId);
        param.setFilterObject("demandDetailId", nextDemandDetailId);
        return super.save(param);
    }

    /**
     * 根据申报id获取申报详情
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public void queryDemandInfo(SP171106Param param)throws Exception{
        SP171106Bean result = super.findOne(SqlId.SQL_ID_QUERY_DEMAND_INFO, param);
        param.setLgcsCode(result.getLgcsCode());
        param.setLgcsName(result.getLgcsName());
        param.setDemandYearMonth(result.getDemandYearMonth());
        param.setDemandStartDate(result.getDemandStartDate());
        param.setDemandEndDate(result.getDemandEndDate());
        param.setIsConfirm(result.getIsConfirm());
        param.setPdCode(result.getPdCode());
        param.setPdTypeCode(result.getPdTypeCode());
        param.setPdName(result.getPdName());
        param.setClassesName(result.getClassesName());
        param.setMachiningName(result.getMachiningName());
        param.setBreedName(result.getBreedName());
        param.setGradeCode(result.getGradeCode());
        param.setFeatureName(result.getFeatureName());
        param.setIsAgree(result.getIsAgree());
        param.setPublishTotalNum(result.getPublishTotalNum());
    }
    /**
     * 根据申报id获取申报详情
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public void queryPdInfo(SP171106Param param)throws Exception{
        SP171106Bean result = super.findOne(SqlId.SQL_ID_QUERY_PD_INFO, param);
        param.setLgcsCode(result.getLgcsCode());
        param.setLgcsName(result.getLgcsName());
        param.setIsConfirm(result.getIsConfirm());
        param.setPdCode(result.getPdCode());
        param.setPdTypeCode(result.getPdTypeCode());
        param.setPdName(result.getPdName());
        param.setClassesName(result.getClassesName());
        param.setMachiningName(result.getMachiningName());
        param.setBreedName(result.getBreedName());
        param.setGradeCode(result.getGradeCode());
        param.setFeatureName(result.getFeatureName());
        param.setIsAgree(result.getIsAgree());
    }
}
