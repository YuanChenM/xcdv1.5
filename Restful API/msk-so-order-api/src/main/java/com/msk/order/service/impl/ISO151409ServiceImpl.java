package com.msk.order.service.impl;

import com.msk.common.constant.NumberConstant;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.exception.SystemException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.common.utils.XmlUtils;
import com.msk.order.bean.param.ISO151409RestReturnListParam;
import com.msk.order.bean.param.ISO151409RestParam;
import com.msk.order.bean.result.ISO151409ReturnDetailRestResult;
import com.msk.order.bean.result.ISO151409ReturnInfoRestResult;
import com.msk.order.bean.result.ISO151409RestResult;
import com.msk.order.service.ISO151409Service;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 退货单查询接口
 * service层
 */
@Service
public class ISO151409ServiceImpl implements ISO151409Service {

    @Autowired
    private BaseJdbcImpl baseJdbc;


    /**
     * 查询退货单
     *
     * @param rsParam
     * @return
     */
    @Override
    public ISO151409RestResult findReturnOrder(ISO151409RestParam rsParam) {
        ISO151409RestResult rsResult = new ISO151409RestResult();
        String countSql = this.getCountSql();
        Integer pageCount = rsParam.getPageCount();
        Integer count = this.getReturnOrderCount(countSql, rsParam);
        if (pageCount != null) {  //  设置总页数
            Integer page = count / pageCount;
            if (count % pageCount > NumberConstant.IntDef.INT_ZERO) {
                page = page + NumberConstant.IntDef.INT_ONE;
            }
            rsResult.setTotalPage(page);
        }
        String returnOrderSql = this.getReturnOrderSql();
        List<ISO151409ReturnInfoRestResult> returnInfoRsResultList = this.mapsToSoReturnListBySql(returnOrderSql, rsParam);
        if (!CollectionUtils.isEmpty(returnInfoRsResultList)) {
            for (ISO151409ReturnInfoRestResult returnInfoRsResult : returnInfoRsResultList) {
                String returnOrderDetailSql = this.getReturnOrderDetailSql();
                List<ISO151409ReturnDetailRestResult> soReturnDetailList = this.mapsToSoReturnDetailList(returnOrderDetailSql, rsParam, returnInfoRsResult.getReturnId());
                returnInfoRsResult.setReturnDetails(soReturnDetailList);
            }
        }
        rsResult.setPageNo(rsParam.getPageNo());
        rsResult.setReturnInfos(returnInfoRsResultList);
        rsResult.setTotalCount(returnInfoRsResultList.size());
        return rsResult;
    }

    /**
     * 通过sql 查询 退货单数量
     *
     * @param sql
     * @return
     */
    public Integer getReturnOrderCount(String sql, ISO151409RestParam rsParam) {
        List<String> parameters = new ArrayList<>();
        sql = this.getWhereSQL(sql, parameters, rsParam);
        List<Map<String, Object>> mapList = this.getMaps(sql, parameters);
        Integer count = NumberConstant.IntDef.INT_ZERO;
        if (!CollectionUtils.isEmpty(mapList)) {
            Map<String, Object> map = mapList.get(NumberConstant.IntDef.INT_ZERO);
            BigInteger findReturnOrderCount = (BigInteger) map.get("count");
            count = findReturnOrderCount.intValue();
        }
        return count;
    }

