package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PD141124Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PD141124Logic
 *
 * @author gyh
 */
@Service
public class PD141124Logic extends BaseLogic {


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        final String SQL_ID_FIND_MAX_PD_CLASSESTREE_ID = "findMaxClassesTreeId";
    }

    /** Add: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
    @Transactional
    public Long getMaxPdClassesTreeId(BaseParam param){
        PD141124Bean bean = this.findOne(SqlId.SQL_ID_FIND_MAX_PD_CLASSESTREE_ID, param);
        Long maxId = bean.getMaxClassesTreeId();
        return maxId;
    }
    /** Add: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
}
