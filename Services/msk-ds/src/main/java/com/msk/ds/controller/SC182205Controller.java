package com.msk.ds.controller;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.DsPrintTask;
import com.msk.ds.bean.*;
import com.msk.ds.logic.SC182205Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * SC182205Controller
 *
 * @author yi_qixiang
 *
 */
@Controller
@RequestMapping("SC182205")
public class SC182205Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182205Controller.class);

    /**
     * sc182205Logic
     */
    @Autowired
    private SC182205Logic sc182205Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, SC182203Bean sc182203Bean) {
        logger.info("产品批次明细打印画面初始化");
        SC182205Bean sc182205Bean = new SC182205Bean();
        String classesName = "";
        String breedName = "";
        String machiningName = "";
        String featureName = "";
        String weightName = "";
        String gradeName = "";

        String proLotNum = sc182203Bean.getProLotNum();
        SC182205Param sc182205Param = new SC182205Param();
        sc182205Param.setProLotNum(proLotNum);
        sc182205Bean = sc182205Logic.getDsProductLot(sc182205Param);
//        classesName = sc182205Logic.getClassesName(sc182205Bean.getClassesCode());
//        breedName = sc182205Logic.getBreedName(sc182205Bean.getBreedCode());
//        machiningName = sc182205Logic.getMachiningName(sc182205Bean.getMachiningCode());
//        featureName = sc182205Logic.getFeatureName(sc182205Bean.getFeatureCode());
//        weightName = sc182205Logic.getWeightName(sc182205Bean.getWeightCode());
//        gradeName = sc182205Logic.getGradeName(sc182205Bean.getGradeCode());
        model.addAttribute("classesName", sc182205Bean.getClassesName());
        model.addAttribute("breedName", sc182205Bean.getBreedName());
        model.addAttribute("machiningName", sc182205Bean.getMachiningName());
        model.addAttribute("featureName", sc182205Bean.getFeatureName());
        model.addAttribute("weightName", sc182205Bean.getWeightName());
        model.addAttribute("gradeName", sc182205Bean.getGradeName());
        model.addAttribute("sc182205Bean", sc182205Bean);

        return "ds/SC182205";
    }

    /**
     * （保存打印数据）
     *
     * @param sc182205Bean
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save( Model model, SC182205Bean sc182205Bean) throws Exception {
        logger.info("保存打印数据");
        String proLotNum = sc182205Bean.getProLotNum();
        String productPrintNum = sc182205Bean.getProductPrintNum();
//        String printTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        SC182205Param sc182205Param = new SC182205Param();
        sc182205Param.setProLotNum(proLotNum);
        sc182205Param.setProductPrintNum(productPrintNum);
        sc182205Param.setPrintTime(DateTimeUtil.getCustomerDate());
        //Add for Bug#2547 at 2016/09/09 by li_kai1 Start
        this.setCommonParam(sc182205Param);
        //Add for Bug#2547 at 2016/09/09 by li_kai1 End
        Date currentDate = DateTimeUtil.getCustomerDate();
        sc182205Param.setCrtTime(currentDate);
        sc182205Param.setUpdTime(currentDate);
        sc182205Param.setActTime(currentDate);
        sc182205Logic.saveProductPrintNum(sc182205Param);


        model.addAttribute("classesName", sc182205Bean.getClassesName());
        model.addAttribute("breedName", sc182205Bean.getBreedName());
        model.addAttribute("machiningName", sc182205Bean.getMachiningName());
        model.addAttribute("featureName", sc182205Bean.getFeatureName());
        model.addAttribute("weightName", sc182205Bean.getWeightName());
        model.addAttribute("gradeName", sc182205Bean.getGradeName());
        model.addAttribute("sc182205Bean", sc182205Bean);



        return "ds/SC182205";
    }
    /**
     * 导出CSV数据
     * @return 数据
     */
    @RequestMapping(value = "dataExport",method = RequestMethod.POST)
    public void dataExport(HttpServletResponse response, SC182205Bean sc182205Bean) throws Exception{
        logger.info("导出CSV数据");
        //设置下载弹出框
        // Modify for 3177 at 2016/10/10 by likai Start
        String filename = "打印产品批次明细.txt";
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
        }else {
            filename = URLEncoder.encode(filename,"UTF-8");
        }
        response.setContentType("application/csv;charset=gbk");
        response.setHeader("Content-Disposition","attachment;filename="+filename);
        // Modify for 3177 at 2016/10/10 by likai End
        //新建打印输出对象
        PrintWriter out = response.getWriter();

        try {
            /**修改  应上海需求*/
            out.write("产品名称,产品等级,操作码,阅读码,打印页码"+"\r\n");
            out.write(sc182205Bean.getBreedName()+","+sc182205Bean.getGradeName()+","+sc182205Bean.getProLotNum()+","+sc182205Bean.getReadProLotNum()+","+sc182205Bean.getProductPrintNum());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

}