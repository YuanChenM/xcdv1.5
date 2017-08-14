package com.msk.seller.rest;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEpManager;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231138RsResult;
import com.msk.seller.logic.SL241103008Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/17.
 */
@RestController
public class ISL231138RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231138RsController.class);
    @Autowired
    private SL241103008Logic sL241103008Logic;
    /**
     * 增加企业管理团队接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpManager/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.seller.validator.ISL231138Validator")
    public RsResponse<ISL231138RsResult> createSLEpManager(@RequestBody RsRequest<SlEpManager> param) {
        logger.debug("增加企业管理团队接口");
        RsResponse<ISL231138RsResult> rs = new RsResponse<ISL231138RsResult>();
        ISL231138RsResult result = new ISL231138RsResult();
        SlEpManager slEpManager = param.getParam();
        //epId外间关联sl_enterprise（企业资质）
        //查询传过来的epid是否存在sl_enterprise中
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("epId",StringUtil.toSafeString(slEpManager.getEpId()));
        SlEpManager slEpManager1 = sL241103008Logic.findEpId1(baseParam);
        if(null == slEpManager1){
            throw new BusinessException("您输入的企业团队没有对应的企业，请输入正确的epId");
        }
        sL241103008Logic.saveManagerPort(slEpManager);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("增加企业管理团队成功");
        return rs;
    }
}
