package com.msk.bms.ssc.controller;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCDifferRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.ssc.bean.SSC11311Bean;
import com.msk.ssc.bean.SSC11311Param;
import com.msk.ssc.bean.SSC11311Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 业务控制器：管理生产商入库差异单页面的业务
 * Created by xia_xiaojie on 2016/7/4.
 */
@Controller
@RequestMapping("/SSC11311")
public class SSC11311Controller extends BaseController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SSC11311Controller.class);

    /**
     * 生产商入库差异单页面初始化
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String init(Model model) {
        List differStatuses = this.getConstant("DifferStatus");
        model.addAttribute("differStatuses", differStatuses);
        return "ssc/SSC11311";
    }

    private List getConstant(String type) {
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap(type);
        TreeMap<String, String> codeMasterTree = new TreeMap<String, String>();
        codeMasterTree.putAll(codeMasterMap);
        return new ArrayList(codeMasterTree.entrySet());
    }

    /**
     * 生产商入库差异单列表查询
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageResult<SSC11311Bean> search(SSC11311Param queryParam) {
        this.handleParameter(queryParam);
        RsResponse<SSC11311Result> respResult = ISSCDifferRestUtil.queryDifferBasics(queryParam);
        SSC11311Result ssc11311Result = respResult.getResult();
        List<SSC11311Bean> sscDifferBasics = ssc11311Result.getSscDifferBasics();
        this.newLine(sscDifferBasics);

        PageResult<SSC11311Bean> pageResult = new PageResult<SSC11311Bean>();
        pageResult.setData(sscDifferBasics);
        pageResult.setRecordsTotal(ssc11311Result.getTotalCount());
        return pageResult;
    }

    /**
     * 处理查询条件
     */
    private void handleParameter(SSC11311Param queryParam) {
        Map<String, Object> filterMap = queryParam.getFilterMap();

        //模糊查询，%field%
        Object differCode = filterMap.get("differCode");
        if (null != differCode) {
            queryParam.setDifferCode(DbUtils.buildLikeCondition(String.valueOf(differCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object contractCode = filterMap.get("contractCode");
        if (null != contractCode) {
            queryParam.setContractCode(DbUtils.buildLikeCondition(String.valueOf(contractCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object contractName = filterMap.get("contractName");
        if (null != contractName) {
            queryParam.setContractName(DbUtils.buildLikeCondition(String.valueOf(contractName).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object purchaserName = filterMap.get("purchaserName");
        if (null != purchaserName) {
            queryParam.setPurchaserName(DbUtils.buildLikeCondition(String.valueOf(purchaserName).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object supplierName = filterMap.get("supplierName");
        if (null != supplierName) {
            queryParam.setSupplierName(DbUtils.buildLikeCondition(String.valueOf(supplierName).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object deliveryCode = filterMap.get("deliveryCode");
        if (null != deliveryCode) {
            queryParam.setDeliveryCode(DbUtils.buildLikeCondition(String.valueOf(deliveryCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object deliveryPreIntoCode = filterMap.get("deliveryPreIntoCode");
        if (null != deliveryPreIntoCode) {
            queryParam.setDeliveryPreIntoCode(DbUtils.buildLikeCondition(String.valueOf(deliveryPreIntoCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

        //过滤“已确认和未确认”
        String confirmStatus = StringUtil.toSafeString(filterMap.get("confirmStatus"));
        if (!confirmStatus.contains(",")) {
            queryParam.setConfirmStatus(confirmStatus);
        }

        queryParam.setEtd(StringUtil.toSafeString(filterMap.get("etd")));
        queryParam.setEta(StringUtil.toSafeString(filterMap.get("eta")));
        queryParam.setDeliveryDate(StringUtil.toSafeString(filterMap.get("deliveryDate")));
        queryParam.setArriveDate(StringUtil.toSafeString(filterMap.get("arriveDate")));
    }

    /**
     * 发货订单:入库单=1:N，入库单字段在页面换行显示
     */
    private void newLine(List<SSC11311Bean> ssc11311Beans) {
        for (SSC11311Bean ssc11311Bean : ssc11311Beans) {
            ssc11311Bean.setDeliveryPreIntoCode(ssc11311Bean.getDeliveryPreIntoCode().replaceAll(",", "<br />"));
            ssc11311Bean.setEtd(ssc11311Bean.getEtd().replaceAll(",", "<br />"));
            ssc11311Bean.setDeliveryDate(ssc11311Bean.getDeliveryDate().replaceAll(",", "<br />"));
            ssc11311Bean.setEta(ssc11311Bean.getEta().replaceAll(",", "<br />"));
            ssc11311Bean.setArriveDate(ssc11311Bean.getArriveDate().replaceAll(",", "<br />"));
        }
    }

    /**
     * 将差异单状态更新为已确认
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public String confirm(SSC11311Bean ssc11311Bean) {
        ssc11311Bean.setUpdId(this.getLoginUser().getEmplId());
        ssc11311Bean.setUpdTime(DateTimeUtil.getCustomerDate());

        //获取登录信息
        LoginUser loginUser = super.getLoginUser();
        ssc11311Bean.setConfirmId(loginUser.getEmplId());
        ssc11311Bean.setConfirmName(loginUser.getEmplName());
        ssc11311Bean.setConfirmTime(DateTimeUtil.getCustomerDate());

        //差异单状态 1：已确认
        ssc11311Bean.setConfirmStatus(String.valueOf(SscConstant.DifferStatus.CONFIRM));
        RsResponse<SSC11311Result> respResult = ISSCDifferRestUtil.confirmDifferBasic(ssc11311Bean);
        return respResult.getStatus();
    }

}
