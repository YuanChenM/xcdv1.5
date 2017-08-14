package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC1130601RsBean;
import com.msk.ssc.bean.SSC11306RsBean;
import com.msk.ssc.bean.SSC11306RsParam;
import com.msk.ssc.logic.SSC11306Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yang_yang
 */
@RestController
public class ISSC11306RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11306RestController.class);

    @Autowired
    private SSC11306Logic SSC11306Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        //修改发货订单基本信息
        static String SQL_ID_MODIFY_ORDER_BASIC = "modifyOrderBasic";
        //删除发货订单产品信息
        static String SQL_ID_MODIFY_ORDER_PD = "modifyOrderPd";
        //修改发货订单产品信息
        static String SQL_ID_MODIFY_PD = "modifyPd";
        //保存发货订单产品信息
        static String SQL_ID_SAVE_ORDER_PD = "saveOrderPd";
        //获取deliveryCode
        static String SQL_ID_GET_MAX_DELIVERY_CODE = "getMaxDeliveryCode";
        //获取发货订单产品信息
        static String SQL_ID_FIND_ORDER_PD = "findOrderPd";
    }

    /**
     * 查询发货订单内容列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/searchOrderPd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<SSC1130601RsBean>> searchOrderPd(@RequestBody RsRequest<SSC11306RsParam> param) {
        logger.info("查询发货订单内容列表");
        RsResponse<PageResult<SSC1130601RsBean>> response = new RsResponse<>();

        PageResult<SSC1130601RsBean> res = this.SSC11306Logic.findPage(param.getParam(), SSC1130601RsBean.class);
        if(res != null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 查询发货订单基本信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findOrderBasic",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11306RsBean> findOrderBasic(@RequestBody RsRequest<SSC11306RsParam> param) {
        logger.info("查询发货订单基本信息");
        RsResponse<SSC11306RsBean> response = new RsResponse<>();

        SSC11306RsBean res = this.SSC11306Logic.findOne(param.getParam());
        if(res != null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 修改发货订单产品信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyOrderPd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11306RsBean> modifyOrderPd(@RequestBody RsRequest<SSC11306RsParam> param) {
        logger.info("修改发货订单产品信息");

        RsResponse<SSC11306RsBean> response = new RsResponse<>();

        int result = this.SSC11306Logic.modifyOrderPd(SqlId.SQL_ID_MODIFY_ORDER_PD,param.getParam());
        if(result > 0){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("修改成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("修改失败");
        }
        return response;
    }

    /**
     * 修改发货订单基本信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyOrderBasic",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11306RsBean> modifyOrderBasic(@RequestBody RsRequest<SSC11306RsParam> param) {
        logger.info("修改发货订单基本信息");

        RsResponse<SSC11306RsBean> response = new RsResponse<>();

        int result = this.SSC11306Logic.modifyOrderBasic(SqlId.SQL_ID_MODIFY_ORDER_BASIC,param.getParam());
        if(result > 0){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("修改成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("修改失败");
        }
        return response;
    }

    /**
     * 修改发货订单基本信息(与modifyOrderBasic合并)
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyOrderBasicInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11306RsBean> modifyOrderBasicInfo(@RequestBody RsRequest<SSC11306RsParam> param) {
        logger.info("修改发货订单基本信息");

        RsResponse<SSC11306RsBean> response = new RsResponse<>();

        int result = this.SSC11306Logic.modify(param.getParam());
        if(result > 0){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("修改成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("修改失败");
        }
        return response;
    }

    /**
     * 修改发货订单基本信息(与modifyOrderPd合并)
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyOrderPdInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC1130601RsBean> modifyOrderPdInfo(@RequestBody RsRequest<SSC1130601RsBean> param) {
        logger.info("修改发货订单基本信息");

        RsResponse<SSC1130601RsBean> response = new RsResponse<>();

        int result = this.SSC11306Logic.modify(SqlId.SQL_ID_MODIFY_PD,param.getParam());
        if(result > 0){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("修改成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("修改失败");
        }
        return response;
    }

    /**
     * 新增发货订单基本信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/saveOrderBasic",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11306RsBean> saveOrderBasic(@RequestBody RsRequest<SSC11306RsParam> param) {
        logger.info("新增发货订单基本信息");

        RsResponse<SSC11306RsBean> response = new RsResponse<>();
        SSC11306RsParam ssc11306RsParam = param.getParam();
        Long deliveryId = this.commonLogic.maxId("ssc_delivery_order_basic", "DELIVERY_ID");

        ssc11306RsParam.setDeliveryId(deliveryId.toString());

        //获取deliveryCode   FH201607110001
        String deliveryCode = "FH" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.getCustomerDate());
        ssc11306RsParam.setDeliveryCode(deliveryCode);

        SSC11306RsBean rsBean = this.SSC11306Logic.findOne(SqlId.SQL_ID_GET_MAX_DELIVERY_CODE,ssc11306RsParam);

        if(rsBean != null && !StringUtil.isNullOrEmpty(rsBean.getDeliveryCode())){
            deliveryCode = "FH" + DecimalUtil.add(DecimalUtil.getBigDecimal(rsBean.getDeliveryCode()), BigDecimal.ONE);
        }else{
            deliveryCode += "0001";
        }

        ssc11306RsParam.setDeliveryCode(deliveryCode);

        int result = this.SSC11306Logic.save(ssc11306RsParam);
        if(result > 0){
            SSC11306RsBean ssc11306RsBean = new SSC11306RsBean();
            ssc11306RsBean.setDeliveryId(deliveryId);
            response.setResult(ssc11306RsBean);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("新增成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("新增失败");
        }
        return response;
    }

    /**
     * 新增发货订单产品信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/saveOrderPd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC1130601RsBean> saveOrderPd(@RequestBody RsRequest<SSC1130601RsBean> param) {
        logger.info("新增发货订单产品信息");

        RsResponse<SSC1130601RsBean> response = new RsResponse<SSC1130601RsBean>();
        SSC1130601RsBean ssc1130601RsBean = param.getParam();
        Long detailId = this.commonLogic.maxId("ssc_delivery_order_pd", "DETAIL_ID");

        ssc1130601RsBean.setDetailId(detailId);

        int result = this.SSC11306Logic.save(SqlId.SQL_ID_SAVE_ORDER_PD,ssc1130601RsBean);

        if(result > 0){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("新增成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("新增失败");
        }
        return response;
    }

    /**
     * 查询发货订单产品信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findOrderPd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC1130601RsBean> findOrderPd(@RequestBody RsRequest<SSC11306RsParam> param) {
        logger.info("查询发货订单产品信息");
        RsResponse<SSC1130601RsBean> response = new RsResponse<>();

        SSC1130601RsBean res = SSC11306Logic.findOne(SqlId.SQL_ID_FIND_ORDER_PD,param.getParam());
        if(res != null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

}


