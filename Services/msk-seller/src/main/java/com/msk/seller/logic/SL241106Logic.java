package com.msk.seller.logic;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlPdPkg;
import com.msk.core.entity.SlProduct;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.product.bean.StdItem;
import com.msk.seller.bean.*;
import com.msk.seller.utils.ISLRestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * SL241105Logic
 *
 * @author GYH
 * @version 1.0
 */
@Service
public class SL241106Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_MODIFY_AUDIT_RESULT = "modifyAuditResult";
        static final String SQL_ID_MODIFY_MONITOR_RESULT = "modifyMonitorResult";
        static final String SQL_ID_GET_PD_PKG_COUNT = "getCount";
        static final String SQL_ID_FIND_SL_PD_PKG_INFO = "findSlPdPkgInfo";
        static final String SQL_ID_FIND_SL_PD_PKG_LIST = "findSlPdPkgList";
        static final String SQL_ID_FIND_SL_PD_PKG_COUNT = "findSlPdPkgCount";
    }

    /**
     * 查询卖家产品包装信息
     *
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<ISL231109RsSlPdPkg> findSlPdPkgInfo(BaseParam param) {
        return super.findList(SqlId.SQL_ID_FIND_SL_PD_PKG_INFO, param);
    }

    /**
     * 查询卖家产品包装信息List
     *
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<ISL231109RsSlPdPkg> findSlPdPkgList(BaseParam param) {
        return super.findList(SqlId.SQL_ID_FIND_SL_PD_PKG_LIST, param);
    }


    /**
     * 重写查询卖家产品包装标准信息
     * 调用接口
     *
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public PageResult<SL241106Bean> findPage(BasePageParam param) {
        PageResult<SL241106Bean> results = super.findPage(param, SL241106Bean.class);
        List<SL241106Bean> afterDatas = this.filterResults(results.getData(), param);
        results.setData(afterDatas);
        results.setRecordsTotal(afterDatas.size());
        return results;
    }

    /**
     * 重写查询卖家产品包装标准信息
     * 调用接口
     *
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SL241106Bean> findPageList(BasePageParam param) {
        List<SL241106Bean> results = super.findPageList(param, SL241106Bean.class);
        return this.filterResults(results, param);
    }

    /**
     * 从接口过滤数据
     *
     * @param listDatas
     * @param param
     * @return
     */
    private List<SL241106Bean> filterResults(List<SL241106Bean> listDatas, BasePageParam param) {
        if (!CollectionUtils.isEmpty(listDatas)) {
            //调用产品接口 批量产品编码以及条件查询产品信息
            Map<String, Object> maps = this.getPdInfosAndNormInfos(listDatas, param);
            for (SL241106Bean bean : listDatas) {
                String key = bean.getStandardId() + bean.getPkgCode();
                PDInfoResult pdInfoResult = (PDInfoResult) maps.get(key);
                if (null != pdInfoResult) {
                    bean.setNormsSuttle(pdInfoResult.getNormsSuttle());
                    bean.setNormsError(pdInfoResult.getNormsError());
                    bean.setNormsNumber(pdInfoResult.getNormsNumber());
                    bean.setNormsSize(pdInfoResult.getNormsSize());
                    bean.setNormsTexture(pdInfoResult.getNormsTexture());
                    bean.setNormsOut(pdInfoResult.getNormsOut());
                    bean.setNormsKg(pdInfoResult.getNormsKg());
                    bean.setNormsOutSize(pdInfoResult.getNormsOutSize());
                    bean.setNormsOutTexture(pdInfoResult.getNormsOutTexture());
                    bean.setNormsLength(pdInfoResult.getNormsLength());
                    bean.setNormsWidth(pdInfoResult.getNormsWidth());
                    bean.setNormsHeight(pdInfoResult.getNormsHeight());
                    bean.setNormsVolume(pdInfoResult.getNormsVolume());
                } else {
                    bean.setNormsSuttle("");
                    bean.setNormsError("");
                    bean.setNormsNumber("");
                    bean.setNormsSize("");
                    bean.setNormsTexture("");
                    bean.setNormsOut("");
                    bean.setNormsKg("");
                    bean.setNormsOutSize("");
                    bean.setNormsOutTexture("");
                }
            }
        }
        return listDatas;
    }

    /**
     * 根据批量产品编码和包装规格编码查询产品的包装标准信息
     *
     * @param data
     * @param param
     * @return
     */
    private Map<String, Object> getPdInfosAndNormInfos(List<SL241106Bean> data, BasePageParam param) {
        PDInfoParam pdInfoParam = new PDInfoParam();
        List<StdItem> stdParams = new ArrayList<StdItem>();
        for (SL241106Bean bean : data) {
            StdItem stdItem = new StdItem();
            stdItem.setStandardId(StringUtil.toSafeString(bean.getStandardId()));
            stdItem.setNormsCode(bean.getPkgCode());
            stdParams.add(stdItem);
        }
        pdInfoParam.setStdParams(stdParams);
        RsResponse<ProductBeanResult> pdBeanResultRsResponse = ISLRestUtil.findpdNormsInfos(pdInfoParam);
        ProductBeanResult productBeanResult = pdBeanResultRsResponse.getResult();
        // 查询数据 做 map
        Map<String, Object> maps = new HashMap<String, Object>();
        if(null != productBeanResult){
            List<PDInfoResult> pdInfoResults = productBeanResult.getResult();
            if(!CollectionUtils.isEmpty(pdInfoResults)){
                for (PDInfoResult pdInfoResult : pdInfoResults) {
                    String key = pdInfoResult.getStandardId() + pdInfoResult.getNormsCode();
                    maps.put(key, pdInfoResult);
                }
            }
        }
        return maps;
    }

    /**
     * 查询卖家产品包装记录数
     *
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public int getCount(BaseParam param) {
        return this.getCount(SqlId.SQL_ID_GET_PD_PKG_COUNT, param);
    }

    /**
     * 包装标准审核
     *
     * @param slPdPkg slPdPkg
     * @return 结果
     */
    @Transactional
    public int modifyAuditResult(SlPdPkg slPdPkg) {
        return super.modify(SqlId.SQL_ID_MODIFY_AUDIT_RESULT, slPdPkg);
    }

    /**
     * 审核人审核
     *
     * @param slPdPkg slPdPkg
     * @return 结果
     */
    @Transactional
    public int modifyMonitorResult(SlPdPkg slPdPkg) {
        return super.modify(SqlId.SQL_ID_MODIFY_MONITOR_RESULT, slPdPkg);
    }

    /**
     * 校验包装标准是否存在
     * 管忠恒
     *
     * @param param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public int findSlPdPkgCount(SlProduct param) {
        return super.getCount(SqlId.SQL_ID_FIND_SL_PD_PKG_COUNT, param);
    }


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}