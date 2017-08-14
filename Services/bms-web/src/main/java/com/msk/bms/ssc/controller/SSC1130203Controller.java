package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.msk.bms.ssc.bean.SSC11302Param;
import com.msk.bms.ssc.controller.common.ISSCBidRestUtil;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.ssc.bean.SSC11302RsBeen;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Controller
 *
 * @author zhao_chen1
 */
@Controller
@RequestMapping("SSC1130203")
public class SSC1130203Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC1130203Controller.class);

    /**
     * 初始化页面
     *
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SSC11302Param param, Model model) {
        /*参数epid需要修改确认*/
        logger.debug("调取卖家下产品信息");
        ISLSellerRsParam islSellerRsParam = new ISLSellerRsParam();
        islSellerRsParam.setSlCode(param.getSlCode());
        islSellerRsParam.setProdEpId(param.getSupplierId());
        //localhost
        //param.setSlCode("20");
        model.addAttribute("pdList", ISSCRestUtil.getSlProductList(islSellerRsParam));
        model.addAttribute("ssc11302Param", param);

        return "ssc/SSC1130203";
    }

    @RequestMapping(value = "insertBidProductDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    String insertBidProductDetail(SSC11302RsBeen ssc11302RsBeen) {
        logger.info("新增产品信息");
        //新增时排他检测
        if(ssc11302RsBeen.getDetailId()== null){
            SSC11302Param ssc11302Param = new SSC11302Param();
            ssc11302Param.setBidId(ssc11302RsBeen.getBidId().toString());
            ssc11302Param.setPdCode(ssc11302RsBeen.getPdCode());
            RsResponse<SSC11302RsBeen> rsPdResponse = this.findPD(ssc11302Param);
            if(rsPdResponse.getStatus().equals(SystemConst.RsStatus.SUCCESS)){
                throw new BusinessException("该数据已经被添加，请刷新页面再试!");
            }
        }


        BigDecimal weight = DecimalUtil.multiply(new BigDecimal(ssc11302RsBeen.getProductBox()), ssc11302RsBeen.getWeightVal()).multiply(ssc11302RsBeen.getSettkementStandardPrice());
        String returnCode =  ssc11302RsBeen.getBidId().toString();
        ssc11302RsBeen.setProductValue(weight);

        super.setCommonParam(ssc11302RsBeen);
        ssc11302RsBeen.setUpdTime(DateTimeUtil.getCustomerDate());

        ssc11302RsBeen.setProductQua((DecimalUtil.multiply(new BigDecimal(ssc11302RsBeen.getProductBox()), ssc11302RsBeen.getWeightVal())));
        int result = ISSCBidRestUtil.insertBidProductDetail(ssc11302RsBeen);
        if (result == NumberConst.IntDef.INT_ONE) {
            return returnCode;
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }



    /**
     * 根据合同id,pdCodes  查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "findBidPD", method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<SSC11302RsBeen> findPD(SSC11302Param param) {
        RsRequest<SSC11302Param> request = new RsRequest<SSC11302Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindBidPd();
        //String url = "http://localhost:8080/msk-ssc-api/api/ssc/findBidPd";
        RsResponse<SSC11302RsBeen> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11302RsBeen>>() {
        });
        return rsResponse;
    }


}
