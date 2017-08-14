package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdStandard;
import com.msk.core.entity.SlPdTncStdOther;
import com.msk.core.entity.SlProduct;
import com.msk.seller.bean.*;
import com.msk.seller.logic.*;
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

import java.util.ArrayList;
import java.util.List;

/**
 * SL241122Controller
 * 其他标准录入
 *
 * @author gyh
 */
@Controller
@RequestMapping("SL241122")
public class SL241122Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SL241122Controller.class);

    @Autowired
    private SL241117Logic sl241117Logic;
    @Autowired
    private ISL231109RsLogic isl231109Logic;
    @Autowired
    private ProductLogic productLogic;
    @Autowired
    private Sl241116Logic sl241116Logic;
    @Autowired
    private SL241122Logic sl241122Logic;


    /**
     * 初始化卖家产品加工质量标准设置
     *
     * @param sl241116Bean sl241116Bean
     * @param model        model
     * @return 其他标准页面
     * @author gyh
     */
    @RequestMapping(value = "init/{type}", method = RequestMethod.POST)
    public String init(SL241116Bean sl241116Bean, Model model,@PathVariable(value = "type")String type) {
        logger.debug("卖家产品其他标准页面初始化");
        model.addAttribute("sl241116Bean", sl241116Bean);
        model.addAttribute("type",type);
        //接收参数
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", sl241116Bean.getPdClassesCode());
        param.setFilter("machiningCode", sl241116Bean.getMachiningCode());
        param.setFilter("breedCode", sl241116Bean.getPdBreedCode());
        param.setFilter("slCode", sl241116Bean.getSlCode());
        //List<String> slPdIdlists = new ArrayList<>();
        //slPdIdlists.add(StringUtil.toSafeString(sl241116Bean.getSlPdId()));
        //param.getFilterMap().put("slPdIdlists", slPdIdlists);
        param.getFilterMap().put("slPdId", sl241116Bean.getSlPdId());
        //1.卖家产品其他标准维护，需要传入standardId，2.加工技术和加工质量标准页面的查看卖家产品其他标准，不需要传入standardId
        if("1".equals(type)){
           List<PdStandard> pdStandards = productLogic.findPdStandard(param);
            if (CollectionUtils.isEmpty(pdStandards) || pdStandards.size() < 1) {
                throw new BusinessException("对不起，没有查询到记录！");
            }
            param.getFilterMap().put("standardId", pdStandards.get(0).getStandardId());
            model.addAttribute("standardId", pdStandards.get(0).getStandardId());
        }
//        param.getFilterMap().put("stdFlg", 1);
//        List<SL241122Bean> sl241122Beans = isl231109Logic.getOrgStdInfo(param);//原种种源标准指标
//        model.addAttribute("sl241122Beans", sl241122Beans);
//        param.getFilterMap().put("stdFlg", 2);
//        List<SL241123Bean> sl241123Beans = isl231109Logic.getFedStdInfo(param);//原种饲养标准指标
//        model.addAttribute("sl241123Beans", sl241123Beans);
//        param.getFilterMap().put("stdFlg", 3);
//        List<SL241124Bean> sl241124Beans = isl231109Logic.getGnqStdInfo(param);//通用质量标准
//        model.addAttribute("sl241124Beans", sl241124Beans);
//        param.getFilterMap().put("stdFlg", 4);
//        List<SL241125Bean> sl241125Beans = isl231109Logic.getTspStdInfo(param);//储存运输标准
//        model.addAttribute("sl241125Beans", sl241125Beans);
//        param.getFilterMap().put("stdFlg", 5);
//        List<SL241126Bean> sl241126Beans = isl231109Logic.getSftStdInfo(param);//安全标准
//        model.addAttribute("sl241126Beans", sl241126Beans);

        int mun =  0;

        param.getFilterMap().put("stdFlg", 1);
        List<SL241122Bean> sl241122Beans = isl231109Logic.getStdInfo(new SL241122Bean(),param);//原种种源标准指标
        mun = mun + sl241122Beans.size();
        model.addAttribute("sl241122Beans", sl241122Beans);
        param.getFilterMap().put("stdFlg", 2);
        List<SL241123Bean> sl241123Beans = isl231109Logic.getStdInfo(new SL241123Bean(),param);//原种饲养标准指标
        mun = mun + sl241123Beans.size();
        model.addAttribute("sl241123Beans", sl241123Beans);
        param.getFilterMap().put("stdFlg", 3);
        List<SL241124Bean> sl241124Beans = isl231109Logic.getStdInfo(new SL241124Bean(),param);//通用质量标准
        mun = mun + sl241124Beans.size();
        model.addAttribute("sl241124Beans", sl241124Beans);
        param.getFilterMap().put("stdFlg", 4);
        List<SL241125Bean> sl241125Beans = isl231109Logic.getStdInfo(new SL241125Bean(),param);//储存运输标准
        mun = mun + sl241125Beans.size();
        model.addAttribute("sl241125Beans", sl241125Beans);
        param.getFilterMap().put("stdFlg", 5);
        List<SL241126Bean> sl241126Beans = isl231109Logic.getStdInfo(new SL241126Bean(),param);//安全标准
        mun = mun + sl241126Beans.size();
        model.addAttribute("sl241126Beans", sl241126Beans);

        if(0 == mun){
            throw new BusinessException("对不起，没有查询到记录！");
        }

        return "sl/SL241122";
    }

    /**
     * 保存标准值数据
     *
     * @param sl241122Param sl241122Param
     * @param model         model
     * @return 实例化
     * @author gyh
     */
