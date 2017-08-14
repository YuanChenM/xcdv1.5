package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121310Param;
import com.msk.buyers.bean.IBY121207RsParam;
import com.msk.buyers.logic.BY121304Logic;
import com.msk.buyers.logic.BY121310Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 买家详细画面
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("BY121304")
public class BY121304Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121304Controller.class);

    @Autowired
    private BY121304Logic by121304Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId,Model model) {
        logger.debug("买家详细画面");
        model.addAttribute("buyerId",buyerId);
        ByBuyerAccount buyerAccount = by121304Logic.findBuyerAccount(buyerId);
        if(null != buyerAccount){
            model.addAttribute("buyerAccount",buyerAccount);
        }



        return "buyers/BY121304";
    }
    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<IBY121207RsParam> search(@PathVariable("buyerId") String buyerId,BasePageParam param) {
        logger.debug("买家员工信息查询");
        param.getFilterMap().put("buyerId", buyerId);
        DbUtils.buildLikeCondition(param, "employeeName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "employeeTel", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "employeeQq", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "employeeWechat", DbUtils.LikeMode.PARTIAL);
        PageResult<IBY121207RsParam> result = by121304Logic.findPage(param, IBY121207RsParam.class);
        // 买家类型
        Map<String,String> EmployeeMap = CodeMasterManager.findCodeMasterMap("EmployeeType");
        for (IBY121207RsParam iby121207RsParam : result.getData()){
            if(StringUtil.isNullOrEmpty(iby121207RsParam.getEmployeeType())){
                iby121207RsParam.setEmployeeTypeName("");
            }else{
                iby121207RsParam.setEmployeeTypeName(EmployeeMap.get(iby121207RsParam.getEmployeeType()));
            }
        }
        return result;
    }

    /**
     * 删除雇员信息
     * @param byBuyerEmployee
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public @ResponseBody String deleteEmployee(IBY121207RsParam byBuyerEmployee){
        super.setCommonParam(byBuyerEmployee);
        Date currentDate = DateTimeUtil.getCustomerDate();
        byBuyerEmployee.setUpdTime(currentDate);
        byBuyerEmployee.setCrtTime(currentDate);
        byBuyerEmployee.setActTime(currentDate);
        by121304Logic.deleteEmployee(byBuyerEmployee);
        return "buyers/BY121304";
    }

}
