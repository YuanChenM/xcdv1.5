package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductPageResult;
import com.msk.seller.bean.SL241130Param;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yang_chunyan on 2016/6/20.
 */
public class ProviderPackageLogic extends BaseLogic {
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private ProductLogic productLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yang_chunyan
     */
    interface SqlId {
        static final String SQL_ID_FIND_PROVIDER_PACKAGE = "findProviderPackage";
        static final String SQL_ID_SAVE_MCT_PROVIDER = "saveMctProvider";
        static final String SQL_ID_SAVE_PROVIDER_PACKAGE ="saveProviderPackage";
        static final String SQL_ID_SAVE_TNC_PROVIDER ="saveTncProvider";
    }

    /**
     * 查询卖家申请产品审核数据
     * @param param
     * @param <T>
     * @return
     */
    @Transactional
    public <T extends BaseEntity> List<T> findProviderPackagePageList(PDInfoParam param,ProductPageResult pdResult) {
        param.setPaging(true);
        return super.findPageList(param, pdResult);
    }

    /**
     * 插入原料信息
     * @param param
     * @return
     */
    @Transactional
    public int saveMctProvider(PDInfoParam param){
        int count = 0;
        List<PdMctStdDiscussProvider> pdMctStdDiscussProviders = param.getPdMctStdDiscussProviders();
        if(CollectionUtils.isNotEmpty(pdMctStdDiscussProviders)){
            for(PdMctStdDiscussProvider pdMct : pdMctStdDiscussProviders){
                Long keyId = commonLogic.maxId("PD_MCT_STD_DISCUSS_PROVIDER", "KEY_ID");
                pdMct.setKeyId(keyId);
                /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
                Date currentDate = DateTimeUtil.getCustomerDate();
                pdMct.setCrtId(param.getCrtId());
                pdMct.setUpdId(param.getUpdId());
                pdMct.setActId(param.getActId());
                pdMct.setActTime(currentDate);
                pdMct.setUpdTime(currentDate);
                /**Add: 横展开添加共通设置 2016/09/27  BY  任强  End */
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                pdMct.setRaiseDate(new Date());
                pdMct.setCrtTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                super.save(SqlId.SQL_ID_SAVE_MCT_PROVIDER,pdMct);
                count ++;
            }
        }
        return count;
    }

