package com.msk.price.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.price.bean.*;
import com.msk.price.utils.PriceCycleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 查询价盘箱价接口Login
 * @author peng_hao
 * @version 1.0
 */
@Service
public class ISP171182Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171182Logic.class);
    /**
     * 查询价盘箱价
     *
     * @param request 查询参数
     * @return 返回结果
     */
    @Transactional(readOnly = true)
    public PricePlateInfoResult findAllList(RsRequest<PricePlateInfoParam> request) {
        logger.info("获取价盘箱价");
        PricePlateInfoResult rs = new PricePlateInfoResult();
        PricePlateInfoParam param = request.getParam();
        //取得数据COUNT
        //如果未传价盘周期，默认传入当前价盘
        if(StringUtils.isEmpty(request.getParam().getPricePeriod())){
            // 根据当前系统日期获取当天价盘周期
            String pricePeriod=null;
            PriceCycleParam priceCycleParam = new PriceCycleParam();
            Date dateNow = DateTimeUtil.getCustomerDate();
            priceCycleParam.setCurrentDate(dateNow);
            PriceCycleResult priceCycle = PriceCycleUtil.getPriceCycle(priceCycleParam);
            if (StringUtils.isEmpty(pricePeriod)) {
                pricePeriod = priceCycle.getCycleCode();
            }
            param.setPricePeriod(pricePeriod);
        }
        int totalCount= super.getPageCount(param);
        rs.setTotalCount(totalCount);
        rs.setTotalPage(param.getPageCount());
        rs.setPageNo(param.getPageNo());
        rs.setLgcsCode(param.getLgcsCode());
        rs.setPricePeriod(param.getPricePeriod());
        //若COUNT为0，直接返回
        if (totalCount == 0) {
            return rs;
        }

        //查询箱价格
        List<PricePlateInfoBean> searchList = super.findPageList(param);
        rs.setSearchList(searchList);
        return rs;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
