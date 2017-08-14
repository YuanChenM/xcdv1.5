package com.msk.product.controller;

import com.msk.common.base.BaseController;
import com.msk.product.bean.PdTcProviderContentParam;
import com.msk.product.logic.PD14112601Logic;
import com.msk.product.logic.PD14112801Logic;
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
 * 市场需求审核注册修改添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD14112801")
public class PD14112801Controller extends BaseController {
    @Autowired
    private PD14112801Logic pd14112801Logic;

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
    public String init(Model model,@RequestParam(value="classestreeCode",required = false)String classestreeCode,@RequestParam(value="getDivId",required = false)String getDivId) {
        model.addAttribute("breedCode",classestreeCode);
        model.addAttribute("getDivId",getDivId);
        return "pd/PD14112801";
    }

    /**
     * 保存数据
     *
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody List<PdTcProviderContentParam> save(PdTcProviderContentParam pdTcProviderContentParam,String getDivId) {
        List<PdTcProviderContentParam> listMat=new ArrayList<>();
        /**Add: 横展开添加共通设置 2016/09/12  BY  任强  Start */
        super.setCommonParam(pdTcProviderContentParam);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        String breedCode=pdTcProviderContentParam.getBreedCode();
        int count=pd14112801Logic.queryProviderContent(pdTcProviderContentParam);
        if(count<=0){
            int num=pd14112801Logic.saveProviderContent(pdTcProviderContentParam);
            if(num>0){
                listMat=pd14112601Logic.queryProviderContent(breedCode,getDivId);
            }
        }else{
            PdTcProviderContentParam pdTcProviderContent=new PdTcProviderContentParam();
            pdTcProviderContent.setMessage("1");
            listMat.add(pdTcProviderContent);
        }
        return listMat;
    }
}
