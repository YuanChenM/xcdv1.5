package com.msk.ds.controller;

import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.ds.bean.DistSuppChain;
import com.msk.ds.bean.SC181102Param;
import com.msk.ds.logic.SC181102Logic;
import org.apache.commons.collections.CollectionUtils;
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
 * SC181102Controller
 *
 * @author zhou_yajun
 *
 */
@Controller
@RequestMapping("SC181102")
public class SC181102Controller extends BaseController {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC181102Controller.class);

    @Autowired
    private SC181102Logic sc181102Logic;
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(SC181102Param sc181102Param,Model model) {
        logger.info("月度管控画面初始化");
        // 登录用户信息设定
        this.setCommonParam(sc181102Param);
        //获取分销月度
        DistSuppChain distMonth = sc181102Logic.getDistMonth(sc181102Param);
        //获取半旬
        DistSuppChain halfName = sc181102Logic.getHalfName(sc181102Param);
        //获取所有产品的数据集合
        DistSuppChain productNum = sc181102Logic.getProductNum(sc181102Param);
        //获取所有产品的合计数据
        DistSuppChain productSumNum = sc181102Logic.getProductSumNum(sc181102Param);

        model.addAttribute("distMonth",distMonth);
        model.addAttribute("productNum", productNum);
        model.addAttribute("productSumNum",productSumNum);
        model.addAttribute("halfParam",halfName);
        model.addAttribute("userType",sc181102Param.getUserType());
        return "ds/SC181102";
    }
    /**
     * 下拉框选择
     * @param sc181102Param sc181102Param
     * @return 画面
     */
    @RequestMapping(value = "selectChange",
            method = RequestMethod.POST)
    public @ResponseBody
    List<DistSuppChain> selectChange(SC181102Param sc181102Param) {
        logger.info("月度管控画面下拉框选择");
        this.setCommonParam(sc181102Param);
        List<DistSuppChain> selectValueList = sc181102Logic.getSelectValue(sc181102Param);
        if(CollectionUtils.isNotEmpty(selectValueList)) {
            if(!StringUtil.isNullOrEmpty(sc181102Param.getDistMonth()) && StringUtil.isNullOrEmpty(sc181102Param.getAreaCode())){
//                for(int i=0;i<selectValueList.size();i++){
                    String AreaCode = selectValueList.get(0).getAreaCode();
                    sc181102Param.setAreaCode(AreaCode);
                    List<DistSuppChain> selectValueList1 = sc181102Logic.getSelectValue(sc181102Param);
                    selectValueList.get(0).setSupplierInfoList(selectValueList1);
//                }
            }
        }
        return selectValueList;
    }
}
