package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS210111001RsParam;
import com.msk.bs.bean.IBS2101110RsParam;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlBsBuyerLeagues;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by gyh on 2016/4/8.
 * 编辑买手囤货联盟
 */
public class IBS2101110Validator extends BaseValidator<RsRequest<IBS2101110RsParam>> {

    @Override
    public void validatorData(RsRequest<IBS2101110RsParam> entity) {
        IBS2101110RsParam ibs2101110RsParam = entity.getParam();
        List<IBS210111001RsParam> slBsLeaguesList = ibs2101110RsParam.getSlBsLeaguesList();
        this.validatorRequired("买手囤货联盟参数对象不能为空", slBsLeaguesList);
        if (!CollectionUtils.isEmpty(slBsLeaguesList) && slBsLeaguesList.size() > 0) {
            for (IBS210111001RsParam slBsBuyerLeagues : slBsLeaguesList) {
                this.validatorRequired("联盟主买手ID", slBsBuyerLeagues.getOwnerSlCode());
                this.validatorRequired("联盟方买手ID", slBsBuyerLeagues.getAllianceSlCode());
                this.validatorRequired("产品类别", slBsBuyerLeagues.getClass());
                this.validatorRequired("产品二级分类编码", slBsBuyerLeagues.getMachiningCode());
                this.validatorRequired("产品品种", slBsBuyerLeagues.getBreedCode());
                this.validatorRequired("产品特征", slBsBuyerLeagues.getFeatureCode());
                this.validatorRequired("净重编码", slBsBuyerLeagues.getWeightCode());
                this.validatorRequired("产品等级编码", slBsBuyerLeagues.getGradeCode());
                this.validatorRequired("分红比例", slBsBuyerLeagues.getDivide());
                this.validatorRequired("申请状态", slBsBuyerLeagues.getApplyStatus());
                if ("1".equals(slBsBuyerLeagues.getApplyStatus())) {
                    this.validatorRequired("申请日时", slBsBuyerLeagues.getApplyTime());
                }
            }
        }
    }

}






