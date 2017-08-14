package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121302Bean;
import com.msk.buyers.logic.BY121302Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜场列表
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("BY121302")
public class BY121302Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121302Controller.class);

    @Autowired
    private BY121302Logic by121302Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("菜场列表初始化");
        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        return "buyers/BY121302";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
        method = RequestMethod.POST)
    public @ResponseBody PageResult<BY121302Bean> search(BasePageParam param) {
        logger.debug("菜场列表查询");
        DbUtils.buildLikeCondition(param, "marketCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketName", DbUtils.LikeMode.PARTIAL);
        PageResult<BY121302Bean> result = by121302Logic.findPage(param, BY121302Bean.class);

        return result;
    }

    /**
     * 删除菜场
     *
     * @param marketId marketId
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "delete/{marketId}",
        method = RequestMethod.POST)
    public String delete(@PathVariable("marketId") String marketId, Model model) {
        logger.debug("删除菜场");
        BY121302Bean entity =new BY121302Bean();
        super.setCommonParam(entity);
        Date currentDate = DateTimeUtil.getCustomerDate();
        entity.setFodMarketId(marketId);
        entity.setUpdTime(currentDate);
        int result = by121302Logic.deleteMarketFoodById(entity);
        if (result == NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("删除失败,未找到该菜场!");
        }
        return "buyers/BY121302";
    }
}
