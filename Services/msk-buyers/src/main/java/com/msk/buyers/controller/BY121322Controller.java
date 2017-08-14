package com.msk.buyers.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.buyers.bean.BY121322Bean;
import com.msk.buyers.bean.BY121322Param;
import com.msk.buyers.bean.BY121322RsParam;
import com.msk.buyers.bean.BY121322RsResult;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.district.bean.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by guan_zhongheng on 2016/8/9.
 */
@Controller
@RequestMapping("BY121322")
public class BY121322Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(BY121322Controller.class);

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
        // 查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult()
                .getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setType(NumberConst.IntDef.INT_ONE);
        List<PDInfoResult> classesList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
        if (!CollectionUtils.isEmpty(classesList)) {
            // 传参数
            PDInfoParam machiningParam = new PDInfoParam();
            String classesCode = classesList.get(NumberConst.IntDef.INT_ZERO).getClassesCode();
            machiningParam.setClassesCode(classesCode);
            machiningParam.setType(NumberConst.IntDef.INT_TWO);
            List<PDInfoResult> machining = RestCommUtil.getPdClassesList(machiningParam).getResult().getResult();
            model.addAttribute("classesList", classesList);
            model.addAttribute("machiningList", machining);
        } else {
            model.addAttribute("classesList", null);
            model.addAttribute("machiningList", null);
        }
        return "buyers/BY121322";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121322Bean> search(BY121322Param param) {
        param.setSearchType("0");
        if(param.getStartTime() != ""){
            param.setStartTime(param.getStartTime() + " 00:00:00");
        }
        if(param.getEndTime() != ""){
            param.setEndTime(param.getEndTime() + " 23:59:59");
        }
        PageResult<BY121322Bean> result = new PageResult<BY121322Bean>();
        BY121322RsParam rsParam = new BY121322RsParam();
        BeanUtils.copyProperties(param, rsParam);
        rsParam.setPageCount(param.getPageSize());
        rsParam.setPageNo((param.getStartPos() / param.getPageSize()) + 1);
        RsResponse<BY121322RsResult> by121322RsResult = RestCommUtil.getSaleMarketControlInfo(rsParam);
        result.setRecordsTotal(by121322RsResult.getResult().getTotalCount());
        if (by121322RsResult.getResult().getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            result.setData(by121322RsResult.getResult().getSlHouseSaleList());
        } else {
            result.setData(new ArrayList<BY121322Bean>());
        }
        return result;
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

    /**
     * 城市变更，获取区县下拉框数据
     *
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "cityChange/{cityCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<DistrictBean> findDistrictList(@PathVariable("cityCode") String cityCode) {
        List<DistrictBean> districtList = null;
        if (!StringUtil.isNullOrEmpty(cityCode)) {
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityCode(cityCode);
            districtParam.setIsLoadDistrict(NumberConst.IntDef.INT_ZERO);
            districtParam.setFlag(NumberConst.IntDef.INT_ZERO);
            RsResponse<DistrictResult> districts = RestCommUtil.getDistrictList(districtParam);
            districtList = districts.getResult().getDistrictList();
        }
        return districtList;
    }

    /**
     * 一级分类下拉框变更，获取二级分类下拉框
     *
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "classesChange/{classesCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PDInfoResult> findMachiningList(@PathVariable("classesCode") String classesCode) {
        List<PDInfoResult> machiningList = null;
        if (!StringUtil.isEmpty(classesCode)) {
            // 传参数
            PDInfoParam machiningParam = new PDInfoParam();
            machiningParam.setCode(classesCode);
            machiningParam.setType(NumberConst.IntDef.INT_TWO);
            RsResponse<ProductBeanResult> pdClassesList = RestCommUtil.getPdClassesList(machiningParam);
            machiningList = pdClassesList.getResult().getResult();
        }
        return machiningList;
    }
}
