package com.msk.br.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.BR121401Param;
import com.msk.br.bean.BR121401RsPageResult;
import com.msk.buyers.utils.BuyerTypeUtil;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrByPoolFileInfo;
import com.msk.district.bean.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 买家标准产品池一览Controller
 *
 * @author zhao_chen1
 */
@Controller
@RequestMapping("BR121401")
public class BR121401Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BR121401Controller.class);


    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(BR121401Param param, Model model) {
        logger.debug("报表页面初始化");
        logger.debug("Code Master 买家类型");
        //读取Code Master DB
        //redisDao.setDatabase(RedisDataBaseDef.CODE_MASTER_DB);
        TreeMap<String, String> treeMap = new TreeMap<>();
        // 买家类型
        Map<String, String> buyersMap = BuyerTypeUtil.getInstance().getBuyerTypeMap(); //CodeMasterManager.findCodeMasterMap(BuyersConst.BuyerType.TYPE) ;
        treeMap.putAll(buyersMap);
        List buyerTypeList = new ArrayList(treeMap.entrySet());
        model.addAttribute("buyerTypeList", buyerTypeList);
        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        //一级分类
        logger.info("调取产品接口开始,获取一级分类");
        RsRequest<PDInfoParam> pdRequest = new RsRequest<PDInfoParam>();
        //传参数
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setType(NumberConst.IntDef.INT_ONE);
        pdRequest.setAuth("MSK00001");
        pdRequest.setLoginId("msk01");
        pdRequest.setSiteCode("1");
        pdRequest.setParam(pdInfoParam);
        String pdUrl = ConfigManager.getMskProductService() + ConfigManager.getPdTypeNameInfoSearchService();
        RsResponse<ProductBeanResult> pdList = RestClientUtil.post(pdUrl, pdRequest, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        if (!CollectionUtils.isEmpty(pdList.getResult().getResult())) {
            List<PDInfoResult> classesList = pdList.getResult().getResult();
            //初始化二级分类
            if (!CollectionUtils.isEmpty(classesList)) {
                logger.info("调取产品接口开始,获取二级分类");
                //传参数
                PDInfoParam machiningParam = new PDInfoParam();
                String classesCode = classesList.get(NumberConst.IntDef.INT_ZERO).getClassesCode();
                machiningParam.setClassesCode(classesCode);
                machiningParam.setType(NumberConst.IntDef.INT_TWO);

                RsRequest<PDInfoParam> machiningRequest = new RsRequest<PDInfoParam>();
                machiningRequest.setAuth("MSK00001");
                machiningRequest.setLoginId("msk01");
                machiningRequest.setSiteCode("1");
                machiningRequest.setParam(machiningParam);


                //引用上面的地址
                RsResponse<ProductBeanResult> machiningList = RestClientUtil.post(pdUrl, machiningRequest, new TypeReference<RsResponse<ProductBeanResult>>() {
                });
                List<PDInfoResult> machining = machiningList.getResult().getResult();
                model.addAttribute("classesList", classesList);
                model.addAttribute("machiningList", machining);
            }
        } else {
            model.addAttribute("classesList", null);
            model.addAttribute("machiningList", null);
        }

        model.addAttribute("logisticsAreaList", logisticsAreaList);
        return "br/BR121401";
    }


    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BrByPoolFileInfo> search(BR121401Param param) {
        logger.info("开始调取文件信息表接口");
        PageResult<BrByPoolFileInfo> result = new PageResult();
        RsRequest<BR121401Param> fileInfoRequest = new RsRequest<BR121401Param>();
        fileInfoRequest.setSiteCode("1");
        fileInfoRequest.setAuth("MSK00001");
        fileInfoRequest.setLoginId("msk01");
        fileInfoRequest.setParam(param);
//        String url = "http://localhost:8084/msk-buyers-report-api/api/br/findBrByPoolFileInfo";
        String url = SystemServerManager.BuyersReportServerManager.getFindBrByPoolFileInfo();
        RsResponse<BR121401RsPageResult> been = RestClientUtil.post(url, fileInfoRequest, new TypeReference<RsResponse<BR121401RsPageResult>>() {});
        result.setData(been.getResult().getBrByPoolFileInfos());
        result.setRecordsTotal(been.getResult().getTotalCount());
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
            RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();

            DistrictParam districtParam = new DistrictParam();
            districtParam.setLgcsAreaCode(lgcsAreaCode);
            districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
            districtParam.setFlag(NumberConst.IntDef.INT_ZERO);

            request.setAuth("MSK00001");
            request.setLoginId(districtParam.getActId());
            request.setSiteCode("1");
            request.setParam(districtParam);

            String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryCityService();
            RsResponse<DistrictResult> city = RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
            });

           /* List<LgcsAreaBean> LgcsAreaBeanList =city.getResult().getLgcsAreaList();
            if (!CollectionUtils.isEmpty(LgcsAreaBeanList)){
                for (int i =0 ;i<LgcsAreaBeanList.size();i++){
                    cityList = LgcsAreaBeanList.get(i).getCityList();
                }
            }*/

            cityList = city.getResult().getCityList();
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
            RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityCode(cityCode);
            districtParam.setIsLoadDistrict(NumberConst.IntDef.INT_ZERO);
            districtParam.setFlag(NumberConst.IntDef.INT_ZERO);
            request.setAuth("MSK00001");
            request.setLoginId(districtParam.getActId());
            request.setSiteCode("1");
            request.setParam(districtParam);
            String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryDistrictService();
            RsResponse<DistrictResult> district = RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
            });
            districtList = district.getResult().getDistrictList();
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
            RsRequest<PDInfoParam> machiningRequest = new RsRequest<PDInfoParam>();
            //传参数
            PDInfoParam machiningParam = new PDInfoParam();
            machiningParam.setCode(classesCode);
            machiningParam.setType(NumberConst.IntDef.INT_TWO);
            machiningRequest.setAuth("MSK00001");
            machiningRequest.setLoginId("msk01");
            machiningRequest.setSiteCode("1");
            machiningRequest.setParam(machiningParam);
            String pdUrl = ConfigManager.getMskProductService() + ConfigManager.getPdTypeNameInfoSearchService();
            RsResponse<ProductBeanResult> response = RestClientUtil.post(pdUrl, machiningRequest, new TypeReference<RsResponse<ProductBeanResult>>() {
            });
            machiningList = response.getResult().getResult();
        }
        return machiningList;
    }
}
