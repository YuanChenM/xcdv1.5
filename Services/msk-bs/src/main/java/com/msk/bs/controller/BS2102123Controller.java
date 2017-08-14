package com.msk.bs.controller;

import com.alibaba.fastjson.JSON;
import com.hoperun.core.consts.NumberConst;
import com.msk.br.bean.IBR121307RsPageResult;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.bs.bean.BS2102123Bean;
import com.msk.bs.bean.BS2102123Param;
import com.msk.bs.bean.BS2102124Bean;
import com.msk.bs.logic.BSCommLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrMPdClasses;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 新增冻品管家组信息
 * Created by ren_qiang on 2016/9/13.
 */
@Controller
@RequestMapping(value = "BS2102123")
public class BS2102123Controller extends BaseController {

    @Autowired
    private BSCommLogic bsCommLogic;

    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model){
        DistrictParam districtParam = new DistrictParam();
        //查询物流区
        RsResponse<DistrictResult> resultRsResponse= this.bsCommLogic.getLogisticsAreaList(districtParam);
        List<LgcsAreaBean> lgcsAreaList = resultRsResponse.getResult().getLgcsAreaList();
        IBR121307RsParam ibr121307RsParam = new IBR121307RsParam();
        ibr121307RsParam.setType("0");
        //查询一级分类
        IBR121307RsPageResult result = CommRestUtil.getMachiningCodeOrClassesCode(ibr121307RsParam);
        List<BrMPdClasses> brMPdClassesesLst = result.getBrMPdClassesList();

        //获取买手一级分类类型
        List<BS2102124Bean> buyerTypeList = CommRestUtil.getBuyerType();

        model.addAttribute("lgcsAreaList",lgcsAreaList);
        model.addAttribute("pdClasseslst",brMPdClassesesLst);
        model.addAttribute("buyerTypeList",buyerTypeList);

        return "bs/BS2102123";
    }

    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody String save(BS2102123Param param){

        if(null != param){
            super.setCommonParam(param);
            Integer cot = CommRestUtil.addHkGroup(param);
            if ( cot == 0){
                return "S";
            }
            else {
                return "F";
            }
        }
        else {
            return "F";
        }

    }

    /**
     * 根据物流区编码查询地区信息
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "findCityLst",
            method = RequestMethod.POST)
    public @ResponseBody
    List<CityBean> findCity(String lgcsAreaCode){
        RsResponse<DistrictResult> resultRsResponse =null;
        DistrictParam districtParam = new DistrictParam();
        List<CityBean> cityList = null;
        if(StringUtils.hasLength(lgcsAreaCode)){
            districtParam.setLgcsAreaCode(lgcsAreaCode);
            districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
            cityList= CommRestUtil.getCityList(districtParam);
        }
        return cityList;
    }

    /**
     * 根据一级分类编码查询二级分类信息
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "findPdMachiningLst",
            method = RequestMethod.POST)
    public @ResponseBody List<BrMPdClasses> findPdMachiningLst(String classesCode){
        IBR121307RsParam ibr121307RsParam = new IBR121307RsParam();
        ibr121307RsParam.setType("1");
        ibr121307RsParam.setClassesCode(classesCode);
        //查询二级级分类
        IBR121307RsPageResult result = CommRestUtil.getMachiningCodeOrClassesCode(ibr121307RsParam);
        List<BrMPdClasses> brMPdClassesesLst = result.getBrMPdClassesList();
        return brMPdClassesesLst;
    }

}
