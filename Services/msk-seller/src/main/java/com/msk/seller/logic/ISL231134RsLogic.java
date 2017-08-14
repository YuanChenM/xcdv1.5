package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.*;
import com.hoperun.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by cx on 2016/2/24.
 */
@Service
public class ISL231134RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Id
     */
    interface SqlId {
        static final String SQL_ID_FINDSLSELLER = "findSlSeller";
        static final String SQL_ID_FINDSLENTERPRISE = "findSlEnterprise";
        static final String SQL_ID_SAVESLEPAGENTAUTH= "saveSlEpAgentAuth";
        static final String SQL_ID_SAVESLEPOEMAUTH= "saveSlEpOemAuth";
        static final String SQL_ID_FINDSLEPAGENTAUTH= "findSlEpAgentAuth";
        static final String SQL_ID_FINDSLEPOEMAUTH= "findSlEpOemAuth";
    }

    /**
     * gzh 有待优化
     * 根据卖家编码查询用户是否存在
     * @param slCode 卖家编码
     * @return slSeller对象
     */
    @Transactional(readOnly = true)
    public SlSeller findIsl231180SLEpAuthIfExist(String slCode) {
        BaseParam base = new BaseParam();
        base.setFilter("slCode",slCode);
        SlSeller slSeller = super.findOne(SqlId.SQL_ID_FINDSLSELLER,base);
        return slSeller;
    }

    /**
     * 查询卖家企业基本信息
     * @param epId 企业ID
     * @return slEnterprise对象
     */
    @Transactional(readOnly = true)
    public SlEnterprise findSlEnterpriseIfExist(Long epId) {
        BaseParam base = new BaseParam();
        base.setFilter("epId",epId.toString());
        SlEnterprise slEnterprise = super.findOne(SqlId.SQL_ID_FINDSLENTERPRISE,base);
        return slEnterprise;
    }

    /**
     * 保存卖家代理及分销授权
     * @param slEpAgentAuth 传入保存对象
     */
    @Transactional
    public void saveSlEpAgentAuth(SlEpAgentAuth slEpAgentAuth) {
        slEpAgentAuth.setCrtTime(DateTimeUtil.getCustomerDate());
        super.save(SqlId.SQL_ID_SAVESLEPAGENTAUTH,slEpAgentAuth);
    }

    /**
     * 保存卖家OEM委托授权标志
     * @param slEpOemAuth
     */
    @Transactional
    public void saveSlEpOemAuth(SlEpOemAuth slEpOemAuth) {
        slEpOemAuth.setCrtTime(new Date());
        super.save(SqlId.SQL_ID_SAVESLEPOEMAUTH,slEpOemAuth);
    }
    /**
     * 根据生产商id 和 卖家编码查询代理商是否已经存在
     */
    @Transactional(readOnly = true)
    public void findIfExist(String slCode, Long epId) {
        BaseParam base = new BaseParam();
        base.setFilter("slCode",slCode);
        base.setFilter("epId",epId.toString());
        SlEpAgentAuth slEpAgentAuth = super.findOne(SqlId.SQL_ID_FINDSLEPAGENTAUTH,base);
        if(null!=slEpAgentAuth){
            throw new BusinessException("已经存在的代理商，无法重复录入");
        }
    }

    /**
     * 根据生产商id 和 卖家编码查询
     * @param slCode 卖家编码
     * @param epId 企业ID
     */
    @Transactional(readOnly = true)
    public void findOemIfExist(String slCode, Long epId) {
        BaseParam base = new BaseParam();
        base.setFilter("slCode",slCode);
        base.setFilter("epId",epId.toString());
        SlEpOemAuth slEpOemAuth = super.findOne(SqlId.SQL_ID_FINDSLEPOEMAUTH,base);
        if(null!=slEpOemAuth){
            throw new BusinessException("已经存在的OEM商，无法重复录入");
        }
    }

}







