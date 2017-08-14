package com.msk.batch.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BBR12140303Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR12140303Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String DELETE_SL_PRODUCT = "deleteSlProduct";
        String add_Sl_Product = "addSlProduct";
    }

    /**
     * 清除卖家基本数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int deleteSlProduct(BaseParam param) {
        logger.info("清除卖家基本数据");
        return super.remove(SqlId.DELETE_SL_PRODUCT, param);
    }


    /**
     * 同步卖家产品数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int addSlProduct(BaseParam param) {
        logger.info("同步卖家产品数据");
       /* Date currentDate = DateTimeUtil.getCustomerDate();
        param.getFilterMap().put("crtTime",currentDate);
        param.getFilterMap().put("updTime",currentDate);
        param.getFilterMap().put("actTime",currentDate);*/
        return super.save(SqlId.add_Sl_Product, param);
    }

}
