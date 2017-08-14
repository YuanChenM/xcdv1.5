package com.msk.bs.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.IBS2101106Bean;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.bs.logic.BS2101103Logic;
import com.msk.bs.logic.BSCommLogic;
import com.msk.bs.logic.IBS2101106RsLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.MdProvince;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 买手店管家会员列表Controller.
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101103")
public class BS2101103Controller extends BaseController {
    @Autowired
    private BS2101103Logic bS2101103Logic;
    @Autowired
    private BSCommLogic bsCommLogic;
    @Autowired
    private IBS2101106RsLogic iBS2101106RsLogic;

    /**
     * 实例化页面
     *
     * @return 买手店管家会员列表
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(@RequestParam(value = "slCode", required = false) String slCode,
                       @RequestParam(value = "slCodeDis", required = false) String slCodeDis,
                       @RequestParam(value = "slContact", required = false) String slContact,
                       @RequestParam(value = "houseAccount", required = false) String houseAccount,
                       @RequestParam(value = "houseCode", required = false) String houseCode,
                       @RequestParam(value = "houseContact", required = false) String houseContact,
                       @RequestParam(value = "houseCodeDis", required = false) String houseCodeDis,
                       @RequestParam(value = "applyStatus", required = false) String applyStatus,
                       @RequestParam(value = "flagNum", required = false) String flagNum,
                       @RequestParam(value = "houseShowName", required = false) String houseShowName,
                       Model model) {

        //List<MdProvince> mdProvinces = bsCommLogic.findMdProvinces(new BaseParam());
        List<LgcsAreaBean> LgcsArea =  CommRestUtil.getLogisticsAreaList(new DistrictParam());
        model.addAttribute("LgcsArea", LgcsArea);
        model.addAttribute("slCode", slCode);
        model.addAttribute("slContact", slContact);
        model.addAttribute("slCodeDis", slCodeDis);
        model.addAttribute("houseCode", houseCode);
        model.addAttribute("houseAccount", houseAccount);
        model.addAttribute("houseContact", houseContact);
        model.addAttribute("houseCodeDis", houseCodeDis);
        model.addAttribute("applyStatus", applyStatus);
        model.addAttribute("flagNum", flagNum);
        model.addAttribute("houseShowName", houseShowName);
        return "/bs/BS2101103";
    }

    /**
     * 买手店管家会员列表
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author cx
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<IBS2101107Bean> search(
            @RequestParam(value = "lgcsAreaCode", required = false) String lgcsAreaCode,
            @RequestParam(value = "cityCode", required = false) String cityCode,
            @RequestParam(value = "districtCode", required = false) String districtCode,
            @RequestParam(value = "applyStatus", required = false) String applyStatus,
            BasePageParam basePageParam) {
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc start */
//        DbUtils.buildLikeCondition(basePageParam, "lgcsCode", DbUtils.LikeMode.FRONT);
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc end */
        /** Modfiy:  Bug#2620 : 买手管家会员列表：查询条件不起作用 2016/9/12   BY  zhukai1  Start */
       /* DbUtils.buildLikeCondition(basePageParam, "buyerCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "busiTel", DbUtils.LikeMode.FRONT);*/
        /** Modfiy:  Bug#2620 : 买手管家会员列表：查询条件不起作用 2016/9/12   BY  zhukai1  end */
        String applyStatusName= StringUtil.toSafeString(basePageParam.getFilterMap().get("applyStatusName"));
        // 自身买手管家会员列表跳转
        if(!StringUtil.isNullOrEmpty(applyStatusName)){
            String [] applyStatusNames=applyStatusName.split(",");
            basePageParam.getFilterMap().put("applyStatusNames", applyStatusNames);
        }
        // 从冻品管家列表跳转过来的
        if(!StringUtil.isNullOrEmpty(applyStatus)){
            basePageParam.getFilterMap().put("applyStatus", applyStatus);
        }
        basePageParam.setFilter("lgcsAreaCode", lgcsAreaCode);
        basePageParam.setFilter("cityCode", cityCode);
        basePageParam.setFilter("districtCode", districtCode);
        basePageParam.setFilter("buyerFlag", "1");
        if (isDebug) {
            return null;
        }
        return this.bS2101103Logic.findBS2101103List(basePageParam);
    }

    /**
     * 解除 买手店管家会员关系
     *
     * @param houseCode
     * @param buyerId
     * @param slCode
     * @return
     */
    @RequestMapping(value = "relievePelation", method = RequestMethod.POST)
    public
    @ResponseBody
    String relievePelation(@RequestParam(value = "houseCode", required = false) String houseCode,
                           @RequestParam(value = "buyerId", required = false) String buyerId,
                           @RequestParam(value = "slCode", required = false) String slCode) {
        IBS2101106Bean iBS2101106Bean = new IBS2101106Bean();
        iBS2101106Bean.setHouseCode(houseCode);
        iBS2101106Bean.setBuyerId(buyerId);
        iBS2101106Bean.setSlCode(slCode);
        iBS2101106Bean.setBuyerFlag("1");
        iBS2101106Bean.setDelFlg("1");
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(iBS2101106Bean);
        Date date = DateTimeUtil.getCustomerDate();
        iBS2101106Bean.setCrtTime(date);
        iBS2101106Bean.setUpdTime(date);
        iBS2101106Bean.setActTime(date);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        iBS2101106RsLogic.editExclusive(iBS2101106Bean);

        return "1";
    }

    /**
     * 成为买手店管家会员
     * @param iBS2101106Bean
     * @return 结果
     */
    @RequestMapping(value = "buildPelation", method = RequestMethod.POST)
    public @ResponseBody int buildPelation(IBS2101106Bean iBS2101106Bean) {
        iBS2101106Bean.setBuyerFlag("1");
        iBS2101106Bean.setApplyStatus("2");
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(iBS2101106Bean);
        Date date = DateTimeUtil.getCustomerDate();
        iBS2101106Bean.setCrtTime(date);
        iBS2101106Bean.setUpdTime(date);
        iBS2101106Bean.setActTime(date);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        return iBS2101106RsLogic.editExclusive(iBS2101106Bean);
    }


    /**
     * 根据物流区Code查询城市信息
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "findCity", method = RequestMethod.POST)
    @ResponseBody
    public List<CityBean> findCity(String lgcsAreaCode){
        DistrictParam param = new DistrictParam();
        param.setLgcsAreaCode(lgcsAreaCode);
        param.setIsLoadCity(0);
        param.setFlag(0);
        return  CommRestUtil.getProvinceCityList(param);
    }

    /**
     * 根据cityCode查询对应下面的区信息
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "findDistrict", method = RequestMethod.POST)
    @ResponseBody
    public List<DistrictBean> findDistrict(String cityCode){
        DistrictParam param = new DistrictParam();
        param.setCityCode(cityCode);
        param.setFlag(0);
        return  CommRestUtil.getDistrictList(param);
    }
}
