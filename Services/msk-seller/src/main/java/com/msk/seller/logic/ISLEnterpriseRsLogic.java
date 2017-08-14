package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.*;
import com.msk.seller.utils.SLCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_chi on 2016/7/5.
 */
@Service
public class ISLEnterpriseRsLogic extends BaseLogic {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISLEnterpriseRsLogic.class);


    interface SqlId {
        String SQL_ID_GET_SL_ENTERPRISE = "getSlEnterprise";
        String SQL_ID_GET_SL_ENTERPRISE_SL_COUNT = "getSlEnterpriseSlCount";
        String SQL_ID_GET_SL_ENTERPRISE_SL = "getSlEnterpriseSl";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 查询企业名称和企业ID
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ISLEnterpriseRsResult> getSlEnterprise(ISLSellerRsParam islSellerRsParam) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("slMainClassList", islSellerRsParam.getSlMainClassList());
        param.getFilterMap().put("slCode", islSellerRsParam.getSlCode());
        return this.findList(SqlId.SQL_ID_GET_SL_ENTERPRISE, param);
    }


    /**
     * 分页根据卖家编码关联对应的生产商(生产商)
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISLEnterpriseRsPage getSelfData(ISLEnterpriseRsParam param) {
        ISLEnterpriseRsPage pageResult = new ISLEnterpriseRsPage();
        // 查询数量
        Integer totalCount =  this.getCount(param);
        pageResult.setTotalCount(totalCount);
        pageResult.setPageNo(param.getPageNo());
        pageResult.setTotalPage(pageResult.getTotalCount(), param.getPageCount());
        if (pageResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            List<ISLEnterpriseRsResult> slEnterpriseList = this.findPageList(param);
            for (ISLEnterpriseRsResult bean : slEnterpriseList) {
                String authTermBegin = DateTimeUtil.formatDate(SLCheckUtil.FORMAT_YYYYMMDD_HHMMSS, bean.getAuthTermBegin());
                String authTermEnd = DateTimeUtil.formatDate(SLCheckUtil.FORMAT_YYYYMMDD_HHMMSS, bean.getAuthTermEnd());
                if (!StringUtil.isNullOrEmpty(authTermBegin) && !StringUtil.isNullOrEmpty(authTermEnd)) {
                    bean.setOnTime(authTermBegin + "~" + authTermEnd);
                } else {
                    bean.setOnTime("");
                }
            }
            pageResult.setIslEnterpriseList(slEnterpriseList);
            return pageResult;
        }
        return pageResult;
    }


    /**
     * 分页根据卖家编码关联对应的生产商(非生产商)
     *
     * @param param 分页参数
     * @return 结果集
     */
    @Transactional(readOnly = true)
    public  ISLEnterpriseRsPage getOemAgentData(ISLEnterpriseRsParam param) {
        ISLEnterpriseRsPage pageResult = new ISLEnterpriseRsPage();
        // 查询数量
        Integer totalCount =  this.getCount(SqlId.SQL_ID_GET_SL_ENTERPRISE_SL_COUNT,param);
        pageResult.setTotalCount(totalCount);
        pageResult.setPageNo(param.getPageNo());
        pageResult.setTotalPage(pageResult.getTotalCount(), param.getPageCount());
        if (pageResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            List<ISLEnterpriseRsResult> slEnterpriseList = this.findList(SqlId.SQL_ID_GET_SL_ENTERPRISE_SL,param);
            for (ISLEnterpriseRsResult bean : slEnterpriseList) {
                String authTermBegin = DateTimeUtil.formatDate(SLCheckUtil.FORMAT_YYYYMMDD_HHMMSS, bean.getAuthTermBegin());
                String authTermEnd = DateTimeUtil.formatDate(SLCheckUtil.FORMAT_YYYYMMDD_HHMMSS, bean.getAuthTermEnd());
                if (!StringUtil.isNullOrEmpty(authTermBegin) && !StringUtil.isNullOrEmpty(authTermEnd)) {
                    bean.setOnTime(authTermBegin + "~" + authTermEnd);
                } else {
                    bean.setOnTime("");
                }
            }
            pageResult.setIslEnterpriseList(slEnterpriseList);
            return pageResult;
        }
        return pageResult;
    }

}
