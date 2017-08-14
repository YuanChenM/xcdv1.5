package com.msk.seller.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.consts.BussinessConst;
import com.msk.core.entity.SlEpAgentAuth;
import com.msk.core.entity.SlEpBrand;
import com.msk.core.entity.SlPdBrand;
import com.msk.seller.bean.SL2411030033Bean;
import com.msk.seller.bean.SL2411030073Bean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL2411030071Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by writer on 2016/2/2.
 */
@Controller
@RequestMapping("SL2411030071")
public class SL2411030071Controller extends BaseUploadController{


    @Autowired
    private SL2411030071Logic sL2411030071Logic;
    @RequestMapping("init")
    public  String init(Model model){
        List<SlEpAgentAuth> proxyBean = this.sL2411030071Logic.findSlEpAgentAuth();
        model.addAttribute("agent",new SL2411030073Bean());
        model.addAttribute("proxyBean1",proxyBean);
        return "sl/SL2411030071";
    }

    /**
     * 编辑界面的新增操作，新增自有品牌
     * @param brandCertFile 品牌证书图片
     * @param file 品牌荣誉证书图片
     * @param brandFile 品牌包装图片
     * @param response 响应
     * @param sL2411030073Bean 接收
     * @param sL2411030033Bean 接收
     * @param request 获取session信息
     * @throws IOException 异常抛出
     */
    @RequestMapping("insert")
    public void insert(String jsp_epIdUp2,String jsp_slCodeUp,MultipartFile brandCertFile,MultipartFile file,MultipartFile brandFile,HttpServletResponse response,SL2411030073Bean sL2411030073Bean,SL2411030033Bean sL2411030033Bean,HttpServletRequest request) throws IOException {
        if(null==sL2411030073Bean.getBrandClass() || "".equals(sL2411030073Bean.getBrandClass())){
            throw new BusinessException("请选择产品类别信息");
        }
        //验证是否为重复保存
        //根据brandName和epId来查找数据库是否存在
       // if(null==request.getSession().getAttribute("jsp_epId2") || "".equals(request.getSession().getAttribute("jsp_epId2"))){
        if(StringUtil.isNullOrEmpty(jsp_epIdUp2)){
            throw new BusinessException("请重新进入");
        }
        //Long epid = (Long)request.getSession().getAttribute("jsp_epId2");
        Long epid = StringUtil.toLong(jsp_epIdUp2);
        String brandNo = sL2411030073Bean.getBrandNo();
        List<SlEpBrand> s= this.sL2411030071Logic.checkBrand(brandNo);
        //品牌已经存在 告知用户重复了
        if (s.size()>0) {
            throw new BusinessException("商标注册证已经存在！请重新填写！");
        }else {
            //不存在账户，那么就新增即可
            //if(null==request.getSession().getAttribute("jsp_slCode") || "".equals(request.getSession().getAttribute("jsp_slCode"))){
            if(StringUtil.isNullOrEmpty(jsp_slCodeUp)){
                throw new BusinessException("请先创建账号信息");
            }
           // String slCode = request.getSession().getAttribute("jsp_slCode").toString();
            String slCode = StringUtil.toSafeString(jsp_slCodeUp);
            Long epId =epid;
            sL2411030073Bean.setEpId(epId);
            //根据epid查询brandid，进行设置
            SlEpBrand brand = this.sL2411030071Logic.maxBrandByEpId(epId);
            Long brandId=1L;
            if (brand == null) {
                sL2411030033Bean.setBrandId(brandId);
                sL2411030073Bean.setBrandId(brandId);
            } else {
                if(brand.getBrandId()+1L>9){
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

            if (brandCertFile.getSize() > 0) {
                //图片上传到ftp里面
                /**保存品牌荣誉证书图片*/
                String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epid+"/";
                String uploadFileName=BusinessConst.SLPath.BRAND+brandId;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(brandCertFile,uploadFilePath,uploadFileName);
            }
            if (file.getSize() > 0) {
                //图片上传到ftp里面
                /**保存品牌荣誉证书图片*/
                String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epid+"/";
                String uploadFileName=BusinessConst.SLPath.BRANDHONOR+brandId;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(file,uploadFilePath,uploadFileName);
            }
            if(brandFile.getSize()>0){
                /**保存包装图片*/
                String uploadFilePath= BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epid+"/";
                String uploadFileName=BusinessConst.SLPath.BRANDPAC+brandId;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(brandFile, uploadFilePath, uploadFileName);
            }
            super.callBack(null, "保存成功", response);
        }
    }

    @RequestMapping("insert2")
    public void insert2(String jsp_slCodeUp,HttpServletResponse response,SL2411030073Bean sL2411030073Bean,HttpServletRequest request) throws IOException {
        //卖家产品品牌表插入
       // if(null==request.getSession().getAttribute("jsp_slCode") || "".equals(request.getSession().getAttribute("jsp_slCode"))){

        //Modify be started in 2016/9/6
        if(StringUtil.isEmpty(sL2411030073Bean.getBrandClass())){
             throw new BusinessException("请选择产品类别信息");
         }
        //Modify be end in 2016/9/6
        if(StringUtil.isNullOrEmpty(jsp_slCodeUp)){
            throw new BusinessException("请先创建账号信息");
        }
        //String slcode = request.getSession().getAttribute("jsp_slCode").toString();
        String slcode = StringUtil.toSafeString(jsp_slCodeUp);
        if(null==sL2411030073Bean.getContractNo()){
            throw new BusinessException("代理及分销授权合同号不能为空");
        }
        Long id=sL2411030073Bean.getProducerEpId();

        //Modify be start in 2016/9/6
        if(null == id || "".equals(id)){
            throw new BusinessException("请先选择生产商名称");
        }
        //Modify be end in 2016/9/5
            SlPdBrand s=sL2411030071Logic.selectBrandId(sL2411030073Bean.getBrandName(),id);
        if(null==s){
            throw new BusinessException("不存在的卖家产品品牌");
        }
        sL2411030073Bean.setBrandId(s.getBrandId());
        sL2411030073Bean.setSlCode(slcode);
        sL2411030073Bean.setBrandType(2);
        //sL2411030073Bean.setCrtId(super.getLoginUser().getCrtId());
        sL2411030073Bean.setVer(NumberConst.IntDef.INT_ONE);

        //Modified by xia_xiaojie on 2016/7/1. Modified start.
        sL2411030073Bean.setDelFlg("0");
        //Modified end.

        sL2411030073Bean.setBrandEpId(sL2411030073Bean.getProducerEpId());
        SlPdBrand slPdBrand=this.sL2411030071Logic.findSlPdBrand(sL2411030073Bean);
        if(null!=slPdBrand){
            throw new BusinessException("对不起，您录入了重复的品牌，请重新录入");
        }
        sL2411030073Bean.setCrtId(getLoginUser().getEmplId());
        this.sL2411030071Logic.saveSlBrand(sL2411030073Bean);
        super.callBack(null, "保存成功", response);
    }

    protected void callBack2(String account,String callbackFun, String message, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<script type='text/javascript'>");
        writer.write("window.parent.");
        if(StringUtil.isEmpty(callbackFun)){
            writer.write("callbackFun2('"+message+"','"+account+"')");
        }else{
            writer.write(callbackFun+"(");
            if(!StringUtil.isEmpty(message)){
                writer.write("'"+message+"'");
            }
            writer.write(")");
        }
        writer.write("</script>");
    }
}
