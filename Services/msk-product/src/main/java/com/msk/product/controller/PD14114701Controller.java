package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.web.utils.SessionManger;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.LoginUser;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141147Bean;
import com.msk.product.logic.PD14114701Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品原种饲养标准指标controller
 *
 * @author FJM
 */
@Controller
@RequestMapping(value = "pd14114701")
public class PD14114701Controller extends BaseUploadController {
    @Autowired
    private PD14114701Logic pd14114701Logic;
    /** 图片文件后缀 */
    private static String[] IMG_SUFFIXS = { "png", "jpg", "jpeg", "bmp", "gif", "PNG", "JPG", "JPEG", "BMP", "GIF" };
    /** 图片文件后缀 */
    private static long IMG_SIZE = 20 * 1024 * 1024 ;


    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author FJM
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classestreeCode", required = false) String classestreeCode,
                       @RequestParam(value = "breedName", required = false) String breedName,
                       @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "machiningCode", required = false) String machiningCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode,
                       @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "weightCode", required = false) String weightCode) {
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", classestreeCode);
        PdStandard standBean = this.pd14114701Logic.findOne(param);
        if (standBean == null) {
            throw new BusinessException("数据异常,请联系管理员(standardId找不到)!");
        }

        // 查询产品饲养标准指标
        String standardId = standBean.getStandardId().toString();
        model.addAttribute("standardId", standardId);
        param.setFilter("standardId", standardId);
        //获取产品产品中源 和产品品种显示页面数量 xhy start
        List<PD141124showNameBean> showName= this.pd14114701Logic.findBreedNameAndFea(param);
        if(showName.size()> NumberConst.IntDef.INT_ZERO){
            PD141124showNameBean names = new PD141124showNameBean();
            String feaNames  = "";
            for(PD141124showNameBean beans:showName){
                feaNames += beans.getFeatureNames()+"/";
            }
            names.setFeatureNames(feaNames.substring(NumberConst.IntDef.INT_ZERO, feaNames.length() - NumberConst.IntDef.INT_ONE));
            model.addAttribute("feaNames",names.getFeatureNames());
        }
        model.addAttribute("breedName", breedName);
        //end

        List<PD141147Bean> list = this.pd14114701Logic.findList(param);

        // 标准指标不存在，查询指标内容
        if (list.size() == NumberConst.IntDef.INT_ZERO) {
            List<PD141147Bean> itemList = this.pd14114701Logic.findListPdFedStd(param);
            if (itemList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PD141147Bean itemBean : itemList) {
                    list.add(itemBean);
                }
            }
        }
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);

        //查询图片
        //List<PD141147Bean> pics = this.pd14114701Logic.findPicList(standardId);

        model.addAttribute("list", list);
        return "pd/PD14114701";
    }


    public void changePicType(MultipartFile file){

        Boolean checkFileSuffixResult = super.checkFileSuffix(file, IMG_SUFFIXS);
        if (!checkFileSuffixResult) {
            throw new BusinessException("上传文件格式不正确");
        }
        Boolean checkFileSizeResult = super.checkFileSize(file, IMG_SIZE);
        if (!checkFileSizeResult) {
            throw new BusinessException("上传文件过大");
        }
    }
    /**
     * 保存修改数据
     *
     * @param model model
     * @param bean PD141147Bean
     *
     * @return 父画面
     * @author FJM
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public void save(HttpServletResponse response,Model model, PD141147Bean bean, @RequestParam(value = "classesCode", required = false) String classesCode) throws Exception{
        BaseParam param = new BaseParam();
        //this.setCommonParam(param);
        // 1.检查文件是否是图片和图片的大小
        for (int i = 0; i < bean.getFedStdItemIdArray().length; i++) {


            if((bean.getFedGoodValFile1()[i]).getSize()>0){
                changePicType(bean.getFedGoodValFile1()[i]);
            }
            if((bean.getFedGoodValFile2()[i]).getSize()>0){
                changePicType(bean.getFedGoodValFile2()[i]);
            }
            if((bean.getFedGoodValFile3()[i]).getSize()>0){
                changePicType(bean.getFedGoodValFile3()[i]);
            }
            if((bean.getFedGoodValFile4()[i]).getSize()>0){
                changePicType(bean.getFedGoodValFile4()[i]);
            }

            if((bean.getFedNormalValFile1()[i]).getSize()>0){changePicType(bean.getFedNormalValFile1()[i]);}
            if((bean.getFedNormalValFile2()[i]).getSize()>0){changePicType(bean.getFedNormalValFile2()[i]);}
            if((bean.getFedNormalValFile3()[i]).getSize()>0){changePicType(bean.getFedNormalValFile3()[i]);}
            if((bean.getFedNormalValFile4()[i]).getSize()>0){changePicType(bean.getFedNormalValFile4()[i]);}

            if((bean.getFedBadValFile1()[i]).getSize()>0){changePicType(bean.getFedBadValFile1()[i]);}
            if((bean.getFedBadValFile2()[i]).getSize()>0){changePicType(bean.getFedBadValFile2()[i]);}
            if((bean.getFedBadValFile3()[i]).getSize()>0){changePicType(bean.getFedBadValFile3()[i]);}
            if((bean.getFedBadValFile4()[i]).getSize()>0){changePicType(bean.getFedBadValFile4()[i]);}


        }
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
       super.setCommonParam(param);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        this.pd14114701Logic.saveAndEdit(bean, param);
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        super.callBack(null, "保存成功", response);
    }

/*
    */
/** BaseController中没有该判断 request是根据控制层获取*//*

    */
/**
     * 获得登陆用户信息
     *
     * @return 登陆信息
     *//*

    protected LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SessionManger.getSessionManger(request)
                .getValue(SessionManger.USER_SESSION_KEY);
        if (loginUser == null) {
            throw new BusinessException("登录信息失效，请重新登录！");
        }
        return loginUser;
    }

    */
/**
     * 设置共通字段
     *
     * @param param Param
     *//*

    protected void setCommonParam(BaseParam param) {
        LoginUser loginUser = this.getLoginUser();
        param.setCrtId(loginUser.getEmplId());
        param.setUpdId(loginUser.getEmplId());
        param.setActId(loginUser.getEmplId());
        param.setUserType(loginUser.getUserType());
    }
*/
}

