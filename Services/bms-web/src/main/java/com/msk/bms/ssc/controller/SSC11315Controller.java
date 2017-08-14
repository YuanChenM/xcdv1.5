package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCDeliveryConfirmOrderRestUtil;
import com.msk.bms.ssc.controller.common.ISSCDeliveryPreInfoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by sun_jiaju on 2016/7/5.
 */
@Controller
@RequestMapping("SSC11315")
public class SSC11315Controller extends BaseController {
    private static Logger logger = getLogger(SSC11315Controller.class);

    @RequestMapping(value = "init/{type}" ,method = RequestMethod.POST)
    public String init (Model model,SSC11315Param ssc11315Param,@PathVariable(value = "type") String type){
        logger.debug("发货确认单详情页面初始化");
        // 发货确认单信息
        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = ISSCDeliveryConfirmOrderRestUtil.findDeliveryConfirm(ssc11315Param);

        SSC11315DeliveryConfirmRsBean deliveryConfirm = rsResponse.getResult();
        if (deliveryConfirm != null && SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())){
            deliveryConfirm.setContractName(ssc11315Param.getContractName());
            model.addAttribute("deliveryConfirm",deliveryConfirm);
        }

        // 发货确认产品信息总计
        RsResponse<SSC11315DeliveryConfirmDetailRsBean> detailRsResponse = ISSCDeliveryConfirmOrderRestUtil.findDeliveryConfirmDetailTotal(ssc11315Param);

        SSC11315DeliveryConfirmDetailRsBean total = detailRsResponse.getResult();
        if (total != null && SystemConst.RsStatus.SUCCESS.equals(detailRsResponse.getStatus())){
            model.addAttribute("allInfo",total);
        }
        model.addAttribute("ssc11315Param",ssc11315Param);
        model.addAttribute("type",type);

        return "ssc/SSC11315";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11315DeliveryConfirmDetailRsBean> search(SSC11315Param ssc11315Param) {
        RsResponse<PageResult<SSC11315DeliveryConfirmDetailRsBean>> detailRsResponse = ISSCDeliveryConfirmOrderRestUtil.findDeliveryConfirmDetail(ssc11315Param);

        PageResult<SSC11315DeliveryConfirmDetailRsBean> detailResult = detailRsResponse.getResult();
        if (detailResult != null && SystemConst.RsStatus.SUCCESS.equals(detailRsResponse.getStatus())){
            return detailResult;
        } else {
            return new PageResult<>();
        }
    }

    /**
     * 更新发货确认产品信息
     * @param ssc11315Param
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update( Model model, SSC11315Param ssc11315Param) throws Exception {
        logger.debug("更新发货确认产品信息");
        LoginUser loginUser = super.getLoginUser();
        ssc11315Param.setUpdId(loginUser.getEmplId());
        ssc11315Param.setUpdTime(DateTimeUtil.getCustomerDate());
        ssc11315Param.setCrtId(loginUser.getEmplId());
        ssc11315Param.setCrtName(loginUser.getEmplName());

        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = ISSCDeliveryConfirmOrderRestUtil.modifyDeliveryConfirmDetail(ssc11315Param);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }

        if(ssc11315Param.getDeliveryConfirmBasicVer() != null){
            ssc11315Param.setVer(ssc11315Param.getDeliveryConfirmBasicVer());

            ISSCDeliveryConfirmOrderRestUtil.modifyDeliveryConfirm(ssc11315Param);
        }

        return this.init(model, ssc11315Param, "1");
    }

    /**
     * 更新发货确认单信息
     * @param param
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, SSC11315Param param) throws Exception {
        logger.debug("更新发货确认单信息");
        //再修改,确认单状态及三方状态重置
        LoginUser loginUser = super.getLoginUser();

        if(!StringUtil.isNullOrEmpty(param.getByConfirmStatus())){
            if(param.getByConfirmStatus().equals("1")||param.getByConfirmStatus().equals("2")){
                param.setByConfirmId(loginUser.getEmplId());
                param.setByConfirmName(loginUser.getEmplName());
                param.setByConfirmTime(DateTimeUtil.getCustomerDate());
            }
         }
        if(!StringUtil.isNullOrEmpty(param.getWhConfirmStatus())){
            if(param.getWhConfirmStatus().equals("1")||param.getWhConfirmStatus().equals("2")){
                param.setWhConfirmId(loginUser.getEmplId());
                param.setWhConfirmName(loginUser.getEmplName());
                param.setWhConfirmTime(DateTimeUtil.getCustomerDate());
            }
        }
        if(!StringUtil.isNullOrEmpty(param.getPdConfirmStatus())){
            if(param.getPdConfirmStatus().equals("1")||param.getPdConfirmStatus().equals("2")){
                param.setPdConfirmId(loginUser.getEmplId());
                param.setPdConfirmName(loginUser.getEmplName());
                param.setPdConfirmTime(DateTimeUtil.getCustomerDate());
            }
        }

        param.setUpdId(loginUser.getEmplId());
        param.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = ISSCDeliveryConfirmOrderRestUtil.modifyDeliveryConfirm(param);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }
        return this.init(model, param,"1");
    }

    /**
     * 插入发货预入库单、发货预入库产品信息
     * @return
     */
    @RequestMapping(value = "insertDeliveryPreInto",method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<SSC11315DeliveryConfirmRsBean> insertDeliveryPreInto(SSC11315Param param){
        logger.info("插入发货预入库单、发货预入库产品信息");
        param.setPdCodes(param.getPdCodesStr().split(","));
        param.setProductPlanBoxes(param.getProductPlanBoxesStr().split(","));

        LoginUser loginUser = super.getLoginUser();
        param.setUpdId(loginUser.getEmplId());
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        param.setCrtId(loginUser.getEmplId());
        param.setCrtTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = ISSCDeliveryPreInfoRestUtil.insertDeliveryPreInto(param);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }

        if (rsResponse.getResult() == null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            rsResponse.getResult().setDeliveryPreIntoId(0L);
        }
        return rsResponse;
    }

    /**
     * check确认单产品是否全部装车，从而判断是否可以再生成预入库单
     *
     * @param ssc11315Param
     * @return
     */
    @RequestMapping(value = "checkPdPlanBox", method = RequestMethod.POST)
    @ResponseBody
    public String checkPdPlanBox(SSC11315Param ssc11315Param) {
        return ISSCDeliveryConfirmOrderRestUtil.checkPdPlanBox(ssc11315Param);
    }


    /**
     * 获取发货确认单待装车产品列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "chooseConfirmPds", method = RequestMethod.POST)
    public String chooseProduct(Model model, SSC11315Param ssc11315Param) {
        logger.debug("获取发货确认单待装车产品列表");

        List<SSC11315DeliveryConfirmDetailRsBean> pdBeans = ISSCDeliveryConfirmOrderRestUtil.chooseConfirmPds(ssc11315Param);

        model.addAttribute("pdBeans", pdBeans);
        model.addAttribute("deliveryBatch", ssc11315Param.getDeliveryBatch());
        model.addAttribute("deliveryConfirmId", ssc11315Param.getDeliveryConfirmId());
        model.addAttribute("deliveryConfirmCode", ssc11315Param.getDeliveryConfirmCode());

        return "ssc/SSC1131501";
    }
}
