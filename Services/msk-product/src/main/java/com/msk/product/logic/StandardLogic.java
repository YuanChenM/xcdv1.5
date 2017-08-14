package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 产品标准Logic
 * @author jiang_nan
 */
@Service
public class StandardLogic extends BaseLogic {
    
    interface SqlId{
        String SQL_ID_INSERT_PD_STANDARD = "insertPdStandard";
        String SQL_ID_COUNT_PD_STANDARD = "countPdStandard";
    }
    
    /**
     * 根据类别编码,品种编码,卖家编码获得标准信息
     * @param classesCode 类别编码
     * @param breedCode 品种编码
     * @param sellerCode 卖家编码
     * @return 产品标准
     */
    @Transactional(readOnly=true)
    public PdStandard findStandard(String classesCode,String breedCode,String sellerCode){
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", classesCode);
        param.setFilter("breedCode", breedCode);
        param.setFilter("sellerCode", sellerCode);
        return super.findOne(param);
    }

    /**
     * 根据类别编码,品种编码,卖家编码添加标准信息
     * @param classesCode 类别编码
     * @param breedCode 品种编码
     * @param sellerCode 卖家编码
     * @return 产品标准
     */
    @Transactional
    public void save(String classesCode,String breedCode,String sellerCode){
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", classesCode);
        param.setFilter("breedCode", breedCode);
        if (sellerCode == null){
            sellerCode = "";
        }
        param.setFilter("sellerCode", sellerCode);
        super.save(SqlId.SQL_ID_INSERT_PD_STANDARD, param);
    }

    @Transactional(readOnly=true)
    public int getStdCount(String classesCode,String breedCode,String sellerCode){
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", classesCode);
        param.setFilter("breedCode", breedCode);
        param.setFilter("sellerCode", sellerCode);
        return super.getCount(SqlId.SQL_ID_COUNT_PD_STANDARD, param);
    }
    
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    
}
