package com.msk.price.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171109Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhu_kai1 on 2016/6/30.
 */
@Service
public class SP171116Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SP171116Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * 查询采购员待报价产品信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171109Bean> search(BasePageParam param){
        logger.debug("查询采购员待报价产品信息");
        PageResult<SP171109Bean>  temp= this.findPage(param,SP171109Bean.class);
        logger.debug("查询采购员待报价产品信息结束");
        return temp ;
    }
}
