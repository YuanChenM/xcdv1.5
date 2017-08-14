package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SscIntoDetail;
import com.msk.ssc.bean.SSC11309RsBean;
import com.msk.ssc.bean.SSC11310RsBean;
import com.msk.ssc.bean.SSC11310RsParam;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 生产商入库单详细Controller
 *
 * @author liu_yan2
 */
@Controller
@RequestMapping("SSC11310")
public class SSC11310Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11310Controller.class);

    /**
     * 初始化页面
     * type为直接进入列表再进详细 2从合同一览页面进列表再进详细
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{type}", method = RequestMethod.POST)
    public String init(SSC11309RsBean sscIntoBasic, Model model,@PathVariable(value = "type")String type) {
        logger.debug("生产商入库单详细页面初始化");
        if (null != sscIntoBasic ) {
            model.addAttribute("sscIntoBasic", sscIntoBasic);
        }
        model.addAttribute("type", type);
        return "ssc/SSC11310";
    }


    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{intoId}", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11310RsBean> search(SSC11310RsParam ssc11310RsParam, @PathVariable(value = "intoId") Long intoId) {
        RsRequest<SSC11310RsParam> request = new RsRequest<SSC11310RsParam>();
        ssc11310RsParam.setIntoId(intoId);
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(ssc11310RsParam);
//        String url = "http://localhost:8080/msk-ssc-api/api/ssc/findSscIntoDetail";
        String url = SystemServerManager.SellerSupplyChainManage.getFindSscIntoDetail();
        RsResponse<PageResult<SSC11310RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11310RsBean>>>() {});
        return response.getResult();
    }

    /**
     * 修改生产商入库单详细信息
     * @param bean
     * @return
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    @ResponseBody
    public int modifySscIntoDetail(SSC11310RsBean bean, Model model)throws Exception{
        SscIntoDetail sscIntoDetail = new SscIntoDetail();
        sscIntoDetail.setId(bean.getId());
        sscIntoDetail.setReceiveBox(bean.getReceiveBox());
        sscIntoDetail.setReceiveWeight(bean.getReceiveWeight());
        sscIntoDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        sscIntoDetail.setUpdId("admin");
        logger.debug("修改生产商入库单详细信息");
        RsRequest<SscIntoDetail> request = new RsRequest<SscIntoDetail>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(sscIntoDetail);
//        String url="http://localhost:8080/msk-ssc-api/api/ssc/modifySscIntoDetail";
        String url = SystemServerManager.SellerSupplyChainManage.getModifySscIntoDetail();
        RsResponse<Integer> response= RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {});
        return response.getResult();
    }
}
