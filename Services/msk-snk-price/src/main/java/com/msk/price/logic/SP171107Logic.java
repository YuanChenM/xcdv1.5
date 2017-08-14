package com.msk.price.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171107Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 其他供应商申报数量一览
 * xu_wei
 */
@Service
public class SP171107Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171107Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 其他供应商申报数量
     *
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171107Bean> findSP171107List(BasePageParam pageParam) {
        logger.info("其他供应商申报数量一览");
        PageResult<SP171107Bean> result = super.findPage(pageParam, SP171107Bean.class);
        if (result != null) {
            List<SP171107Bean> SP171107list = result.getData();
            for (SP171107Bean SP171107Bean : SP171107list) {
                if(pageParam.getFilterMap().get("isSupply").equals("是")){
                    SP171107Bean.setSlCode(SP171107Bean.getSlName());
                }else{
                    SP171107Bean.setSlCode(SP171107Bean.getSlId());
                }
                if(!pageParam.getFilterMap().get("isNum").equals("是")){
                    SP171107Bean.setApplyNum(StringConst.MIDDLE_LINE);
                }
                if (StringUtil.isEmpty(SP171107Bean.getDemandId())) {
                    //供应商编码
                    SP171107Bean.setSlCode(StringConst.MIDDLE_LINE);
                    //申报数量
                    SP171107Bean.setApplyNum(StringConst.MIDDLE_LINE);
                }
            }
        }
        return result;
    }
}
