package com.msk.bs.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.IBS210111201Bean;
import com.msk.bs.bean.IBS2101112Bean;
import com.msk.bs.logic.BS2101112Logic;
import com.msk.bs.logic.BSCommLogic;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.MdProvince;

/**
 * 买手冻品管家的买家履历信息
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101112")
public class BS2101112Controller extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(BS2101112Controller.class);
    @Autowired
    private BS2101112Logic bs2101112Logic;

    @Autowired
    private BSCommLogic bsCommLogic;

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(@RequestParam(value = "slCode", required = false) String slCode,
                       @RequestParam(value = "slContact", required = false) String slContact,
                       @RequestParam(value = "slCodeDis", required = false) String slCodeDis,
                       @RequestParam(value = "buyerId", required = false) String buyerId,
                       @RequestParam(value = "houseCode", required = false) String houseCode,
                       @RequestParam(value = "houseAccount", required = false) String houseAccount,
                       @RequestParam(value = "houseCodeDis", required = false) String houseCodeDis,
                       @RequestParam(value = "houseContact", required = false) String houseContact,
                       @RequestParam(value = "flagNum", required = false) String flagNum,
                       @RequestParam(value = "pageType", required = false) String pageType,
                       @RequestParam(value = "houseShowName", required = false) String houseShowName,
                       Model model) {
        logger.debug("查询买手冻品管家的买家履历信息");
        if (isDebug) {
            return null;
        }
        model.addAttribute("slCode", slCode);
        model.addAttribute("slContact", slContact);
        model.addAttribute("slCodeDis", slCodeDis);
        model.addAttribute("buyerId", buyerId);
        model.addAttribute("houseCode", houseCode);
        model.addAttribute("houseCodeDis", houseCodeDis);
        model.addAttribute("houseAccount", houseAccount);
        model.addAttribute("houseContact", houseContact);
        model.addAttribute("flagNum", flagNum);
        model.addAttribute("pageType", pageType);
        model.addAttribute("houseShowName", houseShowName);
        List<MdProvince> mdProvinces = bsCommLogic.findMdProvinces(new BaseParam());
        model.addAttribute("mdProvinces", mdProvinces);
        return "bs/BS2101111";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<IBS2101112Bean> search(
            @RequestParam(value = "provinceCode", required = false) String provinceCode,
            @RequestParam(value = "cityCode", required = false) String cityCode,
            @RequestParam(value = "districtCode", required = false) String districtCode,
            @RequestParam(value = "provinceCode1", required = false) String provinceCode1,
            @RequestParam(value = "cityCode1", required = false) String cityCode1,
            @RequestParam(value = "districtCode1", required = false) String districtCode1,
            @RequestParam(value = "searchDataFlag", required = false) String searchDataFlag,
            BasePageParam basePageParam) {

        if(StringUtil.isNullOrEmpty(basePageParam.getFilterMap().get("slCode").toString()) || "-".equals(basePageParam.getFilterMap().get("slCodeDis"))){
            PageResult<IBS2101112Bean> pageResult = new PageResult<IBS2101112Bean>();
            pageResult.setRecordsTotal(0);
            pageResult.setData(new ArrayList<IBS2101112Bean>());
           return pageResult;
        }

        String code = basePageParam.getFilterMap().get("buyerInfo.buyerCode").toString();
        basePageParam.setFilter("buyerCode1", code);
        basePageParam.setFilter("buyerName1", basePageParam.getFilterMap().get("buyerInfo.buyerName").toString());
        basePageParam.setFilter("buyerAddr1", basePageParam.getFilterMap().get("buyerInfo.buyerAddr").toString());
        basePageParam.setFilter("busiTel1", basePageParam.getFilterMap().get("buyerInfo.busiTel").toString());
        String houseCodeDis1 = (String) basePageParam.getFilterMap().get("houseInfo.houseCodeDis");
        String houseContact1 = (String) basePageParam.getFilterMap().get("houseInfo.houseContact");
        if (null != houseCodeDis1 && !"".equals(houseCodeDis1)) {
            /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  Start */
            basePageParam.setFilter("houseCodeDis1", DbUtils.buildLikeCondition(houseCodeDis1,DbUtils.LikeMode.FRONT));
            /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  End */
        }
        if (null != houseContact1 & !"".equals(houseContact1)) {
            /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  Start */
            basePageParam.setFilter("houseContact1", DbUtils.buildLikeCondition(houseContact1,DbUtils.LikeMode.FRONT));
            /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  End */
        }
        DbUtils.buildLikeCondition(basePageParam, "startTime", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "endTime", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerReason", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyershopReason", DbUtils.LikeMode.FRONT);
        //根据买家区划查询
        basePageParam.setFilter("provinceCode", provinceCode);
        basePageParam.setFilter("cityCode", cityCode);
        basePageParam.setFilter("districtCode", districtCode);
        //根据冻品管家区划查询
        basePageParam.setFilter("provinceCode1",provinceCode1);
        basePageParam.setFilter("cityCode1", cityCode1);
        basePageParam.setFilter("districtCode1", districtCode1);

        basePageParam.setFilter("searchDataFlag", searchDataFlag);
        if (isDebug) {
            return null;
        }
        //PageResult<IBS2101112Bean> pageResult = this.bs2101112Logic.findPage(basePageParam, IBS2101112Bean.class);
        PageResult<IBS2101112Bean> pageResult = bs2101112Logic.findIBS2101112List(basePageParam);
        List<IBS2101112Bean> listBeans = pageResult.getData();
        for (IBS2101112Bean ibs2101112Bean : listBeans) {
            IBS210111201Bean ibs210111201Bean = ibs2101112Bean.getBuyerInfo();
            String cityName="";
            String provinceName="";
            String districtName="";
            if(!StringUtil.isEmpty(ibs2101112Bean.getBuyerInfo().getCityName())){
                cityName = ibs2101112Bean.getBuyerInfo().getCityName();
            }
            if(!StringUtil.isEmpty(ibs2101112Bean.getBuyerInfo().getProvinceName())){
                 provinceName = ibs2101112Bean.getBuyerInfo().getProvinceName();
            }
            if(!StringUtil.isEmpty(ibs2101112Bean.getBuyerInfo().getDistrictName())){
                 districtName = ibs2101112Bean.getBuyerInfo().getDistrictName();
            }

            String districtName1 = provinceName + " " + cityName + " " + districtName;
            ibs210111201Bean.setDistrictName1(districtName1);
            ibs2101112Bean.setBuyerInfo(ibs210111201Bean);
            if(null == ibs2101112Bean.getHouseInfo().getHouseCodeDis()){
                ibs2101112Bean.getHouseInfo().setHouseCodeDis("");
            }
            if(null == ibs2101112Bean.getHouseInfo().getHouseShowName()){
                ibs2101112Bean.getHouseInfo().setHouseShowName("");
            }
        }
        return pageResult;
    }
}
