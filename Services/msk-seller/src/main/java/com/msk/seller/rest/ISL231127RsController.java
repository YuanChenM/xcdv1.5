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
public class ISL231127RsController extends BaseRsController{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231127RsController.class);
    @Autowired
    private SL24110300301Logic sl24110300301Logic;
    /**
     * 增加新的企业专业资质证照
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpCert/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231127Validator")
    public RsResponse<ISL231127RsResult> createSLEpCertItem(@RequestBody RsRequest<ISL231127RsParam> param) {
        List<ISL231127CertInfoList> ISL231127CertInfoLists =param.getParam().getCertInfoList();
        for(int i = 0;i< ISL231127CertInfoLists.size();i++){
            SlEpCert slEpCert1 = new SlEpCert();
            SlEpCert slEpCert2=this.sl24110300301Logic.findSlEpCertById(ISL231127CertInfoLists.get(i).getEpId(), ISL231127CertInfoLists.get(i).getCertId().toString());
            if(slEpCert2!=null){
                throw new BusinessException("您输入了已经存在的证照");
            }
            slEpCert1.setEpId(ISL231127CertInfoLists.get(i).getEpId());
            slEpCert1.setCertId(ISL231127CertInfoLists.get(i).getCertId());
            slEpCert1.setCrtId(ISL231127CertInfoLists.get(i).getCrtId());
            slEpCert1.setCertName(ISL231127CertInfoLists.get(i).getCertName());
            /**根据用户企业Id查询最大的certseq*/
            Long maxCertSeq=this.sl24110300301Logic.findMaxCertSeq(ISL231127CertInfoLists.get(i).getEpId());
            slEpCert1.setCertSeq(maxCertSeq);
            /**保存slepcert*/
            this.sl24110300301Logic.saveSlEpCert(slEpCert1);
            List<SlEpCertItem> slEpCertItems= ISL231127CertInfoLists.get(i).getCertItemList();
            List<SlEpCertItem> slEpCertItemList=new ArrayList<SlEpCertItem>();
            for(int j=0;j<slEpCertItems.size();j++){
                SlEpCertItem slEpCertItem = new SlEpCertItem();
                slEpCertItem.setEpId(ISL231127CertInfoLists.get(i).getEpId());
                slEpCertItem.setCertId(ISL231127CertInfoLists.get(i).getCertId());
                slEpCertItem.setCertSeq(maxCertSeq);
                slEpCertItem.setCertItemId(slEpCertItems.get(j).getCertItemId());
                slEpCertItem.setCertItemName(slEpCertItems.get(j).getCertItemName());
                slEpCertItem.setCertItemValue(slEpCertItems.get(j).getCertItemValue());
                slEpCertItemList.add(slEpCertItem);
            }
            this.sl24110300301Logic.saveSlEpCertItem(slEpCertItemList);
        }
        RsResponse<ISL231127RsResult> rs = new RsResponse<ISL231127RsResult>();
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("新增成功");
        return rs;
    }
}
