package com.msk.br.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.BR121404RsPageResult;
import com.msk.br.bean.BR121404RsParam;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.StatusConst;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrSingleByFileInfo;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 单一买家标准产品池列表Controller
 *
 * @author yaun_zhifei
 */
@Controller
@RequestMapping("BR121404")
public class BR121404Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(BR121404Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(String buyerId, String buyerName, String buyerCode, String marketName, Model model) {
        logger.debug("单一买家标准产品池列表初始化");
        model.addAttribute("buyerId", buyerId);
        model.addAttribute("buyerName", buyerName);
        model.addAttribute("buyerCode", buyerCode);
        model.addAttribute("marketName", marketName);
        return "br/BR121404";
    }

    /**
     * 单一买家标准产品池列表
     *
     * @param
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BrSingleByFileInfo> search(@PathVariable("buyerId") String buyerId, BR121404RsParam br121404RsParam) {
        logger.info("单一买家标准产品池列表");
        PageResult<BrSingleByFileInfo> result = new PageResult<>();
        RsRequest<BR121404RsParam> request = new RsRequest<BR121404RsParam>();
        //系统当前月
        String nowYearMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, DateTimeUtil.getCustomerDate());
        String year = nowYearMonth.substring(0, 4);
        String month = nowYearMonth.substring(4, 6);

        //订单详细全部收货状态7
        String detailStatus = StringUtil.toString(StatusConst.OrderDetailStatusDef.ALL_RECEIPT);
        br121404RsParam.setBuyerId(StringUtil.toString(buyerId));
        br121404RsParam.setFileName("二级分类产品" + year + "年" + month + "月买家产品池");
        br121404RsParam.setMonth(month);
        br121404RsParam.setDetailStatus(detailStatus);
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(br121404RsParam);
        //String url = "http://localhost:8280/api/br/findBrSingleByFileInfoList";
        String url = SystemServerManager.BuyersReportServerManager.getFindBrSingleByFileInfoList();
        RsResponse<BR121404RsPageResult> list = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121404RsPageResult>>() {
        });
        result.setData(list.getResult().getBrSingleByFileInfoList());
        result.setRecordsTotal(list.getResult().getTotalCount());
        return result;
    }

    /**
     * 生成文件名并生成Excel
     *
     * @param bean
     */
    @RequestMapping(value = "createExcel", method = RequestMethod.POST)
    @ResponseBody
    public void dataResolve(BrSingleByFileInfo bean) {
        RsRequest<BrSingleByFileInfo> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        //String url = "http://localhost:8280/api/br/findDataResolve";
        String url = SystemServerManager.BuyersReportServerManager.getFindDataResolve();
        RestClientUtil.post(url, bean, new TypeReference<RsResponse<BrSingleByFileInfo>>() {
        });
    }

}
