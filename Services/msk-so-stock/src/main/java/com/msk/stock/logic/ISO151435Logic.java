package com.msk.stock.logic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.OrderConst;
import com.msk.common.logic.CommonLogic;
import com.msk.stock.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 订单库存变更接口
 *
 * @author zhang_qiang1
 */
@Service
public class ISO151435Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(ISO151435Logic.class);


    @Autowired
    private CommonLogic  commonLogic;

   //  根据订单类型

    /**
     *  根据订单类型变更库存
     * @param param
     * @return
     */

    @Transactional
    public ISO151435RsResult  updateStock(ISO151435RsParam param){
        ISO151435RsResult rsResult=new ISO151435RsResult();
        Integer orderType=  param.getOrderType();
        if(orderType== OrderConst.OrderType.DISTRIBUTION_ORDER){ /**分销订单*/
                 this.distribtionOrder(param);
        }else if(orderType==OrderConst.OrderType.THIRD_PARTY_ORDER){ /**第三方订单*/
                 this.thirdPartyOrder(param);
        }else if(orderType== OrderConst.OrderType.BUYER_STOCKPILING_ORDER){ /**买手囤货订单*/
                 this.buyerStockpingOrder(param);
        }else  if(orderType== OrderConst.OrderType.BUYER_SALE_ORDER){ /**买手销售订单*/
                 this.buyerSaleOrder(param);
        }else if(orderType== OrderConst.OrderType.THIRD_BUYER_SALE_ORDER){  /**第三方买手销售订单*/
                  this.thirdBuyerSaleOrder(param);
        }else if(orderType== OrderConst.OrderType.THIRD_BUYER_ORDER){   /**第三方买手囤货订单*/
//                 this.thridBuyerOrder(param);
        }else if(orderType== OrderConst.OrderType.BIG_PROMOTION_ORDER){ /**大促会订单    目前没有使用        */

        }
        return  rsResult;
    }


    /**
     * 分销订单  卖家库存减少 ，供应商库存减少
     * @param param
     */
    public void   distribtionOrder(ISO151435RsParam param){
        BasePageParam slparm=new BasePageParam();
        slparm.getFilterMap().put("slCode",param.getSlCode());
        slparm.getFilterMap().put("supplyPatform",param.getSupplyPatform());
        slparm.getFilterMap().put("lgcsCode",param.getLgcsCode());
        for(StockSupplier supplier:param.getSupplierList()){
            slparm.getFilterMap().put("pdCode",supplier.getPdCode());
            slparm.getFilterMap().put("orderQty",supplier.getOrderQty());
            slparm.getFilterMap().put("supplierCode",supplier.getSupplierCode());
            super.modify(SqlId.SQL_UPDATE_SP_STOCK, slparm);
            super.modify(SqlId.SQL_UPDATE_SL_STOCK, slparm);
        }
    }


    /**
     * 第三方订单  卖家减少，对应的供应商减少
     * @param param
     */
    public void thirdPartyOrder(ISO151435RsParam param){
        int effectCount=0;
        BasePageParam slparm=new BasePageParam();
        slparm.getFilterMap().put("slCode",param.getSlCode());
        slparm.getFilterMap().put("lgcsCode",param.getLgcsCode());
        slparm.getFilterMap().put("supplyPatform",param.getSupplyPatform());
        for(StockSupplier supplier:param.getSupplierList()){
            slparm.getFilterMap().put("pdCode",supplier.getPdCode());
            slparm.getFilterMap().put("orderQty",supplier.getOrderQty());
            slparm.getFilterMap().put("supplierCode",supplier.getSupplierCode());
            effectCount= super.modify(SqlId.SQL_UPDATE_SP_STOCK, slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sp");
            effectCount=super.modify(SqlId.SQL_UPDATE_SL_STOCK, slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sl");
        }
    }


    /**
     * 买手销售订单  买手（sl）库存减少, 买手 （sp）减少
     * @param param
     */
    public void buyerSaleOrder(ISO151435RsParam param){
        int effectCount=0;
        BasePageParam slparm=new BasePageParam();
        slparm.getFilterMap().put("slCode",param.getSlCode());
        slparm.getFilterMap().put("lgcsCode",param.getLgcsCode());
        slparm.getFilterMap().put("buyCode",param.getBuyerCode());// 买手code
        slparm.getFilterMap().put("buyName",param.getBuyerName());//  买手名称
        slparm.getFilterMap().put("supplyPatform",param.getSupplyPatform());
        for(StockSupplier supplier:param.getSupplierList()){
            slparm.getFilterMap().put("pdCode", supplier.getPdCode());
            slparm.getFilterMap().put("orderQty", supplier.getOrderQty());
            slparm.getFilterMap().put("supplierCode", supplier.getSupplierCode());
           /* effectCount= super.modify(SqlId.SQL_UPDATE_SL_STOCK, slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sl");*/
            effectCount=super.modify(SqlId.SQL_MINUS_SL_BUYER_STOCK,slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sl");
            effectCount= super.modify(SqlId.SQL_MINUS_SP_BUYER_STOCK, slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sp");

        }
    }


    /**
     * 第三方买手销售订单    买手 减少     供应商  减少
     * @param param
     */
    public void thirdBuyerSaleOrder(ISO151435RsParam param){
        int effectCount=0;
        BasePageParam slparm=new BasePageParam();
        slparm.getFilterMap().put("slCode",param.getSlCode());
        slparm.getFilterMap().put("lgcsCode",param.getLgcsCode());
        slparm.getFilterMap().put("buyCode",param.getBuyerCode());// 买手code
        slparm.getFilterMap().put("buyName",param.getBuyerName());//  买手名称
        slparm.getFilterMap().put("supplyPatform",param.getSupplyPatform());
        for(StockSupplier supplier:param.getSupplierList()){
            slparm.getFilterMap().put("pdCode", supplier.getPdCode());
            slparm.getFilterMap().put("orderQty", supplier.getOrderQty());
            slparm.getFilterMap().put("supplierCode", supplier.getSupplierCode());
           /* effectCount= super.modify(SqlId.SQL_UPDATE_SL_STOCK, slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sl");*/
            effectCount=super.modify(SqlId.SQL_MINUS_SL_BUYER_STOCK,slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sl");
            effectCount= super.modify(SqlId.SQL_MINUS_SP_BUYER_STOCK, slparm);
            this.checkStock(effectCount,slparm.getFilterMap(),"so_stock_sp");

        }
    }

    /**
     * 买手囤货订单    买手(sl)增加， 买手（sp）增加    供应商减少
     * @param param
     */
    public void buyerStockpingOrder(ISO151435RsParam param){
        //  先检测  买手有没有这样的库存
        BasePageParam basePageParam=new BasePageParam();
        basePageParam.getFilterMap().put("lgcsCode",param.getLgcsCode());
        basePageParam.getFilterMap().put("buyerCode",param.getBuyerCode());
        basePageParam.getFilterMap().put("sourceSlCode",param.getBuyerCode());
        basePageParam.getFilterMap().put("supplyPatform",param.getSupplyPatform());
        for(StockSupplier supplier :param.getSupplierList()){//  供应商
            basePageParam.getFilterMap().put("pdCode",supplier.getPdCode());
            Integer  buySLcount= (Integer) super.findObject(SqlId.SQL_CHECK_SL_BUYER_STOCK,basePageParam);
            if(buySLcount==0){//  创建对应pdcode 的买手库存 在so_stock_sl
             Long maxId= commonLogic.maxId("so_stock_sl","STOCK_ID");
                basePageParam.getFilterMap().put("sl_stock_id",maxId);
              super.modify(SqlId.SQL_ADD_BUYER_SL_STOCK,basePageParam);
            }//无论是否存在  买手增加，供应商减少
                basePageParam.getFilterMap().put("orderQty",supplier.getOrderQty());
                super.modify(SqlId.SQL_PLUS_SL_BUYER_STOCK,basePageParam);
                basePageParam.getFilterMap().put("supplierCode",supplier.getSupplierCode());
                super.modify(SqlId.SQL_UPDATE_SP_STOCK,basePageParam);
                //  先判断    在stock_sp  是否存在 供应商给买手的库存
            Integer  buySPcount= (Integer) super.findObject(SqlId.SQL_CHECK_SP_BUYER_STOCK,basePageParam);
            if(buySPcount==0){ //创建对应pdcode 的买手库存 在so_stock_sp
                Long maxId= commonLogic.maxId("so_stock_sp","STOCK_ID");
                basePageParam.getFilterMap().put("sp_stock_id",maxId);
                super.modify(SqlId.SQL_ADD_BUYER_SP_STOCK,basePageParam);
            }
             super.modify(SqlId.SQL_PLUS_SP_BUYER_STOCK,basePageParam);// 添加 供应商（买手）库存

        }
    }

    /**
     * 第三方买手囤货订单  sl 买手 增加    sp  买手增加  sp  供应商 减少
     * @param param
     */
    public void thridBuyerOrder(ISO151435RsParam param){
        //  先检测  买手有没有这样的库存
        BasePageParam basePageParam=new BasePageParam();
        basePageParam.getFilterMap().put("lgcsCode",param.getLgcsCode());
        basePageParam.getFilterMap().put("buyerCode",param.getBuyerCode());
        basePageParam.getFilterMap().put("sourceSlCode",param.getBuyerCode());
        basePageParam.getFilterMap().put("supplyPatform",param.getSupplyPatform());
        basePageParam.getFilterMap().put("slCode",param.getSlCode());

        for(StockSupplier supplier :param.getSupplierList()){//  供应商
            basePageParam.getFilterMap().put("pdCode",supplier.getPdCode());
            Integer  buySLcount= (Integer) super.findObject(SqlId.SQL_CHECK_SL_BUYER_STOCK,basePageParam);
            if(buySLcount==0){
                //  创建对应pdcode 的买手库存 在so_stock_sl
                Long maxId= commonLogic.maxId("so_stock_sl","STOCK_ID");
                basePageParam.getFilterMap().put("sl_stock_id",maxId);
                super.modify(SqlId.SQL_ADD_BUYER_SL_STOCK,basePageParam);
            }
            //无论是否存在  买手增加，供应商减少
            basePageParam.getFilterMap().put("orderQty", supplier.getOrderQty());
            super.modify(SqlId.SQL_PLUS_SL_BUYER_STOCK, basePageParam);
            basePageParam.getFilterMap().put("supplierCode", supplier.getSupplierCode());
            super.modify(SqlId.SQL_UPDATE_SP_STOCK,basePageParam);
            //  先判断    在stock_sp  是否存在 供应商给买手的库存
            Integer  buySPcount= (Integer) super.findObject(SqlId.SQL_CHECK_SP_BUYER_STOCK,basePageParam);
            if(buySPcount==0){
                //创建对应pdcode 的买手库存 在so_stock_sp
                Long maxId= commonLogic.maxId("so_stock_sp","STOCK_ID");
                basePageParam.getFilterMap().put("sp_stock_id",maxId);
                super.modify(SqlId.SQL_ADD_BUYER_SP_STOCK,basePageParam);
            }
            super.modify(SqlId.SQL_PLUS_SP_BUYER_STOCK,basePageParam);// 添加 供应商（买手）库存
        }
    }


    /**
     *
     * @param effectCount
     * @param filterMap
     */
    public void  checkStock(int effectCount,Map<String,Object> filterMap,String tahbleName){
        StringBuffer  message=new StringBuffer();
        if(effectCount==0){
            message.append("该订单变更库存数据时，没有找到对应的库存在"+tahbleName+"中，");
            if("so_sotock_sp".equals(tahbleName)){
                message.append("条件为：slCode:"+filterMap.get("slCode")+" lgcsCode:"+filterMap.get("lgcsCode")+ "pdCode:" +filterMap.get("pdCode"));

            }else if("so_stock_sl".equals(tahbleName)){
                message.append("条件为：supplieCode:"+filterMap.get("supplieCode")+" lgcsCode:"+filterMap.get("lgcsCode")+ "pdCode:" +filterMap.get("pdCode"));
            }
            message.append(" :"+filterMap.get(""));
            throw new BusinessException(message.toString());
        }else if(effectCount>1){
            message.append("该订单变更库存数据时,数据有问题！");
            throw new BusinessException(message.toString());
        }
    }
    /**
     *
     * @param baseDao
     */
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
        String SQL_UPDATE_SL_STOCK = "updateSLStock";

        String SQL_MINUS_SL_BUYER_STOCK = "minusSLBuyerStock";

        String SQL_PLUS_SL_BUYER_STOCK = "plusSLBuyerStock";

        String SQL_MINUS_SP_BUYER_STOCK = "minusSPBuyerStock";

        String SQL_PLUS_SP_BUYER_STOCK = "plusSPBuyerStock";

        String SQL_UPDATE_SP_STOCK="updateSPStock";

        String SQL_ADD_BUYER_SL_STOCK="addSLBuyerStock";

        String SQL_ADD_BUYER_SP_STOCK="addSPBuyerStock";

        String SQL_CHECK_SL_BUYER_STOCK="checkSLBuyerStock";

        String SQL_CHECK_SP_BUYER_STOCK="checkSPBuyerStock";
    }
}
