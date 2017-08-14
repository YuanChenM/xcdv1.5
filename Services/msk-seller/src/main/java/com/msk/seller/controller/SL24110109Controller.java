package com.msk.seller.controller;

import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.seller.bean.SL241103070Bean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110109Logic;
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
@RequestMapping("SL24110109")
public class SL24110109Controller extends BaseUploadController{

    @Autowired
    private SL24110109Logic sl24110109Logic;
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(String jsp_epId2,MultipartFile file,HttpServletResponse response,SL241103070Bean sL241103070Bean,HttpServletRequest request) throws IOException {
        sL241103070Bean.setUpdId(getLoginUser().getUpdId());
        this.sl24110109Logic.update(sL241103070Bean);
        if (file.getSize() == 0) {
            super.callBack(null, "保存成功", response);
        } else {
            //卖家负责人图片
            if(null!=sL241103070Bean && sL241103070Bean.getEleaderFlg().equals("团队负责人")){
                //图片上传到ftp里面
                //String epId=request.getSession().getAttribute("jsp_epId2").toString();
                String epId = StringUtil.toSafeString(jsp_epId2);
                String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
                String uploadFileName=BusinessConst.SLPath.ECTEAM+"01";
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(file,uploadFilePath,uploadFileName);
                super.callBack(null, "保存成功", response);
            }else {
                //图片上传到ftp里面
               // String epId=request.getSession().getAttribute("jsp_epId2").toString();
                String epId = StringUtil.toSafeString(jsp_epId2);
                String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
                String uploadFileName=BusinessConst.SLPath.ECTEAM+sL241103070Bean.getEmemberId();
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(file,uploadFilePath,uploadFileName);
                super.callBack(null, "保存成功", response);
            }

        }
    }

    @RequestMapping("delete")
    public void deleteETeam(String jsp_epId2,SL241103070Bean sL241103070Bean,HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.sl24110109Logic.removeETeam(sL241103070Bean);
       // String epId=request.getSession().getAttribute("jsp_epId2").toString();
        String epId = StringUtil.toSafeString(jsp_epId2);
        String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/";
        //卖家负责人图片
        String uploadFileName="";
        if(null!=sL241103070Bean && sL241103070Bean.getEleaderFlg().equals("团队负责人")){
            uploadFileName=BusinessConst.SLPath.ECTEAM+"01";
        }else{
            uploadFileName=BusinessConst.SLPath.ECTEAM+sL241103070Bean.getEmemberId();
        }

        SLUploadFile slUploadFile = new SLUploadFile();
        slUploadFile.deleteFileFromFtp(uploadFilePath, uploadFileName);
        super.callBack(null,"删除成功",response);
    }

}
