package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.core.entity.SlPdPkg;
import com.msk.seller.bean.SL241101Bean;
import com.msk.seller.bean.SL241106Bean;
import com.msk.seller.logic.SL241106Logic;
import com.msk.seller.logic.Sl241101Logic;
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

/**
 * SL241106Controller
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Controller
@RequestMapping("SL241106")
public class SL241106Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SL241106Controller.class);
    @Autowired
    private SL241106Logic sl241106Logic;
    @Autowired
    private Sl241101Logic sl241101Logic;

    /**
     * 页面初始化操作
     * @param model The Model
     * @return SL241106
     */
    @RequestMapping(value="init",method = RequestMethod.POST)
    public String init(Model model,SL241101Bean bean){
        BasePageParam param = new BasePageParam();
        param.setFilter("slCode", bean.getSlCode());
        param.setPaging(false);
        PageResult<SL241101Bean> sl241101Beans = sl241101Logic.findPageResult(param);
        if (!CollectionUtils.isEmpty(sl241101Beans.getData())) {
            model.addAttribute("slName", sl241101Beans.getData().get(0).getSlShowName());
        }
        model.addAttribute("slCode", bean.getSlCode());
        model.addAttribute("bean",bean);
        return "sl/SL241106";
    }

    /**
     * 数据加载
     * @param param The Base Page Param
     * @return The PageResult SL241106Bean
     */
    @RequestMapping(value="search/{slCode}",method = RequestMethod.POST)
    public @ResponseBody PageResult<SL241106Bean> search(@PathVariable(value = "slCode")String slCode,BasePageParam param){
        param.setFilter("slCode",slCode);
        DbUtils.buildLikeCondition(param, "epName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "brandName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "classesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "breedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "featureName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "machiningName", DbUtils.LikeMode.PARTIAL);
        String auditResult = StringUtil.toSafeString(param.getFilterMap().get("auditResult"));
        String monitorResult = StringUtil.toSafeString(param.getFilterMap().get("monitorResult"));

        if (!StringUtil.isNullOrEmpty(auditResult)) {
            String[] auditResults = auditResult.split(",");
            param.getFilterMap().put("auditResults", auditResults);
        }
        if (!StringUtil.isNullOrEmpty(monitorResult)) {
            String[] monitorResults = monitorResult.split(",");
            param.getFilterMap().put("monitorResults", monitorResults);
        }
        return this.sl241106Logic.findPage(param);
    }

    /**
     * 修改卖家产品包装审核
     * @param type 修改类型
     * @param slPdPkg 参数
     * @return 页面刷新
     */
    @RequestMapping(value="save/{type}",method = RequestMethod.POST)
    public String save(@PathVariable(value = "type") String type,SlPdPkg slPdPkg,Model model){
        slPdPkg.setUpdId(getLoginUser().getEmplId());
        slPdPkg.setUpdTime(DateTimeUtil.getCustomerDate());
        if("check".equals(type)){
            this.sl241106Logic.modifyAuditResult(slPdPkg);
        }else{
            this.sl241106Logic.modifyMonitorResult(slPdPkg);
        }
        SL241101Bean sl241101Bean = new SL241101Bean();
        sl241101Bean.setSlCode(slPdPkg.getSlCode());
        return this.init(model,sl241101Bean);
    }
}
