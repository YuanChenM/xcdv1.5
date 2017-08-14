package com.msk.seller.rest;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlPdArtno;
import com.msk.core.entity.SlProduct;
import com.msk.core.entity.SlProductStatusHis;
import com.msk.seller.bean.*;
import com.msk.seller.logic.ISLProductRsLogic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu_yan2 on 2016/6/21.
 */
@RestController
public class ISLProductRsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISLProductRsController.class);

    @Autowired
    private ISLProductRsLogic ISLProductRsLogic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 查询卖家产品目录报表
     * @param param
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/SlProReport/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<SlProductRsBean>> searchSlProInfo(@RequestBody RsRequest<SlProductRsParam> param){
        RsResponse<List<SlProductRsBean>> result = new RsResponse<List<SlProductRsBean>>();

        SlProductRsParam slProductRsParam = param.getParam();

        List<SlProductRsBean> slProductRsBeans = ISLProductRsLogic.getSlProInfo(slProductRsParam);
        if (!CollectionUtils.isEmpty(slProductRsBeans)) {
            result.setResult(slProductRsBeans);
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("卖家产品目录报表");
        } else {
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("卖家产品目录报表");
        }

        return result;
    }

    /**
     * 查询卖家产品信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/slProduct/list",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLSProductRsResult> searchSlProduct(@RequestBody RsRequest<ISLSellerRsParam> param){
        RsResponse<ISLSProductRsResult> result = new RsResponse<ISLSProductRsResult>();
        ISLSellerRsParam islSellerRsParam = param.getParam();
        List<SlProductRsBean> slProductRsBeans = ISLProductRsLogic.getSlProductInfo(islSellerRsParam);
        if (!CollectionUtils.isEmpty(slProductRsBeans)) {
            ISLSProductRsResult islSProductRsResult = new ISLSProductRsResult();
            islSProductRsResult.setSlProductList(slProductRsBeans);
            result.setResult(islSProductRsResult);
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("查询卖家产品编码和名称成功");
        } else {
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("查询卖家产品编码和名称失败");
        }
        return result;
    }

    /**
     * 查询卖家产品属性
     * @param param
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/slProduct/getPdCode",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISLProductValidator")
    public RsResponse<ISLProductRsResult> getPdCode(@RequestBody RsRequest<ISLProductRsParam> param){
        RsResponse<ISLProductRsResult> rs = new RsResponse<ISLProductRsResult>();
        ISLProductRsParam islProductRsParam = param.getParam();
        ISLProductRsResult result = ISLProductRsLogic.getPdCode(islProductRsParam);

        // 查询的卖家产品属性与输入的卖家货号一一对应，否则报错
        boolean success = false;
        if (CollectionUtils.isNotEmpty(result.getProductList()) && result.getProductList().size() == islProductRsParam.getSlList().size()) {
            // 查询的卖家产品属性与输入的卖家货号匹配的条数
            int match = 0;
            for (SlPdArtno in : islProductRsParam.getSlList()) {
                for (SlPdArtno out : result.getProductList()) {
                    // 如果卖家编码显示码和卖家货号码匹配
                    if (out.getSlCodeDis().equals(in.getSlCodeDis()) && out.getSlPdArtno().equals(in.getSlPdArtno())) {
                        // 找到一条匹配，跳出
                        match ++;
                        break;
                    }
                }
            }
            // 如果查询出的产品条数与匹配的条数相同，则认为查询的卖家产品属性与输入的卖家货号一一对应
            if(result.getProductList().size() == match) {
                success = true;
            }
        }

        if (success) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品属性成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("未查询到卖家产品属性信息");
        }
        return rs;
    }

    /**
     * 卖家产品状态变更记录接口
     * @param param
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/slProduct/statusChange",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLProductRsResult> statusChange(@RequestBody RsRequest<ISLProductRsParam> param){
        RsResponse<ISLProductRsResult> rs = new RsResponse<ISLProductRsResult>();
        ISLProductRsParam islProductRsParam = param.getParam();
        List<SlProduct> productList = ISLProductRsLogic.getProductList(islProductRsParam);
        boolean success = true;
        if(CollectionUtils.isNotEmpty(productList)) {
            // 将查询到的信息记载进履历表
            List<SlProductStatusHis> hisList = new ArrayList<SlProductStatusHis>();
            Long hisId = this.commonLogic.maxIds("SL_PRODUCT_STATUS_HIS", productList.size() + NumberConst.IntDef.INT_ONE);
            for (SlProduct product : productList) {
                SlProductStatusHis his = new SlProductStatusHis();
                BeanUtils.copyProperties(product, his);
                his.setHisId(hisId);
                his.setCrtTime(DateTimeUtil.getCustomerDate());
                hisId --;
                hisList.add(his);
            }
            int savedLines = ISLProductRsLogic.saveProductStatusHis(hisList);
            if (savedLines != hisList.size()) {
                success = false;
            } else {
                success = true;
            }
        }
        if (success) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("卖家产品状态变更记录成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("卖家产品状态变更记录失败");
        }
        return rs;
    }

}
