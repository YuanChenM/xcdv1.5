package com.msk.product.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PD142103Bean;
import com.msk.product.bean.SlCodeNatureBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pxg
 * @version 创建时间：2016年4月20日
 */
@Service
public class PD142103Logic extends BaseLogic {
    @Autowired
    private ProductLogic productLogic;

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_FIND_ONE_CLASS = "findOneClass";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    public PageResult<PD142103Bean> pageResult(BasePageParam pageParam){
        PageResult<PD142103Bean> rs = new PageResult<PD142103Bean>();
        rs=this.findPage(pageParam,PD142103Bean.class);
        List<PD142103Bean> beans=rs.getData();
        for(PD142103Bean bean:beans){
            SlCodeNatureBean slCodeNature = new SlCodeNatureBean();
            slCodeNature.setBrandClass(bean.getBrandClass());
            slCodeNature.setBrandId(bean.getBrandId());
            slCodeNature.setSlCodeDis(bean.getSlCodeDis());
            slCodeNature.setSlMainClass(bean.getSlMainClass());
            slCodeNature.setSlCodeManufacture(bean.getSlCodeManufacture());
            if(!StringUtil.isNullOrEmpty(bean.getSlMainClass())&&!"0".equals(bean.getSlMainClass())){
                bean.setClassestreeCode(bean.getAttributeCode() + bean.getWeightCode() + bean.getNormsCode() + productLogic.getSlCodeNature(slCodeNature));
            }else{
                bean.setClassestreeCode("");
            }
        }
        return rs;
    }
}