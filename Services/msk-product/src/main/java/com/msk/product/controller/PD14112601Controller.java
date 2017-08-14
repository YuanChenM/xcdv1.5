package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.*;
import com.msk.product.logic.PD14112601Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD14112601")
public class PD14112601Controller extends BaseController {
    @Autowired
    private PD14112601Logic pd14112601Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model) {
        BaseParam param = new BaseParam();
        /**Add: Bug #2442 系统长时间未操作后点击菜单报错系统异常 2016/09/06   BY  杨春艳  Start */
        super.setCommonParam(param);
        /**Add: Bug #2442 系统长时间未操作后点击菜单报错系统异常 2016/09/06   BY  杨春艳  End */
        return "pd/PD14112601";
    }

    /**
     * 检索原料描述数据
     * @param classCodeThree classCodeThree
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "queryData", method = RequestMethod.POST)
    public String queryData(@RequestParam(value ="classCodeThree",required = false) String classCodeThree,@RequestParam(value ="classCodeTwo",required = false) String classCodeTwo,Model mav) {
        /**Add: Bug #2442 系统长时间未操作后点击菜单报错系统异常 2016/09/06   BY  杨春艳  Start */
        super.setCommonParam(new BaseParam());
        /**Add: Bug #2442 系统长时间未操作后点击菜单报错系统异常 2016/09/06   BY  杨春艳  End */
        PdClassestreeMat pdClassestreeMat=null;
        PdClassestreeMat pdClassestreeMatData=null;
        List<PD14112601Param> listMat=new ArrayList<>();
        System.out.print(new Date());
        if(null!=classCodeThree){
            pdClassestreeMat=pd14112601Logic.queryClassData(classCodeThree);
            listMat=pd14112601Logic.queryThreeData(classCodeThree);
        } else{
            pdClassestreeMat=pd14112601Logic.queryClassData(classCodeTwo);
            listMat=pd14112601Logic.queryTwoData(classCodeTwo);
        }
        mav.addAttribute("pdClassestreeMat",pdClassestreeMat);
        mav.addAttribute("listMat",listMat);
        System.out.print(new Date());
        return "pd/PD14112601";
    }

    /**
     * 删除市场需求审核注册值
     * @param classestreeCode classestreeCode
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "deleteData", method = RequestMethod.POST)
    public @ResponseBody List<PD141128Param> deleteData(@RequestParam(value ="classestreeCode",required = false) String classestreeCode,@RequestParam(value ="getDivId",required = false) String getDivId,@RequestParam(value ="deleteValue",required = false) String deleteValue) {
        List<PD141128Param> listMat=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        Date date = DateTimeUtil.getCustomerDate();
        param.setUpdTime(date);
        int num=pd14112601Logic.deleteData(deleteValue,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            listMat=pd14112601Logic.queryMarketData(classestreeCode,getDivId);
        }
        return listMat;
    }

    /**
     * 更新市场需求审核注册值
     * @param classestreeCode classestreeCode
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "updateData", method = RequestMethod.POST)
    public @ResponseBody List<PD141128Param> updateData(@RequestParam(value ="classestreeCode",required = false) String classestreeCode,@RequestParam(value ="getDivId",required = false) String getDivId,@RequestParam(value ="updateValue",required = false) String updateValue) {
        List<PD141128Param> listMat=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        int num=pd14112601Logic.updateData(updateValue,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            listMat=pd14112601Logic.queryMarketData(classestreeCode, getDivId);
        }
        return listMat;
    }


    /**
     * 删除卖家供应备案注册
     * @param deleteId deleteId
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "deleteProvider", method = RequestMethod.POST)
    public @ResponseBody List<PdTcProviderPackageParam> deleteProvider(@RequestParam(value ="deleteId",required = false) String deleteId,@RequestParam(value ="deleteClassCode",required = false) String deleteClassCode) {
        List<PdTcProviderPackageParam> list=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        int num=pd14112601Logic.deleteProvider(deleteId,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            List<PdTcProviderPackageParam> listMat=pd14112601Logic.queryProviderData(deleteClassCode);
            if(!CollectionUtils.isEmpty(listMat)){
                for (int i = 0; i <listMat.size() ; i++) {
                    PdTcProviderPackageParam pdTcProviderParam=listMat.get(i);
                    pdTcProviderParam.setClassestreeCode(deleteClassCode);
                    list.add(pdTcProviderParam);
                }
            }
        }
        return list;
    }

    /**
     * 更新卖家供应备案注册
     * @param editId editId
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "updateProvider", method = RequestMethod.POST)
    public @ResponseBody List<PdTcProviderPackageParam> updateProvider(@RequestParam(value ="editId",required = false) String editId,@RequestParam(value ="editClassCode",required = false) String editClassCode) {
        List<PdTcProviderPackageParam> list=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        int num=pd14112601Logic.updateProvider(editId,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            List<PdTcProviderPackageParam> listMat=pd14112601Logic.queryProviderData(editClassCode);
            if(!CollectionUtils.isEmpty(listMat)){
                for (int i = 0; i <listMat.size() ; i++) {
                    PdTcProviderPackageParam pdTcProviderParam=listMat.get(i);
                    pdTcProviderParam.setClassestreeCode(editClassCode);
                    list.add(pdTcProviderParam);
                }
            }
        }
        return list;
    }


    /**
     * 删除正式上线
     * @param onLinedeleteClassCode onLinedeleteClassCode
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "deleteOnLine", method = RequestMethod.POST)
    public @ResponseBody List<PdTcOnlineParam> deleteOnLine(@RequestParam(value ="onLinedeleteClassCode",required = false) String onLinedeleteClassCode,@RequestParam(value ="onLinedeleteId",required = false) String onLinedeleteId) {
        List<PdTcOnlineParam> listMat=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        int num=pd14112601Logic.deleteOnLine(onLinedeleteId,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            listMat=pd14112601Logic.queryOnLineData(onLinedeleteClassCode,null);
        }
        return listMat;
    }

    /**
     * 删除卖家供应备案注册
     * @param contentDeleteId contentDeleteId
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "deleteProviderContent", method = RequestMethod.POST)
    public @ResponseBody List<PdTcProviderContentParam> deleteProviderContent(@RequestParam(value ="contentDeleteClassCode",required = false) String contentDeleteClassCode,@RequestParam(value ="contentDeleteId",required = false) String contentDeleteId) {
        List<PdTcProviderContentParam> listMat=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        int num=pd14112601Logic.deleteProviderContent(contentDeleteId,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            listMat=pd14112601Logic.queryProviderContent(contentDeleteClassCode, null);
        }
        return listMat;
    }

    /**
     * 删除OEM正式上线
     * @param oneLineOemdeleteId oneLineOemdeleteId
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "deleteProviderOem", method = RequestMethod.POST)
    public @ResponseBody List<PdTcOnlineOemParam> deleteProviderOem(@RequestParam(value ="oneLineOemdeleteClassCode",required = false) String oneLineOemdeleteClassCode,@RequestParam(value ="oneLineOemdeleteId",required = false) String oneLineOemdeleteId) {
        List<PdTcOnlineOemParam> listMat=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        int num=pd14112601Logic.deleteOnlineOem(oneLineOemdeleteId,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            listMat=pd14112601Logic.queryOnlineOem(oneLineOemdeleteClassCode, null);
        }
        return listMat;
    }

    /**
     * 删除禁止准入产品
     * @param deleteId deleteId
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "deleteTcForbid", method = RequestMethod.POST)
    public @ResponseBody List<PdTcForbidParam> deleteTcForbid(@RequestParam(value ="deleteClassCode",required = false) String deleteClassCode,@RequestParam(value ="deleteId",required = false) String deleteId) {
        List<PdTcForbidParam> listMat=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        int num=pd14112601Logic.deleteTcForbid(deleteId,param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if(num>0){
            listMat=pd14112601Logic.queryTcForbid(deleteClassCode, null);
        }
        return listMat;
    }
}
