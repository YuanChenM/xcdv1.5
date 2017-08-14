package com.msk.bs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.IBR121307RsPageResult;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.bs.bean.*;
import com.msk.bs.logic.BS2102104Logic;
import com.msk.bs.logic.BSCommLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.BrMPdClasses;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdMachining;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 冻品管家组Controller
 * author ren_qiang  2016/8/1.
 */

@Controller
@RequestMapping(value = "BS2102104")
public class BS2102104Controller extends BaseController{

    @Autowired
    private BSCommLogic bsCommLogic;

    @Autowired
    private BS2102104Logic bs2102104Logic;
    @Autowired
    private BaseRedisDao redisDao;
    /**
     * 实例化页面
     * @param model
     * @return
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    private String init(Model model) {
        DistrictParam districtParam = new DistrictParam();
        //查询物流区
        RsResponse<DistrictResult> resultRsResponse= this.bsCommLogic.getLogisticsAreaList(districtParam);
        List<LgcsAreaBean> lgcsAreaList = resultRsResponse.getResult().getLgcsAreaList();
        IBR121307RsParam ibr121307RsParam = new IBR121307RsParam();
        ibr121307RsParam.setType("0");
        //查询一级分类
        IBR121307RsPageResult result = CommRestUtil.getMachiningCodeOrClassesCode(ibr121307RsParam);
        List<BrMPdClasses> brMPdClassesesLst = result.getBrMPdClassesList();
        //获取买家一级分类类型
        List<BS2102124Bean> buyerTypeList = CommRestUtil.getBuyerType();
        model.addAttribute("lgcsAreaList",lgcsAreaList);
        model.addAttribute("pdClasseslst",brMPdClassesesLst);
        model.addAttribute("buyerTypeList",buyerTypeList);

        return "/bs/BS2102104";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BR121305Bean> search(BR121305Param param) {

        return  this.bs2102104Logic.findIBS2102104List(param);
    }

    /**
     * 根据物流区编码查询地区信息
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "findCityLst",
            method = RequestMethod.POST)
    public @ResponseBody List<CityBean> findCity(String lgcsAreaCode){
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

    /**
     * 编辑后保存
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody String save( String jsonStr){

        Map<String,BS2102104Bean> map = null;
        if(StringUtils.hasLength(jsonStr)){
            map = JSON.parseObject(jsonStr, new TypeReference<Map<String,BS2102104Bean>>(){
            });
            Collection<BS2102104Bean> beans = map.values();
            List<BR121305Param> paramList = new ArrayList<BR121305Param>();
            for(BS2102104Bean bean:beans){
                BR121305Param param = new BR121305Param();
                super.setCommonParam(param);
                Date date = DateTimeUtil.getCustomerDate();
                param.setCrtTime(date);
                param.setUpdTime(date);
                param.setActTime(date);
                param.setHkGroupName(bean.getHkGroupName());
                param.setHkGroupId(bean.getHkGroupId());
                paramList.add(param);
            }
            int cot =bs2102104Logic.editePdGroupName(paramList);
            BS2102104Result result = new BS2102104Result();
            if(cot==1){
                result.setFlag(true);
                return "S";
            }else{
                return "F";
            }

        }
        return "F";
    }
}
