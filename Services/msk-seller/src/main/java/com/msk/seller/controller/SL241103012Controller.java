package com.msk.seller.controller;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.bean.SlEpDdBean;
import com.msk.seller.logic.SL241103012Logic;
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
@RequestMapping("SL241103012")
public class SL241103012Controller extends BaseUploadController{

    @Autowired
    private SL241103012Logic sl241103012Logic;

    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(){
        return "sl/SL241103012";
    }

    /**
     *
     * @param slEpDdBean slEpDdBean
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void save(String jspSL241103012_epId,MultipartFile slEpDdFile,SlEpDdBean slEpDdBean,HttpServletRequest request,HttpServletResponse response)throws IOException{
        //if(null==request.getSession().getAttribute("jsp_epId") || "".equals(request.getSession().getAttribute("jsp_epId"))){
        if(StringUtil.isNullOrEmpty(jspSL241103012_epId)){
            throw new BusinessException("请先创建账号信息");
        }
        /**获取用户存储的当前epID*/
        //Long slEpId = (Long)request.getSession().getAttribute("jsp_epId");
        Long slEpId = StringUtil.toLong(jspSL241103012_epId);

        //slEpDdBean.setCrtId(super.getLoginUser().getCrtId());
        slEpDdBean.setCrtId(getLoginUser().getEmplId());
        int num=sl241103012Logic.saveData(slEpId,slEpDdBean);
        /**保存实验室图片*/
        if(slEpDdFile.getSize()!=0){
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+slEpId+"/";
            String uploadFileName=BusinessConst.SLPath.EPTESTING+slEpDdBean.getDdId();
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(slEpDdFile,uploadFilePath,uploadFileName);
        }
        super.callBack(null,"保存成功",response);
    }
}
