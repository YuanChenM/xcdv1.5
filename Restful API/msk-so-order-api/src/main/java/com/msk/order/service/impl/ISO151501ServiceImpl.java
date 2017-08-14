package com.msk.order.service.impl;

import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.result.PageResult;
import com.msk.common.constant.NumberConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.ISO151501Bean;
import com.msk.order.bean.ISO151501SubOrderToTalBean;
import com.msk.order.entity.SoSubOrder;
import com.msk.order.repository.SoSubOrderRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151501Service;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单列明页面 接口
 * zhangqiang1
 */

@Service
public class ISO151501ServiceImpl extends BaseService<SoSubOrder, Long> implements ISO151501Service {
    @Autowired
    private SoSubOrderRepository subOrderRepository;

    @Autowired
    private BaseJdbcImpl baseJdbc;

    @Override
    public BaseRepository<SoSubOrder, Long> getRepository() {
        return subOrderRepository;
    }


    /**
     * 列表页面查询    导出的excel
     *
     * @param baseParam
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult<ISO151501Bean> findAllOrders(BasePageParam baseParam) throws Exception {
        return findPage(baseParam);

    }

    /**
     * 查询页面
     *
     * @param baseParam
     * @return
     */
    public PageResult<ISO151501Bean> findPage(BasePageParam baseParam) throws Exception {
        PageResult<ISO151501Bean> pageResult = new PageResult<>();
        int recordsTotal = NumberConstant.IntDef.INT_ZERO;
        boolean paging = baseParam.isPaging();
        List<String> parameters = new ArrayList<>();
        String pageSQL = this.getPageSql(baseParam, parameters);
        parameters.clear();
        String pageCountSql = this.getPageCountSql(baseParam, parameters);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (paging) {
            int page = baseParam.getStartPos() / baseParam.getPageSize();
            Pageable pageable = new PageRequest(page, baseParam.getPageSize(), null);
            Page pageContent = baseJdbc.queryForList(pageSQL, pageCountSql, parameters, pageable);
            recordsTotal = new Long(pageContent.getTotalElements()).intValue();
            mapList = pageContent.getContent();
        } else {
            mapList = baseJdbc.queryForListNotCount(pageSQL, parameters, null, true);
        }
        pageResult.setRecordsTotal(recordsTotal);
        List<ISO151501Bean> iso151501BeanList = this.getListBeanByMaps(mapList, baseParam);
        pageResult.setData(iso151501BeanList);
        return pageResult;
    }

