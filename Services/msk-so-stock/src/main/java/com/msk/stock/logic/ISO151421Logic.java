package com.msk.stock.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;

import com.msk.stock.bean.ISO151421ProductsRsResult;
import com.msk.stock.bean.ISO151421RsParam;
import com.msk.stock.bean.ISO151421RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 供货商库存列表ISO151421Logic.
 *
 * @author pxg
 */
@Service
public class ISO151421Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151421Logic.class);

    /**
     * 供货商库存列表
     *
     * @param param param
     * @return 返回结果
     * @author pxg
     */
    public ISO151421RsResult findAllList(RsRequest<ISO151421RsParam> param) {
        ISO151421RsResult rs = new ISO151421RsResult();
        ISO151421RsParam iSO151421RsParam = param.getParam();
        BaseParam params = new BaseParam();
        params.setFilter("supplierCode", iSO151421RsParam.getSupplierCode());
        params.setFilter("districtCode", iSO151421RsParam.getDistrictCode());
        params.setFilter("supplyPlatform","1");
        params.setFilter("stockType","1");
        params.setFilter("supplierName",iSO151421RsParam.getSupplierName());
        List<ISO151421ProductsRsResult> rsResultList = super.findList(params);
        rs.setSupplierCode(iSO151421RsParam.getSupplierCode());
        rs.setDistrictCode(iSO151421RsParam.getDistrictCode());
        if (!CollectionUtils.isEmpty(rsResultList)) {
            rs.setSupplierName(rsResultList.get(0).getSupplierName());
            for (int i = 0; i < rsResultList.size(); i++) {
                ISO151421ProductsRsResult result = rsResultList.get(i);
                result.setProQty(NumberConst.IntDef.INT_ZERO);
                result.setTransQty(NumberConst.IntDef.INT_ZERO);
            }
            rs.setPageNo(NumberConst.IntDef.INT_ONE);
            rs.setTotalPage(NumberConst.IntDef.INT_ONE);
            rs.setTotalCount(rsResultList.size());
            rs.setProducts(rsResultList);
        } else {
            throw new BusinessException("供应商编码为:" + iSO151421RsParam.getSupplierCode() + ",没有对应的记录");
        }
        return rs;
    }

    /**
     * 供货商库存列表测试数据
     *
     * @return 返回结果
     * @author pxg
     */
    public ISO151421RsResult findAllList2() {
        ISO151421RsResult rs = new ISO151421RsResult();
        return rs;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
