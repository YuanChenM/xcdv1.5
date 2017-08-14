package com.msk.stock.logic;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.BaseStockProductParam;
import com.msk.stock.bean.ISO151419RsParam;
import com.msk.stock.bean.ISO151419RsProductResult;
import com.msk.stock.bean.ISO151419RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_jianzhou on 2016/3/3.
 */
@Service
public class ISO151419Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(ISO151419Logic.class);

    /**
     * 库存查询
     * 
     * @param request 查询参数
     * @return ISO151419RsResult 查询结果集
     */
    public ISO151419RsResult findAllList(RsRequest<ISO151419RsParam> request) {
        ISO151419RsResult rs = new ISO151419RsResult();
        ISO151419RsParam param = request.getParam();
        List<BaseStockProductParam> productList = param.getProduct();
        List<String> productCodeList = new ArrayList<String>();
        List<String> productNameList = new ArrayList<String>();
        List<String> weightList = new ArrayList<String>();
        for (BaseStockProductParam productParam : productList) {
            String productCode = productParam.getPdCode();
            if (!StringUtil.isEmpty(productCode)) {
                productCodeList.add(productCode);
            }
            String productName = productParam.getPdName();
            if (!StringUtil.isEmpty(productName)) {
                productNameList.add(productName);
            }
            String weight = productParam.getWeight();
            if (!StringUtil.isEmpty(weight)) {
                weightList.add(weight);
            }
        }

        if (!CollectionUtils.isEmpty(productCodeList)) {
            param.setPdCode(productCodeList);
        }
        if (!CollectionUtils.isEmpty(productNameList)) {
            param.setPdName(productNameList);
        }
        if (!CollectionUtils.isEmpty(weightList)) {
            param.setWeight(weightList);
        }
        int totalCount = super.getPageCount(param);
        rs.setTotalCount(totalCount);
        rs.setTotalPage(totalCount, param.getPageCount());
        rs.setPageNo(param.getPageNo());
        if ( totalCount == 0) {
            return rs;
        }
        List<ISO151419RsProductResult> resultList = super.findPageList(param);
        rs.setProductList(resultList);
        return rs;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
