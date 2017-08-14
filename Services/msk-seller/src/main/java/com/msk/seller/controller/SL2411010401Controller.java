package com.msk.seller.controller;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEpHonor;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL2411010401Logic;
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
@RequestMapping("SL2411010401")
public class SL2411010401Controller extends BaseUploadController {

    @Autowired
    private SL2411010401Logic sL2411010401Logic;

    /**
     * @param file 上传的文件
     * @param slEpHonor 更改后的数据
     * @param request 请求
     * @param response 响应
     * @throws IOException IO异常
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(String jsp_epId2,MultipartFile file,SlEpHonor slEpHonor,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //if(null==request.getSession().getAttribute("jsp_epId2")){
        if(StringUtil.isNullOrEmpty(jsp_epId2)){
            throw new BusinessException("没有获取到账号信息，请重新进入");
        }
        if(null==slEpHonor.getHonorDesc() || "".equals(slEpHonor.getHonorDesc().trim())){
            throw new BusinessException("请填写荣誉描述信息");
        }
       // Long slEpId = (Long)request.getSession().getAttribute("jsp_epId2");
        Long slEpId = StringUtil.toLong(jsp_epId2);
        /**查询同一个epID下的荣誉描述是否相同，相同则提示不必继续添加 不同 则继续增加荣誉*/
        if(null!=slEpHonor.getCertDate()||null!=slEpHonor.getCertIssuer()||null!=slEpHonor.getHonorDesc()){
            slEpHonor.setEpId(slEpId);
            slEpHonor.setUpdId(getLoginUser().getEmplId());
            this.sL2411010401Logic.updateEpHonor(slEpHonor);
            if(file.getSize()!=0){
                String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
                String uploadFileName=BusinessConst.SLPath.EPHON+slEpHonor.getHonorId();
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
            }
            super.callBack(null,"数据更新成功",response);
        }
    }

    /**
     * 删除企业荣誉信息
     * @param slEpHonor 企业荣誉
     * @param request 请求
     * @param response 响应
     * @throws IOException
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public void delete(String  jsp_epId2,SlEpHonor slEpHonor,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //if(null==request.getSession().getAttribute("jsp_epId2")){
        if(StringUtil.isNullOrEmpty(jsp_epId2)){
            throw new BusinessException("没有获取到账号信息，请重新进入");
        }
       // Long slEpId = (Long)request.getSession().getAttribute("jsp_epId2");
        Long slEpId = StringUtil.toLong(jsp_epId2);
        slEpHonor.setEpId(slEpId);
        this.sL2411010401Logic.remove(slEpHonor);
        String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpId + "/";
        String uploadFileName = BusinessConst.SLPath.EPHON+slEpHonor.getHonorId();
        SLUploadFile slUploadFile = new SLUploadFile();
        slUploadFile.deleteFileFromFtp(uploadFilePath, uploadFileName);
        super.callBack(null, "删除成功", response);
    }
}
