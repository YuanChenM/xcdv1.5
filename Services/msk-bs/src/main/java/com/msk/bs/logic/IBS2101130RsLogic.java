package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101130RsBean;
import com.msk.bs.bean.IBS2101130RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ren_qiang on 2016/9/7.
 */
@Service
public class IBS2101130RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    @Transactional
    public Integer saveComplaintInfo(IBS2101130RsParam param){
        long complainId =  commonLogic.maxId("SL_HOUSE_BYCOMPLAINT_REC", "COMPLAIN_ID");
        param.setComplainId(complainId);
        IBS2101130RsBean bean = this.findOne(param);
        param.setSlCode(bean.getSlCode());
        Integer result;
        Integer cot = this.getCount(param);
        if(cot != null && cot>0){
            result = this.modify(param);
        }
        else{
            result = this.save(param);
        }

        return  result;
    }
}
