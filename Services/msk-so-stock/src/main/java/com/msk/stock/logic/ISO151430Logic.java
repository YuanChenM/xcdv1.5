package com.msk.stock.logic;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;

import com.msk.common.logic.CommonLogic;
import com.msk.stock.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 库存查询接口
 *
 * @author zhang_qiang1
 */
@Service
public class ISO151430Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151430Logic.class);

    @Autowired
    private CommonLogic commonLogic;


    /**
     *
     * @param param
     * @return
     */
    public ISO151430RsResult queryUsedStock(StockRsParamList param){
        ISO151430RsResult rsResult=new ISO151430RsResult();
        List<Stock> resultStockList= this.getAllStock(param);
        Integer totalCount = resultStockList.size();
        rsResult.setTotalCount(totalCount);
        if(param.isPaging()){
            resultStockList=this.getPageList(resultStockList,param);
            rsResult.setTotalPage(totalCount, param.getPageCount());
            rsResult.setPageNo(param.getPageNo());
        }
        rsResult.setPdStockList(resultStockList);
        return  rsResult;
    }



    /**
     * 获取所有
     * @param param
     * @return
     */
    public List<Stock>  getAllStock(StockRsParamList param){
        List<Stock> allStock=new ArrayList<>();
        for(StockRsParam stockRsParam:param.getPdList()){
            List<Stock>stockList=this.getStockList(stockRsParam);
            if(stockList!=null&&stockList.size()>0){
                allStock.addAll(stockList);
            }
        }
        return allStock;
    }


    /**
     * 分页后的数据list
     * @param allStock
     * @param param
     * @return
     */
    public List<Stock> getPageList( List<Stock> allStock,StockRsParamList param){
        List<Stock>stockList=new ArrayList<>();
            if(allStock.size()>0){
                Integer pageCount= param.getPageCount();
                Integer startPos= param.getStartPos();
                Integer pageNo=param.getPageNo();// 当前页数
                int y=(allStock.size()-pageCount*pageNo)>0?startPos+pageCount:allStock.size();
                for(int i=startPos;i<y;i++){
                    stockList.add(allStock.get(i));
                }
            }
        return  stockList;
    }



    /**
     * 根据条件查询
     * @param stockRsParam
     * @return
     */
    public List<Stock> getStockList(StockRsParam stockRsParam){
        List<Stock> stockList=this.getBaseDao().getSqlSession().selectList(SqlId.SQL_ID_FIND_PRODUCT_STOCK,stockRsParam);
        return stockList;
    }












    /**
     * 修改 卖家库存数量
     * @param   param
     */
    @Transactional
    public Integer updateSLStockQty(StockRsParamList param){
        List<StockRsParam> list=param.getPdList();
        int sum=0;
        for(StockRsParam stockRsParam:list){
          int i=  this.getBaseDao().getSqlSession().update(SqlId.SQL_ID_UPDATE_SL_STOCK,stockRsParam);
            sum+=i;
        }
        return  new Integer(sum);
    }





    /**
     * 批量更新 （取消冻结供应商库存）
     * @return
     */
    @Transactional
    public Integer batchCancelFrozenStockSL(List<StockRsParam> stockRsParamList){
        int sum=0;
        for(StockRsParam stockRsParam:stockRsParamList){
            int i=  this.cancelFrozenStock(stockRsParam);
            sum+=i;
        }
        return  new Integer(sum);
    }





    /**
     * 检查卖家是否有库存
     *
     * @param stockRsParam
     * @return 库存存在返回true, 不存在返回false
     */
    public boolean checkStock(StockRsParam stockRsParam) {
        logger.debug("检查卖家库存");
        int count = super.getCount(SqlId.SQLID_COUNT_STOCK_SL, stockRsParam);
        if (count > NumberConst.IntDef.INT_ZERO) {
            return true;
        }
        return false;
    }

    
    
    
    


    /**
     * 更新库存（冻结卖家库存）
     *
     * @param param StockRsParam
     * @return 更新结果
     */
    @Transactional
    public int frozenStock(StockRsParam param) {
        logger.debug("冻结库存");
        return this.updateStock(SqlId.SQLID_COUNT_SL_STOCKED, SqlId.SQLID_FROZEN_STOCK_SL, param);
    }

    /**
     * 更新库存（取消冻结卖家库存）
     *
     * @param param StockRsParam
     * @return 更新结果
     */
    public int cancelFrozenStock(StockRsParam param) {
        logger.debug("取消冻结库存");
        return this.updateStock(SqlId.SQLID_COUNT_STOCK_SL, SqlId.SQLID_CANCEL_FROZEN_STOCK_SL, param);
    }

    /**
     * 更新库存
     *
     * @param countSqlId count的sqlId
     * @param sqlId      更新sqlID
     * @param param      StockRsParam
     * @return 更新结果
     */
    private int updateStock(String countSqlId, String sqlId, StockRsParam param) {
        logger.debug("更新库存");
        int count = super.getCount(countSqlId, param);
        if (NumberConst.IntDef.INT_ONE == count) {
            return super.modify(sqlId, param);
        } else if (NumberConst.IntDef.INT_ZERO == count) {
            throw new BusinessException("无该产品库存或库存不足!");
        } else {
            throw new BusinessException("不允许同时更新多个产品的库存数量!");
        }
    }











    /**
     * 增加库存数量或者新增一条库存信息
     *
     * @param StockRsParam StockRsParam
     */
    public void increaseStock(StockRsParam StockRsParam) {
        if (this.checkStock(StockRsParam)) {
            super.modify(SqlId.SQLID_ADD_STOCK_SL_QTY, StockRsParam);
        } else {
            super.save(SqlId.SQLID_INSERT_STOCK_SL, StockRsParam);
            super.save(SqlId.SQLID_INSERT_STOCK_OCC_SL, StockRsParam);
        }
        if (this.checkSuppStock(StockRsParam)) {
            super.modify(SqlId.SQLID_ADD_STOCK_SUPP_QTY, StockRsParam);
        } else {
            super.save(SqlId.SQLID_INSERT_STOCK_SUPP, StockRsParam);
            super.save(SqlId.SQLID_INSERT_STOCK_OCC_SUPP, StockRsParam);
        }
    }

    /**
     * 检查供应商是否有库存
     *
     * @param param
     * @return 库存有返回true, 没有返回false
     */
    public boolean checkSuppStock(StockRsParam param) {
        logger.debug("检查供应商库存");
        int count = super.getCount(SqlId.SQLID_COUNT_STOCK_SUPP, param);
        if (count > NumberConst.IntDef.INT_ZERO) {
            return true;
        }
        return false;
    }

    /**
     * 检查供应商库存数量
     *
     * @param StockRsParam
     * @return 库存充足返回true, 不足返回false
     */
    public boolean checkSuppStockQty(StockRsParam StockRsParam) {
        logger.debug("检查供应商库存");
        int count = super.getCount(SqlId.SQLID_COUNT_SUPP_STOCKED, StockRsParam);
        if (count > NumberConst.IntDef.INT_ZERO) {
            return true;
        }
        return false;
    }










   /* *//**
     * 卖家可用库存
     * @param StockRsParam
     * @return
     *//*
    public BigDecimal getSalerStock(StockRsParam StockRsParam)
    {
        StockRsParam.setHistoryDate(getTodayTime());

        BigDecimal salerStockNum = (BigDecimal)this.findObject(SqlId.SQL_ID_GET_SALER_STOCK_NUM,StockRsParam);

        //得到主订单订单编号
        List salerOrderList = this.findList(SqlId.SQL_ID_GET_ORDER_LIST,StockRsParam);

        //得到子订单订单编号
        List childSalerOrderList = this.findList(SqlId.SQL_ID_GET_CHILD_ORDER_LIST,StockRsParam);

        //得到所有的订单编号
        List orderList = getOrderList(salerOrderList,childSalerOrderList);

        if(CollectionUtils.isEmpty(orderList))
        {
            return salerStockNum;
        }
        //得到所有今天订单的供货数量
        BigDecimal todayStockNum = getTodayStockNum(orderList,StockRsParam);

        return DecimalUtil.subtract(salerStockNum,todayStockNum);
    }*/



    /**
     * 得到所有今天订单的供货数量
     * @param orderList
     * @param StockRsParam
     * @return
     */
    public BigDecimal getTodayStockNum(List orderList,StockRsParam StockRsParam)
    {
        Map<String,Object> paramsMap = new HashMap<String,Object>();

        paramsMap.put("orderList",orderList);
        paramsMap.put("pdCode",StockRsParam.getPdCode());

        BaseParam baseParam = new BaseParam();
        baseParam.setFilterMap(paramsMap);

        return (BigDecimal)this.findObject(SqlId.SQL_ID_GET_TODAY_STOCK_NUM,baseParam);
    }
    /**
     * 编译订单主和子订单List
     * @param OrderList
     * @param childOrderList
     * @return
     */
    public List getOrderList(List OrderList,List childOrderList)
    {
        if (CollectionUtils.isEmpty(childOrderList))
        {
            return OrderList;
        }

        for(int i = 0 ;i < childOrderList.size();i++)
        {
            OrderList.add(childOrderList.get(i));
        }
        return OrderList;
    }

    /**
     * 得到当前日期参数
     * @return
     */
    public String getTodayTime()
    {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);//获取年份
        int month=ca.get(Calendar.MONTH) + 1;//获取月份
        int day=ca.get(Calendar.DATE);//获取日

        String monthStr = month +"";
        String dayStr = day + "";
        if(month>0 && month <10)
        {
            monthStr = "0"+month;
        }
        if(day>0 && day <10)
        {
            dayStr = "0"+day;
        }
        return year+"-"+monthStr+"-"+dayStr;
    }

    /**
     * 产品库存判断是否断货
     * @param param 库存参数
     */
    public boolean shortSupplyProduct(StockRsParam param){
        //标识是否断货
        boolean flag = false;
        param.setStockNum(BigDecimal.ZERO);

        //检查卖家是否有库存
        boolean isSellerQty = this.checkStock(param);
        //检查供应商是否有库存
        boolean isSupplyQty = this.checkSuppStock(param);

        if(isSellerQty || isSupplyQty)
        {
            flag = true;
        }
        return flag;
    }



    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId. sql定义
     *
     * @author zhang_qiang1
     */
    interface SqlId {
        String SQL_ID_FIND_PRODUCT_STOCK = "com.msk.stock.logic.ISO151430Logic.findSLStock";

        String SQL_ID_UPDATE_SL_STOCK="com.msk.stock.logic.ISO151430Logic.updateSLSTOCK";

        String SQLID_FROZEN_STOCK_SL = "frozenStockSl";

        String SQLID_COUNT_SL_STOCKED = "countSlStocked";

        String SQLID_COUNT_STOCK_SL = "countSoStockSl";

        String SQLID_CANCEL_FROZEN_STOCK_SL = "cancelFrozenStockSl";

        String SQL_ID_UPDATE_SO_STOCK_SL= "updateSoStockSl";

        String SQL_ID_INSERT_SO_STOCK_RECORD= "insertSoStockRecord";

        String SQL_ID_INSERT_SO_STOCK_SL= "insertSoStockSl";

        String SQLID_ADD_STOCK_SL_QTY = "addStockSlQty";

        String SQLID_INSERT_STOCK_SL = "insertStockSl";

        String SQL_ID_GET_SALER_STOCK_NUM = "getSalerStockNum";

        String SQLID_INSERT_STOCK_OCC_SL = "insertStockOccSl";

        String SQLID_ADD_STOCK_SUPP_QTY = "addStockSuppQty";

        String SQL_ID_GET_TODAY_STOCK_NUM = "getTodayStockNum";

        String SQLID_INSERT_STOCK_SUPP = "insertStockSupp";

        String SQLID_INSERT_STOCK_OCC_SUPP = "insertStockOccSupp";

        String SQLID_COUNT_STOCK_SUPP = "countSoStockSupp";

        String SQLID_COUNT_SUPP_STOCKED = "countSuppStocked";

    }




}
