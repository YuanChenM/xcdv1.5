package com.msk.seller.controller;

import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.bean.SlEpDdBean;
import com.msk.seller.logic.SL24110112Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by writer on 2016/1/31.
 */
@Controller
@RequestMapping("SL24110112")
public class SL24110112Controller extends BaseUploadController {

    @Autowired
    private SL24110112Logic sl24110112Logic;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(String jsp_epId2, MultipartFile file, HttpServletResponse response, SlEpDdBean slEpDdBean, HttpServletRequest request) throws IOException {
        //slEpDdBean.setUpdId(super.getLoginUser().getUpdId());
//        String epId=request.getSession().getAttribute("jsp_epId2").toString();
//        if(null!=epId){
//            slEpDdBean.setEpId(Long.valueOf(epId));
//        }
        String epId = StringUtil.toSafeString(jsp_epId2);
        if(!StringUtil.isNullOrEmpty(epId)){
            slEpDdBean.setEpId(StringUtil.toLong(jsp_epId2));
        }
        slEpDdBean.setUpdId(getLoginUser().getEmplId());
        this.sl24110112Logic.updateSlEPDd(slEpDdBean);
        if (file.getSize() == 0) {
            super.callBack(null, "保存成功", response);
        } else {
            //图片上传到ftp里面
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/";
            String uploadFileName = BusinessConst.SLPath.EPTESTING + slEpDdBean.getDdId();
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
            super.callBack(null, "保存成功", response);
        }
    }

    @RequestMapping("delete")
    public void deleteETeam(String jsp_epId2, SlEpDdBean slEpDdBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String epId=request.getSession().getAttribute("jsp_epId2").toString();
//        if(null!=epId){
//            slEpDdBean.setEpId(Long.valueOf(epId));
//        }
        String epId = StringUtil.toSafeString(jsp_epId2);
        if(!StringUtil.isNullOrEmpty(epId)){
            slEpDdBean.setEpId(StringUtil.toLong(jsp_epId2));
        }
        slEpDdBean.setUpdId(getLoginUser().getEmplId());
        this.sl24110112Logic.delteSlEPDd(slEpDdBean);
        String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/";
        String uploadFileName = BusinessConst.SLPath.EPTESTING + slEpDdBean.getDdId();
        SLUploadFile slUploadFile = new SLUploadFile();
        slUploadFile.deleteFileFromFtp(uploadFilePath, uploadFileName);
        super.callBack(null, "删除成功", response);
    }

}
