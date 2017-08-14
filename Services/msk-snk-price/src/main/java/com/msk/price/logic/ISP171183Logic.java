package com.msk.price.logic;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by yang_yang on 2016/5/16.
 */
@Service
public class ISP171183Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(ISP171183Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * SqlId. sql定义
     */
    interface SqlId {
        static final String SQL_ID_GET_PRICE_CYCLE = "getPriceCycle";
        static final String SQL_ID_GET_PRICE_LIST = "getPriceList";
    }

    /**
     * 获取神农客产品查询价盘
     * @return
     */
    @Transactional(readOnly = true)
    public RsResponse<List<ISP171183Bean>> getPriceCycle(RsRequest<ISP171183Param> request) {

        logger.info("获取神农客产品查询价盘");
        RsResponse<List<ISP171183Bean>> rs = new RsResponse<List<ISP171183Bean>>();
        ISP171183Param rsParam = request.getParam();

        List<ISP171183Bean> resultList = new ArrayList<ISP171183Bean>();

        List<ISP171183Bean> priceCycle = this.findList(SqlId.SQL_ID_GET_PRICE_CYCLE,rsParam);

        for (ISP171183Bean bean : priceCycle) {
            ISP171183Param param = new ISP171183Param();
            param.setProductId(bean.getProductId());
            param.setLogiAreaCode(bean.getLogiAreaCode());
            param.setPricePeriod(bean.getPricePeriod());

            List<ISP171183Bean> priceList = this.findList(SqlId.SQL_ID_GET_PRICE_LIST,param);
            List<ISP171183Bean> list = new ArrayList<ISP171183Bean>();

            for (ISP171183Bean price:priceList) {
                ISP171183Bean priceBean = new ISP171183Bean();
                priceBean.setOrderLevel(price.getOrderLevel());
                priceBean.setPriceOfKg(price.getPriceOfKg());
                priceBean.setPriceOfBox(price.getPriceOfBox());
                list.add(priceBean);
            }

            bean.setPricelist(list);

            resultList.add(bean);

        }

        rs.setResult(resultList);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("数据查询成功!");

        return rs;
    }

}
