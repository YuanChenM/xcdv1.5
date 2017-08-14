package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlProduct;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231169RsParam;
import com.msk.seller.bean.ISL231169RsResult;
import com.msk.seller.logic.ISL231166RsLogic;
import com.msk.seller.logic.ISL231169RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
@RestController
public class ISL231169RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231169RsController.class);
    @Autowired
    private ISL231166RsLogic iSL231166RsLogic;
    @Autowired
    private ISL231169RsLogic iSL231169RsLogic;
    /**
     * 增加卖家产品接口
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slProduct/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231169Validator")
    public RsResponse<ISL231169RsResult> createSLProduct(@RequestBody RsRequest<ISL231169RsParam> param) {
        logger.debug("增加卖家产品接口");
        RsResponse<ISL231169RsResult> rs = new RsResponse<ISL231169RsResult>();
        ISL231169RsParam iSL231169RsParam = param.getParam();
        //查询传过来的iscode是否存在sl_seller中
        BaseParam baseParam = new BaseParam();
        List<SlProduct> list1 = iSL231169RsParam.getSlPdList();
        for(int i = 0; i<list1.size();i++){
            SlProduct slProduct = list1.get(i);
            baseParam.setFilter("slCode", StringUtil.toSafeString(slProduct.getSlCode()));
            List<SlSeller> slSellerList = iSL231166RsLogic.findIsCode(baseParam);
            if(slSellerList.isEmpty()){
                throw new BusinessException("传入参数有误请重新输入！");
            }
        }
        List<SlProduct> list = iSL231169RsParam.getSlPdList();
        String crtId = getLoginUser().getEmplId();

        for(int j = 0; j < list.size(); j++){
            SlProduct slProduct = list.get(j);
            slProduct.setCrtId(crtId);
            int num = iSL231169RsLogic.savaSLProduct(slProduct);
            if(num == 0){
                throw new BusinessException("保存失败");
            }
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("增加卖家产品成功");
        return rs;
    }
}
