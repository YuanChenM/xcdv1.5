package com.msk.ssc.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;

import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscIntoBasic;
import com.msk.core.entity.SscIntoDetail;
import com.msk.ssc.bean.SSC11309Bean;
import com.msk.ssc.bean.SSC11309RsParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liu_yan2 on 2016/6/30
 */
@Service
public class SSC11309Logic extends BaseLogic {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(SSC11309Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private SSC11310Logic ssc11310Logic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    public int batchSaveSscIntoBasic(SSC11309RsParam param){
        int result = 0;
        List<SSC11309Bean> sscIntoBasicList = param.getSscIntoBasicList();
        if (CollectionUtils.isNotEmpty(sscIntoBasicList)) {
            // 插入SSC_INTO_BASIC
            for (SSC11309Bean sscIntoBasic : sscIntoBasicList){
                Long maxIntoId = commonLogic.maxId("SSC_INTO_BASIC", "INTO_ID");
                sscIntoBasic.setIntoId(maxIntoId);
                List<SscIntoDetail> sscIntoDetailList = sscIntoBasic.getSscIntoDetailList();
                if (CollectionUtils.isNotEmpty(sscIntoDetailList)) {
                    for(SscIntoDetail sscIntoDetail : sscIntoDetailList) {
                        Long maxId = commonLogic.maxId("SSC_INTO_DETAIL", "ID");
                        sscIntoDetail.setId(maxId);
                        sscIntoDetail.setIntoId(maxIntoId);;
                    }
                    ssc11310Logic.batchSave(sscIntoDetailList);
                }
            }
            result = super.batchSave(sscIntoBasicList);
        }
        return result;
    }
}