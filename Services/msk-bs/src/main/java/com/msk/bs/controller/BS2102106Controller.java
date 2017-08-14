package com.msk.bs.controller;

import com.alibaba.fastjson.JSON;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.IBR121307RsPageResult;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.bs.bean.BS210110501Param;
import com.msk.bs.bean.BS2102105Param;
import com.msk.bs.bean.BS2102105Result;
import com.msk.bs.bean.BS2102124Bean;
import com.msk.bs.logic.BS2102105Logic;
import com.msk.bs.logic.BSHouseLeverLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.BrMPdClasses;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 添加冻品管家
 * Created by yang_chunyan on 2016/8/2.
 */
@Controller
@RequestMapping(value="BS2102106")
public class BS2102106Controller extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BS2102105Controller.class);

    @Autowired
    private BS2102105Logic bs2102105Logic;

    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;

    /**
     * 初始化新增冻品管家页面
     * @param pageParam
     * @param model
     * @return
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(BS2102105Param pageParam, Model model) {
        String lgcsCode = pageParam.getLgcsAreaCode();
        String districtCode = pageParam.getCityCode();
        String lgcsName = pageParam.getLgcsAreaName();
        String districtName = pageParam.getCityName();
        String buyerType = pageParam.getBuyerType();
        String buyerTypeName = pageParam.getBuyerTypeName();
        String classes = pageParam.getClassesCode();
        String machining = pageParam.getMachiningCode();
        String classesName = pageParam.getClassesName();
        String machiningName = pageParam.getMachiningName();
        String groupName = pageParam.getHkGroupName();
        String groupId = pageParam.getHkGroupId();
        model.addAttribute("lgcsCode",lgcsCode);
        model.addAttribute("cityCode",districtCode);
        model.addAttribute("lgcsName",lgcsName);
        model.addAttribute("cityName",districtName);
        model.addAttribute("buyerType",buyerType);
        model.addAttribute("buyerTypeName",buyerTypeName);
        model.addAttribute("classes",classes);
        model.addAttribute("machining",machining);
        model.addAttribute("classesName",classesName);
        model.addAttribute("machiningName",machiningName);
        model.addAttribute("groupName", groupName);
        model.addAttribute("groupId",groupId);
        SlHouseType houseType = new SlHouseType();
        houseType.setTypeLever("0");
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlHouseType(houseType);
        model.addAttribute("houseTypeList",houseTypeList);

        DistrictParam districtParam = new DistrictParam();
        districtParam.setLgcsAreaCode(lgcsCode);
        districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
        List<CityBean> cityList = CommRestUtil.getCityList(districtParam);
        model.addAttribute("cityList",cityList);

        return "bs/BS2102106";
    }

    /**
     * 导出冻品管家页面
     * @param model
     * @return
     */
    @RequestMapping(value = "initExport",
            method = RequestMethod.POST)
    public String initExport(Model model) {
        List<LgcsAreaBean> lgcsAreaBeanList = CommRestUtil.getLogisticsAreaList(new DistrictParam());
        model.addAttribute("lgcsAreaBeanList",lgcsAreaBeanList);

        IBR121307RsParam ibr121307RsParam = new IBR121307RsParam();
        ibr121307RsParam.setType("0");
        //查询一级分类
       IBR121307RsPageResult result = CommRestUtil.getMachiningCodeOrClassesCode(ibr121307RsParam);
        List<BrMPdClasses> pdClasseslst =result.getBrMPdClassesList();
        model.addAttribute("pdClasseslst",pdClasseslst);

        //获取买手一级分类类型
        List<BS2102124Bean> buyerTypeList = CommRestUtil.getBuyerType();
        model.addAttribute("buyerTypeList",buyerTypeList);

        return "bs/BS2102108";
    }

    /**
     * 根据物流区编码查询地区
     * @param param
     * @return
     */
    @RequestMapping(value = "findDistrictByArea",method = RequestMethod.POST)
    public @ResponseBody
    List<CityBean> findDistrictByArea(BS2102105Param param) {
        DistrictParam districtParam = new DistrictParam();
        districtParam.setLgcsAreaCode(param.getLgcsAreaCode());
        districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
        List<CityBean> cityList = CommRestUtil.getCityList(districtParam);
        return cityList;
    }

    /**
     * 查询所有管家
     * @param basePageParam
     * @return 结果 数据
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<BS2102105Result> search(BS2102105Param basePageParam) {
        return bs2102105Logic.findPageHouseResult(basePageParam);
    }

    /**
     * 向管家组批量添加管家
     * @param param
     * @return 结果 数据
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody void save(@RequestParam(value = "param",
            required = false)String param,@RequestParam(value = "groupId",
            required = false)String groupId) {
        BS2102105Param bsParam = new BS2102105Param();
        List<BS210110501Param> list = JSON.parseArray(param, BS210110501Param.class);
        /**Modify: Bug #2623 9.12冻品管家组总控表下载时，现9.12日添加上海物流区冻品管家组成员，
         * 冻品管家组报表下载时，选择上海物流区，所属期9.12到9.14，点击下载后报表内容为空，
         * 应显示9.12日添加的冻品管家组成员 2016/09/13   BY  朱凯  Start */
        Date date = new Date();
        for (BS210110501Param bs210110501Param : list){
            bs210110501Param.setJoinTime(date);
            bs210110501Param.setCrtTime(date);
            bs210110501Param.setActTime(date);
            bs210110501Param.setUpdTime(date);
        }
        bsParam.setCrtTime(DateTimeUtil.getCustomerDate());
        bsParam.setActTime(DateTimeUtil.getCustomerDate());
        bsParam.setUpdTime(DateTimeUtil.getCustomerDate());
        /**Modify: Bug #2623 9.12冻品管家组总控表下载时，现9.12日添加上海物流区冻品管家组成员，
         * 冻品管家组报表下载时，选择上海物流区，所属期9.12到9.14，点击下载后报表内容为空，
         * 应显示9.12日添加的冻品管家组成员 2016/09/13   BY  朱凯  end */
        bsParam.setHouseList(list);
        bsParam.setHkGroupId(groupId);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(bsParam);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */

        CommRestUtil.getUpdateHkGroupInfos(bsParam);
    }

}
