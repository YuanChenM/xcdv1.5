package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.RealityMeasureParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RealityMeasureLogic extends BaseLogic {
    @Override
    @Transactional
    public int save(BaseParam baseParam) {
        RealityMeasureParam param = (RealityMeasureParam)baseParam;
        String pdRltMsrId = param.getPdRltMsrId();
        //新增主表信息
        if(StringUtil.isEmpty(pdRltMsrId)){
            int count = this.getPageCount(param);
            count ++;
            param.setPdRltMsrId(String.valueOf(count));
            return super.save(param);
        }
        return NumberConst.IntDef.INT_ZERO;
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