//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    String save(SL241122Param sl241122Param, Model model) {
//
//
//        String[] orgStdItemIdArray = sl241122Param.getOrgStdItemIdArray();//原种种源标准指标
//        String[] fedStdItemIdArray = sl241122Param.getFedStdItemIdArray();//原种饲养标准指标
//        String[] sftItemIdArray = sl241122Param.getSftItemIdArray();//安全标准
//        String[] tspItemIdArray = sl241122Param.getTspItemIdArray();//储存运输标准
//        String[] gnqItemIdArray = sl241122Param.getGnqItemIdArray();//通用质量标准
//         String[] gnqValArray=sl241122Param.getGnqValArray();//同意标志
//         String[] orgValArray=sl241122Param.getOrgValArray();//同意标志
//         String[] fedValArray=sl241122Param.getFedValArray();//同意标志
//         String[] sftValArray=sl241122Param.getSftValArray();//同意标志
//         String[] tspValArray=sl241122Param.getTspValArray();//同意标志
//        SlPdTncStdOther other = new SlPdTncStdOther();
//        other.setSlPdId(sl241122Param.getSlPdId());
//        other.setSlCode(sl241122Param.getSlCode());
//        other.setStandardId(sl241122Param.getStandardId());
//        other.setDelFlg("0");
//        super.setCommonParam(other);
//        BasePageParam param = new BasePageParam();
//        param.setPaging(false);
//        param.setFilter("slCode", sl241122Param.getSlCode());
//        param.getFilterMap().put("slPdId", sl241122Param.getSlPdId());
//        param.getFilterMap().put("standardId", sl241122Param.getStandardId());
//        param.getFilterMap().put("stdFlag", 1);
//        if(orgStdItemIdArray!=null&&orgStdItemIdArray.length>0){
//            for (int i = 0; i < orgStdItemIdArray.length; i++) {
//                param.setFilter("tncStdItemId", orgStdItemIdArray[i]);
//                other.setAgreeFlg(orgValArray[i]);
//                List<SlPdTncStdOther> others = isl231106Logic.findSlPdTncStd(param);
//                other.setStdFlag(1);
//                other.setTncStdItemId(orgStdItemIdArray[i]);
//                if (CollectionUtils.isEmpty(others) || others.size() < 1) {
//                    other.setVer(1);
//                    isl231106Logic.insertSlPdOrgStd(other);
//                } else {
//                    SlPdTncStdOther other1=others.get(0);
//                    if(other1.getVer()!=null){
//                        other.setVer(other1.getVer()+1);
//                    }else{
//                        other.setVer(1);
//                    }
//                    isl231106Logic.modifySlPdOrgStd(other);
//                }
//            }
//        }
//        param.getFilterMap().put("stdFlag", 2);
//        if(fedStdItemIdArray!=null&&fedStdItemIdArray.length>0){
//            for (int i = 0; i < fedStdItemIdArray.length; i++) {
//                param.setFilter("tncStdItemId", fedStdItemIdArray[i]);
//                List<SlPdTncStdOther> others = isl231106Logic.findSlPdTncStd(param);
//                other.setAgreeFlg(fedValArray[i]);
//                other.setStdFlag(2);
//                other.setTncStdItemId(fedStdItemIdArray[i]);
//                if (CollectionUtils.isEmpty(others) || others.size() < 1) {
//                    other.setVer(1);
//                    isl231106Logic.insertSlPdOrgStd(other);
//                } else {
//                    SlPdTncStdOther other1=others.get(0);
//                    if(other1.getVer()!=null){
//                        other.setVer(other1.getVer()+1);
//                    }else{
//                        other.setVer(1);
//                    }
//                    isl231106Logic.modifySlPdOrgStd(other);
//                }
//            }
//        }
//        param.getFilterMap().put("stdFlag", 5);
//        if(sftItemIdArray!=null&&sftItemIdArray.length>0){
//            for (int i = 0; i < sftItemIdArray.length; i++) {
//                param.setFilter("tncStdItemId", sftItemIdArray[i]);
//
//                List<SlPdTncStdOther> others = isl231106Logic.findSlPdTncStd(param);
//                other.setAgreeFlg(sftValArray[i]);
//                other.setStdFlag(5);
//                other.setTncStdItemId(sftItemIdArray[i]);
//                if (CollectionUtils.isEmpty(others) || others.size() < 1) {
//                    other.setVer(1);
//                    isl231106Logic.insertSlPdOrgStd(other);
//                } else {
//                    SlPdTncStdOther other1=others.get(0);
//                    if(other1.getVer()!=null){
//                        other.setVer(other1.getVer()+1);
//                    }else{
//                        other.setVer(1);
//                    }
//                    isl231106Logic.modifySlPdOrgStd(other);
//                }
//            }
//        }
//        param.getFilterMap().put("stdFlag",4);
//        if(tspItemIdArray!=null&&tspItemIdArray.length>0){
//            for (int i = 0; i < tspItemIdArray.length; i++) {
//                param.setFilter("tncStdItemId", tspItemIdArray[i]);
//                List<SlPdTncStdOther> others = isl231106Logic.findSlPdTncStd(param);
//                other.setAgreeFlg(tspValArray[i]);
//                other.setStdFlag(4);
//                other.setTncStdItemId(tspItemIdArray[i]);
//                if (CollectionUtils.isEmpty(others) || others.size() < 1) {
//                    other.setVer(1);
//                    isl231106Logic.insertSlPdOrgStd(other);
//                } else {
//                    SlPdTncStdOther other1=others.get(0);
//                    if(other1.getVer()!=null){
//                        other.setVer(other1.getVer()+1);
//                    }else{
//                        other.setVer(1);
//                    }
//                    isl231106Logic.modifySlPdOrgStd(other);
//                }
//            }
//        }
//        param.getFilterMap().put("stdFlag", 3);
//        if(gnqItemIdArray!=null&&gnqItemIdArray.length>0){
//            for (int i = 0; i < gnqItemIdArray.length; i++) {
//                param.setFilter("tncStdItemId", gnqItemIdArray[i]);
//                List<SlPdTncStdOther> others = isl231106Logic.findSlPdTncStd(param);
//                other.setAgreeFlg(gnqValArray[i]);
//                other.setStdFlag(3);
//                other.setTncStdItemId(gnqItemIdArray[i]);
//                if (CollectionUtils.isEmpty(others) || others.size() < 1) {
//                    other.setVer(1);
//                    isl231106Logic.insertSlPdOrgStd(other);
//                } else {
//                    SlPdTncStdOther other1=others.get(0);
//                    if(other1.getVer()!=null){
//                        other.setVer(other1.getVer()+1);
//                    }else{
//                        other.setVer(1);
//                    }
//                    isl231106Logic.modifySlPdOrgStd(other);
//                }
//            }
//        }
//        SlProduct slProduct = new SlProduct();
//        slProduct.setSlPdId(sl241122Param.getSlPdId());
//        slProduct.setSlCode(sl241122Param.getSlCode());
//        super.setCommonParam(slProduct);
//        String checkRs=sl241117Logic.checkAgree(param);
//        if ("1".equals(checkRs)) {
//            //修改卖家产品为试销
////            slProduct.setStatus("4");
////            this.sl241116Logic.upSlPdStatus(slProduct);
//        } else {
//            //修改卖家产品为论证中
//            slProduct.setStatus("2");
//            //更改加工质量等级
//            this.sl241116Logic.upSlPdStatus(slProduct);
//        }
//        return "1";
//    }

    /**
     * 保存标准值数据
     *
     * @param sl241122Param sl241122Param
     * @param model         model
     * @return 实例化
     * @author 管忠恒
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(SL241122Param sl241122Param, Model model) {
        List<SlPdTncStdOther> otherList = new ArrayList<SlPdTncStdOther>();
        String[] orgStdItemIdArray = sl241122Param.getOrgStdItemIdArray();//原种种源标准指标
        String[] fedStdItemIdArray = sl241122Param.getFedStdItemIdArray();//原种饲养标准指标
        String[] sftItemIdArray = sl241122Param.getSftItemIdArray();//安全标准
        String[] tspItemIdArray = sl241122Param.getTspItemIdArray();//储存运输标准
        String[] gnqItemIdArray = sl241122Param.getGnqItemIdArray();//通用质量标准
        String[] gnqValArray=sl241122Param.getGnqValArray();//同意标志
        String[] orgValArray=sl241122Param.getOrgValArray();//同意标志
        String[] fedValArray=sl241122Param.getFedValArray();//同意标志
        String[] sftValArray=sl241122Param.getSftValArray();//同意标志
        String[] tspValArray=sl241122Param.getTspValArray();//同意标志
        SlPdTncStdOther other = null;
        if(orgStdItemIdArray!=null&&orgStdItemIdArray.length>0){
            for (int i = 0; i < orgStdItemIdArray.length; i++) {
                other =  new SlPdTncStdOther();
                this.setComParams(other, sl241122Param);
                other.setTncStdItemId(orgStdItemIdArray[i].split("_")[0]);
                String standId = StringUtil.toSafeString(orgStdItemIdArray[i].split("_")[1]);
                other.setStandardId(Integer.parseInt(standId));
                other.setAgreeFlg(orgValArray[i]);
                other.setStdFlag(1);
                otherList.add(other);
            }
        }
        if(fedStdItemIdArray!=null&&fedStdItemIdArray.length>0){
            for (int i = 0; i < fedStdItemIdArray.length; i++) {
                other =  new SlPdTncStdOther();
                this.setComParams(other, sl241122Param);
                other.setTncStdItemId(fedStdItemIdArray[i].split("_")[0]);
                String standId = StringUtil.toSafeString(fedStdItemIdArray[i].split("_")[1]);
                other.setStandardId(Integer.parseInt(standId));
                other.setAgreeFlg(fedValArray[i]);
                other.setStdFlag(2);
                otherList.add(other);
            }
        }
        if(sftItemIdArray!=null&&sftItemIdArray.length>0){
            for (int i = 0; i < sftItemIdArray.length; i++) {
                other =  new SlPdTncStdOther();
                this.setComParams(other, sl241122Param);
                other.setTncStdItemId(sftItemIdArray[i].split("_")[0]);
                String standId = StringUtil.toSafeString(sftItemIdArray[i].split("_")[1]);
                other.setStandardId(Integer.parseInt(standId));
                other.setAgreeFlg(sftValArray[i]);
                other.setStdFlag(5);
                otherList.add(other);
            }
        }
        if(tspItemIdArray!=null&&tspItemIdArray.length>0){
            for (int i = 0; i < tspItemIdArray.length; i++) {
                other =  new SlPdTncStdOther();
                this.setComParams(other, sl241122Param);
                other.setTncStdItemId(tspItemIdArray[i].split("_")[0]);
                String standId = StringUtil.toSafeString(tspItemIdArray[i].split("_")[1]);
                other.setStandardId(Integer.parseInt(standId));
                other.setAgreeFlg(tspValArray[i]);
                other.setStdFlag(4);
                otherList.add(other);
            }
        }
        if(gnqItemIdArray!=null&&gnqItemIdArray.length>0){
            for (int i = 0; i < gnqItemIdArray.length; i++) {
                other =  new SlPdTncStdOther();
                this.setComParams(other, sl241122Param);
                other.setTncStdItemId(gnqItemIdArray[i].split("_")[0]);
                String standId = StringUtil.toSafeString(gnqItemIdArray[i].split("_")[1]);
                other.setStandardId(Integer.parseInt(standId));
                other.setAgreeFlg(gnqValArray[i]);
                other.setStdFlag(3);
                otherList.add(other);
            }
        }
        // 批量处理标准值数据信息
        sl241122Logic.batchProcessLogic(otherList,getLoginUser().getEmplId());
        // 校验数据
        List<SlProduct> list = new ArrayList<SlProduct>();
        SlProduct slProduct = new SlProduct();
        slProduct.setSlPdId(sl241122Param.getSlPdId());
        slProduct.setSlCode(sl241122Param.getSlCode());
        super.setCommonParam(slProduct);
        list.add(slProduct);
        List<SlProduct> errParamList = sl241117Logic.checkAgreeNew(list);
        for(SlProduct product:errParamList){
            //修改卖家产品为论证中
            product.setStatus("2");
            product.setUpdId(getLoginUser().getEmplId());
        }
        // 修改卖家产品状态(方法暂时未做修改)
        this.sl241116Logic.upSlPdStatus(errParamList);
        return "1";
    }

    /**
     * 共通的赋值方式
     */
    public SlPdTncStdOther setComParams(SlPdTncStdOther slPdTncStdOther,SL241122Param sl241122Param){
        slPdTncStdOther.setSlPdId(sl241122Param.getSlPdId());
        slPdTncStdOther.setSlCode(sl241122Param.getSlCode());
        slPdTncStdOther.setStandardId(sl241122Param.getStandardId());
        slPdTncStdOther.setDelFlg("0");
        super.setCommonParam(slPdTncStdOther);
        return slPdTncStdOther;
    }
}
