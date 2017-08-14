package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC1130801Logic;
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

/**
 * 支付管控单
 */
@RestController
@Api(description = "支付管控单RestController",tags = {"ISSC1130801RestController"})
public class ISSC1130801RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC1130801RestController.class);


    @Autowired
    private SSC1130801Logic ssc1130801Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 查询付款记录
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "查询",notes = "分页查询支付管控单信息")
    @RequestMapping(value = "/ssc/findPaymentInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<SSC1130801RsBean>> findPaymentInfo(@RequestBody RsRequest<SSC1130801RsParam> param) {
        logger.info("查询付款记录");
        RsResponse<PageResult<SSC1130801RsBean>> response=new  RsResponse<PageResult<SSC1130801RsBean>>();

        PageResult<SSC1130801RsBean> res =this.ssc1130801Logic.findPage(param.getParam(), SSC1130801RsBean.class);

        if(res!=null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }


    /**
     * 新增/编辑支付记录
     *
     * @param ssc1130801RsBean
     * @return
     */
    @ApiOperation(value = "新增or修改",notes = "根据主键，新增或者修改支付管控单记录")
    @RequestMapping(value = "/ssc/saveOrModifyPaymentInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public RsResponse<Integer> saveOrModifyPaymentInfo(@RequestBody RsRequest<SSC1130801RsBean> ssc1130801RsBean) {

        RsResponse<Integer> response= new RsResponse<Integer>();
        Integer result = null;
        SSC1130801RsBean bean = ssc1130801RsBean.getParam();
        if(bean.getPaymentId()!=null){
            logger.info("支付记录修改");
            bean.setRemitTime(DateTimeUtil.getCustomerDate());
             result = this.ssc1130801Logic.modifyPaymentInfo(bean);
        } else{
            logger.info("支付管控新增");
            Long id = commonLogic.maxId("ssc_payment_info","PAYMENT_ID");
            bean.setPaymentId(id);
            bean.setStatus(SscConstant.PaymentStatus.UNPAID);
            result = this.ssc1130801Logic.savePaymentInfo(bean);
        }
        if(result>0){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("操作成功");
            response.setResult(result);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("改数据已被修改，请刷新页面再试！");
        }
        return response;
    }




}


