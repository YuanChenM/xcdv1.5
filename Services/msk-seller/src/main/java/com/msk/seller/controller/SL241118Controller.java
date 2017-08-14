package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdMctStdDiscussProvider;
import com.msk.core.entity.SlPdMctStdNew;
import com.msk.core.entity.SlProduct;
import com.msk.product.bean.MctStdBean;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductStdResult;
import com.msk.seller.bean.SL241116Bean;
import com.msk.seller.bean.SL241118Bean;
import com.msk.seller.bean.SL241118Param;
import com.msk.seller.logic.ISL231109RsLogic;
import com.msk.seller.logic.SL241117Logic;
import com.msk.seller.logic.SL241118Logic;
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
 * SL241118Controller
 *
 * @author gyh
 */
@Controller
@RequestMapping("SL241118")
public class SL241118Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SL241118Controller.class);

    @Autowired
    private SL241118Logic sl241118Logic;
    @Autowired
    private SL241117Logic sl241117Logic;
    @Autowired
    private Sl241116Logic sl241116Logic;

    /**
     * 卖家产品加工技术标准页面初始化。替代旧init()。
     * Created by xia_xiaojie on 2016/6/28.
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SL241116Bean sl241116Bean, Model model) {
        logger.debug("卖家产品加工技术标准页面初始化");
        PDInfoParam param = new PDInfoParam();
        param.setBreedCode(sl241116Bean.getPdBreedCode());
        param.setMachiningCode(sl241116Bean.getMachiningCode());
        param.setClassesCode(sl241116Bean.getPdClassesCode());
        param.setFeatureCode(sl241116Bean.getPdFeatureCode());
        param.setType(NumberConst.IntDef.INT_ONE);
        RsResponse<ProductStdResult> responce = ISLRestUtil.getPdProductStd(param);
        List<MctStdBean> mctStdList = responce.getResult().getMctStdList();

        if (CollectionUtils.isEmpty(mctStdList)) {
            throw new BusinessException("对不起，没有查询到记录！");
        }
        // 数据信息补全
        List<SL241118Bean> sl241118Beans = sl241118Logic.completeTncDate(responce.getResult(), sl241116Bean.getSlCode(),
                sl241116Bean.getSlPdId());

        model.addAttribute("sl241116Bean", sl241116Bean);
        model.addAttribute("sl241118Beans", sl241118Beans);
        return "sl/SL241118";
    }

    /**
     * 保存加工技术标准
     *
     * @param sl241118Param sl241118Param
     * @param model         model
     * @return 实例化
     * @author gyh
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(SL241118Param sl241118Param, Model model) {
        //修改产品的加工质量等级
        SlProduct slProduct = new SlProduct();
        slProduct.setSlPdId(sl241118Param.getSlPdId());
        slProduct.setSlCode(sl241118Param.getSlCode());
        slProduct.setSlQltGradeCode(Integer.parseInt(sl241118Param.getAgreeFlg()));
        slProduct.setCrtId(getLoginUser().getEmplId());
        slProduct.setUpdId(getLoginUser().getEmplId());
        slProduct.setUpdTime(DateTimeUtil.getCustomerDate());
        super.setCommonParam(slProduct);
        // 产品加工质量标准维护数据获取
        List<SlPdMctStdNew> slPdMctStdNewList = new ArrayList<SlPdMctStdNew>();
        // 不同意的处理方式
        List<PdMctStdDiscussProvider> needSaveProviders = new ArrayList<PdMctStdDiscussProvider>();
        String[] contentArray = sl241118Param.getContentArray();
        String[] checkArray = sl241118Param.getCheckArray();
        String[] pdMctStdItemIdArray = sl241118Param.getPdMctStdItemIdArray();
        if(pdMctStdItemIdArray != null && checkArray != null && contentArray != null){
            for(int i = 0; i < pdMctStdItemIdArray.length; i++){
                SlPdMctStdNew stdNew = new SlPdMctStdNew();
                BeanUtils.copyProperties(sl241118Param,stdNew);
                super.setCommonParam(stdNew);
                stdNew.setAgreeFlg(checkArray[i]);
                stdNew.setStdVal(contentArray[i]);
                stdNew.setStdItemId(pdMctStdItemIdArray[i]);
                stdNew.setCrtId(getLoginUser().getEmplId());
                stdNew.setUpdId(getLoginUser().getEmplId());
                if ("0".equals(checkArray[i])) {
                    PdMctStdDiscussProvider provide = new PdMctStdDiscussProvider();
                    provide.setStandardId(sl241118Param.getStandardId().longValue());
                    provide.setSlPdId(sl241118Param.getSlPdId());
                    provide.setMctStdItemId(sl241118Param.getStdItemId());
                    provide.setMctStdVal(contentArray[i]);
                    provide.setProviderCode(sl241118Param.getSlCode());
                    provide.setProviderName(sl241118Param.getSlShowName());
                    provide.setDiscussStatus(0);
                    //stdNew.setStdItemId(pdMctStdItemIdArray[i]);
                    provide.setVer(1);
//                 provide.setKeyId(commonLogic.maxId("PD_MCT_STD_DISCUSS_PROVIDER", "KEY_ID"));
                    provide.setDelFlg("0");
                    super.setCommonParam(provide);
//                 sl241118Logic.saveNotAgree(provide);
                    provide.setCrtId(getLoginUser().getEmplId());
                    provide.setCrtTime(DateTimeUtil.getCustomerDate());
                    needSaveProviders.add(provide);
                    slProduct.setSlTncGradeCode(5);
                }
                slPdMctStdNewList.add(stdNew);
            }
        }
        // 修改产品的加工质量等级
        sl241118Logic.modifySlProduct(slProduct);
        // 维护加工质量标准
        sl241118Logic.findSlPdMctStdList(slPdMctStdNewList);
        //调用接口 批量保存不同意
        int result = sl241118Logic.saveNotAgree(needSaveProviders);
        if( result != needSaveProviders.size()) {
            logger.error("调用接口saveMctProvider没有成功插入所有数据！");
        }
        //全部同意判断其他标准是否符合要求 OLD单个调用
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.getFilterMap().put("slPdId", sl241118Param.getSlPdId());
        basePageParam.getFilterMap().put("slCode", sl241118Param.getSlCode());
        String checkRs=sl241117Logic.checkAgree(basePageParam);
        if (!"1".equals(checkRs)) {
            slProduct.setStatus("2");
            this.sl241116Logic.upSlPdStatus(slProduct);
        }
        return "1";
    }

}
