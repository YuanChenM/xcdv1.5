package com.msk.seller.logic;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.logic.CommonLogic;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.hoperun.core.consts.NumberConst;
import com.msk.core.entity.*;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.consts.SystemConst;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.*;
import com.msk.seller.utils.ISLRestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * ISL231106Logic.
 *
 * @author gyh
 */
@Service
public class ISL231106Logic extends BaseLogic {
    @Autowired
    private Sl241116Logic sl241116Logic;
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SL241118Logic sl241118Logic;
    @Autowired
    private SL241119Logic sl241119Logic;
    @Autowired
    private SL241117Logic sl241117Logic;

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
        static final String SQL_ID_FIND_SL_PD_ORG_STD_COUNT = "findSlPdTncStdCount";
        static final String SQL_ID_FIND_SL_PD_ORG_STD_LIST = "findSlPdTncStdList";
    }

    /**
     * 查询其他指标
     *
     * @param other 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public int findSlPdTncStdCount(BaseEntity other) {
        return this.getCount(SqlId.SQL_ID_FIND_SL_PD_ORG_STD_COUNT, other);
    }

    /**
     * 查询其他指标
     *
     * @param slPdTncStdOthers 参数
     * @return 结果
     */
    @Transactional
    public void findSlPdTncStdList(List<SlPdTncStdOther> slPdTncStdOthers) {
        BasePageParam param = new BasePageParam();
        param.getFilterMap().put("slPdTncStdOthers", slPdTncStdOthers);
        // 封装数据
        List<SlProduct> slPdTncList = new ArrayList<SlProduct>();
        List<SlPdTncStdOther> others = this.findList(SqlId.SQL_ID_FIND_SL_PD_ORG_STD_LIST, param);
        Map<String, String> maps = new HashMap<String, String>();
        if (!CollectionUtils.isEmpty(others)) {
            for (SlPdTncStdOther other : others) {
                String key = other.getSlCode() + "_" + other.getSlPdId() + "_" + other.getStandardId() + "_"
                        + other.getTncStdItemId();
                maps.put(key, other.getSlCode());
            }
        }
        for (SlPdTncStdOther other : slPdTncStdOthers) {
            String key = other.getSlCode() + "_" + other.getSlPdId() + "_" + other.getStandardId() + "_"
                    + other.getTncStdItemId();
            String value = maps.get(key);
            other.setCrtTime(DateTimeUtil.getCustomerDate());
            if (value == null) {
                this.save(other);
            } else {
                this.modify(other);
            }
            // 封装数据
            SlProduct slProduct = new SlProduct();
            slProduct.setSlPdId(other.getSlPdId());
            slProduct.setSlCode(other.getSlCode());
            slPdTncList.add(slProduct);
        }
        // 数据校验 获取不符合规范的信息（后期可拓展 也可获取校验正确的额数据列表）
        List<SlProduct> errParamList = sl241117Logic.checkAgreeNew(slPdTncList);
        for (SlProduct product : errParamList) {
            // 修改卖家产品为论证中
            product.setStatus("2");
        }
        // 修改卖家产品状态(方法暂时未做修改)
        this.sl241116Logic.upSlPdStatus(errParamList);

    }

    /**
     * 卖家产品及卖家产品加工技术标准维护
     *
     * @param slPdQtyList 卖家产品加工技术标准信息
     * @return 操作结果
     */
    @Transactional(readOnly = false,
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class)
    public String mainTainSlPdQty(List<ISL231106RsSlPdQty> slPdQtyList) {
        String mainTail = "";

        // 批量查询卖家可卖产品
        BasePageParam basePageParam = new BasePageParam();
        List<String> slCodeAndSlPdIds = new ArrayList<String>();
        List<String> slCodeSlPdIdStdItemIds = new ArrayList<String>();
        for (ISL231106RsSlPdQty slPdQty : slPdQtyList) {
            slCodeAndSlPdIds.add(slPdQty.getSlCode() + slPdQty.getSlPdId());
            for (ISL231106RsPdQltStd std : slPdQty.getSlPdMctStdList()) {
                slCodeSlPdIdStdItemIds.add(std.getSlCode() + slPdQty.getSlPdId() + std.getStdItemId());
            }
        }
        basePageParam.setPaging(false);
        basePageParam.setFilterObject("slCodeAndSlPdIds", slCodeAndSlPdIds);
        List<SL241116Bean> sl241116Beans = sl241116Logic.findSlProductSList(basePageParam);
        // 卖家可卖产品结果集
        Map<String, SL241116Bean> slPdQtyMaps = new HashMap<>();
        for (SL241116Bean sl241116Bean : sl241116Beans) {
            slPdQtyMaps.put(sl241116Bean.getSlCode() + sl241116Bean.getSlPdId(), sl241116Bean);
        }

        // 产品加工技术标准结果集
        Map<String, SlPdMctStdNew> slPdMctStdNewMaps = new HashMap<>();
        if (!CollectionUtils.isEmpty(slCodeSlPdIdStdItemIds) && slCodeSlPdIdStdItemIds.size() > 0) {
            // 批量查询卖家该产品加工技术标准
            BaseParam param = new BaseParam();
            basePageParam.setFilterObject("slCodeSlPdIdStdItemIds", slCodeSlPdIdStdItemIds);
            List<SlPdMctStdNew> slPdMctStdNews = sl241118Logic.findSlPdMctStdNews(param);
            for (SlPdMctStdNew slPdMctStdNew : slPdMctStdNews) {
                String key = slPdMctStdNew.getSlCode() + slPdMctStdNew.getSlPdId() + slPdMctStdNew.getStdItemId();
                slPdMctStdNewMaps.put(key, slPdMctStdNew);
            }
        }

        // 需要保存的产品加工技术标准表(论证中供应商习惯标准）
        List<PdMctStdDiscussProvider> needSaveProviders = new ArrayList<>();
        for (ISL231106RsSlPdQty slPdQty : slPdQtyList) {
            String key = slPdQty.getSlCode() + slPdQty.getSlPdId();
            Integer maxId = null;
            SlProduct slProduct = new SlProduct();
            if (!slPdQtyMaps.containsKey(key)) {
                // 批量保存的产品加工质量标准表(论证中供应商习惯标准）
                this.batchInsertMctProvider(needSaveProviders);
                // 不存在
                throw new BusinessException("卖家产品ID为" + slPdQty.getSlPdId() + "不存在！");
            } else {
                // 存在,修改
                slProduct = slPdQtyMaps.get(key);
                maxId = slProduct.getSlPdId();
                slProduct.setUpdId(slPdQty.getLoginId());
                slProduct.setSlQltStd(slPdQty.getSlQltStd());
                slProduct.setSlQltGradeCode(slPdQty.getSlQltGradeCode());
                sl241118Logic.modifySlProduct(slProduct);

                Date nowDate = DateTimeUtil.getCustomerDate();
                for (ISL231106RsPdQltStd std : slPdQty.getSlPdMctStdList()) {
                    String subKey = slPdQty.getSlCode() + maxId + std.getStdItemId();
                    SlPdMctStdNew stdNew = new SlPdMctStdNew();
                    stdNew.setCrtTime(nowDate);
                    stdNew.setUpdTime(nowDate);
                    if (slPdMctStdNewMaps.containsKey(subKey)) {
                        // 修改
                        if (std.getVer() == null) {
                            std.setVer(slPdMctStdNewMaps.get(subKey).getVer() + 1);
                        }
                        stdNew = std;
                        std.setSlPdId(maxId);
                        stdNew.setUpdId(std.getLoginId());
                        stdNew.setDelFlg(std.getDelFlg());
                        sl241118Logic.modify(stdNew);
                    } else {
                        // 新增
                        stdNew = std;
                        std.setSlPdId(maxId);
                        stdNew.setCrtId(std.getLoginId());
                        sl241118Logic.save(stdNew);
                    }
                    // 不同意的处理方式
                    if ("0".equals(std.getAgreeFlg())) {
                        PdMctStdDiscussProvider provide = new PdMctStdDiscussProvider();
                        provide.setStandardId(std.getStandardId().longValue());
                        provide.setSlPdId(std.getSlPdId());
                        provide.setMctStdItemId(std.getStdItemId());
                        provide.setMctStdVal(std.getStdVal());
                        provide.setProviderCode(std.getSlCode());
                        provide.setProviderName(std.getSlCode());
                        provide.setDiscussStatus(0);
                        stdNew.setStdItemId(std.getStdItemId());
                        provide.setCrtId(std.getLoginId());
                        provide.setCrtTime(nowDate);
                        // provide.setKeyId(commonLogic.maxId("PD_MCT_STD_DISCUSS_PROVIDER", "KEY_ID"));
                        // sl241118Logic.saveNotAgree(provide);
                        needSaveProviders.add(provide);
                        slProduct.setSlQltGradeCode(4);
                        sl241118Logic.modifySlProduct(slProduct);
                    }
                }
            }
            // 全部同意判断其他标准是否符合要求
            String checkRs = sl241117Logic.checkAgree(basePageParam);
            if ("1".equals(checkRs)) {
                // 修改卖家产品为试销
                // slProduct.setStatus("4");
                // this.sl241116Logic.upSlPdStatus(slProduct);
            } else {
                // 修改卖家产品为论证中
                slProduct.setStatus("2");
                this.sl241116Logic.upSlPdStatus(slProduct);
            }
        }

        // 批量保存不同意 处理方式
        return this.batchInsertMctProvider(needSaveProviders);
    }

    // 调用接口 批量保存的产品加工技术标准表(论证中供应商习惯标准）
    @Transactional(readOnly = false,
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class)
    private String batchInsertMctProvider(List<PdMctStdDiscussProvider> needSaveProviders) {
        String mainTail = "";
        int result = sl241118Logic.saveNotAgree(needSaveProviders);
        if (result == needSaveProviders.size()) {
            mainTail = "更新卖家产品加工技术标准成功！";
        } else {
            mainTail = "要求批量保存 " + needSaveProviders.size() + " 条，接口实际批量保存" + result + "条记录";
        }
        return mainTail;
    }

    /**
     * 卖家产品维护
     *
     * @param slPdList 卖家产品信息
     * @return 操作名称
     */
    @Transactional
    public String mainTainSlProduct(List<ISL231106RsSlProduct> slPdList) {
        String mainTail = "";
        List<SlProduct> delSlProList = new ArrayList<SlProduct>();
        List<SlProduct> slProductList = new ArrayList<SlProduct>();
        for(ISL231106RsSlProduct slProduct:slPdList){
            if("1".equals(slProduct.getDelFlg())){
                // 需要删除操作的
                slProduct.setUpdId(slProduct.getLoginId());
                delSlProList.add(slProduct);
            }else{
                // 需要业务处理的 用于修改
                slProduct.setUpdId(slProduct.getLoginId());
                // 用于新增
                slProduct.setCrtId(slProduct.getLoginId());
                slProductList.add(slProduct);
            }
        }
        // 删除操作的
        mainTail = sl241116Logic.delSlProducts(delSlProList);
        // 业务数据操作
        sl241116Logic.slProductHandle(slProductList);

        return mainTail;
    }

    /**
     * 卖家产品加工质量标准维护
     * 管忠恒
     * 
     * @param slRsPdTncList 卖家产品信息
     * @return 操作名称
     */
    @Transactional
    public String mainTainSlPdTncNew(List<ISL231106RsSlPdTnc> slRsPdTncList) {
        String mainTail = "";
        // 数据封装
        List<SlProduct> slPdTncList = new ArrayList<SlProduct>();
        // 产品加工质量标准维护数据获取
        List<SlPdTncStdNew> slPdTncStdNewList = new ArrayList<SlPdTncStdNew>();
        // 不同意的处理方式
        List<PdTncStdDiscussProvider> providerList = new ArrayList<PdTncStdDiscussProvider>();
        for (ISL231106RsSlPdTnc slPdTnc : slRsPdTncList) {
            SlProduct slProduct = new SlProduct();
            // 参数传递进入时候已经 validator验证过
            slProduct.setSlCode(slPdTnc.getSlCode());
            slProduct.setSlPdId(slPdTnc.getSlPdId());
            slProduct.setSlTncStd(slPdTnc.getSlTncStd());
            slProduct.setSlTncGradeCode(slPdTnc.getSlTncGradeCode());
            // 查询条件不存在可以优先存入SQL语句不影响
            slProduct.setUpdId(slPdTnc.getLoginId());
            slPdTncList.add(slProduct);
            // 获取校验数据信息
            // 提取卖家产品加工质量标准维护数据
            List<ISL231106RsPdTncStd> pdTncStds = slPdTnc.getSlPdTncStdList();
            for (ISL231106RsPdTncStd tncStd : pdTncStds) {
                // 获取单个加工质量对象
                SlPdTncStdNew slPdTncStdNew = new SlPdTncStdNew();
                slPdTncStdNew.setSlCode(tncStd.getSlCode());
                slPdTncStdNew.setSlPdId(slPdTnc.getSlPdId());
                slPdTncStdNew.setStandardId(tncStd.getStandardId());
                slPdTncStdNew.setStdItemId(tncStd.getStdItemId());
                slPdTncStdNew.setCrtId(tncStd.getLoginId());
                slPdTncStdNew.setAgreeFlg(tncStd.getAgreeFlg());
                slPdTncStdNew.setStdVal(tncStd.getStdVal());
                slPdTncStdNew.setDelFlg(tncStd.getDelFlg());
                slPdTncStdNewList.add(slPdTncStdNew);
                // 获取产品技术标准信息
                if (!StringUtils.isEmpty(tncStd.getAgreeFlg()) && "0".equals(tncStd.getAgreeFlg())) {
                    PdTncStdDiscussProvider provide = new PdTncStdDiscussProvider();
                    provide.setStandardId(tncStd.getStandardId().longValue());
                    provide.setSlPdId(tncStd.getSlPdId());
                    provide.setTncStdItemId(tncStd.getStdItemId());
                    provide.setTncStdVal(tncStd.getStdVal());
                    provide.setProviderCode(tncStd.getSlCode());
                    provide.setProviderName(tncStd.getSlCode());
                    provide.setDiscussStatus(0);
                    provide.setCrtId(tncStd.getLoginId());
                    providerList.add(provide);

                    slProduct.setSlTncGradeCode(4);
                }
            }
        }
        // 数据校验 获取不符合规范的信息（后期可拓展 也可获取校验正确的额数据列表）
        List<SlProduct> errParamList = sl241117Logic.checkAgreeNew(slPdTncList);
        for (SlProduct product : errParamList) {
            // 修改卖家产品为论证中
            product.setStatus("2");
        }
        // 修改卖家产品状态(方法暂时未做修改)
        this.sl241116Logic.upSlPdStatus(errParamList);

        // 处理卖家产品信息
        List<SL241116Bean> sl241116Beans = sl241116Logic.findSlProductList(slPdTncList);
        if (!CollectionUtils.isEmpty(sl241116Beans)) {
            // 批量修改
            sl241117Logic.modifySlProduct(sl241116Beans);
        }
        // 维护加工质量标准
        sl241117Logic.findSlPdTncStdList(slPdTncStdNewList);

        // 产品加工质量标准维护数据获取（调用接口）
        sl241117Logic.saveNotAgree(providerList);

        // 调用接口 批量保存不同意
        int result = sl241117Logic.saveNotAgree(providerList);
        if (result != providerList.size()) {
            return mainTail = "要求批量保存 " + providerList.size() + " 条，接口实际批量保存" + result + "条记录";
        }
        return "更新卖家产品加工质量标准成功！";
    }

    /**
     * 卖家产品包装标准维护
     *
     * @param slPdPkgList 卖家产品信息
     * @return 操作名称
     */
    @Transactional
    public String mainTainSlPdPkg(List<ISL231106RsSlPdPkg> slPdPkgList) {
        String mainTail = "";

        BasePageParam basePageParam = setSlPdPkgPrarm(slPdPkgList);
        basePageParam.setPaging(false);
        // 根据卖家产品id查询产品信息
        List<SL241116Bean> sl241116Beans = sl241116Logic.findSlProductSList(basePageParam);
        Map<String, SL241116Bean> sl241116BeansMap = new HashMap<>();
        for (SL241116Bean sl241116Bean : sl241116Beans) {
            sl241116BeansMap.put(
                "slPdId" + ":" + sl241116Bean.getSlCode() + ":" + StringUtil.toSafeString(sl241116Bean.getSlPdId()),
                sl241116Bean);
        }

        List<SL241119Bean> sl241119Slpkglist = sl241119Logic.findslPkgList(basePageParam);
        Map<String, SL241119Bean> sl241119SlpkgMap = new HashMap<>();
        for (SL241119Bean SL241119Bean : sl241119Slpkglist) {
            sl241119SlpkgMap.put("standardId" + ":" + StringUtil.toSafeString(SL241119Bean.getStandardId()) + ":"
                    + StringUtil.toSafeString(SL241119Bean.getNormsCode()),
                SL241119Bean);
        }

        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.getFilterMap().put("normsCodes", basePageParam.getFilterMap().get("normsCodes"));
        pdInfoParam.getFilterMap().put("slCodes", basePageParam.getFilterMap().get("slCodes"));
        pdInfoParam.getFilterMap().put("standardIds", basePageParam.getFilterMap().get("standardIds"));
        pdInfoParam.getFilterMap().put("slPdIds", basePageParam.getFilterMap().get("slPdIds"));
        pdInfoParam.getFilterMap().put("slPdPkgIds", basePageParam.getFilterMap().get("slPdPkgIds"));
        RsResponse<ProductBeanResult> productInfoResponse = ISLRestUtil.findpdNormsInfos(pdInfoParam);
        List<PdNormsStd> pdNormsStds = productInfoResponse.getResult().getPdNormsStds();
        Map<String, SL241119Bean> pdNormsStdsmap = new HashMap<>();
        for (PdNormsStd pdNormsStd : pdNormsStds) {
            SL241119Bean sl241119Bean = copyPdNormsStd(pdNormsStd);
            SL241119Bean SL241119Beanmap = sl241119SlpkgMap
                .get("standardId" + ":" + StringUtil.toSafeString(pdNormsStd.getStandardId()) + ":"
                        + StringUtil.toSafeString(pdNormsStd.getNormsCode()));
            if (SL241119Beanmap != null) {
                /** 是否已经选中 */
                sl241119Bean.setSlPdPkgId(SL241119Beanmap.getSlPdPkgId());
                /** 卖家产品ID */
                sl241119Bean.setCheckFlag(SL241119Beanmap.getCheckFlag());
            } else {
                /** 卖家产品ID */
                sl241119Bean.setSlPdPkgId(0);
                /** 是否已经选中 */
                sl241119Bean.setCheckFlag(StringConst.EMPTY);
            }
            sl241119Bean.setSlCode(SL241119Beanmap.getSlCode());
            sl241119Bean.setSlPdId(SL241119Beanmap.getSlPdId());
            pdNormsStdsmap.put(SL241119Beanmap.getSlCode() + ":" + SL241119Beanmap.getSlPdId(), sl241119Bean);
        }
        // 封装数据
        List<SlProduct> slProductList = new ArrayList<SlProduct>();
        for (ISL231106RsSlPdPkg slPdPkg : slPdPkgList) {
            SL241116Bean sl241116Bean = sl241116BeansMap
                .get("slPdId" + ":" + slPdPkg.getSlCode() + ":" + StringUtil.toSafeString(slPdPkg.getSlPdId()));

            if (sl241116Bean != null) {
                SL241119Bean sl241119Bean = pdNormsStdsmap
                    .get("slPdId" + ":" + slPdPkg.getSlCode() + ":" + StringUtil.toSafeString(slPdPkg.getSlPdId()));
                // 产品包装存在则修改，不存在则提示产品包装不存在
                if (sl241119Bean != null) {
                    SL241119Bean sl241119Param = sl241119Bean;
                    SlPdPkg pdPkg = new SlPdPkg();
                    pdPkg.setSlCode(slPdPkg.getSlCode());
                    pdPkg.setSlPdId(slPdPkg.getSlPdId());
                    pdPkg.setPkgCode(slPdPkg.getPkgCode());
                    pdPkg.setStandardId(slPdPkg.getStandardId());
                    pdPkg.setProdEpId(sl241116Bean.getProdEpId());
                    pdPkg.setBrandEpId(sl241116Bean.getBrandEpId());
                    pdPkg.setBrandId(sl241116Bean.getBrandId());
                    pdPkg.setPdClassesCode(sl241116Bean.getPdClassesCode());
                    pdPkg.setPdBreedCode(sl241116Bean.getPdBreedCode());
                    pdPkg.setPdFeatureCode(sl241116Bean.getPdFeatureCode());
                    pdPkg.setInSglNw(sl241119Param.getNormsSuttle());
                    pdPkg.setInSglNwRange(sl241119Param.getNormsError());
                    pdPkg.setInNw(sl241119Param.getNetweightInner());
                    pdPkg.setInNumber(sl241119Param.getNormsNumber());
                    pdPkg.setInSize(sl241119Param.getNormsSize());
                    pdPkg.setInMts(sl241119Param.getNormsOutTexture());
                    pdPkg.setOutSpec(sl241119Param.getNormsOut());
                    pdPkg.setOutNw(sl241119Param.getNetweightOut());
                    pdPkg.setOutGw(sl241119Param.getNormsKg());
                    pdPkg.setOutSize(sl241119Param.getNormsOutSize());
                    pdPkg.setOutMts(sl241119Param.getNormsOutTexture());
                    pdPkg.setPkgTen(sl241119Param.getNormsTen());
                    pdPkg.setOutLength(sl241119Param.getNormsLength());
                    pdPkg.setOutWidth(sl241119Param.getNormsWidth());
                    pdPkg.setOutHeight(sl241119Param.getNormsHeight());
                    pdPkg.setOutVolume(sl241119Param.getNormsVolume());
                    pdPkg.setCrtId(slPdPkg.getLoginId());
                    pdPkg.setUpdId(slPdPkg.getLoginId());
                    // 判断卖家产品包装是否存在，存在修改，不存在新增
                    if (sl241119Param.getSlPdPkgId() == null) {
                        pdPkg.setSlPdPkgId(commonLogic.maxId("SL_PD_PKG", "SL_PD_PKG_ID").intValue());
                        this.sl241116Logic.saveSlPdPkg(pdPkg);
                        mainTail = "新增卖家产品包装成功！";
                    } else {
                        // 修改卖家产品包装
                        pdPkg.setUpdId(slPdPkg.getLoginId());
                        if(StringUtil.isNullOrEmpty(slPdPkg.getDelFlg())){
                            pdPkg.setDelFlg("0");
                        }else{
                            pdPkg.setDelFlg(slPdPkg.getDelFlg());
                        }
                        this.sl241116Logic.modifySlPdPkg(pdPkg);
                        mainTail = "更新卖家产品包装成功！";
                    }
                } else {
                    throw new BusinessException(
                        "产品包装" + slPdPkg.getPkgCode() + "产品标准" + slPdPkg.getStandardId() + "不存在！请检查后提交。");
                }
            } else {
                // 卖家产品id不存在
                throw new BusinessException("卖家产品ID" + slPdPkg.getSlPdId() + "不存在！请检查后提交。");
            }

            // 全部同意判断其他标准是否符合要求
            SlProduct slProduct = new SlProduct();
            slProduct.setSlPdId(slPdPkg.getSlPdId());
            slProduct.setSlCode(slPdPkg.getSlCode());
            slProductList.add(slProduct);
        }
        // 数据校验 获取不符合规范的信息（后期可拓展 也可获取校验正确的额数据列表）
        List<SlProduct> errParamList = sl241117Logic.checkAgreeNew(slProductList);
        for (SlProduct product : errParamList) {
            // 修改卖家产品为论证中
            product.setStatus("2");
        }
        // 修改卖家产品状态(方法暂时未做修改)
        this.sl241116Logic.upSlPdStatus(errParamList);
        return mainTail;
    }

    /**
     * 产品安全标准 通用方法 自动根据类型匹配
     * 管忠恒
     * 
     * @param slPdStdList 卖家产品安全标准信息
     * @return 结果
     */
    public String mainTainSlPdStd(List<SlPdTncStdOther> slPdStdList) {
        this.findSlPdTncStdList(slPdStdList);
        return "更新卖家产品安全标准成功！";
    }

    /**
     * 卖家产品信息维护
     *
     * @param param 参数
     * @return 结果
     */
    public RsResponse saveSlQlt(RsRequest<ISL231106RsParam> param) {
        RsResponse rs = new RsResponse();
        ISL231106RsParam iSL231106RsParam = param.getParam();
        List<ISL231106RsSlProduct> slPdList = iSL231106RsParam.getSlPdList();// 卖家能销售的产品信息
        List<ISL231106RsSlPdQty> slPdQtyList = iSL231106RsParam.getSlPdMctList();// 卖家产品质量标准信息List
        List<ISL231106RsSlPdTnc> slPdTncList = iSL231106RsParam.getSlPdTncList();// 卖家产品加工技术标准指标
        List<ISL231106RsSlPdPkg> slPdPkgList = iSL231106RsParam.getSlPdPkgList();// 卖家产品包装标准信息
        List<SlPdTncStdOther> slPdOrgStdList = iSL231106RsParam.getSlPdOrgStdList();// 卖家原种种源标准
        List<SlPdTncStdOther> slPdFedStdList = iSL231106RsParam.getSlPdFedStdList();// 卖家产品饲养标准
        List<SlPdTncStdOther> slPdGnqStdList = iSL231106RsParam.getSlPdGnqStdList();// 卖家产品通用质量标准
        List<SlPdTncStdOther> slPdTspStdList = iSL231106RsParam.getSlPdTspStdList();// 卖家产品储存运输标准
        List<SlPdTncStdOther> slPdSftStdList = iSL231106RsParam.getSlPdSftStdList();// 卖家产品安全标准
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        String message = "";
        // 卖家能销售的产品信息
        if (!CollectionUtils.isEmpty(slPdList) && slPdList.size() > 0) {
            message += this.mainTainSlProduct(slPdList);
        }
        // 卖家产品质量标准信息List
        if (!CollectionUtils.isEmpty(slPdQtyList) && slPdQtyList.size() > 0) {
            message += this.mainTainSlPdQty(slPdQtyList);
        }
        // 卖家产品加工技术标准指标
        if (!CollectionUtils.isEmpty(slPdTncList) && slPdTncList.size() > 0) {
            // 优化调用新处理
            message += this.mainTainSlPdTncNew(slPdTncList);

        }
        // 卖家产品包装标准信息
        if (!CollectionUtils.isEmpty(slPdPkgList) && slPdPkgList.size() > 0) {
            message += this.mainTainSlPdPkg(slPdPkgList);
        }
        // 卖家原种种源标准
        if (!CollectionUtils.isEmpty(slPdOrgStdList) && slPdOrgStdList.size() > 0) {
            message += this.mainTainSlPdStd(slPdOrgStdList);
        }
        // 卖家产品饲养标准
        if (!CollectionUtils.isEmpty(slPdFedStdList) && slPdFedStdList.size() > 0) {
            message += this.mainTainSlPdStd(slPdFedStdList);
        }
        // 卖家产品通用质量标准
        if (!CollectionUtils.isEmpty(slPdGnqStdList) && slPdGnqStdList.size() > 0) {
            message += this.mainTainSlPdStd(slPdGnqStdList);
        }
        // 卖家产品储存运输标准
        if (!CollectionUtils.isEmpty(slPdTspStdList) && slPdTspStdList.size() > 0) {
            message += this.mainTainSlPdStd(slPdTspStdList);
        }
        // 卖家产品安全标准
        if (!CollectionUtils.isEmpty(slPdSftStdList) && slPdSftStdList.size() > 0) {
            message += this.mainTainSlPdStd(slPdSftStdList);
        }
        if (StringUtil.isNullOrEmpty(message)) {
            throw new BusinessException("接口参数不规范，请检查后提交！");
        }
        rs.setMessage(message);
        return rs;
    }

    /**
     * 卖家能销售的产品信息
     *
     * @param slPdList 卖家产品信息
     * @return 操作名称
     */
    public Map<String, BasePageParam> setSlProductPrarm(List<ISL231106RsSlProduct> slPdList) {
        Integer[] slPdIds = new Integer[slPdList.size()];
        String[] slCodes = new String[slPdList.size()];
        Integer[] prodEpIds = new Integer[slPdList.size()];
        Integer[] brandEpIds = new Integer[slPdList.size()];
        Integer[] brandIds = new Integer[slPdList.size()];
        String[] pdCodes1 = new String[slPdList.size()];
        String[] pdCodes2 = new String[slPdList.size()];

        Integer icount = 0;

        for (ISL231106RsSlProduct isl231106SlProduct : slPdList) {
            // 如果删除标志不为空执行删除操作，为空执行其他操作
            if (!StringUtil.isNullOrEmpty(isl231106SlProduct.getDelFlg())
                    && "1".equals(isl231106SlProduct.getDelFlg())) {
                // 卖家产品ID
                Integer slPdId = isl231106SlProduct.getSlPdId();
                slPdIds[icount] = slPdId;
            } else {
                // 卖家编码
                String slCode = isl231106SlProduct.getSlCode();
                slCodes[icount] = slCode;
                // 生产商企业ID
                Integer prodEpId = isl231106SlProduct.getProdEpId();
                prodEpIds[icount] = prodEpId;
                // 品牌商企业ID
                Integer brandEpId = isl231106SlProduct.getBrandEpId();
                brandEpIds[icount] = brandEpId;
                // 产品品牌ID
                Integer brandId = isl231106SlProduct.getBrandId();
                brandIds[icount] = brandId;
                // 产品编码
                String pdCode1 = isl231106SlProduct.getPdClassesCode() + isl231106SlProduct.getMachiningCode()
                        + isl231106SlProduct.getPdBreedCode() + isl231106SlProduct.getPdFeatureCode()
                        + isl231106SlProduct.getWeightCode();
                pdCodes1[icount] = pdCode1;

                String pdCode2 = isl231106SlProduct.getPdClassesCode() + isl231106SlProduct.getMachiningCode()
                        + isl231106SlProduct.getPdBreedCode() + "00" + isl231106SlProduct.getWeightCode();
                pdCodes2[icount] = pdCode2;
            }
            icount++;
        }

        Map<String, BasePageParam> basePageParams = new HashMap<>();
        BasePageParam basePageParam1 = new BasePageParam();
        basePageParam1.getFilterMap().put("slPdIds", slPdIds);
        basePageParams.put("basePageParam1", basePageParam1);

        BasePageParam basePageParam2 = new BasePageParam();
        basePageParam2.getFilterMap().put("slCodes", slCodes);
        basePageParam2.getFilterMap().put("prodEpIds", prodEpIds);
        basePageParam2.getFilterMap().put("brandEpIds", brandEpIds);
        basePageParam2.getFilterMap().put("brandIds", brandIds);
        basePageParam2.getFilterMap().put("pdCodes", pdCodes1);
        basePageParams.put("basePageParam2", basePageParam2);

        BasePageParam basePageParam3 = new BasePageParam();
        basePageParam3.getFilterMap().put("slCodes", slCodes);
        basePageParam3.getFilterMap().put("prodEpIds", prodEpIds);
        basePageParam3.getFilterMap().put("brandEpIds", brandEpIds);
        basePageParam3.getFilterMap().put("brandIds", brandIds);
        basePageParam3.getFilterMap().put("pdCodes", pdCodes2);
        basePageParams.put("basePageParam3", basePageParam3);
        return basePageParams;
    }

    /**
     * 卖家产品包装标准信息
     *
     * @param slPdPkgList 卖家产品包装标准信息
     * @return 操作名称
     */
    public BasePageParam setSlPdPkgPrarm(List<ISL231106RsSlPdPkg> slPdPkgList) {
        String[] normsCodes = new String[slPdPkgList.size()];
        String[] slCodes = new String[slPdPkgList.size()];
        String[] standardIds = new String[slPdPkgList.size()];
        String[] slPdIds = new String[slPdPkgList.size()];
        String[] slPdPkgIds = new String[slPdPkgList.size()];

        Integer icount = 0;
        for (ISL231106RsSlPdPkg isl231106RsSlPdPkg : slPdPkgList) {
            // 卖家产品包装Id
            String normsCode = isl231106RsSlPdPkg.getPkgCode();
            normsCodes[icount] = normsCode;
            // 买家编码
            String slCode = isl231106RsSlPdPkg.getSlCode();
            slCodes[icount] = slCode;
            // 标准产品ID
            String standardId = StringUtil.toSafeString(isl231106RsSlPdPkg.getStandardId());
            standardIds[icount] = standardId;
            // 卖家产品ID
            String slPdId = StringUtil.toSafeString(isl231106RsSlPdPkg.getSlPdId());
            slPdIds[icount] = slPdId;
            // 卖家产品包装ID
            String slPdPkgId = StringUtil.toSafeString(isl231106RsSlPdPkg.getSlPdPkgId());
            slPdPkgIds[icount] = slPdPkgId;
            icount++;
        }

        BasePageParam basePageParam = new BasePageParam();
        basePageParam.getFilterMap().put("normsCodes", normsCodes);
        basePageParam.getFilterMap().put("slCodes", slCodes);
        basePageParam.getFilterMap().put("standardIds", standardIds);
        basePageParam.getFilterMap().put("slPdIds", slPdIds);
        basePageParam.getFilterMap().put("slPdPkgIds", slPdPkgIds);
        return basePageParam;
    }

    /**
     * PdNormsStd copy
     * 
     * @param pdnormsstd
     * @return
     */
    private SL241119Bean copyPdNormsStd(PdNormsStd pdnormsstd) {
        SL241119Bean ret = new SL241119Bean();
        ret.setStandardId(pdnormsstd.getStandardId());
        ret.setNormsCode(pdnormsstd.getNormsCode());
        ret.setNormsSuttle(pdnormsstd.getNormsSuttle());
        ret.setNormsError(pdnormsstd.getNormsError());
        ret.setNormsSize(pdnormsstd.getNormsSize());
        ret.setNormsTexture(pdnormsstd.getNormsTexture());
        ret.setNormsOut(pdnormsstd.getNormsOut());
        ret.setNormsKg(pdnormsstd.getNormsKg());
        ret.setNormsOutSize(pdnormsstd.getNormsOutSize());
        ret.setNormsOutTexture(pdnormsstd.getNormsOutTexture());
        ret.setNormsTen(pdnormsstd.getNormsTen());
        ret.setDelFlg(pdnormsstd.getDelFlg());
        ret.setVer(pdnormsstd.getVer());
        ret.setActId(pdnormsstd.getActId());
        ret.setCrtId(pdnormsstd.getCrtId());
        ret.setUpdId(pdnormsstd.getUpdId());
        ret.setActTime(pdnormsstd.getActTime());
        ret.setCrtTime(pdnormsstd.getCrtTime());
        ret.setUpdTime(pdnormsstd.getUpdTime());
        return ret;
    }

    /**
     * 设置卖家产品品源信息
     */
    public BasePageParam setSlPdOrgStd(List<SlPdTncStdOther> slPdOrgStdList, Integer stdFlag) {
        Integer[] slPdIds = new Integer[slPdOrgStdList.size()];
        String[] slCodes = new String[slPdOrgStdList.size()];
        // Integer[] stdFlags = new Integer[slPdOrgStdList.size()];
        Integer[] standardIds = new Integer[slPdOrgStdList.size()];
        String[] tncStdItems = new String[slPdOrgStdList.size()];
        int icount = 0;
        for (SlPdTncStdOther other : slPdOrgStdList) {
            slPdIds[icount] = other.getSlPdId();
            slCodes[icount] = other.getSlCode();
            // stdFlags[icount] = stdFlag;
            standardIds[icount] = other.getStandardId();
            tncStdItems[icount] = other.getTncStdItemId();
            icount++;
        }
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.getFilterMap().put("slCodes", slCodes);
        basePageParam.getFilterMap().put("slPdIds", slPdIds);
        basePageParam.getFilterMap().put("stdFlag", stdFlag);
        basePageParam.getFilterMap().put("standardIds", standardIds);
        basePageParam.getFilterMap().put("tncStdItems", tncStdItems);
        return basePageParam;
    }
}
