package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.product.bean.PD141120Bean;
import com.msk.product.logic.PD141120Logic;
import com.msk.product.logic.ProductLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品价盘维护
 *
 * @author gyh
 */
@Controller
@RequestMapping("PD141120")
public class PD141120Controller extends BaseController {
    @Autowired
    private PD141120Logic pd141120Logic;
    @Autowired
    private ProductLogic productLogic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 实例化页面
     *
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model) {
        BaseParam param = new BaseParam();
        //产品类别信息
        List<PdClasses> pdClassess = productLogic.findPdClasses(param);
        model.addAttribute("pdClasses", pdClassess);
        //产品等级信息
        List<PdGrade> pdGrades = productLogic.findPdGrade(param);
        model.addAttribute("pdGrades", pdGrades);
        //产品价盘等级
//        List<PdOrderlevel> pdOrderlevelList = this.pd141120Logic.findPdOrderlevel(param);
//        model.addAttribute("pdOrderlevelList", pdOrderlevelList);
        //物流区域
        /*modify by daiyoucheng start at 2016/6/20*/
        //List<MdLogisticsArea> commLogisticsAreas = this.pd141120Logic.findLogisticsArea(param);
          List<LgcsAreaBean> commLogisticsAreas = this.pd141120Logic.findLogisticsArea(param);
        /*modify by daiyoucheng end at 2016/6/20*/
        model.addAttribute("commLogisticsAreas", commLogisticsAreas);
        PD141120Bean bean = new PD141120Bean();
        bean.setWayCode("01");
        model.addAttribute("pdPriceprdLogiarea", bean);
        model.addAttribute("pdWayInfo", this.pd141120Logic.findPdWayInfo(param));
        return "pd/PD141120";
    }

    /**
     * 实例化页面
     *
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "initDetail",
            method = RequestMethod.POST)
    public String initDetail(Model model, BaseParam param) {
        //产品价盘等级
        List<PdOrderlevel> pdOrderlevelList = this.pd141120Logic.findPdOrderlevel(param);
        model.addAttribute("pdOrderlevelList", pdOrderlevelList);
        return "pd/PD14112001";
    }


    /**
     * 查询机器信息
     *
     * @param param param
     * @return
     */
    @RequestMapping(value = "findMachining", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdMachining> findMachining(BaseParam param) {

        return this.productLogic.findPdMachining(param);
    }


    /**
     * 查询品种信息
     *
     * @param param param
     * @return
     */
    @RequestMapping(value = "findBreed", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdBreed> findBreed(BaseParam param) {
        return this.productLogic.findPdBreed(param);
    }

    /**
     * 查询特征信息
     *
     * @param param param
     * @return 特征信息
     */
    @RequestMapping(value = "findFeature", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdFeature> findFeature(BaseParam param) {
        return this.productLogic.findPdFeature(param);
    }

    /**
     * 查询包装净重信息
     *
     * @param param param
     * @return 包装净重信息
     */
    @RequestMapping(value = "findPdWeight", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdWeight> findPdWeight(BaseParam param) {
        return this.productLogic.findPdWeight(param);
    }


    /**
     * 开始新周期价盘
     *
     * @return 结果
     * @author gyh
     */
    @RequestMapping(value = "newPriceprd", method = RequestMethod.POST)
    public @ResponseBody String newPriceprd() {
        pd141120Logic.newPriceprd();
        return "1";
    }

    /**
     * 查询包装信息
     *
     * @param param param
     * @return 包装信息
     */
    @RequestMapping(value = "findNorms", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdNormsStd> findNorms(BaseParam param) {
        return this.productLogic.findPdNormsStd(param);
    }

    /**
     * 保存价盘信息
     *
     * @param param param
     * @param model model
     * @return 页面
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public
    @ResponseBody
    String save(PD141120Bean param, Model model) {
        //周期
        String[] pricecycleDates = param.getPricecycleDate().split("-");
        String pricecycleDate = pricecycleDates[0] + pricecycleDates[1];
        param.setPricecyclePeriod(pricecycleDate + param.getPricecycle());
        //产品编码:一级分类+二级分类+品种+特征+净重
        param.setPdtMixcode(param.getClassesCode() + param.getMachiningCode() + param.getBreedCode() + param.getFeatureCode() + param.getWeightCode());
        //查询产品价盘是否存在
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("classesCode", param.getClassesCode());
        baseParam.setFilter("machiningCode", param.getMachiningCode());
        baseParam.setFilter("breedCode", param.getBreedCode());
        baseParam.setFilter("featureCode", param.getFeatureCode());
        baseParam.setFilter("weightCode", param.getWeightCode());
        baseParam.setFilter("gradeCode", param.getGradeCode());
        baseParam.setFilter("logiareaCode", param.getLogiareaCode());
        baseParam.setFilter("pricecyclePeriod", param.getPricecyclePeriod());
        int rs = this.pd141120Logic.getPageCount(baseParam);
        if (rs > 0) {
            throw new BusinessException("该产品已经存在价盘信息，不能重复生成！");
        }

        String[] orderlevelCodeArray = param.getOrderlevelCodeArray();//订单等级编码
        String[] orderlevelNameArray = param.getOrderlevelNameArray();//订单等级名称
        BigDecimal[] priceofkgArray = param.getPriceofkgArray();//公斤价
        BigDecimal[] pricePercentArray = param.getPricePercentArray();//报价平衡系数数组
        String[] boxCntArray = param.getBoxCntArray();//箱数范围数组
        Integer[] boxCntminArray = param.getBoxCntminArray();//箱数范围下限数组
        Integer[] boxCntmaxArray = param.getBoxCntmaxArray();//箱数范围上限数组
        for (int i = 0; i < orderlevelCodeArray.length; i++) {
            //主键
            param.setPricecycleId(commonLogic.maxId("PD_PRICEPRD_LOGIAREA", "PRICECYCLE_ID"));
            //等级名称
            param.setOrderlevelName(orderlevelNameArray[i]);
            //等级编号
            param.setOrderlevelCode(orderlevelCodeArray[i]);
            //公斤价
            param.setPriceofkg(priceofkgArray[i]);
            //箱价
            param.setPriceofbox(param.getNetweight().multiply(priceofkgArray[i]));
            this.setCommonParam(param);
            param.setDelFlg("0");
            param.setVer(1);
            param.setPricePercent(pricePercentArray[i]);
            param.setBoxCnt(boxCntArray[i]);
            param.setBoxCntmin(boxCntminArray[i]);
            param.setBoxCntmax(boxCntmaxArray[i]);
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            param.setCrtTime(new Date());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
            this.pd141120Logic.save(param);
            this.pd141120Logic.savePdLogiareaOrderlevel(param);
        }
        return "1";
    }

}
