package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdMachining;
import com.msk.product.bean.PD142104Bean;
import com.msk.product.bean.SlCodeNatureBean;
import com.msk.product.logic.PD141126Logic;
import com.msk.product.logic.PD142104Logic;
import com.msk.product.logic.ProductLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 卖家批次产品目录报表controller
 *
 * @author gyh
 */
@Controller
@RequestMapping("PD142104")
public class PD142104Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PD142104Controller.class);
    @Autowired
    private PD141126Logic pd141126Logic;
    @Autowired
    private PD142104Logic pd142104Logic;
    @Autowired
    private ProductLogic productLogic;
    /**
     * 查询二级分类信息
     *
     * @param param param
     * @return
     */
    @RequestMapping(value = "findPdMachining", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdMachining> findPdMachining(BaseParam param) {
        return this.productLogic.findPdMachining(param);
    }

    /**
     * 卖家批次产品目录报表
     *
     * @return 页面
     * @author gyh
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model) {
        List<PdClassestree> classesList = pd141126Logic.queryOneClassify();
        model.addAttribute("classesList", classesList);
        BaseParam param=new BaseParam();
        param.setFilter("classesCode","01");
        model.addAttribute("machiningInfo",this.productLogic.findPdMachining(param));
        return "pd/PD142104";
    }

    /**
     * 卖家批次产品目录报表数据
     *
     * @return 数据
     * @author gyh
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<PD142104Bean> search(BasePageParam pageParam) {
        DbUtils.buildLikeCondition(pageParam, "machiningName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "scientificName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "slLicAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "slName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "sllfAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "sllfName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "brandName", DbUtils.LikeMode.FRONT);
        logger.debug("产品包装检索");
        PageResult<PD142104Bean> pageResult = this.pd142104Logic.findPage(pageParam, PD142104Bean.class);
        List<PD142104Bean> beans = pageResult.getData();
        for (PD142104Bean bean : beans) {
            SlCodeNatureBean slCodeNature = new SlCodeNatureBean();
            slCodeNature.setBrandClass(bean.getBrandClass().trim());
            slCodeNature.setBrandId(bean.getBrandId());
            slCodeNature.setSlCodeDis(bean.getSlCodeDis());
            slCodeNature.setSlMainClass(bean.getSlMainClass().trim());
            slCodeNature.setSlCodeManufacture(bean.getSlCodeManufacture());
            if(!StringUtil.isNullOrEmpty(bean.getLotCode())){
                bean.setLotPdCode(bean.getAttributeCode() + bean.getWeightCode() + bean.getNormsCode() + productLogic.getSlCodeNature(slCodeNature) + bean.getLotCode());
            }else{
                bean.setLotPdCode("");
            }
        }
        return pageResult;
    }
}
