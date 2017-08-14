package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PD141106Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PD141106Logic extends BaseLogic {
    @Autowired
    private RealityMeasureLogic realityMeasureLogic;
    interface SqlId{
        String SQL_ID_REMOVE_REALITY_QUALITY_VALUE = "removeRealityQualityValue";
    }
    
    
    
    @Override
    @Transactional
    public int save(BaseParam baseParam) {
        PD141106Param param = (PD141106Param)baseParam;
        realityMeasureLogic.save(baseParam);
        super.remove(SqlId.SQL_ID_REMOVE_REALITY_QUALITY_VALUE, param);
        String [] pdQuaStdValIdArray = param.getPdQuaStdValIdArray();
        String [] pdReaValArray = param.getPdReaValArray();
        String [] remarkArray = param.getRemarkArray();
        int length = pdQuaStdValIdArray.length;
        String pdRltMsrId = param.getPdRltMsrId();
//        for (int i = 0; i < length; i++) {
//            String  pdQuaStdValId = pdQuaStdValIdArray[i];
//            String  pdReaVal = pdReaValArray[i];
//            String  remark = remarkArray[i];
//            PdRealityQualityValue realityQualityValue = new PdRealityQualityValue();
//            realityQualityValue.setPdRltMsrId(pdRltMsrId);
//            realityQualityValue.setPdQuaStdValId(pdQuaStdValId);
//            realityQualityValue.setPdReaVal(pdReaVal);
//            realityQualityValue.setRemark(remark);
//            super.save(realityQualityValue);
//        }
        return NumberConst.IntDef.INT_ONE;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
