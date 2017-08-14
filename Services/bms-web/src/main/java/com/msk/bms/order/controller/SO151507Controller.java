package com.msk.bms.order.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.result.SO15150701RestResultList;
import com.msk.order.bean.result.SO15150702BeanResult;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_shuai on 2016/8/3.
 */
@Controller
@RequestMapping("SO151507")
public class SO151507Controller extends BaseController {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(SO151507Controller.class);

    /**
     * 页面初始化
     * @return
     */
    @RequestMapping(value="init/{returnId}",method = RequestMethod.POST)
    public String init(@PathVariable(value = "returnId") String returnId, @RequestParam(value = "orderId",
            required = false) String orderId, Model mv){
        logger.info("退货单详细页面初始化");

        if (StringUtils.isNotBlank(orderId)) {
            logger.info("查询订单明细");
//            BaseParam param = new BaseParam();
//            param.setFilter("orderId",orderId);
            RsRequest<Long> request = new RsRequest<Long>();
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setSiteCode("1");
            request.setParam(Long.valueOf(orderId));
            String url = SystemServerManager.SoOrderApiServerManager.getFindPageOrderDetail();
            RsResponse<SoOrder> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SoOrder>>() {
            });
            List<SoOrderDetail> orderDetailList= rsResponse.getResult().getSoOrderDetailList();
            BigDecimal orderAllMoney = BigDecimal.ZERO;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<SO15150702BeanResult> detailList = new ArrayList<SO15150702BeanResult>();
            for(SoOrderDetail soOrderDetail : orderDetailList){
                SO15150702BeanResult so15150702BeanResult = new SO15150702BeanResult();
                try {
                    BeanUtils.copyProperties(so15150702BeanResult,soOrderDetail);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (soOrderDetail.getProDate() != null){
                    so15150702BeanResult.setExpectedDate(sdf.format(soOrderDetail.getProDate()));
                }
                orderAllMoney = orderAllMoney.add(soOrderDetail.getOrderQty().multiply(soOrderDetail.getPdPrice()));
                detailList.add(so15150702BeanResult);
            }
            mv.addAttribute("detailList", detailList);
            mv.addAttribute("orderAllMoney", orderAllMoney);

        }
        if (StringUtils.isNotBlank(returnId)) {
            logger.info("查询退货单明细");
            RsRequest<BaseParam> request = new RsRequest<BaseParam>();
            BaseParam baseParam = new BaseParam();
            baseParam.setFilter("returnId",returnId);
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setSiteCode("1");
            request.setParam(baseParam);
            String url = SystemServerManager.SoOrderApiServerManager.getFindPageReturnOrderDetail();
            RsResponse<SO15150701RestResultList> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SO15150701RestResultList>>() {
            });
            mv.addAttribute("returnGoodsList", rsResponse.getResult().getSo15150701BeanResultList());
            mv.addAttribute("returnId", returnId);
            mv.addAttribute("orderId",orderId);
            if(!CollectionUtils.isEmpty(rsResponse.getResult().getSo15150701BeanResultList())){
                mv.addAttribute("returnCode", rsResponse.getResult().getSo15150701BeanResultList().get(0).getReturnCode());
            }
        }

        return "order/SO151507";
    }
}
