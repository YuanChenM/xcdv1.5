package com.msk.seller.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.*;
import com.msk.seller.bean.*;
import com.msk.seller.utils.ISLRestUtil;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gyh on 2016/2/29.
 */
@Service
public class ISL231109RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private Sl241116Logic sl241116Logic;

    @Autowired
    private SL241106Logic sL241106Logic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_GET_MCT_STD_INFO = "getMctStdInfo";
        static final String SQL_ID_GET_TNC_STD_INFO = "getTncStdInfo";
        static final String SQL_ID_GET_ORG_STD_INFO = "getOrgStdInfo";
        static final String SQL_ID_GET_FED_STD_INFO = "getFedStdInfo";
        static final String SQL_ID_GET_GNQ_STD_INFO = "getGnqStdInfo";
        static final String SQL_ID_GET_TSP_STD_INFO = "getTspStdInfo";
        static final String SQL_ID_GET_SFT_STD_INFO = "getSftStdInfo";
    }

    /**
     * 查询卖家产品标准信息
     *
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<ISL231109RsProduct> findSlProduct(ISL231109RsParam param) {
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.setFilter("slCode", param.getSlCode());
        basePageParam.setFilter("prodEpId", StringUtil.toSafeString(param.getProdEpId()));
        basePageParam.setFilter("brandEpId", StringUtil.toSafeString(param.getBrandEpId()));
        basePageParam.setFilter("brandId", StringUtil.toSafeString(param.getBrandId()));
        basePageParam.setFilter("pdClassesCode", param.getPdClassesCode());
        basePageParam.setFilter("machiningCode", param.getMachiningCode());
        basePageParam.setFilter("pdBreedCode", param.getPdBreedCode());
        basePageParam.setFilter("pdFeatureCode", param.getPdFeatureCode());
        basePageParam.setFilter("weightCode", param.getWeightCode());
        List<SL241116Bean> sl241116Beans = sl241116Logic.findPageResultList(basePageParam);
        // 处理卖家产品标准信息
        List<ISL231109RsProduct> results = findSlProductInfo(sl241116Beans, param);
        return results;
    }

    /**
     * 处理卖家产品标准信息
     *
     * @param param
     * @return 结果
     * @sl241116Beans sl241116Beans 参数
     */
    @Transactional
    private List<ISL231109RsProduct> findSlProductInfo(List<SL241116Bean> sl241116Beans, ISL231109RsParam param) {

        List<ISL231109RsProduct> results = new ArrayList<ISL231109RsProduct>();
        if (!CollectionUtils.isEmpty(sl241116Beans) && sl241116Beans.size() > 0) {
            List<String> slPdIdlists = new ArrayList<String>();
            List<ISL231109RsSlPdPkg> slPdPkgList = new ArrayList<ISL231109RsSlPdPkg>();

            for (SL241116Bean bean : sl241116Beans) {
                ISL231109RsProduct result = new ISL231109RsProduct();
                result.setPdStatus(bean.getStatus());
                result.setSlCode(bean.getSlCode());
                result.setSlPdId(bean.getSlPdId());
                result.setProdEpId(bean.getProdEpId());
                result.setProdEpName(bean.getProdEpName());
                result.setBrandEpId(bean.getBrandEpId());
                result.setBrandEpName(bean.getBrandEpName());
                result.setBrandId(bean.getBrandId());
                result.setBrandName(bean.getBrandName());
                result.setPdClassesCode(bean.getPdClassesCode());
                result.setPdClassesName(bean.getPdClassesName());
                result.setMachiningCode(bean.getMachiningCode());
                result.setMachiningName(bean.getMachiningName());
                result.setPdBreedCode(bean.getPdBreedCode());
                result.setPdBreedName(bean.getPdBreedName());
                result.setPdFeatureCode(bean.getPdFeatureCode());
                result.setPdFeatureName(bean.getPdFeatureName());
                result.setWeightCode(bean.getWeightCode());
                result.setWeightName(bean.getWeightName());
                result.setDistFlg(bean.getDistFlg());
                result.setDistmskFlg(bean.getDistmskFlg());
                result.setSlQltStd(bean.getSlQltStd());
                result.setSlQltGradeCode(bean.getSlQltGradeCode());
                result.setQltNgReason(bean.getQltNgReason());
                result.setQltAuditStatus(bean.getQltAuditStatus());
                result.setQltAuditor(bean.getQltAuditor());
                result.setQltAuditDate(bean.getQltAuditDate());
                result.setQltMonitorResult(bean.getQltMonitorResult());
                result.setQltMonitorAuditor(bean.getQltMonitorAuditor());
                result.setQltMonitorDate(bean.getQltMonitorDate());
                result.setSlTncStd(bean.getSlTncStd());
                result.setSlTncGradeCode(bean.getSlTncGradeCode());
                result.setTncNgReason(bean.getTncNgReason());
                result.setTncAuditStatus(bean.getTncAuditStatus());
                result.setTncAuditor(bean.getTncAuditor());
                result.setTncAuditDate(bean.getTncAuditDate());
                result.setTncMonitorResult(bean.getTncMonitorResult());
                result.setTncMonitorAuditor(bean.getTncMonitorAuditor());
                result.setTncMonitorDate(bean.getTncMonitorDate());
                result.setVer(bean.getVer());

                String slPdId = StringUtil.toSafeString(bean.getSlPdId());
                if (!StringUtil.isNullOrEmpty(slPdId)) {
                    slPdIdlists.add(slPdId);
                }

                ISL231109RsSlPdPkg isl231109RsSlPdPkg = new ISL231109RsSlPdPkg();
                isl231109RsSlPdPkg.setSlCode(bean.getSlCode());
                isl231109RsSlPdPkg.setSlPdId(bean.getSlPdId());
                slPdPkgList.add(isl231109RsSlPdPkg);

                results.add(result);
            }

            Map<String, Object> maps = new HashMap<String, Object>();
            // 查询卖家产品包装信息List
            BaseParam baseParam = new BaseParam();
            baseParam.getFilterMap().put("slPdPkgList", slPdPkgList);
            List<ISL231109RsSlPdPkg> slPdPkgReLists = sL241106Logic.findSlPdPkgList(baseParam);
            Map<String, Object> slPdIdMap = new HashMap<String, Object>();
            if (null != slPdPkgReLists && slPdPkgReLists.size() > 0) {
                for (ISL231109RsSlPdPkg bean : slPdPkgReLists) {
                    String key = bean.getSlCode() + bean.getSlPdId();
                    List<ISL231109RsSlPdPkg> listsV = (List<ISL231109RsSlPdPkg>) slPdIdMap.get(key);
                    if (null == listsV) {
                        listsV = new ArrayList<ISL231109RsSlPdPkg>();
                    }
                    listsV.add(bean);
                    slPdIdMap.put(key, listsV);
                }
            }
            maps.put("slPdIdMap", slPdIdMap);

            // 7个子方法接口处理
            if (null != slPdIdlists && slPdIdlists.size() > 0) {
                BaseParam params = new BaseParam();
                params.getFilterMap().put("slPdIdlists", slPdIdlists);
                // 准备 产品加工质量标准数据
                List<SL241117Bean> sl241117Beans = getTncStd(params);
                // 准备 加工技术标准详细页面数据
                List<SL241118Bean> sl241118Beans = getMctStd(params);
                // 准备 卖家产品原种种源标准数据
                params.getFilterMap().put("stdFlg", 1);
                List<SL241122Bean> sl241122Beans = getOrgStdInfo(params);
                // 准备 卖家产品饲养标准数据
                params.getFilterMap().put("stdFlg", 2);
                List<SL241123Bean> sl241123Beans = getFedStdInfo(params);
                // 准备 卖家产品通用质量标准数据
                params.getFilterMap().put("stdFlg", 3);
                List<SL241124Bean> sl241124Beans = getGnqStdInfo(params);
                // 准备 卖家产品储存运输标准数据
                params.getFilterMap().put("stdFlg", 4);
                List<SL241125Bean> sl241125Beans = getTspStdInfo(params);
                // 准备 卖家产品安全标准数据
                params.getFilterMap().put("stdFlg", 5);
                List<SL241126Bean> sl241126Beans = getSftStdInfo(params);

                // 处理 产品加工质量标准数据
                Map<String, Object> sl241117BeansLMap = new HashMap<String, Object>();
                if (null != sl241117Beans && sl241117Beans.size() > 0) {
                    for (SL241117Bean sl241117BeanM : sl241117Beans) {
                        // 准备数据map
                        String slPdId = sl241117BeanM.getSlPdId() + "";
                        List<SL241117Bean> listsV = (List<SL241117Bean>) sl241117BeansLMap.get(slPdId);
                        if (null == listsV) {
                            listsV = new ArrayList<SL241117Bean>();
                        }
                        listsV.add(sl241117BeanM);
                        sl241117BeansLMap.put(slPdId, listsV);
                    }
                }
                // 处理 加工技术标准详细页面数据
                Map<String, Object> sl241118BeansLMap = new HashMap<String, Object>();
                if (null != sl241118Beans && sl241118Beans.size() > 0) {
                    for (SL241118Bean sl241118BeanM : sl241118Beans) {
                        // 准备数据map
                        String slPdId = sl241118BeanM.getSlPdId() + "";
                        List<SL241118Bean> listsV = (List<SL241118Bean>) sl241118BeansLMap.get(slPdId);
                        if (null == listsV) {
                            listsV = new ArrayList<SL241118Bean>();
                        }
                        listsV.add(sl241118BeanM);
                        sl241118BeansLMap.put(slPdId, listsV);
                    }
                }
                // 处理 卖家产品原种种源标准数据
                Map<String, Object> sl241122BeansLMap = new HashMap<String, Object>();
                if (null != sl241122Beans && sl241122Beans.size() > 0) {
                    for (SL241122Bean sl241122BeanM : sl241122Beans) {
                        // 准备数据map
                        String slPdId = sl241122BeanM.getSlPdId()+"";
                        List<SL241122Bean> listsV = (List<SL241122Bean>) sl241122BeansLMap.get(slPdId);
                        if (null == listsV) {
                            listsV = new ArrayList<SL241122Bean>();
                        }
                        listsV.add(sl241122BeanM);
                        sl241122BeansLMap.put(slPdId, listsV);
                    }
                }
                // 处理 卖家产品饲养标准数据
                Map<String, Object> sl241123BeansLMap = new HashMap<String, Object>();
                if (null != sl241123Beans && sl241123Beans.size() > 0) {
                    for (SL241123Bean sl241123BeanM : sl241123Beans) {
                        // 准备数据map
                        String slPdId = sl241123BeanM.getSlPdId() + "";
                        List<SL241123Bean> listsV = (List<SL241123Bean>) sl241123BeansLMap.get(slPdId);
                        if (null == listsV) {
                            listsV = new ArrayList<SL241123Bean>();
                        }
                        listsV.add(sl241123BeanM);
                        sl241123BeansLMap.put(slPdId, listsV);
                    }
                }
                // 处理 卖家产品通用质量标准数据
                Map<String, Object> sl241124BeansLMap = new HashMap<String, Object>();
                if (null != sl241124Beans && sl241124Beans.size() > 0) {
                    for (SL241124Bean sl241124BeanM : sl241124Beans) {
                        // 准备数据map
                        String slPdId = sl241124BeanM.getSlPdId() + "";
                        List<SL241124Bean> listsV = (List<SL241124Bean>) sl241124BeansLMap.get(slPdId);
                        if (null == listsV) {
                            listsV = new ArrayList<SL241124Bean>();
                        }
                        listsV.add(sl241124BeanM);
                        sl241124BeansLMap.put(slPdId, listsV);
                    }
                }
                // 处理 卖家产品储存运输标准数据
                Map<String, Object> sl241125BeansLMap = new HashMap<String, Object>();
                if (null != sl241125Beans && sl241125Beans.size() > 0) {
                    for (SL241125Bean sl251125BeanM : sl241125Beans) {
                        // 准备数据map
                        String slPdId = sl251125BeanM.getSlPdId() + "";
                        List<SL241125Bean> listsV = (List<SL241125Bean>) sl241125BeansLMap.get(slPdId);
                        if (null == listsV) {
                            listsV = new ArrayList<SL241125Bean>();
                        }
                        listsV.add(sl251125BeanM);
                        sl241125BeansLMap.put(slPdId, listsV);
                    }
                }
                // 处理 卖家产品安全标准数据
                Map<String, Object> sl241126BeansLMap = new HashMap<String, Object>();
                if (null != sl241126Beans && sl241126Beans.size() > 0) {
                    for (SL241126Bean sl261126BeanM : sl241126Beans) {
                        // 准备数据map
                        String slPdId = sl261126BeanM.getSlPdId() + "";
                        List<SL241126Bean> listsV = (List<SL241126Bean>) sl241126BeansLMap.get(slPdId);
                        if (null == listsV) {
                            listsV = new ArrayList<SL241126Bean>();
                        }
                        listsV.add(sl261126BeanM);
                        sl241126BeansLMap.put(slPdId, listsV);
                    }
                }
                maps.put("sl241117BeansLMap", sl241117BeansLMap);
                maps.put("sl241118BeansLMap", sl241118BeansLMap);
                maps.put("sl241122BeansLMap", sl241122BeansLMap);
                maps.put("sl241123BeansLMap", sl241123BeansLMap);
                maps.put("sl241124BeansLMap", sl241124BeansLMap);
                maps.put("sl241125BeansLMap", sl241125BeansLMap);
                maps.put("sl241126BeansLMap", sl241126BeansLMap);
            }

            if (null != maps && maps.size() > 0) {
                results = appendAllData(results, maps);
            }
        }
        return results;
    }

    /**
     * 七个子方法数据合并
     *
     * @param results
     * @param maps
     * @return
     */
    @Transactional
    private List<ISL231109RsProduct> appendAllData(List<ISL231109RsProduct> results, Map<String, Object> maps) {
        Map<String, Object> slPdIdMap = (Map<String, Object>) maps.get("slPdIdMap");
        Map<String, Object> sl241117BeansLMap = (Map<String, Object>) maps.get("sl241117BeansLMap");
        Map<String, Object> sl241118BeansLMap = (Map<String, Object>) maps.get("sl241118BeansLMap");
        Map<String, Object> sl241122BeansLMap = (Map<String, Object>) maps.get("sl241122BeansLMap");
        Map<String, Object> sl241123BeansLMap = (Map<String, Object>) maps.get("sl241123BeansLMap");
        Map<String, Object> sl241124BeansLMap = (Map<String, Object>) maps.get("sl241124BeansLMap");
        Map<String, Object> sl241125BeansLMap = (Map<String, Object>) maps.get("sl241125BeansLMap");
        Map<String, Object> sl241126BeansLMap = (Map<String, Object>) maps.get("sl241126BeansLMap");
        for (ISL231109RsProduct bean : results) {
            String slPdId = StringUtil.toSafeString(bean.getSlPdId());
            if (!StringUtil.isNullOrEmpty(slPdId)) {
                List<ISL231109RsSlPdPkg> slPdPkgReLists = (List<ISL231109RsSlPdPkg>) slPdIdMap
                    .get(bean.getSlCode() + slPdId);
                if (null != slPdPkgReLists && slPdPkgReLists.size() > 0) {
                    bean.setSlPdPkgList(slPdPkgReLists);
                }

                List<SL241117Bean> sl241117BeanList = (List<SL241117Bean>) sl241117BeansLMap.get(slPdId);
                if (null != sl241117BeanList && sl241117BeanList.size() > 0) {
                    bean.setSlPdTncStdList(sl241117BeanList);
                }

                List<SL241118Bean> sl241118BeanList = (List<SL241118Bean>) sl241118BeansLMap.get(slPdId);
                if (null != sl241118BeanList && sl241118BeanList.size() > 0) {
                    bean.setSlPdMctStdList(sl241118BeanList);
                }

                List<SL241122Bean> sl241122BeanList = (List<SL241122Bean>) sl241122BeansLMap.get(slPdId);
                if (null != sl241122BeanList && sl241122BeanList.size() > 0) {
                    bean.setSlPdOrgStdList(sl241122BeanList);
                }

                List<SL241123Bean> sl241123BeanList = (List<SL241123Bean>) sl241123BeansLMap.get(slPdId);
                if (null != sl241123BeanList && sl241123BeanList.size() > 0) {
                    bean.setSlPdFedStdList(sl241123BeanList);
                }

                List<SL241124Bean> sl241124BeanList = (List<SL241124Bean>) sl241124BeansLMap.get(slPdId);
                if (null != sl241124BeanList && sl241124BeanList.size() > 0) {
                    bean.setSlPdGnqStdList(sl241124BeanList);
                }else {
                    List<SL241124Bean> sl241124BeanListNu = (List<SL241124Bean>) sl241124BeansLMap.get("null");
                    if (null != sl241124BeanListNu && sl241124BeanListNu.size() > 0) {
                        bean.setSlPdGnqStdList(sl241124BeanListNu);
                    }
                }

                List<SL241125Bean> sl241125BeanList = (List<SL241125Bean>) sl241125BeansLMap.get(slPdId);
                if (null != sl241125BeanList && sl241125BeanList.size() > 0) {
                    bean.setSlPdTspStdList(sl241125BeanList);
                }else {
                    List<SL241125Bean> sl241125BeanListNu = (List<SL241125Bean>) sl241125BeansLMap.get("null");
                    if (null != sl241125BeanListNu && sl241125BeanListNu.size() > 0) {
                        bean.setSlPdTspStdList(sl241125BeanListNu);
                    }
                }

                List<SL241126Bean> sl241126BeanList = (List<SL241126Bean>) sl241126BeansLMap.get(slPdId);
                if (null != sl241126BeanList && sl241126BeanList.size() > 0) {
                    bean.setSlPdSftStdList(sl241126BeanList);
                }else {
                    List<SL241126Bean> sl241126BeanListNu = (List<SL241126Bean>) sl241126BeansLMap.get("null");
                    if (null != sl241126BeanListNu && sl241126BeanListNu.size() > 0) {
                        bean.setSlPdSftStdList(sl241126BeanListNu);
                    }
                }
            }
        }
        return results;
    }

    /**
     * 准备 产品加工质量标准数据
     *
     * @param params
     * @return
     */
    @Transactional
    public List<SL241117Bean> getTncStd(BaseParam params) {
        List<SL241117Bean> sl241117BeansR = new ArrayList<SL241117Bean>();
        List<SL241117Bean> sl241117Beans = super.findList(SqlId.SQL_ID_GET_TNC_STD_INFO, params);
        if (null != sl241117Beans && sl241117Beans.size() > 0) {
            sl241117BeansR = getTncStdData(sl241117Beans);
        }
        return sl241117BeansR;
    }

    /**
     * 处理 产品加工质量标准数据
     *
     * @param sl241117Beans
     * @return
     */
    private List<SL241117Bean> getTncStdData(List<SL241117Bean> sl241117Beans) {
        List<SL241117Bean> sl241117BeansR = new ArrayList<SL241117Bean>();
        // 卖家查询数据处理
        Map<String, Object> sl241117BeanMap = new HashMap<String, Object>();
        List<StdItem> stdItemList = new ArrayList<StdItem>();
        for (SL241117Bean slb : sl241117Beans) {
            String key = slb.getStandardId() + slb.getTncStdItemId();
            List<SL241117Bean> sl241117BeansV = (List<SL241117Bean>) sl241117BeanMap.get(key);
            if (null == sl241117BeansV) {
                sl241117BeansV = new ArrayList<SL241117Bean>();
            }
            sl241117BeansV.add(slb);
            sl241117BeanMap.put(key, sl241117BeansV);

            // 准备接口数据
            StdItem stdItem = new StdItem();
            stdItem.setStandardId(slb.getStandardId() + "");
            stdItem.setStdItemId(slb.getTncStdItemId());
            stdItemList.add(stdItem);
        }

        // 调接口查询数据
        RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(stdItemList, null,
            NumberConst.IntDef.INT_EIGHT);
        ProductStdResult productStdResult = responce.getResult();
        if (null != productStdResult) {
            List<TncStdBean> tncStdBeanList = productStdResult.getTncStdList();
            // 接口数据处理
            for (TncStdBean slb : tncStdBeanList) {
                // 处理 levelId = 1
                String key = slb.getStandardId() + slb.getTncStdItemId();
                List<SL241117Bean> sl241117BeanListV = (List<SL241117Bean>) sl241117BeanMap.get(key);
                for (SL241117Bean sl241117BeanV : sl241117BeanListV) {
                    SL241117Bean sl241117BeanM = new SL241117Bean();
                    // 合并数据 levelId = 1
                    sl241117BeanM = setSL241117Bean(slb, sl241117BeanV);
                    sl241117BeansR.add(sl241117BeanM);
                }
            }
        }
        return sl241117BeansR;
    }

    /**
     * 处理 产品加工质量标准数据 javabean
     *
     * @param tncStdBean
     * @param sl241117BeanV
     * @return
     */
    private SL241117Bean setSL241117Bean(TncStdBean tncStdBean, SL241117Bean sl241117BeanV) {
        SL241117Bean sl241117Bean = new SL241117Bean();

        sl241117Bean.setStandardId(tncStdBean.getStandardId());
        sl241117Bean.setTncStdItemId(tncStdBean.getTncStdItemId());
        sl241117Bean.setTncStdVal1(tncStdBean.getTncStdVal1());
        sl241117Bean.setTncStdVal2(tncStdBean.getTncStdVal2());
        sl241117Bean.setTncStdVal3(tncStdBean.getTncStdVal3());
        sl241117Bean.setTncStdItemName(tncStdBean.getTncStdItemName());
        sl241117Bean.setLevelId(tncStdBean.getLevelId());
        sl241117Bean.setParentId(tncStdBean.getParentId());
        sl241117Bean.setIsCatalog(tncStdBean.getIsCatalog());

        sl241117Bean.setStdVal(sl241117BeanV.getStdVal());
        sl241117Bean.setAgreeFlg(sl241117BeanV.getAgreeFlg());
        sl241117Bean.setStdDate(sl241117BeanV.getStdDate());
        sl241117Bean.setSlCode(sl241117BeanV.getSlCode());
        sl241117Bean.setSlPdId(sl241117BeanV.getSlPdId());
        sl241117Bean.setVer(sl241117BeanV.getVer());
        return sl241117Bean;
    }

    /**
     * 准备 加工技术标准详细页面数据
     *
     * @param params
     * @return
     */
    @Transactional
    public List<SL241118Bean> getMctStd(BaseParam params) {
        List<SL241118Bean> sl241118BeansR = new ArrayList<SL241118Bean>();
        List<SL241118Bean> sl241118Beans = super.findList(SqlId.SQL_ID_GET_MCT_STD_INFO, params);
        if (null != sl241118Beans && sl241118Beans.size() > 0) {
            sl241118BeansR = getMctStdData(sl241118Beans);
        }
        return sl241118BeansR;
    }

    /**
     * 处理 加工技术标准详细页面数据
     *
     * @param sl241118Beans
     * @return
     */
    private List<SL241118Bean> getMctStdData(List<SL241118Bean> sl241118Beans) {
        List<SL241118Bean> sl241118BeansR = new ArrayList<SL241118Bean>();
        // 卖家查询数据处理
        Map<String, Object> sl241118BeanMap = new HashMap<String, Object>();
        List<StdItem> stdItemList = new ArrayList<StdItem>();
        for (SL241118Bean slb : sl241118Beans) {
            String key = slb.getStandardId() + slb.getMctStdItemId();
            List<SL241118Bean> sl241118BeansV = (List<SL241118Bean>) sl241118BeanMap.get(key);
            if (null == sl241118BeansV) {
                sl241118BeansV = new ArrayList<SL241118Bean>();
            }
            sl241118BeansV.add(slb);
            sl241118BeanMap.put(key, sl241118BeansV);

            // 准备接口数据
            StdItem stdItem = new StdItem();
            stdItem.setStandardId(slb.getStandardId() + "");
            stdItem.setStdItemId(slb.getMctStdItemId());
            stdItemList.add(stdItem);
        }

        // 调接口查询数据
        RsResponse<ProductStdResult> response = ISLRestUtil.getPdProductStd(stdItemList, null,
            NumberConst.IntDef.INT_ONE);
        ProductStdResult productStdResult = response.getResult();

        // 接口数据处理
        if (null != productStdResult) {
            List<MctStdBean> mctStdBeanList = productStdResult.getMctStdList();
            for (MctStdBean slb : mctStdBeanList) {
                // 处理 levelId = 1
                String key = slb.getStandardId() + slb.getMctStdItemId();
                List<SL241118Bean> sl241118BeanListV = (List<SL241118Bean>) sl241118BeanMap.get(key);
                for (SL241118Bean sl241118BeanV : sl241118BeanListV) {
                    SL241118Bean sl241118BeanM = new SL241118Bean();
                    // 合并数据 levelId = 1
                    sl241118BeanM = setSL241118Bean(slb, sl241118BeanV);
                    sl241118BeansR.add(sl241118BeanM);
                }
            }
        }
        return sl241118BeansR;
    }

    /**
     * 处理 加工技术标准详细页面数据 javabean
     *
     * @param mctStdBean
     * @param sl241118BeanV
     * @return
     */
    private SL241118Bean setSL241118Bean(MctStdBean mctStdBean, SL241118Bean sl241118BeanV) {
        SL241118Bean sl241118Bean = new SL241118Bean();

        sl241118Bean.setStandardId(mctStdBean.getStandardId());
        sl241118Bean.setMctStdItemId(mctStdBean.getMctStdItemId());
        sl241118Bean.setMctOkVal(mctStdBean.getMctOkVal());
        sl241118Bean.setMctNgVal(mctStdBean.getMctNgVal());
        sl241118Bean.setMctStdItemName(mctStdBean.getMctStdItemName());
        sl241118Bean.setLevelId(mctStdBean.getLevelId());
        sl241118Bean.setParentId(mctStdBean.getParentId());
        sl241118Bean.setIsCatalog(mctStdBean.getIsCatalog());

        sl241118Bean.setStdVal(sl241118BeanV.getStdVal());
        sl241118Bean.setAgreeFlg(sl241118BeanV.getAgreeFlg());
        sl241118Bean.setStdDate(sl241118BeanV.getStdDate());
        sl241118Bean.setSlCode(sl241118BeanV.getSlCode());
        sl241118Bean.setSlPdId(sl241118BeanV.getSlPdId());
        sl241118Bean.setVer(sl241118BeanV.getVer());
        return sl241118Bean;
    }

    /**
     * 标准信息获取共通
     */
    public <T extends BaseEntity> List<T> getStdInfo(T t, BaseParam param) {
        List<T> result = new ArrayList<T>();
        String standardId = StringUtil.toSafeString(param.getFilterMap().get("standardId"));
        String stdFlg = StringUtil.toSafeString(param.getFilterMap().get("stdFlg"));
        RsResponse<ProductStdResult> responce = null;
        ProductStdResult productStdResult = null;
        if ("1".equals(stdFlg)) {
            responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId, NumberConst.IntDef.INT_THREE);
            productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<OrgStdBean> list = productStdResult.getOrgStdList();
            result = (List<T>) this.getOrgStdDataNew(param, list);
        } else if ("2".equals(stdFlg)) {
            responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId, NumberConst.IntDef.INT_FOUR);
            productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<FedStdBean> list = productStdResult.getFedStdlist();
            result = (List<T>) this.getFedStdDataNew(param, list);
        } else if ("3".equals(stdFlg)) {
            responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId, NumberConst.IntDef.INT_FIVE);
            productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<GnqStdBean> list = productStdResult.getGnqStdlist();
            result = (List<T>) this.getGnqStdDataNew(param, list);
        } else if ("4".equals(stdFlg)) {
            responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId, NumberConst.IntDef.INT_SIX);
            productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<TspStdBean> list = productStdResult.getTspStdlist();
            result = (List<T>) this.getTspStdDataNew(param, list);
        } else if ("5".equals(stdFlg)) {
            responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId, NumberConst.IntDef.INT_SEVEN);
            productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<SftStdBean> list = productStdResult.getSftStdlist();
            result = (List<T>) this.getSftStdDataNew(param, list);
        }
        return result;
    }

    /**
     * 原种种源标准指标
     * 
     * @param param
     * @param orgStdBeanList
     * @return
     */
    @Transactional
    private List<SL241122Bean> getOrgStdDataNew(BaseParam param, List<OrgStdBean> orgStdBeanList) {
        List<SL241122Bean> resultList = new ArrayList<SL241122Bean>();
        // 查看卖家数据库 指向到 卖家+产品+产品标准
        List<SL241122Bean> sl241122Beans = super.findList(SqlId.SQL_ID_GET_ORG_STD_INFO, param);
        String standardId = StringUtil.toSafeString(param.getFilterMap().get("standardId"));
        for (OrgStdBean orgBean : orgStdBeanList) {
            SL241122Bean sl241122Bean = new SL241122Bean();
            if (!StringUtil.isNullOrEmpty(standardId)) {
                BeanUtils.copyProperties(orgBean, sl241122Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑
                for (SL241122Bean bean : sl241122Beans) {
                    if (sl241122Bean.getOrgStdItemId().equals(bean.getOrgStdItemId())) {
                        sl241122Bean.setAgreeFlg(bean.getAgreeFlg());
                    }
                }
                sl241122Bean.setSlCode(StringUtil.toSafeString(param.getFilterMap().get("slCode")));
                sl241122Bean.setSlPdId(Integer.parseInt(StringUtil.toSafeString(param.getFilterMap().get("slPdId"))));
                resultList.add(sl241122Bean);
            } else {
                // 对应老逻辑中 INNER JOIN 逻辑 暂时同步 老逻辑 没有standardId 随机取一个
                for (SL241122Bean bean : sl241122Beans) {
                    if (resultList.size() > 0 && !resultList.get(0).getStandardId().equals(bean.getStandardId())) {
                        continue;
                    }
                    if (orgBean.getOrgStdItemId().equals(bean.getOrgStdItemId())
                            && orgBean.getStandardId().equals(bean.getStandardId())) {
                        BeanUtils.copyProperties(orgBean, sl241122Bean);
                        sl241122Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241122Bean.setSlCode(bean.getSlCode());
                        sl241122Bean.setSlPdId(bean.getSlPdId());
                        resultList.add(sl241122Bean);
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 原种饲养标准指标
     * 
     * @param param
     * @param fedStdBeanList
     * @return
     */
    @Transactional
    private List<SL241123Bean> getFedStdDataNew(BaseParam param, List<FedStdBean> fedStdBeanList) {
        List<SL241123Bean> resultList = new ArrayList<SL241123Bean>();
        String standardId = StringUtil.toSafeString(param.getFilterMap().get("standardId"));
        List<SL241123Bean> sl241123Beans = super.findList(SqlId.SQL_ID_GET_FED_STD_INFO, param);
        for (FedStdBean fedBean : fedStdBeanList) {
            SL241123Bean sl241123Bean = new SL241123Bean();
            if (!StringUtil.isNullOrEmpty(standardId)) {
                BeanUtils.copyProperties(fedBean, sl241123Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑
                for (SL241123Bean bean : sl241123Beans) {
                    if (sl241123Bean.getFedStdItemId().equals(bean.getFedStdItemId())) {
                        sl241123Bean.setAgreeFlg(bean.getAgreeFlg());
                    }
                }
                sl241123Bean.setSlCode(StringUtil.toSafeString(param.getFilterMap().get("slCode")));
                sl241123Bean.setSlPdId(Integer.parseInt(StringUtil.toSafeString(param.getFilterMap().get("slPdId"))));
                resultList.add(sl241123Bean);
            } else {
                // 对应老逻辑中 INNER JOIN 逻辑 暂时同步 老逻辑 没有standardId 随机取一个
                for (SL241123Bean bean : sl241123Beans) {
                    if (resultList.size() > 0 && !resultList.get(0).getStandardId().equals(bean.getStandardId())) {
                        continue;
                    }
                    if (fedBean.getFedStdItemId().equals(bean.getFedStdItemId())
                            && fedBean.getStandardId().equals(bean.getStandardId())) {
                        BeanUtils.copyProperties(fedBean, sl241123Bean);
                        sl241123Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241123Bean.setSlCode(bean.getSlCode());
                        sl241123Bean.setSlPdId(bean.getSlPdId());
                        resultList.add(sl241123Bean);
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 通用质量标准
     * 
     * @param param
     * @param gnqStdBeanList
     * @return
     */
    @Transactional
    private List<SL241124Bean> getGnqStdDataNew(BaseParam param, List<GnqStdBean> gnqStdBeanList) {

        List<SL241124Bean> resultList = new ArrayList<SL241124Bean>();
        String standardId = StringUtil.toSafeString(param.getFilterMap().get("standardId"));
        List<SL241124Bean> sl241124Beans = super.findList(SqlId.SQL_ID_GET_GNQ_STD_INFO, param);

        for (GnqStdBean gnqStdBean : gnqStdBeanList) {
            // LEVEL = 1对应的BEAN
            SL241124Bean sl241124Bean = new SL241124Bean();
            List<GnqStdBean> pdGnqStds = gnqStdBean.getPdGnqStds();
            List<SL241124Bean> pdGnqStdBeans = new ArrayList<SL241124Bean>();
            if (!StringUtil.isNullOrEmpty(standardId)) {
                // 第一级赋值
                BeanUtils.copyProperties(gnqStdBean, sl241124Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑 LEVEL = 1
                for (SL241124Bean bean : sl241124Beans) {
                    if (sl241124Bean.getGnqStdItemId().equals(bean.getGnqStdItemId()) ) {
                        sl241124Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241124Bean.setLevelId("1");
                    }
                }
                sl241124Bean.setSlCode(StringUtil.toSafeString(param.getFilterMap().get("slCode")));
                sl241124Bean.setSlPdId(Integer.parseInt(StringUtil.toSafeString(param.getFilterMap().get("slPdId"))));
                // 二级处理 LEVEL = 2 用MAP获取LIST 依旧需要循环
                for (GnqStdBean twoGnqStdBean : pdGnqStds) {
                    if(twoGnqStdBean.getStandardId() != null){
                        SL241124Bean twoSlBean = new SL241124Bean();
                        BeanUtils.copyProperties(twoGnqStdBean, twoSlBean);
                        for (SL241124Bean bean : sl241124Beans) {
                            if (twoSlBean.getGnqStdItemId().equals(bean.getGnqStdItemId())
                                    && twoSlBean.getStandardId().equals(bean.getStandardId())) {
                                twoSlBean.setAgreeFlg(bean.getAgreeFlg());
                                twoSlBean.setLevelId("2");
                            }
                        }
                        pdGnqStdBeans.add(twoSlBean);
                    }
                }
                sl241124Bean.setPdGnqStds(pdGnqStdBeans);
            } else {
                // 第一级赋值
                BeanUtils.copyProperties(gnqStdBean, sl241124Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑 LEVEL = 1
                for (SL241124Bean bean : sl241124Beans) {
                    if (sl241124Bean.getGnqStdItemId().equals(bean.getGnqStdItemId())
                            && sl241124Bean.getStandardId().equals(bean.getStandardId())) {
                        sl241124Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241124Bean.setLevelId("1");
                        sl241124Bean.setSlCode(bean.getSlCode());
                        sl241124Bean.setSlPdId(bean.getSlPdId());
                    }
                }
                for (GnqStdBean twoGnqStdBean : pdGnqStds) {
                    for (SL241124Bean bean : sl241124Beans) {
                        if (twoGnqStdBean.getGnqStdItemId().equals(bean.getGnqStdItemId())
                                && twoGnqStdBean.getStandardId().equals(bean.getStandardId())
                                && "0".equals(bean.getDelFlg())){
                            SL241124Bean twoSlBean = new SL241124Bean();
                            BeanUtils.copyProperties(twoGnqStdBean, twoSlBean);
                            twoSlBean.setAgreeFlg(bean.getAgreeFlg());
                            twoSlBean.setLevelId("2");
                            twoSlBean.setSlCode(bean.getSlCode());
                            twoSlBean.setSlPdId(bean.getSlPdId());
                            pdGnqStdBeans.add(twoSlBean);
                        }
                    }
                }
                sl241124Bean.setPdGnqStds(pdGnqStdBeans);
            }
            resultList.add(sl241124Bean);
        }
        return resultList;
    }

    /**
     * 通用质量标准
     * 
     * @param param
     * @param tspStdBeanList
     * @return
     */
    @Transactional
    private List<SL241125Bean> getTspStdDataNew(BaseParam param, List<TspStdBean> tspStdBeanList) {

        List<SL241125Bean> resultList = new ArrayList<SL241125Bean>();
        String standardId = StringUtil.toSafeString(param.getFilterMap().get("standardId"));
        List<SL241125Bean> sl241125Beans = super.findList(SqlId.SQL_ID_GET_TSP_STD_INFO, param);

        for (TspStdBean tspStdBean : tspStdBeanList) {
            // LEVEL = 1对应的BEAN
            SL241125Bean sl241125Bean = new SL241125Bean();
            List<TspStdBean> pdTspStds = tspStdBean.getPdTspStds();
            List<SL241125Bean> pdTspStdBeans = new ArrayList<SL241125Bean>();
            if (!StringUtil.isNullOrEmpty(standardId)) {
                // 第一级赋值
                BeanUtils.copyProperties(tspStdBean, sl241125Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑 LEVEL = 1
                for (SL241125Bean bean : sl241125Beans) {
                    if (sl241125Bean.getTspStdItemId().equals(bean.getTspStdItemId())) {
                        sl241125Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241125Bean.setLevelId("1");
                    }
                }
                sl241125Bean.setSlCode(StringUtil.toSafeString(param.getFilterMap().get("slCode")));
                sl241125Bean.setSlPdId(Integer.parseInt(StringUtil.toSafeString(param.getFilterMap().get("slPdId"))));
                // 二级处理 LEVEL = 2 用MAP获取LIST 依旧需要循环
                for (TspStdBean twoTspStdBean : pdTspStds) {
                    if(twoTspStdBean.getStandardId() != null){
                        SL241125Bean twoSlBean = new SL241125Bean();
                        BeanUtils.copyProperties(twoTspStdBean, twoSlBean);
                        for (SL241125Bean bean : sl241125Beans) {
                            if (twoSlBean.getTspStdItemId().equals(bean.getTspStdItemId())
                                    && twoSlBean.getStandardId().equals(bean.getStandardId())) {
                                twoSlBean.setAgreeFlg(bean.getAgreeFlg());
                                twoSlBean.setLevelId("2");
                            }
                        }
                        pdTspStdBeans.add(twoSlBean);
                    }
                }
                sl241125Bean.setPdTspStds(pdTspStdBeans);
            } else {
                // 第一级赋值
                BeanUtils.copyProperties(tspStdBean, sl241125Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑 LEVEL = 1
                for (SL241125Bean bean : sl241125Beans) {
                    if (sl241125Bean.getTspStdItemId().equals(bean.getTspStdItemId())
                            && sl241125Bean.getStandardId().equals(bean.getStandardId())) {
                        sl241125Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241125Bean.setLevelId("1");
                        sl241125Bean.setSlCode(bean.getSlCode());
                        sl241125Bean.setSlPdId(bean.getSlPdId());
                    }
                }
                for (TspStdBean twoTspStdBean : pdTspStds) {
                    for (SL241125Bean bean : sl241125Beans) {
                        if (twoTspStdBean.getTspStdItemId().equals(bean.getTspStdItemId())
                                && twoTspStdBean.getStandardId().equals(bean.getStandardId())
                                && "0".equals(bean.getDelFlg())) {
                            SL241125Bean twoSlBean = new SL241125Bean();
                            BeanUtils.copyProperties(twoTspStdBean, twoSlBean);
                            twoSlBean.setAgreeFlg(bean.getAgreeFlg());
                            twoSlBean.setLevelId("2");
                            twoSlBean.setSlCode(bean.getSlCode());
                            twoSlBean.setSlPdId(bean.getSlPdId());
                            pdTspStdBeans.add(twoSlBean);
                        }
                    }
                }
                sl241125Bean.setPdTspStds(pdTspStdBeans);
            }
            resultList.add(sl241125Bean);
        }
        return resultList;
    }

    /**
     * 通用质量标准
     * 
     * @param param
     * @param sftStdBeanList
     * @return
     */
    @Transactional
    private List<SL241126Bean> getSftStdDataNew(BaseParam param, List<SftStdBean> sftStdBeanList) {
        List<SL241126Bean> resultList = new ArrayList<SL241126Bean>();
        String standardId = StringUtil.toSafeString(param.getFilterMap().get("standardId"));
        List<SL241126Bean> sl241126Beans = super.findList(SqlId.SQL_ID_GET_SFT_STD_INFO, param);
        for (SftStdBean sftStdBean : sftStdBeanList) {
            // LEVEL = 1对应的BEAN
            SL241126Bean sl241126Bean = new SL241126Bean();
            List<SftStdBean> pdSftStds = sftStdBean.getPdSftStds();
            List<SL241126Bean> pdSftStdBeans = new ArrayList<SL241126Bean>();
            if (!StringUtil.isNullOrEmpty(standardId)) {
                // 第一级赋值
                BeanUtils.copyProperties(sftStdBean, sl241126Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑 LEVEL = 1
                for (SL241126Bean bean : sl241126Beans) {
                    if (sl241126Bean.getSftStdItemId().equals(bean.getSftStdItemId())) {
                        sl241126Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241126Bean.setLevelId("1");
                    }
                }
                sl241126Bean.setSlCode(StringUtil.toSafeString(param.getFilterMap().get("slCode")));
                sl241126Bean.setSlPdId(Integer.parseInt(StringUtil.toSafeString(param.getFilterMap().get("slPdId"))));
                // 二级处理 LEVEL = 2 用MAP获取LIST 依旧需要循环
                for (SftStdBean twoSftStdBean : pdSftStds) {
                    if(twoSftStdBean.getStandardId() != null){
                        SL241126Bean twoSlBean = new SL241126Bean();
                        BeanUtils.copyProperties(twoSftStdBean, twoSlBean);
                        for (SL241126Bean bean : sl241126Beans) {
                            if (twoSlBean.getSftStdItemId().equals(bean.getSftStdItemId())
                                    && twoSlBean.getStandardId().equals(bean.getStandardId())) {
                                twoSlBean.setAgreeFlg(bean.getAgreeFlg());
                                twoSlBean.setLevelId("2");
                            }
                        }
                        pdSftStdBeans.add(twoSlBean);
                    }
                }
                sl241126Bean.setPdSftStds(pdSftStdBeans);
            } else {
                // 第一级赋值
                BeanUtils.copyProperties(sftStdBean, sl241126Bean);
                // 对应老逻辑中 LEFT JOIN 逻辑 LEVEL = 1
                for (SL241126Bean bean : sl241126Beans) {
                    if (sl241126Bean.getSftStdItemId().equals(bean.getSftStdItemId())
                            && sl241126Bean.getStandardId().equals(bean.getStandardId())) {
                        sl241126Bean.setAgreeFlg(bean.getAgreeFlg());
                        sl241126Bean.setLevelId("1");
                        sl241126Bean.setSlCode(bean.getSlCode());
                        sl241126Bean.setSlPdId(bean.getSlPdId());
                    }
                }
                for (SftStdBean twoSftStdBean : pdSftStds) {
                    for (SL241126Bean bean : sl241126Beans) {
                        if (twoSftStdBean.getSftStdItemId().equals(bean.getSftStdItemId())
                                && twoSftStdBean.getStandardId().equals(bean.getStandardId())
                                && "0".equals(bean.getDelFlg())) {
                            SL241126Bean twoSlBean = new SL241126Bean();
                            BeanUtils.copyProperties(twoSftStdBean, twoSlBean);
                            twoSlBean.setAgreeFlg(bean.getAgreeFlg());
                            twoSlBean.setLevelId("2");
                            twoSlBean.setSlCode(bean.getSlCode());
                            twoSlBean.setSlPdId(bean.getSlPdId());
                            pdSftStdBeans.add(twoSlBean);
                        }
                    }
                }
                sl241126Bean.setPdSftStds(pdSftStdBeans);
            }
            resultList.add(sl241126Bean);
        }
        return resultList;
    }

    /**
     * 准备 卖家产品原种种源标准数据
     *
     * @param params
     * @return
     */
    public List<SL241122Bean> getOrgStdInfo(BaseParam params) {
        List<SL241122Bean> sl241122BeansR = new ArrayList<SL241122Bean>();
        String standardId = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        // standardId 不为空 调用接口查询数据
        RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId,
            NumberConst.IntDef.INT_THREE);
        ProductStdResult productStdResult = responce.getResult();
        if (productStdResult == null) {
            productStdResult = new ProductStdResult();
        }
        List<OrgStdBean> orgStdBeanList = productStdResult.getOrgStdList();
        if (null != orgStdBeanList && orgStdBeanList.size() > 0) {
            sl241122BeansR = this.getOrgStdDataNew(params, orgStdBeanList);
        }
        return sl241122BeansR;
    }

    /**
     * 处理 卖家产品原种种源标准数据
     *
     * @param params
     * @param orgStdBeanList
     * @return
     */
    @Transactional
    private List<SL241122Bean> getOrgStdData(BaseParam params, List<OrgStdBean> orgStdBeanList) {
        List<SL241122Bean> sl241122BeansR = new ArrayList<SL241122Bean>();

        // Modified by xia_xiaojie on 2016/6/24. Modified start.
        // String standardId = (String) params.getFilterMap().get("standardId");
        String standardId = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        // Modified end.

        // 查询数据
        List<SL241122Bean> sl241122Beans = super.findList(SqlId.SQL_ID_GET_ORG_STD_INFO, params);
        // 卖家查询数据处理
        Map<String, Object> sl241122BeanMap = new HashMap<String, Object>();
        List<StdItem> stdItemList = new ArrayList<StdItem>();
        if (null != sl241122Beans && sl241122Beans.size() > 0) {
            for (SL241122Bean slb : sl241122Beans) {
                String key = slb.getStandardId() + slb.getOrgStdItemId();
                List<SL241122Bean> sl241122BeansV = (List<SL241122Bean>) sl241122BeanMap.get(key);
                if (null == sl241122BeansV) {
                    sl241122BeansV = new ArrayList<SL241122Bean>();
                }
                sl241122BeansV.add(slb);
                sl241122BeanMap.put(key, sl241122BeansV);

                if (StringUtil.isNullOrEmpty(standardId)) {
                    // 准备接口数据
                    StdItem stdItem = new StdItem();
                    stdItem.setStandardId(slb.getStandardId() + "");
                    stdItem.setStdItemId(slb.getOrgStdItemId());
                    stdItemList.add(stdItem);
                }
            }
        }

        // standardId 为空 调接口
        if (null != stdItemList && stdItemList.size() > 0) {
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(stdItemList, null,
                NumberConst.IntDef.INT_THREE);
            ProductStdResult productStdResult = responce.getResult();
            orgStdBeanList = productStdResult.getOrgStdList();
        }

        // 接口数据合并
        if (null != orgStdBeanList && orgStdBeanList.size() > 0) {
            // 接口数据处理
            for (OrgStdBean slb : orgStdBeanList) {
                // 处理 levelId = 1
                String key = slb.getStandardId() + slb.getOrgStdItemId();
                List<SL241122Bean> sl241122BeanListV = (List<SL241122Bean>) sl241122BeanMap.get(key);
                if (null != sl241122BeanListV && sl241122BeanListV.size() > 0) {
                    for (SL241122Bean sl241122BeanV : sl241122BeanListV) {
                        SL241122Bean sl241122BeanM = new SL241122Bean();
                        // 合并数据 levelId = 1
                        sl241122BeanM = setSL241122Bean(slb, sl241122BeanV);
                        sl241122BeansR.add(sl241122BeanM);
                    }
                } else {
                    SL241122Bean sl241122BeanM = new SL241122Bean();
                    // 合并数据 levelId = 1
                    sl241122BeanM = setSL241122Bean(slb, new SL241122Bean());
                    sl241122BeansR.add(sl241122BeanM);
                }
            }
        }
        return sl241122BeansR;
    }

    /**
     * 处理 卖家产品原种种源标准数据 javabean
     *
     * @param orgStdBean
     * @param sl241122BeanV
     * @return
     */
    private SL241122Bean setSL241122Bean(OrgStdBean orgStdBean, SL241122Bean sl241122BeanV) {
        SL241122Bean sl241122Bean = new SL241122Bean();

        sl241122Bean.setStandardId(orgStdBean.getStandardId());
        sl241122Bean.setOrgStdItemId(orgStdBean.getOrgStdItemId());
        sl241122Bean.setOrgGoodVal(orgStdBean.getOrgGoodVal());
        sl241122Bean.setOrgNormalVal(orgStdBean.getOrgNormalVal());
        sl241122Bean.setOrgBadVal(orgStdBean.getOrgBadVal());
        sl241122Bean.setOrgStdItemName(orgStdBean.getOrgStdItemName());
        sl241122Bean.setLevelId(orgStdBean.getLevelId());
        sl241122Bean.setParentId(orgStdBean.getParentId());
        sl241122Bean.setIsCatalog(orgStdBean.getIsCatalog());

        sl241122Bean.setAgreeFlg(sl241122BeanV.getAgreeFlg());
        sl241122Bean.setSlCode(sl241122BeanV.getSlCode());
        sl241122Bean.setSlPdId(sl241122BeanV.getSlPdId());
        return sl241122Bean;
    }

    /**
     * 准备 卖家产品饲养标准数据
     *
     * @param params
     * @return
     */
    public List<SL241123Bean> getFedStdInfo(BaseParam params) {
        List<SL241123Bean> sl241123BeansR = new ArrayList<SL241123Bean>();
        String standardId = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        if (!StringUtil.isNullOrEmpty(standardId)) {
            // standardId 不为空 调用接口查询数据
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId,
                NumberConst.IntDef.INT_FOUR);
            ProductStdResult productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<FedStdBean> fedStdBeanList = productStdResult.getFedStdlist();
            if (null != fedStdBeanList && fedStdBeanList.size() > 0) {
                // 处理接口数据
                sl241123BeansR = getFedStdData(params, fedStdBeanList);
            }
        } else {
            sl241123BeansR = getFedStdData(params, new ArrayList<FedStdBean>());
        }
        return sl241123BeansR;
    }

    /**
     * 处理 卖家产品饲养标准数据
     *
     * @param params
     * @param fedStdBeanList
     * @return
     */
    @Transactional
    private List<SL241123Bean> getFedStdData(BaseParam params, List<FedStdBean> fedStdBeanList) {
        List<SL241123Bean> SL241123BeansR = new ArrayList<SL241123Bean>();
        String standardId = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        // 查询数据
        List<SL241123Bean> sl241123Beans = super.findList(SqlId.SQL_ID_GET_FED_STD_INFO, params);
        // 卖家查询数据处理
        Map<String, Object> SL241123BeanMap = new HashMap<String, Object>();
        List<StdItem> stdItemList = new ArrayList<StdItem>();
        for (SL241123Bean slb : sl241123Beans) {
            String key = slb.getStandardId() + slb.getFedStdItemId();
            List<SL241123Bean> SL241123BeansV = (List<SL241123Bean>) SL241123BeanMap.get(key);
            if (null == SL241123BeansV) {
                SL241123BeansV = new ArrayList<SL241123Bean>();
            }
            SL241123BeansV.add(slb);
            SL241123BeanMap.put(key, SL241123BeansV);

            if (StringUtil.isNullOrEmpty(standardId)) {
                // 准备接口数据
                StdItem stdItem = new StdItem();
                stdItem.setStandardId(slb.getStandardId() + "");
                stdItem.setStdItemId(slb.getFedStdItemId());
                stdItemList.add(stdItem);
            }
        }

        // standardId 为空 调接口
        if (null != stdItemList && stdItemList.size() > 0) {
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(stdItemList, null,
                NumberConst.IntDef.INT_FOUR);
            ProductStdResult productStdResult = responce.getResult();
            fedStdBeanList = productStdResult.getFedStdlist();
        }

        // 接口数据合并
        if (null != fedStdBeanList && fedStdBeanList.size() > 0) {
            for (FedStdBean slb : fedStdBeanList) {
                // 处理 levelId = 1
                String key = slb.getStandardId() + slb.getFedStdItemId();
                List<SL241123Bean> SL241123BeanListV = (List<SL241123Bean>) SL241123BeanMap.get(key);
                if (null != SL241123BeanListV && SL241123BeanListV.size() > 0) {
                    for (SL241123Bean SL241123BeanV : SL241123BeanListV) {
                        SL241123Bean SL241123BeanM = new SL241123Bean();
                        // 合并数据 levelId = 1
                        SL241123BeanM = setSL241123Bean(slb, SL241123BeanV);
                        SL241123BeansR.add(SL241123BeanM);
                    }
                } else {
                    SL241123Bean sl241123BeanM = new SL241123Bean();
                    // 合并数据 levelId = 1
                    sl241123BeanM = setSL241123Bean(slb, new SL241123Bean());
                    SL241123BeansR.add(sl241123BeanM);
                }
            }
        }
        return SL241123BeansR;
    }

    /**
     * 处理 卖家产品饲养标准数据 javabean
     *
     * @param fedStdBean
     * @param sl241123BeanV
     * @return
     */
    private SL241123Bean setSL241123Bean(FedStdBean fedStdBean, SL241123Bean sl241123BeanV) {
        SL241123Bean sl241123Bean = new SL241123Bean();

        sl241123Bean.setStandardId(fedStdBean.getStandardId());
        sl241123Bean.setFedStdItemId(fedStdBean.getFedStdItemId());
        sl241123Bean.setFedGoodVal(fedStdBean.getFedGoodVal());
        sl241123Bean.setFedNormalVal(fedStdBean.getFedNormalVal());
        sl241123Bean.setFedBadVal(fedStdBean.getFedBadVal());
        sl241123Bean.setFedStdItemName(fedStdBean.getFedStdItemName());
        sl241123Bean.setLevelId(fedStdBean.getLevelId());
        sl241123Bean.setParentId(fedStdBean.getParentId());
        sl241123Bean.setIsCatalog(fedStdBean.getIsCatalog());

        sl241123Bean.setAgreeFlg(sl241123BeanV.getAgreeFlg());
        sl241123Bean.setSlCode(sl241123BeanV.getSlCode());
        sl241123Bean.setSlPdId(sl241123BeanV.getSlPdId());
        return sl241123Bean;
    }

    /**
     * 准备 卖家产品通用质量标准数据
     *
     * @param params
     * @return
     */
    public List<SL241124Bean> getGnqStdInfo(BaseParam params) {
        List<SL241124Bean> SL241124BeansR = new ArrayList<SL241124Bean>();
        String standardId = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        if (!StringUtil.isNullOrEmpty(standardId)) {
            // standardId 不为空 调用接口查询数据
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId,
                NumberConst.IntDef.INT_FIVE);
            ProductStdResult productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<GnqStdBean> gnqStdBeanList = productStdResult.getGnqStdlist();
            if (null != gnqStdBeanList && gnqStdBeanList.size() > 0) {
                // 处理接口数据
                SL241124BeansR = getGnqStdData(params, gnqStdBeanList);
            }
        } else {
            SL241124BeansR = getGnqStdData(params, new ArrayList<GnqStdBean>());
        }
        return SL241124BeansR;
    }

    /**
     * 处理 卖家产品通用质量标准数据
     *
     * @param params
     * @param gnqStdBeanList
     * @return
     */
    @Transactional
    private List<SL241124Bean> getGnqStdData(BaseParam params, List<GnqStdBean> gnqStdBeanList) {
        List<SL241124Bean> SL241124BeansR = new ArrayList<SL241124Bean>();
        String standardIdParam = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        // 查询数据
        List<SL241124Bean> sl241124Beans = super.findList(SqlId.SQL_ID_GET_GNQ_STD_INFO, params);
        // 卖家查询数据处理
        Map<String, Object> SL241124BeanMap = new HashMap<String, Object>();
        Map<String, Integer> SL241124BeanSIdMap = new HashMap<String, Integer>();
        List<StdItem> stdItemList = new ArrayList<StdItem>();
        for (SL241124Bean slb : sl241124Beans) {
            // standardId + gnqStdItemId 对 数组
            String standardId = slb.getStandardId() + "";
            String key = standardId + slb.getGnqStdItemId();
            List<SL241124Bean> SL241124BeansV = (List<SL241124Bean>) SL241124BeanMap.get(key);
            if (null == SL241124BeansV) {
                SL241124BeansV = new ArrayList<SL241124Bean>();
            }
            SL241124BeansV.add(slb);
            SL241124BeanMap.put(key, SL241124BeansV);

            // standardId 和 slPdId 一对一
            SL241124BeanSIdMap.put(standardId, slb.getSlPdId());

            if (StringUtil.isNullOrEmpty(standardIdParam)) {
                // 准备接口数据
                StdItem stdItem = new StdItem();
                stdItem.setStandardId(standardId);
                stdItem.setStdItemId(slb.getGnqStdItemId());
                stdItemList.add(stdItem);
            }
        }
        // standardId 为空 调接口
        if (null != stdItemList && stdItemList.size() > 0) {
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(stdItemList, null,
                NumberConst.IntDef.INT_FIVE);
            ProductStdResult productStdResult = responce.getResult();
            gnqStdBeanList = productStdResult.getGnqStdlist();
        }

        // 接口数据处理
        if (null != gnqStdBeanList && gnqStdBeanList.size() > 0) {
            SL241124BeansR = setSL241124BeanAppend(gnqStdBeanList, SL241124BeanMap, SL241124BeanSIdMap);
        }
        return SL241124BeansR;
    }

    /**
     * 处理 卖家产品通用质量标准数据 接口数据合并
     *
     * @param gnqStdBeanList
     * @param SL241124BeanMap
     * @param SL241124BeanSIdMap
     * @return
     */
    private List<SL241124Bean> setSL241124BeanAppend(List<GnqStdBean> gnqStdBeanList,
        Map<String, Object> SL241124BeanMap, Map<String, Integer> SL241124BeanSIdMap) {
        List<SL241124Bean> SL241124BeansR = new ArrayList<SL241124Bean>();
        for (GnqStdBean slb : gnqStdBeanList) {
            // 处理 levelId = 1
            String key = slb.getStandardId() + slb.getGnqStdItemId();
            List<SL241124Bean> SL241124BeanListV = (List<SL241124Bean>) SL241124BeanMap.get(key);
            if (null != SL241124BeanListV && SL241124BeanListV.size() > 0) {
                for (SL241124Bean SL241124BeanV : SL241124BeanListV) {
                    SL241124Bean SL241124BeanM = new SL241124Bean();
                    // 合并数据 levelId = 1
                    SL241124BeanM = setSL241124Bean(slb, SL241124BeanV);
                    // 处理 levelId = 2
                    List<SL241124Bean> SL241124BeanListM = setSL241124BeanList(slb, SL241124BeanMap);
                    if (null != SL241124BeanListM && SL241124BeanListM.size() > 0) {
                        SL241124BeanM.setPdGnqStds(SL241124BeanListM);
                    }
                    // add 数据
                    SL241124BeansR.add(SL241124BeanM);
                }
            } else {
                SL241124Bean SL241124BeanM = new SL241124Bean();
                // 根据 standardId 获取 slPdId
                SL241124Bean SL241124BeanN = new SL241124Bean();
                Integer slPdId = SL241124BeanSIdMap.get(slb.getStandardId() + "");
                SL241124BeanN.setSlPdId(slPdId);
                SL241124BeanM = setSL241124Bean(slb, SL241124BeanN);
                // 处理 levelId = 2
                List<SL241124Bean> SL241124BeanListM = setSL241124BeanList(slb, SL241124BeanMap);
                if (null != SL241124BeanListM && SL241124BeanListM.size() > 0) {
                    SL241124BeanM.setPdGnqStds(SL241124BeanListM);
                }
                // add 数据
                SL241124BeansR.add(SL241124BeanM);
            }
        }
        return SL241124BeansR;
    }

    /**
     * 处理 卖家产品通用质量标准数据 list
     *
     * @param slb
     * @param SL241124BeanMap
     * @return
     */
    private List<SL241124Bean> setSL241124BeanList(GnqStdBean slb, Map<String, Object> SL241124BeanMap) {
        List<SL241124Bean> SL241124BeanList = new ArrayList<SL241124Bean>();
        List<GnqStdBean> gnqStdBeans = slb.getPdGnqStds();
        if (null != gnqStdBeans && gnqStdBeans.size() > 0) {
            for (GnqStdBean slbs : gnqStdBeans) {
                String key = slbs.getStandardId() + slbs.getGnqStdItemId();
                List<SL241124Bean> SL241124BeanListV = (List<SL241124Bean>) SL241124BeanMap.get(key);
                if (null != SL241124BeanListV && SL241124BeanListV.size() > 0) {
                    for (SL241124Bean SL241124BeanV : SL241124BeanListV) {
                        String delFlg = SL241124BeanV.getDelFlg();
                        if (StringUtil.isNullOrEmpty(delFlg) || "0".equals(delFlg)) {
                            SL241124Bean SL241124BeanMs = new SL241124Bean();
                            // 合并数据 levelId = 2
                            SL241124BeanMs = setSL241124Bean(slbs, SL241124BeanV);
                            SL241124BeanList.add(SL241124BeanMs);
                        }
                    }
                } else {
                    SL241124Bean SL241124BeanM = new SL241124Bean();
                    SL241124BeanM = setSL241124Bean(slbs, new SL241124Bean());
                    SL241124BeanList.add(SL241124BeanM);
                }
            }
        }
        return SL241124BeanList;
    }

    /**
     * 处理 卖家产品通用质量标准数据 javabean
     *
     * @param gnqStdBean
     * @param SL241124BeanV
     * @return
     */
    private SL241124Bean setSL241124Bean(GnqStdBean gnqStdBean, SL241124Bean SL241124BeanV) {
        SL241124Bean SL241124Bean = new SL241124Bean();

        SL241124Bean.setStandardId(gnqStdBean.getStandardId());
        SL241124Bean.setGnqStdItemId(gnqStdBean.getGnqStdItemId());
        SL241124Bean.setGnqGoodVal(gnqStdBean.getGnqGoodVal());
        SL241124Bean.setGnqOkVal(gnqStdBean.getGnqOkVal());
        SL241124Bean.setGnqNgVal(gnqStdBean.getGnqNgVal());
        SL241124Bean.setGnqStdItemName(gnqStdBean.getGnqStdItemName());
        SL241124Bean.setLevelId(gnqStdBean.getLevelId());
        SL241124Bean.setParentId(gnqStdBean.getParentId());
        SL241124Bean.setIsCatalog(gnqStdBean.getIsCatalog());

        SL241124Bean.setAgreeFlg(SL241124BeanV.getAgreeFlg());
        SL241124Bean.setSlCode(SL241124BeanV.getSlCode());
        SL241124Bean.setSlPdId(SL241124BeanV.getSlPdId());
        return SL241124Bean;
    }

    /**
     * 准备 卖家产品储存运输标准数据
     *
     * @param params
     * @return
     */
    public List<SL241125Bean> getTspStdInfo(BaseParam params) {
        List<SL241125Bean> SL241125BeansR = new ArrayList<SL241125Bean>();
        String standardId = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        if (!StringUtil.isNullOrEmpty(standardId)) {
            // standardId 不为空 调用接口查询数据
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId,
                NumberConst.IntDef.INT_SIX);
            ProductStdResult productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<TspStdBean> tspStdBeanList = productStdResult.getTspStdlist();
            if (null != tspStdBeanList && tspStdBeanList.size() > 0) {
                // 处理接口数据
                SL241125BeansR = getTspStdData(params, tspStdBeanList);
            }
        } else {
            SL241125BeansR = getTspStdData(params, new ArrayList<TspStdBean>());
        }
        return SL241125BeansR;
    }

    /**
     * 处理 卖家产品储存运输标准数据
     *
     * @param params
     * @param tspStdBeanList
     * @return
     */
    @Transactional
    private List<SL241125Bean> getTspStdData(BaseParam params, List<TspStdBean> tspStdBeanList) {
        List<SL241125Bean> SL241125BeansR = new ArrayList<SL241125Bean>();
        String standardIdParam = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        // 查询数据
        List<SL241125Bean> sl241125Beans = super.findList(SqlId.SQL_ID_GET_TSP_STD_INFO, params);
        // 卖家查询数据处理
        Map<String, Object> SL241125BeanMap = new HashMap<String, Object>();
        Map<String, Integer> SL241125BeanSIdMap = new HashMap<String, Integer>();
        List<StdItem> stdItemList = new ArrayList<StdItem>();
        for (SL241125Bean slb : sl241125Beans) {
            // standardId + gnqStdItemId 对 数组
            String standardId = slb.getStandardId() + "";
            String key = standardId + slb.getTspStdItemId();
            List<SL241125Bean> SL241125BeansV = (List<SL241125Bean>) SL241125BeanMap.get(key);
            if (null == SL241125BeansV) {
                SL241125BeansV = new ArrayList<SL241125Bean>();
            }
            SL241125BeansV.add(slb);
            SL241125BeanMap.put(key, SL241125BeansV);

            // standardId 和 slPdId 一对一
            SL241125BeanSIdMap.put(standardId, slb.getSlPdId());

            if (StringUtil.isNullOrEmpty(standardIdParam)) {
                // 准备接口数据
                StdItem stdItem = new StdItem();
                stdItem.setStandardId(standardId);
                stdItem.setStdItemId(slb.getTspStdItemId());
                stdItemList.add(stdItem);
            }
        }

        // standardId 为空 调接口
        if (null != stdItemList && stdItemList.size() > 0) {
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(stdItemList, null,
                NumberConst.IntDef.INT_SIX);
            ProductStdResult productStdResult = responce.getResult();
            tspStdBeanList = productStdResult.getTspStdlist();
        }

        // 接口数据处理
        if (null != tspStdBeanList && tspStdBeanList.size() > 0) {
            SL241125BeansR = setSL241125BeanAppend(tspStdBeanList, SL241125BeanMap, SL241125BeanSIdMap);
        }
        return SL241125BeansR;
    }

    /**
     * 处理 卖家产品储存运输标准数据 接口数据合并
     *
     * @param tspStdBeanList
     * @param SL241125BeanMap
     * @param SL241125BeanSIdMap
     * @return
     */
    private List<SL241125Bean> setSL241125BeanAppend(List<TspStdBean> tspStdBeanList,
        Map<String, Object> SL241125BeanMap, Map<String, Integer> SL241125BeanSIdMap) {
        List<SL241125Bean> SL241125BeansR = new ArrayList<SL241125Bean>();
        for (TspStdBean slb : tspStdBeanList) {
            // 处理 levelId = 1
            String key = slb.getStandardId() + slb.getTspStdItemId();
            List<SL241125Bean> SL241125BeanListV = (List<SL241125Bean>) SL241125BeanMap.get(key);
            if (null != SL241125BeanListV && SL241125BeanListV.size() > 0) {
                for (SL241125Bean SL241125BeanV : SL241125BeanListV) {
                    SL241125Bean SL241125BeanM = new SL241125Bean();
                    // 合并数据 levelId = 1
                    SL241125BeanM = setSL241125Bean(slb, SL241125BeanV);
                    // 处理 levelId = 2
                    List<SL241125Bean> SL241125BeanListM = setSL241125BeanList(slb, SL241125BeanMap);
                    if (null != SL241125BeanListM && SL241125BeanListM.size() > 0) {
                        SL241125BeanM.setPdTspStds(SL241125BeanListM);
                    }
                    // add 数据
                    SL241125BeansR.add(SL241125BeanM);
                }
            } else {
                SL241125Bean SL241125BeanM = new SL241125Bean();
                // 根据 standardId 获取 slPdId
                SL241125Bean SL241125BeanN = new SL241125Bean();
                Integer slPdId = SL241125BeanSIdMap.get(slb.getStandardId() + "");
                SL241125BeanN.setSlPdId(slPdId);
                // 合并数据 levelId = 1
                SL241125BeanM = setSL241125Bean(slb, SL241125BeanN);
                // 处理 levelId = 2
                List<SL241125Bean> SL241125BeanListM = setSL241125BeanList(slb, SL241125BeanMap);
                if (null != SL241125BeanListM && SL241125BeanListM.size() > 0) {
                    SL241125BeanM.setPdTspStds(SL241125BeanListM);
                }
                // add 数据
                SL241125BeansR.add(SL241125BeanM);
            }
        }
        return SL241125BeansR;
    }

    /**
     * 处理 卖家产品储存运输标准数据 list
     *
     * @param slb
     * @param SL241125BeanMap
     * @return
     */
    private List<SL241125Bean> setSL241125BeanList(TspStdBean slb, Map<String, Object> SL241125BeanMap) {
        List<SL241125Bean> SL241125BeanList = new ArrayList<SL241125Bean>();
        List<TspStdBean> tspStdBeans = slb.getPdTspStds();
        if (null != tspStdBeans && tspStdBeans.size() > 0) {
            for (TspStdBean slbs : tspStdBeans) {
                String key = slbs.getStandardId() + slbs.getTspStdItemId();
                List<SL241125Bean> SL241125BeanListV = (List<SL241125Bean>) SL241125BeanMap.get(key);
                if (null != SL241125BeanListV && SL241125BeanListV.size() > 0) {
                    for (SL241125Bean SL241125BeanV : SL241125BeanListV) {
                        String delFlg = SL241125BeanV.getDelFlg();
                        if (StringUtil.isNullOrEmpty(delFlg) || "0".equals(delFlg)) {
                            SL241125Bean SL241125BeanMs = new SL241125Bean();
                            // 合并数据 levelId = 2
                            SL241125BeanMs = setSL241125Bean(slbs, SL241125BeanV);
                            SL241125BeanList.add(SL241125BeanMs);
                        }
                    }
                } else {
                    SL241125Bean SL241125BeanM = new SL241125Bean();
                    SL241125BeanM = setSL241125Bean(slbs, new SL241125Bean());
                    SL241125BeanList.add(SL241125BeanM);
                }
            }
        }
        return SL241125BeanList;
    }

    /**
     * 处理 卖家产品储存运输标准数据 javabean
     *
     * @param tspStdBean
     * @param SL241125BeanV
     * @return
     */
    private SL241125Bean setSL241125Bean(TspStdBean tspStdBean, SL241125Bean SL241125BeanV) {
        SL241125Bean SL241125Bean = new SL241125Bean();

        SL241125Bean.setStandardId(tspStdBean.getStandardId());
        SL241125Bean.setTspStdItemId(tspStdBean.getTspStdItemId());
        SL241125Bean.setTspGoodVal(tspStdBean.getTspGoodVal());
        SL241125Bean.setTspOkVal(tspStdBean.getTspOkVal());
        SL241125Bean.setTspNgVal(tspStdBean.getTspNgVal());
        SL241125Bean.setTspStdItemName(tspStdBean.getTspStdItemName());
        SL241125Bean.setLevelId(tspStdBean.getLevelId());
        SL241125Bean.setParentId(tspStdBean.getParentId());
        SL241125Bean.setIsCatalog(tspStdBean.getIsCatalog());

        SL241125Bean.setAgreeFlg(SL241125BeanV.getAgreeFlg());
        SL241125Bean.setSlCode(SL241125BeanV.getSlCode());
        SL241125Bean.setSlPdId(SL241125BeanV.getSlPdId());
        return SL241125Bean;
    }

    /**
     * 准备 卖家产品安全标准数据
     *
     * @param params
     * @return
     */
    public List<SL241126Bean> getSftStdInfo(BaseParam params) {
        List<SL241126Bean> SL241126BeansR = new ArrayList<SL241126Bean>();
        String standardId = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        if (!StringUtil.isNullOrEmpty(standardId)) {
            // standardId 不为空 调用接口查询数据
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(new ArrayList<StdItem>(), standardId,
                NumberConst.IntDef.INT_SEVEN);
            ProductStdResult productStdResult = responce.getResult();
            if (productStdResult == null) {
                productStdResult = new ProductStdResult();
            }
            List<SftStdBean> sftStdBeanList = productStdResult.getSftStdlist();
            if (null != sftStdBeanList && sftStdBeanList.size() > 0) {
                // 处理接口数据
                SL241126BeansR = getSftStdData(params, sftStdBeanList);
            }
        } else {
            SL241126BeansR = getSftStdData(params, new ArrayList<SftStdBean>());
        }
        return SL241126BeansR;
    }

    /**
     * 处理 卖家产品安全标准数据
     *
     * @param params
     * @param sftStdBeanList
     * @return
     */
    @Transactional
    private List<SL241126Bean> getSftStdData(BaseParam params, List<SftStdBean> sftStdBeanList) {
        List<SL241126Bean> SL241126BeansR = new ArrayList<SL241126Bean>();
        String standardIdParam = StringUtil.toSafeString(params.getFilterMap().get("standardId"));
        // 查询数据
        List<SL241126Bean> sl241126Beans = super.findList(SqlId.SQL_ID_GET_SFT_STD_INFO, params);
        // 卖家查询数据处理
        Map<String, Object> SL241126BeanMap = new HashMap<String, Object>();
        Map<String, Integer> SL241126BeanSIdMap = new HashMap<String, Integer>();
        List<StdItem> stdItemList = new ArrayList<StdItem>();
        for (SL241126Bean slb : sl241126Beans) {
            // standardId + gnqStdItemId 对 数组
            String standardId = slb.getStandardId() + "";
            String key = standardId + slb.getSftStdItemId();
            List<SL241126Bean> SL241126BeansV = (List<SL241126Bean>) SL241126BeanMap.get(key);
            if (null == SL241126BeansV) {
                SL241126BeansV = new ArrayList<SL241126Bean>();
            }
            SL241126BeansV.add(slb);
            SL241126BeanMap.put(key, SL241126BeansV);

            // standardId 和 slPdId 一对一
            SL241126BeanSIdMap.put(standardId, slb.getSlPdId());

            if (StringUtil.isNullOrEmpty(standardIdParam)) {
                // 准备接口数据
                StdItem stdItem = new StdItem();
                stdItem.setStandardId(standardId);
                stdItem.setStdItemId(slb.getSftStdItemId());
                stdItemList.add(stdItem);
            }
        }

        // standardId 为空 调接口
        if (null != stdItemList && stdItemList.size() > 0) {
            RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(stdItemList, null,
                NumberConst.IntDef.INT_SEVEN);
            ProductStdResult productStdResult = responce.getResult();
            sftStdBeanList = productStdResult.getSftStdlist();
        }

        // 接口数据处理
        if (null != sftStdBeanList && sftStdBeanList.size() > 0) {
            SL241126BeansR = setSL241126BeanAppend(sftStdBeanList, SL241126BeanMap, SL241126BeanSIdMap);
        }
        return SL241126BeansR;
    }

    /**
     * 处理 卖家产品安全标准数据 接口数据合并
     *
     * @param gnqStdBeanList
     * @param SL241126BeanMap
     * @param SL241126BeanSIdMap
     * @return
     */
    private List<SL241126Bean> setSL241126BeanAppend(List<SftStdBean> gnqStdBeanList,
        Map<String, Object> SL241126BeanMap, Map<String, Integer> SL241126BeanSIdMap) {
        List<SL241126Bean> SL241126BeansR = new ArrayList<SL241126Bean>();
        for (SftStdBean slb : gnqStdBeanList) {
            // 处理 levelId = 1
            String key = slb.getStandardId() + slb.getSftStdItemId();
            List<SL241126Bean> SL241126BeanListV = (List<SL241126Bean>) SL241126BeanMap.get(key);
            if (null != SL241126BeanListV && SL241126BeanListV.size() > 0) {
                for (SL241126Bean SL241126BeanV : SL241126BeanListV) {
                    SL241126Bean SL241126BeanM = new SL241126Bean();
                    // 合并数据 levelId = 1
                    SL241126BeanM = setSL241126Bean(slb, SL241126BeanV);
                    // 处理 levelId = 2
                    List<SL241126Bean> SL241126BeanListM = setSL241126BeanList(slb, SL241126BeanMap);
                    if (null != SL241126BeanListM && SL241126BeanListM.size() > 0) {
                        SL241126BeanM.setPdSftStds(SL241126BeanListM);
                    }
                    // add 数据
                    SL241126BeansR.add(SL241126BeanM);
                }
            } else {
                SL241126Bean SL241126BeanM = new SL241126Bean();
                // 根据 standardId 获取 slPdId
                SL241126Bean SL241126BeanN = new SL241126Bean();
                Integer slPdId = SL241126BeanSIdMap.get(slb.getStandardId() + "");
                SL241126BeanN.setSlPdId(slPdId);
                SL241126BeanM = setSL241126Bean(slb, SL241126BeanN);
                // 处理 levelId = 2
                List<SL241126Bean> SL241126BeanListM = setSL241126BeanList(slb, SL241126BeanMap);
                if (null != SL241126BeanListM && SL241126BeanListM.size() > 0) {
                    SL241126BeanM.setPdSftStds(SL241126BeanListM);
                }
                // add 数据
                SL241126BeansR.add(SL241126BeanM);
            }
        }
        return SL241126BeansR;
    }

    /**
     * 处理 卖家产品安全标准数据 list
     *
     * @param slb
     * @param SL241126BeanMap
     * @return
     */
    private List<SL241126Bean> setSL241126BeanList(SftStdBean slb, Map<String, Object> SL241126BeanMap) {
        List<SL241126Bean> SL241126BeanList = new ArrayList<SL241126Bean>();
        List<SftStdBean> sftStdBeans = slb.getPdSftStds();
        if (null != sftStdBeans && sftStdBeans.size() > 0) {
            for (SftStdBean slbs : sftStdBeans) {
                String key = slbs.getStandardId() + slbs.getSftStdItemId();
                List<SL241126Bean> SL241126BeanListV = (List<SL241126Bean>) SL241126BeanMap.get(key);
                if (null != SL241126BeanListV && SL241126BeanListV.size() > 0) {
                    for (SL241126Bean SL241126BeanV : SL241126BeanListV) {
                        String delFlg = SL241126BeanV.getDelFlg();
                        if (StringUtil.isNullOrEmpty(delFlg) || "0".equals(delFlg)) {
                            SL241126Bean SL241126BeanMs = new SL241126Bean();
                            // 合并数据 levelId = 2
                            SL241126BeanMs = setSL241126Bean(slbs, SL241126BeanV);
                            SL241126BeanList.add(SL241126BeanMs);
                        }
                    }
                } else {
                    SL241126Bean SL241126BeanM = new SL241126Bean();
                    SL241126BeanM = setSL241126Bean(slbs, new SL241126Bean());
                    SL241126BeanList.add(SL241126BeanM);
                }
            }
        }
        return SL241126BeanList;
    }

    /**
     * 处理 卖家产品安全标准数据 javabean
     *
     * @param sftStdBean
     * @param SL241126BeanV
     * @return
     */
    private SL241126Bean setSL241126Bean(SftStdBean sftStdBean, SL241126Bean SL241126BeanV) {
        SL241126Bean SL241126Bean = new SL241126Bean();

        SL241126Bean.setStandardId(sftStdBean.getStandardId());
        SL241126Bean.setSftStdItemId(sftStdBean.getSftStdItemId());
        SL241126Bean.setSftGoodVal(sftStdBean.getSftGoodVal());
        SL241126Bean.setSftOkVal(sftStdBean.getSftOkVal());
        SL241126Bean.setSftNgVal(sftStdBean.getSftNgVal());
        SL241126Bean.setSftStdItemName(sftStdBean.getSftStdItemName());
        SL241126Bean.setLevelId(sftStdBean.getLevelId());
        SL241126Bean.setParentId(sftStdBean.getParentId());
        SL241126Bean.setIsCatalog(sftStdBean.getIsCatalog());

        SL241126Bean.setAgreeFlg(SL241126BeanV.getAgreeFlg());
        SL241126Bean.setSlCode(SL241126BeanV.getSlCode());
        SL241126Bean.setSlPdId(SL241126BeanV.getSlPdId());
        return SL241126Bean;
    }

}
