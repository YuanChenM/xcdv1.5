package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PdTcProviderPackageParam;
import com.msk.product.logic.PD141155Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 正式上线添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141155")
public class PD141155Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PD141155Controller.class);
    @Autowired
    private PD141155Logic pd141155Logic;
    /**
     * 实例化页面
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,BaseParam param) {
        String tcProviderId= StringUtil.toSafeString(param.getFilterMap().get("tcProviderId"));
        PdTcProviderPackageParam list= pd141155Logic.queryOneData(tcProviderId);
        PdTcProviderPackageParam packageParam=new PdTcProviderPackageParam();
        packageParam.setClassestreeCode(StringUtil.toSafeString(param.getFilterMap().get("classesCode")));
        packageParam.setProviderCode(StringUtil.toSafeString(param.getFilterMap().get("providerCode")));
        packageParam.setMachiningCode(StringUtil.toSafeString(param.getFilterMap().get("machiningCode")));
        packageParam.setBreedCode(StringUtil.toSafeString(param.getFilterMap().get("breedCode")));
        packageParam.setFeatureCode(StringUtil.toSafeString(param.getFilterMap().get("featureCode")));
        packageParam.setWeightCode(StringUtil.toSafeString(param.getFilterMap().get("weightCode")));
        model.addAttribute("packageParam",packageParam);
        model.addAttribute("list",list);
        return "pd/PD141155";
    }

    /**
     * 保存数据
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "saveData",
            method = RequestMethod.POST)
    public @ResponseBody int saveData(PdTcProviderPackageParam pdTcProviderPackage) {
        logger.debug("审核意见状态保存");
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdTcProviderPackage);
        Date date = DateTimeUtil.getCustomerDate();
        pdTcProviderPackage.setActTime(date);
        pdTcProviderPackage.setUpdTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        int num=pd141155Logic.saveDataStatus(pdTcProviderPackage);
        return num;
    }

    /**
     * 查询数据
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "checkSave",
            method = RequestMethod.POST)
    public @ResponseBody int checkSave(PdTcProviderPackageParam pdTcProviderPackage) {
        logger.debug("查询是否已经审核");
        int num= NumberConst.IntDef.INT_ZERO;
        PdTcProviderPackageParam list= pd141155Logic.queryOneData(StringUtil.toSafeString(pdTcProviderPackage.getTcProviderId()));
        if(NumberConst.IntDef.INT_ONE==list.getAuditStatus()){
            num=NumberConst.IntDef.INT_ONE;
        }
        return num;
    }

    /**
     * 查询品种
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "checkBreed",
            method = RequestMethod.POST)
    public @ResponseBody int checkBreed(String classesCode,String machiningCode,String breedName) {
        logger.debug("查询品种是否已在品种表存在");
        int num= pd141155Logic.findBreedName(classesCode,machiningCode,breedName);
        return num;
    }

    /**
     * 查询特征
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "checkFeature",
            method = RequestMethod.POST)
    public @ResponseBody int checkFeature(String classesCode,String machiningCode,String breedCode,String featureName) {
        logger.debug("查询特征是否已在特征表存在");
        int num= pd141155Logic.findFeatureName(classesCode,machiningCode,breedCode,featureName);
        return num;
    }

    /**
     * 查询净重
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "checkWeight",
            method = RequestMethod.POST)
    public @ResponseBody int checkWeight(String classesCode,String machiningCode,String breedCode,String featureCode,String weightName) {
        logger.debug("查询特征是否已在特征表存在");
        int num= pd141155Logic.findWeightName(classesCode,machiningCode,breedCode,featureCode,weightName);
        return num;
    }

    /**
     * 查询包装信息
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "checkNorms",
            method = RequestMethod.POST)
    public @ResponseBody int checkNorms(String classesCode,String machiningCode,String breedCode,String featureCode,String weightCode,String normsOut) {
        logger.debug("查询包装信息");
        int num= pd141155Logic.findStandardId(classesCode, machiningCode, breedCode, featureCode, weightCode, normsOut);
        return num;
    }
}
