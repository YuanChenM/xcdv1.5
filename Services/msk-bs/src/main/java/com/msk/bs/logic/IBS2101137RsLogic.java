package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101137Bean;
import com.msk.bs.bean.IBS2101137Result;
import com.msk.bs.bean.IBS2101137RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlBsBankaccount;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ni_shaotang on 2016/10/28.
 */
@Service
public class IBS2101137RsLogic extends BaseLogic {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(IBS2101137RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public IBS2101137Result queryBuyerList(IBS2101137RsParam param) {
        IBS2101137Result pageResult = new IBS2101137Result();
        //获取买手信息列表
        List<IBS2101137Bean> list = this.findPageList(param, pageResult);
        if (pageResult.getTotalCount() > 0) {
            //买手code集合
            List<String> slCodeList = new ArrayList<String>();
            for (IBS2101137Bean bean : list) {
                if (!StringUtil.isNullOrEmpty(bean.getSlCode())) {
                    slCodeList.add(bean.getSlCode());
                }
            }
            if (slCodeList.size() > 0) {
                //根据买手code获取银行卡列表信息
                BaseParam codeParam = new BaseParam();
                codeParam.getFilterMap().put("codeList", slCodeList);
                List<SlBsBankaccount> bankList = this.findList(codeParam);
                for (IBS2101137Bean bean : list) {
                    String slCode = bean.getSlCode();
                    if (!StringUtil.isNullOrEmpty(slCode)) {
                        for (SlBsBankaccount bank : bankList) {
                            if (slCode.equals(bank.getSlCode())) {
                                bean.getBankInfoList().add(bank);
                            }
                        }
                    }
                }
            }
            pageResult.setBuyershopList(list);
        }
        return pageResult;
    }
}
