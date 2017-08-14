package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PD141108Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PD141108Logic extends BaseLogic {

    @Autowired
    private RealityMeasureLogic realityMeasureLogic;

    interface SqlId {
        String SQL_ID_REMOVE_REALITY = "removeReality";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    @Transactional
    public int save(BaseParam baseParam) {
        PD141108Param param = (PD141108Param) baseParam;
        realityMeasureLogic.save(baseParam);
        super.remove(SqlId.SQL_ID_REMOVE_REALITY, param);

        int length = param.getPdTncStdIdArray().length;
        String pdRltMsrId = param.getPdRltMsrId();
//        for (int i = 0; i < length; i++) {
//
//            PdRealityTechnical realityTechnical = new PdRealityTechnical();
//            realityTechnical.setPdRltMsrId(pdRltMsrId);
//            realityTechnical.setPdTncStdId(param.getPdTncStdIdArray()[i]);
//            realityTechnical.setContent(param.getReaContentArray()[i]);
//            realityTechnical.setGradeCode("0");
//            realityTechnical.setStatus("0");
//            super.save(realityTechnical);
//        }
        return NumberConst.IntDef.INT_ONE;
    }
}
