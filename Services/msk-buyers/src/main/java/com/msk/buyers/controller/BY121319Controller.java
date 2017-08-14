package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BY121319Bean;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 *
 */
@Controller
@RequestMapping("BY121319")
public class BY121319Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121319Controller.class);

    /**
     * 买家营销工具管控表初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}", method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家营销工具管控表");
        //获取所有的营销工具类型
        Map<String, String> marketingTool = CodeMasterManager.findCodeMasterMap("MarketingTool");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(marketingTool);
        /*List marketingToolList = new ArrayList(treeMap.entrySet());*/

        RsRequest<ByBuyerBasicInfo> request = new RsRequest<ByBuyerBasicInfo>();
        ByBuyerBasicInfo param = new ByBuyerBasicInfo();
        param.setBuyerId(buyerId);
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//      String url = "http://localhost:8080/msk-by/api/by/findToolToBuyerId";
        String url = SystemServerManager.BuyersServerManage.getFindToolToBuyerId();
        RsResponse<ByBuyerBasicInfo> basicBean = RestClientUtil.post(url, request, new TypeReference<RsResponse<ByBuyerBasicInfo>>() {
        });

        ByBuyerBasicInfo bean = basicBean.getResult();
        List<BY121319Bean> toolBeanList = new ArrayList<>();
        if (bean != null) {
            String tool = bean.getMarketingTools();
            List<String> toolList = new ArrayList();
            if (!StringUtil.isEmpty(tool)) {
                String tools[] = tool.split(",");
                toolList = Arrays.asList(tools);
            }
            for (String key : treeMap.keySet()) {
                BY121319Bean by121319Bean = new BY121319Bean();
                by121319Bean.setMarketingTools(key);
                by121319Bean.setMarketingToolsName(treeMap.get(key));
                by121319Bean.setIsChecked("0");

                for (int j = 0; j < toolList.size(); j++) {
                    if (key.equals(toolList.get(j))) {
                        by121319Bean.setIsChecked("1");
                        break;
                    }
                }
                toolBeanList.add(by121319Bean);
            }
            model.addAttribute("toolBeanList", toolBeanList);
            String startTime = bean.getTelMarketingStartTime();
            String endTime = bean.getTelMarketingEndTime();


            if (startTime != null) {
                model.addAttribute("telMarketingStartTime", bean.getTelMarketingStartTime());
            } else {
                model.addAttribute("telMarketingStartTime", null);
            }
            if (endTime != null) {
                model.addAttribute("telMarketingEndTime", bean.getTelMarketingEndTime());
            } else {
                model.addAttribute("telMarketingEndTime", null);
            }

        } else {

            for (String key : treeMap.keySet()) {
                BY121319Bean by121319Bean = new BY121319Bean();
                by121319Bean.setMarketingTools(key);
                by121319Bean.setMarketingToolsName(treeMap.get(key));
                by121319Bean.setIsChecked("0");

                toolBeanList.add(by121319Bean);
            }

            model.addAttribute("toolBeanList", toolBeanList);
            model.addAttribute("telMarketingStartTime", null);
            model.addAttribute("telMarketingEndTime", null);
        }
        RsRequest<ByBuyerBasicInfo> req = new RsRequest<ByBuyerBasicInfo>();
        ByBuyerBasicInfo time = new ByBuyerBasicInfo();
        req.setSiteCode("1");
        req.setAuth("MSK00001");
        req.setLoginId("msk01");
        req.setParam(time);
//        String timeUrl = "http://localhost:8080/msk-by/api/by/common/allTimesList";
        String timeUrl = SystemServerManager.BuyersServerManage.getGetBuyersAllTimesList();
        RsResponse<String[]> timeBean = RestClientUtil.post(timeUrl, req, new TypeReference<RsResponse<String[]>>() {
        });
        List<String> timeList;
        if (null != timeBean.getResult() && timeBean.getResult().length > 0) {
            timeList = Arrays.asList(timeBean.getResult());
            model.addAttribute("timeList", timeList);
        }
        model.addAttribute("buyerId", buyerId);
        return "buyers/BY121319";
    }


    /**
     * 营销工具保存
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(ByBuyerBasicInfo param) {
        logger.info("买家营销工具管控表编辑保存");
        String buyerId = param.getBuyerId();
        RsRequest<ByBuyerBasicInfo> request = new RsRequest<ByBuyerBasicInfo>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//      String url = "http://localhost:8080/msk-by/api/by/saveTOOLToDataBase";
        String url = SystemServerManager.BuyersServerManage.getSaveTOOLToDataBase();
        RsResponse<Integer> basicBean = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        int updateLine = basicBean.getResult();
        String returnFlg = "保存失败";
        if (updateLine > 0) {
            returnFlg = "保存成功";
        }
        return returnFlg;
    }


}
