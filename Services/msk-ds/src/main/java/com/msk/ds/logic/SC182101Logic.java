package com.msk.ds.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.ds.bean.SC181103Bean;
import com.msk.ds.bean.SC182101Bean;
import com.msk.ds.rest.comm.RestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SC182101Logic.
 *
 **/
@Service
public class SC182101Logic extends ExcelAsyncPrintService {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182101Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author pxg
     */
    private interface SqlId {
        String SQL_GET_SUM_DELIVERY = "getSumDelivery";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public SC182101Bean getSumDelivery(BasePageParam param) {

        return super.findOne(SqlId.SQL_GET_SUM_DELIVERY,param);
    }

    /**
     * 导出为Excel文件
     * @param param
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, ?> getDataSource(Object param) {
        // 生成pageParam
        BasePageParam pageParam = new BasePageParam();
        // 设置FilterMap
        pageParam.setFilterMap((Map<String, Object>) param);
        // 初始化用户信息
        pageParam.setUserType((String) pageParam.getFilterMap().get("userType"));
        pageParam.setCrtId((String) pageParam.getFilterMap().get("crtId"));
        pageParam.getFilterMap().remove("userType");
        pageParam.getFilterMap().remove("crtId");

        // 如果为供应商，则设置crtId
        if(CommCodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(pageParam.getUserType())){

            pageParam.getFilterMap().put("crtId", pageParam.getCrtId());
        }

        // 设置FilterMap
        DbUtils.buildLikeCondition(pageParam, "deliveryStockId", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "distMonth", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "lgcsName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "suppName", DbUtils.LikeMode.PARTIAL);
        String deliveryStockStatus = StringUtil.toSafeString(pageParam.getFilterMap().get("deliveryStockStatus"));
        if (!StringUtil.isNullOrEmpty(deliveryStockStatus)) {
            String[] deliveryStocks = deliveryStockStatus.split(",");
            pageParam.getFilterMap().put("deliveryStocks", deliveryStocks);
        }

        String sourceFlag = StringUtil.toSafeString(pageParam.getFilterMap().get("sourceFlg"));

        if(!StringUtil.isNullOrEmpty(sourceFlag)){
            String[] sourceFlags = sourceFlag.split(",");
            pageParam.getFilterMap().put("sourceFlg",sourceFlags);
        }

        DbUtils.buildLikeCondition(pageParam, "deliveryReceiveStockTime", DbUtils.LikeMode.FRONT);

        // 查询结果
        List<SC182101Bean> dataList = this.findPageList(pageParam, SC182101Bean.class);

        // 初始化合计数据
        BigDecimal totalSendPlanNum = BigDecimal.ZERO;
        BigDecimal totalSendActualNum = BigDecimal.ZERO;
        BigDecimal totalReceiveNum = BigDecimal.ZERO;
        BigDecimal totalSendPlanQty = BigDecimal.ZERO;
        BigDecimal totalSendActualQty = BigDecimal.ZERO;
        BigDecimal totalRecriveQty = BigDecimal.ZERO;
        BigDecimal totalDifferNum = BigDecimal.ZERO;
        BigDecimal totalDifferQty = BigDecimal.ZERO;

        // 当查询结果不为空的时候，组装数据，计算合计
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, String> deliveryStockStatusMap = CodeMasterManager.findCodeMasterMap("DeliveryStockStatus");
            Map<String, String> sourceFlgMap = CodeMasterManager.findCodeMasterMap("SourceFlg");

            for (SC182101Bean bean : dataList) {
                if (!StringUtil.isNullOrEmpty(bean.getDeliveryStockStatus())) {
                    String deliveryStockStatusName = deliveryStockStatusMap.get(bean.getDeliveryStockStatus());
                    if (null != deliveryStockStatusName) {
                        bean.setDeliveryStockStatus(deliveryStockStatusName);
                    }
                }
                if (!StringUtil.isNullOrEmpty(bean.getSourceFlg())) {
                    String sourceFlgName = sourceFlgMap.get(bean.getSourceFlg());
                    bean.setSourceFlg(sourceFlgName);
                }

                totalSendPlanNum = totalSendPlanNum.add(bean.getSendPlanNum());
                totalSendActualNum = totalSendActualNum.add(bean.getSendActualNum());
                totalReceiveNum = totalReceiveNum.add(bean.getReceiveNum());
                totalSendPlanQty = totalSendPlanQty.add(bean.getSendPlanQty());
                totalSendActualQty = totalSendActualQty.add(bean.getSendActualQty());
                totalRecriveQty = totalRecriveQty.add(bean.getRecriveQty());
                totalDifferNum = totalDifferNum.add(bean.getDifferNum());
                totalDifferQty = totalDifferQty.add(bean.getDifferQty());
            }

            // 调用接口获取生产商名称并放入结果集中
            if(dataList.size() > 0){

                //获取集合
                List<String> slCodeList = new ArrayList<String>();

                //将供应商编码作为查询条件传入调用卖家接口获得生产商名称
                for(SC182101Bean bean:dataList){
                    slCodeList.add(bean.getSuppCode());
                }
                //调用接口获取生产商名称并放入结果集中
                Map<String,String> map = RestUtil.getEPNameBySuppCode(slCodeList);

                //在返回对象里放入调用接口得来的生产商名
                for(SC182101Bean bean:dataList){
                    bean.setManuName("");
                    String value = map.get(bean.getSuppCode());
                    if(!StringUtil.isEmpty(value)){
                        bean.setManuName(value);
                    }
                }
            }
        }

        // 设置paramMap
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("list", dataList);
        paramMap.put("totalSendPlanNum", totalSendPlanNum);
        paramMap.put("totalSendActualNum", totalSendActualNum);
        paramMap.put("totalReceiveNum", totalReceiveNum);
        paramMap.put("totalSendPlanQty", totalSendPlanQty);
        paramMap.put("totalSendActualQty", totalSendActualQty);
        paramMap.put("totalRecriveQty", totalRecriveQty);
        paramMap.put("totalDifferNum", totalDifferNum);
        paramMap.put("totalDifferQty", totalDifferQty);

        return paramMap;
    }
}
