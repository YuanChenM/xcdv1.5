package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEpCert;
import com.msk.core.entity.SlEpCertItem;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.*;
import com.msk.seller.logic.SL24110103Logic;
import com.msk.seller.logic.SL24110300301Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/22.
 */
@RestController
public class ISL231128RsController extends BaseRsController{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231128RsController.class);
    @Autowired
    private SL24110103Logic sl24110103Logic;
    /**
     * 增加新的企业专业资质证照
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpCert/update",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231128Validator")
    public RsResponse<ISL231128RsResult> updateSLEpCertItem(@RequestBody RsRequest<ISL231128RsParam> param) {
        List<ISL231128CertInfoList> certInfoList =param.getParam().getCertInfoList();
        for(int i =0;i<certInfoList.size();i++){
            Long epId=certInfoList.get(i).getEpId();
            List<SL24110302_1Bean> list = certInfoList.get(i).getCertItemList();
            for(SL24110302_1Bean sl24110302_1Bean : list){
                sl24110302_1Bean.setEpId(epId.toString());
            }
            this.sl24110103Logic.updateItem(list);
        }
        RsResponse<ISL231128RsResult> rs = new RsResponse<ISL231128RsResult>();
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("更新成功");
        return rs;
    }
}
