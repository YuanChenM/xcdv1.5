package com.msk.seller.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.utils.SLControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PD141101Logic
 * @author pxg
 */
@Service
public class SL241128Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取第一级分类
     * @return
     */
    public List<PDInfoResult> queryClassify(String classestreeCode,String treeLevel){
        List<PDInfoResult> pdClassestreeList = SLControllerUtil.findClassTree(classestreeCode, treeLevel);
        return pdClassestreeList;
    }

}
