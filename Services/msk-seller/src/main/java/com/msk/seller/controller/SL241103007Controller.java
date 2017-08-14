package com.msk.seller.controller;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEpBrand;
import com.msk.core.entity.SlPdBrand;
import com.msk.seller.bean.SL2411030033Bean;
import com.msk.seller.bean.SL2411030073Bean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL2411030071Logic;
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
 * Created by writer on 2016/1/27.
 */
@Controller
@RequestMapping("SL241103007")
public class SL241103007Controller extends BaseUploadController {

    @Autowired
    private SL2411030071Logic sL2411030071Logic;

    @RequestMapping("insert2")
    public void insert2(String jspSL241103007_slCode, HttpServletResponse response, SL2411030073Bean sL2411030073Bean, HttpServletRequest request) throws IOException {
        if(null==sL2411030073Bean.getBrandClass() || "".equals(sL2411030073Bean.getBrandClass())){
            throw new BusinessException("请选择产品类别信息");
        }
        //卖家产品品牌表插入
        //if(null==request.getSession().getAttribute("jsp_slCode") || "".equals(request.getSession().getAttribute("jsp_slCode"))){
        if (StringUtil.isNullOrEmpty(jspSL241103007_slCode)) {
            throw new BusinessException("请先创建账号信息");
        }
        // String slCode = request.getSession().getAttribute("jsp_slCode").toString();
        String slCode = StringUtil.toSafeString(jspSL241103007_slCode);

        //从企业产品品牌表查出分销授权合同号下对应的品牌id
        if (null == sL2411030073Bean.getContractNo()) {
            throw new BusinessException("代理及分销授权合同号不能为空");
        }
        Long id = sL2411030073Bean.getProducerEpId();
        SlPdBrand s = sL2411030071Logic.selectBrandId(sL2411030073Bean.getBrandName(), id);
        if (null == s) {
            throw new BusinessException("不存在的卖家产品品牌");
        }
        sL2411030073Bean.setBrandId(s.getBrandId());
        sL2411030073Bean.setSlCode(slCode);
        sL2411030073Bean.setBrandType(2);
        //sL2411030073Bean.setCrtId(super.getLoginUser().getCrtId());
        //Modified by xia_xiaojie on 2016/7/1. Modified start.
//        sL2411030073Bean.setDelFlg("1");
        //Modified end.

        sL2411030073Bean.setBrandEpId(sL2411030073Bean.getProducerEpId());
        SlPdBrand slPdBrand = this.sL2411030071Logic.findSlPdBrand(sL2411030073Bean);
        if (null != slPdBrand) {
            throw new BusinessException("对不起，您录入了重复的品牌，请重新录入");
        }
        sL2411030073Bean.setCrtId(getLoginUser().getEmplId());
        this.sL2411030071Logic.saveSlBrand(sL2411030073Bean);
        super.callBack(null, "保存成功", response);
    }


    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public void insert(String jspSL241103007_epId, String jspSL241103007_slCode, MultipartFile brandCertFile, MultipartFile fileBrand, MultipartFile brandFile, HttpServletResponse response, SL2411030073Bean sL2411030073Bean, SL2411030033Bean sL2411030033Bean, HttpServletRequest request) throws IOException {
        if(null==sL2411030073Bean.getBrandClass() || "".equals(sL2411030073Bean.getBrandClass())){
            throw new BusinessException("请选择产品类别信息");
        }
        //验证是否为重复保存
        //根据brandName和epId来查找数据库是否存在
        // if(null==request.getSession().getAttribute("jsp_epId") || "".equals(request.getSession().getAttribute("jsp_epId"))){
        if (StringUtil.isNullOrEmpty(jspSL241103007_epId)) {
            throw new BusinessException("请先创建账号信息");
        }
        //Long epid = (Long) request.getSession().getAttribute("jsp_epId");
        Long epid = StringUtil.toLong(jspSL241103007_epId);

        String brandNo = sL2411030073Bean.getBrandNo();
        List<SlEpBrand> s = this.sL2411030071Logic.checkBrand(brandNo);
        //品牌已经存在 告知用户重复了
        if (s.size() > 0) {
            throw new BusinessException("商标注册证已经存在！请重新填写！");
        } else {
            //不存在账户，那么就新增即可
            //if (null == request.getSession().getAttribute("jsp_slCode") || "".equals(request.getSession().getAttribute("jsp_slCode"))) {
            if (StringUtil.isNullOrEmpty(jspSL241103007_slCode)) {
                throw new BusinessException("请先创建账号信息");
            }
           // String slCode = request.getSession().getAttribute("jsp_slCode").toString();
            String slCode = StringUtil.toSafeString(jspSL241103007_slCode);
            Long epId = epid;
            sL2411030073Bean.setEpId(epId);
            //根据epid查询brandid，进行设置
            SlEpBrand brand = this.sL2411030071Logic.maxBrandByEpId(epId);
            Long brandId = 1L;
            if (brand == null) {
                sL2411030033Bean.setBrandId(brandId);
                sL2411030073Bean.setBrandId(brandId);
            } else {
                if (brand.getBrandId() + 1L > 9) {
                    throw new BusinessException("企业产品品牌不能超过9个");
                }
                brandId = brand.getBrandId() + 1L;
                sL2411030033Bean.setBrandId(brandId);
                sL2411030073Bean.setBrandId(brandId);
            }

            //品牌荣誉表中，根据上面设置的epId和Brand_id,查询honor_id。如果为0设置为1，否则+1
            /**现在一个品牌对应一个荣誉*/
            sL2411030033Bean.setHonorId(1);
            sL2411030073Bean.setEpId(epId);
            sL2411030033Bean.setEpId(epId);
            //sL2411030033Bean.setCrtId(super.getLoginUser().getCrtId());
            //sL2411030073Bean.setCrtId(super.getLoginUser().getCrtId());
            sL2411030073Bean.setCrtId(getLoginUser().getEmplId());
            this.sL2411030071Logic.saveBr(sL2411030073Bean);
            this.sL2411030071Logic.saveHr(sL2411030033Bean);
            sL2411030073Bean.setBrandType(1);
            sL2411030073Bean.setSlCode(slCode);
            sL2411030073Bean.setBrandEpId(epId);
            this.sL2411030071Logic.savePd(sL2411030073Bean);

            if (null != brandCertFile && brandCertFile.getSize() > 0) {
                //图片上传到ftp里面
                /**保存品牌荣誉证书图片*/
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.BRAND + brandId;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(brandCertFile, uploadFilePath, uploadFileName);
            }
            if (null != fileBrand && fileBrand.getSize() > 0) {
                //图片上传到ftp里面
                /**保存品牌荣誉证书图片*/
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.BRANDHONOR + brandId;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(fileBrand, uploadFilePath, uploadFileName);
            }
            if (null != brandFile && brandFile.getSize() > 0) {
                /**保存包装图片*/
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.BRANDPAC + brandId;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(brandFile, uploadFilePath, uploadFileName);
            }
            super.callBack(null, "保存成功", response);
        }
    }
}