    /**
     * 获取List<ISO151501Bean>
     *
     * @param mapList
     * @return
     */
    public List<ISO151501Bean> getListBeanByMaps(List<Map<String, Object>> mapList, BasePageParam baseParam) throws Exception {
        ISO151501SubOrderToTalBean total = this.getTotalQtyAndAmount(baseParam);
        List<ISO151501Bean> beans = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            ISO151501Bean bean = new ISO151501Bean();
            BeanUtils.populate(bean, map);
            ISO151501SubOrderToTalBean subOrderQtyAndAmount = this.getSubTotalQtyAndAmount(bean.getSubOrderId());
            bean.setOrderQty(DecimalUtil.getBigDecimal(subOrderQtyAndAmount.getTotalQty()));    //单个subOrder数量
            Double doubleSubOrderTotalAmount = subOrderQtyAndAmount.getTotalAmount();
            DecimalFormat df = new DecimalFormat("0.00");
            String strSubOrderTotalAmount = df.format(doubleSubOrderTotalAmount);
            if (!StringUtil.isEmpty(strSubOrderTotalAmount)) {
                bean.setOrderAmount(DecimalUtil.getBigDecimal(strSubOrderTotalAmount));   // 单个subOrder金额
            }
            bean.setTotalQty(DecimalUtil.getBigDecimal(total.getTotalQty()));//总计数量
            String strTotalAmount = df.format(total.getTotalAmount());
            if (!StringUtil.isEmpty(strTotalAmount)) {
                bean.setTotalAmount(DecimalUtil.getBigDecimal(strTotalAmount));// 总计金额
            }
            beans.add(bean);
        }
        return this.setPageQtyAmdAmount(beans);
    }

    /**
     * 获取当前页面的总量和金额
     *
     * @param beans
     * @return
     */
    public List<ISO151501Bean> setPageQtyAmdAmount(List<ISO151501Bean> beans) {
        BigDecimal pageOrderQty = new BigDecimal(NumberConstant.IntDef.INT_ZERO);
        BigDecimal pageOrderAmount = new BigDecimal(NumberConstant.IntDef.INT_ZERO);
        for (ISO151501Bean bean : beans) {
            pageOrderQty = DecimalUtil.add(bean.getOrderQty(), pageOrderQty);
            pageOrderAmount = DecimalUtil.add(pageOrderAmount, bean.getOrderAmount());
            pageOrderQty = pageOrderQty.setScale(NumberConstant.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
            pageOrderAmount = pageOrderAmount.setScale(NumberConstant.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
        }
        for (ISO151501Bean bean : beans) {
            bean.setCurrentPageAmount(pageOrderAmount);
            bean.setCurrentPageQty(pageOrderQty);
        }
        return beans;
    }

    /**
     * 获取所有总计
     *
     * @return
     * @throws Exception
     */
    public ISO151501SubOrderToTalBean getTotalQtyAndAmount(BasePageParam baseParam) throws Exception {
        ISO151501SubOrderToTalBean subOrderToTalBean = this.getQtyAndAmount(baseParam, null);
        return subOrderToTalBean;
    }

    /**
     * 获取单个分批订单的订单量和金额
     *
     * @param subOrderId
     * @return
     * @throws Exception
     */
    public ISO151501SubOrderToTalBean getSubTotalQtyAndAmount(Long subOrderId) throws Exception {
        ISO151501SubOrderToTalBean subOrderToTalBean = this.getQtyAndAmount(null, subOrderId);
        Double doubleSubOrderTotalAmount = subOrderToTalBean.getTotalAmount();
        DecimalFormat df = new DecimalFormat("0.00");
        String strSubOrderTotalAmount = df.format(doubleSubOrderTotalAmount);
        if (!StringUtil.isEmpty(strSubOrderTotalAmount)) {
            subOrderToTalBean.setTotalAmount(Double.parseDouble(strSubOrderTotalAmount));
        }
        return subOrderToTalBean;
    }


    /**
     * @param subOrderId
     */
    public ISO151501SubOrderToTalBean getQtyAndAmount(BasePageParam baseParam, Long subOrderId) throws Exception {
        List<String> parameters = new ArrayList<>();
        String sql = this.getTotalSql(baseParam, subOrderId, parameters);
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql, parameters, null, true);
        Map<String, Object> map = mapList.get(NumberConstant.IntDef.INT_ZERO);
        ISO151501SubOrderToTalBean toTalBean = new ISO151501SubOrderToTalBean();
        BeanUtils.populate(toTalBean, map);
        return toTalBean;
    }

    /**
     * 获取合计的sql
     *
     * @param subOrderId
     * @return
     */
    public String getTotalSql(BasePageParam baseParam, Long subOrderId, List<String> parameters) {
        String sql = SqlUtil.getSqlBySqlId("ISO151501.total");
        if (subOrderId != null) {// 计算单个subOrder数量金额
            sql += "   and  detail.SUB_ORDER_ID = ?" + parameters.size();
            parameters.add(subOrderId.toString());
        } else {// 根据条件 计算总计数量金额
            sql = this.addWhere(sql, baseParam, parameters);
        }
        return sql;
    }


    /**
     * 通过sql 导出
     *
     * @param baseParam
     * @return
     */
    @Override
    public List<Map<String, Object>> exportOrderAndDetail(BasePageParam baseParam) {
        String sql = SqlUtil.getSqlBySqlId("ISO151501.Search");
        List<Map<String, Object>> resultList = this.getListRows(sql, baseParam);
        return resultList;
    }


    /**
     * 原生sql  查询
     *
     * @param sql
     * @param baseParam
     * @return
     */
    private List<Map<String, Object>> getListRows(String sql, BasePageParam baseParam) {
        List<String> parameters = new ArrayList<>();
        sql = this.addWhere(sql, baseParam, parameters);
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql, parameters, null, true);
        return mapList;
    }

    /**
     * 条件查询
     *
     * @param sql
     * @param baseParam
     * @param parameters
     * @return
     */
    public String addWhere(String sql, BasePageParam baseParam, List<String> parameters) {
        String orderTimeStart = (String) (baseParam.getFilterMap().get("orderTimeStart"));
        if (!StringUtil.isEmpty(orderTimeStart)) {
            sql += " AND soOrder.ORDER_TIME >=  ?" + parameters.size();
            parameters.add(orderTimeStart + " 00:00:00");
        }
        String orderTimeEnd = (String) (baseParam.getFilterMap().get("orderTimeEnd"));
        if (!StringUtil.isEmpty(orderTimeEnd)) {
            sql += " AND soOrder.ORDER_TIME<= ?" + parameters.size();
            parameters.add(orderTimeEnd + "23:59:59");
        }
        String orderCode = (String) (baseParam.getFilterMap().get("orderCode"));
        if (!StringUtil.isEmpty(orderCode)) {
            sql += " AND soOrder.ORDER_CODE like ?" + parameters.size();
            parameters.add(orderCode.trim());
        }
        String orderId = (String) (baseParam.getFilterMap().get("orderId"));
        if (!StringUtil.isEmpty(orderId)) {
            sql += " AND soOrder.ORDER_ID = ?" + parameters.size();
            parameters.add(orderId.trim());
        }
        String saName = (String) (baseParam.getFilterMap().get("saName"));
        if (!StringUtil.isEmpty(saName)) {
            sql += " AND soOrder.SA_NAME like ?" + parameters.size();
            parameters.add(saName.trim());
        }
        String buyerCode = (String) (baseParam.getFilterMap().get("buyerCode"));
        if (!StringUtil.isEmpty(buyerCode)) {
            sql += "  AND soOrder.BUYER_CODE like ?" + parameters.size();
            parameters.add(buyerCode.trim());
        }
        String buyerName = (String) (baseParam.getFilterMap().get("buyerName"));
        if (!StringUtil.isEmpty(buyerName)) {
            sql += "  AND soOrder.BUYER_NAME like ?" + parameters.size();
            parameters.add(buyerName.trim());
        }
        String districtCode = (String) (baseParam.getFilterMap().get("districtCode"));
        if (!StringUtil.isEmpty(districtCode)) {
            sql += "  AND soOrder.DISTRICT_CODE =  ?" + parameters.size();
            parameters.add(districtCode);
        }
        String orderSource = (String) (baseParam.getFilterMap().get("orderSource"));
        if (!StringUtil.isEmpty(orderSource)) {
            sql += "   AND soOrder.ORDER_SOURCE = ?" + parameters.size();
            parameters.add(orderSource);
        }
        String orderType = (String) (baseParam.getFilterMap().get("orderType"));
        if (!StringUtil.isEmpty(orderType)) {
            sql += "  AND subOrder.ORDER_TYPE = ?" + parameters.size();
            parameters.add(orderType);
        }
        String subOrderStatus = (String) (baseParam.getFilterMap().get("subOrderStatus"));
        if (!StringUtil.isEmpty(subOrderStatus)) {
            sql += "  AND subOrder.SUB_ORDER_STATUS  in ( " + subOrderStatus + ")";
        }
        sql += " order by soOrder.ORDER_ID DESC";
        return sql;
    }


    /**
     * 获取页面的sql
     *
     * @param baseParam
     * @return
     */
    public String getPageSql(BasePageParam baseParam, List<String> parameters) {
        String sql = SqlUtil.getSqlBySqlId("ISO151501.page");
        sql = this.addWhere(sql, baseParam, parameters);
        return sql;
    }

    /**
     * 获取分页数量的sql
     *
     * @param baseParam
     * @return
     */
    public String getPageCountSql(BasePageParam baseParam, List<String> parameters) {
        String sql = SqlUtil.getSqlBySqlId("ISO151501.pageCount");
        sql = this.addWhere(sql, baseParam, parameters);
        return sql;
    }


}
