package com.msk.buyers.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.logic.BY121325Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("BY121325")
public class BY121325Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BY121324Controller.class);

    @Autowired
    private BY121325Logic by121325Logic;

    /**
     * 初始化页面
     *
     * @param buyerId
     * @param model
     * @return
     */
    @RequestMapping(value = "init/{buyerId}", method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        model.addAttribute("buyerId", buyerId);
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);

        return "buyers/BY121325";
    }


    /**
     * 通过物流区查询城市
     *
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "lgcsAreaChange/{lgcsAreaCode}", method = RequestMethod.POST)
    public
    @ResponseBody
    List<CityBean> findCityList(@PathVariable("lgcsAreaCode") String lgcsAreaCode) {
        List<CityBean> cityList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(lgcsAreaCode)) {
            logger.info("开始调取城市列表接口");
            DistrictParam districtParam = new DistrictParam();
            districtParam.setLgcsAreaCode(lgcsAreaCode);
            districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
            districtParam.setFlag(NumberConst.IntDef.INT_ZERO);
            RsResponse<DistrictResult> citys = RestCommUtil.getCityList(districtParam);
            cityList = citys.getResult().getCityList();
            logger.info("城市列表接口调取结束");
        }
        return cityList;
    }

}
