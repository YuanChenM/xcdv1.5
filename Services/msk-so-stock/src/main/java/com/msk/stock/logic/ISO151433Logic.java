package com.msk.stock.logic;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.StatusConst;
import com.msk.stock.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存查询接口
 *
 * @author zhang_qiang1
 */
@Service
public class ISO151433Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(ISO151433Logic.class);

    /**
     *
     * @param param
     * @return
     */
    public ISO151433RsResult queryUsedStock(ISO151433RsParam param){
        ISO151433RsResult rsResult=this.getRsResult(param);
        Integer totalCount = rsResult.getPdStockList().size();
        rsResult.setTotalCount(totalCount);
        return  rsResult;
    }

    /**
     *
     * @param param
     * @return
     */
    public ISO151433RsResult getRsResult(ISO151433RsParam param){
        ISO151433RsResult rsResult=new ISO151433RsResult();
        List<Stock> stockListResult=  rsResult.getPdStockList();
        List<StockRsParam> pdList= param.getPdList();
        for(StockRsParam stockRsParam:pdList){
            Stock stock= this.getStockByStockRsParam(stockRsParam);
            if(stock!=null){
                stock.setPdTypeCode(stockRsParam.getPdTypeCode().trim());//返回之前的pdTypeCode
            }
            stockListResult.add(stock);
        }
        return  rsResult;
    }

    /**
     *
     * @param stockRsParam
     * @return
     */
     public Stock  getStockByStockRsParam(StockRsParam stockRsParam){
         Map<String,Object>map= this.fillPar(stockRsParam);// 设置 查询参数
         Stock stock   =  this.getBaseDao().getSqlSession().selectOne(SqlId.SQL_ID_FIND_PRODUCT_STOCK,map);
         return  stock;
     }

    /*
     * @param param  把前台参数  封装到map 中
     * @return
     */
    public Map<String,Object> fillPar( StockRsParam stockRsParam){
        Map<String,Object>map=new HashMap<String, Object>();
       map.put("salePlatform",stockRsParam.getSalePlatform());// 平台
       map.put("pdTypeCode",stockRsParam.getPdTypeCode().trim());// pdTypeCode;
       map.put("lgcsCode",stockRsParam.getLgcsCode());// 物流区
        return map;
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
    String SQL_ID_FIND_PRODUCT_STOCK = "com.msk.stock.logic.ISO151433Logic.findSLStock";
}
}
