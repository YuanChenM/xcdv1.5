package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121413Bean;
import com.msk.buyers.bean.BY121413Param;
import com.msk.buyers.logic.BY121413Logic;
import com.msk.common.base.BaseController;
import com.msk.core.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("BY121413")
public class BY121413Controller extends BaseController {


    @Autowired
    private BY121413Logic by121413Logic;
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121413Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{marketId}", method = RequestMethod.POST)
    public String init(@PathVariable("marketId") String marketId, Model model) {
        logger.debug("买家商城账号信息");
        model.addAttribute("marketId", marketId);
        return "buyers/BY121413";
    }

    /**
     * 目标买家市场调研产品一览表
     * @param param
     * @param marketId
     * @return
     */
    @RequestMapping(value = "isTargetSearch/{marketId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121413Bean> isTargetSearch(BasePageParam param, @PathVariable("marketId") String marketId) {
        logger.info("目标市场");
        PageResult<BY121413Bean> result = new PageResult<BY121413Bean>();
        //查询条件
        String buyerStoreNo = param.getFilterMap().get("buyerStoreNo").toString();
       /* if (buyerStoreNo.contains(StringConst.UNDERLINE)) {
            buyerStoreNo = buyerStoreNo.replace(StringConst.UNDERLINE, "\\" + StringConst.UNDERLINE);
        }else if(buyerStoreNo.contains(StringConst.PRE)){
            buyerStoreNo = buyerStoreNo.replace(StringConst.PRE, "\\" + StringConst.PRE);
        }else if (buyerStoreNo.contains("\\")){
            buyerStoreNo = buyerStoreNo.replace("\\", "\\\\\\" );
        }*/
        param.getFilterMap().put("buyerStoreNo",buyerStoreNo);
        DbUtils.buildLikeCondition(param, "buyerStoreNo", DbUtils.LikeMode.PARTIAL);
       /* param.getFilterMap().put("buyerStoreNo","%"+buyerStoreNo+"%");*/
        param.getFilterMap().put("marketId", marketId);
        param.getFilterMap().put("isTargetMerchant", "1");
        List<BY121413Bean> list = by121413Logic.targetSearchList(param);
        result.setData(list);
        int count = by121413Logic.getPageCount(param);
        result.setRecordsTotal(count);
        return result;
    }


    /**
     *  非目标买家市场调研产品一览表
     *
     * @param marketId
     * @param param
     * @return
     */
    @RequestMapping(value = "nonTargetSearch/{marketId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121413Bean> nonTargetSearch(@PathVariable("marketId") String marketId, BasePageParam param) {
        logger.info("非目标买家");
        PageResult<BY121413Bean> result = new PageResult<BY121413Bean>();
        param.getFilterMap().put("marketId", marketId);
        param.getFilterMap().put("isTargetMerchant", "0");
        //店铺号搜索，转义特殊字符
        String buyerStoreNo = param.getFilterMap().get("buyerStoreNo").toString();
       /* if (buyerStoreNo.contains(StringConst.UNDERLINE)) {
            buyerStoreNo = buyerStoreNo.replace(StringConst.UNDERLINE, "\\" + StringConst.UNDERLINE);
        }else if(buyerStoreNo.contains(StringConst.PRE)){
            buyerStoreNo = buyerStoreNo.replace(StringConst.PRE, "\\" + StringConst.PRE);
        }else if(buyerStoreNo.contains("\\")){
            buyerStoreNo = buyerStoreNo.replace("\\", "\\\\\\" );
        }*/
        param.getFilterMap().put("buyerStoreNo",buyerStoreNo);
        DbUtils.buildLikeCondition(param, "buyerStoreNo", DbUtils.LikeMode.PARTIAL);

        List<BY121413Bean> list = by121413Logic.nonTargetSearchList(param);
        result.setData(list);
        int count = by121413Logic.getPageCount(param);
        result.setRecordsTotal(count);
        return result;
    }

    /**
     *  非目标买家市场调研产品删除数据
     *
     * @param by121413Param
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteProduct(BY121413Param by121413Param) {
        String buyerStoreNo=  by121413Param.getBuyerStoreNo();
        Long storeId= by121413Param.getStoreId();
        //设置更新共同字段
        BaseEntity entity  = new BaseEntity();
        super.setCommonParam(entity);
        Date currentDate = DateTimeUtil.getCustomerDate();
        entity.setUpdTime(currentDate);

        BaseParam deleteParam = new BaseParam();
        deleteParam.getFilterMap().put("updId", entity.getUpdId());
        deleteParam.getFilterMap().put("updTime",entity.getUpdTime() );
        deleteParam.getFilterMap().put("storeId",storeId);
        deleteParam.getFilterMap().put("buyerStoreNo",buyerStoreNo);
        int deleteBrSearch = by121413Logic.deleteBrSearch(deleteParam);
        by121413Logic.deleteSalePd(deleteParam);
        if (deleteBrSearch == NumberConst.IntDef.INT_ONE) {
            by121413Param.getFilterMap().put("isMerchantNew", "1");
            by121413Param.getFilterMap().put("isMarketNew", "1");
            by121413Param.getFilterMap().put("isPhaseNew", "1");
            by121413Param.getFilterMap().put("marketId", by121413Param.getMarketId());
            by121413Param.getFilterMap().put("updId", entity.getUpdId());
            by121413Param.getFilterMap().put("updTime",entity.getUpdTime() );
            by121413Logic.modifyBasicStatus(by121413Param);
            return SystemConst.RsStatus.SUCCESS;
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }

}
