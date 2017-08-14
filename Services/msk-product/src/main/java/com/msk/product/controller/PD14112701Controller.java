package com.msk.product.controller;

import com.msk.common.base.BaseController;
import com.msk.product.bean.PdTcOnlineOemParam;
import com.msk.product.logic.PD14112601Logic;
import com.msk.product.logic.PD14112701Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 正式上线添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD14112701")
public class PD14112701Controller extends BaseController {
    @Autowired
    private PD14112701Logic pd14112701Logic;

    @Autowired
    private PD14112601Logic pd14112601Logic;
    /**
     * 实例化页面
     *
     * @param getDivId getDivId
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,@RequestParam(value="classestreeCode",required = false)String classestreeCode,@RequestParam(value="getDivId",required = false)String getDivId) {
        model.addAttribute("breedCode",classestreeCode);
        model.addAttribute("getDivId",getDivId);
        return "pd/PD14112701";
    }


    /**
     * 保存正式上线数据
     * param
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody List<PdTcOnlineOemParam> saveOnLine(PdTcOnlineOemParam pdTcOnlineOemParam,String getDivId){
        List<PdTcOnlineOemParam> listOnLine=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/12  BY  任强  Start */
        super.setCommonParam(pdTcOnlineOemParam);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        String breedCode=pdTcOnlineOemParam.getBreedCode();
        int count=pd14112701Logic.queryOnlineOem(pdTcOnlineOemParam);
        if(count<=0){
            int num=pd14112701Logic.saveOnlineOem(pdTcOnlineOemParam);
            if(num>0){
                listOnLine=pd14112601Logic.queryOnlineOem(breedCode, getDivId);
            }
        }else{
            PdTcOnlineOemParam pdTcOnlineOem=new PdTcOnlineOemParam();
            pdTcOnlineOem.setMessage("1");
            listOnLine.add(pdTcOnlineOem);
        }
        return listOnLine;
    }
}
