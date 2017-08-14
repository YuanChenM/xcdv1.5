package com.msk.buyers.controller;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.BY121422Param;
import com.msk.buyers.bean.BY121322Bean;
import com.msk.buyers.bean.BY121322Param;
import com.msk.buyers.bean.BY121322RsParam;
import com.msk.buyers.bean.BY121322RsResult;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.FileUtils;
import com.msk.core.entity.BrByPoolFileInfo;
import com.msk.district.bean.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.sso.client.excption.SystemException;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by jiang_zhenping on 2016/8/23.
 */

@Controller
@RequestMapping("BY121326")
public class BY121326Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(BY121326Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        model.addAttribute("buyerId", buyerId);
        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setType(NumberConst.IntDef.INT_ONE);
        List<PDInfoResult> classesList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
        if (!CollectionUtils.isEmpty(classesList)) {
            //传参数
            String classesCode = classesList.get(NumberConst.IntDef.INT_ZERO).getClassesCode();
            model.addAttribute("classesList", classesList);
        } else {
            model.addAttribute("classesList", null);
        }
        return "buyers/BY121326";
    }

    /**
     * 物流区变更，获取城市下拉框数据
     *
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "lgcsAreaChange/{lgcsAreaCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<CityBean> findCityList(@PathVariable("lgcsAreaCode") String lgcsAreaCode) {
        List<CityBean> cityList = null;
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