    /**
     * 通过sql 查询 把map 转成退货单列表
     *
     * @param sql
     * @return
     */
    public List<ISO151409ReturnInfoRestResult> mapsToSoReturnListBySql(String sql, ISO151409RestParam rsParam) {
        List<String> parameters = new ArrayList<>();
        sql = this.getWhereSQL(sql, parameters, rsParam);
        sql = this.limitSql(sql, rsParam);
        List<Map<String, Object>> mapList = this.getMaps(sql, parameters);
        List<ISO151409ReturnInfoRestResult> soReturnList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(mapList)) {
            for (Map<String, Object> map : mapList) {
                ISO151409ReturnInfoRestResult soReturn = new ISO151409ReturnInfoRestResult();
                try {
                    BeanUtils.populate(soReturn, map);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SystemException(e.getMessage());
                }
                soReturnList.add(soReturn);
            }
        }
        return soReturnList;
    }

    /**
     * @param sql
     * @return
     */
    public List<ISO151409ReturnDetailRestResult> mapsToSoReturnDetailList(String sql, ISO151409RestParam rsParam, Integer returnId) {
        List<String> parameters = new ArrayList<>();
        sql = this.childWhereCondition(sql, parameters, rsParam, returnId);
        List<Map<String, Object>> mapList = this.getMaps(sql, parameters);
        List<ISO151409ReturnDetailRestResult> soReturnDetailList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(mapList)) {
            for (Map<String, Object> map : mapList) {
                ISO151409ReturnDetailRestResult soReturnDetail = new ISO151409ReturnDetailRestResult();
                try {
                    BeanUtils.populate(soReturnDetail, map);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SystemException(e.getMessage());
                }
                soReturnDetailList.add(soReturnDetail);
            }
        }
        return soReturnDetailList;
    }

    /**
     * 获取查询数量的sql
     *
     * @param
     * @return
     */
    public String getCountSql() {
        String countSql = SqlUtil.getSqlBySqlId("ISO151409.getReturnOrderCountSql");
        return countSql;
    }

    /**
     * 获取查询退货单sql
     *
     * @return
     */
    public String getReturnOrderSql() {
        String returnOrderSql = SqlUtil.getSqlBySqlId("ISO151409.returnOrderSql");
        return returnOrderSql;
    }

    /**
     * 获取退货单明细sql
     *
     * @return
     */
    public String getReturnOrderDetailSql() {
        String returnOrderDetailSql = SqlUtil.getSqlBySqlId("ISO151409.getReturnOrderDetailSql");
        return returnOrderDetailSql;
    }


    public String getWhereSQL(String sql, List<String> parameters, ISO151409RestParam param) {
        if (!StringUtil.isEmpty(param.getBuyersCode())) {
            sql += " and sr.BUYER_CODE= ?" + parameters.size();
            parameters.add(param.getBuyersCode());
        }
        if (!StringUtil.isEmpty(param.getBuyersId())) {
            sql += " and so.BUYER_ID= ?" + parameters.size();
            parameters.add(param.getBuyersId());
        }
        if (!StringUtil.isEmpty(param.getSellerCode())) {
            sql += " and sr.SELLER_CODE= ?" + parameters.size();
            parameters.add(param.getSellerCode());
        }
        if (!StringUtil.isEmpty(param.getReturnStatus())) {
            sql += " and sr.RETURN_STATUS in ( " + param.getReturnStatus() + ")";
        }
        Date returnTimeFrom = param.getReturnTimeFrom();
        if (returnTimeFrom != null) {
            sql += " and sr.APPLY_TIME >= DATE_FORMAT( ?" + parameters.size() + ",'%Y-%c-%d 00:00:00')";
            parameters.add(DateTimeUtil.formatDate("yyyy-MM-dd", returnTimeFrom));
        }
        Date returnTimeTo = param.getReturnTimeTo();
        if (returnTimeTo != null) {
            sql += " and sr.APPLY_TIME <= DATE_FORMAT( ?" + parameters.size() + ",'%Y-%c-%d 23:59:59')";
            parameters.add(DateTimeUtil.formatDate("yyyy-MM-dd", returnTimeTo));
        }
        if (!StringUtil.isEmpty(param.getReturnSource())) {
            sql += " and sr.RETURN_SOURCE= ?" + parameters.size();
            parameters.add(param.getReturnSource());
        }
        if (!StringUtil.isEmpty(param.getDistrictCode())) {
            sql += " and sr.DISTRICT_CODE= ?" + parameters.size();
            parameters.add(param.getDistrictCode());
        }
        if (param.getRefundMode() != null) {
            sql += " and sr.RETURN_MODE= ?" + parameters.size();
            parameters.add(StringUtil.formatNum(param.getRefundMode()));
        }
        BigDecimal returnMountMin = param.getReturnAmountMin();
        if (returnMountMin != null) {
            int returnMountMinInt = returnMountMin.intValue();
            sql += " and sr.RETURN_AMOUNT >= ?" + parameters.size();
            parameters.add(StringUtil.formatNum(returnMountMinInt));
        }
        BigDecimal returnMountMax = param.getReturnAmountMax();
        if (returnMountMax != null) {
            int returnMountMaxInt = returnMountMax.intValue();
            sql += " and sr.RETURN_MODE <= ?" + parameters.size();
            parameters.add(StringUtil.formatNum(returnMountMaxInt));
        }
        if (param.getIsInvoice() != null) {
            sql += " and sr.IS_INVOICE= ?" + parameters.size();
            parameters.add(StringUtil.formatNum(param.getIsInvoice()));
        }
        if (param.getSelfDeliveryFlg() != null) {
            sql += " and so.SELF_DELIVERY_FLG= ?" + parameters.size();
            parameters.add(StringUtil.formatNum(param.getSelfDeliveryFlg()));
        }
        if (!StringUtil.isEmpty(param.getSellers())) {//  直销员id
            sql += " and so.SA_ID= ?" + parameters.size();
            parameters.add(param.getSellers());
            sql += " or so.SA_NAME= ?" + parameters.size();
            parameters.add(param.getSellers());
        }
        if (!StringUtil.isEmpty(param.getOrderTaker())) {
            sql += " and so.ORDER_TAKER= ?" + parameters.size();
            parameters.add(param.getOrderTaker());
        }
        List<ISO151409RestReturnListParam> returnList = param.getReturnList();
        if (!CollectionUtils.isEmpty(returnList)) {
            Map<String, String> resultMap = getWhereInPam(returnList);
            String returnIds = resultMap.get("returnIds");
            String returnCodes = resultMap.get("returnCodes");
            sql += " and sr.RETURN_ID in (" + returnIds + ")";
            if (returnCodes.length() > NumberConstant.IntDef.INT_ZERO) {
                sql += " or sr.RETURN_CODE in (" + returnCodes + ")";
            }
        }
        return sql;
    }


    /**
     * @param sql
     * @return
     */
    public List<Map<String, Object>> getMaps(String sql, List<String> parameters) {
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql, parameters, null, true);
        return mapList;
    }




    /**
     * 获取 符合in  条件 returnIds  returnCodes
     *
     * @param returnList
     * @return
     */
    private Map<String, String> getWhereInPam(List<ISO151409RestReturnListParam> returnList) {
        Map<String, String> resultMap = new HashMap<>();
        String returnIds = "";
        String returnCodes = "";
        if (!CollectionUtils.isEmpty(returnList)) {
            for (ISO151409RestReturnListParam bean : returnList) {
                if (bean.getReturnId() != null) {
                    returnIds += bean.getReturnId() + " ,";
                }

                if (!StringUtil.isEmpty(bean.getReturnCode())) {
                    returnCodes += bean.getReturnCode() + " ,";
                }
            }
        }
        if (returnIds.length() > NumberConstant.IntDef.INT_ZERO) {
            returnIds = returnIds.substring(NumberConstant.IntDef.INT_ZERO, returnIds.length() - 1);
        }
        if (returnCodes.length() > NumberConstant.IntDef.INT_ZERO) {
            returnCodes = returnCodes.substring(NumberConstant.IntDef.INT_ZERO, returnCodes.length() - 1);
        }
        resultMap.put("returnIds", returnIds);
        resultMap.put("returnCodes", returnCodes);
        return resultMap;
    }


    /**
     * @param sql
     * @param param
     * @return
     */
    private String childWhereCondition(String sql, List<String> parameters, ISO151409RestParam param, Integer returnId) {
        sql += " and detail.RETURN_ID= ?" + parameters.size();
        parameters.add(returnId.toString());
        if (!StringUtil.isEmpty(param.getOrderDetailLevel())) {
            sql += " and soDetail.ORDER_DETAIL_LEVEL in ( ?" + parameters.size() + ")";
            parameters.add(param.getOrderDetailLevel());
        }
        if (!StringUtil.isEmpty(param.getOrderDetailType())) {
            sql += " and soDetail.ORDER_DETAIL_TYPE in ( ?" + parameters.size() + ")";
            parameters.add(param.getOrderDetailType());
        }
        return sql;
    }


    /**
     * @param sql
     * @param param
     * @return
     */
    private String limitSql(String sql, ISO151409RestParam param) {
        Integer pageNo = param.getPageNo();
        Integer pageCount = param.getPageCount();
        if (pageNo != null && pageCount != null) {
            Integer begin = NumberConstant.IntDef.INT_ZERO;
            if (pageNo > NumberConstant.IntDef.INT_ZERO) {
                begin = (pageNo - NumberConstant.IntDef.INT_ONE) * pageCount;
            }
            sql += " LIMIT " + begin + "," + pageNo * pageCount;
        }
        return sql;
    }

}
