package com.msk.stock.logic;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.stock.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

/**
 * 库存查询接口
 *
 * @author zhang_qiang1
 */
@Service
public class ISO151432Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151432Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    /**
     *
     * @param param
     * @return
     */
    public StockResult queryUsedStock(StockRsParamList param){
        StockResult rsResult=new StockResult();
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
     * 修改 供应商库存数量
     * @param   param
     */
    @Transactional
    public Integer updateSpStockQty(StockRsParamList param){
        List<StockRsParam> list=param.getPdList();
        int sum=0;
        for(StockRsParam stockRsParam:list){
         int i=  this.getBaseDao().getSqlSession().update(SqlId.SQL_ID_UPDATE_SP_STOCK,stockRsParam);
            sum+=i;
        }
        return  sum;
    }

    //--------------------------------------------------------------NEW  ADD  ----------------------------------------------------------------------------------

    /**
     *
     * @param iso151432RsParam
     * @return
     */
    public StockResult  queryStockQty( StockRsParamList iso151432RsParam){
        StockResult result=new StockResult();
        List<Stock>pdStockList=  result.getPdStockList();
        for(StockRsParam stockRsParam:iso151432RsParam.getPdList()){
          Stock stock= this.getSuppStockQty(stockRsParam);
            pdStockList.add(stock);
      }
    return result;
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
     * 查询供应商库存数量
     *
     * @param param StockRsParam
     * @return 供应商库存
     */
    @Transactional(readOnly = true)
    public Stock getSuppStockQty(StockRsParam param) {
        logger.debug("获得供应商库存数量");
        List<Stock> suppStockList =this.getStockList(param);
        if (suppStockList != null &&suppStockList.size()>0) {
            Stock stock=  suppStockList.get(NumberConst.IntDef.INT_ZERO);
            if (suppStockList.size() == NumberConst.IntDef.INT_ONE) {
                BigDecimal stockQty=  stock.getStockQty();
                stock.setSumStock(stockQty);
            } else {
                BigDecimal stockQty = BigDecimal.ZERO;
                for (Stock soStockSupp : suppStockList) {
                    stockQty = DecimalUtil.add(stockQty, soStockSupp.getStockQty());
                }
                stock.setSumStock(stockQty);
            }
            return stock;
        } else {
            throw new BusinessException("没有该产品的库存!");
        }
    }

    /**
     * 更新库存（冻结供应商库存）
     *
     * @param param StockRsParam
     * @return 更新结果
     */
    @Transactional
    public int frozenStockSupp(StockRsParam param) {
        logger.debug("冻结库存");
        return this.updateStock(SqlId.SQLID_COUNT_SUPP_STOCKED, SqlId.SQLID_FROZEN_STOCK_SUPP, param);
    }


    /**
     * 批量更新 （取消冻结供应商库存）
     * @return
     */
    @Transactional
    public Integer batchCancelFrozenStockSupp(List<StockRsParam> stockRsParamList){
        int sum=0;
        for(StockRsParam stockRsParam:stockRsParamList){
          int i=  this.cancelFrozenStockSupp(stockRsParam);
            sum+=i;
        }
        return  sum;
    }


    /**
     * 更新库存（取消冻结供应商库存）
     *
     * @param param StockRsParam
     * @return 更新结果
     */

    public int cancelFrozenStockSupp(StockRsParam param) {
        logger.debug("取消冻结库存");
        return this.updateStock(SqlId.SQLID_COUNT_STOCK_SUPP, SqlId.SQLID_CANCEL_FROZEN_STOCK_SUPP, param);
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
     * 备份库存
     *
     * @param StockRsParam
     * @return 影响行数
     */
    public int saveStockHistory(StockRsParam StockRsParam) {
        logger.debug("备份库存");
        return super.save(SqlId.SQL_ID_INSERT_SO_STOCK_SP_HISTORY, StockRsParam);
    }


    /**
     * 保存供应商库存
     * @param StockRsParam
     * @return 影响行数
     */
    @Transactional
    public int saveStockOfSupplier(StockRsParam StockRsParam){
        int returnInt = 0;


        //更新供应商库存
        returnInt = super.modify(SqlId.SQL_ID_UPDATE_SO_STOCK_SP, StockRsParam);
        //影响行数<=0时，说明供应商库存中不存在此商品，则新增供应商库存
        if (returnInt <= 0) {
            returnInt = super.save(SqlId.SQL_ID_INSERT_SO_STOCK_SP, StockRsParam);
        }

        //更新卖家库存
        if (returnInt == 1) {
            returnInt = saveStockOfSeller(StockRsParam);
        } else {
            throw new BusinessException("更新卖家库存失败,请和管理员联系");
        }

        //保存库存变更履历
        if (returnInt == 1) {
            returnInt = saveStockRecord(StockRsParam);
        } else {
            throw new BusinessException("保存库存变更履历失败,请和管理员联系");
        }

        return returnInt;
    }

    /**
     * 保存卖家库存
     * @param StockRsParam
     * @return 影响行数
     */
    public int saveStockOfSeller(StockRsParam StockRsParam) {
        //影响行数
        int returnInt = 0;
        returnInt = super.modify(SqlId.SQL_ID_UPDATE_SO_STOCK_SL, StockRsParam);
        //影响行数<=0时，说明数据库中不存在此商品，则新增卖家库存
        if (returnInt <= 0) {
            StockRsParam.setStockId(this.commonLogic.maxId("SO_STOCK_SL", "STOCK_ID"));
            returnInt = super.save(SqlId.SQL_ID_INSERT_SO_STOCK_SL, StockRsParam);
        }
        return returnInt;
    }


    /**
     * 保存库存变更履历
     * @param StockRsParam
     * @return 影响行数
     */
    public int saveStockRecord(StockRsParam StockRsParam){
        logger.debug("保存库存变更履历");
        //变更类型
        if (StockRsParam.getStockNum().compareTo(BigDecimal.ZERO) >= 0) {
            //增加库存
            StockRsParam.setChangeType("1");
        } else {
            //减少库存
            StockRsParam.setChangeType("2");
        }
        StockRsParam.setStockId(this.commonLogic.maxId("SO_STOCK_RECORD", "STOCK_ID"));
        return super.save(SqlId.SQL_ID_INSERT_SO_STOCK_RECORD, StockRsParam);
    }

    /**
     * 保存供应商库存
     * @param StockRsParamList
     * @return 影响行数
     */
    @Transactional
    public void saveStockOfSupplierList(List<StockRsParam> StockRsParamList) {
        StockRsParam StockRsParam = null;
        for(int i =0;i < StockRsParamList.size(); i++) {
            StockRsParam = StockRsParamList.get(i);
            int returnInt = 0;
            //更新供应商库存
            returnInt = super.modify(SqlId.SQL_ID_UPDATE_SO_STOCK_SP, StockRsParam);
            //影响行数<=0时，说明供应商库存中不存在此商品，则新增供应商库存
            if (returnInt <= 0) {
                StockRsParam.setStockId(this.commonLogic.maxId("SO_STOCK_SP", "STOCK_ID"));
                returnInt = super.save(SqlId.SQL_ID_INSERT_SO_STOCK_SP, StockRsParam);
            }

            //更新卖家库存
            if (returnInt == 1) {
                returnInt = saveStockOfSeller(StockRsParam);
            } else {
                throw new BusinessException("更新卖家库存失败,请和管理员联系");
            }

            //保存库存变更履历
            if (returnInt == 1) {
                returnInt = saveStockRecord(StockRsParam);
            } else {
                throw new BusinessException("保存库存变更履历失败,请和管理员联系");
            }
        }
    }


   /* *//**
     * 供应商可用库存
     * @param StockRsParam
     * @return
     *//*
    public BigDecimal getSupplierStock(StockRsParam StockRsParam)
    {
        StockRsParam.setHistoryDate(getTodayTime());

        BigDecimal spStockNum = (BigDecimal)this.findObject(SqlId.SQL_ID_GET_SP_STOCK_NUM,StockRsParam);

        //得到主订单订单编号
        List spOrderList = this.findList(SqlId.SQL_ID_GET_ORDER_LIST,StockRsParam);

        //得到子订单订单编号
        List childSpOrderList = this.findList(SqlId.SQL_ID_GET_CHILD_ORDER_LIST,StockRsParam);

        //得到所有的订单编号（问题：当主子订单数量比较大的时候不建议用这种方式可以分批处理）
        List orderList = getOrderList(spOrderList,childSpOrderList);

        if(CollectionUtils.isEmpty(orderList))
        {
            return spStockNum;
        }
        //得到所有今天订单的供货数量
        BigDecimal todayStockNum = getTodayStockNum(orderList,StockRsParam);

        return DecimalUtil.subtract(spStockNum,todayStockNum);
    }

    *//**
     * 得到所有今天订单的供货数量
     * @param orderList
     * @param StockRsParam
     * @return
     *//*
    public BigDecimal getTodayStockNum(List orderList,StockRsParam StockRsParam)
    {
        Map<String,Object> paramsMap = new HashMap<String,Object>();

        paramsMap.put("orderList",orderList);
        paramsMap.put("pdCode",StockRsParam.getPdCode());

        BaseParam baseParam = new BaseParam();
        baseParam.setFilterMap(paramsMap);

        return (BigDecimal)this.findObject(SqlId.SQL_ID_GET_TODAY_STOCK_NUM,baseParam);
    }*/
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
        String SQL_ID_FIND_PRODUCT_STOCK = "com.msk.stock.logic.ISO151432Logic.findSPStock";

        String SQL_ID_UPDATE_SP_STOCK="com.msk.stock.logic.ISO151432Logic.updateSPSTOCK";

        String SQLID_COUNT_STOCK_SUPP = "countSoStockSupp";

        String SQLID_COUNT_SUPP_STOCKED = "countSuppStocked";

        String SQLID_FROZEN_STOCK_SUPP = "frozenStockSupp";

        String SQLID_CANCEL_FROZEN_STOCK_SUPP = "cancelFrozenStockSupp";

        String SQL_ID_INSERT_SO_STOCK_SP_HISTORY= "insertSoStockSpHistory";

        String SQL_ID_UPDATE_SO_STOCK_SP= "updateSoStockSp";

        String SQL_ID_INSERT_SO_STOCK_SP= "insertSoStockSp";

        String SQL_ID_UPDATE_SO_STOCK_SL= "updateSoStockSl";

        String SQL_ID_INSERT_SO_STOCK_SL= "insertSoStockSl";

        String SQL_ID_INSERT_SO_STOCK_RECORD= "insertSoStockRecord";
    }




}
