package com.msk.seller.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEpCap;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110300402Logic;
import com.msk.seller.logic.SL24110300502Logic;
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
@RequestMapping("SL24110300502")
public class SL24110300502Controller extends BaseUploadController {

    @Autowired
    private SL24110300502Logic sL24110300502Logic;
    @Autowired
    private SL24110300402Logic sL24110300402Logic;
    /** 库容*/
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void save(String jspSL24110300502_epId,MultipartFile file,SlEpCap slEpCap,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //if(null==request.getSession().getAttribute("jsp_epId") || "".equals(request.getSession().getAttribute("jsp_epId"))){
        if(StringUtil.isNullOrEmpty(jspSL24110300502_epId)){
            throw new BusinessException("请先创建账号信息");
        }
        /**获取用户存储的当前epID*/
        //Long slEpId = (Long)request.getSession().getAttribute("jsp_epId");
        Long slEpId = StringUtil.toLong(jspSL24110300502_epId);

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
            /** 库容 */
            slEpCap.setUpdId(getLoginUser().getEmplId());
            this.sL24110300502Logic.updateSlEpCap(slEpCap);
        }
        if(file.getSize()!=0){
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
            String uploadFileName=BusinessConst.SLPath.EPWAREHOUSE;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(file,uploadFilePath,uploadFileName);
        }
        super.callBack(null,"上传成功",response);
    }
}
