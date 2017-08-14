package com.msk.seller.controller;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlPdBrand;
import com.msk.seller.bean.SL2411030033Bean;
import com.msk.seller.bean.SL2411030073Bean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110107Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by fjm on 2016/2/1.
 */
@Controller
@RequestMapping("SL24110107")
public class SL24110107Controller extends BaseUploadController{

    @Autowired
    private SL24110107Logic sl24110107Logic;

    /**
     * 更新自有品牌
     * @param brand 图片名称
     * @param brandpac 图片名称
     * @param brandHonor 图片名称
     * @param response 响应
     * @param sL2411030033Bean 修改后的参数
     * @param request 请求
     * @throws IOException
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(String jsp_slCode,MultipartFile brand,MultipartFile brandpac,MultipartFile brandHonor,HttpServletResponse response,SL2411030033Bean sL2411030033Bean,HttpServletRequest request) throws IOException {
        //sL2411030033Bean.setCrtId(super.getLoginUser().getUpdId());
        //String slCode = request.getSession().getAttribute("jsp_slCode").toString();
        String slCode = StringUtil.toSafeString(jsp_slCode);
        sL2411030033Bean.setSlCode(slCode);
        sL2411030033Bean.setUpdId(getLoginUser().getEmplId());
        this.sl24110107Logic.updateBrand(sL2411030033Bean);
        this.sl24110107Logic.updateHonor(sL2411030033Bean);
        this.sl24110107Logic.updatePdBrand(sL2411030033Bean);
        if (null!=brand && brand.getSize() > 0) {
                /**保存品牌荣誉证书图片*/
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030033Bean.getEpId()+"/";
            String uploadFileName=BusinessConst.SLPath.BRAND+sL2411030033Bean.getBrandId();
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(brand,uploadFilePath,uploadFileName);
        }
        if (null!=brandHonor && brandHonor.getSize() > 0) {
            //图片上传到ftp里面
            /**保存品牌荣誉证书图片*/
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030033Bean.getEpId()+"/";
            String uploadFileName=BusinessConst.SLPath.BRANDHONOR+sL2411030033Bean.getBrandId();
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(brandHonor,uploadFilePath,uploadFileName);
        }
        if(null!=brandpac && brandpac.getSize()>0){
            /**保存包装图片*/
            String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030033Bean.getEpId()+"/";
            String uploadFileName=BusinessConst.SLPath.BRANDPAC+sL2411030033Bean.getBrandId();
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(brandpac, uploadFilePath, uploadFileName);
        }
        super.callBack(null, "保存成功", response);
    }


    /**
     * 删除自有品牌
     * @param response 响应
     * @param sl2411030033Bean 参数
     * @param request 请求
     * @throws IOException
     */
    @RequestMapping("delete")
    public void deleteBrand(String jsp_slCode,HttpServletResponse response,SL2411030033Bean sl2411030033Bean,HttpServletRequest request) throws IOException {
        //String slCode = request.getSession().getAttribute("jsp_slCode").toString();
        String slCode = StringUtil.toSafeString(jsp_slCode);
        String brandId=sl2411030033Bean.getBrandId().toString();
        String epId = sl2411030033Bean.getEpId().toString();
        List<SlPdBrand> slPdBrandList=this.sl24110107Logic.findListByEpId(brandId,epId);
        for(SlPdBrand slPdBrand : slPdBrandList){
            if(!slPdBrand.getSlCode().equals(slCode)){
                throw new BusinessException("有其他卖家在使用该品牌，无法删除");
            }
        }
        sl2411030033Bean.setUpdId(getLoginUser().getEmplId());
        this.sl24110107Logic.removePDBrand(sl2411030033Bean);
        this.sl24110107Logic.removeHonor(sl2411030033Bean);
        this.sl24110107Logic.removeBrand(sl2411030033Bean);
        super.callBack(null,"删除成功",response);
    }

    /**
     * 更新代理品牌
     * @param sl2411030073Bean 参数
     * @param response 响应
     * @throws IOException
     */
    @RequestMapping(value = "update2",method = RequestMethod.POST)
    public void update2(String jsp_slCode,SL2411030073Bean sl2411030073Bean,HttpServletResponse response) throws IOException {
        //String slCode = request.getSession().getAttribute("jsp_slCode").toString();
        String slCode = StringUtil.toSafeString(jsp_slCode);
        sl2411030073Bean.setSlCode(slCode);
        sl2411030073Bean.setUpdId(getLoginUser().getEmplId());
        this.sl24110107Logic.updateAgentPdBrand(sl2411030073Bean);
        super.callBack(null,"更新成功",response);
    }

    /**
     * 删除代理品牌
     * @param sl2411030073Bean 参数
     * @param response 响应
     */
    @RequestMapping("delete2")
    public void deleteAgentPdBrand(String jsp_slCode,SL2411030073Bean sl2411030073Bean,HttpServletResponse response) throws IOException {
        //String slCode = request.getSession().getAttribute("jsp_slCode").toString();
        String slCode = StringUtil.toSafeString(jsp_slCode);
        sl2411030073Bean.setSlCode(slCode);
        sl2411030073Bean.setUpdId(getLoginUser().getEmplId());
        this.sl24110107Logic.deleteAgentPdBrand(sl2411030073Bean);
        super.callBack(null,"删除成功",response);
    }
}
