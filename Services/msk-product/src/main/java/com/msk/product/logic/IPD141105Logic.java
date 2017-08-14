package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141105RsParam;
import com.msk.product.bean.IPD141105RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月11日 下午17:26:11
 *          查询查询产品主码
 */
@Service
public class IPD141105Logic extends BaseLogic {


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * @param param 参数
     * @return List 集合
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141105RsResult> findAllPdCode(IPD141105RsParam param) {
        // 码等级
        BaseParam baseParam = new BaseParam();
        if(null != param.getCodeLevel()){
            baseParam.setFilter("codeLevel", param.getCodeLevel().toString());
        }
        baseParam.setFilter("classesCode", param.getClassesCode());
        baseParam.setFilter("machiningCode", param.getMachiningCode());
        baseParam.setFilter("breedCode", param.getBreedCode());
        baseParam.setFilter("featureCode", param.getFeatureCode());
        baseParam.setFilter("weightCode", param.getWeightCode());
        List<IPD141105RsResult> newList = super.findList(baseParam);
        List<IPD141105RsResult> newList2 = new ArrayList<IPD141105RsResult>();
        for (IPD141105RsResult beans : newList) {
            if (!newList2.contains(beans)) newList2.add(beans);
        }
        return newList2;
    }

}
