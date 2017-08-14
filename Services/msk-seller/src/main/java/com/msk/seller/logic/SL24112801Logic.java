package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.PDInfoParam;
import com.msk.seller.bean.SL24112801Bean;
import com.msk.seller.bean.SL24112801Param;
import com.msk.seller.utils.SLControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * PD141101Logic
 *
 * @author pxg
 */
@Service
public class SL24112801Logic extends BaseLogic {

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
    interface SqlId {
        String SQL_ID_FIND_QUERY_SLPRODUTC = "querySlProduct";
    }

    /**
     * 获取原料描述信息
     *
     * @return
     */
    public PdClassestreeMat queryClassData(String classCode) {
        PdClassestreeMat pdClassestreeMat = SLControllerUtil.findClassData(classCode,"0");
        return pdClassestreeMat;
    }

    /**
     * 选择三级分类 或 二级分类  获取产品总控目录数据
     *
     * @return
     */
    public List<SL24112801Param> queryData(String classCodeThree,String classCodeTwo) {
        // 调接口
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setClassCodeThree(classCodeThree);
        pdInfoParam.setClassCodeTwo(classCodeTwo);
        List<SL24112801Param> listMat = SLControllerUtil.findPdClassestreeMat(pdInfoParam);
        return listMat;
    }

    /**
     * 查询省级卖家池卖家目录
     * @param classestreeCode classestreeCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<SL24112801Bean> querySlProduct(String classestreeCode) {
        BaseParam param = new BaseParam();
        if(!StringUtil.isNullOrEmpty(classestreeCode)){
            if (classestreeCode.length() == 5) {
                String classCode=classestreeCode.substring(0, 2);
                String machingCode=classestreeCode.substring(2,3);
                String breedCode = classestreeCode.substring(3, 5);
                param.setFilter("classCode", classCode);
                param.setFilter("machingCode", machingCode);
                param.setFilter("breedCode", breedCode);
            }else{
                String classCode=classestreeCode.substring(0, 2);
                String machingCode=classestreeCode.substring(2,3);
                param.setFilter("classCode", classCode);
                param.setFilter("machingCode", machingCode);
            }
            param.setFilter("status","4");
            param.setFilter("statusTwo","5");
        }
        List<SL24112801Bean> sl24112801BeanList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLPRODUTC,param);
        return sl24112801BeanList;
    }


}
