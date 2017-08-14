package com.msk.bs.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.*;
import com.msk.bs.logic.*;
import com.msk.common.base.BaseController;
import com.msk.core.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 买手囤货同盟申请.
 *
 * @author gyh
 */
@Controller
@RequestMapping(value = "BS2101108")
public class BS2101108Controller extends BaseController {
    @Autowired
    private BS2101108Logic bS2101108Logic;
    @Autowired
    private IBS2101110RsLogic ibs2101110RsLogic;
    /*@Autowired
    private ProductLogic productLogic;*/

    /**
     * 买手囤货同盟申请编辑信息页面
     * @param model
     * @return 结果
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model,BS2101101Bean bs2101101Bean) {
        BaseParam param=new BaseParam();
        //产品类别信息
        List<PdClasses> pdClassess = null;//productLogic.findPdClasses(param);
        model.addAttribute("pdClassess", pdClassess);
        //产品等级
        List<PdGrade> pdGrades = null;//productLogic.findPdGrade(param);
        model.addAttribute("pdGrades", pdGrades);
        model.addAttribute("bs2101101Bean",bs2101101Bean);
        return "/bs/BS2101108";
    }
    /**
     * 查询二级分类信息
     *
     * @param param param
     * @return 结果
     */
    @RequestMapping(value = "findPdMachining", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdMachining> findPdMachining(BaseParam param) {
        return null;//this.productLogic.findPdMachining(param);
    }
    /**
     * 查询品种信息
     *
     * @param param param
     * @return 结果
     */
    @RequestMapping(value = "findBreed", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdBreed> findBreed(BaseParam param) {
        return null;//this.productLogic.findPdBreed(param);
    }

    /**
     * 查询特征信息
     *
     * @param param param
     * @return 结果
     */
    @RequestMapping(value = "findFeature", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdFeature> findFeature(BaseParam param) {
        return null;//this.productLogic.findPdFeature(param);
    }

    /**
     * 查询包装净重信息
     *
     * @param param param
     * @return 结果
     */
    @RequestMapping(value = "findPdWeight", method = RequestMethod.POST)
    public
    @ResponseBody
    List<PdWeight> findPdWeight(BaseParam param) {
        return null;//this.productLogic.findPdWeight(param);
    }

    /**
     * 查询产品等级信息
     *
     * @param param 参数
     * @return 结果
     * @author gyh
     */
    @RequestMapping(value = "findPdGrade", method = RequestMethod.POST)
    public List<PdGrade> findPdGrade(BaseParam param) {
        return null;//this.productLogic.findPdGrade(param);
    }

    /**
     * 查询已申请的买手同盟信息
     * @param basePageParam
     * @return 结果 数据
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<IBS210111101RsResult> search(BasePageParam basePageParam){
        return ibs2101110RsLogic.findPage(basePageParam, IBS210111101RsResult.class);
    }

    /**
     * 保存买手同盟信息
     * @param rsParam 参数
     * @return 结果
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody String save(IBS210111001RsParam rsParam){
        List<IBS210111001RsParam> paramList=new ArrayList<IBS210111001RsParam>();
        super.setCommonParam(rsParam);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        paramList.add(rsParam);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        ibs2101110RsLogic.editSlBsBuyerLeagues(paramList);
        return "1";
    }
}
