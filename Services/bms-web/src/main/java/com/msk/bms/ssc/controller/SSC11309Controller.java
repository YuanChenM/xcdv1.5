package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.SSC11301RsBean;
import com.msk.ssc.bean.SSC11301RsParam;
import com.msk.ssc.bean.SSC11309RsBean;
import com.msk.ssc.bean.SSC11309RsParam;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 生产商入库单管理Controller
 *
 * @author liu_yan2
 */
@Controller
@RequestMapping("SSC11309")
public class SSC11309Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11309Controller.class);

    /**
     * 初始化页面
     * type为直接进入列表 2从合同一览页面进
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{type}", method = RequestMethod.POST)
    public String init(SscContractBasic sscContractBasic, Model model,@PathVariable(value = "type")String type){
        logger.debug("生产商入库单管理页面初始化");
        if ("2".equals(type)) {
            model.addAttribute("sscContractBasic", sscContractBasic);
        }
        model.addAttribute("type", type);
        return "ssc/SSC11309";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11309RsBean> search(SSC11309RsParam ssc11309RsParam, @RequestParam(value = "contractId", required = false) String contractId){
        PageResult<SSC11309RsBean> result = new PageResult();
        RsRequest<SSC11309RsParam> request = new RsRequest<SSC11309RsParam>();

        if(!StringUtil.isNullOrEmpty(contractId)) {
            ssc11309RsParam.setContractId(contractId);
        }
        String intoCode = StringUtil.toString(ssc11309RsParam.getFilterMap().get("intoCode"));
        String contractName = StringUtil.toString(ssc11309RsParam.getFilterMap().get("contractName"));
        String supplierName = StringUtil.toString(ssc11309RsParam.getFilterMap().get("supplierName"));
        String purchaserName = StringUtil.toString(ssc11309RsParam.getFilterMap().get("purchaserName"));
        String deliveryCode = StringUtil.toString(ssc11309RsParam.getFilterMap().get("deliveryCode"));
        String intoType = StringUtil.toString(ssc11309RsParam.getFilterMap().get("intoType"));
        String contractCode = StringUtil.toString(ssc11309RsParam.getFilterMap().get("contractCode"));
        String[] intoTypes = null;
        if(!StringUtil.isNullOrEmpty(intoType)) {
            intoTypes = intoType.split(",");
            ssc11309RsParam.setIntoTypes(intoTypes);
        }
        ssc11309RsParam.setContractCode(contractCode);
        ssc11309RsParam.setIntoCode(intoCode);
        ssc11309RsParam.setContractName(contractName);
        ssc11309RsParam.setSupplierName(supplierName);
        ssc11309RsParam.setPurchaserName(purchaserName);
        ssc11309RsParam.setDeliveryCode(deliveryCode);
        ssc11309RsParam.setIntoCode(intoCode);


        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(ssc11309RsParam);
//        String url = "http://localhost:8080/msk-ssc-api/api/ssc/findSscIntoBasic";
        String url = SystemServerManager.SellerSupplyChainManage.getFindSscIntoBasic();
        RsResponse<PageResult<SSC11309RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11309RsBean>>>() {});
        return response.getResult();
    }

}
