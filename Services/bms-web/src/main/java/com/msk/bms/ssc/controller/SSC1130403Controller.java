package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.seller.bean.SlProductRsBean;
import com.msk.ssc.bean.SSC11304Param;
import com.msk.ssc.bean.SSC11304ProductBean;
import com.msk.ssc.bean.SSC11304Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 业务控制器：管理合同产品
 * Created by xia_xiaojie on 2016/8/31.
 */
@Controller
@RequestMapping("/SSC1130403")
public class SSC1130403Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SSC1130403Controller.class);

    /**
     * 查询合同产品
     */
    @RequestMapping(value = "/product/search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11304ProductBean> searchProducts(SSC11304Param ssc11304Param) {
        List<SSC11304ProductBean> productBeans = ISSCContractRestUtil.findProductsByContractId(ssc11304Param);
        this.calculateProducts(productBeans);
        this.renderCSS(productBeans);

        PageResult<SSC11304ProductBean> pageResult = new PageResult<SSC11304ProductBean>();
        pageResult.setData(productBeans);
        return pageResult;
    }

    /**
     * 计算产品的箱数、重量、金额和合计
     */
    private void calculateProducts(List<SSC11304ProductBean> productBeans) {
        BigDecimal thousand = new BigDecimal(NumberConst.IntDef.INT_THOUSAND);
        int totalBoxes = NumberConst.IntDef.INT_ZERO;   //总箱数
        BigDecimal totalTonnages = BigDecimal.ZERO;     //总吨数
        BigDecimal totalValues = BigDecimal.ZERO;       //总货值
        BigDecimal totalPayments = BigDecimal.ZERO;     //预付款总额

        for (SSC11304ProductBean productBean : productBeans) {
            productBean.setProductTonnage(DecimalUtil.divide(productBean.getProductQua(), thousand));  //千克转化为吨
            totalBoxes += productBean.getProductBox();
            totalTonnages = DecimalUtil.add(totalTonnages, productBean.getProductTonnage());
            totalValues = DecimalUtil.add(totalValues, productBean.getProductValue());
            totalPayments = DecimalUtil.add(totalPayments, productBean.getPaymentAmount());
        }

        //将合计值保存在list的第一个元素中
        if (!CollectionUtils.isEmpty(productBeans)) {
            SSC11304ProductBean productBean = productBeans.get(NumberConst.IntDef.INT_ZERO);
            productBean.setTotalBoxes(totalBoxes);
            productBean.setTotalTonnages(totalTonnages);
            productBean.setTotalValues(totalValues);
            productBean.setTotalPayments(totalPayments);
        }
    }

    /**
     * 文字加样式
     */
    private void renderCSS(List<SSC11304ProductBean> productBeans) {
        String red = "<font color='red'>未分配</font>";
        String purple = "<font color='purple'>部分分配</font>";
        String green = "<font color='green'>已分配完</font>";

        for (SSC11304ProductBean productBean : productBeans) {
            String deliveryPlan = productBean.getDeliveryPlan();
            if (deliveryPlan.equals("未分配")) {
                productBean.setDeliveryPlan(red);
            }
            else if (deliveryPlan.equals("部分分配")) {
                productBean.setDeliveryPlan(purple);
            }
            else if (deliveryPlan.equals("已分配完")) {
                productBean.setDeliveryPlan(green);
            }
        }
    }

    /**
     * 删除合同产品
     */
    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteProduct(SSC11304ProductBean productBean) {
        productBean.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<SSC11304Result> respResult = ISSCContractRestUtil.deleteProduct(productBean);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }
        return respResult.getStatus();
    }

    /**
     * 修改合同产品
     */
    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateProduct(SSC11304ProductBean productBean) {
        this.handleBean(productBean);
        productBean.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<SSC11304Result> respResult = ISSCContractRestUtil.updateProduct(productBean);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }
        return respResult.getStatus();
    }

    /**
     * 计算产品重量、货值和预付款
     */
    private void handleBean(SSC11304ProductBean productBean) {
        BigDecimal kg = DecimalUtil.multiply(productBean.getWeightVal(), new BigDecimal(productBean.getProductBox()));
        productBean.setProductQua(kg);

        BigDecimal productValue = DecimalUtil.multiply(kg, productBean.getSettkementStandardPrice());
        productBean.setProductValue(productValue);

        BigDecimal firstAmount = DecimalUtil.multiply(productValue, productBean.getDownPayment());
        productBean.setPaymentAmount(DecimalUtil.divide(firstAmount, new BigDecimal(NumberConst.IntDef.INT_HUNDRED)));
    }

    /**
     * 弹出新增产品对话框
     */
    @RequestMapping(value = "/product/add/pre", method = RequestMethod.POST)
    public String preAddProduct(SSC11304Param ssc11304Param, Model model) {
        ISLSellerRsParam islSellerRsParam = new ISLSellerRsParam();
        islSellerRsParam.setSlCode(ssc11304Param.getPurchaserCode());
        islSellerRsParam.setProdEpId(ssc11304Param.getSupplierId());
        List<SlProductRsBean> slProductRsBeans = ISSCRestUtil.getSlProductList(islSellerRsParam);

        model.addAttribute("productBeans", slProductRsBeans);
        model.addAttribute("contractId", ssc11304Param.getContractId());
        return "ssc/SSC1130403";
    }

    /**
     * 添加合同产品
     */
    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    @ResponseBody
    public String addProduct(SSC11304ProductBean productBean) {
        if (this.existProduct(productBean)) {
            throw new BusinessException("当前产品已经被别人添加了，请重新加载数据进行修改！");
        }
        this.handleBean(productBean);
        productBean.setCrtId(super.getLoginUser().getEmplId());
        return ISSCContractRestUtil.saveProduct(productBean);
    }

    /**
     * 根据合同ID，产品CODE，判断合同中是否已存在该产品
     */
    private boolean existProduct(SSC11304ProductBean productBean) {
        SSC11304Param ssc11304Param = new SSC11304Param();
        ssc11304Param.setContractId(productBean.getContractId());
        ssc11304Param.setPdCode(productBean.getPdCode());
        RsResponse<SSC11304Result> respResult = ISSCContractRestUtil.findPd(ssc11304Param);
        if (respResult.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            return true;
        }
        return false;
    }

}
