package com.msk.price.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.ConfigParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.price.bean.SP171191Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;


/**
 * Created by zhang_qiang1
 */
@Service
public class SP171191Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(SP171191Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 根据条件查询对应的数据
     *
     * @param basePageParam
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171191Bean> getPageR(BasePageParam basePageParam) {
        PageResult<SP171191Bean> pageResult = this.findPage(basePageParam, SP171191Bean.class);
        List<SP171191Bean> beans = pageResult.getData();
        for (SP171191Bean bean : beans) {
            if (StringUtil.isEmpty(bean.getWayName())) {
                bean.setWayCode("");
            }
        }
        return pageResult;
    }


    /**
     * 判断能否添加
     *
     * @param bean
     * @return
     */
    public boolean isSave(SP171191Bean bean) {
        boolean flag = false;
        if (bean.getWaygradePdId() == null) {
            flag = true;
        }
        return flag;
    }


    @Transactional
    public void btachSaveOrUpdate(Collection<SP171191Bean> beans, BaseParam baseParam) {
        for (SP171191Bean bean : beans) {
            if (this.isSave(bean)) {
                Long maxId = commonLogic.maxId("SP_WAYGRADE_PD", "WAGRADE_PD_ID");
                bean.setWaygradePdId(maxId);
                // 1:卖家供应商用,目前只有这个，先写死。
                bean.setSystemType("1");
                bean.setCrtTime(DateTimeUtil.getCustomerDate());
                bean.setUpdTime(DateTimeUtil.getCustomerDate());
                bean.setActTime(DateTimeUtil.getCustomerDate());
                bean.setCrtId(baseParam.getCrtId());
                bean.setActId(baseParam.getActId());
                bean.setUpdId(baseParam.getUpdId());
                if ("".equals(bean.getMinVal())) {
                    bean.setMinVal(null);
                }
                this.saveBean(bean);
            } else {
                bean.setUpdTime(DateTimeUtil.getCustomerDate());
                bean.setUpdId(baseParam.getUpdId());
                if ("箱".equals(bean.getUnits())) {
                    bean.setUnits("1");
                } else if ("吨".equals(bean.getUnits())) {
                    bean.setUnits("2");
                }
                this.updateBean(bean);
            }
        }
    }
    @Transactional
    public void saveWay(BasePageParam basePageParam, String wayCode, String marketingName) {
        PageResult<SP171191Bean> pageResult = this.findPage(basePageParam, SP171191Bean.class);
        List<SP171191Bean> beans = pageResult.getData();
        for (SP171191Bean bean : beans) {
            bean.setWayCode(wayCode);
            bean.setMarketingName(marketingName);
            if (this.isSave(bean)) {
                Long maxId = commonLogic.maxId("SP_WAYGRADE_PD", "WAGRADE_PD_ID");
                bean.setWaygradePdId(maxId);
                // 1:卖家供应商用,目前只有这个，先写死。
                bean.setSystemType("1");
                bean.setCrtTime(DateTimeUtil.getCustomerDate());
                bean.setUpdTime(DateTimeUtil.getCustomerDate());
                bean.setActTime(DateTimeUtil.getCustomerDate());
                bean.setCrtId(basePageParam.getCrtId());
                bean.setActId(basePageParam.getActId());
                bean.setUpdId(basePageParam.getUpdId());
                if ("".equals(bean.getMinVal())) {
                    bean.setMinVal(null);
                }
                this.saveBean(bean);
            } else {
                bean.setUpdTime(DateTimeUtil.getCustomerDate());
                bean.setUpdId(basePageParam.getUpdId());
                bean.setUnits(null);
                bean.setMinVal(null);
                this.updateBean(bean);
            }
        }
    }

    /**
     * @param bean
     */
    @Transactional
    public void saveBean(SP171191Bean bean) {
        super.save(SqlId.SAVE_SP_WAYGRADE_PD, bean);
    }


    /**
     * @param bean
     */
    @Transactional
    public void updateBean(SP171191Bean bean) {
        super.save(SqlId.UPDATE_SP_WAYGRADE_PD, bean);
    }


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {


        static final String UPDATE_SP_WAYGRADE_PD = "UPDATE_SP_LOGS_PRODUCT";//修改


        static final String SAVE_SP_WAYGRADE_PD = "SAVE_SP_LOGS_PRODUCT";// 增加 产品所属价盘通道等级对应关系表


    }
}
