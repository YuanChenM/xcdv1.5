package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BY1213141Bean;
import com.msk.buyers.bean.BY121314Param;
import com.msk.buyers.bean.BY121314RsPageResult;
import com.msk.buyers.bean.IBY1213141RsBean;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerDelivery;
import com.msk.district.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhou_yajun on 2016/7/15.
 */
@Controller
@RequestMapping("BY1213141")
public class BY1213141Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY1213141Controller.class);

    /**
     * 买家收货信息新增编辑画面
     * @param id
     * @return
     */
    @RequestMapping(value = "init/{buyerId}/{id}")
    public String init(@PathVariable("buyerId") String buyerId,@PathVariable("id") String id,Model model){

        model.addAttribute("id",id);
        model.addAttribute("buyerId",buyerId);
        //根据id获取买家收货信息
        RsRequest<BY121314Param> paramRsRequest = new RsRequest<>();
        BY121314Param param = new BY121314Param();
        param.setId(id);
        param.setBuyerId(buyerId);
        paramRsRequest.setAuth("MSK00001");
        paramRsRequest.setLoginId("msk01");
        paramRsRequest.setSiteCode("1");
        paramRsRequest.setParam(param);
        //String url="http://localhost:8080/msk-buyers/api/by/deliveryAddr/query";
        String url= SystemServerManager.BuyersServerManage.getQueryDeliveryAddr();
        RsResponse<BY121314RsPageResult> response= RestClientUtil.post(url,paramRsRequest,new TypeReference<RsResponse<BY121314RsPageResult>>(){
        });
        List<ByBuyerDelivery> buyerDeliveryList=response.getResult().getBrOBuyerInfoList();
        ByBuyerDelivery buyerDelivery=null;
        if(!CollectionUtils.isEmpty(buyerDeliveryList)){
            buyerDelivery=buyerDeliveryList.get(NumberConst.IntDef.INT_ZERO);
            model.addAttribute("buyerDelivery",buyerDelivery);
        }

        //从物流区接口中获取省基础数据
        RsRequest<BaseParam> request = new RsRequest<>();
        DistrictParam districtParam = new DistrictParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        request.setParam(districtParam);
        String provinceUrl = SystemServerManager.DistrictServerManage.getDistrictQueryProvince();
        RsResponse<DistrictResult> provinceResultList = RestClientUtil.post(provinceUrl, request, new TypeReference<RsResponse<DistrictResult>>() {});
        List<ProvinceBean> provinceList = provinceResultList.getResult().getProvinceList();
        model.addAttribute("provinceList",provinceList);
        if(null != buyerDelivery){
            //如果是编辑模式,获取省下的所有城市
            districtParam.setProvinceCode(buyerDelivery.getProvinceCode());
            districtParam.setIsLoadCity(0);
            List<CityBean> cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
            model.addAttribute("cityList",cityList);
            //如果是编辑模式,获取城市下的所有区县
            districtParam.setCityCode(buyerDelivery.getCityCode());
            List<DistrictBean> districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
            model.addAttribute("districtList",districtList);
        }

        //上海订单配送区域ShOrderDeliveryArea
        Map<String, String> shOrderDeliveryAreas = CodeMasterManager.findCodeMasterMap(BuyersConstant.ShOrderDeliveryArea.TYPE);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(shOrderDeliveryAreas);
        model.addAttribute("shOrderDeliveryAreas", shOrderDeliveryAreas);
        return "buyers/BY1213141";
    }

    /**
     * 保存买家配送信息
     * @param by1213141Bean
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody void save(BY1213141Bean by1213141Bean){
        setDefaultAddr(by1213141Bean);
        //更新买家配送信息
        RsRequest<BY1213141Bean> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(by1213141Bean);
        //String url="http://localhost:8080/msk-buyers/api/by/deliveryAddr/update";
        String url = SystemServerManager.BuyersServerManage.getUpdateDeliveryAddr();
        RsResponse<ByBuyerDelivery> response = RestClientUtil.post(url,request,new TypeReference<RsResponse<ByBuyerDelivery>>(){
        });
    }

    /**
     * 删除配送信息
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}")
    public @ResponseBody void delete(@PathVariable("id") String id){
        //买家配送地址信息删除
        RsRequest<IBY1213141RsBean> request=new RsRequest<>();
        IBY1213141RsBean param=new IBY1213141RsBean();
        param.setId(StringUtil.toLong(id));
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url="http://localhost:8080/msk-buyers/api/by/deliveryAddr/delete";
        String url=SystemServerManager.BuyersServerManage.getDeleteDeliveryAddr();
        RsResponse<Integer> response = RestClientUtil.post(url,request,new TypeReference<RsResponse<Integer>>(){
        });
    }

    /**
     * 设置默认地址
     * @param by1213141Bean
     */
    public void setDefaultAddr(BY1213141Bean by1213141Bean){
        if(null != by1213141Bean.getDefaultFlg()){
            if("1".equals(by1213141Bean.getDefaultFlg()[0])){
                by1213141Bean.setIsDefault("1");
            }
        }else{
            by1213141Bean.setIsDefault("0");
        }
    }

    /**
     * 物流区变更，获取城市下拉框数据
     * @param provinceCode
     * @return
     */
    @RequestMapping(value = "provinceChange/{provinceCode}",
            method = RequestMethod.POST)
    public @ResponseBody List<CityBean> findCityList(@PathVariable("provinceCode") String provinceCode){
        List<CityBean> cityList = null;
        if(!StringUtil.isNullOrEmpty(provinceCode)){
            DistrictParam districtParam = new DistrictParam();
            districtParam.setProvinceCode(provinceCode);
            districtParam.setIsLoadCity(0);
            cityList =  RestCommUtil.getCityList(districtParam).getResult().getCityList();
        }
        return cityList;
    }
    /**
     * 城市变更，获取区县下拉框数据
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "cityChange/{cityCode}",
            method = RequestMethod.POST)
    public @ResponseBody List<DistrictBean> findDistrictList(@PathVariable("cityCode") String cityCode){
        List<DistrictBean> districtList = null;
        if(!StringUtil.isNullOrEmpty(cityCode)){
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityCode(cityCode);
            districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
        }
        return districtList;
    }
}
