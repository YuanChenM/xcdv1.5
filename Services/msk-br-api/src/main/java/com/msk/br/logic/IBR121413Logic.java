package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121413RsBean;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IBR121413Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121413Logic.class);

    interface SqlId{
        static String BUYER_PD_CLASSES = "buyerPdClasses";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public List<IBR121413RsBean> synBuyerPdClasses(BaseParam param){

        List<IBR121413RsBean> buyerPdClassesList = this.findList(SqlId.BUYER_PD_CLASSES,param);

        return buyerPdClassesList;
    }
}
