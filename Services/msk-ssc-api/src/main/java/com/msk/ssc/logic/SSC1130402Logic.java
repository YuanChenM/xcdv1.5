package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscDeliveryPlanBasic;
import com.msk.ssc.bean.SSC11304DeliveryPlanBean;
import com.msk.ssc.bean.SSC11304PackageBean;
import com.msk.ssc.bean.SSC11304Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xia_xiaojie on 2016/8/31.
 */
@Service
public class SSC1130402Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(SSC1130402Logic.class);

    /**
     * SQL ID常量
     */
    private interface SqlId {
        String DELETE_DELIVERY_PLAN_BY_CID_PCODE = "deleteDeliveryPlanByCidPcode";
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
     * 根据合同ID和产品CODE，删除产品的交货计划
     */
    @Transactional
    public int deleteDeliveryPlanByCidPcode(SSC11304DeliveryPlanBean deliveryPlanBean) {
        deliveryPlanBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.remove(SqlId.DELETE_DELIVERY_PLAN_BY_CID_PCODE, deliveryPlanBean);
    }

}
