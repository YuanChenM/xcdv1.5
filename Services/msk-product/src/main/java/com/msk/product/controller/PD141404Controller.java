package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdNormsStd;
import com.msk.product.bean.*;
import com.msk.product.logic.PD141404Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * controller 卖家单个产品及状态在线管控一览
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "PD141404")
public class PD141404Controller extends BaseController {
    @Autowired
    private PD141404Logic pd141404Logic;

    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 实例化页面
     *
     * @return 卖家产品信息页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    private String init(Model model, PD141403Bean beans) {

        BaseParam param = new BaseParam();
        if (StringUtils.isBlank(beans.getSlId()) || StringUtils.isBlank(beans.getSlCodeDis()) || StringUtils.isBlank(beans.getSlPdId()) || StringUtils.isBlank(beans.getStandardId())) {
            throw new BusinessException("数据异常,请联系管理员!");
        }
        param.setFilter("slId", beans.getSlId());
        param.setFilter("slCodeDis", beans.getSlCodeDis());
        param.setFilter("slPdId", beans.getSlPdId());
        param.setFilter("standardId", beans.getStandardId());
        if(StringUtils.isBlank(beans.getClassestreeCode())) throw new BusinessException("包装数据异常,请联系管理员");
        param.setFilter("classestreeCode", beans.getClassestreeCode());
        String showTable = beans.getClassestreeCode().substring(NumberConst.IntDef.INT_TWO,NumberConst.IntDef.INT_THREE);
        model.addAttribute("showTable", showTable);
        if (StringUtils.isBlank(beans.getShowDate())) {
            beans.setShowDate(formatter.format(new Date()));
            PD141403Bean polNum = this.pd141404Logic.findMaxId(param);
            if (polNum != null) {
                beans.setPdLot(polNum.getPdLot());
            }
        }

        model.addAttribute("beans", beans);
        param.setFilter("showDate", beans.getShowDate());
        if(showTable.equals(String.valueOf(NumberConst.IntDef.INT_ONE))) {
            //产品原种种源标准指标 数据
            List<PD141404OrgBean> listOrg = this.pd141404Logic.findListOrg(param);
            if (listOrg.size() > NumberConst.IntDef.INT_ZERO) {
                model.addAttribute("listOrg", listOrg);
            } else {
                throw new BusinessException("没有查询到指定的产品原种种源标准!请联系管理员");
            }
            // end

            //产品原种饲养标准指标 数据
            List<PD141404FedBean> listFed = this.pd141404Logic.findListFed(param);
            if (listFed.size() > NumberConst.IntDef.INT_ZERO) {
                model.addAttribute("listFed", listFed);
            } else {
                throw new BusinessException("没有查询到指定的产品饲养标准!请联系管理员");
            }
        }
        // end

        //产品加工技术指标 数据
        List<PD141404MctBean> listMct = this.pd141404Logic.findListMct(param);
        if (listMct.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("listMct", listMct);
        } else {
            throw new BusinessException("没有查询到指定的产品加工技术!请联系管理员");
        }
        // end

        //产品加工质量 数据
        List<PD141404TncBean> listTnc = this.pd141404Logic.findInitListTnc(param);
        if (listTnc != null && listTnc.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("listTnc", listTnc);
        } else {
            throw new BusinessException("没有查询到指定的产品加工质量!请联系管理员");
        }
        // end

        //产品通用质量
        List<PD141404GnqBean> listGnq = this.pd141404Logic.findInitListGnq(param);
        if (listGnq != null && listTnc.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("listGnq", listGnq);
        } else {
            throw new BusinessException("没有查询到指定的产品通用质量!请联系管理员");
        }
        // end

        //产品安全指标 数据
        List<PD141404SftBean> listSft = this.pd141404Logic.findInitListSft(param);
        if (listSft != null && listSft.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("listSft", listSft);
        } else {
            throw new BusinessException("没有查询到指定的产品通用质量!请联系管理员");
        }
        // end

        //产品运输指标 数据
        List<PD141404TspBean> listTsp = this.pd141404Logic.findInitListTsp(param);
        if (listTsp != null && listTsp.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("listTsp", listTsp);
        } else {
            throw new BusinessException("没有查询到指定的产品通用质量!请联系管理员");
        }
        // end

        //产品包装指标 数据
         List<PD141404NormsChildBean> listNorms = this.pd141404Logic.findInitListNorms(param);
          if (listNorms!=null && listNorms.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("listNorms", listNorms);
        } else {
            throw new BusinessException("没有查询到指定的产品包装标准!请联系管理员");
        }
        // end
        return "pd/PD141404";
    }

    /**
     * 数据保存修改操作
     *
     * @param beans
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    private
    @ResponseBody
    String save(PD141404Bean beans) {

        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        if (beans != null) {
            param.setFilter("slId", beans.getSlId());
            param.setFilter("slCodeDis", beans.getSlCodeDis());
            param.setFilter("slPdId", beans.getSlPdId());
            param.setFilter("standardId", beans.getStandardId());
            param.setFilter("checkDate", beans.getShowDate());
            param.setFilter("pdLot",beans.getPdLot());
            if(StringUtils.isBlank(beans.getClassestreeCode())) throw new BusinessException("包装数据不存在,请联系管理员!");
            param.setFilter("classestreeCode",beans.getClassestreeCode());
            PdNormsStd normsStandardId = this.pd141404Logic.findNormsStandardId(param);
           if(normsStandardId!=null){
               param.setFilter("normStandardId",normsStandardId.getStandardId().toString());
           }
            int size = this.pd141404Logic.getSize(param);
            if (size > NumberConst.IntDef.INT_ZERO) {
                //修改操作
                this.pd141404Logic.updateOnline(beans, param);
                return NumberConst.IntDef.INT_ONE + "";
            } else {
                //新增操作
                this.pd141404Logic.saveData(beans, param);
                return NumberConst.IntDef.INT_TWO + "";
            }
        }
        return NumberConst.IntDef.INT_THREE + "";
    }

}