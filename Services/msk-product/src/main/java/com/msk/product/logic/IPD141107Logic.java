package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.IPD141107RsParam;
import com.msk.product.bean.IPD141107RsResult;
import com.msk.product.bean.IPD141146RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月13日 下午14:26:20
 *          查询指定产品品种的标准质量档案卡信息
 */
@Service
public class IPD141107Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * @param param RsRequest输入的json数据
     * @return List 集合
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141107RsResult> findListPdQltTncStd(IPD141107RsParam param) {
        if (param == null){
            param = new IPD141107RsParam();
        }
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("classestreeCode",param.getClassesCode()+param.getMachiningCode()+param.getBreedCode());
        List<IPD141107RsResult>  beanList=  super.findList(baseParam);
        if (beanList!=null) {
            return beanList;
        }
        return null;
    }


    @Transactional(readOnly = true)
    public List<BaseEntity> selectSourceTnc(IPD141146RsParam param) {
        if (param == null){
            param = new IPD141146RsParam();
        }
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("classestreeCode",param.getClassesCode()+param.getMachiningCode()+param.getBreedCode());
        List<BaseEntity>  beanList=  super.findList(baseParam);
        return beanList;
    }

}