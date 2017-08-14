package com.msk.seller.controller;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEpCert;
import com.msk.seller.bean.SL24110302_1Bean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110103Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by writer on 2016/1/27.
 */
@Controller
@RequestMapping("SL24110103")
public class SL24110103Controller extends BaseUploadController {

    @Autowired
    private SL24110103Logic sL24110103Logic;

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(String jsp_epId2,MultipartFile file,Long certSeq,String[] certItemSeq,String[] certItemValue,String certId,HttpServletRequest request,HttpServletResponse response) throws IOException {
       // Long slEpId = (Long)request.getSession().getAttribute("jsp_epId2");
        Long slEpId = StringUtil.toLong(jsp_epId2);
        ArrayList<SL24110302_1Bean> lists=new ArrayList<SL24110302_1Bean>();
        if(null==certItemSeq){
            throw new BusinessException("您没有输入任何信息");
        }
        for(int i =0; i <certItemSeq.length;i++){
            SL24110302_1Bean sl24110302_1Bean = new SL24110302_1Bean();
            sl24110302_1Bean.setEpId(slEpId.toString());
            sl24110302_1Bean.setCertItemSeq(certItemSeq[i]);
            sl24110302_1Bean.setCertSeq(certSeq);
            sl24110302_1Bean.setCertItemValue(certItemValue[i]);
            sl24110302_1Bean.setUpdId(getLoginUser().getEmplId());
            sl24110302_1Bean.setUpdTime(DateTimeUtil.getCustomerDate());
            lists.add(sl24110302_1Bean);
        }
        this.sL24110103Logic.updateCertItem(lists);
        if(file.getSize()>0) {
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpId + "/";
            String uploadFileName = BusinessConst.SLPath.CERT + certId;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
        }
        super.callBack(null, "保存成功", response);
    }


    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public void delete(String jsp_epId2,Long certSeq,String[] certItemSeq,String[] certItemValue,String certId,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //Long slEpId = (Long)request.getSession().getAttribute("jsp_epId2");
        Long slEpId = StringUtil.toLong(jsp_epId2);
        ArrayList<SL24110302_1Bean> lists=new ArrayList<SL24110302_1Bean>();
        ArrayList<SlEpCert> slEpCerts = new ArrayList<SlEpCert>();
        if(null!=certItemSeq){
            for(int i =0; i <certItemSeq.length;i++){
                SL24110302_1Bean sl24110302_1Bean = new SL24110302_1Bean();
                SlEpCert slEpCert = new SlEpCert();
                sl24110302_1Bean.setEpId(slEpId.toString());
                slEpCert.setEpId(slEpId);
                sl24110302_1Bean.setCertItemSeq(certItemSeq[i]);
                sl24110302_1Bean.setCertSeq(certSeq);
                slEpCert.setCertSeq(certSeq);
                sl24110302_1Bean.setCertItemValue(certItemValue[i]);
                lists.add(sl24110302_1Bean);
                slEpCerts.add(slEpCert);
            }
            this.sL24110103Logic.deleteDate(lists);
            this.sL24110103Logic.deleteSlEpCert(slEpCerts);
        }else {
            SlEpCert slEpCert = new SlEpCert();
            slEpCert.setEpId(slEpId);
            slEpCert.setCertSeq(certSeq);
            slEpCerts.add(slEpCert);
            this.sL24110103Logic.deleteSlEpCert(slEpCerts);
        }
        String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpId + "/";
        String uploadFileName = BusinessConst.SLPath.CERT +certId;
        SLUploadFile slUploadFile = new SLUploadFile();
        slUploadFile.deleteFileFromFtp(uploadFilePath, uploadFileName);
        super.callBack(null, "删除成功", response);
    }
}