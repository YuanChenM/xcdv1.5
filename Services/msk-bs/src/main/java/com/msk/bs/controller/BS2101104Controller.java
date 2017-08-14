package com.msk.bs.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.BS2101104Bean;
import com.msk.bs.bean.BS2102126Bean;
import com.msk.bs.bean.IBS2101106Bean;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.bs.logic.BS2101104Logic;
import com.msk.bs.logic.IBS2101106RsLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;

/**
 * 公共买家池.
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101104")
public class BS2101104Controller extends BaseController {
    @Autowired
    private BS2101104Logic bS2101104Logic;
    @Autowired
    private IBS2101106RsLogic iBS2101106RsLogic;

    /**
     * 实例化页面
     *
     * @return 公共买家池列表
     */
    @RequestMapping(value = "init/{pageType}", method = RequestMethod.POST)
    public String init(@RequestParam(value = "slCode", required = false) String slCode,
                       @RequestParam(value = "houseAccount", required = false) String houseAccount,
                       @RequestParam(value = "houseCode", required = false) String houseCode,
                       @RequestParam(value = "slCodeDis",required = false) String slCodeDis,
                       @RequestParam(value = "slContact",required = false) String slContact,
                       @RequestParam(value = "houseContact",required = false) String houseContact,
                       @RequestParam(value = "flagNum",required = false) String flagNum,
                       @PathVariable(value = "pageType") String pageType,
                       Model model) {

        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc start */
        //上线状态
        Map<String,String> marketingStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        model.addAttribute("marketingStatus",marketingStatus);
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc end */

        List<LgcsAreaBean> LgcsArea =  CommRestUtil.getLogisticsAreaList(new DistrictParam());
        model.addAttribute("lgcsArea", LgcsArea);
        model.addAttribute("slCode", slCode);
        model.addAttribute("slContact", slContact);
        model.addAttribute("slCodeDis", slCodeDis);
        model.addAttribute("houseCode", houseCode);
        model.addAttribute("houseAccount", houseAccount);
        model.addAttribute("houseContact", houseContact);
        model.addAttribute("flagNum",flagNum);
        model.addAttribute("pageType",pageType);
        return "/bs/BS2101104";
    }


    /**
     * 公共买家池列表
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author cx
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<IBS2101107Bean> search(BasePageParam basePageParam) {
        /*DbUtils.buildLikeCondition(basePageParam, "accountName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerTel", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "superiorName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "marketingsStatusName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "busiTel", DbUtils.LikeMode.FRONT);*/
        if (isDebug) {
            return null;
        }
        /*return this.bS2101104Logic.findBS2101041List(basePageParam);*/
        return this.bS2101104Logic.findPublicBuyerPoolInfo(basePageParam);
    }

    /**
     * 查询所有的买家列表信息
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author cx
     */
    @RequestMapping(value = "searchBuyerList", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BS2102126Bean> searchBuyerList(BasePageParam basePageParam) {
        /**入参不做后面追加%处理  跟接口对接人沟通过 */
        /*DbUtils.buildLikeCondition(basePageParam, "accountName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "telNo", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "buyerName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "superiorName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "marketingsStatusName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "busiTel", DbUtils.LikeMode.FRONT);*/

        return this.bS2101104Logic.findBuyerList(basePageParam);
    }


    /**
     * 校验该买家是否已经与管家绑定关系
     * @return
     */
    @RequestMapping(value = "checkHouseAccount", method = RequestMethod.POST)
    public @ResponseBody int checkHouseAccount(String buyerId){
       return   this.bS2101104Logic.checkHouseInfoByBuyerId(buyerId);
    }
    /**
     * 买家申请成为专属会员
     *
     * @return
     */
    @RequestMapping(value = "SlApply", method = RequestMethod.POST)
    public @ResponseBody String SlApply(BS2101104Bean bean, @RequestParam(value = "flge", required = false) String flge) {
        IBS2101106Bean iBS2101106Bean = new IBS2101106Bean();
        iBS2101106Bean.setSlCode(bean.getSlCode());
        iBS2101106Bean.setHouseAccount(bean.getHouseAccount());
        iBS2101106Bean.setHouseCode(bean.getHouseCode());
        String buyerId = bean.getBuyerId();
        iBS2101106Bean.setBuyerId(buyerId);
        iBS2101106Bean.setBuyerFlag("1");
        //冻品管家申请成为专属会员
        if ("7".equals(flge)) {
            iBS2101106Bean.setApplyStatus("2");
            iBS2101106Bean.setApplySide("A");
            //买家申请成为专属会员
        } else if ("6".equals(flge)) {
            iBS2101106Bean.setApplyStatus("2");
            iBS2101106Bean.setApplySide("B");
            //成为锁定期买家
        } else if ("5".equals(flge)) {
            iBS2101106Bean.setApplyStatus("1");
            iBS2101106Bean.setApplySide("B");
        }
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        iBS2101106Bean.setCrtId(param.getCrtId());
        iBS2101106Bean.setCrtTime(new Date());
        iBS2101106Bean.setUpdId(param.getUpdId());
        iBS2101106Bean.setUpdTime(new Date());
        iBS2101106Bean.setActId(param.getActId());
        iBS2101106Bean.setActTime(new Date());
        iBS2101106RsLogic.editExclusive(iBS2101106Bean);
        return "/bs/BS2101104";
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

    @RequestMapping(value = "unbundRelation", method = RequestMethod.POST)
    @ResponseBody
    public int unbundRelation(IBS2101107Bean bean){
        if(bean == null || StringUtil.isNullOrEmpty(bean.getBuyerId())){
            throw new BusinessException("数据为空，不能解绑");
        }
        super.setCommonParam(bean);
        return bS2101104Logic.unbundHouseAccount(bean);
    }
}
