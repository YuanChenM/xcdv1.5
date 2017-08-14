package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;

import com.msk.buyers.bean.IBY121202RsBean;
import com.msk.buyers.bean.IBY121202RsParam;
import com.msk.buyers.bean.IBY121207RsParam;
import com.msk.buyers.logic.BY12130401Logic;
import com.msk.buyers.logic.IBY121202Logic;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrOBuyerPdCla;
import com.msk.core.entity.ByBuyerEmployee;
import com.msk.core.entity.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 买家雇员信息编辑画面
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("BY12130401")
public class BY12130401Controller extends BaseUploadController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY12130401Controller.class);

    @Autowired
    private BY12130401Logic by12130401Logic;
    @Autowired
    private IBY121202Logic iby121202Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{editType}",
            method = RequestMethod.POST)
    public String init(@PathVariable("editType") String editType, ByBuyerEmployee byBuyerEmployee, Model model) {
        logger.debug("买家雇员信息编IBY121207RsParam辑画面");
        List<IBY121207RsParam> employeeList = new ArrayList<>();
        if (!("BY121307Add".equals(editType)) && !("BY121304Add".equals(editType))) {
            employeeList = by12130401Logic.buyerEmployeeFind(byBuyerEmployee);
            if (!CollectionUtils.isEmpty(employeeList)) {
                model.addAttribute("buyerEmployee", employeeList.get(0));
            }
        }
        //员工类型
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap(BuyersConst.EmployeeType.TYPE);
        List<CommConstant> employeeTypeList = new ArrayList(codeMasterMap.entrySet());
        model.addAttribute("employeeTypeList", employeeTypeList);
        model.addAttribute("id", byBuyerEmployee.getId());
        model.addAttribute("buyerId", byBuyerEmployee.getBuyerId());
        model.addAttribute("editType", editType);
        return "buyers/BY12130401";
    }

    /**
     * 买家雇员信息编辑保存
     *
     * @param byBuyerEmployee
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveChange(IBY121207RsParam byBuyerEmployee) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        super.setCommonParam(byBuyerEmployee);
        byBuyerEmployee.setUpdTime(currentDate);
        byBuyerEmployee.setCrtTime(currentDate);
        byBuyerEmployee.setActTime(currentDate);
        byBuyerEmployee.setBusCardId(byBuyerEmployee.getBusCardPicPath());
        int updateCount = by12130401Logic.buyerEmployeeModify(byBuyerEmployee);
        if (updateCount > NumberConst.IntDef.INT_ZERO) {
                //更新买家池买家信息更新买家经营产品信息
                IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
                IBY121202RsParam param = new IBY121202RsParam();
                param.setBuyerId(byBuyerEmployee.getBuyerId());
                BrOBuyerInfo brOBuyerInfo = new BrOBuyerInfo();
                List<BrOBuyerPdCla> brOBuyerPdClaList = new ArrayList<>();
                //查询买家系统买家基本信息
                brOBuyerInfo = iby121202Logic.findBaseBuyerInfo(param);
                //查询买家经营产品信息
                brOBuyerPdClaList = iby121202Logic.findBuyerPdCla(param);
                iby121202RsBean.setIsModify("true");
                iby121202RsBean.setBrOBuyerInfo(brOBuyerInfo);
                iby121202RsBean.setBrOBuyerPdClaList(brOBuyerPdClaList);
                RsRequest<IBY121202RsBean> request = new RsRequest<>();
                request.setSiteCode("1");
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setParam(iby121202RsBean);
//                String url = "http://localhost:8180/api/br/buyerReportInfo/_update";
                String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
                RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });
            return SystemConst.RsStatus.SUCCESS;
        } else if (updateCount == NumberConst.IntDef.INT_N_ONE) {
            return "I";
        } else if (updateCount == NumberConst.IntDef.INT_N_TWO) {
            return "T";
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }
}
