package com.msk.so.controller;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.so.bean.SO153121Bean;
import com.msk.so.logic.SO153121Logic;
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
 * 买手家资金池
 * Created by wu_honglei on 2016/5/6.
 */

@Controller
@RequestMapping("SO153121")
public class SO153121Controller {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO153121Controller.class);

    @Autowired
    private SO153121Logic so153121Logic;

    /**
     * 买手家资金池
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("进入买手资金池一览页面");
        return "so/SO153121";
    }


    /**
     * 查询买手信息
     *
     * @param pageParam BasePageParam
     * @return 卖家产品JSON数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SO153121Bean> search(BasePageParam pageParam) {
        logger.debug("买手资金池一览");
        // 设置参数
        pageParam = this.so153121Logic.setBasePageParam(pageParam);
        PageResult<SO153121Bean> result = this.so153121Logic.findPage(pageParam, SO153121Bean.class);
        if (result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
            SO153121Bean currentBen = new SO153121Bean();
            currentBen = this.so153121Logic.getCurrentInfo(pageParam);
            result.getData().get(0).setCurrentOrderAmount(currentBen.getCurrentOrderAmount());
            result.getData().get(0).setCurrentBalance(currentBen.getCurrentBalance());
            result.getData().get(0).setCurrentActualDue(currentBen.getCurrentActualDue());
            result.getData().get(0).setCurrentActualPaid(currentBen.getCurrentActualPaid());
            result.getData().get(0).setCurrentActualReceiveable(currentBen.getCurrentActualReceiveable());
            result.getData().get(0).setCurrentReliefAmount(currentBen.getCurrentReliefAmount());
            result.getData().get(0).setCurrentActualReceived(currentBen.getCurrentActualReceived());

            pageParam.setPaging(false);
            SO153121Bean totalBen = new SO153121Bean();
            totalBen = this.so153121Logic.getCurrentInfo(pageParam);
            result.getData().get(0).setTotalOrderAmount(totalBen.getCurrentOrderAmount());
            result.getData().get(0).setTotalBalance(totalBen.getCurrentBalance());
            result.getData().get(0).setTotalActualDue(totalBen.getCurrentActualDue());
            result.getData().get(0).setTotalActualPaid(totalBen.getCurrentActualPaid());
            result.getData().get(0).setTotalActualReceiveable(totalBen.getCurrentActualReceiveable());
            result.getData().get(0).setTotalReliefAmount(totalBen.getCurrentReliefAmount());
            result.getData().get(0).setTotalActualReceived(totalBen.getCurrentActualReceived());
        }

        return result;
    }


//    /**
//     * 导出买手信息
//     *
//     * @param pageParam BasePageParam
//     * @return 卖家产品JSON数据
//     */
//    @RequestMapping(value = "dataExport", method = RequestMethod.POST)
//    public void export(BasePageParam pageParam, HttpServletResponse response) throws Exception {
//        logger.debug("导出买手信息");
//        // 设置参数
//        pageParam = this.so153121Logic.setBasePageParam(pageParam);
//        String fileName = "BS_" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.getCustomerDate()) + ".xlsx";
//        response.reset();
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
//        OutputStream ouputStream = response.getOutputStream();
//        this.so153121Logic.export(pageParam, ouputStream);
//    }


//    /**
//     * 导出买手信息
//     *
//     * @param pageParam BasePageParam
//     * @return 卖家产品JSON数据
//     */
//    @RequestMapping(value = "dataExportPOI", method = RequestMethod.POST)
//    public void exportPOI(BasePageParam pageParam, HttpServletResponse response) throws Exception {
//        logger.debug("导出买手信息");
//        // 设置参数
//        pageParam = this.so153121Logic.setBasePageParam(pageParam);
//        Workbook workbook = this.so153121Logic.exportPOI(pageParam);
//        Date date = DateTimeUtil.getCustomerDate();
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String dateStr = sf.format(date);
//        OutputStream o = response.getOutputStream();
//        response.setHeader("Content-disposition", "attachment;filename=" + "BS_" + dateStr + ".xlsx");// the dialogbox of download file.
//        response.setContentType("application/vnd.ms-excel");// set the MIME type.
//        workbook.write(o);
//    }


}
