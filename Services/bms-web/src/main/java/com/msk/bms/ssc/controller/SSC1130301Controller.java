package com.msk.bms.ssc.controller;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * 选择企业信息
 * @author wu_honglei
 */
@Controller
@RequestMapping("SSC1130301")
public class SSC1130301Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC1130301Controller.class);

    /**
     * 初始化页面
     *
     * @param model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model,SSC1130301RsBean ssc1130301RsBean) {
        logger.debug("合同信息列表页面初始化");


        model.addAttribute("ssc1130301RsBean",ssc1130301RsBean);
        return "ssc/SSC1130301";
    }

    /**
     * 分页查询
     *
     * @param ssc11303RsParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11303RsBean> search(SSC11303RsParam ssc11303RsParam) {
        logger.debug("查询合同资料");
        Map<String, Object> filterMap = ssc11303RsParam.getFilterMap();

        String contractName = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("contractName")).trim(), DbUtils.LikeMode.PARTIAL);
        String bidProjectNo = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("bidProjectNo")).trim(), DbUtils.LikeMode.PARTIAL);
        String contractCode = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("contractCode")).trim(), DbUtils.LikeMode.PARTIAL);
        String purchaserName = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("purchaserName")).trim(), DbUtils.LikeMode.PARTIAL);
        String supplierName = DbUtils.buildLikeCondition(StringUtil.toString(filterMap.get("supplierName")).trim(), DbUtils.LikeMode.PARTIAL);
        ssc11303RsParam.setContractName(contractName);
        ssc11303RsParam.setBidProjectNo(bidProjectNo);
        ssc11303RsParam.setContractCode(contractCode);
        ssc11303RsParam.setPurchaserName(purchaserName);
        ssc11303RsParam.setSupplierName(supplierName);
        ssc11303RsParam.setContractActDate(StringUtil.toString(filterMap.get("contractActDateStr")).trim());
        String contractStatusStr = StringUtil.toString(ssc11303RsParam.getContractStatusStr().trim());
        if (!StringUtil.isNullOrEmpty(contractStatusStr)) {
            String[] contractStatusArr = contractStatusStr.split(",");
            ssc11303RsParam.setContractStatusArr(contractStatusArr);
        }
        String contractStatus = StringUtil.toString(filterMap.get("contractStatus"));
        if (!StringUtil.isNullOrEmpty(contractStatus)) {
            String[] contractStatusArr = contractStatus.split(",");
            ssc11303RsParam.setContractStatusArr(contractStatusArr);
        }
        String bidRelationType = StringUtil.toString(filterMap.get("bidRelationType"));
        if (!StringUtil.isNullOrEmpty(bidRelationType)) {
            String[] bidRelationTypeArr = bidRelationType.split(",");
            ssc11303RsParam.setBidRelationTypeArr(bidRelationTypeArr);
        }

        RsResponse<PageResult<SSC11303RsBean>> response = ISSCContractRestUtil.findContractList(ssc11303RsParam);
        return response.getResult();
    }


}
