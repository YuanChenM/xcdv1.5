package com.msk.stock.logic;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.stock.bean.Stock;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 库存查询接口
 *
 * @author zhang_qiang1
 */
@Service
public class ISO151431Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151431Logic.class);


   /**
     *
     * @param param
     * @return
     */
    public StockResult queryUsedStock(StockRsParamList param){
        StockResult rsResult=this.getRsResult(param);
        int totalCount=rsResult.getPdStockList().size();
         rsResult.setTotalCount(totalCount);
        if(param.isPaging()){
            rsResult.setPdStockList(this.getPageList(rsResult.getPdStockList(),param));
            rsResult.setTotalPage(totalCount, param.getPageCount());
            rsResult.setPageNo(param.getPageNo());
        }
        return  rsResult;
    }


    /**
     *
     * @param param
     * @return
     */
    public StockResult getRsResult(StockRsParamList param){
        StockResult rsResult=new StockResult();
        List<Stock>allStockList=  rsResult.getPdStockList();
        List<StockRsParam>pdList=param.getPdList();
        for(StockRsParam stockRsParam:pdList){
           allStockList.addAll(this.getStockList(stockRsParam));
        }
        return  rsResult;
    }


    /**
     *  获取卖家  +  供应商的  可用 库存
     * @param stockRsParam
     * @return
     */
    public  List<Stock> getStockList(StockRsParam stockRsParam){
        List<Stock>stockList=new ArrayList<>();
        String  queryType= stockRsParam.getQueryType();
        if("sl".equals(queryType)){
            List<Stock>slStockList=  this.getPdStockList(stockRsParam,SqlId.SQL_ID_FIND_SL_STOCK);// 卖家库存可用库存
            stockList.addAll(slStockList);
        }else if("sp".equals(queryType)){
            List<Stock>spStockList=  this.getPdStockList(stockRsParam,SqlId.SQL_ID_FIND_SP_STOCK);// 供应商可用库存
            stockList.addAll(spStockList);

        }else {
            List<Stock>slStockList=  this.getPdStockList(stockRsParam,SqlId.SQL_ID_FIND_SL_STOCK);// 卖家库存可用库存
            List<Stock>spStockList=  this.getPdStockList(stockRsParam,SqlId.SQL_ID_FIND_SP_STOCK);// 供应商可用库存
            stockList.addAll(slStockList);
            stockList.addAll(spStockList);
        }
        return stockList;
    }

    /**
     *  根据要求分页
      * @param allStockList
     * @return
     */
    public List<Stock> getPageList( List<Stock>allStockList ,StockRsParamList param){
           List<Stock> stockList=new ArrayList<Stock>();
            if(allStockList.size()>0){
                Integer pageCount= param.getPageCount();
                Integer startPos=  param.getStartPos();
                Integer pageNo= param.getPageNo();
                int y=(allStockList.size()-pageCount*pageNo)>0?startPos+pageCount:allStockList.size();
                for(int i=startPos;i<y;i++){
                    stockList.add(allStockList.get(i));
                }
            }
        return  stockList;
    }

    /**
     *    获取 供应商  或者是   卖家的库存
     * @param stockRsParam
     * @param SQLID_STOCK
     * @return
     */
    public  List<Stock> getPdStockList(StockRsParam stockRsParam,String SQLID_STOCK){
        List<Stock>productStockList= this.getBaseDao().getSqlSession().selectList(SQLID_STOCK,stockRsParam);
        return productStockList;
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
        String SQL_ID_FIND_SL_STOCK = "com.msk.stock.logic.ISO151431Logic.findSLStock";

        String SQL_ID_FIND_SP_STOCK = "com.msk.stock.logic.ISO151431Logic.findSPStock";
    }

}
