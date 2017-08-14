package com.msk.stock.controller;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.hoperun.plug.spring.web.base.BaseController;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.stock.bean.*;
import com.msk.stock.logic.SO151201Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.math.BigDecimal;
import java.util.*;

/**
 * 分销正常库存
 */
@Controller
@RequestMapping("SO151201")
@SessionAttributes("param")
public class SO151201Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO151201Controller.class);

    @Autowired
    private SO151201Logic so151201Logic;

    @Autowired
    private BaseRedisDao redisDao;

    /**
     * 加载分销正常库存界面
     *
     * @return String
     */
    @RequestMapping(value = "init",   method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
       TreeMap  <String,String> treeMap= this.so151201Logic.getsalePlatform();  // 查询销售平台
        List salePlatformList = new ArrayList(treeMap.entrySet());
        model.addAttribute("salePlatformList", salePlatformList);
        model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "so/SO151201";
    }

    /**
     * @param basePageParam basePageParam
     * @return PageResult<SO151201Bean>
     */
  @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SO151201Bean> search(BasePageParam basePageParam) {
      logger.info("查询数据库");
      DbUtils.buildLikeCondition(basePageParam, "lgcsCode", DbUtils.LikeMode.FRONT);
      DbUtils.buildLikeCondition(basePageParam, "warehouseCode", DbUtils.LikeMode.FRONT);

      DbUtils.buildLikeCondition(basePageParam, "lgcsName", DbUtils.LikeMode.FRONT);
      DbUtils.buildLikeCondition(basePageParam, "warehouseName", DbUtils.LikeMode.FRONT);
      DbUtils.buildLikeCondition(basePageParam, "slName", DbUtils.LikeMode.FRONT);
        /*lgcsName   warehouseName    slName     */
      DbUtils.buildLikeCondition(basePageParam, "pdCode", DbUtils.LikeMode.FRONT);

      DbUtils.buildLikeCondition(basePageParam, "supplyPlayFrom", DbUtils.LikeMode.FRONT);
       /* supplyPlayFrom supplierName   */
      DbUtils.buildLikeCondition(basePageParam, "pdName", DbUtils.LikeMode.FRONT);

      return  this.so151201Logic.search(basePageParam);
    }


    /**
     *
     * @param stockId
     * @param stockQty
     * @return
     */
    @RequestMapping(value = "save",  method = RequestMethod.POST)
    @ResponseBody
    public  String save(Long stockId, BigDecimal stockQty) {
        BasePageParam param=new BasePageParam();
       Map<String,Object>map= param.getFilterMap();
        map.put("stockId",stockId);
        map.put("stockQty",stockQty);
        int result = this.so151201Logic.saveStockQty(param);
        if (result > 0) {
            return SystemConst.RsStatus.SUCCESS;
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }
}
