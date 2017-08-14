package com.msk.bms.ssc.controller;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.bms.ssc.controller.common.ISSCDifferRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.excel.ExcelWrite;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  Created by wu_honglei on 2016/7/26
 *  生产商入库差异单导出
 */
@Controller
@RequestMapping("SSC11318")
public class SSC11318Controller extends BaseController {
    /**
     * logger
     */
    Logger logger = LoggerFactory.getLogger(SSC11318Controller.class);



    /**
     * 导出excel
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public void export(HttpServletRequest request, HttpServletResponse response,SSC11312Param param) throws Exception {


        String fileName = "入库差异单_"+param.getDifferCode()+"_"+ DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.getCustomerDate())+ ".xlsx";
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
        OutputStream outputStream = response.getOutputStream();

        this.createExcel(outputStream,param);
    }

    @Transactional(readOnly = true)
    public void createExcel(OutputStream outputStream,SSC11312Param param)throws FileNotFoundException{

        RsResponse<SSC11312Result> respResult = ISSCDifferRestUtil.queryDifferDetails(param);
        List<SSC11312Bean> sscDifferDetailList = respResult.getResult().getSscDifferDetails();
        InputStream in = SSC11318Controller.class.getClassLoader().getResourceAsStream("/temp/reportTemp.xlsx");
        ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(outputStream));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", sscDifferDetailList);
        paramMap.put("ssc11312Param", param);

        excelWrite.excelWrite(paramMap);
    }



    private  List<SSC11312Bean>  createData(){
        List<SSC11312Bean> list = new ArrayList<>();
        SSC11312Bean  bean  = new SSC11312Bean();
        bean.setBreedName("一二三四五六七八");
        bean.setReceiveBox(9999999);
        bean.setReceiveWeightStr("9999999999.9999");
        bean.setReceivePriceStr("10000.00");
        bean.setReceiveAmountStr("999999999999999.99");
        bean.setSendBox(9999999);
        bean.setSendWeightStr("9999999999.9999");
        bean.setSendPriceStr("123456.9874");
        bean.setSendAmountStr("999999999999999.99");
        bean.setPriceDiffStr("100874.84");
        bean.setWeightDiffStr("999999.8745");
        bean.setAmountDiffStr("987744.32");
        list.add(bean);
        return list;
    }



}