    /**
     * 插入申请产品信息
     * @param param
     * @return
     */
    @Transactional
    public int saveProviderPackage(PDInfoParam param){
        int count = 0;
        List<SL241130Param> pdTcProviderPackages = param.getPdTcProviderPackages();
        if(CollectionUtils.isNotEmpty(pdTcProviderPackages)){
            for(SL241130Param pdPackage : pdTcProviderPackages){
                param.setFilter("classesCode", pdPackage.getClassesCode());
                param.setFilter("machiningCode", pdPackage.getMachiningCode());
                param.setFilter("breedCode", pdPackage.getBreedCode());
                param.setFilter("featureCode", pdPackage.getFeatureCode());
                param.setFilter("weightCode", pdPackage.getWeightCode());
                if ("1".equals(pdPackage.getChooseInfo())) {
                    param.setFilter("breedName", pdPackage.getBreedName());
                    List<PdBreed> breeds = productLogic.findPdBreed(param);
                    //查询产品是否存在该品种，存在则提示，不存在再检查是否已经注册过
                    if (!CollectionUtils.isEmpty(breeds)) {
                        throw new BusinessException("申请品种名称为："+pdPackage.getBreedName()+"已经存在！");
                    } else {
                        List<PDInfoResult> beans = this.findPageList(param, PDInfoResult.class);
                        if (!CollectionUtils.isEmpty(beans)) {
                            throw new BusinessException("申请品种名称为："+pdPackage.getBreedName()+"已在申请中！");
                        }
                    }
                } else if ("2".equals(pdPackage.getChooseInfo())) {
                    param.setFilter("featureName", pdPackage.getFeatureName());
                    List<PdFeature> features = productLogic.findPdFeature(param);
                    if (!CollectionUtils.isEmpty(features)) {
                        throw new BusinessException("申请特征名称为："+pdPackage.getFeatureName()+"已经存在！");
                    } else {
                        List<PDInfoResult> beans = this.findPageList(param, PDInfoResult.class);
                        if (!CollectionUtils.isEmpty(beans)) {
                            throw new BusinessException("申请特征名称为："+pdPackage.getFeatureName()+"已在申请中！");
                        }
                    }
                } else if ("3".equals(pdPackage.getChooseInfo())) {
                    param.setFilter("weightName", pdPackage.getWeightName());
                    param.getFilterMap().put("weightVal", pdPackage.getWeightVal());
                    List<PdWeight> weights = productLogic.findPdWeight(param);
                    if (!CollectionUtils.isEmpty(weights)) {
                        throw new BusinessException("申请净重名称为："+pdPackage.getWeightName()+"已经存在！");
                    } else {
                        List<PDInfoResult> beans = this.findPageList(param, PDInfoResult.class);
                        if (!CollectionUtils.isEmpty(beans)) {
                            throw new BusinessException("申请净重名称为："+pdPackage.getWeightName()+"已在申请中！");
                        }
                    }
                } else{
                    pdPackage.setChooseInfo("4");
                    pdPackage.setNormsName(pdPackage.getNormsOut());
                    param.setFilter("normsSuttle", pdPackage.getNormsSuttle());
                    param.setFilter("normsError", pdPackage.getNormsError());
                    param.setFilter("normsNumber", pdPackage.getNormsNumber());
                    param.setFilter("normsSize", pdPackage.getNormsSize());
                    param.setFilter("normsTexture", pdPackage.getNormsTexture());
                    param.setFilter("normsOut", pdPackage.getNormsOut());
                    param.setFilter("normsKg", pdPackage.getNormsKg());
                    param.setFilter("normsOutSize", pdPackage.getNormsOutSize());
                    param.setFilter("normsOutTexture", pdPackage.getNormsOutTexture());
                    param.setFilter("normsTen", pdPackage.getNormsTen());
                    param.getFilterMap().put("normsLength", pdPackage.getNormsLength());
                    param.getFilterMap().put("normsWidth", pdPackage.getNormsWidth());
                    param.getFilterMap().put("normsHeight", pdPackage.getNormsHeight());
                    param.getFilterMap().put("normsVolume", pdPackage.getNormsVolume());
                    param.getFilterMap().put("netweightInner", pdPackage.getNetweightInner());
                    param.getFilterMap().put("netweightOut", pdPackage.getNetweightOut());
                    param.getFilterMap().put("grossweightOut", pdPackage.getGrossweightOut());
                    List<PdNormsStd> normsStds = productLogic.findPdNormsStd(param);
                    if (!CollectionUtils.isEmpty(normsStds)) {
                        throw new BusinessException("申请外包装规格为："+pdPackage.getNormsOut()+"已经存在！");
                    } else {
                        List<PDInfoResult> beans = this.findPageList(param, PDInfoResult.class);
                        if (!CollectionUtils.isEmpty(beans)) {
                            throw new BusinessException("申请外包装规格为："+pdPackage.getNormsOut()+"已在申请中！");
                        }
                    }
                }
                pdPackage.setTcProviderId(commonLogic.maxId("PD_TC_PROVIDER_PACKAGE", "TC_PROVIDER_ID"));
                pdPackage.setFeatureFlg(NumberConst.IntDef.INT_ZERO);
                pdPackage.setDelFlg("0");
                pdPackage.setAuditStatus(NumberConst.IntDef.INT_ZERO);
                /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
                Date currentDate = DateTimeUtil.getCustomerDate();
                pdPackage.setCrtId(param.getCrtId());
                pdPackage.setUpdId(param.getUpdId());
                pdPackage.setActId(param.getActId());
                pdPackage.setActTime(currentDate);
                pdPackage.setUpdTime(currentDate);
                /**Add: 横展开添加共通设置 2016/09/27  BY  任强  End */
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                pdPackage.setApplyDate(new Date());
                pdPackage.setCrtTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                super.save(SqlId.SQL_ID_SAVE_PROVIDER_PACKAGE,pdPackage);
                count++;
            }
        }
        return count;
    }

    /**
     * 插入技术标准
     * @param param
     * @return
     */
    @Transactional
    public int saveTncStdDiscussProvider(PDInfoParam param){
        int count = 0;
        List<PdTncStdDiscussProvider> tncProviders = param.getTncProviders();
        if(CollectionUtils.isNotEmpty(tncProviders)){
            for(PdTncStdDiscussProvider tncProvider : tncProviders){
                Long keyId = commonLogic.maxId("PD_TNC_STD_DISCUSS_PROVIDER", "KEY_ID");
                tncProvider.setKeyId(keyId);
                /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
                Date currentDate = DateTimeUtil.getCustomerDate();
                tncProvider.setCrtId(param.getCrtId());
                tncProvider.setUpdId(param.getUpdId());
                tncProvider.setActId(param.getActId());
                tncProvider.setActTime(currentDate);
                tncProvider.setUpdTime(currentDate);
                /**Add: 横展开添加共通设置 2016/09/27  BY  任强  End */
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                tncProvider.setRaiseDate(new Date());
                tncProvider.setCrtTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                super.save(SqlId.SQL_ID_SAVE_TNC_PROVIDER,tncProvider);
                count++;
            }
        }
        return count;
    }
}
