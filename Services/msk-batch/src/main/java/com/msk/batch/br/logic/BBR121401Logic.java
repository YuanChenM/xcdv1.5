package com.msk.batch.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.br.bean.BBR121401Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.StatusConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrPdPoolInfo;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * BBR121401Logic
 *
 * @author zhou_yajun
 **/
@Service
public class BBR121401Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121401Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    public interface SqlId {
        /** 清除产品池数据*/
        static String DELETE_PD_POOL = "deletePdPool";
        /** 获取产品池基础数据*/
        static String FIND_PD_POOL = "findPdPool";
        /** 批量插入产品池基础数据*/
        static String INSERT_PD_POOL_LIST = "insertPdPoolList";
        /** 单条插入产品池基础数据*/
        static String INSERT_PD_POOL_ONE = "insertPdPoolOne";
        /** 修改产品池数据*/
        static String MODIFY_PD_POOL = "modifyPdPool";
        /** 统计买家数据是否存在*/
        static String COUNT_BUYER_INFO = "countBuyerInfo";
        /** 插入买家池数据*/
        static String INSERT_BUYER_POOL = "insertBuyerPool";
        /** 获取买家需求产品特征*/
        static String FIND_BUYER_NEED_FEA = "findBuyerNeedFea";
    }
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
        logger.debug("买家标准产品池数据整理");
        //清除产品池数据
        this.remove(SqlId.DELETE_PD_POOL,new BaseParam());
        /**获取当前月 前一个月第一天到最后一天的订单收货时间*/
        //系统当前月
        String nowYearMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH,DateTimeUtil.getCustomerDate());
        //当前月的上个月
        String lastYearMonth = DateTimeUtil.getLastMonth(nowYearMonth);
        Date date = DateTimeUtil.parseDate(lastYearMonth,DateTimeUtil.FORMAT_YEAR_MONTH);
        //当前月的上个月的第一天
        String sReceivedTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.firstDay(date));
        String startReceivedTime = sReceivedTime+" 00:00:00";
        //当前月的上个月的最后一天
        String eReceivedTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD,DateTimeUtil.lastDay(date));
        String endReceivedTime = eReceivedTime+" 23:59:59";
        //订单详细全部收货状态7
        String detailStatus= StringUtil.toString(StatusConst.OrderDetailStatusDef.ALL_RECEIPT);
        param.setFilter("startReceivedTime",startReceivedTime);
        param.setFilter("endReceivedTime",endReceivedTime);
        param.setFilter("detailStatus",detailStatus);
        //获取产品池基础数据
        List<BBR121401Param> pdPoolList = this.findList(SqlId.FIND_PD_POOL,param);
        if (!CollectionUtils.isEmpty(pdPoolList)){
            Long maxId = commonLogic.maxId("BR_PD_POOL_INFO","ID");
            for (BBR121401Param pdPoolInfo : pdPoolList){
                //设置产品标准属性码和共通数据
                String standardCode = pdPoolInfo.getClassesCode() + pdPoolInfo.getMachiningCode() + pdPoolInfo.getBreedCode() + pdPoolInfo.getFeatureCode() + pdPoolInfo.getGradeCode();
                pdPoolInfo.setStandardCode(standardCode);
                pdPoolInfo.setId(maxId);
                pdPoolInfo.setCrtId(param.getCrtId());
                pdPoolInfo.setCrtTime(param.getCrtTime());
                pdPoolInfo.setUpdId(param.getUpdId());
                pdPoolInfo.setUpdTime(param.getUpdTime());
                pdPoolInfo.setActId(param.getActId());
                pdPoolInfo.setActTime(param.getActTime());
                //判断产品是否已备案 是否参与神农客分销 是否已经卖家上线
                //如果产品已备案则产品池表中备案特征字段设置为当前特征
                if("1".equals(pdPoolInfo.getSlRecordFeature())){
                    pdPoolInfo.setSlRecordFeature(pdPoolInfo.getFeatureName());
                }else{
                    pdPoolInfo.setSlRecordFeature(null);
                }
                //如果产品已参与神农客分销则产品池表中字段设置为当前特征
                if("1".equals(pdPoolInfo.getOemOnlineFeature())){
                    pdPoolInfo.setOemOnlineFeature(pdPoolInfo.getFeatureName());
                }else{
                    pdPoolInfo.setOemOnlineFeature(null);
                }
                //如果产品已经卖家上线则产品池表中字段设置为当前特征
                if("4".equals(pdPoolInfo.getSlOnlineFeature()) || "5".equals(pdPoolInfo.getSlOnlineFeature())){
                    pdPoolInfo.setSlOnlineFeature(pdPoolInfo.getFeatureName());
                }else{
                    pdPoolInfo.setOemOnlineFeature(null);
                }
                maxId = maxId + NumberConst.IntDef.INT_ONE;
            }
            //插入买家标准产品池表
            this.getBaseDao().batchInsert(SqlId.INSERT_PD_POOL_LIST,pdPoolList);
            //设置买家标准产品池买家需求特征数据
            buyerNeedFeature(param);
            //整理买家池数据
            buyerDateResolve(pdPoolList);
        }
    }

    /**
     * 买家池数据整理
     * @param pdPoolList
     */
    @Transactional
    public void buyerDateResolve(List<BBR121401Param> pdPoolList){
        //数据整理开始
        logger.debug("买家池数据整理");
        for (BBR121401Param bbr121401Param : pdPoolList){
            if(!StringUtils.isNullOrEmpty(bbr121401Param.getBuyerId())){
                //判断该买家是否已存入买家池
                int brBuyerPoolInfo = this.getCount(SqlId.COUNT_BUYER_INFO, bbr121401Param);
                //如果该买家在买家池中不存在,将买家数据插入买家池
                if(brBuyerPoolInfo == NumberConst.IntDef.INT_ZERO){
                    //将买家数据插入买家池
                    this.save(SqlId.INSERT_BUYER_POOL,bbr121401Param);
                }
            }
        }
    }

    /**
     * 设置买家标准产品池买家需求特征数据
     */
    @Transactional
    public void buyerNeedFeature(BaseParam param){
        //获取买家需求产品特征
        List<BBR121401Param> brNeedFeaDetailList = this.findList(SqlId.FIND_BUYER_NEED_FEA,new BaseParam());
        if(!CollectionUtils.isEmpty(brNeedFeaDetailList)){
            for(BBR121401Param needFea : brNeedFeaDetailList){
                //如果字段isStandard是0表示不再目录中,则直接插入产品池表
                if("0".equals(needFea.getIsStandard())){
                    //单条插入买家池数据
                    Long maxId = commonLogic.maxId("BR_PD_POOL_INFO","ID");
                    BrPdPoolInfo pdPoolInfo = new BrPdPoolInfo();
                    pdPoolInfo.setId(maxId);
                    pdPoolInfo.setClassesCode(needFea.getClassesCode());
                    pdPoolInfo.setClassesName(needFea.getClassesName());
                    pdPoolInfo.setMachiningCode(needFea.getMachiningCode());
                    pdPoolInfo.setMachiningName(needFea.getMachiningName());
                    pdPoolInfo.setBreedCode(needFea.getBreedCode());
                    pdPoolInfo.setScientificName(needFea.getScientificName());
                    pdPoolInfo.setLocalName(needFea.getLocalName());
                    pdPoolInfo.setSalesName(needFea.getSalesName());
                    pdPoolInfo.setDemandFeature(needFea.getDemandFeature());
                    pdPoolInfo.setOrderCount(NumberConst.IntDef.INT_ZERO);
                    pdPoolInfo.setSellQty(NumberConst.IntDef.INT_ZERO);
                    pdPoolInfo.setCrtId(param.getCrtId());
                    pdPoolInfo.setCrtTime(param.getCrtTime());
                    pdPoolInfo.setUpdId(param.getUpdId());
                    pdPoolInfo.setUpdTime(param.getUpdTime());
                    pdPoolInfo.setActId(param.getActId());
                    pdPoolInfo.setActTime(param.getActTime());
                    this.save(SqlId.INSERT_PD_POOL_ONE,pdPoolInfo);
                }else{
                    //如果字段isStandard是1表示在目录中,则更新产品池表市场需求审核注册字段
                    //设置标准产品属性码
                    needFea.setStandardCode(needFea.getClassesCode() + needFea.getMachiningCode() + needFea.getFeatureCode());
                    this.modify(SqlId.MODIFY_PD_POOL,needFea);
                }
            }
        }
    }
}
