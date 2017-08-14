package com.msk.ds.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.ds.bean.SC182206Bean;
import com.msk.ds.bean.SC182206Param;
import com.msk.product.bean.ProductBean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * SC182203Logic.
 *
 * @author geng_xingdong
 * @version 1.0
 **/
@Service
public class SC182206Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182206Logic.class);


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * SQL Map 中SQL ID定义
     *
     * @author pxg
     */
    private interface SqlId {
        String SQL_ID_GET_DS_INFORMATION = "getDsInformation";
    }

    /*
    * 查询卖家供应链信息
    *
    * @return 返回卖家供应链的部分信息
    * @param
    * @author geng_xingdong
    */
    @Transactional(readOnly = true)
    public List<SC182206Bean> queryDsInformation(RsRequest<SC182206Param> param) {
        logger.debug("查询卖家的供应链信息");
        SC182206Param sc182206Param = param.getParam();
        List<SC182206Bean> sc182206Beans = new ArrayList<SC182206Bean>();
        if(null != sc182206Param){
            List<ProductBean>  productBeans = sc182206Param.getParams();
            if(!CollectionUtils.isEmpty(productBeans)){
                sc182206Beans = super.findList(SqlId.SQL_ID_GET_DS_INFORMATION, sc182206Param);
            }
        }
        return sc182206Beans;
    }

}






