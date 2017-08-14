package com.msk.seller.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEpCap;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110300402Logic;
import com.msk.seller.logic.SL241103006Logic;
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
 * Created by writer on 2016/1/27.
 */
@Controller
@RequestMapping("SL241103006")
public class SL241103006Controller extends BaseUploadController {

    @Autowired
    private SL241103006Logic sL241103006Logic;
    @Autowired
    private SL24110300402Logic sL24110300402Logic;
    /**企业生产能力信息*/
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void save(String jspSL241103006_epId,MultipartFile labFile,MultipartFile ddEquFile,MultipartFile controllFile,MultipartFile qualityFile,SlEpCap slEpCap,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //if(null==request.getSession().getAttribute("jsp_epId") || "".equals(request.getSession().getAttribute("jsp_epId"))){
        if(StringUtil.isNullOrEmpty(jspSL241103006_epId)){
            throw new BusinessException("请先创建账号信息");
        }
        /**获取用户存储的当前epID*/
        // Long slEpId = (Long)request.getSession().getAttribute("jsp_epId");
        Long slEpId = StringUtil.toLong(jspSL241103006_epId);

        slEpCap.setEpId(slEpId);
        /**查询用户是否已经录入信息了 如果已经录入就更新，如果没有可以正常保存*/
        SlEpCap slCap=this.sL24110300402Logic.findSlEpCapIfExist(slEpId);
        if(null==slCap){
            //Modified by xia_xiaojie on 2016/6/22. Modified start.
            //slEpCap.setCrtId(super.getLoginUser().getCrtId());
            //Modified end.
            slEpCap.setCrtId(getLoginUser().getEmplId());
            this.sL24110300402Logic.saveSlEpCap(slEpCap);
        }else{
            /**更新生产能力表数据*/
            slEpCap.setUpdId(getLoginUser().getEmplId());
            this.sL241103006Logic.updateSlEpCap(slEpCap);
        }
        /**保存实验室图片*/
        if(labFile.getSize()!=0){
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
            String uploadFileName=BusinessConst.SLPath.EPLABORATORY;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(labFile,uploadFilePath,uploadFileName);
        }
        /**保存检测设备图片*/
        if(ddEquFile.getSize()!=0){
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
            String uploadFileName=BusinessConst.SLPath.EPTESTING;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(ddEquFile,uploadFilePath,uploadFileName);
        }
        /**保存品控组织图片*/
        if(controllFile.getSize()!=0){
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
            String uploadFileName=BusinessConst.SLPath.EPQCORGANIZE;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(controllFile,uploadFilePath,uploadFileName);
        }
        /**保存质量控制系统图片*/
        if(qualityFile.getSize()!=0){
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
            String uploadFileName=BusinessConst.SLPath.EPQUALITY;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(qualityFile, uploadFilePath, uploadFileName);
        }
        super.callBack(null,"保存成功",response);
    }
}
