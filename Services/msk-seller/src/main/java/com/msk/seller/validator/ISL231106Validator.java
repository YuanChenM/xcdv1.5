package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlPdTncStdOther;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.*;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by gyh on 2016/2/25.
 */
public class ISL231106Validator extends BaseValidator<RsRequest<ISL231106RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231106RsParam> entity) {
        ISL231106RsParam iSL231106RsParam = entity.getParam();
        if (null != iSL231106RsParam) {
            List<ISL231106RsSlProduct> slPdList = iSL231106RsParam.getSlPdList();
            List<ISL231106RsSlPdQty> slPdMctList = iSL231106RsParam.getSlPdMctList();
            List<ISL231106RsSlPdTnc> slPdTncList = iSL231106RsParam.getSlPdTncList();
            List<ISL231106RsSlPdPkg> slPdPkgList = iSL231106RsParam.getSlPdPkgList();
            List<SlPdTncStdOther> slPdOrgStdList = iSL231106RsParam.getSlPdOrgStdList();//卖家原种种源标准
            List<SlPdTncStdOther> slPdFedStdList = iSL231106RsParam.getSlPdFedStdList();//卖家产品饲养标准
            List<SlPdTncStdOther> slPdGnqStdList = iSL231106RsParam.getSlPdGnqStdList();//卖家产品通用质量标准
            List<SlPdTncStdOther> slPdTspStdList = iSL231106RsParam.getSlPdTspStdList();//卖家产品储存运输标准
            List<SlPdTncStdOther> slPdSftStdList = iSL231106RsParam.getSlPdSftStdList();//卖家产品安全标准
            //卖家原种种源标准
            if (!CollectionUtils.isEmpty(slPdOrgStdList) && slPdOrgStdList.size() > 0) {
                for (SlPdTncStdOther other : slPdOrgStdList) {
                    other.setStdFlag(1);
                    this.validatorRequired("原种种源标准：卖家编码", other.getSlCode());
                    this.validatorRequired("原种种源标准：卖家产品ID", other.getSlPdId());
                    this.validatorRequired("原种种源标准：产品标准ID", other.getStandardId());
                    this.validatorRequired("原种种源标准：标准项目ID", other.getTncStdItemId());
                }
            }
            //卖家产品饲养标准
            if (!CollectionUtils.isEmpty(slPdFedStdList) && slPdFedStdList.size() > 0) {
                for (SlPdTncStdOther other : slPdFedStdList) {
                    other.setStdFlag(2);
                    this.validatorRequired("饲养标准：卖家编码", other.getSlCode());
                    this.validatorRequired("饲养标准：卖家产品ID", other.getSlPdId());
                    this.validatorRequired("饲养标准：产品标准ID", other.getStandardId());
                    this.validatorRequired("饲养标准：标准项目ID", other.getTncStdItemId());
                }
            }
            //卖家产品通用质量标准
            if (!CollectionUtils.isEmpty(slPdGnqStdList) && slPdGnqStdList.size() > 0) {
                for (SlPdTncStdOther other : slPdGnqStdList) {
                    other.setStdFlag(3);
                    this.validatorRequired("通用质量标准：卖家编码", other.getSlCode());
                    this.validatorRequired("通用质量标准：卖家产品ID", other.getSlPdId());
                    this.validatorRequired("通用质量标准：产品标准ID", other.getStandardId());
                    this.validatorRequired("通用质量标准：标准项目ID", other.getTncStdItemId());
                }
            }
            //卖家产品储存运输标准
            if (!CollectionUtils.isEmpty(slPdTspStdList) && slPdTspStdList.size() > 0) {
                for (SlPdTncStdOther other : slPdTspStdList) {
                    other.setStdFlag(4);
                    this.validatorRequired("储存运输标准：卖家编码", other.getSlCode());
                    this.validatorRequired("储存运输标准：卖家产品ID", other.getSlPdId());
                    this.validatorRequired("储存运输标准：产品标准ID", other.getStandardId());
                    this.validatorRequired("储存运输标准：标准项目ID", other.getTncStdItemId());
                }
            }
            //卖家产品安全标准
            if (!CollectionUtils.isEmpty(slPdSftStdList) && slPdSftStdList.size() > 0) {
                for (SlPdTncStdOther other : slPdSftStdList) {
                    other.setStdFlag(5);
                    this.validatorRequired("安全标准：卖家编码", other.getSlCode());
                    this.validatorRequired("安全标准：卖家产品ID", other.getSlPdId());
                    this.validatorRequired("安全标准：产品标准ID", other.getStandardId());
                    this.validatorRequired("安全标准：标准项目ID", other.getTncStdItemId());
                }
            }
            //卖家能销售的产品信息
            if (!CollectionUtils.isEmpty(slPdList) && slPdList.size() > 0) {
                for (ISL231106RsSlProduct slProduct : slPdList) {
                    this.validatorRequired("产品信息：创建更新者ID", slProduct.getLoginId());
                    if (!StringUtil.isNullOrEmpty(slProduct.getDelFlg())) {
                        this.validatorRequired("删除操作：产品信息卖家产品id", slProduct.getSlPdId());
                    }else{
                        this.validatorRequired("产品信息：卖家编码", slProduct.getSlCode());
                        this.validatorRequired("产品信息：生产商企业ID", slProduct.getProdEpId());
                        this.validatorRequired("产品信息：品牌商企业ID", slProduct.getBrandEpId());
                        this.validatorRequired("产品信息：产品品牌ID", slProduct.getBrandId());
                        this.validatorRequired("产品信息：产品类别", slProduct.getPdClassesCode());
                        this.validatorRequired("产品信息：产品二级分类编码", slProduct.getMachiningCode());
                        this.validatorRequired("产品信息：产品品种", slProduct.getPdBreedCode());
                        this.validatorRequired("产品信息：产品特征", slProduct.getPdFeatureCode());
                        this.validatorRequired("产品信息：净重编码", slProduct.getWeightCode());
                        this.validatorRequired("产品信息：是否参与神农客分销", slProduct.getDistFlg());
                        this.validatorRequired("产品信息：是否参与美侍客分销", slProduct.getDistmskFlg());
                    }
                }
            }
            //卖家产品加工技术标准信息List
            if (!CollectionUtils.isEmpty(slPdMctList) && slPdMctList.size() > 0) {
                for (ISL231106RsSlPdQty slPdQty : slPdMctList) {
                    this.validatorRequired("加工技术标准：卖家产品信息中卖家编码", slPdQty.getSlCode());
                    this.validatorRequired("加工技术标准：卖家产品ID", slPdQty.getSlPdId());
                    this.validatorRequired("加工技术标准：创建更新者ID", slPdQty.getLoginId());
                    this.validatorRequired("加工技术标准：产品加工质量标准定级", slPdQty.getSlQltGradeCode());
                    if (StringUtil.isNullOrEmpty(slPdQty.getDelFlg())) {
                        List<ISL231106RsPdQltStd> slPdQtyStdList = slPdQty.getSlPdMctStdList();
                        if (!CollectionUtils.isEmpty(slPdQtyStdList)) {
                            for (ISL231106RsPdQltStd pdQltStd : slPdQtyStdList) {
                                this.validatorRequired("加工技术标准：卖家编码", pdQltStd.getSlCode());
                                this.validatorRequired("加工技术标准：产品标准ID", pdQltStd.getStandardId());
                                this.validatorRequired("加工技术标准：质量标准项目ID", pdQltStd.getStdItemId());
                                this.validatorRequired("加工技术标准：创建更新者ID", pdQltStd.getLoginId());
                            }
                        }
                    }
                }
            }
            //卖家产品加工质量标准指标
            if (!CollectionUtils.isEmpty(slPdTncList) && slPdTncList.size() > 0) {
                for (ISL231106RsSlPdTnc slPdTnc : slPdTncList) {
                    this.validatorRequired("加工质量标准：卖家编码", slPdTnc.getSlCode());
                    this.validatorRequired("加工质量标准：卖家产品ID", slPdTnc.getSlPdId());
                    this.validatorRequired("加工质量标准：创建更新者ID", slPdTnc.getLoginId());
                    this.validatorRequired("加工质量标准：产品加工质量标准定级", slPdTnc.getSlTncGradeCode());
                    if (StringUtil.isNullOrEmpty(slPdTnc.getDelFlg())) {
                        List<ISL231106RsPdTncStd> slPdTncStdList = slPdTnc.getSlPdTncStdList();
                        if (!CollectionUtils.isEmpty(slPdTncStdList)) {
                            for (ISL231106RsPdTncStd pdTncStd : slPdTncStdList) {
                                this.validatorRequired("加工质量标准：卖家编码", pdTncStd.getSlCode());
                                this.validatorRequired("加工质量标准：产品标准ID", pdTncStd.getStandardId());
                                this.validatorRequired("加工质量标准：技术标准项目ID", pdTncStd.getStdItemId());
                                this.validatorRequired("加工质量标准：创建更新者ID", pdTncStd.getLoginId());
                            }
                        }
                    }
                }
            }
            //卖家产品包装标准信息
            if (!CollectionUtils.isEmpty(slPdPkgList) && slPdPkgList.size() > 0) {
                for (ISL231106RsSlPdPkg pdPkg : slPdPkgList) {
                    this.validatorRequired("包装标准：卖家编码", pdPkg.getSlCode());
                    this.validatorRequired("包装标准：卖家产品ID", pdPkg.getSlPdId());
                    this.validatorRequired("包装标准：产品标准ID", pdPkg.getStandardId());
                    this.validatorRequired("包装标准：产品包装编码", pdPkg.getPkgCode());
                    this.validatorRequired("包装标准：创建者ID/更新者ID", pdPkg.getLoginId());
                }
            }

        }
    }
}
