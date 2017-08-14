package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PDTncProviderJinBean;
import com.msk.product.bean.PDtncProviderBean;
import com.msk.product.logic.PD14114902Logic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 产品包装信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping("pd14114902")
public class PD14114902Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PD14114902Controller.class);
    @Autowired
    private PD14114902Logic pd14114902Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 进入列表页面*
     *
     * @param model model
     * @param bean
     * @return "pd/PD14114901"页面
     * @author xhy
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, PDtncProviderBean bean, @RequestParam(required = false) String proFixDateShow,
                       @RequestParam(required = false) String proRaiseDateShow) {
        model.addAttribute("bean", bean);
        model.addAttribute("proFixDateShow", proFixDateShow);
        model.addAttribute("proRaiseDateShow", proRaiseDateShow);
        return "pd/PD14114902";
    }


    //保存修改数据

    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PDtncProviderBean> saveMar(Model model, PDtncProviderBean bean, @RequestParam(value = "proFixDateString", required = false) String proFixDateString) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        if (StringUtils.isNotBlank(proFixDateString)) {
            bean.setFixDate(DateTimeUtil.parseDate(proFixDateString, DateTimeUtil.FORMAT_DATE_YYYYMMDD));
            bean.setDiscussStatus(NumberConst.IntDef.INT_ONE);//结案
            bean.setUpdId(param.getUpdId());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
            bean.setUpdTime(new Date());
            /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
            int modifoyJan = this.pd14114902Logic.modify(bean);
            if (modifoyJan <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("设置供应商结案日失败!");
        }

        param.setFilter("standardId",bean.getStandardId().toString());
        param.setFilter("tncStdItemId",bean.getTncStdItemId());
        param.setFilter("discussStatus", String.valueOf(NumberConst.IntDef.INT_TWO));
        return this.pd14114902Logic.queryListPro(param, bean);
    }
    //设为禁用数据

    @RequestMapping(value = "forbid",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PDTncProviderJinBean queryListPro(Model model, PDtncProviderBean bean) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        bean.setDiscussStatus(NumberConst.IntDef.INT_TWO);//禁止日
        /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
        bean.setFixDate(new Date());
        /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
        bean.setUpdId(param.getUpdId());
        bean.setUpdTime(new Date());
        int modifoyJan = this.pd14114902Logic.modify(bean);
        if (modifoyJan <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("设置市场需求结案日失败!");


        param.setFilter("standardId",bean.getStandardId().toString());
        param.setFilter("tncStdItemId",bean.getTncStdItemId());
        param.setFilter("discussStatus", String.valueOf(NumberConst.IntDef.INT_TWO));

        List<PDtncProviderBean> list = this.pd14114902Logic.queryListPro(param, bean);

        //设为禁止日
        List<PDtncProviderBean> listNo = this.pd14114902Logic.queryListProNo(param, bean);

        PDTncProviderJinBean jinPro = new PDTncProviderJinBean();

        jinPro.setPdTncProList(list);
        jinPro.setPdTncProNoList(listNo);
        return jinPro;
    }
}
