package com.msk.ds.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.ds.bean.*;
import com.msk.ds.consts.BusinessConst;
import com.msk.ds.logic.SC182101Logic;
import com.msk.ds.logic.SC182102Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 发货入库明细PDF打印接口
 *
 * Created by li_kai1 on 2016/8/5.
 */
@Api(description = "发货入库明细PDF打印接口",tags = {"ISC182102PdfController"})
@RestController
public class ISC182102PdfController extends BaseRsController {
    /**
     * logger
     *
     */
    private static Logger logger = LoggerFactory.getLogger(ISC182102PdfController.class);

    @Autowired
    private SC182102Logic sc182102Logic;
    @Autowired
    private SC182101Logic sc182101Logic;
    /**
     * 发货入库单详细列表数据打印
     *
     * @return 分页查询数据
     */
    @ApiOperation(value = "发货入库明细PDF打印",notes = "发货入库明细PDF打印")
    @RequestMapping(value = "/sc/deliveryDetail/pdf/print",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST)
    public RsResponse<ISC182102RsResult> getDeliveryPDFData(@RequestBody RsRequest<ISC182102Param> param) {
        RsResponse<ISC182102RsResult> response = new RsResponse<ISC182102RsResult>();
        ISC182102Param isc182102Param = param.getParam();

        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("deliveryStockId",isc182102Param.getDeliveryStockId());
        baseParam.getFilterMap().put("sourceFlg",isc182102Param.getSourceFlg());
        SC182101Bean sc182101Bean = sc182101Logic.findOne(baseParam);

        response.setMessage("PDF获取数据失败");
        response.setStatus(SystemConst.RsStatus.FAIL);
        SC181102Param sc181102Param = new SC181102Param();
        if (sc182101Bean != null) {
            //已确认
            String status = BusinessConst.StockStatusName.IsSure;
            if (isc182102Param.getEdit()) {
                //待收货
                if(BusinessConst.DeliveryStockStatus.NoDelivery.equals(sc182101Bean.getDeliveryStockStatus())){
                    status = BusinessConst.StockStatusName.NoDelivery;
                }
                //已收货
                if(BusinessConst.DeliveryStockStatus.Delivery.equals(sc182101Bean.getDeliveryStockStatus())){
                    status = BusinessConst.StockStatusName.Delivery;
                }
            }
            sc182101Bean.setStatusName(status);

            /** 打印数据检索条件 */
            sc181102Param.setDistMonth(sc182101Bean.getDistMonth());
            sc181102Param.setAreaCode(sc182101Bean.getLgcsCode());
            sc181102Param.setSupplierCode(sc182101Bean.getSuppCode());
            sc181102Param.setHalfCode(sc182101Bean.getHalfCode());

            //获取半旬
            DistSuppChain halfName = sc182102Logic.getHalfName(sc181102Param);
            if(null != halfName && halfName.getHalfNameList().size() > 0){
               for(DistSuppChain halfName1:halfName.getHalfNameList()){
                  if(halfName1.getHalfCode().equals(sc182101Bean.getHalfCode())){
                    sc182101Bean.setHalfName(halfName1.getHalfName());
                  }
                }
            }


            sc181102Param.getFilterMap().put("deliveryStockId", sc182101Bean.getDeliveryStockId());

            PageResult<SC182102Param> pageList = sc182102Logic.findPageList(sc181102Param,isc182102Param.getEdit());

            List<SC182102Param> dataList = pageList.getData();
            ISC182102RsResult isc182102RsResult = new ISC182102RsResult();
            isc182102RsResult.setSc182102Info(sc182101Bean);
            isc182102RsResult.setDetailList(dataList);

            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("PDF获取数据成功");
            response.setResult(isc182102RsResult);
        }
        return response;

    }
}
