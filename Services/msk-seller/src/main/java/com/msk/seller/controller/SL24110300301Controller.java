package com.msk.seller.controller;

/**
 * Created by fjm on 2016/1/26.
 */

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEpCert;
import com.msk.core.entity.SlEpCertItem;
import com.msk.core.entity.SlMstCert;
import com.msk.core.entity.SlMstCertItem;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110300301Logic;
import com.msk.seller.utils.BusinessConst;
import freemarker.ext.beans.StringModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("SL24110300301")
/**
 * 初始化页面数据，根据certId查询该证照下的所有的项目名称
 *
 */
public class SL24110300301Controller extends BaseUploadController{
    List<SlMstCertItem> slMstCertItems=new ArrayList<>();
    @Autowired
    private SL24110300301Logic sl24110300301Logic;
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(String certId,String jsp_epId,Model model){
       // if(null==request.getSession().getAttribute("jsp_epId") || "".equals(request.getSession().getAttribute("jsp_epId"))){
        if(StringUtil.isNullOrEmpty(jsp_epId)){
            throw new BusinessException("请先创建账号信息");
        }
        if(StringUtil.isNullOrEmpty(certId)){
            throw new BusinessException("不存在的证照号");
        }
       // Long epId = (Long)request.getSession().getAttribute("jsp_epId");
        Long epId = StringUtil.toLong(jsp_epId);

        /**保证同名证照不重复*/
        SlEpCert slEpCert2 = this.sl24110300301Logic.findSlEpCertById(epId,certId.toString());
        if(null!=slEpCert2){
            throw new BusinessException("该证照已经存在，注册完成后，您可以在编辑页面进行修改");
        }
        /**根据certId查询详细的证照信息*/
        slMstCertItems= this.sl24110300301Logic.findSLMstCertItemList(certId);
        if(null==slMstCertItems){
            throw  new BusinessException("没有获取该证照的详细信息");
        }

        model.addAttribute("slMstCertItems",slMstCertItems);
        model.addAttribute("jsp_epId",jsp_epId);
        return "sl/SL24110300301";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void save(MultipartFile file,HttpServletRequest request,HttpServletResponse response,String[] certItemValue,String crtId,String jsp_epId) throws IOException {
        //if(null==request.getSession().getAttribute("jsp_epId")){
        if(StringUtil.isNullOrEmpty(jsp_epId)){
            throw new BusinessException("请先创建账号信息");
        }
        //Long epId=(Long)request.getSession().getAttribute("jsp_epId");
        Long epId = StringUtil.toLong(jsp_epId);
        SlEpCert slEpCert = new SlEpCert();
        Long certId = slMstCertItems.get(0).getCertId();
        slEpCert.setCertId(certId);
        slEpCert.setEpId(epId);
        /**保证同名证照不重复*/
        SlEpCert slEpCert2 = this.sl24110300301Logic.findSlEpCertById(epId,certId.toString());
        if(null!=slEpCert2){
            throw new BusinessException("该证照已经存在，注册完成后，您可以在编辑页面进行修改");
        }
        /**根据certId查询证照名称*/
        SlMstCert slMstCert=this.sl24110300301Logic.findCertNameByCertId(certId);
        if(null==slMstCert){
            throw new BusinessException("不存在的证照名称，请联系管理员");
        }
        slEpCert.setCertName(slMstCert.getCertName());
        /**查询证照的最大certSeq+1*/
        Long maxCertSeq=this.sl24110300301Logic.findMaxCertSeq(epId);
        slEpCert.setCertSeq(maxCertSeq);

        //Modified by xia_xiaojie on 2016/6/22. Modified start.
        //String crtId = super.getLoginUser().getCrtId();
        slEpCert.setCrtId(crtId);
        //Modified end.

        /**保存卖家企业证照信息*/
        this.sl24110300301Logic.saveSlEpCert(slEpCert);
        List<SlEpCertItem> slEpCertItems=new ArrayList<SlEpCertItem>();
        for(int i =0;i<slMstCertItems.size();i++){
            SlEpCertItem slEpCertItem = new SlEpCertItem();
            slEpCertItem.setEpId(epId);
            slEpCertItem.setCertItemValue(certItemValue[i]);
            slEpCertItem.setCertId(certId);
            slEpCertItem.setCertItemName(slMstCertItems.get(i).getCertItemName());
            slEpCertItem.setCertSeq(maxCertSeq);
            slEpCertItem.setCertItemId(slMstCertItems.get(i).getCertItemId());

            //Modified by xia_xiaojie on 2016/6/22. Modified start.
            slEpCertItem.setCrtId(crtId);
            //Modified end.

            slEpCertItems.add(slEpCertItem);
        }
        /**保存证照详细信息*/
        this.sl24110300301Logic.saveSlEpCertItem(slEpCertItems);
        if(file.getSize()!=0){
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
            String uploadFileName=BusinessConst.SLPath.CERT+certId;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(file,uploadFilePath,uploadFileName);
        }
        super.callBack(null,"保存成功",response);
    }
}
