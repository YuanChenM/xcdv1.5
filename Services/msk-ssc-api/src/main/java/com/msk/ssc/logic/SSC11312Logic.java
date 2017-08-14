package com.msk.ssc.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC11312Bean;
import com.msk.ssc.bean.SSC11312Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 业务逻辑类：增删改查生产商入库差异单详情
 * Created by xia_xiaojie on 2016/7/5.
 */
@Service
public class SSC11312Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL ID常量接口
     */
    private interface SqlId {
        String UPDATE_DIFFER_DETAILS = "updateDifferDetails";
        String DELETE_DIFFER_DETAILS = "deleteDifferDetails";
    }

    /**
     * 注入DAO
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public int saveDifferDetails(List<SSC11312Bean> ssc11312Beans) {
        Date now = DateTimeUtil.getCustomerDate();
        for (SSC11312Bean ssc11312Bean : ssc11312Beans) {
            ssc11312Bean.setId(commonLogic.maxId("ssc_differ_detail", "ID"));
            ssc11312Bean.setCrtTime(now);
        }
        return super.batchSave(ssc11312Beans);
    }

    /**
     * 根据差异单ID，更新对应差异单收发货详情
     */
    @Transactional
    public int updateDifferDetails(List<SSC11312Bean> ssc11312Beans) {
        Date now = DateTimeUtil.getCustomerDate();
        for (SSC11312Bean ssc11312Bean : ssc11312Beans) {
            ssc11312Bean.setUpdTime(now);
        }

        SSC11312Bean ssc11312Bean = new SSC11312Bean();
        ssc11312Bean.setDifferDetails(ssc11312Beans);
        return super.modify(SqlId.UPDATE_DIFFER_DETAILS, ssc11312Bean);
    }

    /**
     * 根据差异单ID，软删除对应差异单详情
     */
    @Transactional
    public int deleteDifferDetails(List<SSC11312Bean> ssc11312Beans) {
        Date now = DateTimeUtil.getCustomerDate();
        for (SSC11312Bean ssc11312Bean : ssc11312Beans) {
            ssc11312Bean.setUpdTime(now);
        }

        SSC11312Bean ssc11312Bean = new SSC11312Bean();
        ssc11312Bean.setDifferDetails(ssc11312Beans);
        return super.modify(SqlId.DELETE_DIFFER_DETAILS, ssc11312Bean);
    }

}
