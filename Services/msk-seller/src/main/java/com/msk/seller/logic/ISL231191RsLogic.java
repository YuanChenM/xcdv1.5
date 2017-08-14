package com.msk.seller.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231191Result;
import com.msk.seller.bean.ISL231191RsParam;
import com.msk.seller.bean.SlPdArtnoBean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pxg on 2016/4/26.
 */
@Service
public class ISL231191RsLogic extends BaseLogic {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISL231191RsLogic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_GET_SL_PD_ARTNO= "getSlPdArtno";
        static final String SQL_ID_GET_SKUCODE= "getSkuCode";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询卖家产品货号信息
     * @param param param
     * @return 卖家产品货号信息
     * @author pxg
     */
    @Transactional(readOnly = true)
    public ISL231191Result queryData(RsRequest<ISL231191RsParam> param) {
        logger.debug("查询卖家产品货号信息");
        ISL231191RsParam isl231191RsParam=param.getParam();
        ISL231191Result isl231191Result=new ISL231191Result();
        if(null!=isl231191RsParam){
            BaseParam params=new BaseParam();
            params.setFilter("slCode",isl231191RsParam.getSlCode());
            params.setFilter("classesCode",isl231191RsParam.getClassesCode());
            params.setFilter("machiningCode",isl231191RsParam.getMachiningCode());
            params.setFilter("breedCode",isl231191RsParam.getBreedCode());
            params.setFilter("featureCode",isl231191RsParam.getFeatureCode());
            params.setFilter("weightCode",isl231191RsParam.getWeightCode());
            params.setFilter("gradeCode",isl231191RsParam.getGradeCode());
            params.setFilter("salesPlatform",isl231191RsParam.getSalesPlatform());
            isl231191Result=super.findOne(params);
            if(null==isl231191Result){
                throw new BusinessException("无对应货号数据!");
            }
        }
        return isl231191Result;
    }

    /**
     * 查询卖家产品货号信息
     * @return 查询卖家产品货号信息
     * @author zhangchi
     */
    @Transactional(readOnly = true)
    public SlPdArtnoBean querySlPdArtno(RsRequest<SlPdArtnoBean> param) {
        logger.debug("查询卖家产品货号信息");
        SlPdArtnoBean   isl231196Result = new SlPdArtnoBean();
        SlPdArtnoBean isl231196RsParam = param.getParam();
        BaseParam params=new BaseParam();
        List<SlPdArtnoBean> slPdArtnoBeanList = isl231196RsParam.getSlPdArtnoBeanList();
        if(CollectionUtils.isNotEmpty(slPdArtnoBeanList)){
            params.setFilterObject("slPdArtnoBeanList", slPdArtnoBeanList);
            List<SlPdArtnoBean> slPdArtnoBeanListR = super.findList(params);
            if(CollectionUtils.isNotEmpty(slPdArtnoBeanListR)){
                isl231196Result.setSlPdArtnoBeanList(slPdArtnoBeanListR);
            }
        }else {
            params.setFilter("slCode", isl231196RsParam.getSlCode());
            params.setFilter("classesCode", isl231196RsParam.getClassesCode());
            params.setFilter("machiningCode", isl231196RsParam.getMachiningCode());
            params.setFilter("breedCode", isl231196RsParam.getBreedCode());
            params.setFilter("featureCode", isl231196RsParam.getFeatureCode());
            params.setFilter("weightCode", isl231196RsParam.getWeightCode());
            params.setFilter("salesPlatform", isl231196RsParam.getSalesPlatform());
            params.setFilter("gradeCode", isl231196RsParam.getGradeCode());
            params.setFilter("slPdArtno", isl231196RsParam.getSlPdArtno());
            params.setFilter("slCodeDis", isl231196RsParam.getSlCodeDis());
            // 查询
            isl231196Result =  super.findOne(SqlId.SQL_ID_GET_SL_PD_ARTNO, params);
        }
        return isl231196Result;
    }

    /**
     * 根据卖家、销售平台、物流区域、产品查询产品对应的SKU信息
     * @return 根据卖家、销售平台、物流区域、产品查询产品对应的SKU信息
     * @author renyi
     */
    @Transactional(readOnly = true)
    public List<SlPdArtnoBean> querySkuCode(RsRequest<SlPdArtnoBean> param) {
        logger.debug("查询卖家产品货号信息");
        SlPdArtnoBean  isl231196Result = new SlPdArtnoBean();
        SlPdArtnoBean isl231196RsParam = param.getParam();
        BaseParam params=new BaseParam();
        List<SlPdArtnoBean> products = isl231196RsParam.getProducts();
        List<SlPdArtnoBean> productsR  = null;
        if(CollectionUtils.isNotEmpty(products)){
            params.setFilterObject("products", products);
            params.setFilterObject("salesPlatform", isl231196RsParam.getSalesPlatform());
            params.setFilterObject("saleRegionCode", isl231196RsParam.getSaleRegionCode());
             productsR = super.findList(SqlId.SQL_ID_GET_SKUCODE,params);
        }
        return productsR;
    }





}
