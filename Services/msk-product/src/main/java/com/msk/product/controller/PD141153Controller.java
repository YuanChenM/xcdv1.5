package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdNormsStd;
import com.msk.product.logic.PD141153Logic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * 产品包装编辑信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping("pd141153")
public class PD141153Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PD141153Controller.class);
    @Autowired
    private PD141153Logic pd141153Service;

    /**
     * 进入包装信息编辑页面*
     *
     * @param classestreeCode 包装classesTreeCode
     * @param mav             mav
     * @return pd/PD141153页面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model mav, @RequestParam(value = "classestreeCode", required = false) String classestreeCode,
                       @RequestParam(value = "breedName", required = false) String breedName,
                       @RequestParam(value = "featureName", required = false) String featureName,
                       @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "machiningCode", required = false) String machiningCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode,
                       @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "weightCode", required = false) String weightCode) {


        if (!"".equals(classestreeCode) && null != classestreeCode) {
            logger.info("修改查询");
            mav.addAttribute("classestreeCode", classestreeCode);
            mav.addAttribute("breedName", breedName);
            mav.addAttribute("featureName", featureName);
            BaseParam param1 = new BaseParam();
            param1.setFilter("classestreeCode", classestreeCode);
            PdNormsStd pd = pd141153Service.findOne(param1);
            mav.addAttribute("pdNorms", pd);
        }
        if (StringUtils.isNotBlank(classesCode)) mav.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) mav.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) mav.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) mav.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) mav.addAttribute("weightCode", weightCode);
        return "pd/PD141153";
    }

    /**
     * 包装添加或修改保存操作
     *
     * @param pdNorms 保存参数
     * @param mav     mav
     * @return "pd/PD141111"页面
     * @author pxg
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public String save(PdNormsStd pdNorms, Model mav,@RequestParam(value = "classesCode", required = false) String classesCode) {

        BaseParam params = new BaseParam();
        super.setCommonParam(params);
        pdNorms.setUpdId(params.getUpdId());
        //执行修改操作
        if (StringUtils.isNotBlank(pdNorms.getClassestreeCode())) {
            logger.debug("修改操作");
            if(StringUtils.isNotBlank(pdNorms.getNormsOut())){
                this.pd141153Service.modifyTree(pdNorms);
            }
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("pdNorms", pdNorms);
            //查询修改后的数据和数据库中是否有相同数据
            int num = pd141153Service.findNum(param);
            if (num == NumberConst.IntDef.INT_ZERO) {
                int result = pd141153Service.modify(pdNorms);
                if (result > NumberConst.IntDef.INT_ZERO) {
                    logger.debug("修改成功");
                }
            } else {
                throw new BusinessException("该条数据已经存在,请重新修改");
            }
        }
        if (StringUtils.isNotBlank(classesCode)) mav.addAttribute("classesCode", classesCode);
        return "pd/PD141124";

    }
}
