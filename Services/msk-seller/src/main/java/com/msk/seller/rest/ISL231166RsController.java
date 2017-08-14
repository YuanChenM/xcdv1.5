package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL23116601;
import com.msk.seller.bean.ISL231166RsParam;
import com.msk.seller.bean.ISL231166RsResult;
import com.msk.seller.bean.PdClassesCode;
import com.msk.seller.logic.ISL231166RsLogic;
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
public class ISL231166RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231166RsController.class);
    @Autowired
    private ISL231166RsLogic iSL231166RsLogic;
    /**
     * 增加卖家产品类别接口
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slPdClasses/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231166Validator")
    public RsResponse<ISL231166RsResult> createSLPdClasses(@RequestBody RsRequest<ISL231166RsParam> param) {
        logger.debug("增加卖家产品类别接口");
        RsResponse<ISL231166RsResult> rs = new RsResponse<ISL231166RsResult>();
        ISL231166RsParam iSL231166RsParam = param.getParam();
        //查询传过来的iscode是否存在sl_seller中
        BaseParam baseParam = new BaseParam();
        String isCode = iSL231166RsParam.getSlCode();
        baseParam.setFilter("slCode", StringUtil.toSafeString(iSL231166RsParam.getSlCode()));
        List<SlSeller> slSellerList = iSL231166RsLogic.findIsCode(baseParam);
        if(slSellerList.isEmpty()){
            throw new BusinessException("传入参数有误请重新输入！");
        }
        List<PdClassesCode> list =iSL231166RsParam.getPdClassesCodeList();
        for(int i = 0; i < list.size(); i++){
            String pdClassesCode = list.get(i).getPdClassesCode();
            ISL23116601 iSL23116601 = new ISL23116601();
            iSL23116601.setCrtId(iSL231166RsParam.getCrtId());
            iSL23116601.setSlCode(iSL231166RsParam.getSlCode());
            iSL23116601.setPdClassesCode(pdClassesCode);
            int num = iSL231166RsLogic.savaSLPdClasses(iSL23116601);
            if(num == 0){
                throw new BusinessException("保存失败");
            }
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("增加卖家产品类别成功");
        return rs;
    }
}
