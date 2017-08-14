package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCVerificationRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 核销单一览Controller
 *
 * @author yang_yang
 */
@Controller
@RequestMapping("SSC11321")
public class SSC11321Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11321Controller.class);

    /**
     * 初始化页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("核销单一览初始化");

        //从redis获取合同状态
        Map<String, String> contractStatusMap = CodeMasterManager.findCodeMasterMap("SscOrderStatus");
        List contractStatusList = new ArrayList(contractStatusMap.entrySet());

        model.addAttribute("contractStatusList", contractStatusList);

        //核销单状态
        Map<String, String> statusMap = CodeMasterManager.findCodeMasterMap("VerificationStatus");
        List statusList = new ArrayList(statusMap.entrySet());

        model.addAttribute("statusList", statusList);

        //审核状态
        Map<String, String> auditStatusMap = CodeMasterManager.findCodeMasterMap("VerificationAuditStatus");
        List auditStatusList = new ArrayList(auditStatusMap.entrySet());

        model.addAttribute("auditStatusList", auditStatusList);

        return "ssc/SSC11321";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11321RsBean> search(SSC11321RsParam ssc11321RsParam) {

        if (!StringUtil.isNullOrEmpty((String) ssc11321RsParam.getFilterMap().get("verificationCode"))) {
            ssc11321RsParam.getFilterMap().put("verificationCode", ((String) ssc11321RsParam.getFilterMap().get("verificationCode")).trim());
        }
        if (!StringUtil.isNullOrEmpty((String) ssc11321RsParam.getFilterMap().get("contractCode"))) {
            ssc11321RsParam.getFilterMap().put("contractCode", ((String) ssc11321RsParam.getFilterMap().get("contractCode")).trim());
        }
        if (!StringUtil.isNullOrEmpty((String) ssc11321RsParam.getFilterMap().get("contractName"))) {
            ssc11321RsParam.getFilterMap().put("contractName", ((String) ssc11321RsParam.getFilterMap().get("contractName")).trim());
        }
        if (!StringUtil.isNullOrEmpty((String) ssc11321RsParam.getFilterMap().get("chargerName"))) {
            ssc11321RsParam.getFilterMap().put("chargerName", ((String) ssc11321RsParam.getFilterMap().get("chargerName")).trim());
        }

        String contractStatus = StringUtil.toString(ssc11321RsParam.getFilterMap().get("contractStatus"));
        if (!StringUtil.isNullOrEmpty(contractStatus)) {
            ssc11321RsParam.setContractStatusArr(contractStatus.split(","));
        }

        String status = StringUtil.toString(ssc11321RsParam.getFilterMap().get("status"));
        if (!StringUtil.isNullOrEmpty(status)) {
            ssc11321RsParam.setStatusArr(status.split(","));
        }

        String auditStatus = StringUtil.toString(ssc11321RsParam.getFilterMap().get("auditStatus"));
        if (!StringUtil.isNullOrEmpty(auditStatus)) {
            String[] strs = auditStatus.split(",");
            List<Integer> auditStatuses = new ArrayList<Integer>();
            for (String str : strs) {
                auditStatuses.add(Integer.parseInt(str));
            }
            ssc11321RsParam.setAuditStatuses(auditStatuses);
        }

        if(!StringUtil.isNullOrEmpty(ssc11321RsParam.getIsPaymentRequest())){
            ssc11321RsParam.setPaymentType(SscConstant.SscPaymentType.BALANCE);
        }

        PageResult<SSC11321RsBean> result = ISSCVerificationRestUtil.findVerifications(ssc11321RsParam);
        return result;
    }

    /**
     * 更新核销单审核状态
     */
    @RequestMapping(value = "/auditVerification", method = RequestMethod.POST)
    @ResponseBody
    public String auditVerification(SSC11321RsBean ssc11321RsBean) {
        int auditStatus = ssc11321RsBean.getAuditStatus();
        LoginUser loginUser = super.getLoginUser();
        String userId = loginUser.getEmplId();
        String userName = loginUser.getEmplName();
        Date now = DateTimeUtil.getCustomerDate();
        ssc11321RsBean.setUpdId(userId);

        if (SscConstant.VerificationAuditStatus.PUR_CONFIRM == auditStatus || NumberConst.IntDef.INT_N_ONE == auditStatus) {
            ssc11321RsBean.setPurchaserConfirmId(userId);
            ssc11321RsBean.setPurchaserConfirmName(userName);
            ssc11321RsBean.setPurchaserConfirmTime(now);
        }
        if (SscConstant.VerificationAuditStatus.SUPP_COMFIRM == auditStatus || NumberConst.IntDef.INT_N_TWO == auditStatus) {
            ssc11321RsBean.setSupplierConfirmId(userId);
            ssc11321RsBean.setSupplierConfirmName(userName);
            ssc11321RsBean.setSupplierConfirmTime(now);
        }
        if (auditStatus < NumberConst.IntDef.INT_ZERO) {
            ssc11321RsBean.setAuditStatus(SscConstant.VerificationAuditStatus.CONFIRMED);
        }

        RsResponse<SSC11321RsResult> respResult = ISSCVerificationRestUtil.auditVerification(ssc11321RsBean);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }
        return respResult.getStatus();
    }

    /**
     * 初始化选择核销单页面
     *
     * @param model
     * @return 页面
     */
    @RequestMapping(value = "chooseVerification",method = RequestMethod.POST)
    public String chooseVerification(Model model, SSC11321RsParam ssc11321RsParam) {
        logger.debug("选择核销单页面初始化");
        model.addAttribute("ssc11321RsParam",ssc11321RsParam);
        return "ssc/SSC1132101";
    }

    /**
     * 删除核销单
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteVerification(SSC11321RsBean ssc11321RsBean) {
        ssc11321RsBean.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<SSC11321RsResult> respResult = ISSCVerificationRestUtil.deleteVerification(ssc11321RsBean);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }
        return respResult.getStatus();
    }

}
