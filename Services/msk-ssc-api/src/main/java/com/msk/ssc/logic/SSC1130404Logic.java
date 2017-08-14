package com.msk.ssc.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscPackageMaterialInfo;
import com.msk.ssc.bean.SSC11304PackageBean;
import com.msk.ssc.bean.SSC11304Param;
import com.msk.ssc.bean.SSC11304ProductBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/31.
 */
@Service
public class SSC1130404Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(SSC1130404Logic.class);

    /**
     * SQL ID常量
     */
    private interface SqlId {
        String DELETE_PACKAGE_BY_PRODUCT_ID = "deletePackageByProductId";
    }

    /**
     * 注入DAO
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据合同产品ID，删除产品的包装
     */
    @Transactional
    public int deletePackageByProductId(SSC11304PackageBean packageBean) {
        packageBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.remove(SqlId.DELETE_PACKAGE_BY_PRODUCT_ID, packageBean);
    }

}
