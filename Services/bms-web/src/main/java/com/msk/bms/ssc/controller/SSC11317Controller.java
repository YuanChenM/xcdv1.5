package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCCommonUtil;
import com.msk.bms.ssc.controller.common.ISSCDeliveryPreInfoRestUtil;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.DsConstant;
import com.msk.common.config.server.SystemServerManager;
import com.msk.ds.bean.*;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wang_shuai on 2016/7/8.
 */
@Controller
@RequestMapping("SSC11317")
public class SSC11317Controller  extends BaseController {

    /**
     * logger
     */
    Logger logger = LoggerFactory.getLogger(SSC11317Controller.class);

    /**
     * 页面初始化
     * @return
     */
    @RequestMapping(value = "init/{type}", method = RequestMethod.POST)
    public String init(Model model,SSC11317RsParam ssc11317RsParam, @PathVariable(value = "type") String type){
        logger.info("预入库通知单页面初始化");

        SSC11317PreIntoBean ssc11317PreIntoBean = ISSCDeliveryPreInfoRestUtil.getFindDeliveryPreInfo(ssc11317RsParam);

        //文件下载-特殊字符处理
        try {
            if(!StringUtil.isNullOrEmpty(ssc11317PreIntoBean.getBusinessFileName())){
                ssc11317PreIntoBean.setBusinessFileNameStr(URLEncoder.encode(ssc11317PreIntoBean.getBusinessFileName(), "UTF-8"));
            }
            if(!StringUtil.isNullOrEmpty(ssc11317PreIntoBean.getQuarantineFileName())){
                ssc11317PreIntoBean.setQuarantineFileNameStr(URLEncoder.encode(ssc11317PreIntoBean.getQuarantineFileName(), "UTF-8"));
            }
            if(!StringUtil.isNullOrEmpty(ssc11317PreIntoBean.getInventoryFileName())){
                ssc11317PreIntoBean.setInventoryFileNameStr(URLEncoder.encode(ssc11317PreIntoBean.getInventoryFileName(), "UTF-8"));
            }
            if(!StringUtil.isNullOrEmpty(ssc11317PreIntoBean.getReportFileName())){
                ssc11317PreIntoBean.setReportFileNameStr(URLEncoder.encode(ssc11317PreIntoBean.getReportFileName(), "UTF-8"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        model.addAttribute("ssc11317PreIntoBean",ssc11317PreIntoBean);
        model.addAttribute("type", type);
        model.addAttribute("differId",ssc11317RsParam.getDifferId());
        model.addAttribute("verificationId", ssc11317RsParam.getVerificationId());

        //下载URL
        model.addAttribute("downLoadUrl", SystemServerManager.CommonServerManager.getFileServerDownloadProxy());

        return "ssc/SSC11317";
    }

    /**
     * 查询预入库通知单产品明细
     * @param ssc11317RsParam
     * @return
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11317PrePdBean> search(SSC11317RsParam ssc11317RsParam){
        logger.info("查询预入库通知单产品详情");

        RsResponse<PageResult<SSC11317PrePdBean>> rsResponse = ISSCDeliveryPreInfoRestUtil.findDeliveryPrePd(ssc11317RsParam);
        PageResult<SSC11317PrePdBean> detailResult = rsResponse.getResult();
        if (detailResult != null && SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())){
            return detailResult;
        } else {
            return new PageResult<>();
        }
    }

    /**
     * 更新预入库通知单基本信息
     * @param ssc11317RsParam
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(SSC11317RsParam ssc11317RsParam) throws Exception {
        logger.info("更新预入库通知单基本信息");

        String saveFlg = SystemConst.RsStatus.FAIL;

        LoginUser loginUser = super.getLoginUser();
        ssc11317RsParam.setUpdTime(DateTimeUtil.getCustomerDate());
        ssc11317RsParam.setUpdId(loginUser.getEmplId());

        RsResponse<SSC11317PreIntoBean> rsResponse = ISSCDeliveryPreInfoRestUtil.modifyDeliveryPreInto(ssc11317RsParam);

        if (SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())){
            saveFlg = SystemConst.RsStatus.SUCCESS;
        }
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            throw new BusinessException(rsResponse.getMessage());
        }
        return saveFlg;
    }

    /**
     * 确认发车按钮
     * @param param
     * @return
     */
    @RequestMapping(value = "departure", method = RequestMethod.POST)
    @ResponseBody
    public String departure(SSC11317RsParam param) throws Exception {
        logger.info("确认发车");
        String departureFlg = SystemConst.RsStatus.FAIL;

        SSC11317PreIntoBean ssc11317PreIntoBean = ISSCDeliveryPreInfoRestUtil.getFindDeliveryPreInfo(param);

        ISSCCommonUtil.setCommonParam(param, request);

        ISC182207RsParam isc182207RsParam  = new ISC182207RsParam();
        isc182207RsParam.setReceipt(ssc11317PreIntoBean.getDeliveryPreIntoCode());
        isc182207RsParam.setLgcsCode(ssc11317PreIntoBean.getLgcsAreaCode());
        isc182207RsParam.setSuppCode(ssc11317PreIntoBean.getPurchaserCode());
        isc182207RsParam.setSuppName(ssc11317PreIntoBean.getPurchaserName());
        isc182207RsParam.setPdProduceerId(StringUtil.toSafeString(ssc11317PreIntoBean.getSupplierId()));
        isc182207RsParam.setPdProduceerName(ssc11317PreIntoBean.getSupplierName());
        isc182207RsParam.setDeliveryStockStatus(StringUtil.toSafeString(DsConstant.DeliveryStockStatus.WAIT_SEND));
        isc182207RsParam.setDistMonth(this.getDistMonth(ssc11317PreIntoBean.getEtaStr()));
        isc182207RsParam.setHalfCode(this.getHalfCode(ssc11317PreIntoBean.getEtaStr()));
        isc182207RsParam.setDeliveryWarehouseAddr(ssc11317PreIntoBean.getDeliveryWarehouseAddr());
        isc182207RsParam.setDeliveryResponName(ssc11317PreIntoBean.getDeliveryResponsible());
        isc182207RsParam.setDeliveryResponTel(ssc11317PreIntoBean.getDeliveryResponsibleTel());
        isc182207RsParam.setDeliveryExecuteName(ssc11317PreIntoBean.getDeliveryExecutor());
        isc182207RsParam.setDeliveryExecuteTel(ssc11317PreIntoBean.getDeliveryExecutorTel());
        isc182207RsParam.setTransportUnitName(ssc11317PreIntoBean.getTrafficCompanyName());
        isc182207RsParam.setTransportUnitResponName(ssc11317PreIntoBean.getTrafficCompanyResponsible());
        isc182207RsParam.setTransportUnitResponTel(ssc11317PreIntoBean.getTrafficCompanyResponsibleTel());
        isc182207RsParam.setTransportUnitExecuteName(ssc11317PreIntoBean.getDriverName());
        isc182207RsParam.setTransportUnitExecuteTel(ssc11317PreIntoBean.getDriverTel());
        isc182207RsParam.setDeliveryStockTimeReq(ssc11317PreIntoBean.getEtaStr() + " 00:00:00");
        isc182207RsParam.setStockAddr(ssc11317PreIntoBean.getArriveWarehouseAddr());
        isc182207RsParam.setStockResponName(ssc11317PreIntoBean.getWarehouseKeeper());
        isc182207RsParam.setStockResponTel(ssc11317PreIntoBean.getWarehouseKeeperTel());
        isc182207RsParam.setStockExecuteName(ssc11317PreIntoBean.getAccepter());
        isc182207RsParam.setStockExecuteTel(ssc11317PreIntoBean.getAccepterTel());
        isc182207RsParam.setDeliveryMemo(ssc11317PreIntoBean.getSendRemark());
        isc182207RsParam.setCrtId(param.getCrtId());

        List<ISC182207DetailRsParam> departurePdList = new ArrayList<>();

        param.setPaging(false);
        RsResponse<PageResult<SSC11317PrePdBean>> pdResponse = ISSCDeliveryPreInfoRestUtil.findDeliveryPrePd(param);

        PageResult<SSC11317PrePdBean> detailResult = pdResponse.getResult();

        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal planBox = BigDecimal.ZERO;
        if(detailResult.getData().size() != NumberConst.IntDef.INT_ZERO){
            for(SSC11317PrePdBean pdBean : detailResult.getData()){
                ISC182207DetailRsParam ssc11317DeparturePd = new ISC182207DetailRsParam();
                ssc11317DeparturePd.setPdCode(pdBean.getPdCode());
                if (pdBean.getProductPlanBox() != null) {
                    planBox = new BigDecimal(df.format(pdBean.getProductPlanBox()));
                }
                ssc11317DeparturePd.setPlanDeliveryNum(planBox);
                ssc11317DeparturePd.setActualDeliveryNum(planBox);
                ssc11317DeparturePd.setNormsCode(pdBean.getNormsCode());
                ssc11317DeparturePd.setApplyDeliveryNum(planBox);
                ssc11317DeparturePd.setConfirmDeliveryNum(planBox);
                //产品外包装净重需要后期讨论
                ssc11317DeparturePd.setPdOutNw(pdBean.getWeightVal());
                ssc11317DeparturePd.setMemo(pdBean.getRemark());
                departurePdList.add(ssc11317DeparturePd);
            }
        }
        isc182207RsParam.setProductList(departurePdList);

        RsResponse<SSC11317DepartureParam> rsResponse = ISSCRestUtil.createXML(isc182207RsParam);

        if (SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())){
            param.setDepartureFlg(true);

            RsResponse<PageResult<SSC11317PrePdBean>> pdResponseUpdate = ISSCDeliveryPreInfoRestUtil.findDeliveryPrePd(param);

            if(pdResponseUpdate.getStatus().equals(SystemConst.RsStatus.SUCCESS)){
                departureFlg = SystemConst.RsStatus.SUCCESS;
            }
        }
        return departureFlg;
    }

    /**
     * 模拟产品入库
     * @param param
     * @return
     */
    @RequestMapping(value = "updatePreInto",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String updatePreInto(ISC1892I1Param param){
        logger.info("模拟产品入库");
        ISSCCommonUtil.setCommonParam(param, request);
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        List<ISC1892I1RsParam> productList = new ArrayList<>();
        String jsonStr = param.getTempProductList();
        if(!StringUtil.isNullOrEmpty(jsonStr)) {
            Map<String, ISC1892I1RsParam> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, ISC1892I1RsParam>>() {});
            for(ISC1892I1RsParam bean: map.values()){
                productList.add(bean);
            }
            param.setProductList(productList);
            param.setTempProductList(null);
            RsResponse<ISC1892I1RsResult> rsResponse = ISSCDeliveryPreInfoRestUtil.updatePreInto(param);
            if(SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())) {
                return rsResponse.getResult().getDeliveryStockStatus();
            } else {
                throw new BusinessException(rsResponse.getMessage());
            }
        } else {
            return "9";
        }
    }

    /**
     * 模拟产品入库
     *
     * @return 页面
     */
    @RequestMapping(value = "showTest", method = RequestMethod.POST)
    public String showProduct(String deliveryPreIntoCode,String deliveryPreIntoId,Integer ver,Model model) {
        logger.debug("模拟产品入库");
        model.addAttribute("deliveryPreIntoCode", deliveryPreIntoCode);
        model.addAttribute("ver", ver);
        model.addAttribute("deliveryPreIntoId", deliveryPreIntoId);
        return "ssc/SSC1131701";
    }


    /**
     * 保存上传文件信息(用于文件上传)
     */
    @RequestMapping(value = "saveFileInfo", method = RequestMethod.POST)
    @ResponseBody
    public String saveFileInfo(SSC11317PreIntoBean bean){

        LoginUser loginUser = super.getLoginUser();
        bean.setUpdTime(DateTimeUtil.getCustomerDate());
        bean.setUpdId(loginUser.getEmplId());

        //插入预入库文件信息
        RsResponse<SSC11317PreIntoBean> rsResponse = ISSCDeliveryPreInfoRestUtil.savePreIntoFile(bean);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            throw new BusinessException(rsResponse.getMessage());
        }
        return rsResponse.getStatus();
    }

    /**
     * 根据计划到货日期获取分销月度
     * @param startReceiptDate
     * @return
     */
    private String getDistMonth(String startReceiptDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startReceiptCalendar = Calendar.getInstance();
        startReceiptCalendar.setTime(formatter.parse(startReceiptDate));
        int year = startReceiptCalendar.get(Calendar.YEAR);
        int date = startReceiptCalendar.get(Calendar.DATE);
        int month = startReceiptCalendar.get(Calendar.MONTH) + 1;
        if (date > NumberConst.IntDef.INT_TWENTY_ONE) {
            month = month + 1;
        }
        String monthStr = month < 10 ? "0" + month : ""+ month;
        return StringUtil.toSafeString(year) + monthStr;
    }

    /**
     * 根据计划到货日期获取半旬
     * @param startReceiptDate
     * @return
     */
    private String getHalfCode(String startReceiptDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startReceiptCalendar = Calendar.getInstance();
        startReceiptCalendar.setTime(formatter.parse(startReceiptDate));
        int date = startReceiptCalendar.get(Calendar.DATE);
        if (date >= 1 && date <= 5) {
            return "3";
        }
        if (date >= 6 && date <= 10) {
            return "4";
        }
        if (date >= 11 && date <= 15) {
            return "5";
        }
        if (date >= 16 && date <= 20) {
            return "6";
        }
        if (date >= 21 && date <= 25) {
            return "1";
        }
        return "2";
    }

}
