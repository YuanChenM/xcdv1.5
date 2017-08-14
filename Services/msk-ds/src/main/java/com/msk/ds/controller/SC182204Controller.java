package com.msk.ds.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.ds.bean.ISL231101RsResult;
import com.msk.ds.bean.SC182204Bean;
import com.msk.ds.bean.SC182204Param;
import com.msk.ds.logic.SC182204Logic;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * SC182204Controller
 *
 * @author yi_qixiang
 *
 */
@Controller
@RequestMapping("SC182204")
public class SC182204Controller extends BaseController {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC182204Controller.class);

    @Autowired
    private SC182204Logic sc182204Logic;

    /*@Autowired
    private ProductLogic productLogic;*/

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(SC182204Param sc182204Param,Model model) {
        logger.info("产品批次明细生成画面初始化");
        this.setCommonParam(sc182204Param);
        String halfPeriod = "";
        String halfCode = "";
        /**产品库存类型*/
        String pdStockType = "2";
        String distMonth = "";

        SC182204Bean sc182204Bean = new SC182204Bean();
        sc182204Bean = sc182204Logic.getSC182204Bean(sc182204Param);
        //如果是供应商则只允许其选择自己
        if(CommCodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(sc182204Param.getUserType())){
            ISL231193RsParam param = new ISL231193RsParam();
            param.setSlAccount(sc182204Param.getCrtId());
            ISL231193RsResult rs = RestUtil.queryslEpData(param);
            SC182204Bean bean = new SC182204Bean();
            bean.setSuppCode(rs.getSlCode());
            bean.setSuppName(rs.getEpName());
            List<SC182204Bean> suppList =new ArrayList<>();
            suppList.add(bean);
            sc182204Bean.setSupplyList(suppList);
            sc182204Param.setSuppCode(bean.getSuppCode());
        }
        sc182204Param.setPdStockType(pdStockType);
        List<SC182204Bean> returnList = new ArrayList<SC182204Bean>();
        List<SC182204Bean> stockActualList = new ArrayList<SC182204Bean>();

        try {
            halfCode = calculatHalfPeriod().substring(4,5);
            distMonth = "20"+calculatHalfPeriod().substring(0,4);  //根据系统时间计算
            sc182204Param.setHalfCode(halfCode);
            sc182204Param.setDistMonth(distMonth);
            //Add for #2700 at 2016/09/21 by likai Start
//            sc182204Param.setLgcsCode(sc182204Bean.getLgcsCode());
//            sc182204Param.setSuppCode(sc182204Bean.getSuppCode());
            //Add for #2700 at 2016/09/21 by likai End
            returnList = sc182204Logic.getStockActual(sc182204Param);
                stockActualList = getStockActualList(returnList);
            halfPeriod = calculatHalfPeriod();
        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("stockActualList",stockActualList);
        model.addAttribute("halfPeriod",halfPeriod);
        model.addAttribute("sc182204Bean",sc182204Bean);

        return "ds/SC182204";
    }

    /**
     * 保存变更
     * @param param
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody void save(SC182204Param param) {
        logger.info("保存产品批次明细");
        //Add for Bug#2547 at 2016/09/08 by li_kai1 Start
        this.setCommonParam(param);
        //Add for Bug#2547 at 2016/09/08 by li_kai1 End
        param.setHalfPeriod(calculatHalfPeriod());
        sc182204Logic.saveProductLot(param);
    }

    /**
     * 根据账号得到供应商和销售区域
     * @param
     * @return
     */
     public SC182204Bean getLgcsAndSupp(String slAccount) {
         SC182204Param sc182204Param = new SC182204Param();
         sc182204Param.setSlAccount(slAccount);
         return  sc182204Logic.getLgcsAndSupp(sc182204Param);
     }

    /**
     * 根据产品编号获得产品属性的list
     * @param returnList
     * @return
     */
    public List<SC182204Bean> getStockActualList(List<SC182204Bean> returnList) {
        logger.info("设置页面显示参数");
        List<SC182204Bean> stockACTUALList = new ArrayList<SC182204Bean>();
        for(int i=0;i<returnList.size();i++){
            SC182204Bean retrunSc182204Bean = (SC182204Bean)returnList.get(i);
            SC182204Bean sc182204Bean = new SC182204Bean();
//            ProductBean productBean = productLogic.getProductInfo(retrunSc182204Bean.getPdCode());
            PDInfoParam pdInfoParam = new PDInfoParam();
            pdInfoParam.setPdCode(retrunSc182204Bean.getPdCode());
            PDInfoResult productBean = RestUtil.getProductInfo(pdInfoParam);
//            PDInfoResult productBean = null;
//            if(pdInfoList!=null&&pdInfoList.size()>0){
//                productBean = pdInfoList.get(0);
//            }
            sc182204Bean.setLgcsCode(retrunSc182204Bean.getLgcsCode());
            sc182204Bean.setSuppCode(retrunSc182204Bean.getSuppCode());
            sc182204Bean.setProductBean(productBean);
            sc182204Bean.setSumNewActNum(retrunSc182204Bean.getSumNewActNum());
            String classesCode = "";
            String machiningCode = "";
            String breedCode = "";
            String featureCode = "";
            String gradeCode = "";
            String weightCode = "";
            String normsCode = "";
            String suppCode = "";
            String slCodeManufacture = "";
            String brandId = "";
            if(productBean!=null){
                if(productBean.getClassesCode()!=null){
                    classesCode = productBean.getClassesCode();
                }
                if(productBean.getMachiningCode()!=null){
                    machiningCode = productBean.getMachiningCode();
                }
                if(productBean.getBreedCode()!=null){
                    breedCode = productBean.getBreedCode();
                }
                if(productBean.getFeatureCode()!=null){
                    featureCode = productBean.getFeatureCode();
                }
                if(productBean.getGradeCode()!=null){
                    gradeCode = productBean.getGradeCode();
                }
                if(productBean.getWeightCode()!=null){
                    weightCode = productBean.getWeightCode();
                }
            }
            if(retrunSc182204Bean.getNormsCode()!=null){
                normsCode = retrunSc182204Bean.getNormsCode();
                if(normsCode.length()>2){
                    normsCode = normsCode.substring(1);
                }
            }
            if(retrunSc182204Bean.getSuppCode()!=null){
                suppCode = retrunSc182204Bean.getSuppCode();
            }
            //设置操作码
            sc182204Bean.setProLotNum("92" + suppCode +                  //卖家编号
                    "55555" +                                           //货号
                    calculatHalfPeriod());                               //批次号
            //设置打印页面
            if((retrunSc182204Bean.getSumNewActNum().intValue())==0){
                sc182204Bean.setProductPrintNum("0");
            }else{
                sc182204Bean.setProductPrintNum("1-"+retrunSc182204Bean.getSumNewActNum().intValue());
            }
            sc182204Bean.setNormsCode(retrunSc182204Bean.getNormsCode());
            sc182204Bean.setHalfPeriod(calculatHalfPeriod());
            SC182204Param param = new SC182204Param();
            param.setSuppCode(retrunSc182204Bean.getSuppCode());
            if(sc182204Logic.getManuFactureAndBrand(param)!=null){
                  if((sc182204Logic.getManuFactureAndBrand(param).getSlCodeManufacture())!=null){
                      slCodeManufacture = sc182204Logic.getManuFactureAndBrand(param).getSlCodeManufacture();
                  }
                if((sc182204Logic.getManuFactureAndBrand(param).getBrandId())!=null){
                    brandId = sc182204Logic.getManuFactureAndBrand(param).getBrandId();
                }
            }
            sc182204Bean.setReadProLotNum(suppCode + slCodeManufacture + brandId + classesCode
                    + machiningCode + breedCode + featureCode + gradeCode + weightCode
                    + normsCode + calculatHalfPeriod());
            SC182204Param sc182204Param = new SC182204Param();
            sc182204Param.setProLotNum("92" + suppCode +
                    "55555"+
                    calculatHalfPeriod());
            //判断是否新增明细，如果有则不添加
            if (sc182204Logic.getDsProductLot(sc182204Param)!=null){

            } else {
                stockACTUALList.add(sc182204Bean);
            }
        }
        return stockACTUALList;
    }

    /**
     * 得到半旬期
     * @param
     * @return
     */
    public String calculatHalfPeriod() {
//        String time="20160403";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String time= sdf.format(new Date());
        int dayNum = StringUtil.toInteger(time.substring(NumberConst.IntDef.INT_SIX, NumberConst.IntDef.INT_EIGHT));
        if(dayNum>=NumberConst.IntDef.INT_ONE && dayNum<=NumberConst.IntDef.INT_FIVE){
            return time.substring(2,6)+"3";
        }else if(dayNum>=NumberConst.IntDef.INT_SIX && dayNum<=NumberConst.IntDef.INT_TEN){
            return time.substring(2,6)+"4";
        }else if(dayNum>=NumberConst.IntDef.INT_ELEVEN && dayNum<=NumberConst.IntDef.INT_FIFTEEN){
            return time.substring(2,6)+"5";
        }else if(dayNum>=NumberConst.IntDef.INT_SIXTEEN&&dayNum<=NumberConst.IntDef.INT_TWENTY){
            return time.substring(2,6)+"6";
        }else if(dayNum>=NumberConst.IntDef.INT_TWENTY_ONE &&dayNum<=NumberConst.IntDef.INT_TWENTY_FIVE){
            Calendar c = Calendar.getInstance(); //获得当前时间
            c.add(Calendar.MONTH, 1);
            time = sdf.format(c.getTime());
            return time.substring(2,6)+"1";
        }else {
            Calendar c = Calendar.getInstance(); //获得当前时间
            c.add(Calendar.MONTH, 1);
            time = sdf.format(c.getTime());
            return time.substring(2,6)+"2";
        }

    }

    /**
     * 获取下拉框数据
     * @param sc182204Param sc182204Param
     * @return selectList
     */
    @RequestMapping(value = "selectChange",
            method = RequestMethod.POST)
    public @ResponseBody
    List<SC182204Bean> selectChange(SC182204Param sc182204Param) {
        logger.info("产品批次明细画面下拉框选择");
        List<SC182204Bean> selectList = new ArrayList<>();
            /*供应商*/
//        BaseParam param = new BaseParam();
//        param.setFilter("lgcsCode", sc182204Param.getLgcsCode());
        selectList = sc182204Logic.getSuppListByLgcs(sc182204Param);

        return selectList;
    }

}
