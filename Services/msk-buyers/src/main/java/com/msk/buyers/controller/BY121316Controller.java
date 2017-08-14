package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.hoperun.plug.spring.web.base.BaseUploadController;
import com.msk.buyers.bean.BY121316Bean;
import com.msk.buyers.logic.BY121316Logic;
import com.msk.common.base.BaseController;
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

/**
 *
 */
@Controller
@RequestMapping("BY121316")
public class BY121316Controller extends BaseController {

    @Autowired
    private BY121316Logic  by121316Logic;

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121316Controller.class);
    
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家商城账号信息");

        model.addAttribute("buyerId",buyerId);
        return "buyers/BY121316";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121316Bean> search( @PathVariable("buyerId") String buyerId,BasePageParam basePageParam) {
        logger.info("买家商城账号信息列表");
        basePageParam.getFilterMap().put("buyerId",buyerId);
        DbUtils.buildLikeCondition(basePageParam, "accountName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "accountPass", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "buyerName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "telNo", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "buyerSingleWechat", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "buyerQq", DbUtils.LikeMode.PARTIAL);
        return by121316Logic.findPage(basePageParam,BY121316Bean.class);
    }


    /**
     *
     * @param been
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteByAccount(BY121316Bean been,BaseParam param,Model model ) {
        logger.info("买家商城账号信息列表编辑保存数据");
        /* param.getFilterMap().put("id",been.getId());
           param.getFilterMap().put("buyerId",been.getBuyerId());*/
        String buyerId= been.getBuyerId();
        super.setCommonParam(been);
        Date currentDate = DateTimeUtil.getCustomerDate();
        been.setUpdTime(currentDate);

        by121316Logic.deleteByAccount(been);
        by121316Logic.deleteByBasicInfo(been);
        return this.init(buyerId,model);
    }


    /**
     *
     * @param br121402Param
     * @return
     */
/*    @RequestMapping(value = "save", method = RequestMethod.POST)
    public @ResponseBody String save(BY121316Bean br121402Param) {

       int line =  by121316Logic.updateData(br121402Param);
        if(line != NumberConst.IntDef.INT_ZERO){
            return SystemConst.RsStatus.SUCCESS;
        }else{
            return SystemConst.RsStatus.FAIL;
        }
    }*/

}
