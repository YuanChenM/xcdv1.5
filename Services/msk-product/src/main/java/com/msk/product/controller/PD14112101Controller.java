package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.core.entity.MdLogisticsArea;
import com.msk.common.base.BaseController;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.product.bean.PD141121Bean;
import com.msk.product.logic.PD14112101Logic;
import com.msk.product.logic.PD141121Logic;
import com.msk.product.utils.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 物流区产品管理
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("PD14112101")
public class PD14112101Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(PD141121Controller.class);

    /**
     * 注入PD141121Logic
     */
    @Autowired
    private PD141121Logic pd141121Logic;

    @Autowired
    private PD14112101Logic pd14112101Logic;

    /**
     * 默认选择物流区编码为'01'的物流区,初始化
     * @param model model
     * @return PD141121
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String initA(String lgcsAreaCode,Model model) {
        if(lgcsAreaCode==null){
            return this.init("41", model);
        }else{
            return this.init(lgcsAreaCode,model);
        }

    }

    @RequestMapping(value = "checkedList",
            method = RequestMethod.POST)
    public String checkedList(String lgcsAreaCode,Model model) {

        List<MdLogisticsArea> logisticsAreas = this.pd141121Logic.findListChecked(lgcsAreaCode);
        model.addAttribute("lgcsAreaCode",lgcsAreaCode);
        model.addAttribute("logisticsAreas",logisticsAreas);
        return "pd/PD141121";
    }


    /**
     * 物流区产品管理画面初始化
     * @param lgcsAreaCode 物流区编码
     * @param model model
     * @return PD141121
     */
    @RequestMapping(value = "init/{lgcsAreaCode}",
            method = RequestMethod.POST)
    public String init(@PathVariable("lgcsAreaCode") String lgcsAreaCode, Model model) {
        logger.debug("物流区产品管理画面初始化");
        /*modify by yang_chunyan start at 2016/6/30*/
        DistrictParam para = new DistrictParam();
        para.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO ));
        List<LgcsAreaBean> logisticsAreas = RestUtil.findDistrict(para);
        /*modify by yang_chunyan end at 2016/6/30*/
        model.addAttribute("lgcsAreaCode",lgcsAreaCode);
        model.addAttribute("logisticsAreas",logisticsAreas);
        return "pd/PD14112101";
    }

    /**
     *
     * 添加数据
     * @param bean PD141121Bean
     * @param model model
     * @return 实例化
     */
    @RequestMapping(value = "add",
            method = RequestMethod.POST)
    public String add(PD141121Bean bean, Model model) {
        bean.setDelFlg("0");
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        /*bean.setCrtId("admin");*/
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        bean.setCrtTime(DateTimeUtil.getCustomerDate());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        /*bean.setUpdId("admin");*/
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        bean.setUpdTime(DateTimeUtil.getCustomerDate());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        /*bean.setActId("admin");*/
        bean.setActTime(DateTimeUtil.getCustomerDate());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        bean.setVer(NumberConst.IntDef.INT_ONE);

        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(bean);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        int resultCount = this.pd141121Logic.save(bean);
        if (resultCount <= NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("插入失败，请重新操作！");
        }
        return this.init(bean.getLgcsAreaCode(), model);
    }

    /**
     * 删除数据
     * @param bean PD141121Bean
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    @ResponseBody
    public String delete(PD141121Bean bean, Model model){
        int resultCount = this.pd141121Logic.remove(bean);
        if (resultCount <= NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("删除失败，请重新操作！");
        }
        return "ok";//this.init(bean.getLgcsAreaCode(), model);
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */

    @RequestMapping(value = "search/{lgcsAreaCode}",
            method = RequestMethod.POST)
    public @ResponseBody
    PageResult<PD141121Bean> search(@PathVariable("lgcsAreaCode") String lgcsAreaCode,BasePageParam param) {
        /*logger.debug(lgcsAreaCode);*/
/*
        if(s!=null){
            param.getFilterMap().put("lgcsAreaCode",s);
        }*/
        /*param.getFilterMap().put("lgcsAreaCode", lgcsAreaCode);*/
        //param.setPageSize(NumberConst.IntDef.INT_THIRTY);
        DbUtils.buildLikeCondition(param, "classesCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "machiningCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "breedCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "featureCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "weightCode", DbUtils.LikeMode.FRONT);


        /*DbUtils.buildLikeCondition(param, "lgcsAreaCode", DbUtils.LikeMode.FRONT);*/


        DbUtils.buildLikeCondition(param, "lgcsAreaName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "classesName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "machiningName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "breedName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "featureName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "weightName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "gradeCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(param, "gradeName", DbUtils.LikeMode.FRONT);
        return pd14112101Logic.findPage(param, PD141121Bean.class);
    }

}
