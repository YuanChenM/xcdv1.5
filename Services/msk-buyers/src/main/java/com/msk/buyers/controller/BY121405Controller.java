package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121403Bean;
import com.msk.buyers.bean.BY121405Bean;
import com.msk.buyers.logic.BY121403Logic;
import com.msk.buyers.logic.BY121405Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.ConfigManager;
import com.msk.core.entity.ByMarketTerminal;
import com.msk.core.entity.ByMarketTerminalBasic;
import com.msk.core.entity.ByMarketTerminalByInfo;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou_yajun on 2016/7/11.
 */
@Controller
@RequestMapping("BY121405")
public class BY121405Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121405Controller.class);

    @Autowired
    private BY121405Logic by121405Logic;
    @Autowired
    private BY121403Logic by121403Logic;

    /**
     * 批发市场定性定级审批初始化
     * @param marketId
     * @return
     */
    @RequestMapping(value = "init/{marketId}")
    public String init(@PathVariable("marketId") String marketId,Model model){
        logger.info("批发市场定性定级审批初始化");
        model.addAttribute("marketId",marketId);
        //根据传参批发市场ID获取批发市场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        ByMarketTerminalBasic marketTerminalBasic = by121403Logic.findOne(param);
        model.addAttribute("marketTerminalBasic",marketTerminalBasic);

        //销售产品类型
        Map<String,String> nTargetPdClasses = CodeMasterManager.findCodeMasterMap(BuyersConstant.NTargetPdClasses.TYPE);
        model.addAttribute("NTargetPdClasses",nTargetPdClasses);
        //市场调研阶段
        Map<String,String> researchPhase = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketResearchPhase.TYPE);
        model.addAttribute("researchPhase",researchPhase);
        //从物流区接口中获取物流区基础数据
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList",logisticsAreaList);

        districtParam.setLgcsAreaCode(marketTerminalBasic.getLgcsAreaCode());
        districtParam.setIsLoadCity(0);
        districtParam.setFlag(0);
        //地区信息获取
        List<CityBean> cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
        model.addAttribute("logisticsAreaList",logisticsAreaList);
        model.addAttribute("cityList",cityList);

        //根据批发市场ID获取批发市场图片信息
        List<BY121405Bean> marketFileList = by121405Logic.findList(param);

        //从redis中获取基础数据
        //批发市场等级
        Map<String,String> marketLevel = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketLevel.TYPE);
        model.addAttribute("marketLevel",marketLevel);

        //批发市场性质
        Map<String,String> marketNature = CodeMasterManager.findCodeMasterMap(BuyersConstant.TerminalMarketNature.TYPE);
        model.addAttribute("marketNature",marketNature);

        //设置图片完整路径
        for (BY121405Bean by121405Bean : marketFileList){
            String picPath = ConfigManager.getMskFileDownLoadServices() + by121405Bean.getFileServerId();
            by121405Bean.setMarketFIlePath(picPath);
        }
        model.addAttribute("marketFileList",marketFileList);

        return "buyers/BY121405";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<ByMarketTerminalByInfo> search(String marketId,BasePageParam param) {
        logger.debug("买家员工信息查询");

        param.getFilterMap().put("marketId", marketId);
        DbUtils.buildLikeCondition(param, "merchantName", DbUtils.LikeMode.PARTIAL);

        String merchantType = StringUtil.toSafeString(param.getFilterMap().get("merchantType"));
        if (!StringUtil.isNullOrEmpty(merchantType)){
            String[] merchantTypes = merchantType.split(",");
            param.getFilterMap().put("merchantTypes", merchantTypes);
        }
        PageResult<ByMarketTerminalByInfo> result = by121403Logic.findPage(param, ByMarketTerminalByInfo.class);

        return result;
    }

    /**
     * 批发市场定性定级审批保存
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String save(BY121403Bean by121403Bean) {
        super.setCommonParam(by121403Bean);
        Date currentDate = DateTimeUtil.getCustomerDate();
        by121403Bean.setCrtTime(currentDate);
        by121403Bean.setUpdTime(currentDate);
        by121403Bean.setActTime(currentDate);
        String marketId = by121403Bean.getMarketId();
        BaseParam param = new BaseParam();
        param.getFilterMap().put("marketCode", by121403Bean.getMarketCode());
        List<ByMarketTerminal> terMarketIdList = by121405Logic.getTerMarketId(param);
        String flg = "";
        if (!CollectionUtils.isEmpty(terMarketIdList)) {
            for (ByMarketTerminal list : terMarketIdList) {
                flg = "";
                if (!list.getTerMarketId().equals(marketId)) {
                    logger.info("批发市场编码已存在");
                    flg = "1";
                    break;
                } else {
                    flg = SystemConst.RsStatus.SUCCESS;
                }
            }
            if (flg.equals(SystemConst.RsStatus.SUCCESS)) {
                //如果当前审批状态是已审批则插入一条数据到表里,如果是未审批则更新这条数据
                if (String.valueOf(BuyersConstant.MarketApproveStatus.MarketApproved).equals(by121403Bean.getMarketStatus())) {
                    by121405Logic.marketApproveSave(by121403Bean);
                } else {
                    by121405Logic.modify(by121403Bean);
                }
                //如果点击的是保存并同步按钮,需要同步批发市场标准表,买家编码和买家基本信息表中的批发市场编码
                if ("1".equals(by121403Bean.getSyncFlag())) {
                    by121405Logic.synchroBuyerCodeByMarketCode(by121403Bean);
                }
            }
            return flg;
        } else {
            //如果当前审批状态是已审批则插入一条数据到表里,如果是未审批则更新这条数据
            if (String.valueOf(BuyersConstant.MarketApproveStatus.MarketApproved).equals(by121403Bean.getMarketStatus())) {
                by121405Logic.marketApproveSave(by121403Bean);
            } else {
                by121405Logic.modify(by121403Bean);
            }
            //如果点击的是保存并同步按钮,需要同步批发市场标准表,买家编码和买家基本信息表中的批发市场编码
            if ("1".equals(by121403Bean.getSyncFlag())) {
                by121405Logic.synchroBuyerCodeByMarketCode(by121403Bean);
            }
            return SystemConst.RsStatus.SUCCESS;
        }
    }
}
