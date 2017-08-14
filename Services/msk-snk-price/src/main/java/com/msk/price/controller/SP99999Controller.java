package com.msk.price.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170Param;
import com.msk.price.logic.SP171170Logic;
import com.msk.price.utils.CommRestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mao_yejun on 2016/8/24.
 */
@Controller
@RequestMapping("SP99999")
public class SP99999Controller extends BaseController {
    @Autowired
    private SP171170Logic sp171170Logic;
    private static Logger logger = LoggerFactory.getLogger(SP99999Controller.class);

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SP171170Param param, Model model) {
        // 价盘年月
        String priceDate = "";
        // 价盘周期
        String pricecycle = "";
        if (!StringUtil.isNullOrEmpty(param.getPriceDate()) && !StringUtil.isNullOrEmpty(param.getPricecycle())) {
            // 为了防止点击编辑按钮后将查询条件的价盘周期，重新置为最新价盘
            priceDate = param.getPriceDate();
            pricecycle = param.getPricecycle();
        } else {
            // 默认显示价盘表里已有的最新价盘的数据。
            String pricePlate = sp171170Logic.findNewestPriceplate(param);
            if (!StringUtil.isNullOrEmpty(pricePlate)) {
                priceDate = pricePlate.substring(0, 2) + "-" + pricePlate.substring(2, 4);
                pricecycle = pricePlate.substring(4, 5);
            }
        }
        model.addAttribute("priceDate", priceDate);
        model.addAttribute("pricecycle", pricecycle);
        // 获取物流区信息
        model.addAttribute("logisticsAreas", CommRestUtil.getLogiticsAreaList());
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setType(NumberConst.IntDef.INT_ONE);
        // 调用产品接口（获取一级分类的名称）
        model.addAttribute("pdClasses", CommRestUtil.getPdList(pdInfoParam));
        // 页面用来判断是否能够编辑
        model.addAttribute("flag", param.getFlag());
        model.addAttribute("logcAreaCode", param.getLgcsAreaCode());
        model.addAttribute("classesCode", param.getClassesCode());
        model.addAttribute("machiningCode", param.getMachiningCode());
        model.addAttribute("breedName", param.getBreedName());

        return "sp/SP99999";
    }


    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171170Bean> search(SP171170Param param) {
        String priceDate = "";
        if (!StringUtil.isNullOrEmpty(param.getPriceDate())) {
            priceDate = param.getPriceDate().substring(0, 2) + param.getPriceDate().substring(3, 5);
        }
        param.setPricecyclePeriod(priceDate + param.getPricecycle());
        return sp171170Logic.searchPricePdInfo(param);
    }


    /**
     * 查询产品二级分类
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "findMaching", method = RequestMethod.POST)
    @ResponseBody
    public List<PDInfoResult> findMachingList(SP171170Param param) {
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setClassesCode(param.getClassesCode());
        List<PDInfoParam> pdInfoParams = new ArrayList<>();
        pdInfoParams.add(pdInfoParam);
        PDInfoParam pdParam = new PDInfoParam();
        pdParam.setMachiningCodeList(pdInfoParams);
        return CommRestUtil.getMachingList(pdParam);
    }


    /**
     * 更新价盘公斤价及箱价
     *
     * @param model
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String modify(Model model, String jsonStr,
                         @RequestParam(value = "priceDate", required = false) String priceDate,
                         @RequestParam(value = "pricecycle", required = false) String pricecycle) {
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        Map<String, SP171170Bean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SP171170Bean>>() {
        });
        sp171170Logic.modify(map.values(), baseParam);
        SP171170Param param = new SP171170Param();
        param.setPriceDate(priceDate);
        param.setPricecycle(pricecycle);
        return this.init(param, model);
    }

    /**
     * excel模板导出
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    private void export(SP171170Param param, HttpServletRequest request, HttpServletResponse response) throws Exception {

        param.setPaging(false);
        long startTime = System.currentTimeMillis();
        List<SP171170Bean> list = sp171170Logic.findPageList(param, SP171170Bean.class);
        long endTime = System.currentTimeMillis();
        logger.info("获得数据花费时间:" + (endTime - startTime));

        Map<String, Object> paramMap = new HashMap<>();
        String title = "价盘详情" + param.getTitle();
        paramMap.put("headEntity", title);
        paramMap.put("list", list);
        this.downloadExcel(paramMap, response, title);

    }

    /**
     * Set Excel Download Header.
     *
     * @param response Http Servlet Response
     * @param fileName Excel File Name
     */
    private void setExcelDownloadHeader(HttpServletResponse response, String fileName) {
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Download Excel.
     *
     * @param dataMap  Excel Download Data
     * @param response Http Servlet Response
     * @param fileName Excel File Name
     */
    public void downloadExcel(Map<String, Object> dataMap, HttpServletResponse response, String fileName) throws IOException {
        this.setExcelDownloadHeader(response, fileName);
        this.write(dataMap, response);
    }

    public void write(Map<String, Object> dataMap, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        //创建excel文件对象
        XSSFWorkbook wb = new XSSFWorkbook(SP99999Controller.class.getResourceAsStream("/excel/template/priceTemp1.xlsx"));
        //创建一个张表
        Sheet sheet = wb.getSheet("Sheet1");
        //创建第一行
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cell.setCellValue((String) dataMap.get("headEntity"));
        List<SP171170Bean> list = (List) dataMap.get("list");
        int l=4;
        for (int i = 0; i < list.size(); i++) {
            Row rowData = sheet.createRow(l++);
            createCell(wb,rowData,0,list.get(i).getLogiareaName(),cellStyle);
            createCell(wb,rowData,1,list.get(i).getPdCode(),cellStyle);
            createCell(wb,rowData,2,list.get(i).getClassesName(),cellStyle);
            createCell(wb,rowData,3,list.get(i).getMachiningName(),cellStyle);
            createCell(wb,rowData,4,list.get(i).getBreedName(),cellStyle);
            createCell(wb,rowData,5,list.get(i).getFeatureName(),cellStyle);
            createCell(wb,rowData,6,list.get(i).getWeightName(),cellStyle);
            createCell(wb,rowData,7,list.get(i).getGradeName(),cellStyle);
            createCell(wb,rowData,8,list.get(i).getWayGradeName(),cellStyle);
            createCell(wb,rowData,9,list.get(i).getSupPriceofkg(),cellStyle);
            createCell(wb,rowData,10,list.get(i).getSupPriceofbox(),cellStyle);
            createCell(wb,rowData,11,list.get(i).getOnePriceofkg(),cellStyle);
            createCell(wb,rowData,12,list.get(i).getOnepriceofbox(),cellStyle);
            createCell(wb,rowData,13,list.get(i).getTwoPriceofkg(),cellStyle);
            createCell(wb,rowData,14,list.get(i).getTwoPriceofbox(),cellStyle);
            createCell(wb,rowData,15,list.get(i).getThreePriceofkg(),cellStyle);
            createCell(wb,rowData,16,list.get(i).getThreepriceofbox(),cellStyle);
            createCell(wb,rowData,17,list.get(i).getFourPriceofkg(),cellStyle);
            createCell(wb,rowData,18,list.get(i).getFourPriceofbox(),cellStyle);
            createCell(wb,rowData,19,list.get(i).getFivePriceofkg(),cellStyle);
            createCell(wb,rowData,20,list.get(i).getFivepriceofbox(),cellStyle);
            createCell(wb,rowData,21,list.get(i).getSixPriceofkg(),cellStyle);
            createCell(wb,rowData,22,list.get(i).getSixPriceofbox(),cellStyle);
            createCell(wb,rowData,23,list.get(i).getSevenPriceofkg(),cellStyle);
            createCell(wb,rowData,24,list.get(i).getSevenpriceofbox(),cellStyle);
            createCell(wb,rowData,25,list.get(i).getEightPriceofkg(),cellStyle);
            createCell(wb,rowData,26,list.get(i).getEightPriceofbox(),cellStyle);
            createCell(wb,rowData,27,list.get(i).getNinePriceofkg(),cellStyle);
            createCell(wb,rowData,28,list.get(i).getNinepriceofbox(),cellStyle);

        }
        long endTime = System.currentTimeMillis();
        logger.info("写文件花费时间:" + (endTime - startTime));
        wb.write(response.getOutputStream());
    }
    public static void createCell(Workbook wb, Row row, int column, String value, CellStyle cellStyle, Font ... font) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
//        CellStyle cellStyle = wb.createCellStyle();
//        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
//        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }
}
