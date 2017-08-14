package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdTncStdDiscussProvider;
import com.msk.core.entity.SlPdTncStdNew;
import com.msk.core.entity.SlProduct;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductStdResult;
import com.msk.product.bean.TncStdBean;
import com.msk.seller.bean.SL241116Bean;
import com.msk.seller.bean.SL241117Bean;
import com.msk.seller.bean.SL241117Param;
import com.msk.seller.logic.ISL231109RsLogic;
import com.msk.seller.logic.SL241117Logic;
import com.msk.seller.logic.Sl241116Logic;
import com.msk.seller.utils.ISLRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * SL241117Controller
 *
 * @author gyh
 */
@Controller
@RequestMapping("SL241117")
public class SL241117Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SL241117Controller.class);

    @Autowired
    private SL241117Logic sl241117Logic;
    @Autowired
    private Sl241116Logic sl241116Logic;
    @Autowired
    private ISL231109RsLogic isl231109RsLogic;

    /**
     * Deprecated by xia_xiaojie on 2016/6/28.
     *
     * 初始化卖家产品加工质量标准设置
     *
     * @param sl241116Bean sl241116Bean
     * @param model model
     * @return 加工质量标准页面
     * @author gyh
     */
    /*
     * @RequestMapping(value = "init", method = RequestMethod.POST)
     * public String init(SL241116Bean sl241116Bean, Model model) {
     * logger.debug("卖家产品加工质量标准页面初始化");
     * model.addAttribute("sl241116Bean", sl241116Bean);
     * BaseParam param = new BaseParam();
     * param.setFilter("classesCode", sl241116Bean.getPdClassesCode());
     * param.setFilter("machiningCode", sl241116Bean.getMachiningCode());
     * param.setFilter("breedCode", sl241116Bean.getPdBreedCode());
     * param.setFilter("featureCode", sl241116Bean.getPdFeatureCode());
     * param.setFilter("slCode", sl241116Bean.getSlCode());
     * param.getFilterMap().put("slPdId", sl241116Bean.getSlPdId());
     * List<SL241117Bean> sl241117Beans;
     * if (isDebug) {
     * sl241117Beans = sl241117Logic.getTncStdTestData();
     * } else {
     * sl241117Beans = sl241117Logic.getTncStd(param);
     * if (CollectionUtils.isEmpty(sl241117Beans) || sl241117Beans.size() < 1) {
     * param.getFilterMap().remove("featureCode");
     * sl241117Beans = sl241117Logic.getTncStd(param);
     * if (CollectionUtils.isEmpty(sl241117Beans) || sl241117Beans.size() < 1) {
     * throw new BusinessException("该产品不存在加工质量标准！");
     * }
     * }
     * }
     * model.addAttribute("sL241117Beans", sl241117Beans);
     * return "sl/SL241117";
     * }
     */

    /**
     * 卖家产品加工质量标准页面初始化。替代旧init()。
     * Created by xia_xiaojie on 2016/6/28.
     * 1、通过接口取产品加工标准信息 2、本地补全同意状态
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(SL241116Bean sl241116Bean, Model model) {
        logger.debug("卖家产品加工质量标准页面初始化");
        PDInfoParam param = new PDInfoParam();
        param.setBreedCode(sl241116Bean.getPdBreedCode());
        param.setMachiningCode(sl241116Bean.getMachiningCode());
        param.setClassesCode(sl241116Bean.getPdClassesCode());
        param.setFeatureCode(sl241116Bean.getPdFeatureCode());
        param.setType(NumberConst.IntDef.INT_EIGHT);
        RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(param);
        List<TncStdBean> tncStdList = responce.getResult().getTncStdList();
        if (CollectionUtils.isEmpty(tncStdList)) {
            throw new BusinessException("对不起，没有查询到记录！");
        }
        // 数据信息补全
        List<SL241117Bean> sl241117Beans = sl241117Logic.completeTncDate(responce.getResult(), sl241116Bean.getSlCode(),
            sl241116Bean.getSlPdId());
        model.addAttribute("sl241116Bean", sl241116Bean);
        model.addAttribute("sl241117Beans", sl241117Beans);
        return "sl/SL241117";
    }

    /**
     * 保存标准值数据
     *
     * @param sl241117Param sl241117Param
     * @param model model
     * @return 实例化
     * @author gyh
     */
    @RequestMapping(value = "save",
        method = RequestMethod.POST)
    public @ResponseBody String save(SL241117Param sl241117Param, Model model) {
        // 修改产品的加工质量等级
        SlProduct slProduct = new SlProduct();
        slProduct.setSlPdId(sl241117Param.getSlPdId());
        slProduct.setSlCode(sl241117Param.getSlCode());
        slProduct.setSlTncGradeCode(Integer.parseInt(sl241117Param.getAgreeFlg()));
        slProduct.setCrtId(getLoginUser().getEmplId());
        slProduct.setUpdId(getLoginUser().getEmplId());
        super.setCommonParam(slProduct);
        // 产品加工质量标准维护数据获取
        List<SlPdTncStdNew> slPdTncStdNewList = new ArrayList<SlPdTncStdNew>();
        // 不同意的处理方式
        List<PdTncStdDiscussProvider> providerList = new ArrayList<PdTncStdDiscussProvider>();
        String[] contentArray = sl241117Param.getContentArray();
        String[] checkArray = sl241117Param.getCheckArray();
        String[] pdTncTdItemIdArray = sl241117Param.getPdTncStdItemIdArray();
        if (pdTncTdItemIdArray != null && checkArray != null && contentArray != null) {
            for (int i = 0; i < pdTncTdItemIdArray.length; i++) {
                SlPdTncStdNew stdNew = new SlPdTncStdNew();
                BeanUtils.copyProperties(sl241117Param, stdNew);
                super.setCommonParam(stdNew);
                stdNew.setAgreeFlg(checkArray[i]);
                stdNew.setStdVal(contentArray[i]);
                stdNew.setStdItemId(pdTncTdItemIdArray[i]);
                stdNew.setUpdId(getLoginUser().getEmplId());
                slPdTncStdNewList.add(stdNew);
                if ("0".equals(checkArray[i])) {
                    PdTncStdDiscussProvider provide = new PdTncStdDiscussProvider();
                    provide.setStandardId(sl241117Param.getStandardId().longValue());
                    provide.setSlPdId(sl241117Param.getSlPdId());
                    provide.setTncStdItemId(sl241117Param.getStdItemId());
                    provide.setTncStdVal(contentArray[i]);
                    provide.setProviderCode(sl241117Param.getSlCode());
                    provide.setProviderName(sl241117Param.getSlShowName());
                    provide.setDiscussStatus(0);
                    provide.setCrtId(getLoginUser().getEmplId());
                    provide.setCrtTime(DateTimeUtil.getCustomerDate());
                    super.setCommonParam(provide);
                    providerList.add(provide);
                    slProduct.setSlTncGradeCode(5);
                }
            }
        }
        // 修改产品的加工质量等级
        sl241117Logic.modifySlProduct(slProduct);
        // 维护加工质量标准
        sl241117Logic.findSlPdTncStdList(slPdTncStdNewList);
        // 调用接口 批量保存不同意
        int result = sl241117Logic.saveNotAgree(providerList);
        if (result != providerList.size()) {
            logger.error("调用接口saveTncProvider没有成功插入所有数据！");
        }
        // 全部同意判断其他标准是否符合要求 OLD单个调用
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.getFilterMap().put("slPdId", sl241117Param.getSlPdId());
        basePageParam.getFilterMap().put("slCode", sl241117Param.getSlCode());
        String checkRs = sl241117Logic.checkAgree(basePageParam);
        if (!"1".equals(checkRs)) {
            slProduct.setStatus("2");
            this.sl241116Logic.upSlPdStatus(slProduct);
        }
        return "1";
        
    }

}
