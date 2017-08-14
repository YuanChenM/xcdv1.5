package com.msk.so.controller;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.logic.SO153111Logic;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 卖家资金池
 * Created by wu_honglei on 2016/4/29.
 */

@Controller
@RequestMapping("SO153111")
public class SO153111Controller {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SO153111Controller.class);

    @Autowired
    private SO153111Logic so153111Logic;

    /**
     * 卖家初始化页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model){
        logger.debug("进入卖家资金池一览页面");
        return "so/SO153111";
    }



    /**
     * 卖家查询获得数据
     * @param pageParam BasePageParam
     * @return 卖家产品JSON数据
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody PageResult<SOCp153111Bean> search(BasePageParam pageParam){
        logger.debug("卖家资金池一览");

        // 验证日期
        boolean flag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("startTime"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("endTime")));
        if(!flag){
            throw new BusinessException("日期选择不合理");
        }

        //设置查询参数
        so153111Logic.setParam(pageParam);

        return this.so153111Logic.findSellerBillingPage(pageParam);
    }
//    /**
//     * 卖家列表下载
//     * @param pageParam BasePageParam
//     * @return 卖家产品JSON数据
//     */
//    @RequestMapping(value = "export",method = RequestMethod.POST)
//    public void  exportSeller(BasePageParam pageParam, HttpServletResponse response)throws Exception{
//
//        logger.debug("卖家列表页面下载");
//        // 验证日期
//        boolean flag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("startTime"))
//                , StringUtil.toSafeString(pageParam.getFilterMap().get("endTime")));
//        if(!flag){
//            throw new BusinessException("日期选择不合理");
//        }
//
//        //设置查询参数
//        setParam(pageParam);
//
//
//        String fileName = "SellerPool_" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.getCustomerDate()) + ".xlsx";
//        response.reset();
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
//        OutputStream ouputStream = response.getOutputStream();
//        this.so153111Logic.export(ouputStream, pageParam);
//
//
//    }
    /**
     * 更改结算状态
     * @param sellerBillId,settlementFlg
     * @return 卖家产品JSON数据
     */

    @RequestMapping(value = "changeSettlementFlag",method = RequestMethod.POST)
    public String  changeSettlementFlag(String sellerBillId,String settlementFlag, Model model){
        int resultCount = this.so153111Logic.updateFlg(sellerBillId,settlementFlag);
        if (resultCount <= NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("更新失败，请重新操作！");
        }
        return this.init(model);
    }

}
