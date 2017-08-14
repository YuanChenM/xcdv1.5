package com.msk.price.logic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171111Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * peng_hao
 */
@Service
public class SP171111Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171111Logic.class);

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        String SQL_ID_FIND_APPLY_LIST = "findApplyList";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 其他供应商申报价格
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171111Bean> findSP171111List(BasePageParam pageParam) {
        logger.info("其他供应商申报价格一览");
        PageResult<SP171111Bean> result = super.findPage(pageParam, SP171111Bean.class);
        if(result != null){
            List<SP171111Bean> SP171111list = result.getData();
            for(SP171111Bean SP171111Bean : SP171111list){
                if(pageParam.getFilterMap().get("isSupply").equals("是")){
                    SP171111Bean.setSlCode(SP171111Bean.getEpName());
                }else{
                    SP171111Bean.setSlCode(SP171111Bean.getSlId());
                }
                if(!pageParam.getFilterMap().get("isPrice").equals("是")){
                    SP171111Bean.setWayGradePrice(StringConst.MIDDLE_LINE);
                }
                if(StringUtil.isEmpty(SP171111Bean.getPriceId())){
                    //供应商
                    SP171111Bean.setSlCode(StringConst.MIDDLE_LINE);
                    //申报价格
                    SP171111Bean.setWayGradePrice(StringConst.MIDDLE_LINE);
                }
            }
        }
        return result;
    }
}
