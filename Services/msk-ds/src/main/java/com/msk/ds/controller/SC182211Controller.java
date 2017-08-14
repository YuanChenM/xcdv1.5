package com.msk.ds.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.param.BasePageParam;
import com.msk.core.entity.DsPrintTask;
import com.msk.core.entity.PdClassestree;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.ds.bean.*;
import com.msk.ds.logic.SC182102Logic;
import com.msk.ds.logic.SC182211Logic;
import com.msk.ds.rest.comm.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.net.URLEncoder;
import java.util.List;

/**
 * SC182211Controller
 * @author pxg
 */
@Controller
@RequestMapping("SC182211")
public class SC182211Controller extends BaseController {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC182211Controller.class);

    @Autowired
    private SC182102Logic sc182102Logic;
    @Autowired
    private SC182211Logic sc182211Logic;
    /**
     * 初始化页面
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model) {
        logger.info("产品批次入库及标签打印初始化");
        BaseParam param =new BaseParam();
        this.setCommonParam(param);
        List<SC182211Bean> sc182211BeanList= sc182211Logic.findEpNameList(param);
        model.addAttribute("sc182211BeanList",sc182211BeanList);
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> lgcsCodeList = RestUtil.getAllLgcsList(districtParam);
        model.addAttribute("lgcsCodeList",lgcsCodeList);
        return "ds/SC182211";
    }

    /**
     * 分类数据检索
     * @param slCode slCode
     * @return 页面
     */
    @RequestMapping(value = "queryClass",method = RequestMethod.POST)
    public @ResponseBody List<PdClassestree> queryClass(String slCode,String treeLevel,String parentCode,String classCode) {
        logger.info("查询产品分类信息");
        return sc182211Logic.findSlOneClass(slCode, treeLevel,parentCode,classCode);
    }

    /**
     * 根据卖家产品查询产品生产商
     * @param sc182211Bean sc182211Bean
     * @return 页面
     */
    @RequestMapping(value = "querySlPropEp",method = RequestMethod.POST)
    public @ResponseBody List<SC182211Bean> querySlPropEp(SC182211Bean sc182211Bean) {
        logger.info("查询产品对应的生产商");
        return sc182211Logic.findSlPropEpList(sc182211Bean);
    }

    /**
     * 根据卖家查询品牌
     * @param sc182211Bean sc182211Bean
     * @return 页面
     */
    @RequestMapping(value = "querySlBrand",method = RequestMethod.POST)
    public @ResponseBody List<SC182211Bean> querySlBrand(SC182211Bean sc182211Bean) {
        logger.info("查询品牌信息");
        return sc182211Logic.querySlBrand(sc182211Bean);
    }

    /**
     * 保存信息，生成对应编码
     * @param sc182211Bean sc182211Bean
     * @return 页面
     */
    @RequestMapping(value = "saveData",method = RequestMethod.POST)
    public @ResponseBody int saveData(SC182211Bean sc182211Bean) {
        LoginUser user=  this.getLoginUser();
        sc182211Bean.setCrtId(user.getEmplId());
        sc182211Logic.saveData(sc182211Bean);
        return NumberConst.IntDef.INT_ONE;
    }

    /**
     * 根据卖家查询包装规格
     * @param sc182211Bean sc182211Bean
     * @return 页面
     */
    @RequestMapping(value = "queryNormsOut",method = RequestMethod.POST)
    public @ResponseBody List<SC182211Bean> queryNormsOut(SC182211Bean sc182211Bean) {
        logger.info("查询产品包装规格");
        return sc182211Logic.queryNormsOut(sc182211Bean);
    }
    /**
     * 根据产品查询货号
     * @param sc182211Bean sc182211Bean
     * @return 页面
     */
    @RequestMapping(value = "querySlPdArtno",method = RequestMethod.POST)
    public @ResponseBody List<SC182211Bean> querySlPdArtno(SC182211Bean sc182211Bean) {
        logger.info("查询产品对应的货号");
        return sc182211Logic.findSlPdArtno(sc182211Bean);
    }

    /**
     * 导出CSV数据
     * @return 数据
     */
    @RequestMapping(value = "dataExport",method = RequestMethod.POST)
    public void dataExport(HttpServletResponse response) throws Exception{
        logger.info("导出数据文件");
        //查询出需要打印的数据
        List<DsPrintTask> list=sc182211Logic.findDataExport();
        //设置下载弹出框
        // Modify for 3177 at 2016/10/10 by likai Start
        String filename = "打印标签.csv";
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
        }else {
            filename = URLEncoder.encode(filename,"UTF-8");
        }
        // Modify for 3177 at 2016/10/10 by likai End
        response.setContentType("application/csv;charset=gbk");
        response.setHeader("Content-Disposition","attachment;filename="+filename);
        //新建打印输出对象
        PrintWriter out = response.getWriter();

        try {
            /**修改  应上海需求*/
            out.write("任务编码,操作码,阅读码,品牌名,等级名,产品名称,产品规格,生产商,净含量"+"\n");
            for(DsPrintTask s:list){
                out.write(s.getPrintTaskId()
                        +","+s.getOperateBar()
                        +","+s.getReadBar()
                        +","+s.getBrandName()
                        +","+s.getGradeName()
                        +","+s.getProductName()
                        +","+s.getFeatureName()
                        +","+s.getSuppName()
                        +","+s.getNetWeight()
                        +"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /**
     * 清楚所有数据
     * @return 页面
     */
    @RequestMapping(value = "dataDelete",method = RequestMethod.POST)
    public @ResponseBody int dataDelete() {
        logger.info("新标签打印(清除所有数据)");
        return sc182211Logic.dataDelete();
    }


    /**
     * 查询上次批次数据
     * @return 页面
     */
    @RequestMapping(value = "dataSearch",method = RequestMethod.POST)
    public @ResponseBody String dataSearch(SC182211Bean sc182211Bean) {
        logger.info("查询上批次数据");
        return sc182211Logic.dataSearch(sc182211Bean);
    }
}
