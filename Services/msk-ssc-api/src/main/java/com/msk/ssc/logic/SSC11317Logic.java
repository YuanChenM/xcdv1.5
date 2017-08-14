package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.consts.StockConst;
import com.msk.core.entity.SscDeliveryPrePd;
import com.msk.ds.bean.ISC1892I1Param;
import com.msk.ds.bean.ISC1892I1RsParam;
import com.msk.ds.bean.ISC1892I1RsResult;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.ssc.bean.*;
import com.msk.ssc.rest.common.SSCRestUtil;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;
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
 * Created by wang_shuai on 2016/7/8.
 */
@Service
public class SSC11317Logic extends BaseLogic {
    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(SSC11317Logic.class);


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_FIND_DELIVERY_INTO_INFO = "findDeliveryIntoInfo";//查询预入库通知单基本信息
        String SQL_ID_MODIFY_DELIVERY_INTO_INFO = "modifyDeliveryIntoInfo";//修改预入库单基本信息
        String SQL_ID_UPDATE_DELIVERY_INTO_INFO = "updatePreInto";//更新预入库单中的实际信息
        String SQL_ID_UPDATE_DELIVERY_INTO_PD = "updatePrePd";//更新预入库单中产品的实际信息
        String SQL_ID_UPDATE_DELIVERY_INTO_INFO_RECVSTATUS = "updatePreIntoRecvStatus";//更新预入库单收货状态
        String SQL_ID_MODIFY_STATUS = "modifyStatus";//确认发车时更新预入库状态为在途
        String SQL_ID_SAVE_PRE_INTO_FILE = "savePreIntoFile";//插入预入库文件信息
        String SQL_ID_FIND_LIST_BY_DELIVERY_ID = "findListByDeliveryId";//查询发货订单关联的预入库单基本信息
        String SQL_ID_FIND_PRODUCT_RECV_STATUS = "findProductRecvStatus";//查询应该插入的产品收货状态
    }

    /**
     * 查询预入库通知单基本信息
     *
     * @param ssc11317RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11317PreIntoBean findDeliveryIntoInfo(SSC11317RsParam ssc11317RsParam) {
        SSC11317PreIntoBean ssc11317PreIntoBean = this.findOne(SqlId.SQL_ID_FIND_DELIVERY_INTO_INFO, ssc11317RsParam);
        return ssc11317PreIntoBean;
    }

    /**
     * 查询发货订单关联的预入库单基本信息
     *
     * @param ssc11317RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11317PreIntoBean> findListByDeliveryId(SSC11317RsParam ssc11317RsParam) {
        List<SSC11317PreIntoBean> list = this.findList(SqlId.SQL_ID_FIND_LIST_BY_DELIVERY_ID, ssc11317RsParam);
        return list;
    }

    /**
     * 修改预入库单基本信息
     *
     * @param ssc11317RsParam
     * @return
     */
    @Transactional
    public int modifyDeliveryIntoInfo(SSC11317RsParam ssc11317RsParam) {
        //检查数据版本是否正确
        if(ssc11317RsParam.getDeliveryPreIntoId()!=null&&ssc11317RsParam.getVer()!=null){
            super.versionValidator("SSC_DELIVERY_PRE_INTO",new String[]{"DELIVERY_PRE_INTO_ID"},new Object[]{ssc11317RsParam.getDeliveryPreIntoId()},ssc11317RsParam.getVer());
        }
        int successCount = super.modify(SqlId.SQL_ID_MODIFY_DELIVERY_INTO_INFO, ssc11317RsParam);
        return successCount;
    }

    /**
     * 更新预入库单中的实际信息
     *
     * @param bean
     * @return
     */
    @Transactional
    public int updatePreInto(SSC11317PreIntoBean bean) {
        //检查数据版本是否正确
//            super.versionValidator("SSC_DELIVERY_PRE_PD", new String[]{"DETAIL_ID"}, new Object[]{bean.getDetailId()}, bean.getVer());
        return this.modify(SqlId.SQL_ID_UPDATE_DELIVERY_INTO_INFO, bean);
    }

    /**
     * 批量修改预入库单产品基本信息
     *
     * @param prePdList
     * @return
     */
    @Transactional
    public int batchUpdatePrePd(List<SscDeliveryPrePd> prePdList) {
        int result = 0;
        for (SscDeliveryPrePd prePd : prePdList) {
            //检查数据版本是否正确
//                super.versionValidator("SSC_DELIVERY_PRE_PD", new String[]{"DETAIL_ID"}, new Object[]{prePd.getDetailId()}, prePd.getVer());
            result += this.modify(SqlId.SQL_ID_UPDATE_DELIVERY_INTO_PD, prePd);
        }
        return result;
    }

    /**
     * 更新预入库单中的收获状态信息
     *
     * @param bean
     * @return
     */
    @Transactional
    public int updatePreIntoRecvStatus(SSC11317PreIntoBean bean) {
        //检查数据版本是否正确
        //super.versionValidator("SSC_DELIVERY_PRE_INTO",new String[]{"DELIVERY_PRE_INTO_ID"},new Object[]{bean.getDeliveryPreIntoId()},bean.getVer());
        return this.modify(SqlId.SQL_ID_UPDATE_DELIVERY_INTO_INFO_RECVSTATUS, bean);
    }

    /**
     * 确认发车时更新收货状态为在途
     *
     * @param ssc11317RsParam
     * @return
     */
    @Transactional
    public int updateProductRecStatus(SSC11317RsParam ssc11317RsParam) {
        int updateCount = this.modify(SqlId.SQL_ID_MODIFY_STATUS, ssc11317RsParam);
        return updateCount;
    }

    /**
     * 插入预入库文件信息
     *
     * @param bean
     * @return
     */
    @Transactional
    public int savePreIntoFile(SSC11317PreIntoBean bean) {
        int updateCount = this.modify(SqlId.SQL_ID_SAVE_PRE_INTO_FILE, bean);
        return updateCount;
    }

    /**
     * 修改预入库实际入库数据 以及更新销售库存
     */
    @Transactional
    public ISC1892I1RsResult modifyStock(RsRequest<ISC1892I1Param> param) {
        ISC1892I1RsResult isc1892I1RsResult = new ISC1892I1RsResult();
        ISC1892I1Param bean = param.getParam();

        List<ISC1892I1RsParam> isc1892I1RsParams = bean.getProductList();
        if (CollectionUtils.isEmpty(isc1892I1RsParams)){
            throw new BusinessException("没有入库的产品信息");
        }

        List<String> pdCodes = new ArrayList<String>();
        //处理入库单基本信息
        SSC11317PreIntoBean preInto = new SSC11317PreIntoBean();
        preInto.setDeliveryPreIntoCode(bean.getDeliveryStockId());
        preInto.setRemark(bean.getStockMemo());
        preInto.setArriveDateStr(bean.getInputDate());
        preInto.setUpdId(bean.getUpdId());
        preInto.setUpdTime(bean.getUpdTime());

        //处理入库单产品信息
        SSC11317RsParam ssc11317RsParam = new SSC11317RsParam();
        ssc11317RsParam.setDeliveryPreIntoCode(bean.getDeliveryStockId());
        ssc11317RsParam.setPaging(false);
        List<SscDeliveryPrePd> prePdList = this.findPageList(ssc11317RsParam, SscDeliveryPrePd.class);
        if (CollectionUtils.isNotEmpty(prePdList) && CollectionUtils.isNotEmpty(isc1892I1RsParams)) {
            for(SscDeliveryPrePd prePd: prePdList) {
                prePd.setProductRecvBox(NumberConst.IntDef.INT_ZERO);
                prePd.setUpdId(preInto.getUpdId());
                prePd.setUpdTime(preInto.getUpdTime());
                for (ISC1892I1RsParam isc1892I1RsParam : isc1892I1RsParams) {
                    if (isc1892I1RsParam.getPdCode().equals(prePd.getPdCode())) {
                        prePd.setProductRecvBox(isc1892I1RsParam.getReceiveActualNum().intValue());
                        pdCodes.add(isc1892I1RsParam.getPdCode());
                        break;
                    }
                }
            }
        }
        if (CollectionUtils.isEmpty(prePdList)){
            throw new BusinessException("没有需要入库的产品信息");
        }

        int susCountPreInto = this.updatePreInto(preInto);
        if (susCountPreInto == NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该入库单号信息不存在");
        }

        int susCountPrePd = this.batchUpdatePrePd(prePdList);
        if (susCountPrePd == NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该入库单无对应的产品信息");
        }

        //更新预入库收货状态
        Integer productRecvStatus = (Integer)this.getBaseDao().getSqlSession().selectOne(SqlId.SQL_ID_FIND_PRODUCT_RECV_STATUS, preInto);
        preInto.setProductRecvStatus(productRecvStatus);
        int susCountRecvStatus = this.updatePreIntoRecvStatus(preInto);
        if (susCountRecvStatus == NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("更新预入库收货状态失败");
        }

        //返回产品收货状态
        isc1892I1RsResult.setDeliveryStockStatus(StringUtil.toSafeString(productRecvStatus));

        SSC11317PreIntoBean ssc11317PreIntoBean = this.findDeliveryIntoInfo(ssc11317RsParam);

        //获取入库产品基本信息
        Map<String, PDInfoResult> realPdMap = this.getProductInfoList(pdCodes);

        //调用接口 更新库存
        List<StockRsParam> pdList = new ArrayList<StockRsParam>();
        for(ISC1892I1RsParam isc1892I1RsParam : isc1892I1RsParams) {
            PDInfoResult pdBean = realPdMap.get(isc1892I1RsParam.getPdCode());
            StockRsParam stockParam = new StockRsParam();
            stockParam.setLgcsCode(ssc11317PreIntoBean.getLgcsAreaCode());
            stockParam.setLgcsName(ssc11317PreIntoBean.getLgcsAreaName());
            stockParam.setWarehouseCode(StockConst.WarehouseCode.SH_CODE);
            stockParam.setWarehouseName(StockConst.WarehouseName.SH_NAME);
            stockParam.setSupplyPlatform(String.valueOf(NumberConst.IntDef.INT_ONE));
            stockParam.setSlCode(ssc11317PreIntoBean.getPurchaserCode());
            stockParam.setSlName(ssc11317PreIntoBean.getPurchaserName());
            stockParam.setStockType(String.valueOf(StockConst.StockType.NORMAL_STOCK));
            stockParam.setSupplierCode(ssc11317PreIntoBean.getPurchaserCode());
            stockParam.setSupplierName(ssc11317PreIntoBean.getPurchaserName());
            stockParam.setPdCode(isc1892I1RsParam.getPdCode());
            stockParam.setStockNum(isc1892I1RsParam.getReceiveActualNum());
            stockParam.setFlowId(String.valueOf(preInto.getDeliveryPreIntoCode()));
            // 库存接口新增必填参数enabledStockQty,默认设置为0
            stockParam.setEnabledStockQty(BigDecimal.ZERO);
            stockParam.setUnit("箱");
            if(pdBean != null) {
                stockParam.setClassesCode(pdBean.getClassesCode());
                stockParam.setClassesName(pdBean.getClassesName());
                stockParam.setBreedCode(pdBean.getBreedCode());
                stockParam.setBreedName(pdBean.getBreedName());
                stockParam.setFeatureCode(pdBean.getFeatureCode());
                stockParam.setFeatureName(pdBean.getFeatureName());
                stockParam.setNormsCode(pdBean.getNormsCode());
                stockParam.setNormsName(pdBean.getNormsName());
                stockParam.setPdLevel(pdBean.getGradeCode());
                stockParam.setWeight(StringUtil.toString(pdBean.getWeightVal()));
                stockParam.setPackingVolume(StringUtil.toString(pdBean.getNormsVolume()));
                stockParam.setVolume(StringUtil.toString(pdBean.getNormsVolume()));
                stockParam.setPdName(pdBean.getPdName());
            }
            pdList.add(stockParam);
        }
        StockRsParamList stockRsParamList = new StockRsParamList();
        stockRsParamList.setPdList(pdList);
        RsResponse<StockResult> stockResultRsResponse = SSCRestUtil.saveStockOfSupplierList(stockRsParamList);
        if (SystemConst.RsStatus.FAIL.equals(stockResultRsResponse.getStatus())) {
            throw new BusinessException("同步库存失败");
        }
        return isc1892I1RsResult;
    }

    private Map<String, PDInfoResult> getProductInfoList(List<String> pdCodes) {
        Map<String, PDInfoResult> map = new HashMap<>();
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setPdCodes(pdCodes);
        pdInfoParam.setGradeFlag("YES");
        List<PDInfoResult>  results = SSCRestUtil.getProductInfoList(pdInfoParam);

        for(PDInfoResult pdBean: results) {
            if (!map.containsKey(pdBean.getPdCode())) {
                map.put(pdBean.getPdCode(), pdBean);
            }
        }
        return map;
    }
}
