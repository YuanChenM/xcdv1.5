package com.msk.seller.controller;


import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEpWorkshop;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL2411010501Logic;
import com.msk.seller.logic.SL24110300501Logic;
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
@RequestMapping("SL2411010501")
public class SL2411010501Controller extends BaseUploadController {

    @Autowired
    private SL2411010501Logic sL2411010501Logic;
    @Autowired
    private SL24110300501Logic sl24110300501Logic;
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(String jsp_epId2,MultipartFile file,SlEpWorkshop slEpWorkshop,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //if(null==request.getSession().getAttribute("jsp_epId2")){
        if(StringUtil.isNullOrEmpty(jsp_epId2)){
            throw new BusinessException("没有获取到账号信息，请重新进入");
        }
        if(null==slEpWorkshop.getWorkshopName() || "".equals(slEpWorkshop.getWorkshopName().trim())){
            throw new BusinessException("请填写车间名称信息");
        }
        //Long slEpId = (Long)request.getSession().getAttribute("jsp_epId2");
        Long slEpId = StringUtil.toLong(jsp_epId2);
        slEpWorkshop.setEpId(slEpId);
        //this.sl24110300501Logic.findIfExist(slEpId,slEpWorkshop.getWorkshopName());
        slEpWorkshop.setUpdId(getLoginUser().getEmplId());
        this.sL2411010501Logic.updateSlEpWorkshop(slEpWorkshop);
        if(null!=file){
            if(file.getSize()!=0){
                String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
                String uploadFileName=BusinessConst.SLPath.EPWORKSHOPDES+slEpWorkshop.getWorkshopId();
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
            }
        }
        super.callBack(null,"更新成功",response);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public void delete(String jsp_epId2,SlEpWorkshop slEpWorkshop,HttpServletRequest request,HttpServletResponse response) throws IOException {
       // Long slEpId = (Long)request.getSession().getAttribute("jsp_epId2");
        Long slEpId = StringUtil.toLong(jsp_epId2);
        slEpWorkshop.setEpId(slEpId);
        this.sL2411010501Logic.deleteSlEpWorkshop(slEpWorkshop);
        String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpId + "/";
        String uploadFileName = BusinessConst.SLPath.EPHON+slEpWorkshop.getWorkshopId();
        SLUploadFile slUploadFile = new SLUploadFile();
        slUploadFile.deleteFileFromFtp(uploadFilePath, uploadFileName);
        super.callBack(null, "删除成功", response);
    }
}
