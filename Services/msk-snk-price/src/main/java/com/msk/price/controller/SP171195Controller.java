package com.msk.price.controller;


import com.alibaba.fastjson.TypeReference;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.price.bean.SP171195Param;
import com.msk.price.logic.SP171195Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sun_jiaju
 */

@Controller
@RequestMapping("SP171195")
public class SP171195Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171195Controller.class);

    @Autowired
    private SP171195Logic sp171195Logic ;

    /**
     * 初始化
     * @param model
     * @return
     */
    @RequestMapping(value = "init")
    public String init(Model model,SP171195Param sp171195Param){
        logger.info("一览页面初始化");
        // select
        DistrictParam param = new DistrictParam();
        RsRequest<DistrictParam> requestParam = new RsRequest<DistrictParam>();
        requestParam.setParam(param);
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        RestClientUtil rsClientUtil = new RestClientUtil();
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> result = rsClientUtil.post(url, requestParam, new TypeReference<RsResponse<DistrictResult>>(){});
        if(null != result.getResult() && "S".equals(result.getStatus())) {
            model.addAttribute("lgcsList",result.getResult().getLgcsAreaList());
        }
        return "sp/SP171195";
    }

    /**
     * 需求量预测
     * @param model
     * @param param
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, SP171195Param param) {
        super.setCommonParam(param);
        logger.info("设定需求预测系数");
        int result = sp171195Logic.dataResolve(param);
        return this.init(model,param);
    }
}
