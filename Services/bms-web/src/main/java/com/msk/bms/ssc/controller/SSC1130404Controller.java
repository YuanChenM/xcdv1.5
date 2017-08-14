package com.msk.bms.ssc.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11304PackageBean;
import com.msk.ssc.bean.SSC11304Param;
import com.msk.ssc.bean.SSC11304ProductBean;
import com.msk.ssc.bean.SSC11304Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by xia_xiaojie on 2016/9/1.
 */
@Controller
@RequestMapping("/SSC1130404")
public class SSC1130404Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SSC11304Controller.class);

    /**
     * 弹出添加包材对话框
     */
    @RequestMapping(value = "/package/add/pre", method = RequestMethod.POST)
    public String preAddPackage(SSC11304Param ssc11304Param, Model model) {
        RsResponse<SSC11304Result> respResult = ISSCContractRestUtil.findContractProducts(ssc11304Param);
        List<SSC11304ProductBean> pdBeans = respResult.getResult().getPdPageResult();
        model.addAttribute("pdBeans", pdBeans);
        model.addAttribute("contractId", ssc11304Param.getContractId());
        return "ssc/SSC1130404";
    }

    /**
     * 添加产品包材
     */
    @RequestMapping(value = "/package/add", method = RequestMethod.POST)
    @ResponseBody
    public String addPackage(SSC11304PackageBean packageBean) {
        if (this.existPackage(packageBean)) {
            throw new BusinessException("当前产品的包材已经被别人添加了，请重新加载数据进行修改！");
        }
        return ISSCContractRestUtil.saveContractPackageM(packageBean);
    }

    /**
     * 根据合同ID，产品ID，判断合同中是否已存在该产品的包材
     */
    private boolean existPackage(SSC11304PackageBean packageBean) {
        BaseParam baseParam = new BaseParam();
        Map<String, Object> filterMap = baseParam.getFilterMap();
        filterMap.put("contractId", packageBean.getContractId());
        filterMap.put("detailId", packageBean.getDetailId());
        RsResponse<Long> respResult = ISSCContractRestUtil.findPack(baseParam);
        if (respResult.getResult().intValue() > NumberConst.IntDef.INT_ZERO) {
            return true;
        }
        return false;
    }

    /**
     * 修改产品包材
     */
    @RequestMapping(value = "/package/update", method = RequestMethod.POST)
    @ResponseBody
    public String updatePackage(SSC11304PackageBean packageBean) {
        RsResponse<SSC11304Result> respResult = ISSCContractRestUtil.updateContractPackgeM(packageBean);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }
        return respResult.getStatus();
    }

}
