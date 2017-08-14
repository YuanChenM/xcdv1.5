package com.msk.seller.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.product.bean.NormsParams;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.SL241106Bean;
import com.msk.seller.bean.SL241132Bean;
import com.msk.seller.utils.ISLRestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卖家货号Logic.
 *
 * @author pxg
 */
public class Sl241132Logic extends BaseLogic {

    /**
     * 调用接口过滤数据
     *
     * @param param
     */
    @Transactional(readOnly = true)
    public List<SL241132Bean> findPageList(BasePageParam param) {
        List<SL241132Bean> results = super.findPageList(param, SL241132Bean.class);
        return this.filterResults(results);
    }

    /**
     * 调用接口过滤数据
     *
     * @param param
     */
    @Transactional(readOnly = true)
    public PageResult<SL241132Bean> findPage(BasePageParam param) {
        PageResult<SL241132Bean> results = super.findPage(param, SL241132Bean.class);
        List<SL241132Bean> afterDatas = this.filterResults(results.getData());
        results.setData(afterDatas);
        return results;
    }

    /**
     * 从接口过滤数据
     *
     * @param datas
     * @return
     */
    @Transactional(readOnly = true)
    private List<SL241132Bean> filterResults(List<SL241132Bean> datas) {
        //调用产品接口 批量产品编码以及条件查询产品信息
        Map<String, Object> maps = this.getPdInfosAndNormInfos(datas);
        Map<String, Object> pdMaps = (Map<String, Object>)maps.get("pdMaps");
        Map<String, String> normsMaps = (Map<String, String>)maps.get("normsMaps");

        // 从redis  获取 国内国外
        Map<String, String> pdCountryMap = CodeMasterManager.findCodeMasterMap(SellerConstant.PdCountry.TYPE);
        // 从redis  获取 品牌类型(卖家供应链)
        Map<String, String> saleStatusMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SaleStatus.TYPE);
        // 从redis  获取 供应商分类
        Map<String, String> salePlatformMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SalePlatform.TYPE);

        for (SL241132Bean bean : datas) {
            // 处理 pdCountryName
            if(!StringUtil.isNullOrEmpty(bean.getPdCountry())){
                String value = pdCountryMap.get(bean.getPdCountry());
                if(null != value){
                    bean.setPdCountryName(value);
                }
            }
            // 处理 saleStatusName
            if(!StringUtil.isNullOrEmpty(bean.getSaleStatus())){
                String value = saleStatusMap.get(bean.getSaleStatus());
                if(null != value){
                    bean.setSaleStatusName(value);
                }
            }
            // 处理 salePlatformName
            if(!StringUtil.isNullOrEmpty(bean.getSalePlatform())){
                String value = salePlatformMap.get(bean.getSalePlatform());
                if(null != value){
                    bean.setSalePlatformName(value);
                }
            }

            String key = bean.getClassesCode() + bean.getMachiningCode() + bean.getBreedCode()
                    + bean.getFeatureCode() + bean.getWeightCode();
            SL241132Bean sl241132Bean = (SL241132Bean)pdMaps.get(key);
            if (null != sl241132Bean) {
                bean.setPdClassesName(sl241132Bean.getPdClassesName());
                bean.setMachiningName(sl241132Bean.getMachiningName());
                bean.setPdBreedName(sl241132Bean.getPdBreedName());
                bean.setPdFeatureName(sl241132Bean.getPdFeatureName());
                bean.setWeightName(sl241132Bean.getWeightName());
            }
            key += bean.getNormsCode();
            String value = normsMaps.get(key);
            if (null != value) {
                bean.setNormsName(value);
            }
        }
        return datas;
    }

    /**
     * 根据批量产品编码和包装规格编码查询产品的包装标准信息
     *
     * @param data
     * @return
     */
    private Map<String, Object> getPdInfosAndNormInfos(List<SL241132Bean> data) {
        PDInfoParam pdInfoParam = new PDInfoParam();
        List<NormsParams> normsParamses = new ArrayList<>();
        // 准备数据
        for (SL241132Bean bean : data) {
            if (!StringUtil.isEmpty(bean.getClassesCode()) && !StringUtil.isEmpty(bean.getMachiningCode()) && !StringUtil.isEmpty(bean.getBreedCode())
                    && !StringUtil.isEmpty(bean.getFeatureCode()) && !StringUtil.isEmpty(bean.getWeightCode()) && !StringUtil.isEmpty(bean.getNormsCode())) {
                NormsParams normsParams = new NormsParams();
                normsParams.setClassesCode(bean.getClassesCode());
                normsParams.setMachiningCode(bean.getMachiningCode());
                normsParams.setBreedCode(bean.getBreedCode());
                normsParams.setFeatureCode(bean.getFeatureCode());
                normsParams.setWeightCode(bean.getWeightCode());
                normsParams.setNormsCode(bean.getNormsCode());
                normsParamses.add(normsParams);
            }
        }

        Map<String, Object> maps = new HashMap<String, Object>();
        List<PDInfoResult> result = new ArrayList<PDInfoResult>();
        List<SL241132Bean> datas = new ArrayList<SL241132Bean>();
        if (!CollectionUtils.isEmpty(normsParamses)) {
            // 根据产品编码查询产品名称
            BaseParam params = new BaseParam();
            params.getFilterMap().put("normsParamses",normsParamses);
            datas = super.findList(params);

            // 调接口
            pdInfoParam.setNormsParamses(normsParamses);
            RsResponse<ProductBeanResult> rspe = ISLRestUtil.findpdNormsInfos(pdInfoParam);
            ProductBeanResult productBeanResult = rspe.getResult();
            if (null != productBeanResult) {
                result = productBeanResult.getResult();
            }
        }
        // 拼 map
        Map<String, Object> pdMaps = new HashMap<String, Object>();
        if (!CollectionUtils.isEmpty(datas)) {
            for (SL241132Bean bean : datas) {
                String key = bean.getClassesCode() + bean.getMachiningCode() + bean.getBreedCode()
                        + bean.getFeatureCode() + bean.getWeightCode();
                pdMaps.put(key, bean);
            }
        }
        Map<String, String> normsMaps = new HashMap<String, String>();
        if (!CollectionUtils.isEmpty(result)) {
            for (PDInfoResult bean : result) {
                String value = bean.getNormsOut();
                if (!StringUtil.isEmpty(value)) {
                    String key = bean.getClassesCode() + bean.getMachiningCode() + bean.getBreedCode()
                            + bean.getFeatureCode() + bean.getWeightCode() + bean.getNormsCode();
                    normsMaps.put(key, value);
                }
            }
        }
        maps.put("pdMaps",pdMaps);
        maps.put("normsMaps",normsMaps);
        return maps;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
