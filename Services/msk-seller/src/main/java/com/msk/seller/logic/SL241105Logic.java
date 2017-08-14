package com.msk.seller.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.core.entity.SlProduct;
import com.msk.product.bean.*;
import com.msk.seller.bean.SL241105Bean;
import com.msk.seller.bean.SL241118Bean;
import com.msk.seller.utils.ISLRestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SL241105Logic
 *
 * @author jiang_nan
 * @version 1.0
 */
@Service
public class SL241105Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(SL241105Logic.class);

    /**
     * 取得卖家产品加工技术标准详细页面数据
     * 需要调用接口
     *
     * @param param param
     * @return 加工技术标准数据
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<SL241118Bean> getMctStd(BaseParam param) {
        List<SL241118Bean> results = super.findList(SqlId.SQL_ID_GET_STD_INFO, param);
        if (results == null || results.size() == 0) {
            return results;
        }
        List<StdItem> stdItemList = new ArrayList<>();
        for (SL241118Bean bean : results) {
            StdItem item = new StdItem();
            item.setStandardId(StringUtil.toSafeString(bean.getStandardId()));
            item.setStdItemId(bean.getStdItemId());
            stdItemList.add(item);
        }

        RsResponse<ProductStdResult> response = ISLRestUtil.getPdProductStd(stdItemList, null, 1);
        ProductStdResult result = response.getResult();
        if(result == null){
            result = new ProductStdResult();
        }
        List<MctStdBean> mctStdList = result.getMctStdList();
        Map<String, Object> maps = new HashMap<String, Object>();
        if (!CollectionUtils.isEmpty(mctStdList)) {
            for (MctStdBean mctStdBean : mctStdList) {
                String key = mctStdBean.getMctStdItemId() + mctStdBean.getStandardId();
                maps.put(key, mctStdBean);
            }
        }

        List<SL241118Bean> afterResults = new ArrayList<>();
        for (SL241118Bean bean : results) {
            String key = bean.getStdItemId() + bean.getStandardId();
            MctStdBean mctStdBean = (MctStdBean) maps.get(key);
            if (null != mctStdBean) {
                bean.setStandardId(mctStdBean.getStandardId());
                bean.setMctStdItemId(mctStdBean.getMctStdItemId());
                bean.setMctOkVal(mctStdBean.getMctOkVal());
                bean.setMctNgVal(mctStdBean.getMctNgVal());
                bean.setRemark(mctStdBean.getRemark());
                bean.setMctStdItemName(mctStdBean.getMctStdItemName());
                bean.setLevelId(mctStdBean.getLevelId());
                bean.setParentId(mctStdBean.getParentId());
                bean.setIsCatalog(mctStdBean.getIsCatalog());
                afterResults.add(bean);
            }
        }
        return afterResults;

    }

    /**
     * 卖家产品技术审核
     *
     * @param param param
     * @return 结果
     * @author gyh
     */
    @Transactional(readOnly = false)
    public int modifySlQlt(SlProduct param) {
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        return this.modify(SqlId.SQL_ID_MODIFY_SL_QLT, param);
    }

    /**
     * 卖家产品卫生定级
     *
     * @param param param
     * @return 结果
     * @author gyh
     */
    @Transactional(readOnly = false)
    public int modifySlTncGrade(SlProduct param) {
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        return this.modify(SqlId.SQL_ID_MODIFY_SL_TNC_GRADE, param);
    }

    /**
     * 卖家产品卫生审核
     *
     * @param param param
     * @return 结果
     * @author gyh
     */
    @Transactional(readOnly = false)
    public int modifySlTncMonitor(SlProduct param) {
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        return this.modify(SqlId.SQL_ID_MODIFY_SL_TNC_MONITOR, param);
    }

    /**
     * 调用产品接口获得 根据产品分类code查询产品分类原料
     *
     * @param pageParam
     * @return
     */
    public PdClassestreeMat findOne(BasePageParam pageParam) {
        PDInfoParam param = new PDInfoParam();
        List<String> classTreeCodes = new ArrayList<>();
        classTreeCodes.add(StringUtil.toSafeString(pageParam.getFilterMap().get("classesTreeCode")));
        param.setClassesTreeCodes(classTreeCodes);
        PdClassestreeMat pdClassestreeMat = new PdClassestreeMat();
        RsResponse<ProductBeanResult> results = ISLRestUtil.getPdClassesTreeMatInfo(param);
        ProductBeanResult productBeanResult = results.getResult();
        List<PdClassestreeMat> pdMats = productBeanResult.getTreeMatList();
        if (pdMats.size() > 0) {
            pdClassestreeMat = pdMats.get(0);
        }
        return pdClassestreeMat;
    }

    /**
     * 查询分页
     *
     * @param
     * @return
     * @author
     */
    @Transactional(readOnly = true)
    public PageResult<SL241105Bean> findPageResult(BasePageParam basePageParam){
        PageResult<SL241105Bean>  result =  this.findPage(basePageParam, SL241105Bean.class);
        if(result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO){
            List<SL241105Bean>  lists = result.getData();
            // 从redis  获取 产品技术标准定级(加工质量标准)
            Map<String, String> slTncGradeCodeMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlTncGradeCode.TYPE);
            // 从redis  获取 产品质量标准定级
            Map<String, String> slQltGradeCodeMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlQltGradeCode.TYPE);

            for(SL241105Bean bean : lists){
                // 处理 slTncGradeName
                if(!StringUtil.isNullOrEmpty(bean.getSlTncGradeCode()+"")){
                    String value = slTncGradeCodeMap.get(bean.getSlTncGradeCode()+"");
                    if(null != value){
                        bean.setSlTncGradeName(value);
                    }
                }
                // 处理 slQltGradeName
                if(!StringUtil.isNullOrEmpty(bean.getSlQltGradeCode()+"")){
                    String value = slQltGradeCodeMap.get(bean.getSlQltGradeCode()+"");
                    if(null != value){
                        bean.setSlQltGradeName(value);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 查询分页数据List
     *
     * @param
     * @return
     * @author
     */
    @Transactional(readOnly = true)
    public List<SL241105Bean> findPageResultList(BasePageParam basePageParam){
        List<SL241105Bean>  lists =  this.findPageList(basePageParam, SL241105Bean.class);
        if(CollectionUtils.isNotEmpty(lists)){
            // 从redis  获取 产品技术标准定级(加工质量标准)
            Map<String, String> slTncGradeCodeMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlTncGradeCode.TYPE);
            // 从redis  获取 产品质量标准定级
            Map<String, String> slQltGradeCodeMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlQltGradeCode.TYPE);

            for(SL241105Bean bean : lists){
                // 处理 slTncGradeName
                if(!StringUtil.isNullOrEmpty(bean.getSlTncGradeCode()+"")){
                    String value = slTncGradeCodeMap.get(bean.getSlTncGradeCode()+"");
                    if(null != value){
                        bean.setSlTncGradeName(value);
                    }
                }
                // 处理 slQltGradeName
                if(!StringUtil.isNullOrEmpty(bean.getSlQltGradeCode()+"")){
                    String value = slQltGradeCodeMap.get(bean.getSlQltGradeCode()+"");
                    if(null != value){
                        bean.setSlQltGradeName(value);
                    }
                }
            }
        }
        return lists;
    }




    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_MODIFY_SL_QLT = "modifySlQlt";
        static final String SQL_ID_MODIFY_SL_TNC_GRADE = "modifySlTncGrade";
        static final String SQL_ID_MODIFY_SL_TNC_MONITOR = "modifySlQltMonitor";
        static final String SQL_ID_GET_STD_INFO = "getMctStd";
    }
}