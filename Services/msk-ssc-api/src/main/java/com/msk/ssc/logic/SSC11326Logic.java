package com.msk.ssc.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscDeliveryPdControl;
import com.msk.ssc.bean.*;
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
 * Created by liu_yan2 on 2016/8/8.
 */
@Service
public class SSC11326Logic extends BaseLogic {

    private Logger logger = LoggerFactory.getLogger(SSC11326Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    public CommonLogic commonLogic;

    interface SqlId {
        String SQL_ID_FIND_CONTRACT_PLAN_INFO = "findContractPlanInfo";//查询合同生效日及交货期中的最后交货日
        String SQL_ID_FIND_PRODUCE_PD_CONTROL = "findProducePdControl"; //查询生产商生产计划详细
        String SQL_ID_FIND_STOCK_PRODUCT_DETAIL = "findStockProductDetail"; //入库产品管控
        String SQL_ID_FIND_PRODUCE_TOTAL_DATE = "findProduceTotalByDate"; //查询按日统计
        String SQL_ID_FIND_PRODUCE_TOTAL_NAME = "findProduceTotalByName"; //查询产品统计
        String SQL_ID_BATCH_UPDATE_PRODUCE_PLAN = "batchUpdate";//批量修改日产量
        String SQL_ID_FIND_DELIVERY_PD_CONTROL = "findDeliveryPdControl";//查询运输期产品管控
        String SQL_ID_BATCH_SAVE_DELIVERY_PD_CONTROL = "batchSaveDeliveryPdControl";//批量保存运输期产品管控
        String SQL_ID_BATCH_UPDATE_DELIVERY_PD_CONTROL = "batchUpdateDeliveryPdControl";//批量修改运输期产品管控
    }

    /**
     * 查询合同生效日及交货期中的最后交货日
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11326RsBean findContractPlanInfo(SSC11326RsParam param) {
       return this.findOne(SqlId.SQL_ID_FIND_CONTRACT_PLAN_INFO, param);
    }

    /**
     * 查询生产商生产计划详细
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11326RsResult findProducePdControl(SSC11326RsParam param) {
        List<SSC11326RsBean> ssc11326RsBeanList = this.findList(SqlId.SQL_ID_FIND_PRODUCE_PD_CONTROL, param);
        if (ssc11326RsBeanList.isEmpty()) {
            return null;
        }
        SSC11326RsResult rsResult = new SSC11326RsResult();
        rsResult.setSsc11326RsBeanList(ssc11326RsBeanList);
        rsResult.setTotalByDateList(findProduceTotalByDate(param));
        List<SSC11326RsBean> totalNameList = findProduceTotalByName(param);
        if(null != totalNameList && !totalNameList.isEmpty()) {
            //根据产品编码分组数据
            Map<String, SSC11326RsBean> map = new HashMap<String, SSC11326RsBean>();
            for(SSC11326RsBean bean: totalNameList) {
                if(!map.containsKey(bean)) {
                    if (bean.getTotalPlanNum() == null) {
                        bean.setTotalPlanNum(BigDecimal.ZERO);
                    }
                    if (bean.getTotalRealNum() == null) {
                        bean.setTotalRealNum(BigDecimal.ZERO);
                    }
                    map.put(bean.getPdName(), bean);
                }
            }
            rsResult.setTotalByNameList(map);
        }
        return rsResult;
    }

    /**
     * 按日统计
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11326RsBean> findProduceTotalByDate(SSC11326RsParam param) {
        return this.findList(SqlId.SQL_ID_FIND_PRODUCE_TOTAL_DATE, param);
    }

    /**
     * 按产品统计
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11326RsBean> findProduceTotalByName(SSC11326RsParam param) {
        return this.findList(SqlId.SQL_ID_FIND_PRODUCE_TOTAL_NAME, param);
    }

    /**
     * 入库产品管控
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11317PreIntoBean findStockProductDetail(SSC11326RsParam param) {
        List<SSC11317PrePdBean> ssc11317PrePdBeanList = this.findList(SqlId.SQL_ID_FIND_STOCK_PRODUCT_DETAIL, param);
        if (ssc11317PrePdBeanList.isEmpty()) {
            return null;
        }
        SSC11317PreIntoBean rsResult = new SSC11317PreIntoBean();
        rsResult.setSsc11317PrePdBeanList(ssc11317PrePdBeanList);
        return rsResult;
    }
    /**
     * 批量保存或更新生产商生产计划
     * @param param
     * @return
     */
    @Transactional
    public int batchSaveOrUpdateProducePlan(SSC11326RsParam param) {
        int susCount = NumberConst.IntDef.INT_ZERO;
        List<SSC11326RsBean> ssc11326RsBeans = param.getSsc11326RsBeans();
        List<SSC11326RsBean> ssc11326RsBeanList = this.findList(SqlId.SQL_ID_FIND_PRODUCE_PD_CONTROL, param);
        List<SSC11326RsBean> saveList = new ArrayList<>();
        List<SSC11326RsBean> updateList = new ArrayList<>();
        for(SSC11326RsBean bean: ssc11326RsBeans) {
            for(SSC11326RsBean realBean: ssc11326RsBeanList) {
                //排他
                if(bean.getDetailId().equals(realBean.getDetailId())
                        && bean.getProduceDateStr().equals(realBean.getProduceDateStr())
                        && bean.getType().equals(realBean.getType())) {
                    if(realBean.getVer() != null && !realBean.getVer().equals(bean.getVer())) {
                        throw new BusinessException("当前数据已经被别人修改了,请重新加载数据进行修改");
                    }
                }
            }
            if(bean.getId() == null) {
                Long maxId = commonLogic.maxId("SSC_PRODUCE_PD_CONTROL", "ID");
                bean.setId(maxId);
                saveList.add(bean);
            } else {
                updateList.add(bean);
            }
        }
        if (!saveList.isEmpty()) {
            int saveCount = this.batchSave(saveList);
            if (saveCount == NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("批量保存生产商生产计划失败");
            }
            susCount = saveCount;
        }
        if (!updateList.isEmpty()) {
            int updateCount = NumberConst.IntDef.INT_ZERO;
            for(SSC11326RsBean bean: updateList) {
                updateCount = this.modify(SqlId.SQL_ID_BATCH_UPDATE_PRODUCE_PLAN, bean);
            }
            if (updateCount == NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("批量更新生产商生产计划失败");
            }
            susCount += updateCount;
        }
        return susCount;
    }

    /**
     * 查询运输期产品管控
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11326RsResult findDeliveryPdControl(SSC11326RsParam param) {
        SSC11326RsResult ssc11326RsResult = new SSC11326RsResult();
        List<SSC1132601RsBean> deliveryPdControls = this.findList(SqlId.SQL_ID_FIND_DELIVERY_PD_CONTROL, param);
        if (!deliveryPdControls.isEmpty()) {
            ssc11326RsResult.setDeliveryPdControls(deliveryPdControls);
        }
        return ssc11326RsResult;
    }

    /**
     * 批量保存或更新运输期产品管控
     * @param param
     * @return
     */
    @Transactional
    public int batchSaveOrUpdatePdControl(SSC11326RsParam param) {
        int susCount = NumberConst.IntDef.INT_ZERO;
        List<SSC1132601RsBean> ssc1132601RsBeans = param.getDeliveryPdControls();
        List<SSC1132601RsBean> deliveryPdControls = this.findList(SqlId.SQL_ID_FIND_DELIVERY_PD_CONTROL, param);
        List<SSC1132601RsBean> saveList = new ArrayList<SSC1132601RsBean>();
        List<SSC1132601RsBean> updateList = new ArrayList<SSC1132601RsBean>();
        for(SSC1132601RsBean bean: ssc1132601RsBeans) {
            for(SSC1132601RsBean realBean: deliveryPdControls) {
                //排他
                if(bean.getContractCode().equals(realBean.getContractCode())
                        && bean.getDeliveryBatch().equals(realBean.getDeliveryBatch())) {
                    if(realBean.getVer() != null && !realBean.getVer().equals(bean.getVer())) {
                        throw new BusinessException("当前数据已经被别人修改了,请重新加载数据进行修改");
                    }
                }
            }
            if(bean.getId() == null) {
                Long maxId = commonLogic.maxId("SSC_DELIVERY_PD_CONTROL", "ID");
                bean.setId(maxId);
                saveList.add(bean);
            } else {
                updateList.add(bean);
            }
        }
        if (!saveList.isEmpty()) {
            int saveCount = this.getBaseDao().batchInsert(SqlId.SQL_ID_BATCH_SAVE_DELIVERY_PD_CONTROL, saveList);
            if (saveCount == NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("批量保存生产商运输期产品管控失败");
            }
            susCount = saveCount;
        }
        if (!updateList.isEmpty()) {
            int updateCount = NumberConst.IntDef.INT_ZERO;
            for(SSC1132601RsBean bean: updateList) {
                updateCount = this.modify(SqlId.SQL_ID_BATCH_UPDATE_DELIVERY_PD_CONTROL, bean);
            }
            if (updateCount == NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("批量更新生产商运输期产品管控失败");
            }
            susCount += updateCount;
        }
        return susCount;
    }
}
