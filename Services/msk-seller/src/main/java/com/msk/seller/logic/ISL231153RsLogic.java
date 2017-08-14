package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlPdBrand;
import com.msk.core.entity.SlProduct;
import com.msk.seller.bean.ISL231153RsParam;
import com.msk.seller.bean.ISL231153RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_chi on 2016/4/28.
 */
@Service
public class ISL231153RsLogic extends BaseLogic {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISL231153RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 查询卖家产品品牌信息
     * @return 查询卖家产品品牌信息
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public List<SlPdBrand> querySlPdBrand(RsRequest<SlProduct> param) {
        logger.debug("查询卖家产品品牌信息");
        SlProduct slProduct = param.getParam();
        BaseParam params=new BaseParam();
        params.setFilter("slCode", slProduct.getSlCode());
        params.setFilter("pdClassesCode", slProduct.getPdClassesCode());
        params.setFilter("machiningCode", slProduct.getMachiningCode());
        params.setFilter("pdBreedCode", slProduct.getPdBreedCode());
        params.setFilter("pdFeatureCode", slProduct.getPdFeatureCode());
        params.setFilter("weightCode", slProduct.getWeightCode());
        // 查询
        List<SlPdBrand>   slProductList =  super.findList(params);
        return slProductList;
    }
}
